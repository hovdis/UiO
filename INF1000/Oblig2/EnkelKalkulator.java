import java.util.Scanner;
class EnkelKalkulator
{
  //Oppgave 2.2 a)
  public static int Addere(int talla, int tallb)
  {
    int sumTall = (talla+tallb);
    return sumTall;
  }
  /*Oppgave 2.2 b) (finner ogsaa ut hvilket tall som er stoerst og tar
  det tallet minus det andre.)*/
  public static int Differanse(int talla, int tallb)
  {
    if (talla>tallb)
    {
      int differanse = (talla - tallb);
      return differanse;
    }
    else
    {
      int differanse = (tallb - talla);
      return differanse;
    }
  }
  //Oppgave 2.2 c)
  public static int Produktet(int talla, int tallb)
  {
    int produkt = talla * tallb;
    return produkt;
  }
  //Oppgave 2.2 d)
  public static void main(String[]args)
  {
    Scanner in = new Scanner(System.in);
    System.out.println("Skriv inn et heltall");
    String a = in.nextLine();
    int talla = Integer.parseInt(a);
    System.out.println("Skriv inn et til heltall");
    String b = in.nextLine();
    int tallb = Integer.parseInt(b);
    System.out.println("Summen av tallene blir: "+Addere(talla, tallb));
    System.out.println("Differansen av tallene blir: "+ Differanse(talla, tallb));
    System.out.println("Produktet av tallene blir: "+ Produktet(talla, tallb));
  }
}
