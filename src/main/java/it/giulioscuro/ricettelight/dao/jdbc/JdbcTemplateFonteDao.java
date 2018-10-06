package it.giulioscuro.ricettelight.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import it.giulioscuro.ricettelight.dao.FonteDao;
import it.giulioscuro.ricettelight.model.Fonte;

public class JdbcTemplateFonteDao implements FonteDao {
	
	@Autowired
	private DataSource dataSource;
	
	private NamedParameterJdbcOperations namedParameterJdbcTemplate;


	@PostConstruct
	private void postConstruct() {
		namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		
	}

	@Override
	public long save(Fonte f) {
		String sql = "INSERT INTO TABLE_SITO_FONTE (SIGLA,SITE_URL,SITE_ICON) VALUES (?,?,?)";
		return namedParameterJdbcTemplate.getJdbcOperations().update(sql, new Object[] {f.getSigla(),f.getUrl(),f.getIcon()});
	}

	@Override
	public Fonte load(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		namedParameterJdbcTemplate.getJdbcOperations().execute("DELETE FROM TABLE_SITO_FONTE"  );
		
	}

	@Override
	public void update(Fonte t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Fonte> loadAll() {
		
		String sql = "SELECT * FROM TABLE_SITO_FONTE";
		
		List<Fonte> all = (List<Fonte> )namedParameterJdbcTemplate.getJdbcOperations().query(
				sql, new FonteRowMapper());
		return all;
	}

	@Override
	public List<Fonte> findIngredienteBySigla(String sigla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getRowCount() {
		  String sql = "select count(*) from TABLE_SITO_FONTE"; 
		  return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(sql,Long.class);  
	}

	@Override
	public Fonte load(String sigla) {
		String sql = "SELECT * FROM TABLE_SITO_FONTE WHERE SIGLA = ?";
	
		Fonte fonte = (Fonte)namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
				sql, new Object[] { sigla }, new FonteRowMapper());
		
		return fonte;
		
		
	}
	
	
	
	private class FonteRowMapper implements RowMapper<Fonte>{
		@Override
		public Fonte mapRow(ResultSet rs, int rowNum) throws SQLException {
			Fonte sitoFonte = new Fonte();
			sitoFonte.setSigla(rs.getString("SIGLA"));
			sitoFonte.setUrl(rs.getString("SITE_URL"));
			sitoFonte.setIcon(rs.getString("SITE_ICON"));
			return sitoFonte;
		}
		
	}



}
