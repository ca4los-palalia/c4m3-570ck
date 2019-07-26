package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.DireccionDAO;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Municipio;

@Repository
public class DireccionDAOImpl extends DatabaseMetaclass implements DireccionDAO {
	@Transactional
	public void save(Direccion direccion) {
		sessionFactory.getCurrentSession().saveOrUpdate(direccion);
	}

	@Transactional
	public void update(Direccion direccion) {
		sessionFactory.getCurrentSession().update(direccion);
	}

	@Transactional(readOnly = true)
	public Direccion getById(Long direccion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("idDireccion", direccion));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Direccion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByCodigoPostalId(String codigoPostal) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("cp", codigoPostal));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByEstado(Estado estado) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("estado", estado));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getByMunicipio(Municipio municipio) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.add(Restrictions.eq("municipio", municipio));
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Direccion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Direccion.class);

		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Direccion getUltimoRegistroDireccion() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Direccion.class);

		criteria.addOrder(Order.desc("idDireccion"));
		criteria.setMaxResults(1);
		List<Direccion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Direccion) lista.get(0) : null;
	}
}
