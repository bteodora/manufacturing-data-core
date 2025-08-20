package rs.ac.uns.ftn.db.jdbc.projekatkt3.model;

import java.util.Objects;

public class Artikal {
    private int id;
    private String naziv;
    private String opis;
    private String kategorija;

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

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", kategorija='" + kategorija + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikal artikal = (Artikal) o;
        return id == artikal.id && Objects.equals(naziv, artikal.naziv) && Objects.equals(opis, artikal.opis) && Objects.equals(kategorija, artikal.kategorija);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, naziv, opis, kategorija);
    }
}
