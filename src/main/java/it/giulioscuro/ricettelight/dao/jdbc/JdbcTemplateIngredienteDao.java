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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import it.giulioscuro.ricettelight.dao.IngredienteDao;

import it.giulioscuro.ricettelight.model.Fonte;
import it.giulioscuro.ricettelight.model.Ingrediente;

public class JdbcTemplateIngredienteDao implements IngredienteDao {

	private static final Logger log = LoggerFactory.getLogger(JdbcTemplateRicettaDao.class);

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	private NamedParameterJdbcOperations namedParameterJdbcTemplate;
	
	private static final String INGREDIENTI_TABLE_NAME = "TABLE_INGREDIENTI";
	
    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
      
    }
    
    public void deleteIngredientiTable() {
   	 jdbcTemplate.execute("DELETE FROM INGREDIENTI");
   }
    
	@Override
	public long save(Ingrediente ingrediente) {
		
		
			String sql = "insert into " + INGREDIENTI_TABLE_NAME
					+ " (id,nome,image_url, descrizione)"
					+ " values (INGREDIENTI_SEQ.nextval,:nome,:image_url, :descrizione)";

			KeyHolder holder = new GeneratedKeyHolder();
			//SqlParameterSource parameters = new BeanPropertySqlParameterSource(ricetta);
			
			SqlParameterSource parameters = new MapSqlParameterSource()
					.addValue("nome", ingrediente.getNome())
	    			.addValue("image_url",ingrediente.getImageUrl())
	    			.addValue("descrizione",ingrediente.getDescrizione());
			       // .addValue("ID_RICETTA_FK",ingrediente.getId_ricetta());
			   
	   
			String [] columnames = {"ID"};
			
			namedParameterJdbcTemplate.update(sql, parameters, holder,columnames);
			Long newId = holder.getKey().longValue();
			log.debug("Salvato ingrediente con id: " + newId);

			return newId;
		
	}
	
	public Ingrediente saveAndReturn(Ingrediente i) {
		i.setId(save(i));
		return i;
	}
	
	
	@Override
	public Ingrediente load(String nome) {
		
		log.debug("Loading ingrediente .." + nome);
		
		List<Ingrediente> ingredienti = jdbcTemplate.query("select * from "+ INGREDIENTI_TABLE_NAME+ " where NOME =?", new Object[] { nome },
				(resultSet, i) -> {
					return toIngrediente(resultSet);
				});

		if (ingredienti.size() == 1) {
			return ingredienti.get(0);
		}
		return null;
	}
	
	
	private Ingrediente toIngrediente(ResultSet resultSet) throws SQLException {
		Ingrediente ingrediente = new Ingrediente();
		ingrediente.setId(resultSet.getLong("id"));
		ingrediente.setNome((resultSet.getString("nome")));
		ingrediente.setDescrizione(resultSet.getString("descrizione"));
		ingrediente.setImageUrl(resultSet.getString("image_url"));
		return ingrediente;
	}
	

	@Override
	public Ingrediente load(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Ingrediente t) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Ingrediente> loadAll() {
		
	String sql = "SELECT * FROM " + INGREDIENTI_TABLE_NAME;
		
		List<Ingrediente> all = (List<Ingrediente> )namedParameterJdbcTemplate.getJdbcOperations().query(
				sql, new IngredienteRowMapper());
		return all;
	}

	

	
	
	@Override
	public List<Ingrediente> findIngredienteByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getIngredientiCount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] bulkSave(List<Ingrediente> newIngredienti) {
      String sql = "insert into "+ INGREDIENTI_TABLE_NAME + "  (id,nome,image_url,descrizione ) values (?,?,?,?)";
      return jdbcTemplate.batchUpdate(sql, newIngredienti, newIngredienti.size(),
              new ParameterizedPreparedStatementSetter<Ingrediente>() {
                  @Override
                  public void setValues(PreparedStatement ps, Ingrediente ingrediente) throws SQLException {
                  	   ps.setLong(1,ingrediente.getId());
                       ps.setString(2, ingrediente.getNome());
                       ps.setString(3, ingrediente.getImageUrl());
                       ps.setString(4, ingrediente.getDescrizione());

                  }
              });
	}
	
	private class IngredienteRowMapper implements RowMapper<Ingrediente>{
		@Override
		public Ingrediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Ingrediente ingrediente = new Ingrediente();
			ingrediente.setId(resultSet.getLong("id"));
			ingrediente.setNome((resultSet.getString("nome")));
			ingrediente.setDescrizione(resultSet.getString("descrizione"));
			ingrediente.setImageUrl(resultSet.getString("image_url"));
			return ingrediente;
		}
		
	}

}
