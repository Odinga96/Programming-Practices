package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue.LinkedQueue;
import queue.ListQueue;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ListQueueTest {

    ListQueue listQueue;

    @Before
    public void setUp() throws Exception {
        listQueue =new ListQueue(10);
    }

    @After
    public void tearDown() throws Exception {
        listQueue.newInstance();
    }

    @Test
    public void testEnqueue(){
        assertEquals(0, listQueue.length());

        listQueue.enqueue("Jack");
        listQueue.enqueue("Don");
        listQueue.enqueue("Rooney");

        assertEquals(3, listQueue.length());

    }

    @Test
    public void testDequeue(){
        listQueue.enqueue("Jack");
        listQueue.enqueue("Don");
        listQueue.enqueue("Rooney");

        assertEquals("Jack", listQueue.dequeue());
    }

    @Test
    public void testSize(){
        listQueue.enqueue("Jack");
        listQueue.enqueue("Don");
        listQueue.enqueue("Rooney");

        assertEquals(3, listQueue.length());

        listQueue.dequeue();
        listQueue.dequeue();

        assertEquals(1, listQueue.length());
    }

    @Test
    public void testEmpty(){
        assertTrue(listQueue.isEmpty());

        listQueue.enqueue("Jack");
        listQueue.enqueue("Don");
        listQueue.enqueue("Rooney");

        assertFalse(listQueue.isEmpty());

        listQueue.dequeue();
        listQueue.dequeue();
        listQueue.dequeue();

        assertTrue(listQueue.isEmpty());

    }

    @Test
    public void testFull(){
        assertFalse(listQueue.isFull());

        listQueue.enqueue("Jack");
        listQueue.enqueue("Don");
        listQueue.enqueue("Rooney");
        listQueue.enqueue(40);
        listQueue.enqueue(500);
        listQueue.enqueue(6000);
        listQueue.enqueue(70000);
        listQueue.enqueue(-88888);
        listQueue.enqueue(999999);
        listQueue.enqueue("Waweru");

        assertTrue(listQueue.isFull());
    }
}
