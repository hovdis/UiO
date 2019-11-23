
import java.util.Scanner;//foerst importer je scanner
class Metoder //lager en class som heter Metoder
{

  static void Spoersmaal()
  {
    String navn;//Oppretter stringen "navn"
    String bosted;//Oppretter stringen "bosted"
    Scanner ait;//
    ait = new Scanner(System.in);//lager en variabel for scanneren
    System.out.println("Skriv inn navn:");//spoer om navn
    navn = ait.nextLine();//lagrer det bruker skriver inn som navn
    System.out.println("Skriv inn bosted:");//spoer om bosted
    bosted = ait.nextLine();//lagrer det brukeren skriver inn som bosted
    System.out.println("Hei, "+navn+"! Du du bor paa/i "+bosted+".");
    //Bruker tekst og variabler til aa skrive ut navn og bosted.
  }
  public static void main (String[]args) //Main-metoden
  {
    Spoersmaal();//kaller til metoden Spoersmaal.
    Spoersmaal();//kaller til metoden Spoersmaal.
    Spoersmaal();//kaller til metoden Spoersmaal.
  }
}
