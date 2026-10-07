# A file-scope `static` duplicated across link images

Run `make test` on macOS or Linux. The test prints two distinct addresses and
the sequence `1, 1, 2, 2`. It exits with a failure if either counter's value
or the distinct-address check is wrong.

`counter.c` contains a file-scope `static int counter`. The Makefile compiles
it once to `counter.o`, then includes that same object file in both the
executable and the shared library. Each final link image therefore contains
its own storage for `counter`:

```text
counter.c -> counter.o -----> counter_test
                       \\---> libcounterdemo.dylib (macOS)
                       \\---> libcounterdemo.so    (Linux)
```

The executable calls `counter_next` directly. The shared library calls its
own `counter_next` through `shared_counter_next`. `counter_identity` exposes
the address of each copy only so the test can verify they are distinct.

The counter access functions have hidden symbol visibility. That prevents
ELF symbol interposition from redirecting the library's calls into the
executable's copy if the executable exports symbols dynamically. The
library's public `shared_counter_*` wrappers keep default visibility.

File-scope `static` gives the variable internal linkage within a translation
unit. It is **not** a process-wide singleton. Static storage duration means
each copy lives for the lifetime of its link image; it does not merge copies
linked into separate images.
