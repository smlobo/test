#!/usr/bin/env python3

def pilable(blocks):
    # Iterate tracking the begin and end index.
    # Choose the larger block that can be piled
    begin = 0
    end = len(blocks) - 1

    # Initialize
    if blocks[begin] > blocks[end]:
        current = blocks[begin]
        begin += 1
    else:
        current = blocks[end]
        end -= 1

    while begin <= end:
        # Get the larger of the eligible blocks
        if blocks[begin] > blocks[end]:
            new = blocks[begin]
            begin += 1
        else:
            new = blocks[end]
            end -= 1

        if new > current:
            return False
        current = new

    return True

# main
if __name__ == '__main__':
    # num test cases
    n = int(input())

    for i in range(n):
        # num blocks
        x = int(input())

        blocks = list()
        string_blocks_list = input().split()
        for j in range(x):
            block = int(string_blocks_list[j])
            blocks.append(block)

        print(blocks)

        if pilable(blocks):
            print("Yes")
        else:
            print("No")
