class Dvd {
  private String tittel;
  private Person eier;
  private Person laanTager;
  private boolean utlaant = false;

  //Konstruktoer om eier itte laaner ut dvd med en gong
  public Dvd (String tittel, Person eier) {
    this.tittel = tittel;
    this.eier = eier;
  }
  //Konstruktoer for innleser om dvd´en laanes ut med en gong
  public Dvd (String tittel, Person eier, Person laanTager) {
    this.tittel = tittel;
    this.eier = eier;
    this.laanTager = laanTager;
    utlaant = true;
  }

  public boolean getUtLaant() {
    return utlaant;
  }

  public String laantTil() {        //Sier hvem filmen er laant ut til, om den er det. Ellers returnerer tittelen (brukes til vil oversikt)
    if (getUtLaant()) {
      return tittel + " (ulaant til " + getLaantager() + ").";
    } else {
      return tittel;
    }
  }

  public void setLaaner (Person laanTager) { //Setter laantageren
    laanTager = laanTager;
    utlaant = true;
  }

  public void fjernLaanTager () {
    laanTager = null;
    utlaant = false;
  }

  public String getTittel() {
    return tittel;
  }

  public Person getEier() {
    return eier;
  }

  public Person getLaantager() {
    return laanTager;
  }

}
