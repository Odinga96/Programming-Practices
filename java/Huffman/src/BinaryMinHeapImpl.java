import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 *
 * @param <V> {@inheritDoc}
 * @param <Key> {@inheritDoc}
 *
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {

   private ArrayList<Entry> entries;


    public BinaryMinHeapImpl() {
        this.entries = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() { return this.entries.size(); }

    @Override
    public boolean isEmpty() { return size() == 0;}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) { return entries.contains(value); }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
       Entry<Key,V>  entry=new Entry<>(key,value);
       this.entries.add(entry);

//        Collections.sort(this.entries);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V peek() { return (V) this.entries.get(0).value;}

    /**
     * {@inheritDoc}
     */
    @Override
    public V extractMin() {
        //TODO: implement
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V> values() {
        //TODO: implement
        throw new UnsupportedOperationException();
    }
    /**
     * Helper entry class for maintaining value-key pairs.
     * The underlying indexed list for your heap will contain
     * these entries.
     *
     * You are not required to use this, but we recommend it.
     */
    class Entry<A, B> {

        private A key;
        private B value;

        public Entry(A key, B value) {
            this.value = value;
            this.key = key;
        }

        /**
         * @return  the value stored in the entry
         */
        public B getValue() {
            return this.value;
        }

        /**
         * @return  the key stored in the entry
         */
        public A getKey() {
            return this.key;
        }

        /**
         * Changes the key of the entry.
         *
         * @param key  the new key
         * @return  the old key
         */
        public A setKey(A key) {
            A oldKey = this.key;
            this.key = key;
            return oldKey;
        }

    }

}