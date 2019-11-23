import java.util.*;
import java.io.*;

class DvdArkiv {
  Scanner in = new Scanner (System.in);
  private String eier, tittel, innLaanTager, utLaaner, navn;

  HashMap<String, Person> personer = new HashMap<>();

  public void nyPerson() { //Funker
    System.out.println("Hva heter den nye personen?");
    navn = in.nextLine();
    if (findPerson(navn)) {
      System.out.println(navn + " finnes allerede i person-arkivet");
    } else {
      Person person = new Person (navn);
      personer.put(person.toString(), person);
      System.out.println(navn + " ble lagt til i systemet.");
    }
  }

  public void innleserNyPerson(String navnet) { //funker
    Person person = new Person (navnet);
    personer.put(person.toString(), person);
  }

  public boolean findPerson(String navn) { //funker
    if (personer.get(navn) == null) {
      return false;
    } return true;
  }

  public void kjoepDvd() { //funker
    System.out.println("Hvem har kjoept en DVD?");
    eier = in.nextLine();
    if (findPerson(eier)) {
      System.out.println("Hva heter DVD´en?");
      tittel = in.nextLine();
      personer.get(eier).kjoepDvd(tittel, personer.get(eier));
    } else {
      System.out.println("Det er ingen som heter " + eier);
    }
  }

  public void innleserKjoepDvd(String tittel, Person eier) { //funker
    Dvd dvd = new Dvd(tittel, eier);
    eier.eidDvd.put(tittel, dvd);
  }

  public void innleserKjoepUtlaantDvd(String tittel, Person eier, Person laanTager) { //funker
    Dvd dvd = new Dvd(tittel, eier, laanTager);
    eier.laantUtDvd.put(tittel, dvd);
    laanTager.laantInnDvd.put(tittel, dvd);
  }

  public void laanDvd() { //funker
    System.out.println("Hvem vil laane en DVD?");
    innLaanTager = in.nextLine();
    if (!findPerson(innLaanTager)) {
      System.out.println("Det finnes ingen som heter " + innLaanTager);
    } else {
      System.out.println("Hvem vil " + innLaanTager + " laane av?");
      eier = in.nextLine();
      if (!findPerson(eier)) {
        System.out.println(eier + " finnes itte i systemet");
      } else {
        System.out.println("Hva heter DVD´en?");
        tittel = in.nextLine();
        if (personer.get(eier).findDvd(tittel)) {
          Dvd laaneDvd = personer.get(eier).laanUtDvd(tittel, personer.get(innLaanTager));
          personer.get(innLaanTager).laanInnDvd(laaneDvd);
        } else {
          System.out.println("Det er ingen filmer " + eier + " har som heter " + tittel);
        }
      }
    }
  }

  public void returnDvd() { //funker
    System.out.println("Hvem har laant DVD´en?");
    innLaanTager = in.nextLine();
    System.out.println("Hvem har laant ut DVD´en?");
    utLaaner = in.nextLine();
    if (!findPerson(innLaanTager)) {
      System.out.println("Det er ingen som heter " + innLaanTager);
    } else {
      System.out.println("Hva heter DVD´en?");
      tittel = in.nextLine();
      if (!personer.get(utLaaner).findUtLaantDvd(tittel)) {
        System.out.println(utLaaner + " har itte " + tittel);
      } else {
        personer.get(innLaanTager).returnLaantInnDvd(tittel);
        personer.get(utLaaner).returUtLaantDvd(tittel, personer.get(innLaanTager));
      }
    }
  }

  public void visOversiktPerson() { //funker
    System.out.println("Hvem vil du se oversikt for? (* for alle)");
    navn = in.nextLine();
    if (navn.equals("*")) {
      for(Person person : personer.values()) {
        person.detaljertOversikt();
        System.out.println();
      }
    } else if (findPerson(navn)) {
      personer.get(navn).detaljertOversikt();
    } else {
      System.out.println("Det er ingen som heter " + navn + " i DVD arkivet.");
    }
  }

  public void oversikt() { //funker
    for (Person person : personer.values()) {
      person.oversikt();
    }
  }
  public void innLeser() throws Exception {
    File filen = new File("Tekst.txt");
    Scanner fil = new Scanner(filen);
    String nyLinje = fil.nextLine();

    while (!nyLinje.equals("-")) {
      innleserNyPerson(nyLinje);
      nyLinje = fil.nextLine();
    }

    while (fil.hasNextLine()) {
      nyLinje = fil.nextLine();
      String navn = nyLinje;
      String dvdTittel;

      while (!nyLinje.equals("-") && fil.hasNextLine()) {
        dvdTittel = fil.nextLine();
        nyLinje = dvdTittel;

        if (dvdTittel.charAt(0) != ('*') && !nyLinje.equals("-")) {
          Person tempPerson1 = personer.get(navn);
          innleserKjoepDvd(dvdTittel, tempPerson1);
        } if (dvdTittel.charAt(0) == ('*')) {
          String laanTittel = dvdTittel.substring(1);
          Person tempPerson2 = personer.get(navn);
          String laanTager = fil.nextLine();
          Person tempPerson3 = personer.get(laanTager);
          innleserKjoepUtlaantDvd(laanTittel, tempPerson2, tempPerson3);
        }
      }
    }
  }
}
