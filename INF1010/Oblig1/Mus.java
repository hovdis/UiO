public class Mus {
  private String name;
  private boolean living;

  public Mus (String name) {
    this.name = name;
    living = true;
  }

  public String getName () {
    return name;
  }

  public void killMus () {
    living = false;
    System.out.println(name + " went from being alive, to being dead");
  }

  public boolean checkAlive () {
    if (living == true) {
      return true;
    }return false;
  }
}
