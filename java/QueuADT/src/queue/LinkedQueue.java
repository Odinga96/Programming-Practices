package queue;

import java.util.Iterator;

public class LinkedQueue<E> extends AbstractQueue{
    private Node<E> first;
    private  Node<E> last;
    private  int count;

    public LinkedQueue(int capacity) {
        super(capacity);
        first=null;
        last=null;
        count=0;
    }



    private class Node<E>{
      private E contents;
      private Node  next;

    }

    public class QueueIterator implements Iterator{

        @Override
        public boolean hasNext() {
            return first.next != last;
        }

        @Override
        public Object next() {
            return first.next;
        }
    }

    @Override
    public void enqueue(Object element) {
        Node oldLast = last;
        last = new Node<>();
        last.contents = (E)element;
        last.next = null;
        if (isEmpty()) {
            first = last;
        }
        else {
            oldLast.next = last;
            //count++;
        }
        count++;
    }

    @Override
    public E dequeue() {
        if (count == 0) {
            return null;
        }
        E e = first.contents;
        first = first.next;
        count--;
        return e;
    }

    @Override
    public int length() { return count; }

    @Override
    public LinkedQueue<E> newInstance() { return new LinkedQueue<>(this.capacity()); }

    @Override
    public void clear() {
        first =null;
        last  =null;
        count =0;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator();

    }
}
