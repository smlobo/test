#include <stdio.h>
#include <stdlib.h>

struct Point {
    int x;
    int y;
};

int main(int argv, char **argc) {
    int n = atoi(argc[1]);
    printf("Creating an array of points: %d\n", n);

    struct Point *points = (struct Point *) malloc(n * sizeof(struct Point));

    for (int i = 0; i < n; i++) {
        points[i].x = i;
        points[i].y = i*i;
    }

    for (int i = 0; i < n; i++) {
        printf("[%d] %d, %d\n", i, points[i].x, points[i].y);
    }

    for (int i = 0; i < n; i++) {
        struct Point *point = points+i;
        printf("[%d] %d, %d\n", i, point->x, point->y);
    }

    for (int i = 0; i < n; i++) {
        struct Point *point = &(points[i]);
        printf("[%d] %d, %d\n", i, point->x, point->y);
    }

    free(points);

    return 0;
}
