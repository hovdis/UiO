class Legeliste extends OrdnetLenkeliste<Lege> {





  /**
  * Soeker gjennom listen etter en lege med samme navn som `navn`
  * og returnerer legen (uten aa fjerne den fra listen).
  * Hvis ingen slik lege finnes, returneres `null`.
  * @param navn navnet til legen
  * @return legen
  */
  public Lege finnLege(String navn) {
    for(Lege tmp : this){
      if(tmp.hentNavn().equals(navn)){
        return tmp;
      }
    }
    return null;
  }
  /**
  * Returnerer et String[] med navnene til alle legene i listen
  * i samme rekkefoelge som de staar i listen.
  * @return array med navn til alle legene
  */
  public String[] stringArrayMedNavn() {
    String[] alleNavn = new String[storrelse()];

    int i = 0;
    for(Lege tmp : this){
      alleNavn[i] = tmp.hentNavn();
      i++;
    }
    return alleNavn;
  }
}
