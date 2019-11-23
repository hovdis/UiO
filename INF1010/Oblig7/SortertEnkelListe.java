import java.util.*;
/**
 * Created by Rune on 01.03.2016.
 */
public class SortertEnkelListe<T extends Comparable<T> & Lik> implements AbstraktSortertEnkelListe<T> {

    Node forste;

    public boolean settInn(T data) {
        if (forste == null) {
            forste = new Node(data);
        } else if (data.compareTo(forste.data) < 0){
            Node nyNode = new Node(data);
            nyNode.neste = forste;
            forste = nyNode;
            return true;
        } else {
            Node temp = forste;
            Node forrige = forste;

            while (temp.neste != null) {
                temp = temp.neste;
                if (data.compareTo(temp.data) < 0) {
                    Node ny = new Node(data);
                    forrige.neste = ny;
                    ny.neste = temp;
                    return true;
                }
                forrige = temp;
            }
            temp.neste = new Node (data);
        } return false;
    }

    public T finnObjekt(String index) {
        for (T t : this) {
            if (t.samme(index)) {
                return t;
            }
        } return null;
    }
    public Iterator<T> iterator() {
        return new ListeIterator();
    }

    private class Node {
        Node neste;
        T data;

        public Node (T data) {
           this.data = data;
        }
    }

    public class ListeIterator implements Iterator<T> {
        Node temp;

        public ListeIterator() {
            temp = forste;
        }

        public boolean hasNext() {
            if(temp == null) {
                return false;
            } return true;
        }
        public T next() {
            T data = temp.data;
            temp = temp.neste;
            return data;
        }
    }
}
