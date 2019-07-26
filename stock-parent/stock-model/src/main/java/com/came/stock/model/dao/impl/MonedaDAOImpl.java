package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.MonedaDAO;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class MonedaDAOImpl extends DatabaseMetaclass implements MonedaDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Moneda moneda) {
		sessionFactory.getCurrentSession().saveOrUpdate(moneda);
	}

	@Transactional
	public void delete(Moneda moneda) {
		sessionFactory.getCurrentSession().delete(moneda);
	}

	@Transactional(readOnly = true)
	public Moneda getById(Long idMoneda) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Moneda.class);

		criteria.add(Restrictions.eq("idMoneda", idMoneda));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Moneda> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Moneda) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Moneda> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Moneda.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Moneda> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
