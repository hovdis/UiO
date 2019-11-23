class FemEn{
  public static void main(String[] args) {
    Person p1 = new Person("en", "en");
    Person p2 = new Person("to", "to");
    Person p3 = new Person("tre", "tre");
    Person p4 = new Person("fire", "fire");
    Person p5 = new Person("fem", "fem");
    Butikk b = new Butikk();
    b.entre(p1);
    b.entre(p2);
    b.entre(p3);
    b.entre(p4);
    b.entre(p5);

    b.kassa();
    b.kassa();
    b.kassa();
    b.kassa();
    b.kassa();


  }
}
