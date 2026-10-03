// Implement strtok

#include <stdio.h>
#include <stdlib.h>

char *__strtok_cache;

char *my_strtok(char *str, const char *del) {
  if (str == NULL) {
    str = __strtok_cache;
  }

  if (str == NULL)
    return NULL;

  char *ret = str;
  int found = 0;

  while (*str != '\0') {
    if (*str != *del) {
      str++;
      continue;
    }

    found = 1;
    char *x = str + 1;
    char *d = (char *)del + 1;
    while ((*x != '\0') && (*d != '\0')) {
      if (*x++ != *d++) {
        found = 0;
	break;
      }
    }
      
    if (found == 0) {
      str++;
      continue;
    }

    *str = '\0';
    __strtok_cache = x;
    break;
  }

  if (found == 0) {
    __strtok_cache = NULL;
  }

  return ret;
}

int main() {
  char *str = (char *) malloc(100);

  char *p = str;
  while ((*p++ = getchar()) != '\n');

  printf("%s\n", str);

  int i = 0;
  printf("[%d] %s\n", i++, my_strtok(str, " "));
  char *t = (char *) malloc(50);
  while ((t = my_strtok(NULL, " ")) != NULL) {
    printf("[%d] %s\n", i++, t);
  }

  return 0;
}
