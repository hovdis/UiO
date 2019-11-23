/**
*interface som implementerer to metoder, en for aa laane bok, og en
*for aa returnere.
*@author Rune Hovde
*/
public interface TilUtlaan {
  /**
  *laaner ut boka til laaneren om boka finnes og ingen andre laaner den
  *@param laaner boka blir laant ut
  */
  public void laanUt(String laaner);
  /**
  *metode for aa returnere bok om det finnes noen som laaner den
  *(om om det er en bok som heter det).
  *@return om boka blir returnert: true
  */
  public boolean returnerbok();
}
