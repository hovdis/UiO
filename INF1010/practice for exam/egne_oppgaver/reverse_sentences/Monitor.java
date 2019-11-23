import java.io.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Monitor{
  public static void main(String[] args) throws Exception{
    final int THREADS = 10;
    Scanner in = new Scanner(new File(args[0]));
    String linje;
    Thread[] workers = new Thread[THREADS];
    Monotor mon = new Monotor();
    for(int i = 0; i<THREADS;i++){
      linje = in.nextLine();
      Runnable job = new RevThread(linje, mon);
      workers[i] = new Thread(job);
      workers[i].start();
    }
    try{
      for(int k = 0; k< workers.length;k++){
        workers[k].join();
      }
    } catch(InterruptedException e){}

    ArrayList<String> alt = mon.hentAlle();
    String[] liiiste = new String[alt.size()];

    for(int i = 0; i<liiiste.length;i++){
      String tmp = alt.get(i);
      liiiste[Character.getNumericValue(tmp.charAt(0))] = tmp.substring(1);
    }
    for(int k = 0; k<liiiste.length;k++){
      System.out.println(liiiste[k]);
    }
  }

}



class Monotor{
  private final Lock laas = new ReentrantLock();
  private String[] alle;
  private int num;
  ArrayList<String> liste = new ArrayList<String>();
  public ArrayList<String> hentAlle(){
    return liste;
  }

  public void faaNummer(int num){
    this.num = num;
  }

  public void faaEnRev(String rev){
    System.out.println("mottok: " + rev);
    laas.lock();
    try{
      liste.add(rev);
    } finally{
      laas.unlock();
    }
  }
}
