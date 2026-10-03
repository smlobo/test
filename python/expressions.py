#!/usr/bin/python3

# Integer
x = 10;
y = 6;
print("{} / {} = {:5.3f}".format(x, y, x/y));
print("{} / {} = int({})".format(x, y, int(x/y)));

# Float
yf = 3.1;
print("{} / {} = {:2.5f}".format(x, yf, x/yf));

# String
blah2 = "bbll";
Blah2 = "aaww";
BLAH2 = blah2 + Blah2;
print("{} + {} => {}".format(blah2, Blah2, BLAH2));
print("{} * {} => {}".format(Blah2, 3, Blah2*3));

# String conversion
mystr = "123";
strincr = int(mystr) + 1;
print("str = {} ; + 1 = {}".format(mystr, strincr));

# User input
name = input("Who r u? ");
print("Welcome {}".format(name));

