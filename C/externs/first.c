/* first.c */
#include "global.h"
#include <stdio.h>

int testInt = 5;

int notMain();

int main() {
  printf("testInt = %d\n", testInt);
  notMain();
  return 0;
}