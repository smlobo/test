#!/usr/bin/env python3

s = "Hello"

for i in range(len(s)):
    print(f"{s[i]}  ", end="")
print()
for i in range(len(s)):
    print(f"{i}  ", end="")
print()
for i in range(len(s)):
    print(f"{str(i-len(s))} ", end="")
print()

print(f"s[1:4]: {s[1:4]}")
print(f"s[1:]: {s[1:]}")
print(f"s[:]: {s[:]}")

print(f"s[-4:-1]: {s[-4:-1]}")
print(f"s[-2:]: {s[-2:]}")
print(f"s[:-1]: {s[:-1]}")