import java.util.*;

class Person {
  private String navn;

  HashMap<String, Dvd> eidDvd = new HashMap<>();
  HashMap<String, Dvd> laantUtDvd = new HashMap<>();
  HashMap<String, Dvd> laantInnDvd = new HashMap<>();

  public Person(String navn) {      //Konstruktoeren til person
    this.navn = navn;
  }

  public String toString () { //funker
    return navn;
  }

  public void kjoepDvd (String tittel, Person eier) { //funker
    if (findDvd(tittel)) {
      System.out.println(navn + " har allerede " + tittel);
      return;
    } else {
      Dvd dvd = new Dvd (tittel, eier);
      eidDvd.put(tittel, dvd);
    }
  }

  public Dvd laanUtDvd (String tittel, Person laanTager) { //funker
    Dvd nyLaanBort = null;
    if (findDvd(tittel)) {
      if (!eidDvd.containsKey(tittel)) {
        System.out.println("Filmen kan itte laanes.");
      } else {
        nyLaanBort = eidDvd.get(tittel);
        System.out.println(tittel);
        nyLaanBort.setLaaner(laanTager);
        System.out.println(laanTager.toString());
        laantUtDvd.put(tittel, nyLaanBort);
        eidDvd.remove(tittel);
        System.out.println(laanTager.toString() + " laante " + tittel + " fra " + navn);
      }                       //Usikker paa om je finn denni toStringen
    } return nyLaanBort;
  }

  public boolean findDvd (String tittel) { //funker
    if (eidDvd.get(tittel) == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean findUtLaantDvd (String tittel) { //funker
    if (laantUtDvd.get(tittel) == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean findInnLaantDvd (String tittel) { //Kan funke om laanInnDvd funker
    if (laantInnDvd.get(tittel) == null) {
      return false;
    } else {
      return true;
    }
  }

  public void laanInnDvd(Dvd laaneDvd) { //tror den funker
    laantInnDvd.put(laaneDvd.getTittel(), laaneDvd);
  }

  public void returnLaantInnDvd (String tittel) { //??
    laantInnDvd.remove(tittel);
  }

  public void returUtLaantDvd (String tittel, Person laanTager) { //funker
    Dvd plassholderDvd = null;
    plassholderDvd = laantUtDvd.get(tittel);
    eidDvd.put(tittel, plassholderDvd);
    laantUtDvd.remove(tittel);
    System.out.println(laanTager.toString() + " leverte tilbake " + tittel + " til " + navn);
  }                       //Finner den denni?

  public void detaljertOversikt() { //funker
    System.out.println(navn + " sine DVD´er: ");
    for (Dvd dvd : eidDvd.values()) {
        System.out.println(dvd.getTittel());
    } for (Dvd dvd : laantUtDvd.values()) {
        System.out.println(dvd.getTittel() + " (laanes av " + dvd.getLaantager() + ")");
    }
  }

  public void oversikt() { //funker
    System.out.println(navn + " eier " + eidDvd.size() + " DVD´er");
    System.out.println("har laant ut: " + laantUtDvd.size() + " DVD´er");
    System.out.println("og har laant: " + laantInnDvd.size() + " DVD´er");
  }
}
