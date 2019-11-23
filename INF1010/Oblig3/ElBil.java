/**
* Klassen ElBil som utvider paa klassen Bil.
* Eneste forskjell her er hvor mange kW den har paa batteriet, og metoden for aa sette inn det
* @author Rune Hovde
*/
class ElBil extends Bil {
/**
*Variabelen batteri for aa ta vare paa stoerrelsen av batteriet
*/
  int batteri;

  public ElBil (String regNR, int batteri) {
    super(regNR);
    this.batteri = batteri;
  }
/**
*Metode for aa sette inn batterikapasiteten i kW
*@param double bat
*/
  public void settBatKap (int bat) {
  	batteri = bat;
  }
}
