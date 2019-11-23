#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <ctype.h>
#include <signal.h>

#include <netinet/in.h>
#include <sys/socket.h>
#include <arpa/inet.h>

#define CLOSE_ERROR 4
#define CLOSE_SUCCESS 2


// public variables:
int pipefd1[2], pipefd2[2];
pid_t pid, pid2;
int parent_PID;
int sock;
int interrupt_received = 0;
void interrupt_handler(int signal) {
  if(getpid() == parent_PID){
    printf("\nreceived signal %d\n", signal);
    interrupt_received = 1;
  }
}



/*
 * This function takes two integers, one is a message, either CLOSE_ERROR og CLOSE_SUCCESS.
 * If the int initializedSock is 1, the message is sent to the server.
 *
 * Param: int msg, int initializedSock
 *        msg is info. If the msg is 0, the client is terminated normally
 *        If the msg is CLOSE_ERROR, the client is terminated with an error
 *        The initializedSock is 1 if the socket is connected, and 0 if it is not
 */
void terminate(int msg, int initializedSock){
  unsigned int terminate_msg = 0;
  write(pipefd1[1], &terminate_msg, sizeof(unsigned int));
  close(pipefd1[1]);
  write(pipefd2[1], &terminate_msg, sizeof(unsigned int));
  close(pipefd2[1]);
  if(initializedSock == 1){
    write(sock, &msg, sizeof(int));
    close(sock);
  }
}

/*
 * This function takes an int command that is sent to the socket.
 * Param: int command
 *
 * Return: -1 if an error occurs
 *         0 if it was successfull.
 */
int send_to_server(int command){
  #ifdef DEBUG
  printf("[In function send_to_server]\n");
    printf(">>> [%d] <<< is sending message %d to server\n", getpid(), command);
  #endif
  ssize_t ret = write(sock, &command, sizeof(int));
  if(ret == -1){
    perror("send_to_server()");
    return -1;
  }
  return 0;
}

/*
 * This function first reads five bytes from the socket.
 * The first byte is the jobinfo (jobtype and checksum).
 * The last four bytes is the length of the text.
 * A char array is made with length+1 char-places and set all to zero.
 * The text is read, and the checkSum is calculated.
 * If the jobtype is 7 the file has reached the end, or there is a fault in the file. The client is terminated.
 * If the jobtype is 1 or 0, the text is sent to the respective childs.
 *
 * Return: -1 if there is an error
 *          1 if there are no tasks left, terminate
 *          0 if there are no errors
 */
int read_one_and_ditribute(){
  char jobType;
  int checkSum, i;
  char buf[5] = {0};
  unsigned int length = 0;
  ssize_t bytesRec;

  bytesRec = read(sock, &buf, 5);
  if(bytesRec == 0){
    printf("Server has disconnected1\n");
    terminate(CLOSE_SUCCESS, 1);
    return -1;
  }else if(bytesRec == -1){
    perror("recv()");
    return -1;
  }else{
    jobType = ((buf[0] >> 5) & 7);
    memcpy(&length, buf+1, 4);
    checkSum = (buf[0] & 31);
  }
  if(jobType == 7){ // Q
    sleep(1);
    terminate(CLOSE_SUCCESS, 1);
    return 1;
  }
  char text[length+1];
  memset(text, 0, length);
  bytesRec = read(sock, &text, length);
  text[length] = '\0';

  if(bytesRec == 0){
    printf("Server has disconnected2\n");
    terminate(CLOSE_SUCCESS, 1);
    return -1;
  }else if(bytesRec == -1){
    perror("recv()");
    return -1;
  }

  uint32_t calculatedCheckSum = 0;
  for(i = 0; i < (int)length ; i++){
    calculatedCheckSum += text[i];
  }
  calculatedCheckSum = calculatedCheckSum%32;

  if((int)calculatedCheckSum != checkSum){
    printf("The checksum was not correct\n");
    return -1;
  }
  if(jobType == 1){ // E
    #ifdef DEBUG
      printf("[In function read_one_and_ditribute]\n");
      printf(">>> [%d] <<< is sending a job of type E to a child\n", getpid());
    #endif
    char* msg = malloc(sizeof(char)*length+4);
    memcpy(msg, &length, sizeof(int));
    memcpy(msg+4, text, length);
    write(pipefd2[1], msg, length+4);
    free(msg);
  }else if(jobType == 0){ // O
    #ifdef DEBUG
    printf("[In function read_one_and_ditribute]\n");
      printf(">>> [%d] <<< is sending a job of type O to a child\n", getpid());
    #endif
    char* msg = malloc(sizeof(char)*length+4);
    memcpy(msg, &length, sizeof(int));
    memcpy(msg+4, text, length);
    write(pipefd1[1], msg, length+4);
    free(msg);
  }else{
    printf("The server sent something wrong: %c\n", jobType);
    return -1;
  }
  return 0;
}

