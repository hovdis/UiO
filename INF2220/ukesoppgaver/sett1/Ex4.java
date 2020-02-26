class Ex4{

    int sizeOfTree(Tree tree){
        if(tree.leftNode() != null && tree.rightNode() != null){
            return 1 + sizeOfTree(tree.leftNode()) + sizeOfTree(tree.rightNode());
        } else if(tree.leftNode() == null && tree.rightNode() != null){
            return 1 + sizeOfTree(tree.rightNode());
        } else if(tree.leftNode() != null && tree.rightNode() == null){
            return 1 + sizeOfTree(tree.leftNode());
        }else{
            return 1;
        }
    }

    int sum(){
        int sum = element;

        if(left != null){
            sum += left.sum();
        }
        if(right != null){
            sum += right.sum();
        }

        return sum;
    }

    //return element + (left != null ? left.sum() : 0) + (right != null ? right.sum() : 0);

    public static void main(String[] args){

    }


    void updateNodes(int depth, Node tree){
        int leftTreeHeight = tree.left.updateNodes(depth+1, left);
        int rightTreeHeight = tree.right.updateNodes(depth+1, right);
        if(leftTreeHeight>rightTreeHeight){
            int height = leftTreeHeight;
        }else{
            int height = rightTreeHeight;
        }
        return height;
    }
}
