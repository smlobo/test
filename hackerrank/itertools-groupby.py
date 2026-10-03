#!/usr/bin/env python3

import itertools

def main():
    input_string = input()
    # print(f"Input: {input_string}")

    for k, g in itertools.groupby(input_string):
        # print(f"k: {k}; len(g): {len(list(g))}")
        print(f"({len(list(g))}, {k}) ", end="")
    print()

if __name__ == '__main__':
    main()
