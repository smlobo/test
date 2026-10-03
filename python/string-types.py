#!/usr/bin/env python3

# Raw Strings
print("Raw, Normal, Multi ...")
raw = r"this\n\t and that"

# this\t\n and that
print(f"raw: {raw}")

normal = "this\n\t and that"
print(f"normal: {normal}")

multi = """It was the best of times.
It was the worst of times."""
# It was the best of times.
# It was the worst of times.
print(multi)

# Formatted Strings
print("\nf-strings:")
pi = 3.14159
print(f"pi = {pi: .3f}")

address_book = [{'name':'N.X.', 'addr':'15 Jones St', 'bonus': 70},
  {'name':'J.P.', 'addr':'1005 5th St', 'bonus': 400},
  {'name':'A.A.', 'addr':'200001 Bdwy', 'bonus': 5},]

# N.X.     || 15 Jones St          ||    70
# J.P.     || 1005 5th St          ||   400
# A.A.     || 200001 Bdwy          ||     5
for person in address_book:
    print(f'{person["name"]:8} || {person["addr"]:20} || {person["bonus"]:>5}')

# printf style formatting
print("\nprintf style")
# Split the line into chunks, which are concatenated automatically by Python
text = (
    "%d little pigs come out, "
    "or I'll %s, and I'll %s, "
    "and I'll blow your %s down."
    % (3, 'huff', 'puff', 'house'))
print(text)

# byte & unicode strings
print("\nByte & Unicode ...")
byte_string = b'A byte string'
print(byte_string)
ustring = 'A unicode \u018e string \xf1 \xf2 \xf3 \u0950'
print(ustring)
unicode_to_byte = ustring.encode("utf-8")
print(unicode_to_byte)
byte_to_unicode = unicode_to_byte.decode("utf-8")
print(byte_to_unicode)
