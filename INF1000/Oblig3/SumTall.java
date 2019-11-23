import java.util.Scanner; //importerer scanner
class SumTall //klassen skar hete SumTall
{
  public static void main(String[]args) //main-metoden.
  {
    Scanner ai = new Scanner(System.in); //Den nye scanneren heter ai
    int sumTall=0; //summen av talla begynner med aa vaere 0.
    int tallHolder=1; //Setter den til 1, så while-løkken skar fungere.
    //Denni kan settes til alt utenom 0.

    System.out.println("Skriv inn saa mange tall du vil");
    System.out.println("Naar du itte vil summere flere tall skriver du 0");

    while(tallHolder != 0) // intill du skriver "0" vil denni loopen gå.
    {
      tallHolder = Integer.parseInt(ai.nextLine()); //Det man skriver inn blir
      //lagra som en in, tallHolder
      sumTall = sumTall + tallHolder; //inten tallholder blir lagt til sumTall.
      //Je kunne ogsaa ha skrivi "sumTall += tallHolder."
    }
    System.out.println("Summen er: " + sumTall); //Printer ut aa my summen av
    //alle talla bruker´n skreiv inn.
  }
}
