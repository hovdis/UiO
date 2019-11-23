import java.util.*;

class LenkeListe<T> implements Liste<T>{
  protected class Node<T>{
    public Node neste;
    public Node forrige;
    public T element;

    Node(T element){
      this.element = element;
      this.neste = null;
      this.forrige = null;
    }
    Node(){
      this(null);
    }
    public T getElement(){
      return element;
    }
  }

  protected Node forste;
  protected Node siste;

  LenkeListe(){
    this.forste = null;
    this.siste = null;
  }

  public int storrelse(){
    Node node = forste;
    int antElm = 0;

    while(node != null){
      antElm++;
      node = node.neste;
    }
    return antElm;
  }

  public boolean erTom(){
    if (forste == null){
      return true;
    }
    return false;
  }

  public void settInn(T element){

  }

  public T fjern(){
    if (erTom()){
      return null;
    }
    Node hentetNode = forste;
    forste = forste.neste;
    if (forste != null){
      forste.forrige = null;
    }
    return (T) hentetNode.element;
  }

  public Iterator<T> iterator(){
    return new Listeiterator<T>();
  }

  private class Listeiterator<T> implements Iterator<T>{
    Node initNode = new Node(null);

    Listeiterator(){
      initNode.neste = forste;
    }

    @Override
    public boolean hasNext(){
      if(initNode.neste != null){
        return true;
      } return false;
    }

    @Override
    public T next(){
      if (hasNext()){
        initNode = initNode.neste;
        return (T)initNode.element;
      }
      return null;
    }

    @Override
    public void remove(){

    }










  }
}
