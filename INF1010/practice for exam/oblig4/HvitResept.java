class HvitResept extends Resept{
  HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public String farge(){
    return "hvit";
  }

  public double prisAaBetale(){
    return legemiddel.hentPris();
  }
}
