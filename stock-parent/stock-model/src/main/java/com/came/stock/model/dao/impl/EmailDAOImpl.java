package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.EmailDAO;
import com.came.stock.model.domain.Email;

@Repository
public class EmailDAOImpl extends DatabaseMetaclass implements EmailDAO {
	@Transactional
	public void save(Email email) {
		sessionFactory.getCurrentSession().saveOrUpdate(email);
	}

	@Transactional
	public void update(Email email) {
		sessionFactory.getCurrentSession().saveOrUpdate(email);
	}

	@Transactional
	public void delete(Email email) {
		sessionFactory.getCurrentSession().delete(email);
	}

	@Transactional(readOnly = true)
	public Email getById(Long idEmail) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Email.class);

		criteria.add(Restrictions.eq("idEmail", idEmail));
		List<Email> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Email) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Email> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Email.class);

		List<Email> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Email getUltimoRegistroEmail() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Email.class);

		criteria.addOrder(Order.desc("idEmails"));
		criteria.setMaxResults(1);
		List<Email> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Email) lista.get(0) : null;
	}
}
