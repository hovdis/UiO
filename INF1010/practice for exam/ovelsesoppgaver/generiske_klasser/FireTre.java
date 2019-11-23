class FireTre{
  public static void main(String[] args) {
    Bil bil1 = new Bil("aaa", 1);
    Bil bil2 = new Bil("bbb", 1);
    Bil bil3 = new Bil("ccc", 1);

    Motorsykkel mc1 = new Motorsykkel("a", 1000);
    Motorsykkel mc2 = new Motorsykkel("b", 1000);
    Motorsykkel mc3 = new Motorsykkel("c", 1000);

    Parkeringshus pHus = new Parkeringshus();
    pHus.parkerT(bil1, 1, 10);
    pHus.parkerT(bil2, 2, 10);
    pHus.parkerT(mc1, 1, 1);
    pHus.parkerT(mc2, 1, 3);
    pHus.parkerT(mc3, 1, 1);
    pHus.parkerT(bil3, 3, 2);
  }
}
