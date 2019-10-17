package com.company;

/**
 * MyStack.java
 *
 * Project 1, part 1. Stack use array.
 *
 */

public class MyStack<E> {
    public static final int DEFAULT_SIZE = 32;

    private E[] stack;
    private int count;
    private int maxSize;


    /**
     * Creates an empty Stack with initial capacity of {@code DEFAULT_SIZE}.
     */
    public MyStack(){
        stack = (E[]) new Object[DEFAULT_SIZE];
        maxSize = DEFAULT_SIZE;
    }

    /**
     * Creates an empty Stack with initial capacity.
     * @param initialCapacity the initial capacity.
     */
    public MyStack(int initialCapacity) {
        maxSize = initialCapacity;
        stack = (E[]) new Object[maxSize];
    }



    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements in the stack
     */
    public int size() {
        return count;
    }

    /**
     * Returns {@code true} if this stack contains no elements.
     *
     * @return {@code true} if this stack contains no elements
     */

    public boolean isEmpty() {
        if (count == 0) {
            return true;
        }
        return false;
    }

    /* resize the underlying array holding the elements */
    /* private void resize(int capacity) { } */

    /**
     * Adds the element to this stack.
     * @param e the element to add
     */
    public void push(E e) {
        if (isFull()) {
            int storeMax = 0;
            storeMax = maxSize;
            maxSize = maxSize * 2;
            E[] newStack = (E[]) new Object[maxSize];

            for (int i = 0; i < storeMax; i++) {
                newStack[i] = stack[i];
            }
            stack = newStack;
        }
        stack[++count] = e;
    }


    /**
     * Removes and returns the element most recently added to this stack.
     * @return the element most recently added, or {@code null} if
     * this stack is empty
     *
     */
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        Object elem;
        elem = stack[count];
        stack[count--] = null;

        return (E) elem;
    }


    /**
     * Returns (but does not remove) the item most recently added to this stack.
     * @return the item most recently added to this stack, or {@code null} if
     * this stack is empty
     */
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[count];
    }

    public boolean isFull() {
        return (count == maxSize - 1);
    }


    public static void main(String args[]) {
        MyStack theStack = new MyStack(DEFAULT_SIZE);
        theStack.push(1);
        theStack.push(2);
        theStack.push(3);
        //theStack.pop();
        //theStack.push(4);

        System.out.println("pop: " + theStack.pop());

        System.out.println("SIZE:: " + theStack.size());

        System.out.println("The element at the top of the stack is: " + theStack.peek());

        //System.out.println("Final Stack " + theStack);


    }
}

