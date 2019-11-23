public class Bok implements TilUtlaan {
  private String tittel;
  private String forfatter;
  private String laantager;
  public Bok (String t, String f) {
    this.tittel = t;
    this.forfatter = f;
  }
  public void getTittel() {
    System.out.println(tittel);
  }
  public void getForfatter() {
    System.out.println(forfatter);
  }
  public void getLantager() {
    if (laantager != null) {
      System.out.println(laantager);
    }
    System.out.println("Ingen som laaner boka");
  }

  public void laanUt(String laaner) {
    laantager = laaner;
  }
  public boolean returnerbok() {
    /*if (laantager != null) {
      laantager = null;
      System.out.println("Boka ble levert");
    } else {
      System.out.println("Boka har ingen laaner");
    }*/
    return true;
  }
}
