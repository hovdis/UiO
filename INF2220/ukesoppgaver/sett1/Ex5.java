class Ex5{
  int number(BinNode t){
    //number of nodes
    int sum = 0;
    if(t.left != null){
      sum += number(t.left);
    }
    if(t.right != null){
      sum += number(t.right);
    }
    return sum+1;
  }

  int sum(BinNode t){
    int sum = 0;
    if(t.left != null){
      sum += sum(t.left);
    }
    if(t.right!=null){
      sum=+ sum(t.right);
    }
    return sum+t.data;
  }
}


class BinNode{
  int data;
  BinNode left;
  BinNode right;
}
