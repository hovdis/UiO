/**
* Generisk interface som integrerer de metodene jeg trenger.
*@author runehovd
*/
public interface GenHylle<T> {
  /**
  *@return stoerrelse paa hylla
  */
  public int getStr();
  /**
  *setter inn bok i arrayen, paa den plassen brukeren velger
  *hvis plassen er ledig og den er innenfor hylla.
  *Hvis ikke, skriver den ut at det ikke gaar.
  *@param T bok
  *@param int plass
  *@return true/false
  */
  public boolean settInn(T bok, int plass);
  /**
  *sjekker om plassen inneholder en bok, og om den er innenfor hylle-arrayen
  *@param int plass
  *@return true/false
  */
  public boolean ledig(int plass);
  /**
  *Om plassen inneholder en bok (og er innenfor array-rekkeviddet) tar den ut boka
  *@param int plass
  *@return true/false
  */
  public boolean taUt(int plass);
}
