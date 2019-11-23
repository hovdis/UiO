#include <netdb.h>
#include <stdio.h>
#include <string.h>
#include <unistd.h>
#include <stdlib.h>
#include <signal.h>

#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>

FILE* fp;
int sock, sock_init;
int interrupt_received = 0;


void terminate_and_exit();

/*
 * interrupt handler inspired by Hans Petter (link in protocol.txt)
 * This function taken in the signal and changes the global variable interrupt_received to 1
 * This makes the server terminate normally
 */
void interrupt_handler(int signal) {
    printf("received signal %d\n", signal);
    interrupt_received = 1;
}

/*
 * This methood sends a q-message to the client to say that the server is closing.
 */
void send_error_message(){
  #ifdef DEBUG
    printf("[In function send_error_message]\n");
    printf(">>> [%d] <<< is sending a q-message to client\n", getpid());
  #endif
  char buffer[5];
  int length = 0;
  unsigned char jobType = 7;
  unsigned char jobTypeChecksum = (jobType << 5);
  memcpy(buffer, &jobTypeChecksum, sizeof(unsigned char));
  memcpy(buffer+1, &length, sizeof(int));
  write(sock, buffer, 5);
  fclose(fp);
  return;
}

/*
 * This function takes in one char* with the test, adds all the characters and
 * returns the sum % 32.
 *
 * Param: char* text
 *
 * Return: sum%32
 */
int getChecksum(char* text){
  #ifdef DEBUG
    printf("[In function getChecksum]\n");
    printf(">>> [%d] <<< is computing the checksum\n", getpid());
  #endif
  int sum = 0, i = 0;

  for(;i < (int)strlen(text);i++){
    sum += text[i];
  }
  return sum % 32;
}

/*
 * Reads the jobtype from the file(E or O), and assigns it to 0 or 1.
 * Reads the length of the text and checks the checkSum.
 * If everything goes good, the message to the client is made and sent to the client.
 *
 * Return: -1 if the file is corrupt or done.
 *          0 if everthing is ok
 */
int send_next_job(){
  #ifdef DEBUG
    printf("[In function send_next_job]\n");
    printf(">>> [%d] <<< is going to send a message\n", getpid());
  #endif
  unsigned char jobType = 'X', firstChar = 'X', jobTypeChecksum = 'X';
  unsigned int length = 0;
  int checkSum = 0, index = 0, bytesRead;
  ssize_t bytesSent;

  bytesRead = fread(&firstChar, sizeof(unsigned char), 1, fp);
  if(bytesRead != 1 || feof(fp) != 0){
    send_error_message();
    terminate_and_exit(fp);
    return -2;
  }
  if(firstChar == 'E' || firstChar == 'O'){
    bytesRead = fread(&length, sizeof(unsigned int), 1, fp);
    if(bytesRead != 1 || feof(fp) != 0){
      send_error_message();
      terminate_and_exit(fp);
      fprintf(stderr, "Error in read of length from file!\n");
      return -2;
    }
    char* text = malloc(length + 1);
    bytesRead = fread(text, sizeof(char), length, fp);
    if(bytesRead != (int)length || feof(fp) != 0){
      send_error_message();
      terminate_and_exit(fp);
      fprintf(stderr, "The length was not the same as the textlength!\n");
      free(text);
      return -2;
    }
    text[length] = '\0';

    if(firstChar == 'O'){
      jobType = '0';
    }else if(firstChar == 'E'){
      jobType = '1';
    }else{
      printf("There was something wrong with the first character (jobtype)\n");
      return -2;
    }
    checkSum = getChecksum(text);
    jobTypeChecksum = ((jobType << 5) | checkSum);
    int buffsize = sizeof(unsigned char) + sizeof(unsigned int) + length;
    char buffer[buffsize];

    #ifdef DEBUG
      printf("[In function send_next_job]\n");
      printf(">>> [%d] <<< is sending a message of size %d to the client\n", getpid(), length);
      printf("The jobtype is %c and the checksum is %d\n", firstChar, checkSum);
    #endif
    memset(buffer, 0, buffsize);
    memcpy(buffer, &jobTypeChecksum, sizeof(unsigned char));
    index = sizeof(unsigned char);
    memcpy(buffer + index, &length, sizeof(unsigned int));
    index += sizeof(unsigned int);
    memcpy(buffer + index, text, length);
    bytesSent = write(sock, buffer, buffsize);
    #ifdef DEBUG
      printf(">>> [%d] <<< wrote a message to the client.\n", getpid());
    #endif
    free(text);
    if(bytesSent == -1){
      perror("send_job()");
      return -1;
    }
    return 0;
  }else{
    printf("The file is done reading, no more jobs\n");
    send_error_message();
    terminate_and_exit();
    return 0;
  }
  return 0;
}

/*
 * The terminate function. Closes the socket and file.
 */
void terminate_and_exit(){
  #ifdef DEBUG
    printf("[In function terminate_and_exit]\n");
    printf(">>> [%d] <<< is terminating\n", getpid());
  #endif
  close(sock);
  fclose(fp);
}


/*
 * This function is heavily influenced by Hans Petter Taugbøl Kragset's github example
 * in client-server. Link in protocol.txt
 *
 * Param: Char* port
 *
 * Return: -1 if there is an error
 *         sock_init if there is no errors
 */
