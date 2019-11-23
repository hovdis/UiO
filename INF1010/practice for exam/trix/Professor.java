class Professor extends Forsker{
  String fagUndervisning;
  Professor(String navn, int alder, double timesLonn, String fagomraade, String fagUndervisning){
    super(navn, alder, timesLonn, fagomraade);
    this.fagUndervisning = fagUndervisning;
  }
}
