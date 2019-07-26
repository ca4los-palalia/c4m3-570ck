package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.KardexProveedorDAO;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class KardexProveedorDAOImpl extends DatabaseMetaclass implements KardexProveedorDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(KardexProveedor kardexProveedor) {
		sessionFactory.getCurrentSession().saveOrUpdate(kardexProveedor);
	}

	@Transactional
	public void delete(KardexProveedor kardexProveedor) {
		sessionFactory.getCurrentSession().delete(kardexProveedor);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public KardexProveedor getById(Long idKardexProveedor) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);

		criteria.add(Restrictions.eq("idKardexProveedor", idKardexProveedor));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (KardexProveedor) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<KardexProveedor> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<KardexProveedor> getByEstatus(EstatusRequisicion estatus) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);

		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<KardexProveedor> getByProveedorEstatus(Proveedor proveedor, EstatusRequisicion estatus) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(KardexProveedor.class);
		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("estatusRequisicion", estatus));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<KardexProveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

}
