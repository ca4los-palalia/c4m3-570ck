package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.CalculosCostoDAO;
import com.came.stock.model.domain.CalculosCosto;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class CalculosCostoDAOImpl extends DatabaseMetaclass implements CalculosCostoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(CalculosCosto calculosCosto) {
		sessionFactory.getCurrentSession().saveOrUpdate(calculosCosto);
	}

	@Transactional
	public void delete(CalculosCosto calculosCosto) {
		sessionFactory.getCurrentSession().delete(calculosCosto);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public CalculosCosto getById(Long idCalculosCosto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CalculosCosto.class);

		criteria.add(Restrictions.eq("idCalculosCosto", idCalculosCosto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<CalculosCosto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (CalculosCosto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<CalculosCosto> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CalculosCosto.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<CalculosCosto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
