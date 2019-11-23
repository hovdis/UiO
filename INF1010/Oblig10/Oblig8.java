/**
 * Created by Rune on 02.04.2016.
 */
import java.io.*;
import java.util.*;
public class Oblig8 {
    public int rader;
    public int kolonner;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.println("Hva heter fila paa sudokuen?");
        String filnavn = in.nextLine();
        Innleser innleser = new Innleser(filnavn);
        innleser.lesInnFil();
        innleser.skrivUtBrett();
        innleser.skrivBrettFinnAlleMulige();
        //innleser.SkrivBrettFinnAlleMuligeEnRute();
        innleser.fyllUtAltOgPrint();
    }

}
