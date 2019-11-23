/**
 * Created by Rune on 01.03.2016.
 */
public class EldsteForstReseptListe extends EnkelReseptListe {

    public boolean settInn(Resepter r) {
        if (forste == null) {
            forste = new Node(r);
            siste = forste;
        } else if (forste == siste) {
            siste = new Node(r);
            forste.neste = siste;
        } else {
            Node ny = new Node(r);
            siste.neste = ny;
            siste = ny;
        }
        return true;
    }

}
