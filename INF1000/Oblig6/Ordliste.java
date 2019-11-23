import java.io.*;
import java.util.*;
class Ordliste {    //klassen Ordliste
  ArrayList<Ord> liste = new ArrayList<Ord>();    //Arrayliste som legger til ordene i tekst-fila.
      //Her kunne je ogsaa ha lagd hashmap i stedet.
  public void lesBok(String filnavn) throws Exception {
    String nesteOrd;                              //Plassholder for neste ord i boka.
    File minFil = new File(filnavn);
    Scanner ai = new Scanner (minFil);

    for(int i=0;ai.hasNextLine();i++) {           //Leser gjennom boka
      nesteOrd = ai.nextLine();
      leggTilOrd(nesteOrd);                       //Sender med hvert ord til metoden leggTilOrd.
    }
  } private void leggTilOrd(String ord) {

      if(finnOrd(ord) == null) {                  //Hvis metoden returnerer null
        Ord ordEne = new Ord(ord);                //Oppretter et nytt ord-objektet
        liste.add(ordEne);                        //Legger til ordet i lista
      } else {
        finnOrd(ord).oekAntall();                 //om ordet finnes, oeker vi antall
      }
  }
  public Ord finnOrd(String tekst) {
    for(int i=0;i<liste.size();i++){
          //Hvis inputen er lik det i lista
      if(tekst.equalsIgnoreCase (liste.get(i).toString())) {
        return liste.get(i);                      //returnerer ordet
      }
    } return null;                                //Hvis itte ordet finnes, returnerer null
  }
  public int antallOrd() {
    return liste.size();                          //Returnerer antallet ord i lista
  }
  public int antallForekomster(String tekst) {
      int foreKomster=finnOrd(tekst).hentAntall();//Legger antall forekomster i egen variabel for det
      return foreKomster;                         //Returnerer variabelen med antall forekomster
  }
  public Ord vanligste() {
    int storstAntall = 0;
    String mestBruktOrd;
    int k=0;
    for(int i=0;liste.size()>i;i++) {
      if(liste.get(i).hentAntall()>storstAntall) { //Hvis antallet forekomster i objekter er stoerre enn storstAntall
        storstAntall=liste.get(i).hentAntall();    //Setter det antallet objektet til storstAntall
        mestBruktOrd=liste.get(i).toString();      //Setter navnet til objektet til mestBruktOrd
        k++;
      }
    }
    return liste.get(k);                           //Returnerer objektet som forekommer flest ganger
  }
}
