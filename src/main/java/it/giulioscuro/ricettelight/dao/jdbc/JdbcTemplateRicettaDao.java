package it.giulioscuro.ricettelight.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import it.giulioscuro.ricettelight.dao.RicettaDao;
import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.model.Ricetta;

public class JdbcTemplateRicettaDao implements RicettaDao {

	private static final Logger log = LoggerFactory.getLogger(JdbcTemplateRicettaDao.class);

	@Autowired
	private DataSource dataSource;

	private NamedParameterJdbcOperations namedParameterJdbcTemplate;
	
	private static final String RICETTA_TABLE_NAME = "TABLE_RICETTE";

	@PostConstruct
	private void postConstruct() {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void deleteRicetteTable() {
		namedParameterJdbcTemplate.getJdbcOperations().execute("DELETE FROM " + RICETTA_TABLE_NAME  );
	}

	@Override
	public int[][] bulkSave(List<Ricetta> newRicettas) {

		String sql = "insert into "+RICETTA_TABLE_NAME
				+ " (SIGLA_FONTE,TITOLO, DESCRIZIONE, LINK_URL,IMAGE_URL,ISTRUZIONI,ID_FONTE) values (?,?, ?, ?,?,?,?)";

		return namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, newRicettas, newRicettas.size(),

				new ParameterizedPreparedStatementSetter<Ricetta>() {
					@Override
					public void setValues(PreparedStatement ps, Ricetta ricetta) throws SQLException {
						ps.setString(1, ricetta.getFonte().getSigla());
						ps.setString(2, ricetta.getTitolo());
						ps.setString(3, ricetta.getDescrizione());
						ps.setString(4, ricetta.getLinkUrl());
						ps.setString(5, ricetta.getImageUrl());
						ps.setString(6, ricetta.getIstruzioni());
						ps.setLong(7, ricetta.getId());
					}
				});
	}

	@Override
	public long save(Ricetta ricetta) {

		log.debug("Saving ricetta: " + ricetta);

		String sql = "insert into "+RICETTA_TABLE_NAME
				+ " (id,SIGLA_FONTE_FK,TITOLO, DESCRIZIONE, LINK_URL,IMAGE_URL,ISTRUZIONI,ID_FONTE)"
				+ " values ( nextval('ricette_seq'),:SIGLA_FONTE_FK,:TITOLO, :DESCRIZIONE, :LINK_URL,:IMAGE_URL,:ISTRUZIONI,:ID_FONTE)";

		KeyHolder holder = new GeneratedKeyHolder();
		//SqlParameterSource parameters = new BeanPropertySqlParameterSource(ricetta);
		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("SIGLA_FONTE_FK", ricetta.getFonte().getSigla())
    			.addValue("TITOLO",ricetta.getTitolo())
    			.addValue("DESCRIZIONE",ricetta.getDescrizione())
		        .addValue("LINK_URL",ricetta.getLinkUrl())
		        .addValue("IMAGE_URL",ricetta.getImageUrl())
		        .addValue("ISTRUZIONI",ricetta.getIstruzioni())
	   	        .addValue("ID_FONTE",ricetta.getId());
   
		String [] columnames = {"id"};
		
		
		namedParameterJdbcTemplate.update(sql, parameters, holder,columnames);
		Long newId = holder.getKey().longValue();
		log.debug("Salvata ricetta con id: " + newId);

		return newId;
	}
	
	
	public Ricetta saveAndReturn(Ricetta ricetta) {
		ricetta.setId((save(ricetta)));
		return ricetta;
	}
	

	@Override
	public Ricetta load(long id) {
		List<Ricetta> Ricettas = namedParameterJdbcTemplate.getJdbcOperations().query("select * from " + RICETTA_TABLE_NAME+ " where ID_RICETTA =?", new Object[] { id },
				(resultSet, i) -> {
					return toRicetta(resultSet);
				});

		if (Ricettas.size() == 1) {
			return Ricettas.get(0);
		}
		return null;
	}

	@Override
	public void delete(long id) {
		namedParameterJdbcTemplate.getJdbcOperations().update("delete from " +RICETTA_TABLE_NAME +" where ID_RICETTA = ?", id);
		log.debug("Cancellata ricetta con id " +id);
	}

