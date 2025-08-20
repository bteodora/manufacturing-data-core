package rs.ac.uns.ftn.db.jdbc.projekatkt3.model;

import java.util.Date;
import java.util.Objects;

public class Narudzbina {
    private int id;
    private Date datum;
    private String status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Narudzbina{" +
                "id=" + id +
                ", datum=" + datum +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narudzbina that = (Narudzbina) o;
        return id == that.id && Objects.equals(datum, that.datum) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, datum, status);
    }
}
