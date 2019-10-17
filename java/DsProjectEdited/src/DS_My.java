
public class DS_My implements DataStructureADT {

    // TODO may wish to define an inner class
    class DS<T>{
        private T key;
        private T value;

        public DS(T key, T value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }
    }
    // for storing key and value as a pair
    // such a class and its members should be "private"

    // Private Fields of the class
    // TODO create field(s) here to store data pairs
    private DS[] ds;
    private int  current=0;
    
    public DS_My() {
        // TODO Auto-generated method stub
        ds=new DS[10];
        for (int i = 0; i < ds.length; i++) { ds[i]=null; }

    }

    /**
     * @param k :The  key of the  element to add
     * @param v :The value of the element
     */
    @Override
    public void insert(Comparable k, Object v) {
        // TODO Auto-generated method stub

        //Check of the element
        if (!contains(k)) {

            //check if array is full
            if (current == ds.length - 1) {

                //if the ds is already full swap the elements
                DS[] temp = ds;    //declare a temporary data structure
                ds = new DS[(current + 1) * 2];  //double the current array list

                //copy the temporary ds the new expanded arrays
                System.arraycopy(temp, 0, ds, 0, temp.length);

                //initializze the expanded elements to null
                for (int i = current+1; i < ds.length; i++) { ds[i]=null; }

                //insert the new value and also increment the current index
                ds[current++] =new DS(k,v);

            }else{

//                if array is not full we insert the value into the new element and increment the index
                ds[current++] =new DS(k,v);}
        }else{
            try {
                throw new Exception("Duplicate keys found");
            } catch (Exception ignored) {  }
        }
        
    }

    /**
     * @param k :The key of the element to be removed
     * @return returns true if the element is in the current data structure
     */
    @Override
    public boolean remove(Comparable k) {
        // TODO Auto-generated method stub

        /*the swap value will be used to remove the elements if null
        removed is the variable needed to check if the variable has been deleted
         */
        int swap_begin=0;
        boolean removed=false;

//        we only remove the element if it is contained
        if (contains(k)){

            //loop through to check for the element.
            for (int i = 0; i < ds.length; i++) {

                //if key is equal to key of current element set it to null
                if (ds[i] != null)
                if (ds[i].key.equals(k)) {
                    ds[i] = null;
                    swap_begin=i;
                }
            }

            //swap null values in order to keep elements in front of the list
            while (ds[swap_begin+1] != null){
                DS temp=ds[swap_begin+1];
                ds[swap_begin]=temp;
                ds[swap_begin++]=null;
            }
//            decrement the size by one. i.e by decrementing the current index
            current--;
            removed=true;
        }else{
            try {
                throw new Exception("The key given is not valid");
            } catch (Exception ignored) {}
        }

        return removed;
    }

    /**
     * @param k :Key of the element to check if contained
     * @return
     */
    @Override
    public boolean contains(Comparable k) {
        // TODO Auto-generated method stub

//        initialize contained to false
        boolean contained=false;
        for (DS d : ds) {

//            check if the element is not null
            if (d !=null)

//                if key is same as the current key set contained to true
            if (d.key.equals(k)) {
                contained=true;
                break;
            }
        }

//        return contained
        return contained;
    }


    @Override
    public Object get(Comparable k) {
        // TODO Auto-generated method stub

        //set contained element index to 0
        int index = 0;
        if (contains(k)) {

//            loop through to check for element
            for (DS d : ds) {
                if (d.key.equals(k)) break;
//                increment key if the current value is not the value being searched for
                index++;
            }
        }else{
//            throw exception if the value is not in ds
            try {
                throw new Exception("The key given is not valid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ds[index].value;
    }


    /**
     * @return ; returns the current size of the element
     * the current index will also act as the size of the ds
     */
    @Override
    public int size() { return current; }

}
