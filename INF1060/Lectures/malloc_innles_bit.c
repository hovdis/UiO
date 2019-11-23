/**
  * malloc allokerer vilkårlig store minneområder
  * eks: char *s = malloc(5000); (5000 bytes)
  * s peker nå på en char-array med lengde 5000

  * Hva hvis di vil ha en int-array?
  *   - int* arr = (int*) malloc(5000 * sizeof(int));

  * Generelt:
  * type* s = (type*) malloc(xx * sizeof(type));





  * Innlesing fra bruker:
  * vi trenger stdio(print etc.) og stdlib(malloc)
  * file get string: fgets(s, read_size, stdin);
  *   -s = en string
      -read_size = hvor mye vi skal lese
      -stdin = hvor vi skal lese
**/
#include <stdio.h>
#include <stdlib.h>

char* read_user(char* file_name){
  int read_size;
  char buff[64];

  FILE *file = fopen(file_name, "r");
  fgets(buff, sizeof(buff), file);
  read_size = atoi(buff);

  char* s = (char*) malloc(read_size * sizeof(char));

  fgets(s, read_size, file);

  return s;
}

int main(void){
  char* s = read_user("rest.txt");
  printf("%s\n", s);
}



/**
  * bit-operasjoner
  * operasjon    |     operator     |     A    |   B       |  resultat
  *    NOT                ~            1010                   0101
  *    AND                &            1111      1010         1010
  *    OR                 |            1010      1001         1011
  *    XOR                ^          1010 1010 | 1111 0000 | 0101 1010
  *    SHIFT R            >>           0101       0001        0010
  *    SHIFT L            <<           0101       0010(2)     0100
**/
