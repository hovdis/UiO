import java.util.*;
import java.io.*;

class OrdLeser {
	// variabler for lesing
	Scanner fc; //fil scanner
	String filNavn;
	// variabler for lagring
	int antOrd;
	String[] ord;

	OrdLeser(String filNavn) {
		try {
			// definerer fc og les fil
			this.filNavn = filNavn;
			fc = new Scanner(new File(filNavn));
			lesFil();
		} catch (FileNotFoundException e) {
			// Kutter programmet hvis fila ikke blir funnet
			System.out.println("Fant ikke fila! Vennligst prov igjen!");
			System.exit(1);
		}
	}

	private void lesFil() {
		// definerer antOrd og ord[]
		antOrd = fc.nextInt();
		ord = new String[antOrd];
		fc.nextLine();

		try {
			// les alle ord inn i ord[]
			int current = 0;
			while(current < antOrd) {
				ord[current++] = fc.nextLine();
			}

			if (fc.hasNext()) {
				throw new WrongAmountException();
			}
		} catch (WrongAmountException e) {
			System.out.println("Feil antall ord, sammenlignet med forste linje");
			System.exit(1);
		} catch (NoSuchElementException e) {
			System.out.println("Feil antall ord, sammenlignet med forste linje");
			System.exit(1);
		}
	}

	public String[] getord() {
		return ord;
	}
}

class WrongAmountException extends Exception {
	
}