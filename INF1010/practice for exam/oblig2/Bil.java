class Bil{
  String kjennemerke;

  Bil(String kjennemerke){
    this.kjennemerke = kjennemerke;
  }
  String getKjennemerke(){
    return kjennemerke;
  }

  void skrivInfo(){
    System.out.println("kjennemerke: " + kjennemerke);
  }
}
