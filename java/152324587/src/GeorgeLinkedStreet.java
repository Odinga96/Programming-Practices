import java.util.Iterator;
/**
 * A Doubly-Linked Node implementation of the StreetParking Interface
 *
 * Implement the appropriate methods from StreetParking to get this to compile (and then delete this comment)
 */
public class GeorgeLinkedStreet implements StreetParking{

    private  int current_count;
    private  Node first;
    private  Node last;
    private  Node current;
    private  int  count;
    private  int  lengthOfStreet;
    private  boolean blocked=false;


    public GeorgeLinkedStreet(int lengthOfStreet, int lengthOfGeorgesCar ) {

        first=null;
        last=null;
        current=null;
        count=0;
        current_count=0;
        this.lengthOfStreet=lengthOfStreet;

        push(lengthOfStreet);
        push(lengthOfGeorgesCar);

    }



    private class Node{
        private int contents;
        private int ticketID;
        private Node  next;
        private Node  previous;

        public Node(int contents, int ticketID) {
            this.contents = contents;
            this.ticketID = ticketID;
        }

        public Node(int contents) {
            this.contents = contents;
        }
    }

    @Override
    public int push(int length) {

        System.out.println(length+":\t"+freeSpace());
        if (length <= freeSpace()){

            Node oldLast = last;

            last = new Node(length, count++);
            last.next = null;

            if (first == null) {
                first    =last;
                first.next=last;

                first.previous =null;
                last.previous=first;


                current  =last;

            }
            else {
                oldLast.next = last;
                last.previous=oldLast;

                current=last;
            }

            if (count >1 ){

                if (!blocked) first.contents -= length;
                else {first.next.contents -= length;}
            }


            current_count++;

           return count;
        }
        return -1;
    }

    @Override
    public void remove(int ticketNumber) {

//        boolean found;
//        if (!isBlocked()){
//            int Firstid=first.ticketID;
//            int number=-1;
//            int content=first.contents;
//
//            while (first.next != null){
//                 number=first.next.ticketID;
//
//                 if( number  == Firstid ) break;
//                 if( number  == ticketNumber ) break;
//                 else {
//                    first=first.next;
//                    first.previous=null;
//
//                    last = new Node(content, number);
//
//                     oldLast.next = last;
//                     last.previous=oldLast;
//
//                     current=last;
//                 }
//            }
//
//        }

    }

    @Override
    public boolean block(int size) {
          if (size <= freeSpace() && !isBlocked()){
              
              first.contents-=size;
              Node newFirst=new Node(size);

              first.previous=newFirst;
              newFirst.next=first;

              first=newFirst;


              count++;
              current_count++;
              blocked= true;
              return true;
          }
        return false;
    }

    @Override
    public boolean isBlocked() { return blocked; }

    @Override
    public int numberOfCars() { return count; }

    @Override
    public int freeSpace() {
        if (count == 0) return lengthOfStreet;
        if (!blocked) return first.contents;
        else return first.next.contents;
    }

    @Override
    public Iterator<Integer> iterator() {

        Iterator<Integer>  it=new Iterator<>() {
            @Override
            public boolean hasNext() { return current != null; }

            @Override
            public Integer next() {
                int content=current.contents;
                current=current.previous;
                current_count--;
                return content;
            }
        };
        return it;


    }

    @Override
    public String toString() {

        StringBuilder output= new StringBuilder();
        while (iterator().hasNext()){
            output.append((isBlocked() && current_count==2)?"E":
                          (isBlocked() && current_count==1)?"B":
                                  (!isBlocked() && current_count==1)?"E":"G")

                    .append(iterator().next())
                    .append(" ");
        }



        return String.valueOf(output).trim();
    }


}
