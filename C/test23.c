// angle between hrs and mins

#include <stdio.h>

int main(int argc, char **argv) {
  if (argc != 2) {
    return 1;
  }

  char *in = argv[1];
  int hr;
  int min;
  int num = 0;
  while (*in != '\0') {
    char c = *in++;
    if (c == ':') {
      hr = num;
      num = 0;
      continue;
    }
    num = num*10 + c - '0';
  }
  min = num;

  printf("hr = %d, min = %d\n", hr, min);

  // Hour angle
  int hr_angle = 0;
  if (hr != 12)
    hr_angle += 30 * hr;
  hr_angle += 0.5 * min;

  // Min angle
  int min_angle = 6 * min;

  int angle = abs(hr_angle - min_angle);

  printf("hr_angle = %d; min_angle = %d\n", hr_angle, min_angle);
  printf("angle = %d\n", angle);

  return 0;
}

