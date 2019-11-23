class LIFO<E> implements SimpleQueue<E>{
  Node first;
  public boolean add(E e){
    if(first == null){
      first = new Node(e);
      return true;
    }else{
      Node tmp = new Node(e);
      tmp.next = first;
      first = tmp;
      return true;
    }
  }

  public E poll(){
    if(first == null){
      return null;
    }else if(first.next == null){
      Node tmp = first;
      return tmp.element;
    }else{
      Node tmp = first;
      while(tmp.next.next != null){
        tmp = tmp.next;
      }
      Node tmp2 = tmp.next;
      tmp.next = null;
      return tmp2.element;
    }
  }

  class Node{
    E element;
    Node next;

    Node(E element){
      this.element = element;
    }
  }
}

//TODO: LIFO = Last In First Opu
