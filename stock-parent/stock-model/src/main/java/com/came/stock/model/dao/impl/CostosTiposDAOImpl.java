package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.CostosTiposDAO;
import com.came.stock.model.domain.CostosTipos;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class CostosTiposDAOImpl extends DatabaseMetaclass implements CostosTiposDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(CostosTipos costosTipos) {
		sessionFactory.getCurrentSession().saveOrUpdate(costosTipos);
	}

	@Transactional
	public void delete(CostosTipos costosTipos) {
		sessionFactory.getCurrentSession().delete(costosTipos);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CostosTipos getById(Long idCostosTipos) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CostosTipos.class);

		criteria.add(Restrictions.eq("idCostosTipos", idCostosTipos));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CostosTipos> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<CostosTipos> getAll(boolean limitarOrganizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CostosTipos.class);

		if(limitarOrganizacion)
			criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CostosTipos> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
