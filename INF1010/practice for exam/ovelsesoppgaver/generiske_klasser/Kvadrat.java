class Kvadrat{
  private double sidelengde;

  Kvadrat(double sidelengde){
    this.sidelengde = sidelengde;
  }

  public double omkrets(){
    return 4*sidelengde;
  }

  public double areal(){
    return sidelengde*sidelengde;
  }
}
