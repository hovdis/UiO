/**
 * Created by Rune on 17.02.2016.
 */
public class HvitResept extends Resepter {

    public HvitResept(LegeMiddel legeMiddelPeker, int antallReit, int personNR, Lege legePeker, int pris) {
        super(legeMiddelPeker, antallReit, personNR, legePeker, legeMiddelPeker.getPris());
    }
}


