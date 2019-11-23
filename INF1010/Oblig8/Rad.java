/**
 * Created by Rune on 02.04.2016.
 */
public class Rad {
    //Objekter
    private Rute[] ruteArray;

    Rad(int brettStoerrelse) {
        ruteArray = new Rute[brettStoerrelse];
    }

    public void settRute(Rute a) {
        for(int i=0; i<ruteArray.length; i++){
            if(ruteArray[i]==null){
                ruteArray[i]=a;
                return;
            }
        }
    }

    public Rute[] getRuteArray() {
        return ruteArray;
    }
}
