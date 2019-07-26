package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ContratoDAO;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ContratoDAOImpl extends DatabaseMetaclass implements ContratoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Contrato contrato) {
		sessionFactory.getCurrentSession().saveOrUpdate(contrato);
	}

	@Transactional
	public void delete(Contrato contrato) {
		sessionFactory.getCurrentSession().delete(contrato);
	}

	@Transactional(readOnly = true)
	public Contrato getById(Long idContrato) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contrato.class);

		criteria.add(Restrictions.eq("idContrato", idContrato));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Contrato> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contrato) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Contrato> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contrato.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Contrato> lista = criteria.list();

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
