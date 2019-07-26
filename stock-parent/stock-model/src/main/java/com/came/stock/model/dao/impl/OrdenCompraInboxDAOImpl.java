package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.OrdenCompraInboxDAO;
import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.Organizacion;

@Repository
public class OrdenCompraInboxDAOImpl extends DatabaseMetaclass implements OrdenCompraInboxDAO {
	@Transactional
	public void save(OrdenCompraInbox ordenCompraInbox) {
		sessionFactory.getCurrentSession().saveOrUpdate(ordenCompraInbox);
	}

	@Transactional
	public void delete(OrdenCompraInbox ordenCompraInbox) {
		sessionFactory.getCurrentSession().delete(ordenCompraInbox);
	}

	@Transactional(readOnly = true)
	public List<OrdenCompraInbox> getAllNews(Organizacion organizacion) {
//		return sessionFactory.getCurrentSession().find(
//				"FROM OrdenCompraInbox as o LEFT JOIN FETCH o.ordenCompra as c LEFT JOIN FETCH c.cotizacion as k LEFT JOIN FETCH k.proveedor as p WHERE o.leido = ? AND c.organizacion = ?",
//				new Object[] { Boolean.valueOf(false), organizacion });
				
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompraInbox.class, "ordenCompraInboxTabla");
		criteria.createAlias("ordenCompraInboxTabla.ordenCompra", "ordenCompra");
		criteria.createAlias("ordenCompra.cotizacion", "cotizacion");
		criteria.createAlias("cotizacion.proveedor", "proveedor");
		
//		criteria.setFetchMode("ordenCompraInboxTabla", FetchMode.JOIN);
//		criteria.setFetchMode("ordenCompraInboxTabla", FetchMode.JOIN)
		
		criteria.add(Restrictions.eq("ordenCompraInboxTabla.leido", false));
		criteria.add(Restrictions.eq("ordenCompra.organizacion", organizacion));
		
		List<OrdenCompraInbox> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<OrdenCompraInbox> getAll(Organizacion organizacion) {
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompraInbox.class, "ordenCompraInboxTabla");
		criteria.createAlias("ordenCompraInboxTabla.ordenCompra", "ordenCompra");
		criteria.createAlias("ordenCompra.cotizacion", "cotizacion");
		criteria.createAlias("ordenCompra.proveedor", "proveedor");
		
		criteria.add(Restrictions.eq("ordenCompraInboxTabla.leido", false));
		criteria.add(Restrictions.eq("cotizacion.organizacion", organizacion));
		
		List<OrdenCompraInbox> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
		
		
//		return sessionFactory.getCurrentSession().find(
//				"FROM OrdenCompraInbox as o LEFT JOIN FETCH o.ordenCompra as c LEFT JOIN FETCH c.cotizacion as k LEFT JOIN FETCH k.proveedor as p WHERE c.organizacion = ?",
//				organizacion);
	}
}
