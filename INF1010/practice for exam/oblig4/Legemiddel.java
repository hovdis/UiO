class Legemiddel{
  int id;
  String navn;
  double pris;
  double virkestoff;

  Legemiddel(String navn, double pris, double virkestoff){
    this.navn = navn;
    this.pris = pris;
    this.virkestoff = virkestoff;
  }
  public String toString(){
    return navn +". virkestoff: "+virkestoff;
  }
  public int hentId(){
    return id;
  }
  public String hentNavn() {
    return navn;
  }
  public double hentPris() {
    return pris;
  }
  public double hentVirkestoff() {
    return virkestoff;
  }
}
