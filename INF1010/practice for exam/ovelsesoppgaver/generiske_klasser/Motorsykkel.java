class Motorsykkel{
  private String regNr;
  private int ccm;

  Motorsykkel(String regNr, int ccm){
    this.regNr = regNr;
    this.ccm = ccm;
  }

  public String getRegNr(){
    return regNr;
  }

  public int getCcm(){
    return ccm;
  }
}
