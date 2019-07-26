package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoCostosDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ProductoCostosDAOImpl extends DatabaseMetaclass implements ProductoCostosDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ProductoCostos productoCostos) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoCostos);
	}

	@Transactional
	public void delete(ProductoCostos productoCostos) {
		sessionFactory.getCurrentSession().delete(productoCostos);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoCostos getById(Long idProductoCostos) {
		List<ProductoCostos> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoCostos.class);

		criteria.add(Restrictions.eq("idProductoCostos", idProductoCostos));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoCostos> getAll() {
		List<ProductoCostos> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoCostos.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public ProductoCostos getByDescripcionAndProducto(String descripcion, Producto producto) {
		List<ProductoCostos> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoCostos.class);

		criteria.add(Restrictions.eq("costoDescripcion", descripcion));
		criteria.add(Restrictions.eq("producto", producto));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoCostos> getByProductoOrderMostRecentDate(Producto producto) {
		List<ProductoCostos> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoCostos.class);
		criteria.add(Restrictions.eq("producto", producto));
		criteria.addOrder(Order.desc("actualizacion"));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}
}
