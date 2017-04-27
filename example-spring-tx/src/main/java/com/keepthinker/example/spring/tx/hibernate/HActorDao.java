package com.keepthinker.example.spring.tx.hibernate;

import com.keepthinker.example.model.Actor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Service;

@Service
public class HActorDao extends HibernateTemplate{
	
	@Autowired
	@Override
	public void setSessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
	public Actor get(int id){
		return super.get(Actor.class, id);
	}
	
	public void save(Actor actor){
		super.save(actor);
	}

	
}
