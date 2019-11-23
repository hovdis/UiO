//Oppgave 6.1. Detti er testprogrammet til ord-klassen.
class TestProgram  {
  public static void main(String[] args) {
      //Legger til et ord. "Dette"
    Ord testOrd1 = new Ord ("Dette");
    testOrd1.oekAntall();         //Oeker antallet
    System.out.print("Antall ganger ´"+testOrd1.toString());    //Kaller paa toString
    System.out.println("´ forekommer: "+ testOrd1.hentAntall());//Kaller paa hentAntall

      //Legger til et ord. "skjer"
    Ord testOrd2 = new Ord ("skjer");
    testOrd2.oekAntall();         //Oeker antallet
    testOrd2.oekAntall();         //Oeker antallet
    System.out.print("Antall ganger ´"+testOrd2.toString());    //Kaller paa toString
    System.out.println("´ forekommer: "+ testOrd2.hentAntall());//Kaller paa hentAntall

      //Legger til et ord "her"
    Ord testOrd3 = new Ord ("her");
    testOrd3.oekAntall();         //Oeker antallet
    testOrd3.oekAntall();         //Oeker antallet
    testOrd3.oekAntall();         //Oeker antallet
    System.out.print("Antall ganger ´"+testOrd3.toString());    //Kaller paa toString
    System.out.println("´ forekommer: "+ testOrd3.hentAntall());//Kaller paa hentAntall
  }
}
