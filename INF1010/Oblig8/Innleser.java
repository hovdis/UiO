/**
 * Created by Rune on 05.04.2016.
 */
import java.io.*;
import java.util.*;

public class Innleser {
    private final char TOM_RUTE_TEGN = '.';
    String filNavn;

    //objekter
    public Brett brett;

    //variabler
    public int brettHoyde;
    public int brettBredde;
    public int brettStoerrelse;


    Innleser(String filNavn) {
        this.filNavn = filNavn;
    }

    public void lesInnFil() throws Exception {
        Scanner fc = new Scanner(new File(filNavn));
        brettHoyde = Integer.parseInt(fc.nextLine());
        brettBredde = Integer.parseInt(fc.nextLine());
        brettStoerrelse = brettBredde * brettHoyde;
        brett = new Brett(brettHoyde, brettBredde);
        brett.fyllInnBlankeRuter();

        for (int i = 0; i < brettStoerrelse; i++) {
            String raden = fc.nextLine();
            for (int k = 0; k < brettStoerrelse; k++) {
                char karakteren = raden.charAt(k);
                int verdi = tegnTilVerdi(karakteren);
                Rute rute = new Rute(verdi);
                brett.settRute(rute, i, k);
            }
        }
        brett.opprettDatastruktur();
    }

    /*public void skrivUtBrett() {
        System.out.println(brettHoyde);
        System.out.println(brettBredde);
        for (int i = 0; i < brettStoerrelse; i++) {
            for (int k = 0; k < brettStoerrelse; k++) {
                int verdi = brett.getVerdiRute(i, k);
                char karakter = verdiTilTegn(verdi);
                System.out.print(karakter);
            }
            System.out.println("");
        }
    }*/
    public void skrivBrettFinnAlleMulige() {
        brett.printfinnMulige();
    }
    public void skrivUtBrett(){
        int vannRettStrekCounter = 0;
        int vannrettStrek = 0;
        Rute[][] ruteArray = brett.getRuteArray();

        for(int i = 0; i < ruteArray.length; i++){ //For hver kolonne
            int loddrettStrek = 0;
            for(int k = 0; k < ruteArray[i].length; k++){ //For hver rad
                System.out.print(verdiTilTegn(ruteArray[i][k].getVerdi()));
                loddrettStrek++;
                if(loddrettStrek == brettBredde){ //For hver tredje char, skriv ut |
                    if(k != brettBredde*brettHoyde-1){
                        System.out.print("|");
                        loddrettStrek = 0;
                    }
                }
            } //</rad>


            vannrettStrek++;
            if(vannrettStrek == brettHoyde){ //Skriv ut ---+---+---
                StringBuilder s = new StringBuilder();
                s.append("\n");
                for(int y = 0; y < brettHoyde; y++){
                    for(int x = 0; x<brettBredde; x++){ //Antall streker
                        s.append("-");
                    }
                    if(y != brettHoyde - 1) s.append("+"); //Legg til + hver gang man går forbi en boks
                }
                if(vannRettStrekCounter == brettBredde-1){ //Om det er siste linje så skal det ikke skrives ut noe
                    System.out.println();
                    break;
                }
                System.out.println(s.toString()); //Skriv ut stringbuilder strengen
                vannrettStrek = 0;
                vannRettStrekCounter++;
            }
            else{
                System.out.println(); //Skriv ut tom linje om det ikke er ny boks.
            }
        } //</Kolonne>
    }
    /*public void print() {
        int radTeller = 0;
        int kolonneTeller = 0;
        Rute[][] ruteArray = brett.getRuteArray();
        for(int i = 0 ; i < brettStoerrelse ; i++) { //skal gaa helt ned til siste
            while (kolonneTeller != brettBredde) {
                System.out.println(ruteArray[radTeller][kolonneTeller]);
            }
        }
    }*/


    /**
     * Oversetter et tegn (char) til en tallverdi (int)
     *
     * @param tegn tegnet som skal oversettes
     * @return verdien som tegnet tilsvarer
     */
    public int tegnTilVerdi(char tegn) {
        if (tegn == TOM_RUTE_TEGN) {                // tom rute, DENNE KONSTANTEN MAA DEKLARERES
            return 0;
        } else if ('1' <= tegn && tegn <= '9') {    // tegn er i [1, 9]
            return tegn - '0';
        } else if ('A' <= tegn && tegn <= 'Z') {    // tegn er i [A, Z]
            return tegn - 'A' + 10;
        } else if (tegn == '@') {                   // tegn er @
            return 36;
        } else if (tegn == '#') {                   // tegn er #
            return 37;
        } else if (tegn == '&') {                   // tegn er &
            return 38;
        } else if ('a' <= tegn && tegn <= 'z') {    // tegn er i [a, z]
            return tegn - 'a' + 39;
        } else {                                    // tegn er ugyldig
            return -1;
        }
    }

    /**
     * Oversetter en tallverdi (int) til et tegn (char)
     *
     * @param verdi verdien som skal oversettes
     * @param tom   tegnet som brukes for aa representere 0 (tom rute)
     * @return tegnet som verdien tilsvarer
     */
    public char verdiTilTegn(int verdi) {
        if (verdi == 0) {                           // tom
            return '.';
        } else if (1 <= verdi && verdi <= 9) {      // tegn er i [1, 9]
            return (char) (verdi + '0');
        } else if (10 <= verdi && verdi <= 35) {    // tegn er i [A, Z]
            return (char) (verdi + 'A' - 10);
        } else if (verdi == 36) {                   // tegn er @
            return '@';
        } else if (verdi == 37) {                   // tegn er #
            return '#';
        } else if (verdi == 38) {                   // tegn er &
            return '&';
        } else if (39 <= verdi && verdi <= 64) {    // tegn er i [a, z]
            return (char) (verdi + 'a' - 39);
        } else {                                    // tegn er ugyldig
            System.out.println("kaster unntak");    // HUSK DEFINISJON AV UNNTAKSKLASSE
            return '0';
        }
    }
}
