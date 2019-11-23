class GeneriskStabel<T>{
  public Node forste;
  public void leggPaa(T ny){
    if(erTom()){
      forste = new Node(ny);
    }else{
      Node en = new Node(ny);
      en.neste = forste;
      forste = en;
    }
  }

  public T taAv(){
    Node tmp = forste;
    forste = forste.neste;
    return tmp.data;
  }

  public boolean erTom(){
    return (forste == null);
  }


  class Node{
    protected T data;
    protected Node neste;

    Node(T data){
        this.data = data;
    }
  }
}
