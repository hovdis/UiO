class Sirkel{
  private double radius;

  Sirkel(double radius){
    this.radius = radius;
  }

  public double areal(){
    return 3.14*(radius*radius);
  }

  public double omkrets(){
    return 3.14*radius;
  }
}
