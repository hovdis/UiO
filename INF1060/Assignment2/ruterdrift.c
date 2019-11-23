#include "ruterdrift.h"

#define RED     "\x1b[31m"
#define GREEN   "\x1b[32m"
#define BLUE    "\x1b[34m"
#define RESET   "\x1b[0m"

extern int amount_routers[];
int amount_routers[1];
router_s* routers[MAX_LENGTH];

void writeToFile();

int readFile(char** arg){
	// Get filename
		FILE *file = fopen(arg[1], "r");
		if (file == NULL) {
			printf("Error: arg[1] is NULL\n");
			return 0;
		}
		else printf(GREEN "Success: File '%s' opened successfully" RESET "\n", arg[1]);

		// Read file
		fread(amount_routers, 4, 1, file);
		fgetc(file);

		// Read from file, create struct and insert to map.
		int i;
		router_s *r;
		for (i = 0; i < amount_routers[0]; i++) {
			unsigned char id = fgetc(file);
			unsigned char flag = fgetc(file);
			unsigned char length = fgetc(file);
			int l = (int) length;
			char name[l];
			fread(name, sizeof(char) * l-1, 1, file); //kanskje det er bare l, ikke l-1
			//name[l-1] = '\0';

			r = (router_s*) malloc(sizeof(router_s));
			//memset(r, 0, sizeof(struct router));
			init_router(r, id, flag, length, name);
			routers[id] = r;
			//bare for å få det til å kjøre
			//added_ids[id[0]] = 'y';
			//bare for å få det til å kjøre
			fgetc(file);
		}
		fclose(file);
		printf(GREEN "Read in the routers successfully" RESET "\n");

		return 1;
}

void editFlag(int id){
	unsigned char tmpFlag;
	unsigned char changeNumber;
	if(!routers[id]){
		printf("There are no routers here.\n");
		return;
	}
	changeNumber = routers[id]->FLAGG >> 4;
	if(changeNumber == 15){
		printf(RED "The router can't be changed any more" RESET "\n");
		return;
	}
	printf("What do you want to change?\n");
	printf("Press 1 to change status of active.\n");
	printf("Press 2 to change status of wireless.\n");
	printf("Press 3 to change status of 5GHZ.\n");

	int choice;
	scanf("%d", &choice);
	if(choice == 1){
		if((routers[id]->FLAGG & 1) == 0){ // Hvis det minst signifikante flagget er 0
			tmpFlag = routers[id]->FLAGG | (routers[id]->FLAGG +1);
		}else{ // Hvis det minst signifikante flagget er 1
			tmpFlag = routers[id]->FLAGG & (routers[id]->FLAGG -1);
		}
		changeNumber++;
		routers[id]->FLAGG = ((changeNumber << 4) | (tmpFlag & 7)); //maa ande med 7 for aa ikke faa med den gamle verdien av changeNumber naar jeg orer det sammen.
	}else if(choice == 2){
		if((routers[id]->FLAGG & 2) == 0){
			tmpFlag = (routers[id]->FLAGG | (routers[id]->FLAGG +2));
		}else{
			tmpFlag = (routers[id]->FLAGG & (routers[id]->FLAGG -2));
		}
		changeNumber++;
		routers[id]->FLAGG = ((changeNumber<<4) | (tmpFlag & 7));

	}else if(choice == 3){
		if((routers[id]->FLAGG & 4) == 0){
			tmpFlag = routers[id]->FLAGG | (routers[id]->FLAGG +4);
		}else{
			tmpFlag = routers[id]->FLAGG & (routers[id]->FLAGG -4);
		}
		changeNumber++;
		routers[id]->FLAGG = ((changeNumber << 4) | (tmpFlag & 7));
	}else{
		printf("Your input was not valid..");
		return;
	}
}

void deleteRouter(int id){
	router_s *r = getRouter(id);
	if(r){
		free(r);
		routers[id] = NULL;
	}
}

void editModel(int id){
	router_s *r = getRouter(id);
	if(r){
		printf("Write a new name for the router\n");
		char buff[MAX_LENGTH-3]; //Cant be over 253
		scanf("%s", &buff[0]);
		int count = strlen(buff)+1; //for å få plass til \0
		strcpy(r->name, buff);
		r->name[r->name_length-1] = '\0';
		r->name_length = count;
	}
}

