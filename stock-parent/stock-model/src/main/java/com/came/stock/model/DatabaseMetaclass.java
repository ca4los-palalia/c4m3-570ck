package com.came.stock.model;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseMetaclass {

	@Autowired
	protected SessionFactory sessionFactory;
 
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
}
