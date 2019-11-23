class Person{
  private String navn;
  private String kjop;
  private Person neste;

  Person(String navn, String kjop){
    this.navn = navn;
    this.kjop = kjop;
  }

  public String getNavn(){
    return navn;
  }

  public String getKjop(){
    return kjop;
  }

  @Override
  public String toString(){
    System.out.println("Navn: " + navn);
    System.out.println("Gjenstand: " + kjop);
    if(neste!=null){
      System.out.println("Neste i køen: " + neste);
    }else{
      System.out.println("Er siste i koen");
    }
    return navn;
  }

  public void nyPersonBak(Person ny){
    if(neste == null){
      neste = ny;
    } else{
      neste.nyPersonBak(ny);
    }
  }

  public Person getNeste(){
    return neste;
  }
}
