public class Rotte {
  private String name;
  private boolean alive, hurt;

  public Rotte (String name) {
    this.name = name;
    alive = true;
    hurt = false;
  }

  public String getName () {
    return name;
  }

  public void hurtRotte () {
    if (alive == true && hurt == false) {
      hurt = true;
      System.out.println(name + " went from being healthy, to being hurt.");
    } else if (alive == true && hurt == true) {
      alive = false;
      System.out.println(name + " was killed.");
    }
  }

  public boolean checkAlive () {
    if (alive == true) {
      return true;
    } return false;
  }

  public void checkRotteStatus () {
    if (alive == true && hurt == false) {
      System.out.println(name + " is alive, and not hurt");
    } else if (alive == true && hurt == true) {
      System.out.println(name + " is alive, but hurt");
    } else {
      System.out.println(name + " is dead");
    }
  }
}
