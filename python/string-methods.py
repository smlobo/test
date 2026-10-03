#!/usr/bin/env python3

s = "  Hello123  "

print(f"{s}.lower() -> {s.lower()}")
print(f"{s}.upper() -> {s.upper()}")
print(f"{s}.strip() -> {s.strip()}")

print(f"{s}[2:7].isalpha() -> {s[2:7].isalpha()}")
print(f"{s}[2:7].isalpha() -> {s[2:7].isalpha()}")
print(f"{s}.rstrip()[-3:].isdigit() -> {s.rstrip()[-3:].isdigit()}")
print(f"{s}[:2].isspace() -> {s[:2].isspace()}")

print(f"{s}.find('ll') -> {s.find('ll')}")
print(f"{s}.replace('ll', 'xxxx') -> {s.replace('ll', 'xxxx')}")
print(f"{s}.split('l') -> {s.split('l')}")
print(f"'~~'.join({s}.split('l')) -> {'~~'.join(s.split('l'))}")
