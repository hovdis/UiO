import java.util.*;
/**
Exercise 8 We are going to implement a few functions inside the class BinaryHashMap
  1. Implement the function: boolean remove(String key), which returns false if the
    elements is not present inside BinaryHashMap true otherwise.
  2. Implement the function: String[] keys(), which returns all keys inside the BinaryHashMap.
    (HINT: we know how large this array has to be from this.size)
  3. Implement the function: Object[] toArray(), which should return all values from
    the hash-table.
*/
class Ex8{
  public HashMap<String, String> mappet = new HashMap<String, String>();

  public boolean remove(String key){
    if(mappet.get(key) == null){
      return false;
    } mappet.remove(key);
    return true;
  }

  public String[] keys(){
    String[] ret = new String[mappet.size];
    ArrayList<String> tmp = new ArrayList<>();
    ret.forEach((k,v) -> tmp.add(k));
    for(int i = 0; i<ret.length;i++){
      ret[i] = tmp.get(i);
    }
    return ret;
  }

}
