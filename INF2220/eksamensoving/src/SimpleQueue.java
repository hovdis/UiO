import java.util.Scanner;

public class SimpleQueue {
    Node first;


    void getChoice(){
        Scanner in = new Scanner(System.in);
        int answer;
        for(;;){
            System.out.println("What do you want to do?");
            System.out.println("[1] Get one node and print it out?");
            System.out.println("[2] Get one node and print it out (but also deleate it?");
            System.out.println("[3] exit the program");
            answer = Integer.parseInt(in.nextLine());
            switch (answer){
                case 1:
                    System.out.println("The first node is nr. " + first.value);
                    break;
                case 2:
                    System.out.println();
                    System.out.println("The first node is nr. " + first.value + " deleting it now");
                    first = first.next;
                    break;
                case 4:
                    while(first.next != null){
                        System.out.println("Node: " + first.value);
                        first = first.next;
                    }
                    System.out.println("Node: " + first.value);
                    return;
                case 3:
                    System.out.println();
                    return;
                default:
                    break;
            }
        }
    }



    void makeNodes(){
        for(int i = 0; i < 100; i++){
            insert(i);
        }
    }

    void insert(int value){
        if(first == null){
            first = new Node(value);
            return;
        }
        Node tmp = first;
        while(tmp.next != null){
            tmp = tmp.next;
        }
        tmp.next = new Node(value);
        return;
    }


    private class Node{
        Node next;
        int value;

        Node(int value){
            this.value = value;
        }
    }

}
