class PersonBil extends FossilBil{
  int antSeter;

  PersonBil(String kjennemerke, double co2, int antSeter){
    super(kjennemerke, co2);
    this.antSeter = antSeter;
  }

  int getAntSeter(){
    return antSeter;
  }

  void skrivInfo(){
    System.out.println("Type bil: Personbil");
    super.skrivInfo();
    System.out.println("Antall seter: " + antSeter);
  }
}
