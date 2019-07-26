package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoPreciosDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ProductoPreciosDAOImpl extends DatabaseMetaclass implements ProductoPreciosDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ProductoPrecios productoPrecios) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoPrecios);
	}

	@Transactional
	public void delete(ProductoPrecios productoPrecios) {
		sessionFactory.getCurrentSession().delete(productoPrecios);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoPrecios getById(Long idProductoPrecios) {
		List<ProductoPrecios> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoPrecios.class);

		criteria.add(Restrictions.eq("idProductoPrecios", idProductoPrecios));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoPrecios> getAll() {
		List<ProductoPrecios> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoPrecios.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public ProductoPrecios getByDescripcionAndProducto(String descripcion, Producto producto) {
		List<ProductoPrecios> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoPrecios.class);

		criteria.add(Restrictions.eq("precioDescripcion", descripcion));
		criteria.add(Restrictions.eq("producto", producto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoPrecios> getByProductoOrderMostRecentDate(Producto producto) {
		List<ProductoPrecios> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoPrecios.class);
		criteria.add(Restrictions.eq("producto", producto));
		//criteria.addOrder(Order.desc("actualizacion"));
		criteria.addOrder(Order.asc("precioDescripcion"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}
}
