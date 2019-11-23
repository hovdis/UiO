class DobbelLenke<T>{
  Node first;

  public void insert(T element){
    if(first == null){
      first = new Node(element);
    }else{
      Node tmp = first;
      while(tmp.next != null){
        tmp = tmp.next;
      }
      tmp.next = new Node(element);
      tmp.next.past = tmp;
    }
  }



  class Node{
    Node next, past;
    T elem;

    Node(T elem){
      this.elem = elem;
    }
  }
}
