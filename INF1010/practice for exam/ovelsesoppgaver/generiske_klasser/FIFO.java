/** A simple queue interface.
 * This is a subset of the Queue interface in the java api.
 * @param <E> The type of element to put in the queue (a
 *            class name).
 * */
public class FIFO<E> implements SimpleQueue<E> {
    /** Add an element to the collection.
     * @param e The element.
     * @return true if this collection changed as a result
     *         of the the call.
     * */
    public Node first;
    public boolean add(E e){
        if(first == null){
            first = new Node(e);
            return true;
        }else{
            Node tmp = new Node(e);
            tmp.next = first;
            first = tmp;
            return true;
        }
    }
    /** Remove and return one element from the queue.
     * @return a element or null if the queue is empty.
     * */
    public E poll(){
        if(first == null){
            return null;
        }else if(first.next == null){
            Node tmp = first;
            first = null;
            return tmp.element;
        }else{
            Node tmp = first;
            first = first.next;
            return tmp.element;
        }
    }

    class Node{
        E element;
        Node next;

        Node(E element){
            this.element = element;
        }
    }
}


//TODO: FIFO = first in first out
