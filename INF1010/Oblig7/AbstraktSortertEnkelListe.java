/**
 * Created by Rune on 01.03.2016.
 */
public interface AbstraktSortertEnkelListe<T extends Comparable<T> & Lik> extends Iterable<T> {

    public boolean settInn(T data);
    public T finnObjekt(String index);


}
