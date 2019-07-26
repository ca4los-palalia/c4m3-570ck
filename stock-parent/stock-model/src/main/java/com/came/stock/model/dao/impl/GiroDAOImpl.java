package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.GiroDAO;
import com.came.stock.model.domain.Giro;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class GiroDAOImpl extends DatabaseMetaclass implements GiroDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Giro giro) {
		sessionFactory.getCurrentSession().saveOrUpdate(giro);
	}

	@Transactional
	public void delete(Giro giro) {
		sessionFactory.getCurrentSession().delete(giro);
	}

	@Transactional(readOnly = true)
	public Giro getById(Long idGiro) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Giro.class);

		criteria.add(Restrictions.eq("idGiro", idGiro));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Giro> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Giro) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Giro> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Giro.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Giro> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Giro getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Giro.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Giro> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Giro) lista.get(0) : null;
	}
}
