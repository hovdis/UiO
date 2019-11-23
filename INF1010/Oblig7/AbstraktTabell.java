/**
 * Created by Rune on 01.03.2016.
 */
public interface AbstraktTabell<T> extends Iterable<T> {

    public boolean settInn(T data, int index);
    public T finnObjekt(int index);
    //Iterere over lista.
}
