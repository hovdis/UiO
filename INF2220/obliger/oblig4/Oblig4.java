import java.util.*;
import java.io.*;

class Oblig4{
    public static void main(String[] args) throws Exception{
        BoyerMoore bm = new BoyerMoore();
        Scanner in = new Scanner(new File (args[0]));
        Scanner in2 = new Scanner(new File (args[1]));
        //in er needle
        //in2 er høystakk

        String needle = "";
        while(in.hasNextLine()){
          needle += in.nextLine();
        }
        char[] needleChar = needle.toCharArray();
        String hoystakk = in2.nextLine();
        while(in2.hasNextLine()){
          hoystakk+= in2.nextLine();
        }
        char[] hoystakkArray = hoystakk.toCharArray();
        if(needleChar.length < 1 || hoystakkArray.length < 1){
          System.out.println("One of the files were empty");
          System.exit(0);
        }
        ArrayList<Integer> offset = bm.boyer_Moore_Horspool(needleChar, hoystakkArray);
        System.out.println("offset: " + offset);
    }
}
