class Prosessor {
  int kjerner;
  double hz;

  Prosessor(int kjerner, double hz){
    this.kjerner = kjerner;
    this.hz = hz;
  }


  int getKjerner(){
    return kjerner;
  }
  double getHz(){
    return hz;
  }

  double flops(){
    return (double)(8 * hz * kjerner);
  }
}
