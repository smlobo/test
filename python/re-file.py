#!/usr/bin/env python3

import re
import sys

def main():
    if len(sys.argv) != 2:
        print(f"Usage: {sys.argv[0]} <text-file>")
        exit(1)

    print(f"Reading file: {sys.argv[1]}")

    file_handler = open(sys.argv[1], "r")
    proper_nouns = re.findall(r'[A-Z]\w+\s', file_handler.read())
    print(f"Found {len(proper_nouns)} proper(?) nouns")
    file_handler.close()

    proper_dict = {}
    for proper_noun in proper_nouns:
        if proper_noun.rstrip() in proper_dict:
            proper_dict[proper_noun.rstrip()] += 1
        else:
            proper_dict[proper_noun.rstrip()] = 1

    proper_list = list(proper_dict.items())
    proper_list.sort(key=lambda item : item[1], reverse=True)

    for i in range(len(proper_list)):
        if i > 15:
            break
        print(f"{proper_list[i][0].ljust(8)} -> {proper_list[i][1] : >4}")

if __name__ == "__main__":
    main()
