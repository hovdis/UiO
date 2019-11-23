public class Oblig1 {
  public static void main(String[] args) {
    Bol<Mus> musebol = new Bol<Mus>();
    Bol<Rotte> rottebol = new Bol<Rotte>();
    Katt pelle = new Katt("Pelle");
    pelle.hunt(musebol, rottebol);
    Rotte ronny = new Rotte("Ronny");
    rottebol.insertAnimal(ronny);
    pelle.hunt(musebol, rottebol);
    Mus kaare = new Mus("Kåre");
    musebol.insertAnimal(kaare);
    Mus geir = new Mus("Geir");
    musebol.insertAnimal(geir);
    pelle.hunt(musebol, rottebol);
  }
}


/*
Katten Tom fant ingen gnagere. Jakten avsluttes.
Katten Tom gjorde et angrep på rotta Ronny.
Rotta Ronny gikk fra aa vaere levende til aa vaere skadet.
Bolet er allerede fullt.
Katten Tom gjorde et angrep på musen Jerry.
Musen Jerry gikk fra aa vaere levende til aa vaere dod
*/
