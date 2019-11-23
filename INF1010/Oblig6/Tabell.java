/**
 * Created by Rune on 01.03.2016.
 */
import java.util.*;

public class Tabell<T> implements AbstraktTabell<T> {

    private T[] tabell;

    public Tabell(int plasser) {
        tabell = (T[]) new Object[plasser];
    }

    public boolean settInn(T data, int index) {
        if (index < tabell.length && index >= 0) {
            if (tabell[index] == null) {
                tabell[index] = data;
                return true;
            }
        }
        return false;
    }
    public T finnObjekt(int index) {
        if (index < tabell.length && index >= 0) {
            return tabell[index];
        }
        return null;
    }
    public Iterator<T> iterator() {
        return new TabellIterator();
    }

    private class TabellIterator implements Iterator<T> {
        private int index = 0;

        public boolean hasNext() {
            while (index < tabell.length) {
                if (tabell[index] != null) {
                    break;
                }
                index++;
            }
            return index<tabell.length;
        }
        public T next() {
            index++;
            return tabell[index-1];
        }
    }
}
