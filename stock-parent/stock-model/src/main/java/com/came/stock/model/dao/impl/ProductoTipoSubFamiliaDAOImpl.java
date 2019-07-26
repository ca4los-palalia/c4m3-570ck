package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoTipoSubFamiliaDAO;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.ProductoTipoSubFamilia;

@Repository
public class ProductoTipoSubFamiliaDAOImpl extends DatabaseMetaclass implements ProductoTipoSubFamiliaDAO {

	@Transactional
	public void save(ProductoTipoSubFamilia productoTipoSubFamilia) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoTipoSubFamilia);	
	}

	@Transactional
	public void delete(ProductoTipoSubFamilia productoTipoSubFamilia) {
		sessionFactory.getCurrentSession().delete(productoTipoSubFamilia);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoTipoSubFamilia getById(Long idProductoTipoSubFamilia) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoTipoSubFamilia.class);
		criteria.add(Restrictions.eq("idProductoTipoSubFamilia", idProductoTipoSubFamilia));
		List<ProductoTipoSubFamilia> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ProductoTipoSubFamilia> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoTipoSubFamilia.class);
		List<ProductoTipoSubFamilia> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProductoTipoSubFamilia getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoTipoSubFamilia.class);
		criteria.add(Restrictions.eq("nombre", nombre));
		List<ProductoTipoSubFamilia> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ProductoTipoSubFamilia> getByProductoTipo(ProductoTipo productoTipo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoTipoSubFamilia.class);
		criteria.add(Restrictions.eq("productoTipo", productoTipo));
		List<ProductoTipoSubFamilia> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
