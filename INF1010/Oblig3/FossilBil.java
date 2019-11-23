/**
*Klasse FossilBil som utvider paa klassen Bil.
*@author Rune Hovde
*/
class FossilBil extends Bil {
/**
* Variabel for aa ta vare paa utslippet i hvor mye CO2 den slipper ut.
*/
  double utSlipp;

  public FossilBil(String regNR, double utSlipp) {
    super(regNR);
    this.utSlipp = utSlipp;
  }
/**
*Metode som setter utslippet til det som kommer inn fra parameteret
* @param double slipp
*/
  public void settUtSlipp (double slipp) {
  	utSlipp = slipp;
  }
  //Metode som returnerer doouble-variabelen utSlipp
  public double returnUtslipp () {
	return utSlipp;
  }
}
