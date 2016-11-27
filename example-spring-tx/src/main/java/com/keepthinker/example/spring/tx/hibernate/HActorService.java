package com.keepthinker.example.spring.tx.hibernate;

import java.math.BigInteger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keepthinker.example.model.Actor;

@Service
public class HActorService {

	@Autowired
	private HActorDao hActorDao;

	@Transactional(readOnly = true)
	public Long getCount(){
		return hActorDao.execute(new HibernateCallback<Long>(){
			@Override
			public Long doInHibernate(Session session) throws HibernateException {
				return ((BigInteger) session.createSQLQuery("select count(*) from actor").list().get(0)).longValue();
			}
		});

	}

	@Transactional
	public void save(Actor actor){
		hActorDao.save(actor);
	}
	
	@Transactional(readOnly = true)
	public Actor get(int id){
		return hActorDao.get(id);
	}

	@Transactional
	public void saveAndPrint(Actor actor){
		save(actor);
		System.out.println(get(actor.getId()));
		
	}
}
