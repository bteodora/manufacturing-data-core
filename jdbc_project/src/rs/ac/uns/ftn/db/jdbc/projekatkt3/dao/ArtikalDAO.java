package rs.ac.uns.ftn.db.jdbc.projekatkt3.dao;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.ArtikalDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.jednostavan.ArtikalStatDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.model.Artikal;

import java.sql.SQLException;
import java.util.List;

public interface ArtikalDAO extends CRUDDao<Artikal, Integer>{
    public List<ArtikalStatDTO> findAllStat() throws SQLException;
    public List<ArtikalDTO> findAllArtikli() throws SQLException;
}
