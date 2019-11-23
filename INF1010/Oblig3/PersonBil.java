/**
*Klassen PersonBil som utvider paa Fossilbil, som igjen utvider paa Bil.
*@author Rune Hovde
*/
class PersonBil extends FossilBil {
/**
* Variabel som sier hvor mange passasjerer som kan sitte i bilen
*/
  int passasjerer;
  public PersonBil (String regNR, double utSlipp, int passasjerer) {
    super(regNR, utSlipp);
    this.passasjerer = passasjerer;
  }
/**
* Metode for aa sette inn antall passasjerer bilen kan holde
*@param int pass
*/
  public void settPass (int pass) {
  	passasjerer = pass;
   }
  //Metode som returnerer int-variabelen passasjerer
  public int returnPassasjerer () {
   	return passasjerer;
   }
   //toString metode som returnerer en string.format som inneholder informasjon om personbilen.
   //Bruker her ogsaa super-klasse-metoder.
  public String toString() {
    return String.format("\n RegNr: %s\n Utslipp: %.2f\n Passasjerer: %d\n", super.returnRegNR(), super.returnUtslipp(), passasjerer);
  }
}
