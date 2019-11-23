class LegemiddelB extends Legemiddel{
  int styrke;

  LegemiddelB(String navn, double pris, double virkestoff, int styrke){
    super(navn, pris, virkestoff);
    this.styrke = styrke;
  }

  public int hentVanedannendeStyrke(){
    return styrke;
  }
}
