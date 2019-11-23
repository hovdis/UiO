import java.util.*;
import java.io.*;

public class Mainen{
  public static void main(String[] args) {
        String filnavn = args[0];
        File fil = new File(filnavn);
        Scanner inn = null;
        try {
            inn = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("Fant ikke filen '%s'\n", filnavn);
            System.exit(1);
        }

        Ordliste ordliste = new Ordliste();
        while (inn.hasNextLine()) {
            String ord = inn.nextLine();
            ordliste.leggTil(ord);
        }

        inn.close();

        char startbokstav = args[1].charAt(0);
        ordliste.spill(startbokstav);
    }
}
