class SortMonitor {
	// usorterte ord
	String[] ord;
	String utFil;
	// splitt antall
	int ordPerTraad;
	int rest;
	int antTraader;

	// String array som holder sorterte ord som venter paa en merge
	String[] venteOrd;

	SortMonitor(String[] ord, int antTraader, String utFil) {
		// definerer usorterte ord, antall traader og filnavn for utskriving
		this.ord = ord;
		this.antTraader = antTraader;
		this.utFil = utFil;

		// regner ut ord pr traad og resten
		rest = ord.length % antTraader;
		ordPerTraad = ord.length / antTraader;

		// variable for report()
		venteOrd = null;
		
		// lag antall traader som er definert
		startTraader();		
	}

	private void startTraader() {
		// hver traad faar ordPerTraad sent inn + antall rest Traader faar pluss 1
		for (int i = 0, copyStart = 0; i < antTraader ; i++, copyStart += ordPerTraad) {
			if (i < rest) {
				// de forste rest-antallet av traader faar ett ekstra ord
				BubbleSort sorteringsTraad = new BubbleSort(copyArray(copyStart, ordPerTraad+1), this);
				sorteringsTraad.start();
				copyStart++;
			} else {
				BubbleSort sorteringsTraad = new BubbleSort(copyArray(copyStart, ordPerTraad), this);
				sorteringsTraad.start();
			}
		}
	}

	private String[] copyArray(int startIndex, int antall) {
		// new array av stringer
		String[] newArray = new String[antall];

		// init new array
		for (int i = 0; i < antall; startIndex++, i++) {
			newArray[i] = ord[startIndex];
		}

		return newArray;
	}

	public synchronized void report(String[] sorterteOrd) {
		// hvis sorterteOrd sin lengde er like lang som orginalen, er den ferdig, ellers merge
		if (sorterteOrd.length == ord.length) {
			// lager en ny fil med sorterte ord
			new OrdSkriver(sorterteOrd, utFil);
		} else if (venteOrd == null) {
			// putter de ferdige ordene i koo for merging
			venteOrd = sorterteOrd;
		} else {
			// merge sorterteOrd med venteOrd
			MergeSort mergeTraad = new MergeSort(venteOrd, sorterteOrd, this);
			mergeTraad.start();
			venteOrd = null;
		}
	}
}