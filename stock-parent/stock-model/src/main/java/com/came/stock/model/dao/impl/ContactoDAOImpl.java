package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ContactoDAO;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Telefono;

@Repository
public class ContactoDAOImpl extends DatabaseMetaclass implements ContactoDAO {
	@Transactional
	public void save(Contacto contacto) {
		sessionFactory.getCurrentSession().saveOrUpdate(contacto);
	}

	@Transactional
	public void update(Contacto contacto) {
		sessionFactory.getCurrentSession().update(contacto);
	}

	@Transactional
	public void delete(Contacto contacto) {
		sessionFactory.getCurrentSession().delete(contacto);
	}

	@Transactional(readOnly = true)
	public Contacto getById(Long idContacto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.add(Restrictions.eq("", idContacto));
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Contacto getByTelefono(Telefono telefono) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.add(Restrictions.eq("telefono", telefono));
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Contacto getByIdEmail(Email email) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.add(Restrictions.eq("email", email));
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Contacto> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contacto.class);

		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Contacto getUltimoRegistroContacto() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contacto.class);

		criteria.addOrder(Order.desc("idContacto"));
		criteria.setMaxResults(1);
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Contacto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Contacto getContactoByEmail(Email email) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Contacto.class);
		criteria.add(Restrictions.eq("email", email));
		criteria.setFetchMode("email", FetchMode.JOIN);
		criteria.setFetchMode("email.email", FetchMode.JOIN);
		List<Contacto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
		/*
		List<Contacto> contactos = sessionFactory.getCurrentSession()
				.find("FROM Contacto as c LEFT JOIN FETCH c.email as e WHERE c.email = ?", email);

		return contactos.size() > 0 ? (Contacto) contactos.get(0) : null;
		*/
	}
}
