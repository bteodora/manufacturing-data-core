package rs.ac.uns.ftn.db.jdbc.projekatkt3.dto;

public class ArtikalDTO {
    private int idArtikla;
    private String nazivArtikla;

    public ArtikalDTO(int idArtikla, String nazivArtikla) {
        this.idArtikla = idArtikla;
        this.nazivArtikla = nazivArtikla;
    }

    public int getIdArtikla() {
        return idArtikla;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    // toString() metoda za lep ispis u konzoli
    @Override
    public String toString() {
        return String.format("  ID: %-5d | Naziv: %s", idArtikla, nazivArtikla);
    }
}