/**
* Klassen LasteBil som utvider paa FossilBil som igjen utvider paa Bil.
* FossilBil blir da den naermeste superklassen, mens Bil blir den oeverste superklassen
*@author Rune Hovde
*/
class LasteBil extends FossilBil {
/**
* variabel som sier hvor mye nyttevekt den kan laste.
*/
  double nytteVekt;
  public LasteBil (String regNR, double utSlipp, double nytteVekt) {
    super(regNR, utSlipp);
    this.nytteVekt = nytteVekt;
  }
/**
* Metode som setter inn hvor mye nyttevekt den kan laste. Faar inn fra parameteret
*@param double vekta
*/
  public void settNytteVekt (double vekta) {
  	nytteVekt = vekta;
  }
}
