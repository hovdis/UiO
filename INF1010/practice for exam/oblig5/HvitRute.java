class HvitRute extends Rute{
  char tegn;

  HvitRute(int x, int y, Labyrint labyrint){
    super(x, y, labyrint);
    this.tegn = ' '; //aapen
  }

  char tilTegn(){
    return tegn;
  }
}
