package it.giulioscuro.ricettelight.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

import it.giulioscuro.ricettelight.dao.IngredienteDao;
import it.giulioscuro.ricettelight.dao.RicettaIngredienteDao;

import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.model.Ingrediente;
import it.giulioscuro.ricettelight.model.Ricetta;
import it.giulioscuro.ricettelight.model.RicettaIngrediente;

public class JdbcTemplateRicettaIngredienteDao implements RicettaIngredienteDao {

	private static final Logger log = LoggerFactory.getLogger(JdbcTemplateRicettaIngredienteDao.class);

	@Autowired
	private DataSource dataSource;

	private NamedParameterJdbcOperations namedParameterJdbcTemplate;

	private static final String RICETTA_TABLE_NAME = "TABLE_RICETTE_INGREDIENTI";

	@PostConstruct
	private void postConstruct() {

		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public void deleteRicetteTable() {
		namedParameterJdbcTemplate.getJdbcOperations().execute("DELETE FROM " + RICETTA_TABLE_NAME);
		namedParameterJdbcTemplate.getJdbcOperations().execute("DELETE FROM TABLE_INGREDIENTI" );
		namedParameterJdbcTemplate.getJdbcOperations().execute("DELETE FROM TABLE_RICETTE" );
	}

	@Override
	public int[][] bulkSave(List<RicettaIngrediente> newRicettas) {

		String sql = "insert into " + RICETTA_TABLE_NAME
				+ " (ID_RICETTA,ID_INGREDIENTE, QUANTITA_ESTESA,QUANTITA, UNITA_DI_MISURA) values (?,?,?,?,?)";

		return namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, newRicettas, newRicettas.size(),

				new ParameterizedPreparedStatementSetter<RicettaIngrediente>() {
					@Override
					public void setValues(PreparedStatement ps, RicettaIngrediente ricetta) throws SQLException {
						ps.setLong(1, ricetta.getIdRicetta());
						ps.setLong(2, ricetta.getIdIngrediente());
						ps.setString(3, ricetta.getQuantitaEsetesa());
						ps.setInt(4, ricetta.getQuantita());
						ps.setString(5, ricetta.getUnitaDiMisura());

					}
				});
	}

	//
	public int[][] bulkSave(Set<RicettaIngrediente> newRicettas) {

		String sql = "insert into " + RICETTA_TABLE_NAME
				+ " (ID_RICETTA,ID_INGREDIENTE, QUANTITA_ESTESA,QUANTITA, UNITA_DI_MISURA) values (?,?,?,?,?)";

		return namedParameterJdbcTemplate.getJdbcOperations().batchUpdate(sql, newRicettas, newRicettas.size(),

				new ParameterizedPreparedStatementSetter<RicettaIngrediente>() {
					@Override
					public void setValues(PreparedStatement ps, RicettaIngrediente ricetta) throws SQLException {
						ps.setLong(1, ricetta.getIdRicetta());
						ps.setLong(2, ricetta.getIdIngrediente());
						ps.setString(3, ricetta.getQuantitaEsetesa());
						ps.setInt(4, ricetta.getQuantita());
						ps.setString(5, ricetta.getUnitaDiMisura());

					}
				});
	}

	@Override
	public long save(RicettaIngrediente ricetta) {

		return -1;
	}

	@Override
	public RicettaIngrediente load(long id) {

		return null;
	}

	@Override
	public void delete(long id) {
		namedParameterJdbcTemplate.getJdbcOperations()
				.update("delete from " + RICETTA_TABLE_NAME + " where ID_RICETTA = ?", id);
		log.debug("Cancellata ricetta con id " + id);
	}

	@Override
	public void update(RicettaIngrediente ricetta) {

	}

	// SIGLA_FONTE,TITOLO, DESCRIZIONE, LINK_URL,IMAGE_URL,ISTRUZIONI,ID_FONTE

