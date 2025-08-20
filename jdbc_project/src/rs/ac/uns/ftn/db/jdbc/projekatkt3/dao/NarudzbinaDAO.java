package rs.ac.uns.ftn.db.jdbc.projekatkt3.dao;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan1.IzradaNarudzbineDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan2.StatusNarudzbineDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.model.Narudzbina;

import java.sql.SQLException;
import java.util.List;

public interface NarudzbinaDAO extends CRUDDao<Narudzbina, Integer> {
    List<IzradaNarudzbineDTO> getOrderDetailsWithArticlesAndWires() throws SQLException;
    List<StatusNarudzbineDTO> getOrdersInProductionProgress() throws SQLException;
    boolean createOrderWithItems(int kupacId, List<Integer> artikalIds) throws SQLException;
}