	@Override
	public void update(Ricetta ricetta) {
		String sql = "update " + RICETTA_TABLE_NAME
				+ " SET SIGLA_FONTE= :SIGLA_FONTE,"
				+ "      TITOLO=:TITOLO,"
				+ " DESCRIZIONE=:DESCRIZIONE,"
				+ " LINK_URL=:LINK_URL,"
				+ "IMAGE_URL=:IMAGE_URL,"
				+ "ISTRUZIONI=:ISTRUZIONI,"
				+ "ID_FONTE=:ID_FONTE"
		 + " where ID_RICETTA=" +ricetta.getId() ;

		
		SqlParameterSource parameters = new MapSqlParameterSource()
				.addValue("SIGLA_FONTE", ricetta.getFonte().getSigla())
    			.addValue("TITOLO",ricetta.getTitolo())
    			.addValue("DESCRIZIONE",ricetta.getDescrizione())
		        .addValue("LINK_URL",ricetta.getLinkUrl())
		        .addValue("IMAGE_URL",ricetta.getImageUrl())
		        .addValue("ISTRUZIONI",ricetta.getIstruzioni())
	   	        .addValue("ID_FONTE",ricetta.getId());
		
		 namedParameterJdbcTemplate.update(sql, parameters);
	}

	@Override
	public List<Ricetta> loadAll() {
		return namedParameterJdbcTemplate.getJdbcOperations().query("select * from " + RICETTA_TABLE_NAME, (resultSet, i) -> {
			return toRicetta(resultSet);
		});
	}

	// SIGLA_FONTE,TITOLO, DESCRIZIONE, LINK_URL,IMAGE_URL,ISTRUZIONI,ID_FONTE
	
	private Ricetta toRicetta(ResultSet resultSet) throws SQLException {
		Ricetta ricetta = new Ricetta();
		ricetta.setId(resultSet.getLong("ID"));
		ricetta.setTitolo(resultSet.getString("TITOLO"));
		ricetta.setDescrizione(resultSet.getString("DESCRIZIONE"));
		ricetta.setLinkUrl(resultSet.getString("LINK_URL"));
		ricetta.setImageUrl(resultSet.getString("IMAGE_URL"));
		ricetta.setIstruzioni(resultSet.getString("ISTRUZIONI"));
		Fonte fonte = new Fonte();
		fonte.setSigla(resultSet.getString("SIGLA_FONTE_FK"));
		ricetta.setFonte(fonte);
		ricetta.setId(resultSet.getLong("ID_FONTE"));
		return ricetta;
	}

	@Override
	public List<Ricetta> findRicettaByTitle(String name) {
		return namedParameterJdbcTemplate.getJdbcOperations().query("select * from " +RICETTA_TABLE_NAME + " where TITOLO = ?", new Object[] { name },
				new RowMapper<Ricetta>() {
					@Override
					public Ricetta mapRow(ResultSet resultSet, int i) throws SQLException {
						return toRicetta(resultSet);
					}
				});
	}

	@Override
	public Long getRicettaCount() {
		return namedParameterJdbcTemplate.getJdbcOperations().queryForObject("select count(*) from " +RICETTA_TABLE_NAME, Long.class);

	}
	
	
	private class RicettaRowMapper implements RowMapper<Ricetta>{
		@Override
		public Ricetta mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Ricetta ricetta = new Ricetta();
			ricetta.setId(resultSet.getLong("ID_RICETTA"));
			ricetta.setTitolo(resultSet.getString("TITOLO"));
			ricetta.setDescrizione(resultSet.getString("DESCRIZIONE"));
			ricetta.setLinkUrl(resultSet.getString("LINK_URL"));
			ricetta.setImageUrl(resultSet.getString("IMAGE_URL"));
			ricetta.setIstruzioni(resultSet.getString("ISTRUZIONI"));
			Fonte fonte = new Fonte();
			fonte.setSigla(resultSet.getString("SIGLA_FONTE"));
			ricetta.setFonte(fonte);
			ricetta.setId(resultSet.getLong("ID_FONTE"));
			return ricetta;
		}
		
	}


	@Override
	public Ricetta   findRicettaByIdFonte(String fonte, String idFonte) {
		List<Ricetta> ricette = namedParameterJdbcTemplate.getJdbcOperations()
				.query("select * from " + RICETTA_TABLE_NAME+ " where SIGLA_FONTE_FK =? and ID_FONTE =?", new Object[] { fonte,Integer.valueOf(idFonte) },
				(resultSet, i) -> {
					return toRicetta(resultSet);
				});

		if (ricette.size() == 1) {
			return ricette.get(0);
		}
		return null;
	}
}