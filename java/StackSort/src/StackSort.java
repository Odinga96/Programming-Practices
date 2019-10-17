import java.util.ListIterator;
import java.util.Stack;

public class StackSort {

    public void sortStack(Stack<Integer> stack){
        Stack<Integer> temp=new Stack<>();

        if (!stack.empty()){
            int top=stack.pop();

            sortStack(stack);

            insert(stack, top);
        }
    }

    private void insert(Stack<Integer> stack, int top) {
        if (stack.empty() || top<stack.peek()){
            stack.push(top);
            return;
        }

        int temporary= stack.pop();
        insert(stack, top);


        stack.push(temporary);
    }

    public void displayStack(Stack<Integer> stack){
        System.out.print("Stack (from top):\t");
        ListIterator<Integer>  iterator=stack.listIterator();

        while (iterator.hasNext())
                iterator.next();

        StringBuilder output= new StringBuilder();
        while (iterator.hasPrevious())
            output.append(iterator.previous()).append(", ");

        output.replace(output.lastIndexOf(","),output.lastIndexOf(",")+1," ");


        System.out.println(output);
    }


    public static void main(String[] args) {
        Stack<Integer> myStack=new Stack<>();
        StackSort stackSort= new StackSort();


        myStack.push(1);
        myStack.push(2);
        myStack.push(20);
        myStack.push(11);
        myStack.push(8);
        myStack.push(100);


        stackSort.displayStack(myStack);

        stackSort.sortStack(myStack);

        stackSort.displayStack(myStack);


    }
}
