#include <stdio.h>
#include <stdlib.h>

struct datetime{
  int hour;
  int minutes;
  int seconds;
  int day;
  int month;
  int year;
};

struct timerange{
  struct datetime* front;
  struct datetime* back;
};

void datetime_set_date(struct datetime* dt, int day, int month, int year){
  dt->day = day;
  dt->month = month;
  dt->year = year;
}

void datetime_set_time(struct datetime* dt, int hour, int minutes, int seconds){
  dt->hour = hour;
  dt->minutes = minutes;
  dt->seconds = seconds;
}


void init_datetime(struct datetime* dt, int hour, int minutes, int seconds, int day, int month, int year){
  datetime_set_date(dt, day, month, year);
  datetime_set_time(dt, hour, minutes, seconds);
}



void init_timerange(struct timerange* tr, struct datetime* front, struct datetime* back){
  tr->front = front;
  tr->back = back;
}

void print_datetime(struct datetime* dr){
  printf("%d:%d:%d - %d.%d.%d\n", dr->hour, dr->minutes, dr->seconds, dr->day, dr->month, dr->year);
}

void print_timerange(struct timerange* tr){
  printf("front: %d:%d:%d - %d.%d.%d\n", tr->front->hour, tr->front->minutes, tr->front->seconds, tr->front->day, tr->front->month, tr->front->year);
  printf("back: %d:%d:%d - %d.%d.%d\n", tr->back->hour, tr->back->minutes, tr->back->seconds, tr->back->day, tr->back->month, tr->back->year);
}



int main(){
  struct datetime d1;
  init_datetime(&d1, 10,10,10,9, 9, 1999);
  struct datetime d2;
  init_datetime(&d2, 10,10,10,9, 9, 1999);

  printf("d1 before change: ");
  print_datetime(&d1);
  printf("d2 before change: ");
  print_datetime(&d2);

  init_datetime(&d1, 00, 14, 00, 12, 9, 2017);
  init_datetime(&d2, 01, 15, 01, 13, 10, 2018);

  printf("d1 after change: ");
  print_datetime(&d1);
  printf("d2 after change: ");
  print_datetime(&d2);

  struct timerange tr;
  init_timerange(&tr, &d1, &d2);
  printf("d1 and d2 called from timerange\n");
  print_timerange(&tr);
}
