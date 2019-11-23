// forelesning 22. august 2017
// hjemmeeksamen er 40%, eksamen er 60%.
// i java: int[] arr = new int[100];
// i C : int arr[100];
//

struct person{
    int age;
};

void init_person(Struct person *p, int age){
    (*p).age = age;
}

void funksjon(struct person p){
    //funksjonskropp
}

struct person p;
init_person(&p, 10);

funksjon(p);


//public class Person{
//   private int age;
//   bla bla bla
//
//
//


struct list {
    struct list *next;
    struct list *prev;
    void *data;
};

void funksjon(struct list *l){
    //funksjonskropp
}


struct list l1;
struct list l2;

l1.next = &l2;
l2.prev = &l1;

struct person p;
l1.data = (void*) &p

//i stedet for å skrive: (*p).age = 19; kan man skrive p->age = 19;;
