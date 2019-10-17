package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue.CircularArrayQueue;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class CircularArrayQueueTest {

    private CircularArrayQueue circularArrayQueue;

    @Before
    public void setUp() throws Exception {
        circularArrayQueue =new CircularArrayQueue(10);
    }

    @After
    public void tearDown() throws Exception {
        circularArrayQueue.newInstance();
    }

    @Test
    public void testEnqueue(){
        assertEquals(0, circularArrayQueue.length());

        circularArrayQueue.enqueue("Germany");
        circularArrayQueue.enqueue("Britain");
        circularArrayQueue.enqueue("Cyprus");

        assertEquals(3, circularArrayQueue.length());

    }

    @Test
    public void testDequeue(){
        circularArrayQueue.enqueue("Germany");
        circularArrayQueue.enqueue("Britain");
        circularArrayQueue.enqueue("Cyprus");

        assertEquals("Germany", circularArrayQueue.dequeue());
    }

    @Test
    public void testSize(){
        circularArrayQueue.enqueue("Germany");
        circularArrayQueue.enqueue("Britain");
        circularArrayQueue.enqueue("Cyprus");

        assertEquals(3, circularArrayQueue.length());

        circularArrayQueue.dequeue();
        circularArrayQueue.dequeue();

        assertEquals(1, circularArrayQueue.length());
    }

    @Test
    public void testEmpty(){
        assertTrue(circularArrayQueue.isEmpty());

        circularArrayQueue.enqueue("Kenya");
        circularArrayQueue.enqueue("U.S.A");
        circularArrayQueue.enqueue("Canada");

        assertFalse(circularArrayQueue.isEmpty());

        circularArrayQueue.dequeue();
        circularArrayQueue.dequeue();
        circularArrayQueue.dequeue();

        assertTrue(circularArrayQueue.isEmpty());

    }

    @Test
    public void testFull(){
        assertFalse(circularArrayQueue.isFull());

        circularArrayQueue.enqueue("Kenya");
        circularArrayQueue.enqueue("U.S.A");
        circularArrayQueue.enqueue("Canada");
        circularArrayQueue.enqueue("Malawi");
        circularArrayQueue.enqueue("Sudan");
        circularArrayQueue.enqueue("Germany");
        circularArrayQueue.enqueue("Britain");
        circularArrayQueue.enqueue("Cyprus");
        circularArrayQueue.enqueue("Russia");
        circularArrayQueue.enqueue("Ireland");

        assertTrue(circularArrayQueue.isFull());
    }
}
