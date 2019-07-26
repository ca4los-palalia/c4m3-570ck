package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.JustificacionDAO;
import com.came.stock.model.domain.Justificacion;

@Repository
public class JustificacionDAOImpl extends DatabaseMetaclass implements JustificacionDAO {

	@Transactional
	public void save(Justificacion justificacion) {
		sessionFactory.getCurrentSession().saveOrUpdate(justificacion);
	}

	@Transactional
	public void delete(Justificacion justificacion) {
		sessionFactory.getCurrentSession().delete(justificacion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Justificacion getById(Long idJustificacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Justificacion.class);

		criteria.add(Restrictions.eq("idJustificacion", idJustificacion));
		List<Justificacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Justificacion) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Justificacion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Justificacion.class);
		List<Justificacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Justificacion getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Justificacion.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<Justificacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Justificacion) lista.get(0) : null;
	}
}
