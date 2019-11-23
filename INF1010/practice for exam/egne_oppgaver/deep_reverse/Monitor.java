import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Monitor{
  Lock lock = new ReentrantLock();
  private ArrayList<String> sentence = new ArrayList<String>();
  private ArrayList<String> words = new ArrayList<String>();

  public void getSentence(String sents){
    lock.lock();
    try{
      sentence.add(sents);
    } finally{
      lock.unlock();
    }
  }

  public void getWords(String word){
    System.out.println(word);
    lock.lock();
    try{
      words.add(word);
    } finally{
      lock.unlock();
    }
  }

  public ArrayList<String> getSents(){
    return sentence;
  }

  public ArrayList<String> getWordsah(){/*
    System.out.println("****");
    for(String x : words){
      System.out.println(x);
    }
    System.out.println("****");*/
    return words;
  }


}
