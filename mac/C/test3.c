// 2D array in spiral

int xx[][4] = {{1,2,3,4},
	       {5,6,7,8},
	       {9,10,11,12},
	       {13,14,15,16},
	       {17,18,19,20}};

void print_spiral(int a[5][4], int x, int y) {
  int ib = 1, ie = x-1;
  int jb = 0, je = y-1;
  int i = 0, j = 0;
  int n = 0, s = 0, e = 1, w = 0;
  int nn = 0;

  printf("a=%#x, *a=%#x, *(a+1)=%#x\n", a, *a, *(a+1));
  printf("xx=%#x, *xx=%#x, *(xx+1)=%#x\n", xx, *xx, *(xx+1));

  for (nn=0; nn<x*y; nn++) {
    printf("[%d][%d] %d, ", i, j, a[i][j]);

    if (e && (j<je))
      j++;
    if (w && (j>jb))
      j--;
    if (n && (i>ib))
      i--;
    if (s && (i<ie))
      i++;

    if (e && (j==je)) {
      s = 1; e = 0;
      je--;
    }
    if (w && (j==jb)) {
      n = 1; w = 0;
      jb++;
    }
    if (n && (i==ib)) {
      e = 1; n = 0;
      ib++;
    }
    if (s && (i==ie)) {
      w = 1; s = 0;
      ie--;
    }
  }
  printf("\n");
}

int main() {
  int i, j;
  for (i=0; i<5; i++) {
    for (j=0; j<4; j++) {
      printf("%d\t", xx[i][j]);
    }
    printf("\n");
  }

  print_spiral(xx, 5, 4);
}
