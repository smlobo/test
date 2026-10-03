#!/usr/bin/env python3

class BaseClass:
    def who(self):
        return "BaseClass"


class X(BaseClass):
    def who(self):
        return "X"


class Y(BaseClass):
    def who(self):
        return "Y"


class XY(X, Y):
    # no who()

    def useSelf(self):
        print(f"useSelf: {self.who()}")

    def useSuper(self):
        print(f"useSuper: {super().who()}")

    def useSuperX(self):
        print(f"useSuperX: {super(X, self).who()}")

    def useSuperY(self):
        print(f"useSuperY: {super(Y, self).who()}")


print(f"BaseClass: {BaseClass().who()}")
print(f"X: {X().who()}")
print(f"Y: {Y().who()}")

xy = XY()
print(f"XY: {xy.who()}")
xy.useSelf()
xy.useSuper()
xy.useSuperX()
xy.useSuperY()
