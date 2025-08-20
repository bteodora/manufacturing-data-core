package rs.ac.uns.ftn.db.jdbc.projekatkt3.ui_handler;

import java.util.Scanner;

public class MainUIHandler {

	public static Scanner sc = new Scanner(System.in);

	private final JednostavanUpitUIHandler jednostavanUpitUIHandler = new JednostavanUpitUIHandler();
	private final Komplesan1UpitUIHandler komplesan1UpitUIHandler = new Komplesan1UpitUIHandler();
	private final Kompleksan2UpitUIHandler kompleksan2UpitUIHandler = new Kompleksan2UpitUIHandler();
	private final TransakcijaUIHandler transakcijaUIHandler = new TransakcijaUIHandler();

	public void handleMainMenu() {

		String answer;
		do {
			System.out.println("\nOdaberite opciju:");
			System.out.println("1 - Statistika narucivanja artikala");
			System.out.println("2 - Raščlanjeni pregled proizvodnih naloga i njihovog sastava");
			System.out.println("3 - Analiza napretka narudžbina u proizvodnom ciklusu");
			System.out.println("4 - Kreiranje kompletne narudžbine sa generisanjem svih potrebnih naloga za izradu");
			System.out.println("X - Izlazak iz programa");

			answer = sc.nextLine();

			switch (answer) {
			case "1":
				jednostavanUpitUIHandler.showArtikalStats();
				break;
			case "2":
				komplesan1UpitUIHandler.showDetaljiIzrade();
				break;
			case "3":
				kompleksan2UpitUIHandler.showStatusNarudzbina();
				break;
			case "4":
				transakcijaUIHandler.handleOrderCreation();
			}

		} while (!answer.equalsIgnoreCase("X"));

		sc.close();
	}

}
