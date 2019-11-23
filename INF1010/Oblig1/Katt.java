public class Katt {
  private String name;

  public Katt (String name) {
    this.name = name;
  }

  public void hunt (Bol<Mus> museBol, Bol<Rotte> rotteBol) {

    if (museBol.checkOccupied() && rotteBol.checkOccupied()) {
      Mus mouse = museBol.fetchAnimal();
      if (mouse.checkAlive()) {
        mouse.killMus();
      } else {
        Rotte rat = rotteBol.fetchAnimal();
        if (rat.checkAlive()) {
          rat.hurtRotte();
        } else {
          System.out.println(name + " didn´t find a prey..");
        }
      }
    }
    if (museBol.checkOccupied() && !rotteBol.checkOccupied()) {
      Mus mouse = museBol.fetchAnimal();
      if (mouse.checkAlive()) {
        mouse.killMus();
      } else {
        System.out.println(name + " didn´t find a prey..");
      }
    }
    if (!museBol.checkOccupied() && rotteBol.checkOccupied()) {
      Rotte rat = rotteBol.fetchAnimal();
      if (rat.checkAlive()) {
        rat.hurtRotte();
      } else {
        System.out.println(name + " didn´t find a prey..");
      }
    }
    if (!museBol.checkOccupied() && !rotteBol.checkOccupied()) {
      System.out.println(name + " didn´t find a prey.");
    }
  }
}
