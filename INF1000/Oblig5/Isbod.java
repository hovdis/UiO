import java.util.*;
class Isbod
{
  Scanner innleser = new Scanner(System.in);                //Scannere
  private String[] ansatte = new String [10];               //String-array ansatte, med plass til 10
  private int antallAnsatte=0;                              //Teller for antall ansatte

  public void ansett()                                      //Metoden ansatt
  {
    if(antallAnsatte == 10)                                 //hvis antall ansatte allerede er 10.
    {
      System.out.print("Du har allerede maks antall ansatte. Du maa sparke noen ");
      System.out.println("foer du ansatter fler.");
    }
    else                                                    //Hvis det er plass til fler ansatte
    {
    ansatte[antallAnsatte] = innleser.nextLine();           //Navnet som leses inn blir lagra i neste ledige possisjon i arrayen
    antallAnsatte++;                                        //Legger til i telleren for ansatte
    }
  }

  public void giSistemannSparken()                          //Metoden giSistemannSparken
  {
    System.out.println("Den som faar sparken er stakkars "+ansatte[antallAnsatte-1]);
    ansatte[antallAnsatte-1] = null;                        //sletter siste ansatte
    antallAnsatte-=1;                                       //tar vekk en fra telleren av ansatte
  }

  public void printAlleAnsatte()                            //Metoden printAlleAnsatte
  {
    for(int i=0;i<ansatte.length;i++)
    {
      System.out.println(ansatte[i]);                       //Printer ansatt i plass i.
    }
  }
}
