abstract class Resept{
  int id;
  Legemiddel legemiddel;
  Lege legen;
  int pasientId;
  int antallReit;

  Resept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    this.legemiddel = legemiddel;
    legen = utskrivendeLege;
    this.pasientId = pasientId;
    antallReit = reit;
  }
  @Override
  public String toString(){
    return "Legemiddel: "+legemiddel.toString()+". pasientId: "+pasientId+". Antall reit: "+antallReit;
  }

  public int hentId() {
    return id;
  }
  public Legemiddel hentLegemiddel() {
    return legemiddel;
  }
  public Lege hentLege() {
    return legen;
  }
  public int hentPasientId() {
      return pasientId;
  }
  public int hentReit() {
    return antallReit;
  }
  /**
  * Bruker resepten én gang. Returner false om resepten er
  * oppbrukt, ellers returnerer den true.
  * @return om resepten kunne brukes
  */
  public boolean bruk() {
    if(antallReit > 0){
      antallReit--;
      return true;
    } return false;
  }


  /**
  * Returnerer reseptens farge. Enten "blaa" eller "hvit".
  * @return reseptens farge
  */
  abstract public String farge();
  /**
  * Returnerer prisen pasienten maa betale.
  * @return prisen pasienten maa betale
  */
  abstract public double prisAaBetale();
}
