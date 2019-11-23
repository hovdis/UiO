/**
 * Created by Rune on 18.02.2016.
 */
public class LenkeListe<E extends Comparable<E>> {

    private Node forste;
    private Node siste;


    private class Node {
        Node neste;
        E element;
        Node(E element) {
            this.element = element;
        }
    }

    public boolean tom() {
        if (forste == null) {
            return true;
        } return false;
    }

    public void leggTil(E e) {
        Node nyNode = new Node(e);
        //sjekke om lista er tom:
        if (tom()) {
            forste = nyNode;
            siste = forste;
            return;
        } else if (siste == forste) {
            //ett element i lista
            forste.neste = nyNode;
            siste = nyNode;
        } else {
            //Flere elementer i lista
            siste.neste = nyNode;
            siste = nyNode;
        }
    }

    public E fjernMinste() {
        Node minste = forste; //den minste noden
        Node tempForrige; //trengs for aa bytte forMinste naar vi har funnet en temp som er mindre enn noden minste
        Node temp = forste; //denne brukes for aa sjekke om den er mindre enn noden minste
        Node forMinste = null; //denne brukes for aa bytte peker naar vi har funnet minste

        if(tom()){ //Hvis lista er tom returnerer den bare null
            return null;
        }

        while(temp.neste != null) { //Mens det ikke er siste ledd i lenka
            tempForrige = temp; //Rykker ett hakk frem i lista
            temp = temp.neste; //--------||-------
            if (minste.element.compareTo(temp.element)>=0) { //hvis temp er mindre enn minste
                forMinste = tempForrige; //settes til aa vaere noden foer den minste
                minste = temp; //setter temp til aa vaere minst
            }
        }
        if (minste == forste){ //Hvis den minste er den foerste, vil forminste vaere null.
            forste = minste.neste; //setter den andre noden til aa vaere den forste
            return minste.element; //returnerer den minste noden(foerste som var i rekken)
        }
        forMinste.neste = minste.neste; //setter forMinste til aa peke paa den minste sin neste
        return minste.element; //returnerer den minste
    }

    public boolean inneholder(E e) { //sjekker om lenkelista inneholder elementet e
        for(Node temp = forste; temp != null; temp = temp.neste) {
            if (temp.element == e) { //Hvis den gjoer det, returnerer den true
                return true;
            }
        } return false; //Hvis ikke, returnerer den false
    }




}