int create_socket(char* port){
  #ifdef DEBUG
    printf("[In function create_socket]\n");
    printf(">>> [%d] <<< is setting up the socket\n", getpid());
  #endif
  int port_number;
  char* port_ptr;
  struct sockaddr_in serveraddr;

  sock_init = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
  if(sock_init == -1){
    perror("Socket()");
  }

  port_number = strtol(port, &port_ptr, 10);
  if(port_number <=0){
    printf("That's not a valid number!\n");
    return -1;
  }

  memset(&serveraddr, 0, sizeof(struct sockaddr_in));

  serveraddr.sin_family = AF_INET;
  serveraddr.sin_port = htons(port_number);
  serveraddr.sin_addr.s_addr = INADDR_ANY;

  if(bind(sock_init, (struct sockaddr *)&serveraddr, sizeof(serveraddr))){
    perror("bind()");
    close(sock_init);
    return -1;
  }
  if(listen(sock_init, 0)){
    perror("Listen()");
    close(sock_init);
    return -1;
  }
  #ifdef DEBUG
    printf("[In function create_socket]\n");
    printf(">>> [%d] <<< is waiting for connection\n", getpid());
  #endif
  return sock_init;
}


/*
 * This function is heavily influenced by Hans Petter Taugbøl Kragset's github example
 * in client-server. Link in protocol.txt
 *
 * Return: -1 if there is something wrong with connecting
 *          sock if there it went well.
 */
int accept_connection(){
  struct sockaddr_in clientaddr;
  memset(&clientaddr, 0, sizeof(clientaddr));
  socklen_t addressLength = sizeof(clientaddr);
  sock = accept(sock_init, (struct sockaddr *)&clientaddr, &addressLength);
  if(sock == -1){
    perror("accept()");
    close(sock);
    close(sock_init);
    return -1;
  }
  #ifdef DEBUG
    printf("[In function accept_connection]\n");
    printf(">>> [%d] <<< accepted connection\n", getpid());
  #endif
  return sock;
}

/*
 * Main
 * This function opens the file, creates the socket and accepts the connections before
 * waiting for command from the client.
 * When recieving a command, it is checked for what command it is and the methood
 * that the client asked for is called.
 *
 * Param: int argc, char* argv
 *        the argv[1] is the job-file
 *        the argv[2] is the port
 *
 * Return: 0 if the arguments are not right
 *         -2 if the bytes read from client is wrong
 *         -1 if the client has disconnected
 */

int main(int argc, char* argv[]){
  int ret;
  int i;

  struct sigaction sa;
  memset(&sa, 0, sizeof(struct sigaction));
  sa.sa_handler = &interrupt_handler;

  sigaction(SIGINT, &sa, NULL);

  if(argc != 3){
    fprintf(stderr, "Usage: %s <job-file> <port>\n", argv[0]);
    return 0;
  }
  fp = fopen(argv[1], "r");
  if(fp == NULL){
    exit(EXIT_FAILURE);
  }

  ret = create_socket(argv[2]);
  if(sock_init == -1){
    fclose(fp);
    close(sock_init);
    exit(EXIT_FAILURE);
  }

  ret = accept_connection();
  if(ret == -1){
    terminate_and_exit();
    close(sock_init);
    exit(EXIT_FAILURE);
  }
  close(sock_init);
  #ifdef DEBUG
    printf("[In function man]\n");
    printf(">>> [%d] <<< closed the connection for other clients\n", getpid());
  #endif
  while(interrupt_received == 0){
    int command;
    ssize_t bytesRead = read(sock, &command, sizeof(int));
    #ifdef DEBUG
      printf(">>> [%d] <<< got command %d\n", getpid(), command);
    #endif
    if(bytesRead == 0){
      printf("The client has disconnected\n");
      terminate_and_exit();
      return -1;
    }else if(bytesRead == -1){
      send_error_message();
      return -2;
    }
    if(command == 1){// get one job
      ret = send_next_job();
      if(ret == -1){
        terminate_and_exit();
        exit(EXIT_FAILURE);
      }else if(ret == 2){
        terminate_and_exit();
        exit(EXIT_FAILURE);
      }else if(ret == -2){
        terminate_and_exit();
      }
    }else if(command == 2){
      printf("The client terminated normally\n");
      terminate_and_exit();
      exit(EXIT_FAILURE);
    }else if(command == 4){
      printf("The client terminated with an error\n");
      terminate_and_exit();
      exit(EXIT_FAILURE);
    }else if(command == 9){
      #ifdef DEBUG
        printf("[In function main]\n");
        printf(">>> [%d] <<< is going to send all jobs\n", getpid());
      #endif
      int failCheck;
      while(1){
        failCheck = send_next_job();
        if(failCheck == -2){
          terminate_and_exit();
          break;
        }
      }
    }else if(command > 7){
      #ifdef DEBUG
        printf("[In function main]\n");
        printf(">>> [%d] <<< is going to send %d jobs\n", getpid(), (command >> 4));
      #endif
      int number = (command >> 4);
      for(i = 0; i < number ; i++){
        ret = send_next_job();
        if(ret == -1){
          terminate_and_exit();
          exit(EXIT_FAILURE);
        }else if(ret == 2){
          terminate_and_exit();
          exit(EXIT_FAILURE);
        }else if(ret == -2){
          terminate_and_exit();
        }
      }
    }else{
      printf("Wrong command!\n");
    }
  }
  send_error_message();
  terminate_and_exit();
  exit(EXIT_FAILURE);
  return 1;
}
