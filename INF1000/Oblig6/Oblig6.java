import java.util.*;
import java.io.*;
class Oblig6 {
  public static void main(String[] args)throws Exception {
      Scanner in = new Scanner(System.in);
      Ordliste ordLista = new Ordliste();
      ordLista.lesBok("scarlet.text");
      //Oppgave 6.3 a)
      System.out.println("Antall unike ord er: " +ordLista.antallOrd());
      //Oppgave 6.3 b)
      System.out.println("Ordet ´Holmes´ forekommer " + ordLista.antallForekomster("holmes") + " gang(er).");
      //Oppgave 6.3 c)
      System.out.println("Ordet ´elementary´ forekommer " + ordLista.antallForekomster("elementary") + " gang(er).");
      //Oppgave 6.3 d)
      System.out.println("Det foerste vanligste ordet er: " + ordLista.vanligste());

      //Naa lager je en liten tillegs-oppgave som gjoer at du kan soke paa hvilket som helst
      //ord, saa lenge du vil.
      //Je kunne brukt boolean her, men valgte aa gjoere det med en string i stella.
      String loope = "ja";                          //Hvis denni blir skifta paa slitter while-loopen aa kjore.
      String soke;                                  //Ordet du soker paa.
      while(loope .equals ("ja")) {                 //Mens variabelen loope er ja.
        System.out.println("Hva vil du soke paa?");
        soke = in.nextLine();                       //Lagrer ordet i en variabel
        if(ordLista.finnOrd(soke) == null) {        //Hvis finnord returnerer null(ordet vinnes itte.)
            System.out.println("Ordet du sokte paa finnes itte.");
        } else {                                    //Om ordet finnes
        System.out.println("Antall ganger " + soke + " forekommer er: " + ordLista.antallForekomster(soke));
        }
        System.out.println("Vil du soke paa et ord til?");
        String loopen = in.nextLine();              //Lagrer svaret.
        if(!loope .equalsIgnoreCase (loopen)) {     //Om ordet itte er lik variabelen loope (noe annet enn ja)
          loope = "nei";                            //Bytter loope til nei. Dette kunne blitt bytta til alt anna enn ja.
        }
      }

  }
}
