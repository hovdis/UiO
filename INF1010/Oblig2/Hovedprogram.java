public class Hovedprogram {
  public static void main(String[] args) {
    Hylle hylla = new Hylle (100);

    Bok bok1 = new Bok("Harry potter", "J.K.Rowling");
    Bok bok2 = new Bok("The hitchhikers guide to the galaxy", "Douglas Adams");
    Bok bok3 = new Bok("The hobbit", "J.R.R. Tolkien");

    /*hylla.settInn(bok1, 1);
    hylla.settInn(bok2, 2);
    hylla.settInn(bok3, 3);

    hylla.taUt(1);*/

    test(true, hylla.settInn(bok1, 1));
    test(true, hylla.taUt(1));

    test(true, hylla.settInn(bok1, 1));
    test(true, hylla.settInn(bok2, 1));
    test(true, hylla.ledig(2));
    test(false, hylla.ledig(1));

  }

  public static void test(boolean forventa, boolean kallet) {
    if (forventa == kallet) {
      System.out.println("Detti gikk bra!\n");
    } else {
      System.out.println("dette gikk visst itte saa bra.\n");
    }
  }
}
