import java.util.*;
import java.io.*;
/**
 * Created by Rune on 13.03.2016.
 */
//Paa alle metodene det staar ferdig! paa mangler jeg aa legge til i lister/beholdere.
public class Main {
    //legger til litt farger
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static Tabell<Pasient> pasientTabell = new Tabell<Pasient>(100);       //i index, bruk pasientnummer/loepenummer
    public static Tabell<LegeMiddel> legeMiddelTabell = new Tabell<LegeMiddel>(100);    //i legemiddel kan jeg ha et lopenummer
    public static SortertEnkelListe<Lege> legeTabell = new SortertEnkelListe<Lege>();
    public static EldsteForstReseptListe listen = new EldsteForstReseptListe();
    public static YngsteForstReseptListe yngste = new YngsteForstReseptListe();

    public static void main(String[] args) throws Exception {
        Scanner skriv = new Scanner(System.in);

        boolean loop = true;
        System.out.println("Velkommen til reseptsystemet! Her er valgene dine:");
        while (loop) {
            meny(skriv);
        }
    }

    public static void meny(Scanner skriv) throws Exception {
        System.out.println(ANSI_CYAN + "**************************************************");
        System.out.println("Hva vil du gjoere?");
        System.out.println("1. Lese inn fra fil");
        System.out.println("2. Opprette og legge til et nytt legemiddel");
        System.out.println("3. Opprette og legge til ny lege");
        System.out.println("4. Opprette og legge til ny pasient");
        System.out.println("5. Opprette og legge til et nytt resept");
        System.out.println("6. Hente legemiddelet paa et resept");
        System.out.println("7. Skrive ut statistikk");
        System.out.println("8. skrive til fil");
        System.out.println("**************************************************" + ANSI_RESET);

        String svar = skriv.nextLine();
        valglokke(svar, skriv);
    }

