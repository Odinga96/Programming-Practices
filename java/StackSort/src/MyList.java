import java.util.LinkedList;

public class MyList <AnyType>  {

    private LinkedList<AnyType> list;
        MyList() {
            list=new LinkedList();
        }
        void myPush(AnyType x) {
            list.add(0,x);
        }
        AnyType myPop() { return list.remove(0); }

        void myInject(AnyType x) { list.addLast(x); }


}
