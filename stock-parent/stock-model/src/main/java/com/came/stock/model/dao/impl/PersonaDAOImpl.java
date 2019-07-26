package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.PersonaDAO;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Persona;

@Repository
public class PersonaDAOImpl extends DatabaseMetaclass implements PersonaDAO {
	@Transactional
	public void save(Persona persona) {
		sessionFactory.getCurrentSession().saveOrUpdate(persona);
	}

	@Transactional
	public void update(Persona persona) {
		sessionFactory.getCurrentSession().update(persona);
	}

	@Transactional
	public void delete(Persona persona) {
		sessionFactory.getCurrentSession().delete(persona);
	}

	@Transactional(readOnly = true)
	public Persona getById(Long persona) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Persona.class);

		criteria.add(Restrictions.eq("idPersona", persona));
		List<Persona> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Persona) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Persona> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Persona.class);

		List<Persona> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Persona> getBySexo(Long sexo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Persona.class);

		criteria.add(Restrictions.eq("sexo", sexo));
		List<Persona> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Persona> getByDireccion(Direccion direccion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Persona.class);

		criteria.add(Restrictions.eq("direccion", direccion));
		List<Persona> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Persona> getByContacto(Contacto contacto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Persona.class);

		criteria.add(Restrictions.eq("contacto", contacto));
		List<Persona> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Persona getUltimoRegistroPersona() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Persona.class);

		criteria.addOrder(Order.desc("idPersona"));
		criteria.setMaxResults(1);
		List<Persona> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Persona) lista.get(0) : null;
	}
}
