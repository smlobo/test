#!/usr/bin/env python3

class A:
    def who(self):
        print("A")
        print(A.mro())
        print("A who bye")


class B(A):
    def who(self):
        print("B")
        print(B.mro())
        super().who()
        print("B who bye")


class C(A):
    def who(self):
        print("C")
        print(C.mro())
        super().who()
        print("C who bye")


class D(B, C):
    def who(self):
        print("D")
        print(D.mro())
        super().who()


# D().who()
d = D()
d.who()
print(D.mro())
