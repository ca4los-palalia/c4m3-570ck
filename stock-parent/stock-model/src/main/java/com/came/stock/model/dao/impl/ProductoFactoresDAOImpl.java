package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoFactoresDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoFactores;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ProductoFactoresDAOImpl extends DatabaseMetaclass implements ProductoFactoresDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ProductoFactores productoFactores) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoFactores);
	}

	@Transactional
	public void delete(ProductoFactores productoFactores) {
		sessionFactory.getCurrentSession().delete(productoFactores);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoFactores getById(Long idProductoFactores) {
		List<ProductoFactores> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoFactores.class);

		criteria.add(Restrictions.eq("idProductoFactores", idProductoFactores));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoFactores> getAll() {
		List<ProductoFactores> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoFactores.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public ProductoFactores getByDescripcionAndProducto(String descripcion, Producto producto) {
		List<ProductoFactores> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoFactores.class);

		criteria.add(Restrictions.eq("factorDescripcion", descripcion));
		criteria.add(Restrictions.eq("producto", producto));
		lista = criteria.list();
		return lista.size() > 0 ? lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ProductoFactores> getByProductoOrderMostRecentDate(Producto producto) {
		List<ProductoFactores> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoFactores.class);
		criteria.add(Restrictions.eq("producto", producto));
		criteria.addOrder(Order.desc("actualizacion"));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}
}
