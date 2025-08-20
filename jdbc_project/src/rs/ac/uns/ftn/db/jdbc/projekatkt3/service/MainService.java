package rs.ac.uns.ftn.db.jdbc.projekatkt3.service;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.ArtikalDAO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.NarudzbinaDAO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.impl.ArtikalDAOImpl;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.impl.NarudzbinaDAOImpl;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.ArtikalDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.jednostavan.ArtikalStatDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan1.IzradaNarudzbineDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan2.StatusNarudzbineDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainService {
    private static final ArtikalDAO artikalDAO = new ArtikalDAOImpl();
    private static final NarudzbinaDAO narudzbinaDAO = new NarudzbinaDAOImpl();

    public List<ArtikalStatDTO> getStat() throws SQLException {
        return artikalDAO.findAllStat();
    }

    public List<IzradaNarudzbineDTO> getIzradaNarudzbine() throws SQLException {
        return narudzbinaDAO.getOrderDetailsWithArticlesAndWires();
    }

    public  List<StatusNarudzbineDTO> getStatusNarudzbine() throws SQLException {
        return narudzbinaDAO.getOrdersInProductionProgress();
    }

    public boolean createOrderWithItems(int kupacId, List<Integer> artikalIds) throws SQLException{
        return narudzbinaDAO.createOrderWithItems(kupacId, artikalIds);
    }

    public List<ArtikalDTO> findAllArtikli() throws SQLException {
        List<ArtikalDTO> lista = artikalDAO.findAllArtikli();
        return lista;
    }
}
