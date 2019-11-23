class LegemiddelA extends Legemiddel{
  int narkotiskStyrke;

  LegemiddelA(String navn, double pris, double virkestoff, int narkotiskStyrke){
    super(navn, pris, virkestoff);
    this.narkotiskStyrke = narkotiskStyrke;
  }

  public int hentNarkotiskStyrke(){
    return narkotiskStyrke;
  }
}
