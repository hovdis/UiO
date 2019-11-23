import java.io.*;

class OrdSkriver {
	// ord for aa skrive og filNavn
	String[] ord;
	String filNavn;

	// printWriter for aa skrive til fil
	PrintWriter pW;

	OrdSkriver(String[] ord, String filNavn) {
		try {
			// definerer ord, filNavn og printWriter
			this.ord = ord;
			this.filNavn = filNavn;
			pW = new PrintWriter(filNavn);
			writeFile();
		} catch (IOException e) {
			// Kutter programmet hvis fila ikke blir funnet
			System.out.println("Error under lesing av fil");
			System.exit(1);
		} finally {
			// stopper opp, hvis det skjer error
			pW.close();
		}
	}

	private void writeFile() {
		for (String ord : ord) {
			pW.println(ord);
		}

		// stopper skriving til fil
		pW.close();
	}
}