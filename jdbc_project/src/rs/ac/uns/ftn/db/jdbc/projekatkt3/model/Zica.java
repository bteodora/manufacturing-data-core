package rs.ac.uns.ftn.db.jdbc.projekatkt3.model;

import java.util.Date;
import java.util.Objects;

public class Zica {
    private int id;
    private String naziv;
    private int duzina;
    private Date datum_proizvodnje;

    public int getDuzina() {
        return duzina;
    }

    public void setDuzina(int duzina) {
        this.duzina = duzina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getDatum_proizvodnje() {
        return datum_proizvodnje;
    }

    public void setDatum_proizvodnje(Date datum_proizvodnje) {
        this.datum_proizvodnje = datum_proizvodnje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zica zica = (Zica) o;
        return id == zica.id && duzina == zica.duzina && Objects.equals(naziv, zica.naziv) && Objects.equals(datum_proizvodnje, zica.datum_proizvodnje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, duzina, datum_proizvodnje);
    }

    @Override
    public String toString() {
        return "Zica{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", duzina=" + duzina +
                ", datum_proizvodnje=" + datum_proizvodnje +
                '}';
    }
}