/*
 * This methood takes in an int x. This is the number of jobs that is going to be requested to the server.
 * The command is made and sent to the server.
 * read_one_and_ditribute is called x times to recieve the correct amount of jobs.
 * If the requested amount of jobs is more than the number of jobs, the client is terminated normally
 *
 * Param: int x
 *
 * Return: -1 if error
 *          0 if no errors
 */
int getXjobs(int x){
  #ifdef DEBUG
    printf("[In function getXjobs]\n");
    printf(">>> [%d] <<< is asking server for %d jobs\n", getpid(), x);
  #endif
  int command = ((x<<4) | 8);
  int i = 0;
  int failCheck;
  int ret = send_to_server(command);
  if(ret == -1){
    perror("something went wrong");
    return -1;
  }
  #ifdef DEBUG
    printf("[In function getXjobs]\n");
    printf(">>> [%d] <<< is going to recieve %d jobs from server\n", getpid(), x);
  #endif
  for(; i < x ; i++){
    failCheck = read_one_and_ditribute();
    if(failCheck != 0){
      if(failCheck == 1){
        printf("No more jobs!\n");
        return 1;
      }
      printf("Something went wrong in jobs!\n");
      return -1;
    }
  }
  #ifdef DEBUG
    printf("[In function getXjobs]\n");
    printf(">>> [%d] <<< is done recieving %d jobs from server\n", getpid(), x);
  #endif
  return 0;
}

/*
 * This function calls the send_to_server methood that sends a request for one job
 * read_one_and_ditribute is called one time to recieve one job.
 *
 * Param:
 *
 * Return: -2 if there is an error when sending the command
 *         -1 if there is a problem with read_one_and_ditribute
 *          1 if there are no more jobs
 *          0 if there are no problems
 */
int get_one_job(){
  #ifdef DEBUG
    printf("[In function get_one_job]\n");
    printf(">>> [%d] <<< is going to ask for one job from server\n", getpid());
  #endif
  int command = 1; // 1 = get one job
  int i;

  if(send_to_server(command) == -1){
    return -2;
  }

  i = read_one_and_ditribute();
  if(i != 0){
    if(i == 1){
      return 1;
    }
    printf("Somthing went wrong..\n");
    return -1;
  }
  return 0;
}


/*
 * This methood is heavily inspired by Hans Perrer Taugbøl Kragset's example on github (link in protocol.txt)
 * The difference is that i have some more errorhandling.
 *
 * Param: char* ip, char* port
 *
 * Return: -1 if error
 *         socket if no errors
 */

int create_socket(char* ip, char* port){
  #ifdef DEBUG
    printf("[In function create_socket]\n");
    printf(">>> [%d] <<< is trying to connect to IP [%s] with port [%s]\n", getpid(), ip, port);
  #endif
  int port_number, ip_ret;
  char* port_ptr;
  struct sockaddr_in serveraddr;

  sock = socket(AF_INET, SOCK_STREAM, IPPROTO_TCP);
  if(sock == -1){
    perror("socket()");
    return -1;
  }

  port_number = strtol(port, &port_ptr, 10);
  if(port_number <= 0){
    printf("That is not a valid port\n");
    return -1;
  }

  memset(&serveraddr, 0, sizeof(struct sockaddr_in));
  serveraddr.sin_family = AF_INET;
  serveraddr.sin_port = htons(port_number);

  ip_ret = inet_pton(AF_INET, ip, &serveraddr.sin_addr.s_addr);
  if(ip_ret != 1){
    if(ip_ret == 0) fprintf(stderr, "invalid adress");
    else perror("inet_pton()");
    return -1;
  }
  if(connect(sock, (struct sockaddr *)&serveraddr, sizeof(serveraddr))){
    perror("connect()");
    return -1;
  }

  return sock;
}

/*
 * The command to get all jobs is made and sent to the server.
 * read_one_and_ditribute is called until read_one_and_ditribute returns 1 (return of 1 is "all jobs are done")
 *
 * Return:  -2 if there is an error with send_to_server
 *          -1 if error
 *          1 if done with all jobs
 */
