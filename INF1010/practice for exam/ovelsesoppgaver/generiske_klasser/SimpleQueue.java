/** A simple queue interface.
 * This is a subset of the Queue interface in the java api.
 * @param <E> The type of element to put in the queue (a
 *            class name).
 * */
public interface SimpleQueue<E>
{
    /** Add an element to the collection.
     * @param e The element.
     * @return true if this collection changed as a result
     *         of the the call.
     * */
    public boolean add(E e);
    /** Remove and return one element from the queue.
     * @return a element or null if the queue is empty.
     * */
    public E poll();
}
