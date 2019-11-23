import java.util.*;
import java.io.*;

class Oblig7 {
  public static void main(String[] args) throws Exception {
    DvdArkiv arkiv = new DvdArkiv();
    Scanner in = new Scanner (System.in);
    arkiv.innLeser();
    boolean meny = true;
    System.out.println("Velkommen til DVD-arkivet. Her er tingene du kan gjøre.");
    while (meny) {
      meny = menyen(meny, arkiv, in);
    }
  }

  public static boolean menyen(boolean meny, DvdArkiv arkiv, Scanner in) {
    String menyvalg;
    //Scanner in = new Scanner (System.in);
    System.out.println("----------------------------------");
    System.out.println("1. Opprette en ny person");
    System.out.println("2. Kjoepe en DVD");
    System.out.println("3. Laane en DVD fra en annen person");
    System.out.println("4. Vis oversikt over en eller alle personer");
    System.out.println("5. Vis en oversikt over alle personene.");
    System.out.println("6. Returnere en DVD");
    System.out.println("7. Avlsutte programmet");
    menyvalg = in.nextLine();

    if (menyvalg.equalsIgnoreCase ("1")) {
        arkiv.nyPerson();
    } if (menyvalg.equalsIgnoreCase ("2")) {
        arkiv.kjoepDvd();
    } if (menyvalg.equalsIgnoreCase ("3")) {
        arkiv.laanDvd();
    } if (menyvalg.equalsIgnoreCase ("4")) {
        arkiv.visOversiktPerson();
    } if (menyvalg.equalsIgnoreCase ("5")) {
        arkiv.oversikt();
    } if (menyvalg.equalsIgnoreCase ("6")) {
        arkiv.returnDvd();
    } if (menyvalg.equalsIgnoreCase ("7")) {
      meny = false;
      return meny;
    } return meny;
  }
}
