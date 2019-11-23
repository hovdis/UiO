class MergeSort extends Thread {
	// variable for de sorterte arrayene(ord1 og ord2) og den nye arrayen som blir flettet
	String[] ord1;
	String[] ord2;
	String[] sorterteOrd;

	// monitor for aa rapportere
	SortMonitor monitor;

	MergeSort(String[] ord1, String[] ord2, SortMonitor monitor) {
		// definerer variablene
		this.ord1 = ord1;
		this.ord2 = ord2;
		this.monitor = monitor;
		sorterteOrd = new String[(ord1.length + ord2.length)];
	}

	public void run() {
		sort();
		monitor.report(sorterteOrd);
	}

	private void sort() {
		int index1 = 0;
		int index2 = 0;

		// mergesortering
		for (int i = 0; i < sorterteOrd.length; i++) {
			if (index1 >= ord1.length) {
				sorterteOrd[i] = ord2[index2++];
			} else if (index2 >= ord2.length) {
				sorterteOrd[i] = ord1[index1++];
			} else if (ord1[index1].compareTo(ord2[index2]) < 0) {
				sorterteOrd[i] = ord1[index1++];
			} else {
				sorterteOrd[i] = ord2[index2++];
			}
		}
	}
}