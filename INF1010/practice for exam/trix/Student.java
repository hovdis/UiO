class Student extends Person{
  String laerersted;
  String kurs;
  Student(String navn, int alder, String kurs, String laerersted){
    super(navn, alder);
    this.laerersted = laerersted;
    this.kurs = kurs;
  }
}
