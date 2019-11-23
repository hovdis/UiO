class SvartRute extends Rute{
  char tegn;
  SvartRute(int x, int y, Labyrint labyrint){
    super(x, y, labyrint);
    this.tegn = '-';//lukket
  }

  char tilTegn(){
    return tegn;
  }
}
