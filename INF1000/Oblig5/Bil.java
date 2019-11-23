//Objektet Bil. Det som tilhører dette objektet er i denne koden.
class Bil
{
  public int kilometerStand = 0;
  public final int tankStoerrelse =70;         //Aa my som er plass i tanken
  public final double bensinForbruk = 0.08;    //Aa my som forbrukes pr. 10 km.
  public double tankIgjen = 70;
  public int fyllTank;

  public void kjoerTur(int km)                 //Metoden kjoerTur
  {
    double bensinBruk = km*bensinForbruk;
    if(tankIgjen == 0)                         //om tanken er tom
    {
      System.out.println("sorry mac, du kan itte kjoere no lengre. Gaa resten av vegen du..");
    }
    else if(km*bensinForbruk>tankIgjen)        //om du kjaem til aa brume mer enn du kan
    {
      System.out.print("Du kan desverre itte kjoere saa langt uten aa gaa tomt.");
      System.out.println("Du kjoerte i stedet:" + hentMaksDistanse() + "km.");
      kilometerStand += hentMaksDistanse();   //legger til i kilometerstand saa langt du kan kjoere.
      tankIgjen = 0;                          //tanken blir 0.
    }
    else                                      //Om du kan kjoere saa langt
    {
      System.out.println("Du kjoerte "+km+"km.");
      kilometerStand += km;                   //legger til i kilometerstand.
      tankIgjen -= bensinBruk;                //minker i tanken'
    }
  }

  public void fyllTank(double fyllTank)       //metoden fyllTank
  {
    double tomRom = tankStoerrelse-tankIgjen;
    if(fyllTank>tomRom)                       //om det du skar fylle er mer enn det du kan fylle paa.
    {
      System.out.print("Du kunne itte fylle paa "+fyllTank+", saa da fyller du heller paa");
      System.out.println(" til det er fullt, som er: "+tomRom+" Liter.");
      tankIgjen = tankStoerrelse;             //Tanken blir full.
    }
    else                                      //om du kan fulle paa saa mye.
    {
      tankIgjen += fyllTank;                  //legg til i tanken
    }
  }
  public double hentMaksDistanse()            //Metoden hentMaksDistanse
  {
    double maksDistanse =  tankIgjen/bensinForbruk; //Aa langt du kan kjoere.
    return maksDistanse;                      //Returnerer maksDistansen. Saa langt du kan kjoere.
  }
  public int hentKilometerstand()             //Metoden hentKilometerstand.
  {
    return kilometerStand;                    //returnerer kilometerstanden
  }
}
