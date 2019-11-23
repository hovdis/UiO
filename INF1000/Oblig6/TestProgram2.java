//Testprogram for ordliste og ord.
import java.util.*;
import java.io.*;
class TestProgram2 {
  public static void main(String[] args)throws Exception {
      Ordliste ordLista = new Ordliste();     //Legger til ordlista
      ordLista.lesBok("testTekst.txt");       //Sender med test-txt-fila til ordlista.
        //Kaller paa metoden antallOrd i ordlista
      System.out.println("Antall unike ord er: " +ordLista.antallOrd());
        //Kaller paa metoden vanligste i ordlista
      System.out.println("Det foerste vanligste ordet er: " + ordLista.vanligste());
  }
}
