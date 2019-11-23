class ElBil extends Bil{
  double kWh;

  ElBil(String kjennemerke, double kWh){
    super(kjennemerke);
    this.kWh = kWh;
  }

  double getkWh(){
    return kWh;
  }

  void skrivInfo(){
    System.out.println("Type bil: Elbil");
    super.skrivInfo();
    System.out.println("kWh: " + kWh);
  }
}
