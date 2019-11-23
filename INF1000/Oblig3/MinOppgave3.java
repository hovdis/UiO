import java.util.Scanner; //importerer scanner.
class MinOppgave3 //klassen heter MinOppgave3
{
  public static void main(String[]args) //Main-metoden
  {
    int antallFolk; //antall folk som har vært med i "The Biggest Looser"
    int i = 0; //Telleren satt til 0.
    Scanner in = new Scanner(System.in); //Scanneren heter in

    System.out.print("Velkommen til oppsummeringen av The Biggest Looser!");
    System.out.println("Foerst: Hvor mange var dere som fulgte programmet?");
    antallFolk = Integer.parseInt(in.nextLine()); /*Det brukeren skriver inn
    blir lagt inn i inten antallFolk.*/

    String[] navn = new String[antallFolk]; /*String-array med x antall variabler.
    x er definert av variablen antallFolk.*/
    double[] vektFor = new double[antallFolk]; /*Double-array med x antall vaiebler.
    x er definert av variablem antallFolk*/
    double[] vektEtter = new double[antallFolk]; /*Double-array med x antall vaiebler.
    x er definert av variablem antallFolk*/
    double[] vektRed = new double[antallFolk]; /*Double-array med x antall vaiebler.
    x er definert av variablem antallFolk*/
    double totalNed = 0;
    double gjennomsnitt;

    System.out.println("Hva het de som var med?");
      while(i<antallFolk) //mens i er mindre enn variablen antallFolk
      {
        navn[i] = in.nextLine(); //Det brukeren skriver inn blir lagra som
        //variablen navn[x], hvor x er verdien paa i.
        i+=1; //Legger til en i variablen i.
      }
    i=0; //Setter i til 0
      while(i<antallFolk) //Men i er mindre enn variablen antallFolk.
      {
        System.out.println("Hvor mye veide " + navn[i] + " før programmet?(i Kg)");
        vektFor[i] = Double.parseDouble(in.nextLine());
        //Det brukeren skriver inn blir lagra i variablen vektFor[x], hvor x er
        //verdien for i.
        i+=1; //legger til 1 i i.
      }
    i=0; //Setter i til 0.
      while(i<antallFolk)
      {
        System.out.println("Hvor mye veide " + navn[i] + " etter programmet?");
        vektEtter[i] = Double.parseDouble(in.nextLine()); //Legges til i vektEtter[i]
        vektRed[i] = vektFor[i]-vektEtter[i]; //regner ut mellomlegget mellom vekt foer og etter.
        i+=1; //Legger til 1 i i.
      }
    i=0; //Setter i til 0.
    System.out.println(""); //Lager et mellomrom.
      while(i<antallFolk)
      {
        System.out.println(navn[i] +" har gaatt ned " + vektRed[i] + "Kg.");
        //Bruker baade navn-arrayen og vektRed-arrayen for aa skrive ut melding.
        i+=1; //Legger til 1 i i.
      }
    i=0; //Setter i til 0.
    System.out.println(""); //Lager mellomrom.
      while(i<antallFolk)
      {
        totalNed = totalNed + vektRed[i]; //adderer totalNed med reduksjonen i vekt.
        //For hver gang man gjør det blir det et stoerre tall, og til slutt faar
        //vi totalen, hvor mye alle har mista til sammen. Detti bruker vi naar vi
        //skar regne ut gjennomsnitts-vektreduksjonen.
        i+=1; //legger til 1 i i.
      }
    System.out.println("Totalt har dere gaatt ned: " + totalNed + "Kg!");
    System.out.println(""); //lager mellomrom.
    gjennomsnitt = totalNed/antallFolk; //Regner ut gjennomsnittet ved å dividere
    //totalNed-variablen med antallFolk-variablen.
    System.out.println("Gjennomsnittlig har dere gaatt ned: " +gjennomsnitt+ "Kg hver!");
    System.out.println(""); //Lager mellomrom, saa det er lettere aa se i terminalen.
  }
}
