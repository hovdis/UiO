class Fletting{
  public static void main(String[] args) {
    int[] a = {10, 15, 16, 20, 25};
    int[] b = {1, 2, 11, 27, 28};
    int[] tot = new int[a.length+b.length];
    int acnt = 0;
    int bcnt = 0;
    for(int i = 0; acnt!=a.length || bcnt!=b.length; i++){
      if(a[acnt] == 0){
        tot[i] = b[bcnt++];
      }else if(b[bcnt] == 0){
        tot[i] = a[acnt++];
      }else if(a[acnt] > b[bcnt]){
        tot[i] = b[bcnt++];
      }else{
        tot[i] = a[acnt++];
      }
    }

    for(int k = 0; k<tot.length;k++){
      System.out.println(tot[k]);
    }
  }
}
