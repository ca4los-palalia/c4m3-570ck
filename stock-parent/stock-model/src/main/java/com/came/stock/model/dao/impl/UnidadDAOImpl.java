package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.UnidadDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class UnidadDAOImpl extends DatabaseMetaclass implements UnidadDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Unidad unidad) {
		sessionFactory.getCurrentSession().saveOrUpdate(unidad);
	}

	@Transactional
	public void update(Unidad unidad) {
		sessionFactory.getCurrentSession().update(unidad);
	}

	@Transactional
	public void delete(Unidad unidad) {
		sessionFactory.getCurrentSession().delete(unidad);
	}

	@Transactional(readOnly = true)
	public Unidad getById(Long idUnidad) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("idUnidad", idUnidad));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Unidad) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Unidad> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Unidad getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Unidad) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Unidad> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Unidad> getByUsuario(Usuarios usuario) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Unidad.class);

		criteria.add(Restrictions.eq("usuario", usuario));
		List<Unidad> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
