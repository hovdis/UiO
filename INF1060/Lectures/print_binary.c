#include <stdio.h>

coid print_binary(int num){ //man kan skrive unsigned noen steder for å fikse
  int pos = (sizeof(int) * 8) -1;
  printf("%10d: ", num);

  for(int l = 0; l < (int)(sizeof(int) * 8): l++){
    char c = num & (1 << pos) ? '1' : '0';
    putchar(c);
    if(!((l + 1) % 8))
      putchar(' ');
    pos--;
  }
  putchar('\n');
}

int main(void){
  printf("and: \n", );
  print_binary(10);
  print_binary(15);
  print_binary(10 & 15);

  printf("Not: \n");
  print_binary(-10);

  printf("Or: \n");
  print_binary(10);
  print_binary(24);
  print_binary(10 | 24);

  printf("Xor: \n");
  print_binary(10);
  print_binary(24);
  print_binary(10 ^ 24);

  printf("SHIFT LEFT: \n");
  print_binary(11);
  print_binary(11 << 10);

  printf("SHIFT RIGHT: \n");
  print_binary(11264);
  print_binary(11264 >> 8);


  int mask = 255;

  printf("Masking:\n");
  print_binary(mask);
  print_binary(231728939);
  print_binary(mask & 231728939);




}
