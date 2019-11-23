class Sortering {
	// variabler 
	int antTraader;
	String innFil;
	String utFil;
	// array for aa holde usorterte ord
	String[] ord;

	Sortering(int antTraader, String innFil, String utFil) {
		// definerer variablene
		this.antTraader = antTraader;
		this.innFil = innFil;
		this.utFil = utFil;

		// kaller paa metoder
		lesOrd();
		sorterOrd();
	}

	private void lesOrd() {
		// Leser ord inn i array
		OrdLeser ordLeser = new OrdLeser(innFil);
		ord = ordLeser.getord();
	}

	private void sorterOrd() {
		// Starter sortering
		new SortMonitor(ord, antTraader, utFil);
	}

}