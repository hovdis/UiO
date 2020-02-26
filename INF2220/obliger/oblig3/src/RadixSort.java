import java.util.*;

public class RadixSort {
    final static int NUM_BIT =9; // eller: 6-13 er kanskje best.. finn selv ut hvilken verdi som er raskest
    final static int MIN_NUM = 31; // mellom 16 og 60, kvikksort bruker 47

    double vRadMulti(int [] a){
        long tt = System.nanoTime(); //starter tiden
        int[]b = new int[a.length];
        int max = a[0];

        for(int i = 1; i<a.length; i++){ //finner største tall
            if(a[i] > max){
                max = a[i];
            }
        }

        int numbit = 1; //antall bit som brukes. eks: 1011 vil bruke 4 bit fordi det mest signifikante bittet er 2^3
        int numMax = max;
        while(true){ //finner dette ved å gå ett og ett bit til venstre i det største tallet og sjekke om det er 0. Hvis det er det er numBit antall tall :)
            if((numMax >> 1) == 0){
                break;
            }
            numMax = (numMax >> 1);
            numbit++;
        } //finner numbit

        venstreRadix(a, b, 0, a.length-1, numbit, NUM_BIT); //første kall. hva er dette?

        double tid = (System.nanoTime()) -tt/1000000.0; //slutter tiden og gjør det om til millisekunder
        testSort(a); //sjekker om arrayet faktisk er sortert
        return tid; //returnerer tiden
    }

    void venstreRadix(int[] a, int[] b, int left, int right, int leftSortBit, int maskLen){
        int mask = (1<<maskLen)-1; //setter masken til å være maskLen antall 1'ere. eks: masklen 4 vil være 1111, fordi (1<<4)-1 = (10000)-1 = 1111
        int[] count = new int[mask+1];
        Arrays.fill(count, 0);
        int[] indexes = new int[count.length];
        // -------- deklarasjoner??



        /*
        * Haha. Je har helt glømt å ta med left og right je... ups. Det forandrer litt av koden..
        * */



        for(int i = 0 ; i<=a.length ; i++) {
            count[(a[i] >> (leftSortBit-maskLen) & mask)]++; //Les under for en detaljert beskrivelse av hva denne setningen gjør :)
            /*int tmpNum = (a[i] >> (leftSortBit - maskLen)); //velger de riktige bitsene.... Her tror jeg det er noe som må gjøres... tror ikke jeg skal bruke masklen her.
            int index = (tmpNum & mask); //hvor jeg skal telle opp
            count[index]++; //øker counten på denne plassen*/
        }

        //Nå har jeg prøvd ganske så mye, men klarer itte å få til punkt e.... Jeg veit hva je skar, men itte hvordan... Hvis counten ser sånn her ut: [3, 1, 0, 2, 0, 0] skar indexes (eller oppdatere count da) se sånn her ut: [0, 3, NULL(elns), 4, NULL, NULL] eller noe. Ikke helt sikker på om det skar stå NULL eller 0, men de tilfellene kommer aldri til å skje.
        // Håper du skjønner hva je prøver å gjøre

        /*
        int countBackup[] = count;
        boolean doing = false;
        int indexCount = 0;
        for(int i = 0; i<=count.length;i++){
            if(count[i] != 0){
                if(!doing){
                    doing = true;
                    indexes[i] = count[i];
                }else{
                    indexes[i]--;
                    i--;
                }
            }else{
                doing = false;
            }
        }*/



        //Tror oppgave f ikke er så vanskelig egentlig. Når du har indexes, så veit du hvor ting skal og sånn. Er nesten bare å sette det inn, bare på riktig plass (som du får av indexes og count)

        //G er litt kjip, kanskje, men tror du skar sende med b-arrayet (men det blir a-arrayet i det rekursivet kallet) hvor left og right er start og slutt-plasseringen i b hvor count er over 1. Hvis du itte skjønte det er det itte rart. Lettest å tegne(kan prøve i mårra)
        // Hvis det er så så få tall så skar du heller bruke innstikkSort enn venstreradix..

        //Når det er ferdig tror je egentlig nesten hele obligen er ferdig faktisk!! :)


    }


