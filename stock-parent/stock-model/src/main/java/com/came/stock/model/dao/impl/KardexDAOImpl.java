package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.KardexDAO;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class KardexDAOImpl extends DatabaseMetaclass implements KardexDAO {
//	@Autowired
//	private SessionUtils sessionUtils;
//
//	private Organizacion getOrganizacion() {
//		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
//	}

	@Transactional
	public void save(Kardex kardex) {
		sessionFactory.getCurrentSession().saveOrUpdate(kardex);
	}

	@Transactional
	public void delete(Kardex kardex) {
		sessionFactory.getCurrentSession().delete(kardex);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Kardex getById(Long idKardex, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Kardex.class);

		criteria.add(Restrictions.eq("idKardex", idKardex));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.setMaxResults(1);
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Kardex) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getAll(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Kardex.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getByEstatus(EstatusRequisicion estatus, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Kardex.class);

		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getByKardexProveedorEstatus(KardexProveedor kardexProveedor, EstatusRequisicion estatus, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Kardex.class);
		criteria.add(Restrictions.eq("kardexProveedor", kardexProveedor));
		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getByProducto(Producto producto, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Kardex.class);
		criteria.add(Restrictions.eq("producto", producto));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Kardex> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Producto> getAllProductosNoRepetidos(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Kardex.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.setProjection(Projections.distinct(Projections.property("producto")));
		
		List<Producto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Kardex> getKardexOrderByDateMasReciente(List<Long> listaDesordenada, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Kardex.class);
		criteria.add(Restrictions.in("idKardex", listaDesordenada));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.addOrder(Order.asc("fechaEntrada"));
		criteria.addOrder(Order.asc("typeFormat"));
		List<Kardex> lista = criteria.list();
		
		for (Kardex kardex : lista) {
			System.err.println(kardex.getFechaEntrada() + ": " + kardex.getTypeFormat());
		}
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	

}
