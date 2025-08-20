package rs.ac.uns.ftn.db.jdbc.projekatkt3.ui_handler;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan2.StatusNarudzbineDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.service.MainService;

import java.sql.SQLException;
import java.util.List;

public class Kompleksan2UpitUIHandler {
    private static final MainService mainService = new MainService();

    public void showStatusNarudzbina() {
        // Prilagođavamo separator za novi, širi prikaz
        String separator = "=====================================================================================================================================================";

        try {
            // MainService samo prosleđuje poziv, njega ne moramo menjati
            List<StatusNarudzbineDTO> rezultati = mainService.getStatusNarudzbine();

            System.out.println("\n" + separator);
            // Menjamo naslov da bolje opiše izveštaj
            System.out.println("                         IZVEŠTAJ: Status narudžbina u proizvodnji (najstarije prvo)");
            System.out.println(separator);

            if (rezultati.isEmpty()) {
                System.out.println("Trenutno nema narudžbina sa artiklima u statusu 'U toku izrade'.");
            } else {
                // Pozivamo novu, unapređenu metodu za ispis zaglavlja
                System.out.println(StatusNarudzbineDTO.getFormattedHeader());
                System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------");

                // Petlja ostaje ista, ali sada poziva novu, unapređenu toString() metodu
                for (StatusNarudzbineDTO dto : rezultati) {
                    System.out.println(dto);
                }
            }
            System.out.println(separator + "\n");

        } catch (SQLException e) {
            System.err.println("\n[GREŠKA] Došlo je do problema prilikom preuzimanja podataka iz baze.");
            System.err.println("Poruka greške: " + e.getMessage());
            e.printStackTrace(); // Dobro je dodati i ovo za lakše debagovanje
        }
    }
}