import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class RevThread implements Runnable{
  static int id = 0;
  private int threadID;
  private String reverse;
  private String reversed;
  private String[] words;
  private String tmp;
  private Monotor mon;
  private Lock lock = new ReentrantLock();

  RevThread(String reverse, Monotor mon){
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
      mon.faaEnRev(reversed);
    }finally{
      lock.unlock();
    }
  }
}
