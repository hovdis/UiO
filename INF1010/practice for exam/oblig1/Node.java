class Node {
  int minne;
  boolean dualProc = false;
  Prosessor[] pros;
  Prosessor p1;
  Prosessor p2;

  Node(int minne, double hastighet, int kjerne1){
    this.minne = minne;
    p1 = new Prosessor(kjerne1, hastighet);
    pros = new Prosessor[1];
    pros[0] = p1;
  }
  Node(int minne, double hastighet, int kjerne1, int kjerne2){
    this.minne = minne;
    p1 = new Prosessor(kjerne1, hastighet);
    p2 = new Prosessor(kjerne2, hastighet);
    dualProc = true;
    pros = new Prosessor[2];
    pros[0] = p1;
    pros[1] = p2;
  }

  double leggTilFlops(){
    double sum = 0;
    sum+= p1.flops();
    if(dualProc){
      sum+= p2.flops();
    }
    return sum;
  }
  int getMinne(){
    return minne;
  }
  Prosessor[] getPros(){
    return pros;
  }
}
