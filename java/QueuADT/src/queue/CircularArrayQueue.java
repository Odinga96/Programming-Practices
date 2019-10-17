package queue;

import java.util.Iterator;

public class CircularArrayQueue<E>  extends AbstractQueue {

    private E[] element;
    private int first;
    private int length;

    public CircularArrayQueue(int capacity) {
        super(capacity);
        element=(E[])new Object[capacity];
        first=0;
        length=0;
    }

    public class QueueIterator<E> implements Iterator{

        @Override
        public boolean hasNext() {
            return first+1 <length && element[first+1] !=null;
        }

        @Override
        public Object next() {
            if(hasNext())
            return element[first+1];
            else
                return "The list is empty";
        }
    }


    @Override
    public void enqueue(Object elementN) {
        if (!isFull())
        element[length++]=(E)elementN;
    }

    @Override
    public Object dequeue() {
        if (!isEmpty())
        return element[first++];
        else {
            throw new  IllegalArgumentException("The queue is empty");
        }
    }

    @Override
    public int length() {
        return length-first;
    }

    @Override
    public Queue newInstance() {
        return new CircularArrayQueue(this.capacity());
    }

    @Override
    public void clear() { newInstance(); }

    @Override
    public Iterator iterator() {
        return new QueueIterator<>();
    }
}
