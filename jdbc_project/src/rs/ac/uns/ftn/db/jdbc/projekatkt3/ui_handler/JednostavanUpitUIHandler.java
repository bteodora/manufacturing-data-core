package rs.ac.uns.ftn.db.jdbc.projekatkt3.ui_handler;

import java.sql.SQLException;
import java.util.List;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.jednostavan.ArtikalStatDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.service.MainService;

public class JednostavanUpitUIHandler {

    private static final MainService mainService = new MainService();

    public void showArtikalStats() {
        String separator = "===========================================================================================";

        try {
            // 1. Pozivamo servisnu metodu da dobijemo podatke
            List<ArtikalStatDTO> statistika = mainService.getStat();

            System.out.println("\n" + separator);
            System.out.println("                   IZVEŠTAJ: STATISTIKA NARUDŽBINA PO ARTIKLIMA");
            System.out.println(separator);

            // 2. Proveravamo da li su rezultati prazni
            if (statistika.isEmpty()) {
                System.out.println("Nema dostupnih podataka o naručenim artiklima.");
            } else {
                // 3. Ako ima podataka, ispisujemo zaglavlje i rezultate
                System.out.println(ArtikalStatDTO.getFormattedHeader()); // Koristimo metodu iz DTO
                System.out.println("-------------------------------------------------------------------------------------------");

                for (ArtikalStatDTO dto : statistika) {
                    System.out.println(dto); // Koristimo formatirani toString() iz DTO
                }
            }
            System.out.println(separator + "\n");

        } catch (SQLException e) {
            // 4. Hvatamo i obrađujemo potencijalnu grešku pri radu sa bazom
            System.err.println("\n[GREŠKA] Došlo je do problema prilikom preuzimanja podataka iz baze.");
            System.err.println("Poruka greške: " + e.getMessage());
        }
    }
}