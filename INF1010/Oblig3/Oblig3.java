import java.util.*; //implementerer java.util-pakka
import java.io.*; //implementerer java.io-pakka

class Oblig3 {
	static ArrayList<Bil> fartoy; //oppretter scanneren
	public static void main(String[] args) throws Exception {
 	try {
  	Scanner fil = new Scanner(new File(args[0])); //Scanneren, med parameter som kommer inn fra terminal
		String type;
		fartoy = new ArrayList<Bil>(); //arraylisten
		 while (fil.hasNext()) { //loop for aa gaa gjennom hele fila og legge til objekter av tiktig type
		 	type = fil.next();
		  if (type.equals("BIL")) {
				 Bil bil = new Bil(fil.next());
				 fartoy.add(bil);
		  } if (type.equals("EL")) {
				 ElBil el = new ElBil(fil.next(), Integer.parseInt(fil.next()));
				 fartoy.add(el);
		  } if (type.equals("FOSSIL")) {
				 FossilBil fossilBil = new FossilBil (fil.next(), Double.parseDouble(fil.next()));
				 fartoy.add(fossilBil);
		  } if (type.equals("LASTEBIL")) {
				 LasteBil lasteBil = new LasteBil(fil.next(), Double.parseDouble(fil.next()), Double.parseDouble(fil.next()));
			   fartoy.add(lasteBil);
	  	} if (type.equals("PERSONFOSSILBIL")) {
			   PersonBil personBil = new PersonBil(fil.next(), Double.parseDouble(fil.next()), Integer.parseInt(fil.next()));
			   fartoy.add(personBil);
			}
		 } fil.close();
	} //Hvis du ikke sender med en fil aa lese fra, kalles catch-metoden
	catch(FileNotFoundException e) {
	System.out.println("Fant itte fila");
	}
		skrivUt(); //Kaller paa skrivUt metoden
}
	//Metode som gaar gjennom hele araylista og sjekker etter om objektet er en personbil. Hvis det er det printer den ut toString-metoden til t.
	public static void skrivUt() {
		for (Object t: fartoy) {
			if (t instanceof PersonBil) {
				System.out.println(t);
			}
		}
	}
}