void addFlags(int id){
	router_s *r = getRouter(id);
	unsigned char tmpFlag = 0;
	int answer, notError;
	printf("Is the router active?\n");
	printf("[0] - inactive\n");
	printf("[1] - active\n");
	notError = scanf("%d", &answer);
	if(!notError){
		printf(BLUE "Something went wrong. Try again!" RESET "\n");
		addFlags(id);
		return;
	}
	if(answer != 0 && answer != 1){
		printf(BLUE "Not a valid input! try again " RESET "\n");
		addFlags(id);
		return;
	}
	if(answer == 1){
		tmpFlag |= 1;
	}
	printf("Is the router wireless?\n");
	printf("[0] - no\n");
	printf("[1] - yes\n");
	notError = scanf("%d", &answer);
	if(!notError){
		printf(BLUE "Something went wrong. Try again!" RESET "\n");
		addFlags(id);
		return;
	}
	if(answer != 0 && answer != 1){
		printf(BLUE "Not a valid input! try again " RESET "\n");
		addFlags(id);
		return;
	}
	if(answer == 1){
		tmpFlag |= 2;
	}
	printf("Does the router have 5GHz?\n");
	printf("[0] - no\n");
	printf("[1] - yes\n");
	notError = scanf("%d", &answer);
	if(!notError){
		printf(BLUE "Something went wrong. Try again!" RESET "\n");
		addFlags(id);
		return;
	}
	if(answer != 0 && answer != 1){
		printf(BLUE "Not a valid input! try again " RESET "\n");
		addFlags(id);
		return;
	}
	if(answer == 1){
		tmpFlag |= 4;
	}
	r->FLAGG = tmpFlag;
}

void addRouter(int id){
	if(routers[id]){
		printf("This ID is already taken!\n");
	}else if(id<MAX_LENGTH){
		router_s *r = (router_s*) malloc(sizeof(router_s));
		r->id = id;
		routers[id] = r;
		addFlags(id); // endre denne...
		editModel(id);
		amount_routers[0]++;
	}
}

void printInfo(int id){
	if(getRouter(id)){
		print_router(routers[id]);
	}else{
		printf("You entered an id that is not valid\n");
	}
}

void freeAll(){
	int count = 0;
	for(int i = 0; i< MAX_LENGTH; i++){
		router_s *r = routers[i];
		if(count >= amount_routers[0]){
			return;
		}
		if(getRouter(i)){
			free(r);
			count++;
		}
	}
}

void menu(char** argv){
	int error;
	while(1){
		printf("[1] - Print info about a router based on an ID\n");
		printf("[2] - Change one of the flags in a router based on an ID\n");
		printf("[3] - Change the model/name of a router based on an ID\n");
		printf("[4] - Add a router\n");
		printf("[5] - Delete a router\n");
		printf("[6] - Save the changes and quit the program\n");
		printf("[letter(s)] - quit the program without saving the changes\n>");

		int input;
		error = scanf("%d", &input);
		if(error != 1){
			printf(BLUE "quitting without saving " RESET "\n");
			return;
		}
		if(input == 6){
			writeToFile(argv);
			freeAll();
			return;
		}
		if(input<0 || input>6){
			printf("That is not a valid input\n");
		}else{
			printf("What is the ID?\n>");
			int id;
			scanf("%d", &id);
			if(input == 2){
				editFlag(id);
			}else if(input == 3){
				editModel(id);
			}else if(input == 4){
				addRouter(id);
			}else if(input == 5){
				deleteRouter(id);
			}else if(input == 1){
				printInfo(id);
			}
		}
		printf("\n");
	}
}

void writeToFile(char** argv){
	FILE *wfile;
	wfile = fopen(argv[1], "w");
	if(wfile == NULL) {
    printf(RED "I couldn't open %s for writing." RESET "\n", argv[1]);
    exit(0);
  }
	fwrite(&amount_routers[0], sizeof(int), 1, wfile);
	char newLine = '\n';
	fwrite(&newLine, 1, 1, wfile);
	int i, count = 0;
	router_s *r;
	for(i = 0; i<MAX_LENGTH;i++){
		r = getRouter(i);
		if(r){
			fwrite(&(r->id), sizeof(unsigned char), 1, wfile);
			fwrite(&(r->FLAGG), sizeof(unsigned char), 1, wfile);
			fwrite(&(r->name_length), sizeof(unsigned char), 1, wfile);
			fwrite(&(r->name), sizeof(char), r->name_length-1, wfile);
			fwrite(&newLine, 1, 1, wfile);
			count++;
		}
		if(count == amount_routers[0]){
			printf(GREEN "Done with reading file!" RESET "\n");
			break;
		}
	}
	fclose(wfile);
}

int main(int argc, char** argv){
	if(argc != 2){
		printf(RED "You entered the wrong amount of parameters" RESET "\n");
		return -1;
	}
	readFile(argv);
	menu(argv);
	return 0;
}
