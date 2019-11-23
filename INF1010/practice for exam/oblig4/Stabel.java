class Stabel<T> extends LenkeListe<T>{
  @Override
  public void settInn(T element){
    Node newNode = new Node(element);
    if(erTom()){
      forste = newNode;
      siste = newNode;
      return;
    }
    forste.forrige = newNode;
    newNode.neste = forste;
    forste = newNode;
  }
}
