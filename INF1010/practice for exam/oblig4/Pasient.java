class Pasient{
  String navn;
  long fodselsnummer;
  String gate;
  int postnummer;
  int id;
  Stabel<Resept> resepter = new Stabel<Resept>();
  



  Pasient(String navn, long fodselsnummer, String gateadresse, int postnummer) {
    this.navn = navn;
    this.fodselsnummer = fodselsnummer;
    gate = gateadresse;
    this.postnummer = postnummer;
  }
  public int hentId() {
    return this.id;
  }
  public String hentNavn() {
    return navn;
  }
  public long hentFodselsnummer() {
    return fodselsnummer;
  }
  public String hentGateadresse() {
    return gate;
  }
  public int hentPostnummer() {
    return postnummer;
  }
  public void leggTilResept(Resept resept) {
    resepter.settInn(resept);
  }
  public Stabel<Resept> hentReseptliste() {
    return resepter;
  }
}
