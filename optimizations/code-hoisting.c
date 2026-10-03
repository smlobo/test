extern void bar1(int);
extern void bar2(int);

void foo(int x, int y, int z)
{
  if (z)
    bar1(x + y);
  else
    bar2(x + y);
}
