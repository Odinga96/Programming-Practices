import java.util.NoSuchElementException;
import java.util.Set;

/**
 * A priority queue which essentially acts as a wrapper class for the BinaryMinHeap 
 * when the keys and values are the same. 
 * 
 * The entries in the priority queue must be distinct and non-null. 
 * 
 * DO NOT MODIFY THIS FILE!
 * 
 * @author geyerj, 19sp
 */

public class MinPQ<E extends Comparable<E>> {
    
    private BinaryMinHeap<E, E> heap;
    
    public MinPQ() {
        heap = new BinaryMinHeapImpl<E,E>();
    }
    
     /**
     * @return the number of elements in the PQ
     */
    public int size() {
        return heap.size();
    }

    /**
     * @return true if the PQ is empty
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * @param value the value to check
     * @return  true if the PQ contains the specified value
     * @throws IllegalArgumentException  if value is null
     */
    public boolean contains(E value) {
        if (value == null) {
            throw new IllegalArgumentException("MinPQ doesn't support null values");
        }
        return heap.containsValue(value);
    }
    /**
     * @param value  the value to insert into the PQ,
     *               must be non-null
     * @throws IllegalArgumentException  if value is null or is already in the PQ
     */
    public void add(E value) {
        if (value == null) {
            throw new IllegalArgumentException("MinPQ doesn't support null values");
        }
        if (this.contains(value)) {
            throw new IllegalArgumentException("Value already in MinPQ");
        }
        heap.add(value, value);
    }

    /**
     * @return  the smallest value in the PQ
     * @throws NoSuchElementExecption if the PQ is empty
     * 
     */
    public E peek() {
        return heap.peek();
    }

    /**
     * Removes the smallest value in the PQ
     * Ties broken arbitrarily
     *
     * @return  any of the smallest values in the PQ
     * @throws NoSuchElementException  if the PQ is empty
     */
    public E extractMin() {
        return heap.extractMin();
    }

    /**
     * @return  an unordered Set containing all the values in the PQ
     */
     public Set<E> values() {
         return heap.values();
     }
}
