class BlaaResept extends Resept{

  BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, int pasientId, int reit){
    super(legemiddel, utskrivendeLege, pasientId, reit);
  }

  public String farge(){
    return "blaa";
  }

  public double prisAaBetale(){
    return 0.25*legemiddel.hentPris();
  }
}
