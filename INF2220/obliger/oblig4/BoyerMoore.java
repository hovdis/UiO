import java.util.*;
import java.io.*;

class BoyerMoore{
  int CHAR_MAX = 255;
  public ArrayList<Integer> boyer_Moore_Horspool(char[] needle, char[] haystack){
    if ( needle.length > haystack.length ) return null;
      int[] bad_shift = new int[CHAR_MAX]; // 256
      int last = needle.length - 1;
      int count = needle.length;
      for(int i = 0;i<= last; i++){
        if(needle[i] == '_'){
          count = last-i;
          break;
        }
      }
      if(count == 0) count = 1;

      for(int i = 0; i < CHAR_MAX; i++){
        bad_shift[i] = count;
      }
      int offset = 0;
      ArrayList<Integer> ret = new ArrayList<>();
      int scan = 0;

      int maxoffset = haystack.length - needle.length;
      int shift = last;
      for(int i = 0; i < last; i++){
        int index = (int) needle[i];
        if(needle[i] == '_') bad_shift[index] = 1;
        else bad_shift[index] = last - i;
      }
      while(offset <= maxoffset){
        shift = bad_shift[haystack[offset + last]];
        for(scan = last; needle[scan] == '_' || needle[scan] == haystack[scan+offset]; scan--){
          if(scan == 0){ // match found!
            ret.add(offset);
            break;
          }
          if(needle[scan] == '_') shift = 1;
        }
        offset += shift;
      }
      if(ret.isEmpty()) return null;
      return ret;
    }
}
