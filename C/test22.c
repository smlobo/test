// Min and max of an array in 3n/2 comparisons
// Divide and conquer

struct mm {
  int min;
  int max;
};

struct mm min_and_max(int *a, int l, int u) {
  if ((u - l + 1) <= 2) {
    struct mm r;
    if (a[l] < a[u]) {
      r.min = a[l];
      r.max = a[u];
    }
    else {
      r.min = a[u];
      r.max = a[l];
    }
    return r;
  }
  else {
    struct mm ll = min_and_max(a, l, (u+l)/2);
    struct mm uu = min_and_max(a, (u+l)/2+1, u);

    struct mm r;
    if (ll.min < uu.min)
      r.min = ll.min;
    else
      r.min = uu.min;
    if (ll.max < uu.max)
      r.max = uu.max;
    else
      r.max = ll.max;

    return r;
  }
}

