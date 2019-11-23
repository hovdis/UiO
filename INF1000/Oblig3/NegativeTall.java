class NegativeTall //klassa heter NegativeTall
{
  public static void main(String[]args) //Main-metoden
  {
    int i = 0; //Detti er telleren til while-loopene. inten heter i, og satt til 0.
    int feil = 0; //Telleren for antall negative tall.

    int[] heltall = {1, 4, 5, -2, -4, 6, 10, 3, -2}; //9 inter inni arrayen
    //heltall. heltall 0-8.
    while(i<heltall.length) //mens i er mindre enn antall inter i arrayen heltall.
    {
      //Oppgave 3.4 b)
      if(heltall[i]<0) //Hvis tallet er under 0
      {
        heltall[i] = i; //Bytte det negative tallet med hvilken int det er i arrayen.
        feil+=1; //Teller for negative tall
      }
      i+=1; //Legger til 1 i telleren

    }
    //Oppgave 3.4 a)
    System.out.println(""); //For aa lage litt rom.
    System.out.println("Antall negative tall: "+feil); //printer ut hvor mange
    //negative tall som er telt opp.
    System.out.println(""); //For aa lage litt rom.

    i=0; //Detter inten i til 0 igjen.

    //Oppgave 3.4 c)
    while(i<heltall.length) //Mens i er mindre enn hvor mange inter i arrayen heltall
    {
      System.out.println(heltall[i]); //printer ut alle intene i arrayen heltall
      i+=1; //legger til 1 i i.
    }
  }
}
