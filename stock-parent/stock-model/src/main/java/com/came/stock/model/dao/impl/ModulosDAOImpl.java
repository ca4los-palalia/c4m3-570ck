package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ModulosDAO;
import com.came.stock.model.domain.Modulos;

@Repository
public class ModulosDAOImpl extends DatabaseMetaclass implements ModulosDAO {
	@Transactional
	public void save(Modulos modulos) {
		sessionFactory.getCurrentSession().saveOrUpdate(modulos);
	}

	@Transactional
	public void delete(Modulos modulos) {
		sessionFactory.getCurrentSession().delete(modulos);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Modulos> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Modulos.class);
		List<Modulos> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		//return sessionFactory.getCurrentSession().find("FROM Modulos as m");
	}
}
