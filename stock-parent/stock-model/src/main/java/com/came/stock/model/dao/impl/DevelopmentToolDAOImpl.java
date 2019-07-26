package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.DevelopmentToolDAO;
import com.came.stock.model.domain.DevelopmentTool;

@Repository
public class DevelopmentToolDAOImpl extends DatabaseMetaclass implements DevelopmentToolDAO {
	@Transactional
	public void save(DevelopmentTool developmentTool) {
		sessionFactory.getCurrentSession().saveOrUpdate(developmentTool);
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DevelopmentTool getById(Long developmentTool) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);

		criteria.add(Restrictions.eq("idDevelopmentTool", developmentTool));
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (DevelopmentTool) lista.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<DevelopmentTool> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DevelopmentTool getByValue(String value) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);

		criteria.add(Restrictions.eq("value", value));
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (DevelopmentTool) lista.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DevelopmentTool getByDescripcion(String descripcion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);

		criteria.add(Restrictions.eq("descripcion", descripcion));
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (DevelopmentTool) lista.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<DevelopmentTool> getCountLayouts() {
		
		Integer rows = 0;
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);
		criteria.add(Restrictions.like("value", "%xls%"));
		//criteria.setProjection(Projections.rowCount());
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DevelopmentTool getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (DevelopmentTool) lista.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DevelopmentTool getBussy(String bussy) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(DevelopmentTool.class);

		criteria.add(Restrictions.eq("nombre", bussy));
		List<DevelopmentTool> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (DevelopmentTool) lista.get(0) : null;
	}
}
