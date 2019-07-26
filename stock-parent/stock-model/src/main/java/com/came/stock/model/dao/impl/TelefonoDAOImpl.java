package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.TelefonoDAO;
import com.came.stock.model.domain.Telefono;

@Repository
public class TelefonoDAOImpl extends DatabaseMetaclass implements TelefonoDAO {
	@Transactional
	public void save(Telefono telefono) {
		sessionFactory.getCurrentSession().saveOrUpdate(telefono);
	}

	@Transactional
	public void update(Telefono telefono) {
		sessionFactory.getCurrentSession().update(telefono);
	}

	@Transactional
	public void delete(Telefono telefono) {
		sessionFactory.getCurrentSession().delete(telefono);
	}

	@Transactional(readOnly = true)
	public Telefono getById(Long idTelefono) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Telefono.class);

		criteria.add(Restrictions.eq("idTelefono", idTelefono));
		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Telefono) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Telefono> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Telefono.class);

		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Telefono getUltimoregistroEmail() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Telefono.class);

		criteria.addOrder(Order.desc("idTelefono"));
		criteria.setMaxResults(1);
		List<Telefono> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Telefono) lista.get(0) : null;
	}
}
