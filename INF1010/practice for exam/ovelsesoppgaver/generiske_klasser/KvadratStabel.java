class KvadratStabel{
  public Node forste;
  public void leggPaa(Kvadrat ny){
    if(erTom()){
      forste = new Node(ny);
    }else{
      Node en = forste;
      while(en.neste != null){
        en = en.neste;
      }
      en.neste = new Node(ny);
    }
  }

  public Kvadrat taAv(){
    Node tmp = forste;
    forste = forste.neste;
    return tmp.data;
  }

  public boolean erTom(){
    return (forste == null);
  }


  class Node{
    protected Kvadrat data;
    protected Node neste;

    Node(Kvadrat data){
        this.data = data;
    }
  }
}
