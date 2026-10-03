#!/usr/local/bin/python3
import sys

if len(sys.argv) != 2:
    print(f"Usage: {sys.argv[0]} <number-of-smiths>")
    exit(1)

n = int(sys.argv[1])
print(f"Printing the first {n} smith numbers ...")


def sum_of_digits(num: int) -> int:
    d_sum = 0
    while num > 0:
        remainder = num % 10
        num //= 10
        d_sum += remainder
    return d_sum


def sum_of_prime_factor_digits(num: int) -> int:
    pfd_sum = 0

    # Prime factors (and sum of their digits)
    remainder = num
    n_count = 2
    while n_count <= remainder and n_count != num:
        # Divisible
        if remainder % n_count == 0:
            remainder //= n_count
            pfd_sum += sum_of_digits(n_count)
        else:
            n_count += 1
    
    return pfd_sum


# Keep iterating until the desired number of smiths are reached
count = 2
while n > 0:
    # Sum of the digits
    sod = sum_of_digits(count)

    # Sum of the prime factor digits
    spfd = sum_of_prime_factor_digits(count)

    # Found Smith
    if sod == spfd:
        print(f"[{n}] {count} sum of digits: {sod}")
        n -= 1

    count += 1
