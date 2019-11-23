/**
 * Created by Rune on 01.03.2016.
 */
import java.util.*;

public abstract class EnkelReseptListe implements Iterable<Resepter> {
    
    Node forste;
    Node siste;

    public boolean settInn(Resepter r) {
        if (forste == null) {
            forste = new Node(r);
            siste = forste;
        } else if (forste == siste) {
            forste = new Node(r);
            forste.neste = siste;
        } else {
            Node ny = new Node (r);
            ny.neste = forste;
            forste = ny;
        } return true;
    }

    public Resepter finnResept(int nummer) {
        Node temp = forste;

        while (temp != null) {
            if (temp.data.getNr() == nummer) {
                return temp.data;
            } temp = temp.neste;
        } return null;
    }

    public Iterator<Resepter> iterator() {
        return new ReseptIterator();
    }

    protected class Node {
        Node neste;
        Resepter data;

        public Node(Resepter data) {
            this.data = data;
        }
    }

    protected class ReseptIterator implements Iterator<Resepter> {
        Node temp = forste;
        public boolean hasNext() {
            return temp == null;
        }
        public Resepter next() {
            Resepter denneResepten = temp.data;
            temp = temp.neste;
            return denneResepten;
        }
    }
}
