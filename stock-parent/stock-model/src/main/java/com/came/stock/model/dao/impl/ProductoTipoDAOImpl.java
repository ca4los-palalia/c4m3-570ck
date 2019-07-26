package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoTipoDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ProductoTipoDAOImpl extends DatabaseMetaclass implements ProductoTipoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void saveOrUpdate(ProductoTipo productoTipo) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoTipo);
	}

	@Transactional
	public void save(ProductoTipo productoTipo) {
		sessionFactory.getCurrentSession().saveOrUpdate(productoTipo);
	}

	@Transactional
	public void update(ProductoTipo productoTipo) {
		sessionFactory.getCurrentSession().update(productoTipo);
	}

	@Transactional
	public void delete(ProductoTipo productoTipo) {
		sessionFactory.getCurrentSession().delete(productoTipo);
	}

	@Transactional(readOnly = true)
	public ProductoTipo getById(Long idProductoTipo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoTipo.class);

		criteria.add(Restrictions.eq("idProductoTipo", idProductoTipo));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ProductoTipo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoTipo) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<ProductoTipo> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoTipo.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ProductoTipo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public ProductoTipo getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProductoTipo.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ProductoTipo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ProductoTipo) lista.get(0) : null;
	}
}
