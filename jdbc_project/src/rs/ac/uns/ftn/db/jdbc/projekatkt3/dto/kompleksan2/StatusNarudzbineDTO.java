package rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan2;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StatusNarudzbineDTO {
    // Novi podaci koje upit vraća
    private int idNarudzbine;
    private Date datumNarudzbine; // Koristimo Date za datum
    private int idKupca;
    private int ukupnoArtikala;
    private int naloziUToku;
    private int naloziNaCekanju;
    private int zavrseniNalozi;
    private int ukupanBrojNaloga;

    // Konstruktor za lakše kreiranje objekta u DAO sloju
    public StatusNarudzbineDTO(int idNarudzbine, Date datumNarudzbine, int idKupca, int ukupnoArtikala, int naloziUToku, int naloziNaCekanju, int zavrseniNalozi, int ukupanBrojNaloga) {
        this.idNarudzbine = idNarudzbine;
        this.datumNarudzbine = datumNarudzbine;
        this.idKupca = idKupca;
        this.ukupnoArtikala = ukupnoArtikala;
        this.naloziUToku = naloziUToku;
        this.naloziNaCekanju = naloziNaCekanju;
        this.zavrseniNalozi = zavrseniNalozi;
        this.ukupanBrojNaloga = ukupanBrojNaloga;
    }

    public void setIdNarudzbine(int idNarudzbine) {
        this.idNarudzbine = idNarudzbine;
    }

    public void setDatumNarudzbine(Date datumNarudzbine) {
        this.datumNarudzbine = datumNarudzbine;
    }

    public void setIdKupca(int idKupca) {
        this.idKupca = idKupca;
    }

    public void setUkupnoArtikala(int ukupnoArtikala) {
        this.ukupnoArtikala = ukupnoArtikala;
    }

    public void setNaloziUToku(int naloziUToku) {
        this.naloziUToku = naloziUToku;
    }

    public void setNaloziNaCekanju(int naloziNaCekanju) {
        this.naloziNaCekanju = naloziNaCekanju;
    }

    public void setZavrseniNalozi(int zavrseniNalozi) {
        this.zavrseniNalozi = zavrseniNalozi;
    }

    public void setUkupanBrojNaloga(int ukupanBrojNaloga) {
        this.ukupanBrojNaloga = ukupanBrojNaloga;
    }

    // Generišite gettere za sva nova polja

    public int getIdNarudzbine() { return idNarudzbine; }
    public Date getDatumNarudzbine() { return datumNarudzbine; }
    public int getIdKupca() { return idKupca; }
    public int getUkupnoArtikala() { return ukupnoArtikala; }
    public int getNaloziUToku() { return naloziUToku; }
    public int getNaloziNaCekanju() { return naloziNaCekanju; }
    public int getZavrseniNalozi() { return zavrseniNalozi; }
    public int getUkupanBrojNaloga() { return ukupanBrojNaloga; }

    /**
     * Vraća formatirano zaglavlje za NOVI, bogatiji tabelarni prikaz.
     */
    public static String getFormattedHeader() {
        return String.format("%-15s | %-15s | %-12s | %-10s | %-15s | %-15s | %-15s | %-15s",
                "ID NARUDŽBINE", "DATUM", "ID KUPCA", "UK. ART.", "NALOZI U TOKU", "NA ČEKANJU", "ZAVRŠENI", "UKUPNO NALOGA");
    }

    /**
     * Vraća formatiran string koji predstavlja jedan red u NOVOJ tabeli.
     */
    @Override
    public String toString() {
        // Formatiramo datum u čitljiv oblik
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        String formatiranDatum = sdf.format(this.datumNarudzbine);

        return String.format("%-15d | %-15s | %-12d | %-10d | %-15d | %-15d | %-15d | %-15d",
                idNarudzbine, formatiranDatum, idKupca, ukupnoArtikala, naloziUToku, naloziNaCekanju, zavrseniNalozi, ukupanBrojNaloga);
    }
}