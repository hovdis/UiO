class BubbleSort extends Thread {
	// variabler for ord til sortering og monitor for aa rapportere
	String[] ord;
	SortMonitor monitor;

	BubbleSort(String[] ord, SortMonitor monitor) {
		// definerer variabler
		this.ord = ord;
		this.monitor = monitor;
	}

	public void run() {
		sort();
		monitor.report(ord);
	}

	private void sort() {
		// enkel men ueffektiv sorteringsalgoritme
		for (int i = 0; i < ord.length; i++) {
			for (int j = 0; j < ord.length; j++) {
				if (ord[j].compareTo(ord[i]) > 0) {
					String tmp = ord[j];
					ord[j] = ord[i];
					ord[i] = tmp;
				}
			}
		}
	}
}