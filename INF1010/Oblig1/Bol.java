public class Bol<T> {
  private boolean occupied;
  private T animal;

  public void Bol() {
    occupied = false;
  }

  public void insertAnimal(T animal) {
    if (occupied) {
      System.out.println("the ´bol´ is already occupied.");
    } else {
      this.animal = animal;
      occupied = true;
    }
  }

  public T fetchAnimal() {
    return animal;
  }

  public boolean checkOccupied () {
    if (occupied == false) {
      return false;
    } return true;
  }
}
