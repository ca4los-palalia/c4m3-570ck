package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProveedorProductoDAO;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorProducto;

@Repository
public class ProveedorProductoDAOImpl extends DatabaseMetaclass implements ProveedorProductoDAO {
	@Transactional
	public void save(ProveedorProducto proveedorProducto) {
		sessionFactory.getCurrentSession().save(proveedorProducto);
	}

	@Transactional
	public void update(ProveedorProducto proveedorProducto) {
		sessionFactory.getCurrentSession().update(proveedorProducto);
	}

	@Transactional
	public void delete(ProveedorProducto proveedorProducto) {
		sessionFactory.getCurrentSession().delete(proveedorProducto);
	}

	@Transactional(readOnly = true)
	public ProveedorProducto getById(Long idProveedorProducto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("idProveedorProdcuto", idProveedorProducto));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProveedorProducto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getByProveedor(Proveedor Proveedor) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("proveedor", Proveedor));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getByProducto(Producto producto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.addOrder(Order.desc("idProveedorProdcuto"));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ProveedorProducto> getByProductoProveedor(Producto producto, Proveedor proveedor) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProveedorProducto.class);

		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("producto", producto));
		List<ProveedorProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