    /** N.B. Sorterer a[] stigende – antar at: 0 ≤ a[i]) < 232 , returnerer tiden i millisek. */

    double VKRadixMulti(int [] a) {
        long tt = System.nanoTime();
        int [] b = new int [a.length];
        int max = a[0];


        for(int i = 1; i<a.length;i++){ //finner maxverdi i a
            if(a[i] > max){
                max = a[i];
            }
        }

        // b) bestem numBit = høyeste (mest venstre) bit i ‘max’ som ==1
        // c) Første kall (rot-kallet) på VenstreRadix med a[], b[] , numBit, og lengden av første siffer

        double tid = (System.nanoTime() -tt)/1000000.0;
        testSort(a);
        return tid; // returnerer tiden i ms. det tok å sortere a, som nå er sortert og testet
    }
    // Sorter a[left..right] på siffer med start i bit: leftSortBit, og med lengde: maskLen bit,
    void VenstreRadix ( int [] a, int [] b, int left, int right, int leftSortBit, int maskLen){
        int mask = (1<<maskLen) -1;
        int [] count = new int [mask+1];
        //……………. Andre deklarasjoner ……………
        // d) count[] =hvor mange det er med de ulike verdiene
        // av dette sifret I a [left..right]
        // e) Tell opp verdiene I count[] slik at count[i] sier hvor i b[] vi skal flytte første element med verdien ‘i’ vi sorterer.
        // f) Flytt tallene fra a[] til b[] sorter på dette sifferet I a[left..right] for alle de ulike verdiene for dette sifferet
        // g) Kall enten innstikkSort eller rekursivt VenstreRadix
        // på neste siffer (hvis vi ikke er ferdige) for alle verdiene vi har av nåværende siffer
        // Vurder når vi. skal kopiere tilbake b[] til a[] ??
    }// end VenstreRadix
    void testSort(int [] a){
        for (int i = 0; i< a.length-1;i++) {
            if (a[i] > a[i+1]){
                System.out.println("SorteringsFEIL på: "+
                        i +" a["+i+"]:"+a[i]+" > a["+(i+1)+"]:"+a[i+1]);
                return;
            } }
    }// end testSort

    // Tiden tas selv av VenstreRadix. Du må imidlertid omslutte kallet på Arrays.sort(a) med tidtaking,
    // Disse tidene for en bestemt verdi av n oppbevarer du i to double – arrayer som du
    // så tar innstikkSortering på for å finne medianverdien av hver av dem .
    // Midtelementet i en sortert array er medianen.







    /**
    static int getMax(int a[], int n){
        int max = a[0];
        for(int i = 1 ; i < n ; i++){
            if(a[i] > max){
                max = a[i];
            }
        }
        return max;
    }

    static void countSort(int a[], int n, int exp){
        int b[] = new int[n];
        int count[] = new int[10];
        Arrays.fill(count, 0); //Fyller 0 i hver plass

        for(int i = 0; i < n ; i++){ //Går gjennom je legger til på count der det skal være
            count[ (a[i]/exp)%10 ]++;
        }

        for(int k = 1; k < 10 ; k++){ // gjør om count så count inneholder posisjonen til tallet i b
            count[k] += count[k-1];
        }

        for(int o = n-1; o>=0 ; o--){
            b[count[ (a[o]/exp)%10] -1] = a[o];
            count[(a[o]/exp)%10]--;
        }

        //kopierer arrayen til a, så det er sortert på dette nummeret.
        for(int l = 0; l<n ;l++){
            a[l] = b[l];
        }
    }

    static void radixSort(int a[], int n){
        int m = getMax(a, n);

        for(int exp = 1; m/exp >0;exp*= 10){
            countSort(a, n, exp);
        }
    }

    static void print(int a[], int n){
        for(int i = 0; i<n; i++){
            System.out.println(a[i] + " ");
        }
    }

    public static void main(String[] args){
        int a[] = {100, 3210, 2134, 54, 3, 642, 64};
        int n = a.length;
        System.out.println("Before run:");
        print(a, n);
        radixSort(a, n);
        System.out.println("\nAfter run:");
        print(a, n);
    }*/
}