int get_all_jobs(){
  #ifdef DEBUG
    printf("[In function get_all_jobs]\n");
    printf(">>> [%d] <<< Is going to ask for all jobs\n", getpid());
  #endif
  int msg = 9;
  int failCheck;
  if(send_to_server(msg) == -1){
    return -2;
  }
  while(1){
    failCheck = read_one_and_ditribute();
    if(failCheck != 0){
      if(failCheck == 1){
        #ifdef DEBUG
          printf("[In function get_all_jobs]\n");
          printf(">>> [%d] <<< is recieved all jobs\n", getpid());
        #endif
        terminate(CLOSE_SUCCESS, 1);
        return 1;
      }
      printf("Something went wrong in getXjobs!\n");
      terminate(CLOSE_SUCCESS, 1);
      return -1;
    }
  }
}

/*
 * print the menu
 */
void printMenu(){
  printf("Press 1 to get one job\n");
  printf("Press 2 to get X jobs\n");
  printf("Press 3 to get all jobs\n");
  printf("Press 4 to print the menu\n");
  printf("Press 0 to exit\n");
}

/*
 * The main methood
 * The methood starts out with making the pipes and childs.
 * The childs waits for jobs from the parent, and writes the jobs (if there are no errors in the job) respectivly.
 * The parent chreates the socket and accepts a connection.
 * Parent is where the menu is printed out, and the user can decidce what to do.
 *
 * Param: int argc, char* argv[]
 *        argv is the IP and port for connection
 *
 * Return: 0 if there is an error with connecting or if the userinput is wrong.
 *         1 if the program is closing without faults.
 *         EXIT_SUCCESS if the program is closing without faults
 *         EXIT_FAILURE if the program is closing with a fault
 */
