package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.FamiliasProductoDAO;
import com.came.stock.model.domain.FamiliasProducto;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTipo;

@Repository
public class FamiliasProductoDAOImpl extends DatabaseMetaclass implements FamiliasProductoDAO {
	@Transactional
	public void save(FamiliasProducto familiasProducto) {
		sessionFactory.getCurrentSession().saveOrUpdate(familiasProducto);
	}

	@Transactional
	public void update(FamiliasProducto familiasProducto) {
		sessionFactory.getCurrentSession().update(familiasProducto);
	}

	@Transactional
	public void delete(FamiliasProducto familiasProducto) {
		sessionFactory.getCurrentSession().delete(familiasProducto);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public FamiliasProducto getById(Long idFamiliasProducto) {
		List<FamiliasProducto> producto = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		criteria.add(Restrictions.eq("idFamiliasProducto", idFamiliasProducto));
		producto = criteria.list();
		return producto.size() > 0 ? (FamiliasProducto) producto.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<FamiliasProducto> getAll() {
		List<FamiliasProducto> producto = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		producto = criteria.list();
		return producto.size() > 0 ? producto : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<FamiliasProducto> getByProducto(Producto producto) {
		List<FamiliasProducto> familiasProductoList = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		familiasProductoList = criteria.list();
		return familiasProductoList.size() > 0 ? familiasProductoList : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<FamiliasProducto> getByFamilia(ProductoTipo productoTipo) {
		List<FamiliasProducto> familiasProductoList = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		criteria.add(Restrictions.eq("productoTipo", productoTipo));
		familiasProductoList = criteria.list();
		return familiasProductoList.size() > 0 ? familiasProductoList : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public FamiliasProducto getByProductoProductoTipo(Producto producto, ProductoTipo productoTipo) {
		List<FamiliasProducto> familiasProductoList = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(FamiliasProducto.class);

		criteria.add(Restrictions.eq("productoTipo", productoTipo));
		criteria.add(Restrictions.eq("producto", producto));
		familiasProductoList = criteria.list();
		return familiasProductoList.size() > 0 ? familiasProductoList.get(0) : null;
	}
}
