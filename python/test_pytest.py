# content of test_sample.py
def inc(x):
    return x + 1


def test_fail():
    assert inc(3) == 5


def test_pass():
    assert inc(3) == 4
