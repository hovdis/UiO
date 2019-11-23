#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>


int stringsum(char* s){
  char* c = NULL;
  int sum = 0;
  for (c = s; *c != '\0'; c++) {
    if(!isalpha((int)*c)){
      return -1;
    }
    sum += tolower(*c) -'a' + 1;
  }
  return sum;
}

int distance_between(char* s, char c){
  int count = 0;
  int hasFound = 0;
  char* x = NULL;
  for(x = s; *x != '\0';x++){
    if(*x == c){
      if(hasFound == 1){
        return count;
      }else{
        count++;
        hasFound = 1;
      }
    }else{
      if(hasFound){
        count++;
      }
    }
  }
  return -1;
}

char* string_between(char* s, char c){
  int dist = distance_between(s, c);
  int count = 0;
  char* through;
  if(dist == -1){
    return NULL;
  }
  char* mem = malloc(dist);

  for(through = s; *through != '\0' ; through++){
    if(*through != c){
      count++;
    }else{
      memcpy(mem, &s[count+1], dist-1);
      return mem;
    }
  }
  return NULL;
}

char** split(char* s){
  char* k = strdup(s);
  char** ret = NULL;
  char* word = strtok(k, " ");
  int count = 0;

  while(word) {
    ret = realloc(ret, sizeof(char*) * (count + 2));
    ret[count++] = strdup(word);
    word = strtok(NULL, " ");
  }
  ret[count] = NULL;

  return ret;
}


void stringsum2(char* s, int* res){
  *res = stringsum(s);
}
