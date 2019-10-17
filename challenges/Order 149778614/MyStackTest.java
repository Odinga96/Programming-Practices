package com.company;

import org.junit.*;
import org.junit.rules.Timeout;
import org.junit.runners.MethodSorters;

import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the {@code MyStack} class.
 *
 * <p>CS251 Project 1</p>
 *
 * @author Fangqin Sun
 * @version July 1, 2019
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MyStackTest {
    /*
     * Constants used in testing.
     */
    private static final int[] NUM1;


    /*
     * Fields used in testing.
     */

    static {
        NUM1 = new int[] {1,2,2,5,-1,0,34,999,02,4,4,-38423,3,+45,5,-43,876,34,23,423,42,342,3,2,Integer.MAX_VALUE,Integer.MIN_VALUE};
    } //static

    @Before
    public void setUp() {

    } //setUp

    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @After
    public void tearDown() {}

    @Test(timeout = 100)
    public void testStack1() {
        String message = "testing push() and size()";
        MyStack<Integer> ms = new MyStack<Integer>();
        Stack<Integer> ts = new Stack<Integer>();
        for(int i: NUM1){
            ms.push(i);
            ts.push(i);
            assertEquals(message,ts.size(),ms.size());
        }
    }

    @Test(timeout = 100)
    public void testStack2() {
        Random random = new Random();
        int randomInt;
        String message = "testing push() and peek()";
        MyStack<Integer> ms = new MyStack<Integer>();
        Stack<Integer> ts = new Stack<Integer>();
        for(int i = 0; i < 1000; i++){
            ms.push(i);
            ts.push(i);
            assertEquals(message,ts.peek(),ms.peek());
            randomInt = random.nextInt();
            ms.push(randomInt);
            ts.push(randomInt);
            assertEquals(message,ts.peek(),ms.peek());
        }
    }

    @Test(timeout = 100)
    public void testStack3() {
        String message = "testing pop() and isEmpty()";
        MyStack<Integer> ms = new MyStack<Integer>();
        Stack<Integer> ts = new Stack<Integer>();
        for(int i: NUM1){
            ms.push(i);
            ts.push(i);
        }
        for(int i: NUM1){
            assertEquals(message,ts.pop(),ms.pop());
        }
        assertEquals(message,ts.empty(),ms.isEmpty());
    }

    @Test(timeout = 2000)
    public void testStack4() {
        Random random = new Random();
        int randomInt;
        String message = "testing push() and pop()";
        MyStack<Integer> ms = new MyStack<Integer>();
        Stack<Integer> ts = new Stack<Integer>();
        int c = 0;
        for(int i = 0; i < 80000; i++){
            c++;
            if(ts.empty()){
                System.out.print(" "+c);
                assertEquals(message,ts.empty(),ms.isEmpty());
                randomInt = random.nextInt();
                ms.push(randomInt);
                ts.push(randomInt);
                assertEquals(message,ts.peek(),ms.peek());
            }else{
                if(random.nextInt(10) > 5) {
                    assertEquals(message,ts.pop(),ms.pop());
                }else{
                    randomInt = random.nextInt();
                    ms.push(randomInt);
                    ts.push(randomInt);
                    assertEquals(message,ts.peek(),ms.peek());
                }
            }
            System.out.println(" s "+ms.size());
            assertEquals(message,ts.size(),ms.size());
        }
        while(!ts.empty()){
            assertEquals(message,ts.pop(),ms.pop());
        }
        assertEquals(message,ts.empty(),ms.isEmpty());
    }

}
