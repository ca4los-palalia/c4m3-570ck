package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.RequisicionInboxDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.RequisicionInbox;

@Repository
public class RequisicionInboxDAOImpl extends DatabaseMetaclass implements RequisicionInboxDAO {
	@Transactional
	public void save(RequisicionInbox requisicionInbox) {
		sessionFactory.getCurrentSession().saveOrUpdate(requisicionInbox);
	}

	@Transactional
	public void delete(RequisicionInbox requisicionInbox) {
		sessionFactory.getCurrentSession().delete(requisicionInbox);
	}

	@Transactional(readOnly = true)
	public List<RequisicionInbox> getAllNews(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(RequisicionInbox.class, "rib");
		criteria.createAlias("rib.requisicion", "re");
		criteria.add(Restrictions.eq("rib.leido", false));
		criteria.add(Restrictions.eq("re.organizacion", organizacion));
		
		List<RequisicionInbox> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
//		return sessionFactory.getCurrentSession().find(
//				"FROM RequisicionInbox as r LEFT JOIN FETCH r.requisicion as e WHERE r.leido = ?AND e.organizacion = ?",
//				new Object[] { Boolean.valueOf(false), organizacion });
	}

	@Transactional(readOnly = true)
	public List<RequisicionInbox> getAll(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(RequisicionInbox.class, "rib");
		criteria.createAlias("rib.requisicion", "re");
		criteria.add(Restrictions.eq("re.organizacion", organizacion));
		
		List<RequisicionInbox> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
//		return sessionFactory.getCurrentSession().find(
//				"FROM RequisicionInbox as r LEFT JOIN FETCH r.requisicion as e WHERE e.organizacion = ?", organizacion);
	}
}
