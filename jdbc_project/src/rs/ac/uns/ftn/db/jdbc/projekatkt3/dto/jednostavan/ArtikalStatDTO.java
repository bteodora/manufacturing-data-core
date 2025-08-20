package rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.jednostavan;

import java.util.Objects;

public class ArtikalStatDTO {
    private String naziv;
    private int broj_narudzbina;
    private String kategorija;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getBroj_narudzbina() {
        return broj_narudzbina;
    }

    public void setBroj_narudzbina(int broj_narudzbina) {
        this.broj_narudzbina = broj_narudzbina;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public static String getFormattedHeader() {
        // Poravnava tekst levo (-) u polju određene širine
        return String.format("%-40s %-30s %-20s", "NAZIV ARTIKLA", "KATEGORIJA", "BROJ NARUDŽBINA");
    }

    @Override
    public String toString() {
        return String.format("%-40s %-30s %-20d", naziv, kategorija, broj_narudzbina);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtikalStatDTO that = (ArtikalStatDTO) o;
        return broj_narudzbina == that.broj_narudzbina && Objects.equals(naziv, that.naziv) && Objects.equals(kategorija, that.kategorija);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, broj_narudzbina, kategorija);
    }
}
