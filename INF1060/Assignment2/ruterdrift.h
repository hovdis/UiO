#ifndef __RUTERDRIFT_H
#define __RUTERDRIFT_H

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_LENGTH 256

typedef struct router {
  unsigned char id;
  unsigned char FLAGG;
  unsigned char name_length;
  char name[MAX_LENGTH-3];
}router_s;

void init_router(router_s *r, unsigned char id, unsigned char flag, unsigned char name_length, char name[]){
  r->id = id;
  r->FLAGG = flag;
  r->name_length = name_length;
  strcpy(r->name, name);
  r->name[name_length-1] = '\0';
}

void print_router(router_s *r){
	printf("id: %d\n", r->id);
	printf("Flagg: %02x\n", r->FLAGG);
	printf("Name: %s\n", r->name);
}

extern router_s* routers[];

router_s* getRouter(int id){
	if(id> MAX_LENGTH){
		printf("your index was too high!\n");
		return NULL;
	}
	if(routers[id]){// Kan dette gaa? Hvis det finnes noe der fra foer, men at det ikke er en struct...
		return routers[id];
	}
	return NULL;
}

#endif
