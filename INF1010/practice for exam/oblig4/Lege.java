class Lege implements Comparable<Lege>{
  String navn;
  Koe<Resept> resepter = new Koe<Resept>();

  public Lege(String navn){
    this.navn = navn;
  }
  public String toString(){
    return navn;
  }
  public String hentNavn() {
    return navn;
  }
  public int compareTo(Lege annenLege) {
    if(navn.compareTo(annenLege.hentNavn()) > 0){
      return 1;
    }else if(navn.compareTo(annenLege.hentNavn()) < 0){
      return -1;
    }else{
      return 0;
    }
  }
  public void leggTilResept(Resept resept) {
    resepter.settInn(resept);
  }

  public Koe<Resept> hentReseptliste() {
    return resepter;
  }

}
