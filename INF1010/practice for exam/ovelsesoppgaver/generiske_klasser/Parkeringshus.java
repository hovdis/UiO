class Parkeringshus{
  Parkeringsplass[] p1 = new Parkeringsplass[10];
  Parkeringsplass[] p2 = new Parkeringsplass[10];

  Parkeringshus(){
    for(int i = 0; i< p1.length; i++){
      p1[i] = new Parkeringsplass();
    }
    for(int i = 0; i< p1.length; i++){
      p2[i] = new Parkeringsplass();
    }
  }

  public boolean ledigPlass(int etasje, int plass){
    if(plass<11 && plass > 0){
      if(etasje == 1){
        return !p1[plass-1].opptatt();
      } else if(etasje == 2){
        return !p2[plass-1].opptatt();
      }
    }return false;
  }

  public void parkerT(Object mm, int e, int p){
    if(ledigPlass(e, p)){
      if(e == 1){
        p1[p-1].parker(mm);
      }else{
        p2[p-1].parker(mm);
      }
    }else{
      System.out.println("Dette gikk ikke så bra");
    }
  }
}
