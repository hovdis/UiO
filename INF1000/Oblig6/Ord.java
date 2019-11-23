class Ord {
  String tekst;
  int antall;
  Ord (String tekst) {           //konstruktoeren til classen Ord.
    this.tekst = tekst;
    antall++;                    //Foerste gangen ordet forekommer. Legger til en.

  } public String toString() {   //Returnerer navnet til ord-objektet.
    return tekst;

  } public int hentAntall() {    //Henter antallet forekomster som oekAntall
    return antall;               //oeker etter hvert som scanneren gaar over teksten.

  } public void oekAntall() {
    antall++;                    //Oeker antall forekomster for det ordet med 1.

  }
}
