package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.MunicipioDAO;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Municipio;

@Repository
public class MunicipioDAOImpl extends DatabaseMetaclass implements MunicipioDAO {
	@Transactional
	public void save(Municipio municipio) {
		sessionFactory.getCurrentSession().save(municipio);
	}

	@Transactional
	public void update(Municipio municipio) {
		sessionFactory.getCurrentSession().update(municipio);
	}

	@Transactional
	public void delete(Municipio municipio) {
		sessionFactory.getCurrentSession().delete(municipio);
	}

	@Transactional(readOnly = true)
	public Municipio getById(Long idMunicipio) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Municipio.class);

		criteria.add(Restrictions.eq("idMunicipio", idMunicipio));
		List<Municipio> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Municipio) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Municipio> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Municipio.class);

		List<Municipio> lista = criteria.list();

		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Municipio> getByEstado(Estado estado) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Municipio.class);

		criteria.add(Restrictions.eq("estado", estado));
		List<Municipio> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Municipio getByName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Municipio.class);

		criteria.add(Restrictions.eq("nombre", name));
		List<Municipio> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Municipio) lista.get(0) : null;
	}
	
}
