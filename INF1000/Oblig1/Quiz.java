import java.util.Scanner;
public class Quiz
{
public static void main (String[]args)
  {
int spoersmaal = 10;
int antallriktig = 0;
  System.out.println("---------------------------------------------------------");
  System.out.println("Dette er en quiz som har " + spoersmaal + " spoersmaal.");
  System.out.println("Det vil være fire svaraldernativer for hvert spoersmaal.");
  System.out.println("Svar ved aa skrive svaralternativet fulgt av enter-tasten.");
  System.out.println("Foerst; skriv inn navnet ditt");
  System.out.println("---------------------------------------------------------");
Scanner alt = new Scanner(System.in);
String navn = alt.nextLine();
  System.out.println("Hei " + navn + "! For aa begynne quizen trykker du paa enter.");
  System.out.println("---------------------------------------------------------");
  //Lager mellomrom mellom spoesmaalene for meg selv saa det blir lettere aa skille.
  String enter = alt.nextLine();
  System.out.println("Foerste spoersmaal: hvor mye er kvadratroten av 64 ?");
  System.out.println("a. 64");
  System.out.println("b. 8");
  System.out.println("c. 4096");
  System.out.println("d. 2");
  String svar1 = alt.nextLine() .toLowerCase();
  if (svar1.equals("a")||svar1.equals("c")||svar1.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar1.equals("b"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter2 = alt.nextLine();

//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("------------------------------------------------------");
      System.out.println("Andre spoersmaal: hvor mye er kvadratroten av 110 ?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 12100");
      System.out.println("d. 42");
      String svar2 = alt.nextLine() .toLowerCase();
  if (svar2.equals("b")||svar2.equals("c")||svar2.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar2.equals("a"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter3 = alt.nextLine();
      //Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("----------------------------------------------------");
      System.out.println("Tredje spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar3 = alt.nextLine() .toLowerCase();
  if (svar3.equals("a")||svar3.equals("b")||svar3.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar3.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter4 = alt.nextLine();
//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
//Fra naa gidder je itte aa finne nye spoersmaal, for det har ingen læring.
//Lar spoersmaalene og svarene vaere likt som forrige spoersmaal naa framover.
      System.out.println("----------------------------------------------------");
      System.out.println("Fjerde spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar4 = alt.nextLine() .toLowerCase();
  if (svar4.equals("a")||svar4.equals("b")||svar4.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar4.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter5 = alt.nextLine();
//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("----------------------------------------------------");
      System.out.println("Femte spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar5 = alt.nextLine() .toLowerCase();
  if (svar5.equals("a")||svar5.equals("b")||svar5.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar5.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter6 = alt.nextLine();
//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("----------------------------------------------------");
      System.out.println("Sjette spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar6 = alt.nextLine() .toLowerCase();
  if (svar6.equals("a")||svar6.equals("b")||svar6.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar6.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter7 = alt.nextLine();
//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("----------------------------------------------------");
      System.out.println("Sjuende spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar7 = alt.nextLine() .toLowerCase();
  if (svar7.equals("a")||svar7.equals("b")||svar7.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar7.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter8 = alt.nextLine();
//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("----------------------------------------------------");
      System.out.println("Aattende spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar8 = alt.nextLine() .toLowerCase();
  if (svar8.equals("a")||svar8.equals("b")||svar8.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar8.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter9 = alt.nextLine();
//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("----------------------------------------------------");
      System.out.println("Niende spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar9 = alt.nextLine() .toLowerCase();
  if (svar9.equals("a")||svar9.equals("b")||svar9.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar9.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for neste spoersmaal.");
      String enter10 = alt.nextLine();
//Lager mellomrom mellom spoersmaalene for meg selv saa det blir lettere aa skille.
      System.out.println("----------------------------------------------------");
      System.out.println("Siste spoersmaal: Hva er svaret?");
      System.out.println("a. 11");
      System.out.println("b. 1");
      System.out.println("c. 42");
      System.out.println("d. 4");
      String svar10 = alt.nextLine() .toLowerCase();
  if (svar10.equals("a")||svar10.equals("b")||svar10.equals("d"))
    {
      System.out.println("Desverre var dette feil svar.");
    }
  else if (svar10.equals("c"))
    {
      System.out.println("Gratulerer! Det var riktig svar!");
      antallriktig = antallriktig +1;
    }
  else
    {
      System.out.println("Dette var itte en av svaralternativene, derfor blir det feil svar.");
    }
      System.out.println("Du har naa: " + antallriktig + " poeng");
      System.out.println("Trykk enter for aa se poengsummen din.");
      String enter11 = alt.nextLine();

      System.out.println("Quizen er naa ferdig.");
      System.out.print("Du fikk " + antallriktig + "/" + spoersmaal);
      System.out.println(" poeng");
  }
}
