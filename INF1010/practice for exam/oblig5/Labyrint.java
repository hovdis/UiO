import java.util.*;
import java.io.*;

class Labyrint{
  Rute[][] ruteArray; //(x, y)
  int x, y;

  Labyrint(int x, int y){
    this.x = y;
    this.y = x;
    ruteArray = new Rute[y][x];
  }

  public String toString(){
    for(int i = 0; i<y;i++){
      for(int k = 0; k<x;k++){
        System.out.print(ruteArray[i][k]);
      }
      System.out.println();
    }
    return "hei";
  }


  public static Labyrint lesFraFil(File fil) throws Exception{
    Scanner fc = new Scanner(fil);
    String[] radKolonne = fc.nextLine().split(" ");
    int y = Integer.parseInt(radKolonne[0]);
    int x = Integer.parseInt(radKolonne[1]);

    Labyrint nyLab = new Labyrint(x, y);
    nyLab.lagRuter(y, x, fc);
    //Her skar det bli sett-ruter og sett-naboer
    return nyLab;
  }

  public void lagRuter(int y, int x, Scanner fc){
    String linje[];
    for(int i = 0; fc.hasNextLine(); i++){
      linje = fc.nextLine().split("");
      for(int k = 0; k<linje.length;k++){
        if(erAapning(linje[k], x, y)){
          this.oppdaterRuter(new Aapning(x, y, this), x, y);
        }else{
          if(linje[k].equals(".")){
            this.oppdaterRuter(new HvitRute(x, y, this), x, y);
          }else{
            this.oppdaterRuter(new SvartRute(x, y, this), x, y);
          }
        }
      }
    }
  }

  public boolean erAapning(String h, int x, int y){
    return h.equals(".") &&(y==0||y==this.y||x==0||x==this.x);
  }

  public void oppdaterRuter(Rute rute, int x, int y){
    ruteArray[y][x] = rute;
  }
}
