/**
* Den generelle klassen bil. Denne klassen er den overste klassen, altsaa overste superklassen
*@author Rune Hovde
*/
public class Bil {
/**
* Variabel med kjenningsmerket til bilen
*/
  String regNR;

  public Bil (String regNR) {
    this.regNR = regNR;
  }
/**
* Metode som setter inn registreringsnummeret
*@param int regNR
*/
  public void settRegNr(String regNR) {
  	regNR = regNR;
  }
//metode som returnerer stringen regNR
  public String returnRegNR () {
  	return regNR;
  }
}
