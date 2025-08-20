package rs.ac.uns.ftn.db.jdbc.projekatkt3.ui_handler;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan1.IzradaNarudzbineDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.service.MainService;

import java.sql.SQLException;
import java.util.List;

public class Komplesan1UpitUIHandler {
    private static final MainService mainService = new MainService();

    public void showDetaljiIzrade() { // Promenio sam ime metode da bude deskriptivnije
        String separator = "========================================================================================";

        try {
            // 1. Preuzimanje podataka iz servisa
            List<IzradaNarudzbineDTO> rezultati = mainService.getIzradaNarudzbine();

            System.out.println("\n" + separator);
            System.out.println("     IZVEŠTAJ: Detalji narudžbina u statusu 'U proizvodnji'");
            System.out.println(separator);

            // 2. Provera da li ima rezultata
            if (rezultati.isEmpty()) {
                System.out.println("Nema narudžbina koje su trenutno u proizvodnji.");
            } else {
                // 3. Grupisanje i prikaz rezultata
                int trenutnaNarudzbinaId = -1; // Koristimo za praćenje promene narudžbine

                for (IzradaNarudzbineDTO dto : rezultati) {
                    // Ako je ovo prva narudžbina ili se ID promenio, ispiši novo zaglavlje
                    if (dto.getId_narudzbine() != trenutnaNarudzbinaId) {
                        if (trenutnaNarudzbinaId != -1) {
                            // Dodaj liniju posle prethodne narudžbine za bolju preglednost
                            System.out.println("----------------------------------------------------------------------------------------");
                        }
                        System.out.printf("\nNARUDŽBINA ID: %-10d (Status naloga: %s)\n", dto.getId_narudzbine(), dto.getStatus_naloga_za_izradu());
                        System.out.printf("%-45s | %s\n", "  ARTIKAL", "SASTAVNA ŽICA");
                        System.out.println("----------------------------------------------------------------------------------------");
                        trenutnaNarudzbinaId = dto.getId_narudzbine();
                    }

                    // Ispis detalja (artikal i žica) za trenutnu narudžbinu
                    System.out.printf("%-45s | %s\n", "  -> " + dto.getNaziv_artikla(), dto.getNaziv_zice());
                }
            }
            System.out.println(separator + "\n");

        } catch (SQLException e) {
            System.err.println("\n[GREŠKA] Došlo je do problema prilikom preuzimanja podataka iz baze.");
            System.err.println("Poruka greške: " + e.getMessage());
        }
    }
}
