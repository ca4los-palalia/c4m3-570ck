package com.came.stock.model.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.CotizacionDAO;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.services.EstatusRequisicionService;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class CotizacionDAOImpl extends DatabaseMetaclass implements CotizacionDAO {
	@Autowired
	private EstatusRequisicionService estatusRequisicionService;
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Cotizacion cotizacion) {
		sessionFactory.getCurrentSession().saveOrUpdate(cotizacion);
	}

	@Transactional
	public void update(Cotizacion cotizacion) {
		sessionFactory.getCurrentSession().update(cotizacion);
	}

	@Transactional
	public void delete(Cotizacion cotizacion) {
		sessionFactory.getCurrentSession().delete(cotizacion);
	}

	@Transactional(readOnly = true)
	public Cotizacion getById(Long idCotizacion) {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("idCotizacion", idCotizacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();

		return lista.size() > 0 ? (Cotizacion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Cotizacion> getAll() {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();

		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Cotizacion> getByFechaEnvioCotizacion(Calendar fechaEnvioSolucion) {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("fechaResolucion", fechaEnvioSolucion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();

		return lista.size() > 0 ? lista : null;
	}

	public List<Cotizacion> getByFechaResolicion(Calendar fechaResolucion) {
		return null;
	}

	public List<Cotizacion> getByStatus(Integer status) {
		return null;
	}

	@Transactional(readOnly = true)
	public List<Cotizacion> getByProveedor(Proveedor proveedor) {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Cotizacion> getByRequisicion(Requisicion requisicion) {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("requisicion", requisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Cotizacion> getTopCompras() {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.desc("idCotizacion"));

		criteria.setMaxResults(50);
		lista = criteria.list();

		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public Long getCountRowsCotizacion() {
		Long count = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setProjection(Projections.rowCount());
		count = (Long) criteria.list().get(0);
		count = Long.valueOf(count.longValue() + 1L);

		return count.longValue() > 0L ? count : null;
	}

	@Transactional(readOnly = true)
	public Cotizacion getCotizacionByFolio(String folioCotizacion) {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("folioCotizacion", folioCotizacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();

		return lista.size() > 0 ? (Cotizacion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Cotizacion> getCotizacionesByEstatusRequisicionAndFolioOrProveedorByFolio(String folioCotizacion,
			List<Proveedor> profolioCotizacionveedores, List<EstatusRequisicion> estatus) {
		boolean realizarConsulta = true;

		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);
		if ((folioCotizacion != null) && (!folioCotizacion.isEmpty())) {
			if (!folioCotizacion.equals("*")) {
				criteria.add(Restrictions.like("folioCotizacion", "%" + folioCotizacion + "%"));
			} else {
				realizarConsulta = false;
			}
		}
		if ((profolioCotizacionveedores != null) && (profolioCotizacionveedores.size() > 0)) {
			if (realizarConsulta) {
				criteria.add(Restrictions.in("proveedor", profolioCotizacionveedores));
			}
		}
		if ((estatus != null) && (estatus.size() > 0) && (realizarConsulta)) {
			criteria.add(Restrictions.in("estatusRequisicion", estatus));
		}
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(100);
		lista = criteria.list();

		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public Cotizacion getCotizacionByRequisicionProveedorAndProducto(Requisicion requisicion, Proveedor proveedor,
			Producto producto) {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("requisicion", requisicion));
		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("producto", producto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();

		return lista.size() > 0 ? (Cotizacion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Cotizacion> getByProveedorFolioCotizacionNueva(Proveedor proveedor, String folio,
			EstatusRequisicion estatusRequisicion) {
		List<Cotizacion> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Cotizacion.class);

		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("folioCotizacion", folio));
		criteria.add(Restrictions.eq("estatusRequisicion", estatusRequisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();

		return lista.size() > 0 ? lista : null;
	}
}
