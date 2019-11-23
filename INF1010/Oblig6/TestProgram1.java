/**
 * Created by Rune on 08.03.2016.
 */
public class TestProgram1 {
    public static void main (String[] args) {
        testLege();
        testTabell();
        testResept();
        testYngste();
    }
        public static void testTabell() {
        Tabell<Pasient> pasientTabell = new Tabell<Pasient>(100);
        Pasient a = new Pasient("Rune", 1010, "Hei", 1000);
        Pasient b = new Pasient("Andreas", 10009999, "nei", 1002);
        Pasient c = new Pasient("Simon", 120007, "kei", 2134);
        Pasient d = new Pasient("Lisa", 12999, "lala", 1344);
        Pasient e = new Pasient("Malin", 392190, "heik", 1287);
        Pasient f = new Pasient("Thor", 32945, "lenk", 1342);
        Pasient g = new Pasient("Geir", 234987, "kaka", 2378);

        System.out.println(pasientTabell.settInn(a, 1));
        System.out.println(pasientTabell.settInn(b, 2));
        System.out.println(pasientTabell.settInn(c, 3));
        System.out.println(pasientTabell.settInn(d, 4));
        System.out.println(pasientTabell.settInn(e, 5));
        System.out.println(pasientTabell.settInn(f, 6));
        System.out.println(pasientTabell.settInn(g, 0));

        for (Pasient p: pasientTabell) {
            if (p == null) {
                System.out.println("nei");
            }
            System.out.println(p.hentNavn());
        }
        Tabell<LegeMiddel> lm = new Tabell<LegeMiddel>(100);
        LegeMiddel as = new LegeMiddel("As lege", 100, 10);
        LegeMiddel bs = new LegeMiddel("Bs lege", 100, 10);
        LegeMiddel cs = new LegeMiddel("Cs lege", 100, 10);

        lm.settInn(as, 1);
        lm.settInn(bs, 2);
        lm.settInn(cs, 3);


        for (LegeMiddel legemiddel : lm) {
            System.out.println(legemiddel.toString());
        }
    }

        //LegeMiddel, Pasient -> Tabell

    public static void testLege() {
        SortertEnkelListe<Lege> legeTabell = new SortertEnkelListe<Lege>();
        Lege aa = new Lege("aa");
        Lege bb = new Lege("bb");
        Lege cc = new Lege("cc");
        Lege dd = new Lege("dd");

        legeTabell.settInn(aa);
        legeTabell.settInn(bb);
        legeTabell.settInn(cc);
        legeTabell.settInn(dd);

        for (Lege l: legeTabell) {
            System.out.println(l.hentNavn());
        }
    }

        //Lege - SortertEnkelListe

        // Resept - EldsteForstReseptListe

    public static void testResept() {
        EldsteForstReseptListe listen = new EldsteForstReseptListe();
        LegeMiddel as = new LegeMiddel("As lege", 100, 10);
        LegeMiddel bs = new LegeMiddel("Bs lege", 110, 10);
        LegeMiddel cs = new LegeMiddel("Cs lege", 120, 10);
        LegeMiddel ds = new LegeMiddel("Ds lege", 130, 10);
        Lege aa = new Lege("aa");
        Lege bb = new Lege("bb");
        Lege cc = new Lege("cc");
        Lege dd = new Lege("dd");

        Resepter aaa = new Resepter(as, 10, 1000, aa, as.getPris());
        Resepter bbb = new Resepter(bs, 10, 1000, bb, bs.getPris());
        Resepter ccc = new Resepter(cs, 10, 1000, cc, cs.getPris());
        Resepter ddd = new Resepter(ds, 10, 1000, dd, ds.getPris());

        listen.settInn(aaa);
        listen.settInn(bbb);
        listen.settInn(ccc);
        listen.settInn(ddd);

        for (Resepter k : listen) {
            System.out.println(k.getPris());
        }
    }
    public static void testYngste() {

        YngsteForstReseptListe yngste = new YngsteForstReseptListe();

        LegeMiddel as = new LegeMiddel("As lege", 100, 10);
        LegeMiddel bs = new LegeMiddel("Bs lege", 110, 10);
        LegeMiddel cs = new LegeMiddel("Cs lege", 120, 10);
        LegeMiddel ds = new LegeMiddel("Ds lege", 130, 10);

        Pasient a = new Pasient("Rune", 1010, "Hei", 1000);
        Pasient b = new Pasient("Andreas", 10009999, "nei", 1002);
        Pasient c = new Pasient("Simon", 120007, "kei", 2134);
        Pasient d = new Pasient("Lisa", 12999, "lala", 1344);

        Lege aa = new Lege("aa");
        Lege bb = new Lege("bb");
        Lege cc = new Lege("cc");
        Lege dd = new Lege("dd");

        Resepter aaa = new Resepter(as, 10, 1000, aa, as.getPris());
        Resepter bbb = new Resepter(bs, 10, 1002, bb, bs.getPris());
        Resepter ccc = new Resepter(cs, 10, 2134, cc, cs.getPris());
        Resepter ddd = new Resepter(ds, 10, 1344, dd, ds.getPris());

        yngste.settInn(aaa);
        yngste.settInn(bbb);
        yngste.settInn(ccc);
        yngste.settInn(ddd);

        for (Resepter t : yngste){
            System.out.println(t.getNr());
        }
    }
        // Resept - YngsteForstReseptListe


}
