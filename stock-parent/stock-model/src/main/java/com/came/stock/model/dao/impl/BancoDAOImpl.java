package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.BancoDAO;
import com.came.stock.model.domain.Banco;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class BancoDAOImpl extends DatabaseMetaclass implements BancoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Banco banco) {
		sessionFactory.getCurrentSession().saveOrUpdate(banco);
	}

	@Transactional
	public void delete(Banco banco) {
		sessionFactory.getCurrentSession().delete(banco);
	}

	@Transactional(readOnly = true)
	public Banco getById(Long idBanco) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Banco.class);

		criteria.add(Restrictions.eq("idBanco", idBanco));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Banco> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Banco) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Banco> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Banco.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Banco> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
