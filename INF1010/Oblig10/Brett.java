/**
 * Created by Rune on 02.04.2016.
 */
import java.util.*;
import java.io.*;

public class Brett {

    /*Klassen brett maa inneholde info om alle rutene i sudokubrettet.
    Infoen om hver rude lagres i objekter av klassen Rute*/

    /*Variabler for stoerrelsene*/
    private int boksHoyde;
    private int boksBredde;
    private int brettStoerrelse;

    /*Arrayer for brett-setup*/
    protected Rute[][] ruteArray;
    private Rad[] radArray;
    private Kolonne[] kolonneArray;
    private Boks[] boksArray;

    /*konstruktor*/
    Brett (int boksHoyde, int boksBredde) {
        this.boksHoyde = boksHoyde;
        this.boksBredde = boksBredde;
        brettStoerrelse = boksBredde*boksHoyde;
        ruteArray = new Rute[brettStoerrelse][brettStoerrelse];
        //fyllInnBlankeRuter();
    }

    public void fyllInnBlankeRuter() {
        //forlokke for rader
        for (int i = 0 ; i < ruteArray.length ; i++) {
            //forlokke for kolonner
            for (int k = 0 ; k < ruteArray.length ; k++) {
                //lager ny rute(tom) paa brettet paa plass [i][k]
                ruteArray[i][k] = new Rute();
            }
        }
    }

    public void settRute(Rute rute, int rad, int kolonne) {
        ruteArray[rad][kolonne] = rute;
    }
    public int getVerdiRute(int rad, int kolonne) {
        int returVerdi = ruteArray[rad][kolonne].getVerdi();
        return returVerdi;
    }
    public void opprettDatastruktur(Innleser inn) {
        radArray = new Rad[brettStoerrelse];
        kolonneArray = new Kolonne[brettStoerrelse];
        boksArray = new Boks[brettStoerrelse];

        //oppretter kolonner og rader
        for(int i = 0 ; i < brettStoerrelse ; i++) {
            kolonneArray[i] = new Kolonne(brettStoerrelse);
            radArray[i] = new Rad(brettStoerrelse);
            boksArray[i] = new Boks(brettStoerrelse);
        }

        //Legger ruter og kolonner inn i riktig rad
        for (int i = 0 ; i < ruteArray.length ; i++) {
            for (int k = 0; k< ruteArray.length ;k++) {
                ruteArray[i][k].settKolonne(kolonneArray[k]); //setter kolonnen inn i ruteArrayet
                kolonneArray[k].settRute(ruteArray[i][k]);
                ruteArray[i][k].settRad(radArray[i]);
                radArray[i].settRute(ruteArray[i][k]);
                ruteArray[i][k].settBrett(this);
            }
        }

        //oppretter nestepeker for rutene
        for(int i = 0; i <brettStoerrelse; i++){
            for(int j = 0; j<brettStoerrelse; j++){
                if(j<brettStoerrelse-1) {
                    ruteArray[i][j].neste = ruteArray[i][j + 1];
                } else if(i<brettStoerrelse-1){
                    ruteArray[i][j].neste = ruteArray[i+1][0];
                }
            }
        }

        int teller1 = -1;
        int teller2 = -1;

        //legger inn ruter inni boks
        for(int i = 0; i < ruteArray.length; i++){
            if(i%boksHoyde == 0){
                teller2++;
            }
            for(int j = 0; j < ruteArray.length; j++){
                if(j%boksBredde == 0){
                    teller1++;
                }

                ruteArray[i][j].settBoks(boksArray[teller1+(teller2*boksHoyde)]);
                boksArray[teller1+(teller2*boksHoyde)].settRute(ruteArray[i][j]);
                ruteArray[i][j].settInnleser(inn);

            }
            teller1 = -1;
        }

    }
    public void printfinnAlleMulige(){
        System.out.println("");

        for(int i = 0; i<ruteArray.length; i++){
            for(int j = 0; j<ruteArray.length; j++) {
                if (ruteArray[i][j].finnAlleMuligeTall() != null) {
                    System.out.println("");
                    if (ruteArray[i][j].getVerdi() != 0) {
                        System.out.print("Ruta er fyllt opp");
                    } else{
                        for (int k = 0; k < ruteArray[i][j].finnAlleMuligeTall().length; k++) {
                            System.out.print(ruteArray[i][j].finnAlleMuligeTall()[k]);
                        }
                    }
                }
            }
            System.out.println("");
        }
    }
    public void finnAlleMuligeEnRute(){
        Scanner in = new Scanner(System.in);
        System.out.println("Hvilken rad er ruta i?");
        int rad = Integer.parseInt(in.nextLine());
        System.out.println("Hvilken kolonne er ruta i?");
        int kolonne = Integer.parseInt(in.nextLine());
        if (rad > boksBredde || kolonne > boksHoyde){
            System.out.println("Du oppga en verdi som var for stor");
        } else if (ruteArray[rad][kolonne].getVerdi() != 0) {
            System.out.println("Denne ruta er allerede fylt opp. verdien i den er: " + ruteArray[rad][kolonne].getVerdi());
        } else {
            int[] alleTallIRute = ruteArray[rad][kolonne].finnAlleMuligeTall();
            for(int i = 0; i < alleTallIRute.length ; i++) {
                System.out.print(alleTallIRute[i]);
            }
            System.out.println("");
        }
    }
    public Rute[][] getRuteArray() {
        return ruteArray;
    }
    public void fyllUtAlt() {

        ruteArray[0][0].fyllUtDenneRutaOgResten();

    }
}
