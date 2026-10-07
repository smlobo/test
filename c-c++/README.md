# C and C++ synchronization, side by side

Read the matching `mutex_demo`, `semaphore_demo`, `condition_demo`, and
`atomic_demo` functions in `c_examples.c` and `cpp_examples.cpp`.
Both programs perform the same operations and print the same results.

## Build and run

Requires a C11 compiler and a C++20 compiler/library with `std::jthread` and
`std::counting_semaphore`. The C program uses POSIX threads and semaphores,
so it targets macOS/Linux rather than native Windows.

```sh
make run
```

To use GCC with a sufficiently recent C++ standard library:

```sh
make clean
make CC=gcc CXX=g++ run
```

Each program prints:

```text
mutex: counter = 200000 (expected 200000)
semaphore: consumed 3 permits posted before the consumer started
condition variable: payload = 42 (expected 42)
atomic: counter = 200000 (expected 200000)
atomic release/acquire: payload = 42 (expected 42)
```

## What each primitive means

| Primitive | State it keeps | What it guarantees | Typical use |
| --- | --- | --- | --- |
| Mutex | Locked/unlocked, with ownership | One thread at a time enters the protected region | Protect a container or several related fields |
| Semaphore | Number of available permits | Each successful wait consumes one permit; posting adds permits | Count available resources or queued work |
| Condition variable | No saved notifications | Lets a thread sleep until it can recheck shared state under a mutex | Wait for a queue to become nonempty |
| Atomic | A single atomic object's value | Indivisible operations on that object; ordering depends on memory order | Counters, flags, and carefully designed concurrent algorithms |

## C APIs versus C++ APIs

| Operation | C example | C++ example |
| --- | --- | --- |
| Threads | `pthread_create`, `pthread_join` | `std::jthread`, joins on destruction |
| Lock a mutex | `pthread_mutex_lock` | `std::lock_guard` constructor |
| Unlock a mutex | Explicit `pthread_mutex_unlock` | Guard destructor at scope exit |
| Semaphore wait | `sem_wait` | `acquire` |
| Add a permit | `sem_post` | `release` |
| Wait on a condition | `pthread_cond_wait` inside a `while` loop | `condition.wait(lock, predicate)` |
| Notify a waiter | `pthread_cond_signal` | `notify_one` |
| Atomic type | `atomic_int`, `atomic_bool` from `<stdatomic.h>` | `std::atomic<T>` from `<atomic>` |
| Atomic increment | `atomic_fetch_add_explicit` | `fetch_add` |

C11 also defines optional standard threading APIs in `<threads.h>` (`mtx_t`,
`cnd_t`, etc.). These examples use the widely available POSIX APIs on macOS;
the C atomics are standard C11. C has no standard counting-semaphore API.
C++ mutexes, condition variables, and atomics arrived in C++11; semaphores
and `std::jthread` arrived in C++20.

## 1. Mutex: protect an operation or invariant

Two threads each increment an ordinary integer 100,000 times. Every increment
happens while holding the same mutex. Unlocking and later locking that mutex
also provides synchronization, so the next owner sees the protected writes.

An ordinary `++counter` is a read-modify-write sequence, not an atomic action.
Removing the mutex would introduce a data race and undefined behavior, not
merely a guaranteed smaller result.

In C, matching every successful lock with an unlock is your responsibility.
In C++, `std::lock_guard` uses RAII: its destructor unlocks on scope exit,
including early returns and exception unwinding. The guard sits inside the
loop so it locks one increment at a time.

A mutex has ownership: the owning thread must unlock it. A semaphore has no
such ownership rule; one thread can acquire a permit and another can release
one. A semaphore initialized to one is therefore not identical to a mutex.

## 2. Semaphore: permits survive until consumed

Both examples start at zero, add three permits, and only then start the
consumer. The consumer can complete three waits even though nobody was
waiting when the permits were added. A fourth wait would block until another
permit was posted. This intentionally demonstrates saved permits rather
than relying on thread scheduling or sleeps.

POSIX `sem_wait` may be interrupted by a signal, so the C code retries on
`EINTR`. C++ `acquire` does not expose that POSIX error-code protocol.

The C code uses a named POSIX semaphore because macOS does not support
unnamed POSIX semaphores via `sem_init`. It immediately unlinks the name;
the open semaphore remains usable until closed. The name includes the
process ID and `O_EXCL` prevents opening an existing semaphore accidentally.
The C++ semaphore is an ordinary local object, destroyed after the consumer
joins. The `<3>` argument specifies a required minimum supported maximum;
the implementation may support a larger maximum. Neither API permits
unbounded posting beyond its supported limit.

## 3. Condition variable: wait for a predicate, not a notification

The consumer wants `ready == true`. Both `ready` and `payload` are protected
by the same mutex. Waiting atomically releases the mutex and enters the
wait; before returning, the wait reacquires the mutex.

The C `while (!ready)` loop and C++ predicate overload express the same rule:
recheck the predicate after waking. A wake can be spurious, or another
consumer might have changed the shared state before this thread reacquires
the lock. An `if` is insufficient.

The producer updates the state while holding the mutex, unlocks, then
notifies. If it finishes before the consumer reaches the wait, the consumer
sees `ready == true` and skips waiting. The notification is not saved; the
predicate is. This is why a condition variable needs associated shared state,
and why notifying without changing that state does not make progress here.

C++ uses `std::unique_lock` for waiting because it supports the temporary
unlock/relock that `std::condition_variable` requires; `std::lock_guard` does
not offer that interface.

## 4. Atomics: indivisible updates and explicit ordering

The first atomic example replaces the protected counter with an atomic
counter. `fetch_add` is one atomic read-modify-write operation, so increments
cannot overwrite each other. Relaxed ordering is sufficient: the counter
does not publish other data, and both workers join before the final read.

Splitting the increment into an atomic load followed by an atomic store
would still allow lost updates. Each operation would be atomic, but their
combination would not be one indivisible increment.

The second example publishes an ordinary, non-atomic payload:

1. The producer writes `payload = 42`.
2. It stores `true` to the flag with release ordering.
3. The consumer loads the flag with acquire ordering until it reads `true`.
4. The consumer can now safely read the payload.

The acquire that reads the release synchronizes the threads: the earlier
payload write happens before the consumer reads it. Replacing both orders
with relaxed would remove this guarantee and cause a data race on the
payload. The payload is deliberately read before joining the producer so
this example demonstrates release/acquire rather than join synchronization.

The flag is a one-shot handoff. The producer must not keep modifying the
payload after publication without another synchronization protocol.

These tiny publication examples busy-wait for clarity; use blocking waits
for potentially long delays. C++20 also offers atomic `wait`/`notify_one`.
Atomics are not necessarily lock-free, and `volatile` does not replace an
atomic or a mutex. Default atomic operations use sequentially consistent
ordering; the examples specify weaker orders only where sufficient.

## Lifetime and error handling

C explicitly joins threads and destroys/closes synchronization resources.
POSIX thread functions return error numbers directly; semaphore functions
use `errno`. The helpers demonstrate that distinction and terminate on errors.
C++ uses scoped objects: `std::jthread` requests stop and joins on destruction
(these workers finish naturally), and lock guards release mutexes. Workers
are destroyed before the objects they reference. C++ thread/mutex operations
may report failures through exceptions; these examples allow those to
terminate the program rather than adding a recovery policy.
