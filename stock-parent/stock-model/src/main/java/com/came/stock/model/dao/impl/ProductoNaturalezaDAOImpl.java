package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoNaturalezaDAO;
import com.came.stock.model.domain.ProductoNaturaleza;

@Repository
public class ProductoNaturalezaDAOImpl extends DatabaseMetaclass implements ProductoNaturalezaDAO {
	@Transactional
	public void save(ProductoNaturaleza productoNaturaleza) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoNaturaleza);
	}

	@Transactional
	public void update(ProductoNaturaleza productoNaturaleza) {
		sessionFactory.getCurrentSession().update(productoNaturaleza);
	}

	@Transactional
	public void delete(ProductoNaturaleza productoNaturaleza) {
		sessionFactory.getCurrentSession().delete(productoNaturaleza);
	}

	@Transactional(readOnly = true)
	public ProductoNaturaleza getById(Long idProductoNaturaleza) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		criteria.add(Restrictions.eq("idProductoNaturaleza", idProductoNaturaleza));
		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoNaturaleza) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ProductoNaturaleza> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public ProductoNaturaleza getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoNaturaleza) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public ProductoNaturaleza getBySimbolo(String simbolo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ProductoNaturaleza.class);

		criteria.add(Restrictions.eq("simbolo", simbolo));
		List<ProductoNaturaleza> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoNaturaleza) lista.get(0) : null;
	}
}
