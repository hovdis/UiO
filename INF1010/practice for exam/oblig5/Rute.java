abstract class Rute{
  int x, y;
  Labyrint labyrint;
  Rute nord, sor, ost, vest;

  Rute(int x, int y, Labyrint labyrint){
    this.x = x;
    this.y = y;
    this.labyrint = labyrint;
  }


  abstract char tilTegn();
}
