class FossilBil extends Bil{
  double co2;

  FossilBil(String kjennemerke, double co2){
    super(kjennemerke);
    this.co2 = co2;
  }

  double getCO2(){
    return co2;
  }

  void skrivInfo(){
    super.skrivInfo();
    System.out.println("Utslipp: " + co2 + " g/km");
  }
}
