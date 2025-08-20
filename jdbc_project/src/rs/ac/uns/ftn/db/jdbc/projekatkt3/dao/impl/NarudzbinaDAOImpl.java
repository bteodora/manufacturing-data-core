package rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.impl;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.NarudzbinaDAO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan1.IzradaNarudzbineDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.kompleksan2.StatusNarudzbineDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.model.Narudzbina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NarudzbinaDAOImpl implements NarudzbinaDAO {
    @Override
    public int count() throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(Narudzbina entity) throws SQLException {
        return false;
    }

    @Override
    public int deleteAll() throws SQLException {
        return 0;
    }

    @Override
    public boolean deleteById(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public boolean existsById(Integer integer) throws SQLException {
        return false;
    }

    @Override
    public Iterable<Narudzbina> findAll() throws SQLException {
        return null;
    }

    @Override
    public Iterable<Narudzbina> findAllById(Iterable<Integer> integers) throws SQLException {
        return null;
    }

    @Override
    public Narudzbina findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public boolean save(Narudzbina entity) throws SQLException {
        return false;
    }

    @Override
    public int saveAll(Iterable<Narudzbina> entities) throws SQLException {
        return 0;
    }

    @Override
    public List<IzradaNarudzbineDTO> getOrderDetailsWithArticlesAndWires() throws SQLException{
        String query ="SELECT n.id_nar AS narudzbina,\n" +
                "       a.naziv_art AS artikal,\n" +
                "       z.naziv_z AS zica,\n" +
                "       na.status_nal AS status_naloga\n" +
                "FROM narudzbina n\n" +
                "JOIN sadrzi s ON n.id_nar = s.narudzbina_id_nar\n" +
                "JOIN artikal a ON s.artikal_id_ar = a.id_ar\n" +
                "JOIN nalog_za_izradu na ON s.nalog_za_izradu_id_nal = na.id_nal\n" +
                "JOIN zica z ON 1=1 \n" +
                "JOIN je_izradjen j ON j.zica_id_z = z.id_z\n" +
                "WHERE n.status = 'U proizvodnji'\n" +
                "ORDER BY n.id_nar, a.naziv_art";
        List<IzradaNarudzbineDTO> dtos = new ArrayList<>();
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                IzradaNarudzbineDTO dto = new IzradaNarudzbineDTO();
                dto.setId_narudzbine(resultSet.getInt(1));
                dto.setNaziv_zice(resultSet.getString(3));
                dto.setNaziv_artikla(resultSet.getString(2));
                dto.setStatus_naloga_za_izradu(resultSet.getString(4));
                dtos.add(dto);
            }

        }
        return dtos;
    }

    @Override
    public List<StatusNarudzbineDTO> getOrdersInProductionProgress() throws SQLException {
        String query = "SELECT " +
                "    n.id_nar AS \"ID Narudžbine\", " +
                "    n.datum_nar AS \"Datum naručivanja\", " +
                "    n.kupac_id_kup AS \"ID Kupca\", " +
                "    COUNT(DISTINCT s.artikal_id_ar) AS \"Ukupno Artikala\", " +
                "    COUNT(CASE WHEN na.status_nal = 'U_toku_izrade' THEN 1 END) AS \"Nalozi U Toku\", " +
                "    COUNT(CASE WHEN na.status_nal = 'Na_cekanju' THEN 1 END) AS \"Nalozi Na Čekanju\", " +
                "    COUNT(CASE WHEN na.status_nal = 'Zavrsen' THEN 1 END) AS \"Završeni Nalozi\", " +
                "    COUNT(s.nalog_za_izradu_id_nal) AS \"Ukupan Broj Naloga\" " +
                "FROM narudzbina n " +
                "JOIN sadrzi s ON n.id_nar = s.narudzbina_id_nar " +
                "JOIN nalog_za_izradu na ON s.nalog_za_izradu_id_nal = na.id_nal " +
                "WHERE n.id_nar IN ( " +
                "    SELECT DISTINCT s2.narudzbina_id_nar " +
                "    FROM sadrzi s2 " +
                "    JOIN nalog_za_izradu na2 ON s2.nalog_za_izradu_id_nal = na2.id_nal " +
                "    WHERE na2.status_nal = 'U_toku_izrade' " +
                ") " +
                "GROUP BY n.id_nar, n.datum_nar, n.kupac_id_kup " +
                "ORDER BY n.datum_nar ASC";

        List<StatusNarudzbineDTO> dtos = new ArrayList<>();
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                // Pristupamo podacima preko aliassa - mnogo sigurnije nego preko indeksa!
                int idNarudzbine = resultSet.getInt("ID Narudžbine");
                java.util.Date datumNarudzbine = resultSet.getDate("Datum naručivanja");
                int idKupca = resultSet.getInt("ID Kupca");
                int ukupnoArtikala = resultSet.getInt("Ukupno Artikala");
                int naloziUToku = resultSet.getInt("Nalozi U Toku");
                int naloziNaCekanju = resultSet.getInt("Nalozi Na Čekanju");
                int zavrseniNalozi = resultSet.getInt("Završeni Nalozi");
                int ukupanBrojNaloga = resultSet.getInt("Ukupan Broj Naloga");

                // Kreiramo novi, bogatiji DTO objekat pomoću konstruktora
                StatusNarudzbineDTO dto = new StatusNarudzbineDTO(idNarudzbine, datumNarudzbine, idKupca, ukupnoArtikala, naloziUToku, naloziNaCekanju, zavrseniNalozi, ukupanBrojNaloga);
                dtos.add(dto);
            }
        }
        return dtos;
    }

    @Override
    public boolean createOrderWithItems(int kupacId, List<Integer> artikalIds) throws SQLException {
        String insertNarudzbinaSQL = "INSERT INTO narudzbina (id_nar, datum_nar, status, kupac_id_kup) VALUES (?, SYSDATE, 'Na cekanju', ?)";
        String insertNalogSQL = "INSERT INTO nalog_za_izradu (id_nal, datum_kre, status_nal) VALUES (?, SYSDATE, 'Na_cekanju')";
        String insertSadrziSQL = "INSERT INTO sadrzi (narudzbina_id_nar, artikal_id_ar, nalog_za_izradu_id_nal) VALUES (?, ?, ?)";

        String getNalogIdSQL = "SELECT nalog_za_izradu_seq.NEXTVAL FROM DUAL";
        String getNarudzbinaIdSQL = "SELECT narudzbina_seq.NEXTVAL FROM DUAL";

        Connection connection = null;
        try {
            connection = ConnectionUtil_HikariCP.getConnection();
            connection.setAutoCommit(false);

            int noviNalogId;
            int novaNarudzbinaId;

            try (PreparedStatement psNalogId = connection.prepareStatement(getNalogIdSQL);
                 ResultSet rsNalog = psNalogId.executeQuery()) {
                rsNalog.next();
                noviNalogId = rsNalog.getInt(1);
            }

            try (PreparedStatement psNarudzbinaId = connection.prepareStatement(getNarudzbinaIdSQL);
                 ResultSet rsNarudzbina = psNarudzbinaId.executeQuery()) {
                rsNarudzbina.next();
                novaNarudzbinaId = rsNarudzbina.getInt(1);
            }

            try (PreparedStatement psNalog = connection.prepareStatement(insertNalogSQL)) {
                psNalog.setInt(1, noviNalogId);
                psNalog.executeUpdate();
                System.out.println("Kreiran zajednički nalog za izradu sa ID: " + noviNalogId);
            }

            try (PreparedStatement psNarudzbina = connection.prepareStatement(insertNarudzbinaSQL)) {
                psNarudzbina.setInt(1, novaNarudzbinaId);
                psNarudzbina.setInt(2, kupacId);
                psNarudzbina.executeUpdate();
                System.out.println("Kreirana narudžbina sa ID: " + novaNarudzbinaId);
            }

            System.out.println("Dodavanje artikala u narudžbinu...");
            try (PreparedStatement psSadrzi = connection.prepareStatement(insertSadrziSQL)) {
                for (Integer artikalId : artikalIds) {
                    psSadrzi.setInt(1, novaNarudzbinaId);
                    psSadrzi.setInt(2, artikalId);
                    psSadrzi.setInt(3, noviNalogId);
                    psSadrzi.addBatch();
                }
                psSadrzi.executeBatch();
                System.out.println("  - Svi artikli uspešno povezani.");
            }

            connection.commit();
            return true;

        } catch (SQLException e) {
            System.err.println("\n[GREŠKA] Došlo je do greške u transakciji. Pokrećem rollback...");
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.err.println("Rollback uspešno izvršen.");
                } catch (SQLException ex) {
                    System.err.println("Greška prilikom rollback-a: " + ex.getMessage());
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
