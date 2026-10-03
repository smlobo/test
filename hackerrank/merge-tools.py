#!/usr/bin/env python3

def get_unique(string):
    n = len(string)
    ret_string = ""
    char_set = set()
    for i in range(n):
        current = string[i]
        if current in char_set:
            continue
        char_set.add(current)
        ret_string += current
    return ret_string

def merge_the_tools(string, k):
    # your code goes here

    num_subsets = int(len(string) / k)
    index = 0

    for i in range(num_subsets):
        substring = string[index:index+k]
        unique_substring = get_unique(substring)
        print("substring: {}".format(unique_substring))
        index += k

if __name__ == '__main__':
    # string, k = input(), int(input())

    # test 1
    string = 'AABCAAADA'
    k = 3
    merge_the_tools(string, k)