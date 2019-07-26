package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.OrganizacionDAO;
import com.came.stock.model.domain.Organizacion;

@Repository
public class OrganizacionDAOImpl extends DatabaseMetaclass implements OrganizacionDAO {
	@Transactional
	public void save(Organizacion organizacion) {
		sessionFactory.getCurrentSession().saveOrUpdate(organizacion);
	}

	@Transactional
	public void delete(Organizacion organizacion) {
		sessionFactory.getCurrentSession().delete(organizacion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getOrganizaciones() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.eq("proveedor", false));
		return criteria.list();
		//return sessionFactory.getCurrentSession().find("FROM Organizacion as o");
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByNombreRFC(String compania, String rfc) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.like("nombre", "%" + compania + "%"));
		criteria.add(Restrictions.like("rfc", "%" + rfc + "%"));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByNombre(String compania) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.like("nombre", "%" + compania + "%"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getCompaniasByRFC(String rfc) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.like("rfc", "%" + rfc + "%"));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getAll() {
		//return sessionFactory.getCurrentSession().find("FROM Organizacion as o");
		List<Organizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.eq("proveedor", false));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Organizacion getById(Long idOrganizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);

		criteria.add(Restrictions.eq("idOrganizacion", idOrganizacion));
		List<Organizacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Organizacion) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Organizacion> getAllProveedores() {
		List<Organizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);
		criteria.add(Restrictions.eq("proveedor", true));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}
	
	@Transactional(readOnly = true)
	public Integer getCountRows() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Organizacion.class);
		criteria.add( Restrictions.isNotNull("idOrganizacion"));
		criteria.setProjection(Projections.rowCount());
		int count = Integer.parseInt(String.valueOf(criteria.uniqueResult()));
		return count;
	}
}
