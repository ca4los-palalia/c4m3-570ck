package com.came.stock.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.RequisicionProductoDAO;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.services.EstatusRequisicionService;
import com.came.stock.model.services.ProveedorService;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class RequisicionProductoDAOImpl extends DatabaseMetaclass implements RequisicionProductoDAO {
	@Autowired
	private ProveedorService proveedorService;
	@Autowired
	private EstatusRequisicionService estatusRequisicionService;
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(RequisicionProducto requisicionProducto) {
		sessionFactory.getCurrentSession().saveOrUpdate(requisicionProducto);
	}

	@Transactional
	public void update(RequisicionProducto requisicionProducto) {
		sessionFactory.getCurrentSession().update(requisicionProducto);
	}

	@Transactional
	public void delete(RequisicionProducto requisicionProducto) {
		sessionFactory.getCurrentSession().delete(requisicionProducto);
	}

	@Transactional(readOnly = true)
	public RequisicionProducto getById(Long idRequisicionProducto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("idRequisionProducto", idRequisicionProducto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (RequisicionProducto) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByProducto(Producto producto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByRequisicion(Requisicion requisicion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("requisicion", requisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByProveedor(Proveedor proveedor) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("proveedor", proveedor));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));

		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByLugar(Lugar lugar) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("lugar", lugar));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getAllRequisiciones() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getRequisicionesConEstadoEspecifico(EstatusRequisicion estatusRequisicion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class, "rp");

		criteria.createAlias("rp.requisicion", "rq");
		criteria.add(Restrictions.eq("rq.estatusRequisicion", estatusRequisicion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Proveedor> getAllDistinctByProveedor() {
		EstatusRequisicion estado = this.estatusRequisicionService.getByClave("RQ");

		List<Proveedor> lista = new ArrayList();

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class, "rp");

		criteria.createAlias("rp.requisicion", "rq");
		criteria.setProjection(Projections.distinct(Projections.property("proveedor")));
		criteria.add(Restrictions.eq("rq.estatusRequisicion", estado));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));

		lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByConfiaPartidaGenerica(ConffyaPartidaGenerica cofiaPartidaGenerica) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("cofiaPartidaGenerica", cofiaPartidaGenerica));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<RequisicionProducto> getByCotizacion(Cotizacion cotizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(RequisicionProducto.class);

		criteria.add(Restrictions.eq("cotizacion", cotizacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<RequisicionProducto> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
