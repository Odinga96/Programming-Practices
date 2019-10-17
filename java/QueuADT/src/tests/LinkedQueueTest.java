package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import queue.LinkedQueue;

import static org.junit.Assert.*;

public class LinkedQueueTest {

    LinkedQueue linkedQueue;

    @Before
    public void setUp() throws Exception {
        linkedQueue=new LinkedQueue(10);
    }

    @After
    public void tearDown() throws Exception {
        linkedQueue.newInstance();
    }

    @Test
    public void testEnqueue(){
        assertEquals(0,linkedQueue.length());

        linkedQueue.enqueue("First");
        linkedQueue.enqueue("Second item");
        linkedQueue.enqueue("Third element");

        assertEquals(3, linkedQueue.length());

   }

   @Test
   public void testDequeue(){
       linkedQueue.enqueue("First");
       linkedQueue.enqueue("Second item");
       linkedQueue.enqueue("Third element");

       assertEquals("First",linkedQueue.dequeue());
   }

   @Test
    public void testSize(){
       linkedQueue.enqueue("First");
       linkedQueue.enqueue("Second item");
       linkedQueue.enqueue("Third element");

       assertEquals(3,linkedQueue.length());

       linkedQueue.dequeue();
       linkedQueue.dequeue();

       assertEquals(1,linkedQueue.length());
   }

   @Test
   public void testEmpty(){
        assertTrue(linkedQueue.isEmpty());

       linkedQueue.enqueue("First");
       linkedQueue.enqueue("Second item");
       linkedQueue.enqueue("Third element");

       assertFalse(linkedQueue.isEmpty());

       linkedQueue.dequeue();
       linkedQueue.dequeue();
       linkedQueue.dequeue();

       assertTrue(linkedQueue.isEmpty());

   }

   @Test
    public void testFull(){
        assertFalse(linkedQueue.isFull());

       linkedQueue.enqueue("First");
       linkedQueue.enqueue("Second item");
       linkedQueue.enqueue("Third element");
       linkedQueue.enqueue(4);
       linkedQueue.enqueue(5);
       linkedQueue.enqueue(6);
       linkedQueue.enqueue(7);
       linkedQueue.enqueue(8);
       linkedQueue.enqueue(9);
       linkedQueue.enqueue(10);

       assertTrue(linkedQueue.isFull());
   }
}
