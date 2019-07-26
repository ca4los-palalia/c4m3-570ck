package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.LabelsModulosDAO;
import com.came.stock.model.domain.LabelsModulos;

@Repository
public class LabelsModulosDAOImpl extends DatabaseMetaclass implements LabelsModulosDAO {

	@Transactional
	public void save(LabelsModulos labelsModulos) {
		sessionFactory.getCurrentSession().saveOrUpdate(labelsModulos);
	}

	@Transactional
	public void delete(LabelsModulos labelsModulos) {
		sessionFactory.getCurrentSession().delete(labelsModulos);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public LabelsModulos getById(Long idLabelsModulos) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(LabelsModulos.class);

		criteria.add(Restrictions.eq("idLabelsModulos", idLabelsModulos));
		criteria.setMaxResults(1);
		List<LabelsModulos> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (LabelsModulos) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<LabelsModulos> getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(LabelsModulos.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<LabelsModulos> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<LabelsModulos> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(LabelsModulos.class);
		List<LabelsModulos> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<LabelsModulos> getByModulo(String modulo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(LabelsModulos.class);

		criteria.add(Restrictions.eq("modulo", modulo));
		List<LabelsModulos> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	
}
