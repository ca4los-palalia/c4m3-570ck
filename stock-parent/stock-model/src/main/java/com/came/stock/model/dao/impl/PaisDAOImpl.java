package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.PaisDAO;
import com.came.stock.model.domain.Pais;

@Repository
public class PaisDAOImpl extends DatabaseMetaclass implements PaisDAO {
	@Transactional
	public void save(Pais pais) {
		sessionFactory.getCurrentSession().save(pais);
	}

	@Transactional
	public void delete(Pais pais) {
		sessionFactory.getCurrentSession().delete(pais);
	}

	@Transactional(readOnly = true)
	public List<Pais> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Pais.class);

		List<Pais> lista = criteria.list();

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Pais findById(Long idPais) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Pais.class);

		criteria.add(Restrictions.eq("idPais", idPais));
		List<Pais> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Pais) lista.get(0) : null;
	}
}
