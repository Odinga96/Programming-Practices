import java.util.Set;
import java.util.NoSuchElementException;

/**
 * An abstract class for a binary min-heap in Java.
 * Elements added to the min-heap are given an associated key used
 * to determine its priority. Keys do not have to be distinct.
 * Your task will be to implement this abstract class in BinaryMinHeapImpl.java
 *
 * Your constructor should not take in any arguments, and it should initialize an
 * empty heap. You do NOT have to implement min-heapify, instead building a heap
 * will be done by calling "add()"
 *
 * As always, feel free to add package private fields and helper methods. 
 *
 * @param <Key> the type of priorities for this heap
 * @author joncho, 16sp, gandhit, 16sp, geyerj, 18fa
 */
public interface BinaryMinHeap<Key extends Comparable<Key>, V> {

    /**
     * @return the number of elements in the min-heap
     * 
     * Runtime: O(1)
     */
    int size();

    /**
     * @return true if the heap is empty
     * 
     * Runtime: O(1)
     */
    boolean isEmpty();

    /**
     * @param value  the value to check, may be null
     * @return  true if the min-heap contains the specified value
     *
     * Runtime: O(n)
     */
    boolean containsValue(V value);

    /**
     * @param value  the value to insert into the heap,
     *               may be null
     * @param key  the priority key to associate with the
     *             value, must be non-null
     * @throws IllegalArgumentException  if key is null
     * @throws IllegalArgumentException  if value is already in the min-heap
     *
     * Runtime: O(n)
     */
    void add(Key key, V value);

    /**
     * Updates the key of a particular value in the min-heap
     * to a smaller key.
     *
     * @param value  the value whose associated key to update
     * @param newKey  the key to update value with
     * @throws NoSuchElementException  if value is not in the heap
     * @throws IllegalArgumentException  if newKey is null or newKey > key(value)
     *
     * Runtime: O(n)
     */
    default void decreaseKey(V value, Key newKey) {
        /** 
         * NOTE: 
         *
         * You do NOT need to implement this method for the Huffman assignment. 
         * You will be using it for a later assignment, so wait until then
         * before trying to implement this method. 
         */
        throw new UnsupportedOperationException();
    }

    /**
     * @return  the value with the smallest key in the min-heap
     * @throws NoSuchElementExecption if the heap is empty
     * 
     * Runtime: O(1)
     */
    V peek();

    /**
     * Removes the value with the smallest key in the min-heap.
     * Ties broken arbitrarily
     *
     * @return  any value with the smallest key in the min-heap
     * @throws NoSuchElementException  if the min-heap is empty
     *
     * Runtime: O(log n)
     */
    V extractMin();

    /**
     * @return  an unordered Set containing all the values in the heap
     *
     * Runtime: O(n)
     */
     Set<V> values();
}
