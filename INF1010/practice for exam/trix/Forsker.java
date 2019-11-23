class Forsker extends Ansatt{
  String fagomraade;
  Forsker(String navn, int alder, double timesLonn, String fagomraade){
    super(navn, alder, timesLonn);
    this.fagomraade = fagomraade;
  }
}
