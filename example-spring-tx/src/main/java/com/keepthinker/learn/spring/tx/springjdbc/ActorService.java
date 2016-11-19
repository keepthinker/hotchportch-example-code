package com.keepthinker.example.spring.tx.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepthinker.example.model.Actor;


@Service
public class ActorService {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Long getCount(int id){
        return jdbcTemplate.queryForObject("select count(*) from actor", Long.class);
	}
	
	@Transactional
	public void save(Actor actor){
		jdbcTemplate.update("insert into actor(id, firstname, lastname) values(?, ?, ?)",
				actor.getId(), actor.getFirstName(), actor.getLastName());
	}

	public Actor find(int id){
		return jdbcTemplate.queryForObject("select id, firstname firstName, lastname lastName from actor where id = ?", 
				new BeanPropertyRowMapper<Actor>(Actor.class), id);
	}
}
