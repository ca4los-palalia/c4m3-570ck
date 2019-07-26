package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProveedorDAO;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ProveedorDAOImpl extends DatabaseMetaclass implements ProveedorDAO {

	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Proveedor proveedor) {
		sessionFactory.getCurrentSession().saveOrUpdate(proveedor);
	}

	@Transactional
	public void update(Proveedor proveedor) {
		sessionFactory.getCurrentSession().update(proveedor);
	}

	@Transactional
	public void delete(Proveedor proveedor) {
		sessionFactory.getCurrentSession().delete(proveedor);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Proveedor getById(Long idProveedor) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("idProveedor", idProveedor));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Proveedor) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByContacto(Contacto contacto) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("contacto", contacto));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByContrato(Contrato contrato) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("contrato", contrato));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByDireccionDevolucion(Direccion direccion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("direccionDevolucion", direccion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByDireccionFiscal(Direccion direccion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("direccionFiscal", direccion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByGerenteFinanzas(Persona persona) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("gerenteFinanzas", persona));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByGerenteVentas(Persona persona) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("gerenteVentas", persona));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByRepresentanteLegal(Persona persona) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("representanteLegal", persona));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByRepresentanteClientes(Persona persona) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("representanteAteCliente", persona));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));

		List<Proveedor> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getBysClaveNombreRfc(String buscarTexto) {
		List<Proveedor> lista = null;

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.ilike("clave", "%" + buscarTexto + "%"));
		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.desc("fechaActualizacion"));
		lista = criteria.list();
		if ((lista.equals(null)) || (lista.size() < 1)) {
			Criteria criteria2 = sessionFactory.getCurrentSession().getSessionFactory().openSession()
					.createCriteria(Proveedor.class);

			criteria2.add(Restrictions.ilike("nombre", "%" + buscarTexto + "%"));
			criteria2.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
			criteria2.add(Restrictions.eq("organizacion", getOrganizacion()));
			criteria2.addOrder(Order.desc("fechaActualizacion"));
			lista = criteria2.list();
			if ((lista.equals(null)) || (lista.size() < 1)) {
				Criteria criteria3 = sessionFactory.getCurrentSession().getSessionFactory().openSession()
						.createCriteria(Proveedor.class);

				criteria3.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
				criteria3.add(Restrictions.ilike("rfc", "%" + buscarTexto + "%"));
				criteria3.add(Restrictions.eq("organizacion", getOrganizacion()));
				criteria3.addOrder(Order.desc("fechaActualizacion"));
				lista = criteria3.list();
			}
		}
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByNombre(String nombre) {
		List<Proveedor> lista = null;

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.eq("proveedorActivo", Boolean.valueOf(true)));
		criteria.add(Restrictions.ilike("nombre", "%" + nombre + "%"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.desc("fechaActualizacion"));
		lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<Proveedor> getProveedoresById(List<Long> idsProveedores) {
		List<Proveedor> lista = null;

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Proveedor.class);

		criteria.add(Restrictions.in("idProveedor", idsProveedores));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