    public static void valglokke(String svar, Scanner skriv) throws Exception {
        if (svar.equals("1")) {
            innleser();
        }  else if (svar.equals("2")) {
            nyttLegemiddel(skriv);
        }  else if (svar.equals("3")) {
            nyLege(skriv);
        }  else if (svar.equals("4")) {
            nyPerson(skriv);
        }  else if (svar.equals("5")) {
            nyResept(skriv);
        }  else if (svar.equals("6")) {
            hentLegemiddel(skriv);
        }  else if (svar.equals("7")) {
            skrivUtStatistikk(skriv);
        }  else if (svar.equals("8")) {
            skrivTilFil();
        }
    }
    public static void nyttLegemiddel(Scanner in) { //maa fikse mye paa
        System.out.println("Hva er navnet paa legemiddelet?");
        String legemiddelNavn = in.nextLine();
        System.out.println("Hvor mye koster legemiddelet?");
        int legemiddelPris = Integer.parseInt(in.nextLine());
        System.out.println("Hvor mye virkestoff (i milligram) er legemiddelet?");
        int legemiddelVirkestoff = Integer.parseInt(in.nextLine());
        System.out.println("Er legemiddelet i form av pille, eller mikstur?");
        String pilleMikstur = in.nextLine();
        System.out.println("Er legemiddelet av type A, B eller C?");
        String type = in.nextLine();
        if (type.equalsIgnoreCase("a") || type.equalsIgnoreCase("b")) {
            System.out.println("Hvor mye styrke er det?");
            int styrke = Integer.parseInt(in.nextLine());
            if (pilleMikstur.equalsIgnoreCase("pille")) {
                System.out.println("Hvor mange piller er det i boksen?");
                int antPiller = Integer.parseInt(in.nextLine());
                if (type.equalsIgnoreCase("a")) {
                    TypeAPille aPille = new TypeAPille(styrke, legemiddelNavn, legemiddelPris, legemiddelVirkestoff, antPiller, "pille");
                    int uniktnummer = aPille.getUniktNummer();
                    legeMiddelTabell.settInn(aPille, uniktnummer);
                    System.out.println(ANSI_BLUE + "a pille ble lagt til" + ANSI_RESET);
                } else {
                    TypeBPille bPille = new TypeBPille(styrke, legemiddelNavn, legemiddelPris, legemiddelVirkestoff, antPiller, "pille");
                    int uniktNummer = bPille.getUniktNummer();
                    legeMiddelTabell.settInn(bPille, uniktNummer);
                    System.out.println(ANSI_BLUE + "b pille ble lagt til" + ANSI_RESET);
                }
            } else if (pilleMikstur.equalsIgnoreCase("mikstur")) {
                    System.out.println("Hvor mange cm3 er det i en flaske?");
                    int mengdecm3 = Integer.parseInt(in.nextLine());
                    if (type.equalsIgnoreCase("a")) {
                        TypeAMikstur aMikstur = new TypeAMikstur(styrke, legemiddelNavn, legemiddelPris, legemiddelVirkestoff, mengdecm3, "mikstur");
                        int uniktNummer = aMikstur.getUniktNummer();
                        legeMiddelTabell.settInn(aMikstur, uniktNummer);
                        System.out.println(ANSI_BLUE + "a mikstur ble lagt til" + ANSI_RESET);
                    } else if (type.equalsIgnoreCase("b")) {
                        TypeBMikstur bMikstur = new TypeBMikstur(styrke, legemiddelNavn, legemiddelPris, legemiddelVirkestoff, mengdecm3, "mikstur");
                        int uniktNummer = bMikstur.getUniktNummer();
                        legeMiddelTabell.settInn(bMikstur, uniktNummer);
                        System.out.println(ANSI_BLUE + "b mikstur ble lagt til" + ANSI_RESET);
                    }
                }
        } else if (type.equalsIgnoreCase("c")) {
            if (pilleMikstur.equalsIgnoreCase("pille")) {
                System.out.println("Hvor mange piller er det i boksen?");
                int antPiller = Integer.parseInt(in.nextLine());
                TypeCPille cPille = new TypeCPille(legemiddelNavn, legemiddelPris, legemiddelVirkestoff, antPiller, "pille");
                int uniktNummer = cPille.getUniktNummer();
                legeMiddelTabell.settInn(cPille, uniktNummer);
                System.out.println(ANSI_BLUE + "c pille ble lagt til" + ANSI_RESET);
            } else if (type.equalsIgnoreCase("mikstur")) {
                System.out.println("Hvor mange cm3 er det i en flaske?");
                int cm3 = Integer.parseInt(in.nextLine());
                TypeCMikstur cMikstur = new TypeCMikstur(legemiddelNavn, legemiddelPris, legemiddelVirkestoff, cm3, "mikstur");
                int uniktNummer = cMikstur.getUniktNummer();
                legeMiddelTabell.settInn(cMikstur, uniktNummer);
                System.out.println(ANSI_BLUE + "c mikstur ble lagt til" + ANSI_RESET);
            } else {
                System.out.println("Du har skrevet inn noe feil");
            }
        }
    }
    public static void nyLege(Scanner in) {
        System.out.println("Hva heter legen?");
        String legeNavn = "Dr. " + in.nextLine();
        System.out.println("Hvor mange avtaler har " + legeNavn + "?");
        int avtaler = Integer.parseInt(in.nextLine());
        Lege lege = new Lege(legeNavn, avtaler);
        legeTabell.settInn(lege);
        System.out.println(ANSI_PURPLE + legeNavn + " ble lagt til!" + ANSI_RESET);
    }
    public static void nyPerson(Scanner in) {
        System.out.println("Hva heter pasienten?");
        String pasientNavn = in.nextLine();
        System.out.println("Hva er foedselsnummeret til pasienten?");
        String foedselsNummerPasient = in.nextLine();
        System.out.println("Hva er adressen til pasienten?");
        String adressePasient = in.nextLine();
        System.out.println("Hva er postnummeret til pasienten?");
        String postnummerPasient = in.nextLine();
        Pasient nyPasient = new Pasient (pasientNavn, foedselsNummerPasient, adressePasient, postnummerPasient);
        int rekkeNummer = nyPasient.getLoepeNR();
        pasientTabell.settInn(nyPasient, rekkeNummer);  //pasient p.getpasientnummer();
        System.out.println(ANSI_PURPLE + "En ny pasient ble lagt til!" + ANSI_RESET);
    }
    public static void nyResept(Scanner in) {
        System.out.println("Er resepten blaa eller hvit?");
        String blaaHvit = in.nextLine();
        System.out.println("Hva er nummeret paa pasienten?");
        int persNummer = Integer.parseInt(in.nextLine());
        System.out.println("hvilket nummer er legemiddellet?");
        int legeNummer = Integer.parseInt(in.nextLine());
        System.out.println("Hva er navnet paa legen? ");
        String halla = "Dr. " + in.nextLine();
        System.out.println("Hvor mange reit er det?");
        int antReit = Integer.parseInt(in.nextLine());
        LegeMiddel legeMiddel = legeMiddelTabell.finnObjekt((legeNummer));
        Lege legePeker = legeTabell.finnObjekt(halla);
        if (blaaHvit.equalsIgnoreCase("blaa")) {
            BlaaResept blaaResept = new BlaaResept(legeMiddel, antReit, persNummer, legePeker);
            listen.settInn(blaaResept);
            yngste.settInn(blaaResept);
        } else if (blaaHvit.equalsIgnoreCase("hvit")) {
            int pris = legeMiddelTabell.finnObjekt(legeNummer).getPris();
            HvitResept hvitResept = new HvitResept(legeMiddel, antReit, persNummer, legePeker, pris);
            listen.settInn(hvitResept);
            yngste.settInn(hvitResept);
        } else {
            System.out.println(ANSI_RED + "Du tastet inn noe feil. proev igjen" + ANSI_RESET);
            return;
        }
        System.out.println(blaaHvit + " ble langt til");
    }
    public static void hentLegemiddel(Scanner in) {
        System.out.println("hente et legemiddel på et resept");
        int reseptnummer;
        System.out.println("Hva er reseptnummeret?");
        reseptnummer = in.nextInt();
        Resepter resept = null;
        try {
            resept = yngste.finnResept(reseptnummer);
            System.out.println(resept);
        }  catch (Exception e) {
            System.out.println("resepten ble ikke funnet");
            return;
        }
        if(!resept.erGyldig()) {
           System.out.println("Resepten er ikke gyldig");
        } else {
            System.out.println("hei");
            System.out.printf("Legemiddel: " + resept.getLegeMiddelPeker().toString());
            System.out.println("Antall reit igjen paa legemiddel: " + resept.getAntallReit());
            System.out.println("Lege: " + resept.getLegePeker().hentNavn());
            System.out.println("Pasient: " + resept.getPersonNR());
            System.out.println("Beloep aa betale: " + resept.getPris());
        }

    }
    public static void skrivUtStatistikk(Scanner in) {
        System.out.println("Hva vil du skrive ut?");
        System.out.println("1. Hvor mange vanedannende medisiner som er skrevet ut,");
        System.out.println("samt hvor mange som er skrevet ut i Oslo?");
        System.out.println("2. Skrive ut alle de blaa reseptene til en person.");
        System.out.println("3. skrive ut alle mikstur-prepaater for en lege, samt virkestoff-mengden til piller og mikstur denne legen har skrevet ut");
        System.out.println("4. Liste opp alle leger, og se hvor mange narkotiske legemidler hen har gikk ut");
        System.out.println("5. Liste opp alle personer som har narkotiske legemidler (resept), og hvor mange");
        int valg = Integer.parseInt(in.nextLine());

        if (valg == 1) {

        } else if (valg == 2) {

        } else if (valg == 3) {

        } else if (valg == 4) {

            System.out.println("hei");
            for (Lege l: legeTabell) {
                System.out.println(l.hentNavn());
                int i = 0;
                for (Resepter r : listen ) {
                    Lege tempLege = r.getLegePeker();
                    LegeMiddel temp = r.getLegeMiddelPeker();
                    if (temp instanceof TypeA && tempLege.hentNavn().equalsIgnoreCase(l.hentNavn())) {
                       i++;
                    }
                }
                System.out.println(i);
            }
        } else if (valg == 5) {

        } else {
            System.out.println(ANSI_RED + "Dette var ikke et valg" + ANSI_RESET);
            return;
        }

    }




