/* Denni oppgava tar imot to tall fra brukeren og legg sammen dom, finner
differansen, multipliserer, og deler dom med hverandre, så printer ut svara.*/
import java.util.*;
class Kalkulator
{
  public static void main(String[]args) //treng itte throws exception fordi je itte skar lese fra en fil.
  {
    Scanner in =new Scanner(System.in);

    System.out.println("Skriv inn et heltall");
    int heltall1 = Integer.parseInt(in.nextLine()); //lagrer som heltall1
    System.out.println("Skriv inn et heltall til");
    int heltall2 = Integer.parseInt(in.nextLine());//lagrer som heltall2

    int sumAddere= addisjon(heltall1, heltall2); /*Det som blir gjort i metoden
    addisjon blir lagra i inten sunAddere.*/
    System.out.println("Summen blir: "+sumAddere);

    int differanse = substraksjon(heltall1, heltall2);/*Det som blir gjort i metoden
    substraksjon blir lagra i inten differanse.*/
    System.out.println("Differansen blir: " +differanse);

    int divisjon = heltallsdivisjon(heltall1, heltall2);/*Det som blir gjort i metoden
    heltallsdivisjon blir lagra i inten divisjon.*/
    System.out.println(heltall1+"/"+heltall2+" blir: " +divisjon);

    System.out.println("Skriv inn et tall");
    double tall1 = Double.parseDouble(in.nextLine()); //lagrer som tall1
    System.out.println("Skriv inn et tall til");
    double tall2 = Double.parseDouble(in.nextLine());//lagrer som tall2

    double divSvar = divisjon(tall1, tall2);/*Det som blir gjort i metoden
    divisjon blir lagra i inten divSvar.*/
    System.out.println(tall1+"/"+tall2+" blir: "+divSvar);
  }

  static int addisjon(int heltall1, int heltall2) //metoden for aa addere.
  {
    int sumAddere = heltall1+heltall2; //legg sammen heltalla
    return sumAddere; //sender tilbake inten sumAddere.
  }

  static int substraksjon(int heltall1, int heltall2) //Finn ut hvilket tall
  //som er stoerst, og tar det tallet minus det andre.
  {
    int differanse; //oppretter inten
    if (heltall1>heltall2)
    {
      differanse = heltall1-heltall2;
    }
    else if (heltall1==heltall2)
    {
      differanse = 0;
    }
    else
    {
      differanse=heltall2-heltall1;
    }
    return differanse; //returnerer inten differanse.
  }

  static int heltallsdivisjon(int heltall1, int heltall2) //deler det foerste
  //tallet med det andre.
  {
    int divisjonssvaret = heltall1/heltall2; //deler heltall1 med heltall2 og
    //lagrer svaret som inten divisjonssvaret
    return divisjonssvaret; //sender tilbake inten divisjonssvaret
  }
    //Metoden som deler talla, detti kan inneholde tall bak komma.
  static double divisjon(double tall1, double tall2)
  {
    double divSvar = tall1/tall2; //Deler tall1 med tall2.
    return divSvar; //sender tilbake double´n divSvar.
  }
}
