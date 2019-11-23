class Butikk{
  Person forste = null;
  public void entre(Person p){
    if(forste != null){
      System.out.println("Person " + p.getNavn() + " kommer inn i butikken");
      forste.nyPersonBak(p);
    }else{
      System.out.println("Person " + p.getNavn() + " er den eneste i butikken naa");
      forste = p;
    }
  }

  public void kassa(){
    if(forste != null){
      System.out.println(forste.getNavn() + " kjoper " + forste.getKjop());
      System.out.println("Hadet!");
      forste = forste.getNeste();
    }else{
      System.out.println("Det er ingen flere aa betjene");
    }
  }
}
