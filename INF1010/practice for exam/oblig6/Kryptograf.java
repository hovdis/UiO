  /**
  *Disse får meldinger, som skal dekrypteres.

  *KONSUMENTEN henter meldingen fra MONITOR1
  *Leverer fra seg den dekrypterte meldingen til MONITOR2
  *Operasjonsleder skal hente de dekrypterte meldingene.
  * Her er kryptografene PRODUSENTENE
  */

public class Kryptograf implements Runnable{

    private Monitor monitorKrypterte;
    private Monitor monitorDekrypterte;

    Kryptograf(Monitor monitorKrypterte, Monitor monitorDekrypterte){
        this.monitorKrypterte = monitorKrypterte;
        this.monitorDekrypterte = monitorDekrypterte;
    }

    public void run(){
        try{
            Melding melding = monitorKrypterte.hentMelding();
            while (melding != null){
                melding.dekrypter();
                monitorDekrypterte.settInnMelding(melding);
                melding = monitorKrypterte.hentMelding();
            }
        }catch(InterruptedException ie){
            System.out.println("<---------------Kryptograf  INTERRUPTED---------->");
        }finally{
            Oblig6.kryptografTraadSluttet();
        }
    }
}
