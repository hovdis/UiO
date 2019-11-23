import java.io.*;
import java.util.*;

class Mainen{
  public static void main(String[] args) throws Exception{
    String argument = null;
    if(args.length > 0){
      argument = args[0];
    }
    File fil = new File(argument);
    Labyrint ny;
    try{
      ny =Labyrint.lesFraFil(fil);
      ny.toString();
    } catch (FileNotFoundException e){
      System.out.println("NEI");
      System.exit(1);
    }
  }
}