	private Ricetta toRicetta(ResultSet resultSet) throws SQLException {
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

	@Override
	public Long getRicettaCount() {
		return namedParameterJdbcTemplate.getJdbcOperations()
				.queryForObject("select count(*) from " + RICETTA_TABLE_NAME, Long.class);

	}

	@Override
	public List<RicettaIngrediente> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Ricetta> findByIngredients(String... ingredientNames) {
		MapSqlParameterSource parameters = new MapSqlParameterSource();

		ArrayList<String> names = new ArrayList<String>();
		for (String name : ingredientNames)
			names.add(name);

		parameters.addValue("nomi", names);

		String sql = "with cte as ("
				+ "select id_ricetta , count(*) as cnt from TABLE_RICETTE_INGREDIENTI where id_ingrediente "
				+ "IN (SELECT i.ID FROM TABLE_INGREDIENTI i  WHERE NOME in(:nomi) )"
				+ " group by id_ricetta) select * from TABLE_RICETTE r join cte c"
				+ " on r.id = c.id_ricetta order by c.cnt desc";

		List<Ricetta> result = namedParameterJdbcTemplate.query(sql, parameters, new RicettaRowMapper());

		return result;
	}

	public List<Ricetta> findByIngredients(List<Ingrediente> ingredients) {

		ArrayList<Long> idList = new ArrayList<Long>();

		for (Ingrediente ing : ingredients) {
			idList.add(ing.getId());
		}

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("list", idList);

		String sql = "with cte as ("
				+ "select id_ricetta , count(*) as cnt from TABLE_RICETTE_INGREDIENTI where id_ingrediente "
				+ "IN (:list)" + " group by id_ricetta) select r.id , r.titolo, c.cnt from TABLE_RICETTE r join cte c"
				+ " on r.id = c.id_ricetta order by c.cnt desc";

		List<Ricetta> result = namedParameterJdbcTemplate.query(sql, parameters, new RicettaRowMapper());

		return result;
	}

	public List<Ricetta> findByIngredients(long[] ids) {

		ArrayList<Long> idList = new ArrayList<Long>();

		for (long id : ids) {
			idList.add(id);
		}

		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("list", idList);

		String sql = "with cte as ("
				+ "select id_ricetta , count(*) as cnt from TABLE_RICETTE_INGREDIENTI where id_ingrediente "
				+ "IN (:list)" + " group by id_ricetta) select r.id , r.titolo, c.cnt from TABLE_RICETTE r join cte c"
				+ " on r.id = c.id_ricetta order by c.cnt desc";

		List<Ricetta> result = namedParameterJdbcTemplate.query(sql, parameters, new RicettaRowMapper());

		return result;
	}

	public List<Ingrediente> getIngredients(long idRicetta) {

		String sql = "  select i.nome, ri.quantita_estesa  from table_ricette_ingredienti ri"
				+ "    join table_ingredienti i on ri.id_ingrediente=i.id" + "    where id_ricetta =?";

		List<Ingrediente> all = (List<Ingrediente>) namedParameterJdbcTemplate.getJdbcOperations().query(sql,
				new IngredienteRowMapper(), idRicetta);
		return all;
	}
	
	private class RicettaRowMapper implements RowMapper<Ricetta> {
		@Override
		public Ricetta mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Ricetta ricetta = new Ricetta();
			ricetta.setId(resultSet.getLong("ID"));
			ricetta.setTitolo(resultSet.getString("TITOLO"));
			 ricetta.setDescrizione(resultSet.getString("DESCRIZIONE"));
			 ricetta.setLinkUrl(resultSet.getString("LINK_URL"));
			 ricetta.setImageUrl(resultSet.getString("IMAGE_URL"));
			  ricetta.setIstruzioni(resultSet.getString("ISTRUZIONI"));
			  Fonte fonte = new Fonte();
			  fonte.setSigla(resultSet.getString("SIGLA_FONTE_FK"));
			  ricetta.setFonte(fonte); ricetta.setId(resultSet.getLong("ID_FONTE"));
			 
			return ricetta;
		}

	}

	private class IngredienteRowMapper implements RowMapper<Ingrediente> {
		@Override
		public Ingrediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Ingrediente i = new Ingrediente();
			 // i.setId(resultSet.getLong("ID"));
			  i.setNome(resultSet.getString("NOME"));
			// ricetta.setTitolo(resultSet.getString("TITOLO"));
			/*
			 * ricetta.setDescrizione(resultSet.getString("DESCRIZIONE"));
			 * ricetta.setLinkUrl(resultSet.getString("LINK_URL"));
			 * ricetta.setImageUrl(resultSet.getString("IMAGE_URL"));
			 * ricetta.setIstruzioni(resultSet.getString("ISTRUZIONI")); Fonte fonte = new
			 * Fonte(); fonte.setSigla(resultSet.getString("SIGLA_FONTE"));
			 * ricetta.setFonte(fonte); ricetta.setId(resultSet.getLong("ID_FONTE"));
			 */
			return i;
		}

	
	}
}
