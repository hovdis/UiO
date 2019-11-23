#include <stdio.h>
#include <stdlib.h>
//Pekere:



struct person {
  int age;
};

void init_person(struct person *p, int age){
  p->age = age;
}

void funksjon(struct person p){
  printf("Alder er: %d\n", p.age);
  printf("Adresse til alder: %p\n", &p.age);
}
int main(void){
  struct person p;
  init_person(&p, 10);
  funksjon(p);

  return EXIT_SUCCESS;
}
//Dette er fra forrige forelesning.


/*
Makefile:

eksempel:
listfiles:
  ls
*Denne vil kjøre ls om vi skriver "make listfiles"*
CC=gcc
CFLAGS=-Wall -Wextra - Wpendantic -std=c99
person: person.c
  $(CC) $(CFLAGS) person.c -o person

clean:
  rm -f person

%:%.c
  $(CC) &(CFLAGS) $^ - $@
*Dette er generisk. Man skriver da "make person" hvis du vil legge til person der % er*




*/
