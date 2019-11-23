class Parkeringsplass<T>{
  private T kjoreToy;
  private boolean opptatt = false;

  public void parker(T kjoreToy){
    this.kjoreToy = kjoreToy;
    opptatt = true;
    System.out.println("kjøretøy parkert");
  }

  public T kjorUt(){
    opptatt = false;
    return kjoreToy;
  }

  public boolean opptatt(){
    return opptatt;
  }
}
