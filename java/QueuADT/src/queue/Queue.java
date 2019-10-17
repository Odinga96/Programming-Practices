package queue;

public interface Queue<E> extends Iterable {
    /**
     * @param element :The element to add at the end of the queue
     */
    void enqueue(E element);

    /**
     * @return s element at the front of the queue
     */
    E dequeue();

    /**
     * @return s  the current length of the queue
     */
    int length();

    /**
     * @return the total number of elements the queue can carry
     */
    int capacity();

    /**
     * @return create a new instance of queue.Queue<E> of similar capacity
     */
    Queue<E> newInstance();

    /**
     *
     */
    void clear();

    /**
     * @return :true if the queue.Queue<E> is empty else return false
     */
    boolean isEmpty();

    /**
     * @return :true if the queue.Queue<E> is full else return false
     */
    boolean isFull();

    /**
     * @param that :Adds a queue.Queue<E> elements at the end of this queue.Queue<E>
     */
    void append(Queue<E> that);

    /**
     * @return :Makes a copy of this queue.Queue<E>
     */
    Queue<E> copy();
}
