#!/usr/bin/env python3

import math
import os
import random
import re
import sys

def epoch_secs(date_time):
    month_days = {
        "Jan": 0,
        "Feb": 31,
        "Mar": 59,
        "Apr": 90,
        "May": 120,
        "Jun": 151,
        "Jul": 181,
        "Aug": 212,
        "Sep": 243,
        "Oct": 273,
        "Nov": 304,
        "Dec": 334
    }

    parsed = re.search(r'^.* ([0-9]{2}) ([A-Z][a-z]{2}) ([0-9]{4}) ([0-9]{2}):([0-9]{2}):([0-9]{2}) (.)([0-9]{2})([0-9]{2})$', date_time)

    # print("{} {} {} {} {} {} {} {} {}".format(parsed.group(1), parsed.group(2), 
    #     parsed.group(3), parsed.group(4), parsed.group(5), parsed.group(6), 
    #     parsed.group(7), parsed.group(8), parsed.group(9)))

    # years
    year = int(parsed.group(3))
    year_delta = year - 1968
    day_delta = year_delta * 365
    # leap years
    num_leaps = int(year_delta / 4)
    # century
    num_centuries = year/100 - 20
    num_leaps -= num_centuries
    day_delta += num_leaps

    # months
    day_delta += month_days[parsed.group(2)]

    # days
    day_delta += (int(parsed.group(1)) - 1)
    # print(day_delta)

    # hours
    hour_delta = day_delta * 24
    hour_delta += int(parsed.group(4))
    # time zone
    if parsed.group(7) == "-":
        hour_delta += int(parsed.group(8))
    elif parsed.group(7) == "+": 
        hour_delta -= int(parsed.group(8))

    # minutes
    min_delta = hour_delta * 60
    min_delta += int(parsed.group(5))
    # time zone
    if parsed.group(7) == "-":
        min_delta += int(parsed.group(9))
    elif parsed.group(7) == "+": 
        min_delta -= int(parsed.group(9))

    # seconds
    sec_delta = min_delta * 60
    sec_delta += int(parsed.group(6))
    # print(sec_delta)

    return sec_delta

# Complete the time_delta function below.
def time_delta(t1, t2):
    t1_epoch = epoch_secs(t1)
    t2_epoch = epoch_secs(t2)
    return "{}".format(abs(t1_epoch - t2_epoch))

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

    # test 2
    t1 = "Sat 14 Sep 2126 00:36:44 +1400"
    t2 = "Wed 22 Jun 2050 23:18:57 -0100"
    delta = time_delta(t1, t2)
    print("{} - {} = {} seconds".format(t1, t2, delta))
