public class BinarySearchTree {
    public BinarySearchTreeNode root;
    public void insert(int key) {  }
    public void delete(int key) {  }
    public boolean find(int key) { return true; }

    public int positiveKeySum(){
     int sum=0;

     if (root.key>=0) sum+=root.key;

     BinarySearchTreeNode temp=root;

     return (sum+sum(temp));
    }

    public int sum(BinarySearchTreeNode tree){
        int sum=0;

        if (tree.left !=null && tree.right !=null){
            if (tree.right.key >= 0)
                sum+=tree.right.key;

            if (tree.left.key >= 0)
                sum+=tree.left.key;

              sum+=sum(tree.left);
              sum+=sum(tree.right);
        }

        return sum;
    }

    public void deleteMax(){ this.delete(getMax(root,root.key)); }

    public int getMax(BinarySearchTreeNode tree, int max){

        int maximum=0;

        if (tree.left !=null && tree.right !=null){
            if (tree.right.key >= tree.left.key )
                maximum=tree.right.key;
            else
                maximum=tree.left.key;

            if (max >maximum) maximum=max;
           maximum= getMax(tree.left, maximum);
           maximum= getMax(tree.right, maximum);

        }else if(tree.left !=null){
            maximum=tree.left.key;

            if (max >maximum) maximum=max;
            maximum= getMax(tree.left, maximum);

        }else if (tree.right != null){
            maximum=tree.right.key;

            if (max >maximum)
                maximum=max;
            maximum= getMax(tree.right, maximum);
        }


        if (max>maximum)
            maximum=max;


        return maximum;
    }

    public void printTree(){ print(root);
        System.out.println(" "+root.key);}

    private void print(BinarySearchTreeNode tree){
        int right;
        int left;

        if (tree.left !=null && tree.right !=null){
             right = tree.right.key;
             left  = tree.left.key;

            print(tree.left);
            print(tree.right);

            System.out.print(left+" "+right+" ");
        }

    }


}