    public static void innleser() throws Exception {
        Scanner filInnleser = new Scanner(new File("testFil.txt"));
        String linja = filInnleser.nextLine();
        while (!linja.startsWith("# Slutt")) {
            if (linja.startsWith("# Personer")) {
                System.out.println("Personer\n");
                linja = filInnleser.nextLine();
                while (!linja.equalsIgnoreCase("")){
                    String[] personer = linja.split(", ");
                    int rekkeNummer = Integer.parseInt(personer[0]);
                    Pasient nyPasient = new Pasient (personer[1], personer[2], personer[3], personer[4]);
                    pasientTabell.settInn(nyPasient, rekkeNummer);  //pasient p.getpasientnummer();
                    System.out.println(personer[1]);
                    linja = filInnleser.nextLine();
                }
            } else if (linja.startsWith("# Legemidler")) {
                System.out.println("# Legemidler");
                linja = filInnleser.nextLine();
                while (!linja.equalsIgnoreCase("")) {
                    String[] legeMidler = linja.split(", ");
                    int plassIBeholder = Integer.parseInt(legeMidler[0]);
                    int pris = Integer.parseInt(legeMidler[4]);
                    int antallMengde = Integer.parseInt(legeMidler[5]);
                    int virkestoff = Integer.parseInt(legeMidler[6]);
                    if (legeMidler[3].equalsIgnoreCase("a")) {
                        if (legeMidler[2].equalsIgnoreCase("mikstur")) {
                            leggTilAMikstur(plassIBeholder, legeMidler[1], pris, virkestoff, antallMengde, Integer.parseInt(legeMidler[7]));
                        } else {
                            leggTilAPille(plassIBeholder, Integer.parseInt(legeMidler[7]), legeMidler[1], pris, virkestoff, antallMengde);
                        }
                    } else if (legeMidler[3].equalsIgnoreCase("b")) {
                        if (legeMidler[2].equalsIgnoreCase("mikstur")) {
                            leggTilBMikstur(plassIBeholder, Integer.parseInt(legeMidler[7]), legeMidler[1], pris, virkestoff, antallMengde);
                        } else {
                            leggTilBPille(plassIBeholder, Integer.parseInt(legeMidler[7]), legeMidler[1], pris, virkestoff, antallMengde);
                        }
                    } else {
                        if (legeMidler[2].equalsIgnoreCase("mikstur")) {
                            leggTilCMikstur(plassIBeholder, legeMidler[1], pris, virkestoff, antallMengde);
                        } else{
                            leggTilCPille(plassIBeholder, legeMidler[1], pris, virkestoff, antallMengde);
                        }
                    } linja = filInnleser.nextLine();
                }
            } else if (linja.startsWith("# Leger")) {
                System.out.println("# Leger");
                linja = filInnleser.nextLine();
                while (!linja.equalsIgnoreCase("")) {
                    String[] leger = linja.split(", ");
                    int avtaler = Integer.parseInt(leger[1]);
                    Lege lege = new Lege(leger[0], avtaler);
                    legeTabell.settInn(lege);
                    System.out.println(leger[0]);
                    linja = filInnleser.nextLine();
                }
            } else if (linja.startsWith("# Resepter")) {
                System.out.println("# Resepter");
                linja = filInnleser.nextLine();
                while (!linja.equalsIgnoreCase("")) {
                    String[] resept = linja.split(", ");
                    int listeNummer = Integer.parseInt(resept[0]);
                    String type = resept[1];
                    int personNummer = Integer.parseInt(resept[2]);
                    String legeNavn = resept[3];
                    int legeMiddelNummer = Integer.parseInt(resept[4]);
                    int antallReit = Integer.parseInt(resept[5]);
                    LegeMiddel legeMiddel = legeMiddelTabell.finnObjekt(legeMiddelNummer);
                    Lege legePeker = legeTabell.finnObjekt(legeNavn);
                    int pris = legeMiddelTabell.finnObjekt(legeMiddelNummer).getPris();
                    if (type.equalsIgnoreCase("hvit")) {
                        HvitResept nyResept = new HvitResept(legeMiddel, antallReit, personNummer, legePeker, pris);
                        listen.settInn(nyResept);
                        yngste.settInn(nyResept);
                    } else {
                        BlaaResept nyResept = new BlaaResept(legeMiddel, antallReit, personNummer, legePeker);
                        listen.settInn(nyResept);
                        yngste.settInn(nyResept);
                    }
                    System.out.println(type + " ble langt til");
                    linja = filInnleser.nextLine();
                }
            }
            linja = filInnleser.nextLine();
        }
    }
    public static void leggTilAMikstur(int plass, String navn, int pris, int virkestoffMG, int cm3, int styrke) {
        TypeAMikstur aMikstur = new TypeAMikstur(styrke, navn, pris, virkestoffMG, cm3, "mikstur");
        System.out.println("a mikstur lagt til");
        legeMiddelTabell.settInn(aMikstur, plass);
    }
    public static void leggTilBMikstur(int plass, int styrke, String navn, int pris, int virkestoff, int cm3) {
        TypeBMikstur bMikstur = new TypeBMikstur(styrke, navn, pris, virkestoff, cm3, "mikstur");
        System.out.println("b mikstur lagt til");
        legeMiddelTabell.settInn(bMikstur, plass);
    }
    public static void leggTilCMikstur(int plass, String navn, int pris, int virkestoffMG, int cm3) {
        TypeCMikstur cMikstur = new TypeCMikstur(navn, pris, virkestoffMG, cm3, "mikstur");
        System.out.println("c mikstur lagt til");
        legeMiddelTabell.settInn(cMikstur, plass);
    }
    public static void leggTilAPille(int plass, int styrke, String navn, int pris, int virkestoffMG, int antPiller) {
        TypeAPille aPille = new TypeAPille(styrke, navn, pris, virkestoffMG, antPiller, "pille");
        System.out.println("a pille lagt til");
        legeMiddelTabell.settInn(aPille, plass);
    }
    public static void leggTilBPille(int plass, int styrkeVanedannende, String navn, int pris, int virkestoffMG, int antPiller) {
        TypeBPille bPille = new TypeBPille(styrkeVanedannende, navn, pris, virkestoffMG, antPiller, "pille");
        System.out.println("b pille lagt til");
        legeMiddelTabell.settInn(bPille, plass);
    }
    public static void leggTilCPille(int plass, String navn, int pris, int virkestoffMG, int antPiller) {
        TypeCPille cPille = new TypeCPille(navn, pris, virkestoffMG, antPiller, "pille");
        System.out.println("c pille lagt til");
        legeMiddelTabell.settInn(cPille, plass);
    }

