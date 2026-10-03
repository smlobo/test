#!/usr/bin/python3

class Bird:
    name = None
    color = None
    motion = None

    def express(self):
        return f"I am a {self.name}. I am {self.color} and I {self.motion}"


class Parrot(Bird):
    def __init__(self):
        self.name = "Parrot"
        self.color = "Green"
        self.motion = "fly"


class Ostrich(Bird):
    def __init__(self):
        self.name = "Ostrich"
        self.color = "Brown"
        self.motion = "run"


polly = Parrot()
print("Polly says:", polly.express())

harry = Ostrich()
print("Harry says:", harry.express())

boo = Bird()
print("Boo says:", boo.express())
