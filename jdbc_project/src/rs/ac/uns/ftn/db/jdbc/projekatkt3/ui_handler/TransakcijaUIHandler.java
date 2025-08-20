package rs.ac.uns.ftn.db.jdbc.projekatkt3.ui_handler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.Set;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.ArtikalDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.service.MainService;

public class TransakcijaUIHandler {
    private static final MainService mainService = new MainService();
    private static final Scanner sc = new Scanner(System.in);

    public void handleOrderCreation() {
        System.out.println("\n--- Kreiranje Nove Narudžbine ---");

        try {
            System.out.println("Učitavanje dostupnih artikala...");
            List<ArtikalDTO> dostupniArtikli = mainService.findAllArtikli();

            if (dostupniArtikli.isEmpty()) {
                System.out.println("Trenutno nema dostupnih artikala za naručivanje. Akcija otkazana.");
                return;
            }

            // === NOVI DEO - Kreiranje seta ID-jeva za lakšu proveru ===
            // Pretpostavka je da ArtikalDTO ima metodu getId() koja vraća ID artikla.
            Set<Integer> dostupniIdjevi = dostupniArtikli.stream()
                    .map(ArtikalDTO::getIdArtikla)
                    .collect(Collectors.toSet());

            System.out.println("\n--- Dostupni Artikli ---");
            for (ArtikalDTO artikal : dostupniArtikli) {
                System.out.println(artikal);
            }
            System.out.println("--------------------------");

            int kupacId = 5000; // stavljena neka default vrednost zbog ogranicenja broja tabela u bazi

            List<Integer> artikalIds = new ArrayList<>();
            String unos;
            do {
                System.out.print("Unesite ID artikla koji želite da dodate u korpu (ili 'X' za kraj): ");
                unos = sc.nextLine();
                if (!unos.equalsIgnoreCase("X")) {
                    try {
                        int unetiId = Integer.parseInt(unos);

                        // === NOVI DEO - Validacija unosa ===

                        // 1. Provera da li je artikal već u korpi
                        if (artikalIds.contains(unetiId)) {
                            System.out.println("Greška: Artikal je već dodat u korpu.");
                            continue; // Preskače ostatak petlje i traži novi unos
                        }

                        // 2. Provera da li uneti ID postoji među dostupnim artiklima
                        if (dostupniIdjevi.contains(unetiId)) {
                            artikalIds.add(unetiId);
                            System.out.println("Artikal dodat u korpu.");
                        } else {
                            System.out.println("Greška: Uneli ste ID koji ne postoji. Molimo pokušajte ponovo.");
                        }
                        // === KRAJ NOVOG DELA ===

                    } catch (NumberFormatException e) {
                        System.out.println("Pogrešan unos. Molimo unesite ID kao broj.");
                    }
                }
            } while (!unos.equalsIgnoreCase("X"));

            if (artikalIds.isEmpty()) {
                System.out.println("Nijedan artikal nije dodat. Narudžbina otkazana.");
                return;
            }

            System.out.println("\nZapočinjem transakciju kreiranja narudžbine...");
            boolean uspeh = mainService.createOrderWithItems(kupacId, artikalIds);

            if (uspeh) {
                System.out.println("\n[USPEH] Transakcija je uspešno završena. Narudžbina je kreirana.");
            } else {
                System.out.println("\n[NEUSPEH] Transakcija nije uspela. Sve promene su poništene.");
            }

        } catch (NumberFormatException e) {
            System.err.println("Greška prilikom unosa. ID kupca mora biti broj.");
        } catch (SQLException e) {
            System.err.println("\n[GREŠKA BAZE] Došlo je do problema pri komunikaciji sa bazom.");
            e.printStackTrace();
        }
    }
}