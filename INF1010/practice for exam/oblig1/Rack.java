public class Rack {
  int antallNoderTot;
  int antallNoderInni = 0;
  Node[] nodeListe;
  Rack(int antNoder){
    this.antallNoderTot = antNoder;
    nodeListe = new Node[antallNoderTot];
  }

  boolean ledigPlass(){
    return (antallNoderTot == antallNoderInni);
  }
  void leggTilNode(Node node){
    for(int i = 0; i< nodeListe.length ; i++){
      if(nodeListe[i] == null){
        nodeListe[i] = node;
        antallNoderInni++;
        return;
      }
    }
  }
  Node[] hentNoder(){
    return nodeListe;
  }

  double flopps(){
    double sum = 0;
    for(int i = 0; i< nodeListe.length; i++){
      sum+= nodeListe[i].leggTilFlops();
    }
    return sum;
  }

  int antallMedNokMinne(int antall){
    int tmp = 0;
    for(int i = 0; i<nodeListe.length;i++){
      if(nodeListe[i] != null){
        if(nodeListe[i].getMinne() >= antall){
          tmp++;
        }
      }
    }
    return tmp;
  }
}
