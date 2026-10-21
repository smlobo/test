
bool foo(bool x, bool a, bool b) {
  bool r = a;
  if (x)
    r = b;
  return  r;
}

