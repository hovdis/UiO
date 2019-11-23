/*Denni oppgava er ganske sjoelforklarende. Det er en sparegris som starter paa
0 kroner. du kan legge til, ta ut, eller sjekke aa my peeng du har i grisen din.
Det siste du har et valg om aa gjoere er aa avslutte programmet.*/
import java.util.*;
class Sparegris
{
  public static void main(String[]args)                 //main-metoden
  {
    Peeng cash = new Peeng();                           //Oppretter objektet
    Scanner in = new Scanner(System.in);                //Scanneren
    String svar = "ja";                                 //Setter svaret til ja, for aa faa igang while-loopen
    while(svar .equalsIgnoreCase ("ja"))
    {
      System.out.println("Detti er sparegrisen din. Aa har du lyst til aa gjoere?");
      System.out.println("A. Legge til peeng i grisen og gjoere den glad.");
      System.out.println("B. Ta ut peeng fra sparegrisen");
      System.out.println("C. Se aa my peeng du har");
      System.out.println("D. Avslutte detti programmet");
      String alternativ= in.nextLine();

      if(alternativ .equalsIgnoreCase ("a"))
      {
        cash.leggTilPeeng();                            //Kaller paa metoden leggTilPeeng i objektet
        System.out.println("Har du lyst til aa gjoere noe mer i programmet?");
        svar =in.nextLine();
      }
      else if (alternativ .equalsIgnoreCase ("b"))
      {
        cash.taUtPeeng();                               //Kaller paa metoden taUtPeeng i objektet
        System.out.println("Har du lyst til aa gjoere noe mer i programmet?");
        svar =in.nextLine();
      }
      else if (alternativ .equalsIgnoreCase ("c"))
      {
        cash.sjekkePeeng();                             //Kaller paa metoden sjekkePeeng i objektet
        System.out.println("Har du lyst til aa gjoere noe mer i programmet?");
        svar =in.nextLine();
      }
      else
      {
        System.out.println("Du avslutter naa detti programmet.");
        svar = "nei";                                   //Gjoer saa du kommer ut av while-loopen
      }
    }
  }
}