int main(int argc, char* argv[]){
  int i;
  if(argc != 3){
    fprintf(stderr, "Usage: %s <IP> <port>\n", argv[0]);
    return 0;
  }

  struct sigaction sa;
  memset(&sa, 0, sizeof(struct sigaction));
  sa.sa_handler = &interrupt_handler;

  sigaction(SIGINT, &sa, NULL);

  if(pipe(pipefd1) == -1){
    perror("pipe1()");
    exit(EXIT_FAILURE);
  }
  if(pipe(pipefd2) == -1){
    perror("pipe2()");
    exit(EXIT_FAILURE);
  }

  #ifdef DEBUG
    printf("[In function main]\n");
    printf(">>> [%d] <<< made the pipes\n", getpid());
  #endif


  pid = fork();
  if(pid == -1){
    perror("fork1()");
    terminate(CLOSE_ERROR, 0);
    exit(EXIT_FAILURE);
  }else if(pid == 0){
    #ifdef DEBUG
      printf("[In function main]\n");
      printf(">>> [%d] <<< started\n", getpid());
    #endif
    close(pipefd2[1]);
    close(pipefd2[0]);
    while(1){
      unsigned int length; //E er 1
      ssize_t bytesRead = read(pipefd1[0], &length, sizeof(unsigned int));
      #ifdef DEBUG
        printf("[In function main]\n");
        printf(">>> [%d] <<< recieved a message\n", getpid());
      #endif
      if(bytesRead < 0){
        terminate(CLOSE_ERROR, 1);
        break;
      }else if(bytesRead == 0){
        printf("Exiting pipefd1\n");
        terminate(CLOSE_SUCCESS, 1);
        break;
      }
      if(length == 0){
        #ifdef DEBUG
          printf("[In function main]\n");
          printf(">>> [%d] <<< is closing and terminating\n", getpid());
        #endif
        terminate(CLOSE_SUCCESS, 1);
        close(pipefd1[0]);
        return EXIT_SUCCESS;
      }
      char text[length];
      memset(text, 0, length);
      bytesRead = read(pipefd1[0], text, (int)length);
      if(bytesRead > 0){
        //printf("[%d] Recieved %zd bytes in O2\n", getpid(), bytesRead);
        fprintf(stdout, "%s\n", text);
      }else if(bytesRead == 0){
        #ifdef DEBUG
          printf("[In function main]\n");
          printf(">>> [%d] <<< is closing and terminating\n", getpid());
        #endif
        close(pipefd1[0]);
        printf("Exiting pipefd11\n");
        terminate(CLOSE_SUCCESS, 1);
        return EXIT_SUCCESS;
      }else{
        perror("read text in E");
        terminate(CLOSE_ERROR, 1);
        return EXIT_FAILURE;
      }
    }
    close(pipefd1[0]);
  }
  else{
    pid2 = fork();
    if(pid2 == -1){
      perror("fork1()");
      terminate(CLOSE_ERROR, 0);
      exit(EXIT_FAILURE);
    }else if(pid2 ==0){
      #ifdef DEBUG
        printf("[In function main]\n");
        printf(">>> [%d] <<< stared\n", getpid());
      #endif
      close(pipefd1[0]);
      close(pipefd1[1]);
      while(1){
        int length;
        ssize_t bytesRead = read(pipefd2[0], &length, 4);
        #ifdef DEBUG
          printf("[In function main]\n");
          printf(">>> [%d] <<< recieved a message\n", getpid());
        #endif
        if(bytesRead < 0){
          terminate(CLOSE_ERROR, 1);
          close(pipefd2[0]);
          return EXIT_FAILURE;
        }else if(bytesRead == 0){
          printf("Exiting pipefd2\n");
          terminate(CLOSE_SUCCESS, 1);
          close(pipefd2[0]);
          return EXIT_SUCCESS;
          break;
        }
        if(length == 0){
          terminate(CLOSE_SUCCESS, 1);
          #ifdef DEBUG
            printf("[In function main]\n");
            printf(">>> [%d] <<< is closing and terminating\n", getpid());
          #endif
          close(pipefd2[0]);
          return EXIT_SUCCESS;
        }
        char text[length+1];
        bytesRead = read(pipefd2[0], text, (int)length);
        text[length] = '\0';
        if(bytesRead > 0){
          //printf("[%d] Recieved %zd bytes in E2\n", getpid(), bytesRead);
          fprintf(stderr, "%s\n", text);
        }else if(bytesRead == 0){
          #ifdef DEBUG
            printf("[In function main]\n");
            printf(">>> [%d] <<< is closing and terminating\n", getpid());
          #endif
          close(pipefd2[0]);
          terminate(CLOSE_SUCCESS, 1);
          return EXIT_SUCCESS;
        }else{
          perror("read text in E");
        }
      }

    }else{
      parent_PID = getpid();
      sock = create_socket(argv[1], argv[2]);
      if(sock == -1){
        if (setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &(int){ 1 }, sizeof(int)) < 0)
        perror("setsockopt(SO_REUSEADDR) failed");
        return 0;
      }

      #ifdef DEBUG
        printf("[In function main]\n");
        printf(">>> [%d] <<< connected to the server with IP [%s] and port [%s]\n", getpid(), argv[1], argv[2]);
      #endif
      printMenu();
      char command;
      char* endptr;
      while(interrupt_received == 0){
        usleep(10000);
        printf("\n");
        printf("Choose a command now (Press 4 for menu)\n");
        char buff[255] = {0}; //Got something wrong if i tried fgetc
        fgets(buff, 255, stdin);
        if(interrupt_received != 0){
          interrupt_received = 1;
          break;
        }
        command = toupper(buff[0]);
        if(command == '1'){
          int k = get_one_job();
          if(k == 1){
            #ifdef DEBUG
              printf("[In function main]\n");
              printf(">>> [%d] <<< is terminating\n", getpid());
            #endif
            terminate(EXIT_SUCCESS, 1);
            exit(1);
          }
        }else if(command == '2'){
          printf("How many do you want?\n");
          fgets(buff, 255, stdin);
          i = strtol(buff, &endptr, 10);
          int k = getXjobs(i);
          if(k == 1){
            exit(1);
          }
        }else if(command == '3'){
          int k = get_all_jobs();
          if(k == -1){
            #ifdef DEBUG
              printf("[In function main]\n");
              printf(">>> [%d] <<< is terminating\n", getpid());
            #endif
            terminate(CLOSE_ERROR, 1);
            exit(1);
          }else if(k == 1){
            #ifdef DEBUG
              printf("[In function main]\n");
              printf(">>> [%d] <<< is terminating\n", getpid());
            #endif
            terminate(CLOSE_SUCCESS, 1);
            exit(1);
          }
          break;
        }else if(command == '4'){
          printMenu();
        }else if(command == '0'){
          #ifdef DEBUG
            printf("[In function main]\n");
            printf(">>> [%d] <<< is terminating\n", getpid());
          #endif
          terminate(CLOSE_SUCCESS, 1);
          break;
        }
      }
      terminate(CLOSE_SUCCESS, 1);
    }
    terminate(CLOSE_SUCCESS, 1);
  }
  return 1;
}
