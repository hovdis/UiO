class Oblig9 {
	/*Vi bestemte oss fo aa proeve aa bruke extends Thread i stedet for implements Runnable.
	Dette fordi vi har blitt ambefalt aa bruke implements Runnable, og vi ville se om det var 
	noe saerlig forskjell paa dem. Vi skal lage det samme programmet med implements Runnable i 
	eksamensperioden.*/
	public static void main(String[] args) {
		// variabler 
		int antTraader;
		String innFil;
		String utFil;

		try {
			if (args.length > 2) {
				// initialiserer variabler 
				antTraader = Integer.parseInt(args[0]); 
				innFil = args[1];
				utFil = args[2];
			} else {
				throw new InvalidSyntaxException();
			}
		} catch (InvalidSyntaxException e) {
			System.out.println("Feil syntaks: java Oblig9 <antall traader> <names.txt> <out.txt>");
			return;
		}

		// Starter sortering
		Sortering sorterteOrd = new Sortering(antTraader, innFil, utFil);
	}
}

class InvalidSyntaxException extends Exception {
	
} // slutt InvalidSyntaxException