import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class ReverseThread implements Runnable{

  static int id = 0;
  private int threadID;
  private String reverse;
  private String reversed;
  private String[] words;
  private String tmp;
  private Monitor mon;
  private Lock lock = new ReentrantLock();

  ReverseThread(String reverse, Monitor mon){
    this.reverse = reverse;
    this.mon = mon;
  }

  public void run(){
    lock.lock();
    threadID = id++;
    lock.unlock();
    words = reverse.split(" ");
    for(int i = 0, k=words.length-1; i<words.length;i++, k--){
      if(i>k || i == k){
        break;
      } else{
        tmp = words[i];
        words[i] = words[k];
        words[k] = tmp;
        tmp = null;
      }
    }
    reversed = threadID + " " + words[0];
    for(int y = 1; y<words.length;y++){
      reversed += " " + words[y];
    }
    lock.lock();
    try{
      //mon.faaNummer(threadID);
      mon.getSentence(reversed);
    }finally{
      lock.unlock();
    }
  }


  /*private String text, reversed, tmp;
  private String[] words;
  private int thisNumber;
  public static int number;
  private Lock lock = new ReentrantLock();
  Monitor monitor;

  public ReverseThread(String text, Monitor monitor){
    this.text = text;
    this.monitor = monitor;
  }

  public void run(){
    lock.lock();
    thisNumber = number;
    lock.unlock();
    words = text.split(" ");

    for(int i = 0, k = words.length-1; i< words.length;i++, k--){
      if(i>k || i==k){
        break;
      }else{
        tmp = words[i];
        words[i] = words[k];
        words[k] = tmp;
      }
    }

    reversed = thisNumber + words[0];
    for(int u = 1; u<words.length; u++){
      reversed += " " + words[u];
    }
    lock.lock();
    try{
      monitor.getSentence(reversed);
    } finally{
      lock.unlock();
    }
    /**
    *Her skal det komme monitor-tilbakemelding

  }*/
}