    public static void skrivTilFil() {
        System.out.println(ANSI_RED + "Skrive til fil1" + ANSI_RESET);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter("testFil.txt");
        } catch (FileNotFoundException fnfe) {
            System.out.println("Fant itte fila");
        }

        pw.println("# Personer (nr, navn, fnr, adresse, postnr)");
        for (Pasient a: pasientTabell) {
            pw.println(a.getLoepeNR() + ", " +a.toString() + ", " + a.getFoedselsNR() + ", " + a.getAdresse() + ", " + a.getPostNR());
        }
        pw.println("");
        pw.println("# Legemidler (nr, navn, form, type, pris, antall/mengde, virkestoff [, styrke])");
        for (LegeMiddel lm : legeMiddelTabell) {
            if (lm instanceof TypeAMikstur) {
                pw.println(lm.getUniktNummer()+", "+lm.toString()+", "+lm.getType()+", "+lm.getPris()+", "+((TypeAMikstur) lm).virkestoffPrCm3()+", "+lm.virkestoffMG+", "+((TypeA) lm).getStyrke());
            } else if (lm instanceof TypeAPille) {
                pw.println(lm.getUniktNummer()+", "+lm.toString()+", "+lm.getType()+", "+lm.getPris()+", "+((TypeAPille) lm).getAntPiller()+", "+lm.virkestoffMG+", "+((TypeAPille) lm).getStyrke());
            } else if (lm instanceof TypeBMikstur) {
                pw.println(lm.getUniktNummer()+", "+lm.toString()+", "+lm.getType()+", "+lm.getPris()+", "+((TypeBMikstur) lm).virkestoffPrCm3()+", "+lm.virkestoffMG+", "+((TypeBMikstur) lm).getStyrke());
            } else if (lm instanceof TypeBPille) {
                pw.println(lm.getUniktNummer()+", "+lm.toString()+", "+lm.getType()+", "+lm.getPris()+", "+((TypeBPille) lm).getAntPiller()+", "+lm.virkestoffMG+", "+((TypeBPille) lm).getStyrke());
            } else if (lm instanceof TypeCMikstur) {
                pw.println(lm.getUniktNummer()+", "+lm.toString()+", "+lm.getType()+", "+lm.getPris()+", "+((TypeCMikstur) lm).virkestoffPrCm3()+", "+lm.virkestoffMG);
            } else if (lm instanceof TypeCPille) {
                pw.println(lm.getUniktNummer()+", "+lm.toString()+", "+lm.getType()+", "+lm.getPris()+", "+((TypeCPille) lm).getAntPiller()+", "+lm.virkestoffMG);
            }
        }
        pw.println("");
        pw.println("# Leger (navn, avtalenr / 0 hvis ingen avtale)");
        for (Lege l : legeTabell) {
            pw.println(l.navn+", "+l.avtaler);
        }
        pw.println("");
        pw.println("# Resepter (nr, hvit/blÃ¥, persNummer, legeNavn, legemiddelNummer, reit)");
        for (Resepter r : listen) {
            pw.println(r.getUniktNummer()+", "+r.getFarge()+", "+r.getPersonNR()+", "+r.getLegePeker().toString()+", "+r.getLegeMiddelPeker().getUniktNummer()+", "+r.getAntallReit());
        }
        System.out.println("heiigjen");
        pw.println("");
        pw.println("# Slutt");
        pw.close();
    }
}
