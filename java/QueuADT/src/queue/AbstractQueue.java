package queue;

import java.util.Iterator;
import java.util.Objects;

public class AbstractQueue<E> implements Queue {
    private int capacity;

    public AbstractQueue(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public void enqueue(Object element) {

    }

    @Override
    public E dequeue() {
        return null;
    }

    @Override
    public int length() {
        return 0;
    }

    @Override
    public int capacity() { return this.capacity; }

    @Override
    public Queue newInstance() {
        return new AbstractQueue(this.capacity);
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() { return this.length() == 0; }

    @Override
    public boolean isFull() { return this.capacity == this.length(); }

    @Override
    public void append(Queue that) {
        if (!that.isEmpty()) {
            this.enqueue(that.dequeue());
            this.append(that);
        }
    }

    @Override
    public Queue<E> copy() { return this; }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractQueue<?> that = (AbstractQueue<?>) o;
        return capacity == that.capacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity);
    }
}
