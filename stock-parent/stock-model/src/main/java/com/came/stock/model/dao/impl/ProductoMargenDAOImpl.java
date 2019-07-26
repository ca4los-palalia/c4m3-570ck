package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoMargenDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoMargen;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ProductoMargenDAOImpl extends DatabaseMetaclass implements ProductoMargenDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ProductoMargen productoMargen) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoMargen);
	}

	@Transactional
	public void delete(ProductoMargen productoMargen) {
		sessionFactory.getCurrentSession().delete(productoMargen);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoMargen getById(Long idProductoMargen) {
		List<ProductoMargen> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoMargen.class);

		criteria.add(Restrictions.eq("idProductoMargen", idProductoMargen));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoMargen> getAll() {
		List<ProductoMargen> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoMargen.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public ProductoMargen getByDescripcionAndProducto(String descripcion, Producto producto) {
		List<ProductoMargen> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoMargen.class);

		criteria.add(Restrictions.eq("margenDescripcion", descripcion));
		criteria.add(Restrictions.eq("producto", producto));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoMargen> getByProductoOrderMostRecentDate(Producto producto) {
		List<ProductoMargen> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoMargen.class);
		criteria.add(Restrictions.eq("producto", producto));
		criteria.addOrder(Order.desc("actualizacion"));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}
}
