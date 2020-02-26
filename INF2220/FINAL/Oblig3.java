import java.util.*;

class Oblig3{
  public static void main(String[] args) {
    Random r = new Random();
    double[] radixTot = new double[6];
    double[] quickTot = new double[6];
    double[] radixtid = new double[5];
    double[] quicktid = new double[5];
    int totCount = 0;
    for(int i = 10000000; i >= 100; i /= 10){
      for(int index = 0; index < 5; index++){
        int[] radix = new int[i];
        int[] quick = new int[i];
        for(int j = 0; j < i; j++){
          int next = r.nextInt(i);
          radix[j] = next;
          quick[j] = next;
        }
        VenstreRadix vr = new VenstreRadix();
        radixtid[index] = vr.VRadixMulti(radix);
        quicktid[index] = timeQuicsort(quick);
      }
      radixTot[totCount] = findMedian(radixtid);
      quickTot[totCount] = findMedian(quicktid);
      totCount++;
      System.out.println("n = " + i + ": ");
      System.out.println("VenstreRadix: " + findMedian(radixtid) + " ms");
      System.out.println("QuickSort: " + findMedian(quicktid) + " ms");
      System.out.printf("Speedup: %.2f x faster\n", findMedian(quicktid)/findMedian(radixtid));
    }
    double radixAvg = 0;
    double quickAvg = 0;
    for(int i = 0; i<radixTot.length;i++){
      radixAvg+=radixTot[i];
      quickAvg += quickTot[i];
    }
    radixAvg = radixAvg/6;
    quickAvg = quickAvg/6;
    System.out.println("The avg time-multiplier is: " + quickAvg/radixAvg);
  }


  static double timeQuicsort(int[] array){
    double tt = System.nanoTime();
    Arrays.sort(array);
    return (System.nanoTime() - tt)/1000000.0;
  }

  static double findMedian(double[] array){
    Arrays.sort(array);
    return array[array.length/2]; //returnerer den midterste verdien.
  }
}
