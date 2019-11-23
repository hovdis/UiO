class LasteBil extends FossilBil{
  double nyttevekt;

  LasteBil(String kjennemerke, double co2, double nyttevekt){
    super(kjennemerke, co2);
    this.nyttevekt = nyttevekt;
  }

  double getNyttevekt(){
    return nyttevekt;
  }

  void skrivInfo(){
    System.out.println("Type bil: Lastebil");
    super.skrivInfo();
    System.out.println("Nuttevekt: " + nyttevekt + "kg");
  }
}
