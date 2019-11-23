import java.util.Scanner; //importerer scanner
class FirstArray //klassen skar hete FirstArray.
{
  public static void main(String[]args) //Main-metoden. Har ingen andre metoder i dette programmet.
  {
    Scanner ait = new Scanner(System.in); //Scanneren skar hete ait.
    //Oppgave 3.2 d)
    String[] tekst = new String[5]; //Lager en String-array med fem variabler.0-4
    int i = 0; //denni int´en kommer til å vaere den som teller opp.
    System.out.println("Skriv inn fem navn:"); // Ber om å skrive inn fem navn
    while (i<5) //Mens i er under 5, altså fem ganger.
    {
        tekst[i] = ait.nextLine(); //Det brukeren skriver inn blir lagra i tekst[i],
        //hvor i bestemmer hvilken variabel det er. Byner på 0.
        i+=1; //Legger til 1 i variablem i
    }

    //Oppgave 3.2 a) og b)
    int[] heltall= new int[4]; //Lager en int-array med 4 variabler. 0-3
    i =0; //Setter variablen i til 0.
    while(i<4) //mens i er under 3 (fire gonger)
    {
      heltall[i] = i; //variablem i arrayen heltall blir satt til den samma som i.
      i += 1; //legger til 1 i i.
    }

System.out.println(""); //lager mellomrom
    //Oppgave 3.2 c)
    heltall[0] = 1337; //Je velger å endre tallene manuelt
    heltall[3] = 1337; //Je velger å endre tallene manuelt
    System.out.println(heltall[0]); /*Je gadd itte aa skrive en whilelokke her,
    sjoel om det sikkert hadde blitt mindre koding.*/
    System.out.println(heltall[1]);//--||--
    System.out.println(heltall[2]);//--||--
    System.out.println(heltall[3]);//--||--

System.out.println(""); //lager mellomrom
    //Oppgave 3.2 d)
    i=0; //Setter i til 0
    System.out.println("Innhold i String-arrayen:");
    while(i<5)
    {
      System.out.println(tekst[i]); //brinter ut alle Strin-array-navna.
      i+=1;
    }
  }
}
