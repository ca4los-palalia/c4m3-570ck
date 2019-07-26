package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.PresentacionDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Presentacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class PresentacionDAOImpl extends DatabaseMetaclass implements PresentacionDAO {

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}
	
	
	@Transactional
	public void save(Presentacion unidad) {
		sessionFactory.getCurrentSession().saveOrUpdate(unidad);
	}
	
	@Transactional
	public void delete(Presentacion unidad) {
		sessionFactory.getCurrentSession().delete(unidad);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Presentacion getById(Long idPresentacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Presentacion.class);
		criteria.add(Restrictions.eq("idPresentacion", idPresentacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Presentacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Presentacion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Presentacion.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Presentacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Presentacion> getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Presentacion.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Presentacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}


}
