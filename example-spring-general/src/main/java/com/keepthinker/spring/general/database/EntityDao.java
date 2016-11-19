package com.keepthinker.spring.general.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.keepthinker.example.model.Actor;

public class EntityDao {

	@Autowired
	private DataSource dataSource;

	public void foo(){
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource);
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource);
		DataSourceUtils.getConnection(dataSource);
	}

	public Integer bar(String firstName){

		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		String sql = "select count(*) from T_ACTOR where first_name = :first_name";
		Map<String, String> namedParameters = Collections.singletonMap("first_name", firstName);
		return template.queryForObject( sql, namedParameters,
				Integer.class);
	}

	public int[] batchWithJdbcTemplate(final List<Actor> actors){

		JdbcTemplate template = new JdbcTemplate(dataSource);
		int[] updateCounts = template.batchUpdate("update t_actor set first_name = ?, " +
				"last_name = ? where id = ?",
				new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, actors.get(i).getFirstName());
				ps.setString(2, actors.get(i).getLastName());
				ps.setLong(3, actors.get(i).getId().longValue());
			}
			public int getBatchSize() {
				return actors.size();
			}
		});
		return updateCounts;
	}

	public int[] batchWithJdbc(final List<Actor> actors){
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<Object[]> batch = new ArrayList<Object[]>();
		for (Actor actor : actors) {
			Object[] values = new Object[] {
					actor.getFirstName(),
					actor.getLastName(),
					actor.getId()};
			batch.add(values);
		}
		int[] updateCounts = jdbcTemplate.batchUpdate(
				"update t_actor set first_name = ?, last_name = ? where id = ?",
				batch);
		return updateCounts;
	}

}
