#!/usr/bin/env python3

import os
from datetime import datetime

# Complete the time_delta function below.
def time_delta(t1, t2):
    time_format = "%a %d %b %Y %H:%M:%S %z"
    t1_obj = datetime.strptime(t1, time_format)
    t2_obj = datetime.strptime(t2, time_format)
    delta = t1_obj - t2_obj
    return "{}".format(int(abs(delta.total_seconds())))

if __name__ == '__main__':
    # fptr = open(os.environ['OUTPUT_PATH'], 'w')

    t = int(input())

    for t_itr in range(t):
        t1 = input()

        t2 = input()

        delta = time_delta(t1, t2)

        # fptr.write(delta + '\n')
        print(delta)

    # fptr.close()


    # test 0
    # t1 = "Sun 10 May 2015 13:54:36"
    # t2 = "Sun 10 May 2015 13:54:36"
    # delta = time_delta(t1, t2)
    # print("{} - {} = {} seconds".format(t1, t2, delta))

    # test 1
    t1 = "Sun 10 May 2015 13:54:36 -0700"
    t2 = "Sun 10 May 2015 13:54:36 -0000"
    delta = time_delta(t1, t2)
    print("{} - {} = {} seconds".format(t1, t2, delta))

    # test 2
    t1 = "Sat 02 May 2015 19:54:36 +0530"
    t2 = "Fri 01 May 2015 13:54:36 -0000"
    delta = time_delta(t1, t2)
    print("{} - {} = {} seconds".format(t1, t2, delta))

    # test 3
    t1 = "Sat 14 Sep 2126 00:36:44 +1400"
    t2 = "Wed 22 Jun 2050 23:18:57 -0100"
    delta = time_delta(t1, t2)
    print("{} - {} = {} seconds".format(t1, t2, delta))
