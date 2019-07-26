package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ModulosOrganizacionDAO;
import com.came.stock.model.domain.ModulosOrganizacion;
import com.came.stock.model.domain.Organizacion;

@Repository
public class ModulosOrganizacionDAOImpl extends DatabaseMetaclass implements ModulosOrganizacionDAO {
	@Transactional
	public void delete(ModulosOrganizacion modulosOrganizacion) {
		sessionFactory.getCurrentSession().delete(modulosOrganizacion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ModulosOrganizacion> getModulosByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ModulosOrganizacion.class);
		List<ModulosOrganizacion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista: null;
		//return sessionFactory.getCurrentSession().find("FROM ModulosOrganizacion as m");
	}

	@Transactional
	public void save(ModulosOrganizacion modulosOrganizacion) {
		sessionFactory.getCurrentSession().save(modulosOrganizacion);
	}
}
