public class Hylle<T> implements GenHylle<T>{

private T[] liste;
private int stoerrelse;

  public Hylle(int str) {
    this.stoerrelse = str;
    this.liste = (T[]) new Object[str];
  }

  public int getStr() {
    return stoerrelse;
  }

  public boolean settInn(T bok, int plass) {
    if (ledig(plass) && plass < stoerrelse-1) {
      liste[plass] = bok;
      System.out.println("Boka blei levert");
      return true;
    } else {
      System.out.println("Plassen er opptatt, eller uttafor rekkeviddet.");
      return false;
    }
  }
  public boolean ledig(int plass) {
    if (liste[plass] == null && plass < stoerrelse) {
      return true;
    } return false;
  }
  public boolean taUt(int plass) {
    if (!ledig(plass)) {
      liste[plass] = null;
      System.out.println("Boka ble tatt ut");
      return true;
    } else {
      System.out.println("Finnes ingen boeker der, eller uttenfor rekkeviddet");
      return false;
    }
  }
}
