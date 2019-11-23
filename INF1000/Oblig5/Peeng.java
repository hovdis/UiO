//Objektet Sparegris. Det som tilhører dette objektet er i denne koden.
import java.util.*;

class Peeng
{
    Scanner ins = new Scanner(System.in);                     //Scanneren
    double peengTotalt = 0.0;                                 //Aa my peeng du har i sparegrisen
    double leggeTil;
    double taUt;


    public void leggTilPeeng()                                 //Metoden leggTilPeeng
    {
      System.out.println("Aa my peeng vil du legge til?");
      leggeTil = Double.parseDouble(ins.nextLine());
      peengTotalt+= leggeTil;                                  //Legger til penga i sparegrisen
      System.out.println("Du har naa lagt til: " + leggeTil + " kroner i sparegrisen!");
    }
    public void taUtPeeng()                                    //Metoden taUtPeeng
    {
      System.out.println("Aa my vil du ta ut av grisen?");
      taUt = Double.parseDouble(ins.nextLine());
      if (taUt>peengTotalt)                                    //om du vil ta ut mer enn det er i grisen
      {
        System.out.print("Du kan itte ta ut saa mye..");
        System.out.println("Du har bare igjen " + peengTotalt + " kroner i grisen");
      }
      else
      {
        System.out.println("Du tok ut " + taUt + " kroner fra sparegrisen");
        peengTotalt-=taUt;                                      //fjerner penga fra grisen
      }
    }
    public void sjekkePeeng()                                   //Metoden sjekkePeeng
    {
      System.out.println("Du har "+peengTotalt + " kroner i sparegrisen din");
    }
}
