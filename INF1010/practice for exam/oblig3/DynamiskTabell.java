class DynamiskTabell<T> extends StatiskTabell<T>{
  DynamiskTabell(){
    super(100);
  }
  DynamiskTabell(int kapasitet){
    super(kapasitet);
  }

  @Override
  public void settInn(T element){
    if(tabell.length == storrelse()){
      T[] nyTabell = (T[]) new Object[tabell.length * 2];
      for(int i = 0; i< tabell.length;i++){
        nyTabell[i] = tabell[i];
      }
      tabell = nyTabell;
    }

    tabell[storrelse()] = element;
    antallElementer++;
  }
}
