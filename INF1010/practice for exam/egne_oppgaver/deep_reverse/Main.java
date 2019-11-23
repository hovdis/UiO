import java.io.*;
import java.util.*;

class Main{
  public static void main(String[] args) throws Exception{

    final int THREADS = 1;
    Scanner in = new Scanner(new File(args[0]));
    String linje;
    Thread[] workers = new Thread[THREADS];
    Monitor mon = new Monitor();
    for(int i = 0; i<THREADS;i++){
      linje = in.nextLine();
      Runnable job = new ReverseThread(linje, mon);
      workers[i] = new Thread(job);
      workers[i].start();
    }
    try{
      for(int k = 0; k< workers.length;k++){
        workers[k].join();
      }
    } catch(InterruptedException e){}

    ArrayList<String> sentences = mon.getSents();
    for(String k : sentences){
      System.out.println(k);
    }
    ArrayList<String> everything = new ArrayList<String>();
    Monitor man;
    for(String l:sentences){
      String[] words = l.split(" ");
      int threadsInSent = words.length-1;
      Thread[] workersWords = new Thread[threadsInSent];
      man = new Monitor();
      for(int i = 0; i<threadsInSent;i++){
        Runnable jobs = new DeepestThread(words[i], man);
        workersWords[i] = new Thread(jobs);
        workersWords[i].start();
      }
      try{
        for(int i = 0; i<workersWords.length;i++){
          workersWords[i].join();
        }
      }catch(InterruptedException e){}

      ArrayList<String> oneSent = mon.getWordsah();
      /*System.out.println("****");
      for(String x : oneSent){
        System.out.println(x);
      }
      System.out.println("****");*/
      String one = "";
      for(String pp : oneSent){
        one += pp;
      }
      everything.add(one);
    }

    for(String e : everything){
      System.out.println(e);
    }



    /*Scanner in = new Scanner(new File(args[0]));
    Scanner fc = new Scanner(new File(args[0]));
    int count = 0;
    while(fc.hasNextLine()){
      count++;
      fc.nextLine();
    }
    final int THREADS = count;
    int wordThreads;
    String line;
    Thread[] workersSent = new Thread[THREADS];
    Monitor mon = new Monitor();

    for(int i = 0; i<THREADS; i++){
      line = in.nextLine();
      Runnable job = new ReverseThread(line, mon);
      workersSent[i] = new Thread(job);
      workersSent[i].start();
    }
    try{
      for(int k = 0; k>workersSent.length;k++){
        workersSent[k].join();
      }
    } catch(InterruptedException e){}

    ArrayList<String> revSents = mon.getSents();

    String[] liiiste = new String[revSents.size()];

    for(int i = 0; i<liiiste.length;i++){
      String tmp = revSents.get(i);
      liiiste[Character.getNumericValue(tmp.charAt(0))] = tmp.substring(1);
    }
    for(int k = 0; k<liiiste.length;k++){
      System.out.println(liiiste[k]);
    }*/
  }
}
