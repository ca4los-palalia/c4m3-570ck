package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.EstadoDAO;
import com.came.stock.model.domain.Estado;

@Repository
public class EstadoDAOImpl extends DatabaseMetaclass implements EstadoDAO {
	@Transactional
	public void save(Estado estado) {
		sessionFactory.getCurrentSession().save(estado);
	}

	@Transactional
	public void update(Estado estado) {
		sessionFactory.getCurrentSession().update(estado);
	}

	@Transactional(readOnly = true)
	public Estado getById(Long estado) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Estado.class);

		criteria.add(Restrictions.eq("idEstado", estado));
		List<Estado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Estado) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Estado> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Estado.class);

		List<Estado> lista = criteria.list();

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	public void delete(Estado estado) {
		sessionFactory.getCurrentSession().delete(estado);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Estado getByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Estado.class);

		criteria.add(Restrictions.eq("nombre", name));
		List<Estado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Estado) lista.get(0) : null;
	}
}
