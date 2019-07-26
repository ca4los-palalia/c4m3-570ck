package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.CotizacionInboxDAO;
import com.came.stock.model.domain.CotizacionInbox;
import com.came.stock.model.domain.Organizacion;

@Repository
public class CotizacionInboxDAOImpl extends DatabaseMetaclass implements CotizacionInboxDAO {
	
	
	@Transactional
	public void save(CotizacionInbox cotizacionInbox) {
		sessionFactory.getCurrentSession().saveOrUpdate(cotizacionInbox);
	}

	@Transactional
	public void delete(CotizacionInbox cotizacionInbox) {
		sessionFactory.getCurrentSession().delete(cotizacionInbox);
	}

	@Transactional(readOnly = true)
	public List<CotizacionInbox> getAllNews(Organizacion organizacion) {
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CotizacionInbox.class, "cib");
		criteria.createAlias("cib.cotizacion", "c");
		criteria.createAlias("c.proveedor", "p");
		criteria.createAlias("p.direccionFiscal", "d");
		criteria.createAlias("d.pais", "pais");
		criteria.createAlias("d.estado", "estado");
		criteria.createAlias("d.municipio", "municipio");
		criteria.createAlias("c.requisicion", "requisicion");
		
		criteria.add(Restrictions.eq("cib.leido", false));
		criteria.add(Restrictions.eq("c.organizacion", organizacion));
		
		List<CotizacionInbox> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
//		return sessionFactory.getCurrentSession().find(
//				"FROM CotizacionInbox as cib "
//				+ "LEFT JOIN FETCH cib.cotizacion as c "
//				+ "LEFT JOIN FETCH c.proveedor as p "
//				+ "LEFT JOIN FETCH p.direccionFiscal as d "
//				+ "LEFT JOIN FETCH d.pais as pais "
//				+ "LEFT JOIN FETCH d.estado as e "
//				+ "LEFT JOIN FETCH d.municipio as m "
//				+ "LEFT JOIN FETCH c.requisicion as r "
//				+ "WHERE cib.leido = ? "
//				+ "AND c.organizacion = ?",
//				new Object[] { Boolean.valueOf(false), organizacion });
	}

	@Transactional(readOnly = true)
	public List<CotizacionInbox> getAll(Organizacion organizacion) {
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CotizacionInbox.class, "cib");
		criteria.createAlias("cib.cotizacion", "c");
		criteria.createAlias("c.requisicion", "requisicion");
		criteria.createAlias("c.proveedor", "p");
		criteria.createAlias("p.direccionFiscal", "d");
		criteria.createAlias("d.pais", "pais");
		criteria.createAlias("d.estado", "estado");
		criteria.createAlias("d.municipio", "municipio");
		criteria.add(Restrictions.eq("c.organizacion", organizacion));
		
		List<CotizacionInbox> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
		
//		return sessionFactory.getCurrentSession().find(
//				"FROM CotizacionInbox as c LEFT JOIN FETCH c.cotizacion as o LEFT JOIN FETCH o.proveedor as p LEFT JOIN FETCH p.direccionFiscal as d LEFT JOIN FETCH d.pais as a LEFT JOIN FETCH d.estado as e LEFT JOIN FETCH d.municipio as m LEFT JOIN FETCH o.requisicion as r WHERE o.organizacion = ? ",
//				organizacion);
	}
}
