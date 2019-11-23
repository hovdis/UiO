//I denni oppgava kan du legge til, ta vekk(den siste lagt til) ansatte.
//Eller se over aakke som er lagt til i ansatt-lista.
import java.util.*;
class Oppgave54
{
  public static void main(String[]args)                     //main-metoden
  {
    Scanner tastInn = new Scanner(System.in);               //Scanneren
    Isbod hermannsIsbod = new Isbod();                      //Legger til objektet
    String enGangTil = ("ja");                              //for aa faa igang while-loopen

    while(enGangTil .equalsIgnoreCase ("ja"))               //hvis enGangTil er ja.
    {
      System.out.println("Velkommen til Hermanns Isbod! :)");
      System.out.println("Aa har du lyst til aa gjoera?");
      System.out.println("A. Legge til en ansatt");
      System.out.println("B. Gi den med minst erfaring starken");
      System.out.println("C. Vise alle ansatte");
      System.out.println("D. Avslutte detti programmet.");
      String svar = tastInn.nextLine();
      if (svar.equalsIgnoreCase ("a"))                      //Hvis svare er a
      {
        hermannsIsbod.ansett();                             //Kaller paa metode
        System.out.println("Har du lyst til aa gjoere no mer?");
        enGangTil=tastInn.nextLine();
      }
      else if(svar.equalsIgnoreCase ("b"))                  //Hvis svare er b
      {
        hermannsIsbod.giSistemannSparken();                 //Kaller paa metode
        System.out.println("Har du lyst til aa gjoere no mer?");
        enGangTil=tastInn.nextLine();
      }
      else if(svar.equalsIgnoreCase ("c"))                  //Hvis svare er c
      {
        hermannsIsbod.printAlleAnsatte();                   //Kaller paa metode
        System.out.println("Har du lyst til aa gjoere no mer?");
        enGangTil=tastInn.nextLine();
      }
      else if(svar.equalsIgnoreCase ("d"))                  //Hvis svare er d
      {
        System.out.println("Du avslutta naa detti awsome programmet...");
        enGangTil = ("njet");
      }
    }
  }
}
