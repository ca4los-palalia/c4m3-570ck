package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.PosicionDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Posicion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class PosicionDAOImpl extends DatabaseMetaclass implements PosicionDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void saveOrUpdate(Posicion posicion) {
		sessionFactory.getCurrentSession().saveOrUpdate(posicion);
	}

	@Transactional
	public void update(Posicion posicion) {
		sessionFactory.getCurrentSession().update(posicion);
	}

	@Transactional
	public void save(Posicion posicion) {
		sessionFactory.getCurrentSession().saveOrUpdate(posicion);
	}

	@Transactional
	public void delete(Posicion posicion) {
		sessionFactory.getCurrentSession().delete(posicion);
	}

	@Transactional(readOnly = true)
	public Posicion getById(Long idPosicion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Posicion.class);

		criteria.add(Restrictions.eq("idPosicion", idPosicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Posicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Posicion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Posicion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Posicion.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Posicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
