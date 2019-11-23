import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

class DeepestThread implements Runnable{
  private Lock lock = new ReentrantLock();
  private char[] chars;
  private String text, reversed, lalala;
  private char tmp;
  public static int number = 0;
  private int thisNumber;
  Monitor monitor;
  public DeepestThread(String word, Monitor monitor){
    this.lalala = word;
    this.monitor=  monitor;
  }

  public void run(){
    lock.lock();
      thisNumber = number;
    lock.unlock();
    chars = new char[lalala.length()];
    for(int i = 0; i<lalala.length();i++){
      chars[i] = lalala.charAt(i);
    }
    /*for(int i = 0; i<chars.length;i++){
      System.out.println(chars[i]);
    }*/
    for(int i = 0, k = chars.length-1; i<chars.length; i++, k--){
      if(i>k || i==k){
        break;
      }else{
        tmp = chars[i];
        chars[i] = chars[k];
        chars[k] = chars[i];
      }
    }

    reversed = thisNumber + " " + chars[0];
    for(int l =1; l<chars.length; l++){
      reversed += chars[l];
    }
    lock.lock();
    try{
      monitor.getWords(reversed);
    } finally{
      lock.unlock();
    }

    /**
    *Her kommer det noe monitor-tilbakemelding
    */

  }
}
