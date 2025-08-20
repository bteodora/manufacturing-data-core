package rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan1;

import java.util.Objects;

public class IzradaNarudzbineDTO {
    private int id_narudzbine;
    private String naziv_artikla;
    private String naziv_zice;
    private String status_naloga_za_izradu;

    public int getId_narudzbine() {
        return id_narudzbine;
    }

    public void setId_narudzbine(int id_narudzbine) {
        this.id_narudzbine = id_narudzbine;
    }

    public String getNaziv_artikla() {
        return naziv_artikla;
    }

    public void setNaziv_artikla(String naziv_artikla) {
        this.naziv_artikla = naziv_artikla;
    }

    public String getNaziv_zice() {
        return naziv_zice;
    }

    public void setNaziv_zice(String naziv_zice) {
        this.naziv_zice = naziv_zice;
    }

    public String getStatus_naloga_za_izradu() {
        return status_naloga_za_izradu;
    }

    public void setStatus_naloga_za_izradu(String status_naloga_za_izradu) {
        this.status_naloga_za_izradu = status_naloga_za_izradu;
    }

    @Override
    public String toString() {
        return "IzradaNarudzbineDTO{" +
                "id_narudzbine=" + id_narudzbine +
                ", naziv_artikla='" + naziv_artikla + '\'' +
                ", naziv_zice='" + naziv_zice + '\'' +
                ", status_naloga_za_izradu='" + status_naloga_za_izradu + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IzradaNarudzbineDTO that = (IzradaNarudzbineDTO) o;
        return id_narudzbine == that.id_narudzbine && Objects.equals(naziv_artikla, that.naziv_artikla) && Objects.equals(naziv_zice, that.naziv_zice) && Objects.equals(status_naloga_za_izradu, that.status_naloga_za_izradu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_narudzbine, naziv_artikla, naziv_zice, status_naloga_za_izradu);
    }
}
