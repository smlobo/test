// Matrix with 0 and 1; find path from (0,0) to (n-1,n-1) via 0

#include <stdio.h>

int a[5][5] = {{0, 0, 0, 1, 1},
               {1, 1, 0, 0, 1},
	       {1, 1, 0, 1, 0},
	       {0, 1, 0, 0, 0},
	       {0, 0, 0, 1, 0}};

char b[5][5] = {{'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'},
                {'.','.','.','.','.'}};

int find_path(int x = 0, int y = 0) {
  if ((x >= 5) || (y >= 5) || (x < 0) || (y < 0))
    return 0;

  if (a[x][y] != 0)
    return 0;

  if (b[x][y] == 'x')
    return 0;

  b[x][y] = 'x';

  if ((x == 4) && (y == 4))
    return 1;

  printf("found 0 at: [%d, %d]\n", x, y);

  if (find_path(x+1, y) == 1)
    return 1;
  else if (find_path(x, y+1) == 1)
    return 1;
  else if (find_path(x-1, y) == 1)
    return 1;
  else if (find_path(x, y-1) == 1)
    return 1;

  b[x][y] = '.';
  return 0;
}

int main() {
  if (find_path() == 1)
    printf("Path found\n");

  for (int i=0; i<5; i++) {
    for (int j=0; j<5; j++) {
      putchar(b[i][j]);
      putchar(' ');
    }
    putchar('\n');
  }

  return 0;
}

