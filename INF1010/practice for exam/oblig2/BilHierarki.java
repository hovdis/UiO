import java.util.*;
import java.io.*;

class BilHierarki{
  public static void main(String[] args) throws Exception{
    ArrayList<Bil> biler = new ArrayList<Bil>();
    String fil = args[0];
    Scanner fc = new Scanner(new File(fil));

    String linje;
    String[] ord;
    while(fc.hasNextLine()){
      linje = fc.nextLine();
      ord = linje.split(" ");

      switch (ord[0]) {
        case "EL":
          biler.add(new ElBil(ord[1], Double.parseDouble(ord[2])));
          break;
        case "PERSONBIL":
          biler.add(new PersonBil(ord[1], Double.parseDouble(ord[2]), Integer.parseInt(ord[3])));
          break;
        case "LASTEBIL":
          biler.add(new LasteBil(ord[1], Double.parseDouble(ord[2]), Double.parseDouble(ord[3])));
          break;
        default:
          throw new IllegalArgumentException("Invalid type of car");
      }
    }

    if(args.length > 1){
      String type = args[1];
      for(Bil bilen : biler){
        if(type.equals("EL")){
          if(bilen instanceof ElBil){
            bilen.skrivInfo();
          }
        }else if(type.equals("PERSONBIL")){
          if(bilen instanceof PersonBil){
            bilen.skrivInfo();
          }
        }else if(type.equals("LASTEBIL")){
          if(bilen instanceof LasteBil){
            bilen.skrivInfo();
          }
        }else{
          System.out.println("Wrong filter");
        }
      }
    } else{
      for(Bil bilen : biler){
        bilen.skrivInfo();
      }
    }
  }
}
