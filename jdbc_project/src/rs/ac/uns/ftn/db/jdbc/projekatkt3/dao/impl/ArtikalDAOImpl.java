package rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.impl;

import rs.ac.uns.ftn.db.jdbc.projekatkt3.connection.ConnectionUtil_HikariCP;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dao.ArtikalDAO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.ArtikalDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.dto.jednostavan.ArtikalStatDTO;
import rs.ac.uns.ftn.db.jdbc.projekatkt3.model.Artikal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtikalDAOImpl implements ArtikalDAO {
    @Override
    public int count() throws SQLException {
        return 0;
    }

    @Override
    public boolean delete(Artikal entity) throws SQLException {
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
    public Iterable<Artikal> findAll() throws SQLException {
        return null;
    }

    public List<ArtikalDTO> findAllArtikli() throws SQLException {
        String query = "SELECT id_ar, naziv_art FROM artikal ORDER BY id_ar";
        List<ArtikalDTO> sviArtikli = new ArrayList<>();

        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while(resultSet.next()) {
                int id = resultSet.getInt("id_ar");
                String naziv = resultSet.getString("naziv_art");
                sviArtikli.add(new ArtikalDTO(id, naziv));
            }
        }
        return sviArtikli;
    }

    @Override
    public Iterable<Artikal> findAllById(Iterable<Integer> integers) throws SQLException {
        return null;
    }

    @Override
    public Artikal findById(Integer integer) throws SQLException {
        return null;
    }

    @Override
    public boolean save(Artikal entity) throws SQLException {
        return false;
    }

    @Override
    public int saveAll(Iterable<Artikal> entities) throws SQLException {
        return 0;
    }

    @Override
    public List<ArtikalStatDTO> findAllStat() throws SQLException {
        String query = "SELECT \n" +
                "    a.naziv_art AS naziv_artikla,\n" +
                "    a.kateg AS kategorija,\n" +
                "    COUNT(s.narudzbina_id_nar) AS broj_narudzbina\n" +
                "FROM \n" +
                "    artikal a\n" +
                "JOIN \n" +
                "    sadrzi s ON a.id_ar = s.artikal_id_ar\n" +
                "GROUP BY \n" +
                "    a.naziv_art, a.kateg\n" +
                "ORDER BY \n" +
                "    broj_narudzbina DESC";
        List<ArtikalStatDTO> dtos = new ArrayList<ArtikalStatDTO>();
        try (Connection connection = ConnectionUtil_HikariCP.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ArtikalStatDTO dto = new ArtikalStatDTO();
                dto.setBroj_narudzbina(resultSet.getInt(3));
                dto.setKategorija(resultSet.getString(2));
                dto.setNaziv(resultSet.getString(1));
                dtos.add(dto);
            }

        }
        return dtos;
    }
}
