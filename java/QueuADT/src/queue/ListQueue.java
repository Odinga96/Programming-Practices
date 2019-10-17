package queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListQueue<E> extends AbstractQueue {

    private  List<E> list;

    public ListQueue(int capacity) {
        super(capacity);
        this.list = new ArrayList<>();
    }

    @Override
    public void enqueue(Object element) {
        this.list.add((E)element);
    }

    @Override
    public E dequeue() {
        return list.remove(0);
    }

    @Override
    public int length() {
        return this.list.size();
    }

    @Override
    public ListQueue<E> newInstance() { return new ListQueue<>(capacity()); }

    @Override
    public void clear() {
       list.clear();
    }

    @Override
    public Iterator iterator() {
        return super.iterator();
    }
}
