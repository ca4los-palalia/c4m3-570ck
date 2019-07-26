package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.CuentasPagoDAO;
import com.came.stock.model.domain.CuentaPago;
import com.came.stock.model.domain.Proveedor;

@Repository
public class CuentasPagoDAOImpl extends DatabaseMetaclass implements CuentasPagoDAO {
	@Transactional
	public void save(CuentaPago cuentaPago) {
		sessionFactory.getCurrentSession().saveOrUpdate(cuentaPago);
	}

	@Transactional
	public void update(CuentaPago cuentaPago) {
		sessionFactory.getCurrentSession().saveOrUpdate(cuentaPago);
	}

	@Transactional
	public void delete(CuentaPago cuentaPago) {
		sessionFactory.getCurrentSession().delete(cuentaPago);
	}

	@Transactional(readOnly = true)
	public CuentaPago getById(Long idCuentaPago) {
		List<CuentaPago> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CuentaPago.class);

		criteria.add(Restrictions.eq("idCuentaPago", idCuentaPago));
		lista = criteria.list();
		return lista.size() > 0 ? (CuentaPago) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CuentaPago> getAll() {
		List<CuentaPago> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CuentaPago.class);

		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<CuentaPago> getByProveedor(Proveedor proveedor) {
		List<CuentaPago> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(CuentaPago.class);

		criteria.add(Restrictions.eq("proveedor", proveedor));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}
}
