/*Denni oppgava skar medellere nye biler. Kilometerstanden paa bila
begynner paa 0, og full tank (70Liter). Naar brukeren kjoerer bilen
gaar kilometerstanden ned, sammen med bensinmengden.*/

class Oppgave51
{

  public static void main(String[]args)
  {
    Bil volvo240 = new Bil();                           //Kaller bilen for volvo240

    volvo240.kjoerTur(30);                              //Kaller paa metoden
    volvo240.kjoerTur(40);                              //Kaller paa metoden
    volvo240.kjoerTur(800);                             //Kaller paa metoden
    volvo240.fyllTank(90);                              //Kaller paa metoden
    volvo240.kjoerTur(30);                              //Kaller paa metoden

    int kmStand = volvo240.hentKilometerstand();        //henter kilometerstanden
    System.out.println("Kilometerstanden av bilen er naa: " + kmStand);
  }
}
