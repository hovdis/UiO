/*I denne oppgaven laget je en kalkulator som regnet ut hvor mye
du totalt maa betale om du vil ta opp laan. Hvor mye du faar i rente
blir bestemt av dom alder og om du har fast jobb.*/
import java.util.Scanner;
class MinOppgave2
{
  public static void main(String[]args) //Main-metoden
  {
    Scanner in = new Scanner(System.in);
    int atten = 18; //Oppretter en (egentlig unoedvendig) int
    int gammel = 67; //Oppretter en (egentlig unoedvendig) int
    double laanTotal = 0.0; //laanet starter med 0, men blir oppdatert av bruker
    double lavrente = 1.0275; //Lavrenten er paa 2,75%
    double hoyrente = 1.045; //Hoyrenten er paa 4,5%
    System.out.print("Hei! For aa finne ut om du kan ta laan maa du ");
    System.out.print("foerst skrive inn hvor mye du vil ha(trykk enter), ");
    System.out.println("saa hvor gammel du er.");
    String laanonske = in.nextLine(); //laanonske
    double laanOenske = Double.parseDouble(laanonske); //gjoer om til en Double
    String alder1 = in.nextLine(); //skriver inn en alder som blir lagret som String
    int alder = Integer.parseInt(alder1); //Omgjoer det til en int.

      if ( alder<atten) //om alder er under inten atten
      {
        underAtten(); //Sender deg til mainen underAtten
      }
      else if (alder>=atten && alder<=gammel)//Om alderen er mellom 18 og 67
      {
        System.out.println("Har du en fast jobb?");//Spoer om du har jobb
        String svar = in.nextLine(); //Svaret du skriver inn blir skrivi som en String
        if (svar.equals("ja")) //om svaret er lik ja.
        {
          laanTotal = medJobb(laanOenske, hoyrente);//double´en laanTotal
          //blir endret ved hjelp av metoden medJobb
          sluttMelding(laanOenske, laanTotal); //Viser en sluttmelding (en metode)
        }
        else if(svar.equals("nei")) //Hvis svaret er nei
        {
          laanTotal = utenJobb(laanOenske, lavrente);//double´en laanTotal
          //blir endret ved hjelp av metoden utenJobb
          sluttMelding(laanOenske, laanTotal); //Viser til en sluttmelding (metode)
        }
        }
        else if(alder>gammel) //Hvis alderen er over 67
        {
          laanTotal = gammelLaan(lavrente, laanOenske); //double´en laanTotal
          //blir endret ved hjelp av metoden gammelLaan
          sluttMelding(laanOenske, laanTotal); //Viser til en sluttmelding(metode)
        }


  }
  //Metoden sluttMelding
  public static void sluttMelding(double laanOenske, double laanTotal)
  {
    System.out.print("For aa ta opp laan paa " + laanOenske + "kr, maa");
    System.out.println(" du totalt betale "+ laanTotal + "kr.");
    //Sier hvor mye du ville ta opp og hvor mye du totalt maa betale
    //om du tar opp et laan.
  }
  public static double gammelLaan(double lavrente, double laanOenske) //main gammelLaan
  {
    return lavrente*laanOenske; //Regner ut lavrente*laanOenske og returner det tilbake
  }
  //Metoden inderAtten
  public static void underAtten()
  {
    System.out.println("Desverre kan itte du ta opp laan.");
  }
  //Metoden medJobb
  public static double medJobb(double hoyrente, double laanOenske)
  {
    return hoyrente*laanOenske; //Regner ut hoyrente*laanOenske og returner det tilbake
  }
  //Metoden utenJobb
  public static double utenJobb(double lavrente, double laanOenske)
  {
    return lavrente*laanOenske; //Regner ut lavrente*laanOenske og returner det tilbake
  }
}
