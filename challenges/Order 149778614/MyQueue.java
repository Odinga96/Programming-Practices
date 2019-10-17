package com.company;

/**
 * MyQueue.java
 *
 * Project 1, part 1. Queue use link-list.
 *
 */

public class MyQueue<E> {

    private Node first;
    private Node last;
    private int count;

    class Node {
        private E e;
        private Node next;
    }

    /**
     * Creates an empty queue and initializes variables.
     */
    public MyQueue(){
        first = null;
        last = null;
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number pf elements in the stack
     */
    public int size(){
        return count;
    }

    /**
     * Returns {@code true} if this queue contains no elements.
     *
     * @return {@code true} if this queue contains no elements; {@code false} otherwise
     */
    public boolean isEmpty() {
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds the element to this queue.
     * @param e the element to add
     */
    public void enqueue(E e) {
        Node oldLast = last;
        last = new Node();
        last.e = e;
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

    /**
     * Removes and returns the element on this queue that was least recently added.
     * @return the element on this queue that was least recently added, or {@code null}
     * if this queue is empty
     */

    public E dequeue() {
        if (count == 0) {
            return null;
        }
        E e = first.e;
        first = first.next;
        count--;
        return e;
    }

    /**
     * Returns the item least recently added to this queue.
     * @return the item least recently added to this queue, or {@code null} if
     * this queue is empty
     */

    public E peek() {
        if (count == 0) {
            return null;
        }
        E e = first.e;
        return e;
    }

}

