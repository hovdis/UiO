class Koe<T> extends LenkeListe<T>{
  @Override
  public void settInn(T element){
    Node tmp = new Node(element);

    if(forste != null){
      siste.neste = tmp;
      tmp.forrige = siste;
      siste = tmp;
    }else{
      forste = tmp;
      siste = tmp;
    }
  }
}
