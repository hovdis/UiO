/*Detti programmet skar spoerre brukeren om hviklet ord
brukeren vil leite etter i en tekstfil, og oppgi aa mange
gonger det ordet staar. Det er heller itte case sensitive.
Den kaller ogsaa paa en metode.*/
import java.util.*;
import java.io.*;
class MinOppgave4
{
  public static void main(String[]args)throws Exception
  {
    Scanner in =new Scanner(System.in);
    File minFil = new File("DouglasAdams.txt"); //Fila heter DouglasAdams.txt
    Scanner hovedScanner =new Scanner(minFil); //Scanneren.
    String svar = "ja"; //Svaret om bruker vil soke er satt til ja foest, for
    //aa starte while-loekka
    String sokeOrd = "";
    int antallGanger =0; //antall gonger ordet staar i teksten er satt til 0.
    String innskrevetOrd; //ordet brukeren skriver inn.
    while(svar.equalsIgnoreCase ("ja")) /*Foerste gang er den satt til ja, men
    om brukeren skriver nei foerst vil itte whileloekka kjoeres mer. Om bruker
    skriver inn "ja", gaar while-loekka igjen.*/
    {
      System.out.println("Vil du soeke paa et ord?");
      svar=in.nextLine(); //Byttes ut med "ja" som stod der før. Brukes i starten
      //av while-loekka
      if(svar.equalsIgnoreCase ("ja")) //Om svaret er ja.
      {//Jeg elsker deg Emilie, amma (fra oss, heiagjengen din <3)
        System.out.println("Hvilker ord vil du soeke etter?");
        innskrevetOrd = in.nextLine(); //Blir lagra i innskrevetOrd variabelen.
        antallGanger=antallOrd(sokeOrd, minFil, antallGanger, innskrevetOrd);
        //Kaller paa metoden antallOrd og lagrer det som antallGanger.
        System.out.println("Antall ganger "+ innskrevetOrd +" staar i teksten er "+antallGanger);
        }
        else //Detti er til naar bruker skriver noe anna enn "ja" paa om hen vil
        //soke mer.
        {
        System.out.println("nei vell..Da vil itte je leke med deg heller. Hade.");
        }
    }
  }
  //Metoden som teller aa mange gonger ordet staar.
  static int antallOrd(String sokeOrd, File minFil, int antallGanger, String innskrevetOrd) throws Exception
  {
    Scanner scanneren = new Scanner(minFil); //Scanneren
    antallGanger=0; //Satt til 0 igjen for aa unngaa at alt blir doblet om du soker
    //paa samma ord fler ganger.
    while(scanneren.hasNextLine()) //mens den har en setning til i txt-fila.
    {
      sokeOrd = scanneren.nextLine(); //plassholder for aa som staar i neste setning.
      if(sokeOrd.equalsIgnoreCase (innskrevetOrd)) //om sokeOrd har ordet bruker leiter etter.
      {
        antallGanger++;//legg til en i antallGanger (telleren)
      }
    }
    return antallGanger; //returnerer antallGanger.
  }
}
