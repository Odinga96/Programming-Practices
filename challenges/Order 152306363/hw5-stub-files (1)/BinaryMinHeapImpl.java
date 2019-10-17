import java.util.Set;

/**
 *
 * @param <V> {@inheritDoc}
 * @param <Key> {@inheritDoc}
 *
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        //TODO: implement
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        //TODO: implement
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        //TODO: implement
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
        //TODO: implement
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V peek() {
        //TODO: implement
        throw new UnsupportedOperationException();
    }

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