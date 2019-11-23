class OrdnetLenkeliste<T extends Comparable<T>> extends LenkeListe<T>{
  @Override
  public void settInn(T element){
    Node newNode = new Node(element);
    Node node = forste;

    if(forste == null){
      forste = newNode;
      siste = newNode;
      return;
    }
    T nesteElement = (T)node.element;
    while(element.compareTo(nesteElement) >= 0 && nesteElement != null && node.neste != null){
      node = node.neste;
      nesteElement = (T)node.element;
    }

    if (element.compareTo(nesteElement) >= 0){
      newNode.forrige = node;
      node.neste = newNode;
    }else{
      newNode.neste = node;

      if (node.forrige != null){
        newNode.forrige = node.forrige;
        node.forrige.neste = newNode;
      }else{
        forste = newNode;
      }

      node.forrige = newNode;
    }
  }
}
