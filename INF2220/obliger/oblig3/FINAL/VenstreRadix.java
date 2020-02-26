import java.util.Arrays;

public class VenstreRadix {
  final static int NUM_BIT = 10; // eller: 6 - 13 er kanskje best.. finn selv ut hvilken verdi som er raskest
  final static int MIN_NUM = 50; // mellom 16 og 60, kvikksort bruker 47       10-50 er bra

  double VRadixMulti(int [] a) {
    long tt = System.nanoTime();
    int[] b  = new int [a.length];

    // Finner max-inten
    int max = 0;
    for(int n : a){
      if(n > max){
        max = n;
      }
    }

    // Bestemmer numBit fra det største tallet
    int numBit = 0;
    while(max >= (1 << numBit)){
      numBit++;
    }

    // rotkall
    venstreRadix(a, b, 0, a.length, numBit, NUM_BIT);
    // Slutter tiden
    double tid = (System.nanoTime() - tt)/1000000.0;
    testSort(a);
    return tid; // Returnerer tiden i ms.
  }

  // Sorter a[left..right] på siffer med start i bit: leftSortBit med lengde: maskLenbit,
  public void venstreRadix(int[] a, int [] b, int left, int right, int leftSortBit, int maskLen){

    int mask = (1<<maskLen)-1;
    int[] count = new int [mask+1];

    int bitShift = (leftSortBit > maskLen) ? leftSortBit - maskLen : 0;

    for(int i = left; i < right; i++){
      int siffer = mask & (a[i] >> bitShift); // Isolerer tallet vi er ute etter og adderer opp en i count på den plassen.
      count[siffer]++;
    }

    int next = left;
    for(int i = 0; i < count.length; i++){ // Setter posisjonen
      int curr = count[i];
      count[i] = next;
      next += curr;
    }

    // Setter inn tallene i riktig plss i b
    for(int i = left; i < right; i++){
      int num = mask & (a[i] >> bitShift);
      b[count[num]++] = a[i];
    }

    // Setter inn alle tallene i b[left...right] inn i a[left...right]
    for(int i = left; i < right; i++){
      a[i] = b[i];
    }

    leftSortBit -= maskLen; //gjør klar til neste kall
    if(leftSortBit <= 0) return;

    for(int i = 0; i < count.length; i++){
      if((count[i] - left) > MIN_NUM) {
        venstreRadix(a, b, left, count[i], leftSortBit, maskLen);
      }
      else if((count[i] - left) > 1){
        insertionSort(a, left, count[i]);
      }
      left = count[i];
    }
  }

  void insertionSort(int[] a, int left, int right){
    for(int i = left + 1; i < right; i++){
      int val = a[i];
      int j;
      for(j = i - 1; j >= 0 && a[j] > val ; j--){
        a[j + 1] = a[j];
      }
      a[j + 1] = val;
    }
  }

  void testSort(int [] a){
    for (int i = 0; i< a.length-1;i++) {
      if (a[i] > a[i+1]){
        System.out.println("SorteringsFEIL på: "+
        i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
        return;
      }
    }
  }// end testSort
}
