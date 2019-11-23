class Person{
    private String navn;
    Hus privatHus = null;
    Person(String navn){
        this.navn = navn;
    }
    public void skrivNavn(){
        System.out.println("Navn: " + navn);
    }
    public boolean leggTilHus(Hus huset){
        if (privatHus != null){
            System.out.println(navn + " har allerede et hus!");
            return false;
        } else{
            privatHus = huset;
            huset.setEier(this);
            return true;
        }
    }
    Hus getHus(){
        if(privatHus == null){
            return null;
        }else{
            return privatHus;
        }
    }
}
