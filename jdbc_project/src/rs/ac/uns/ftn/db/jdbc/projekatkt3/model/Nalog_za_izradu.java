package rs.ac.uns.ftn.db.jdbc.projekatkt3.model;

import java.util.Date;
import java.util.Objects;

public class Nalog_za_izradu {
    private int id;
    private Date datum_pocetak;
    private Date datum_zavrsetak;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum_pocetak() {
        return datum_pocetak;
    }

    public void setDatum_pocetak(Date datum_pocetak) {
        this.datum_pocetak = datum_pocetak;
    }

    public Date getDatum_zavrsetak() {
        return datum_zavrsetak;
    }

    public void setDatum_zavrsetak(Date datum_zavrsetak) {
        this.datum_zavrsetak = datum_zavrsetak;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Nalog_za_izradu{" +
                "id=" + id +
                ", datum_pocetak=" + datum_pocetak +
                ", datum_zavrsetak=" + datum_zavrsetak +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nalog_za_izradu that = (Nalog_za_izradu) o;
        return id == that.id && Objects.equals(datum_pocetak, that.datum_pocetak) && Objects.equals(datum_zavrsetak, that.datum_zavrsetak) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datum_pocetak, datum_zavrsetak, status);
    }
}
