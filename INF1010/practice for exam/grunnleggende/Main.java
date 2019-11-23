class Main{
    public static void main(String[] args){
        Person rune = new Person("Rune");
        Person andreas = new Person("Andreas");
        rune.skrivNavn();
        andreas.skrivNavn();
        Hus runesHus = new Hus();
        Hus andreassHus = new Hus();
        rune.leggTilHus(runesHus);
        andreas.leggTilHus(andreassHus);
        rune.getHus().skrivInfoOmEier();
        andreas.getHus().skrivInfoOmEier();
    }
}
