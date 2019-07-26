package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProveedorUsuarioDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorUsuario;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.SessionUtils;
import com.came.stock.utilidades.StockUtils;

@Repository
public class ProveedorUsuarioDAOImpl extends DatabaseMetaclass implements ProveedorUsuarioDAO {
	
	@Autowired
	private SessionUtils sessionUtils;
	StockUtils utilidad = new StockUtils();

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ProveedorUsuario proveedorUsuario) {
		sessionFactory.getCurrentSession().saveOrUpdate(proveedorUsuario);
	}

	@Transactional
	public void delete(ProveedorUsuario proveedorUsuario) {
		sessionFactory.getCurrentSession().delete(proveedorUsuario);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProveedorUsuario getById(Long idProveedorUsuario) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProveedorUsuario.class);
		criteria.add(Restrictions.eq("idProveedorUsuario", idProveedorUsuario));
		List<ProveedorUsuario> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ProveedorUsuario> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProveedorUsuario.class);
		List<ProveedorUsuario> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProveedorUsuario getByProveedor(Proveedor proveedor) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProveedorUsuario.class);
		criteria.add(Restrictions.eq("proveedor", proveedor));
		List<ProveedorUsuario> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProveedorUsuario getByUsuario(Usuarios usuario) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProveedorUsuario.class);
		criteria.add(Restrictions.eq("usuario", usuario));
		List<ProveedorUsuario> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ProveedorUsuario> getLikeNombre(String nombre) {
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProveedorUsuario.class, "proveedorUsuario");
		criteria.createAlias("proveedorUsuario.usuarios", "usuario");
		criteria.add(Restrictions.ilike("usuario.benutzer", "%" + nombre + "%"));
		List<ProveedorUsuario> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProveedorUsuario getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProveedorUsuario.class, "proveedorUsuario");
		criteria.createAlias("proveedorUsuario.usuarios", "usuario");
		criteria.add(Restrictions.eq("usuario.benutzer", nombre));
		List<ProveedorUsuario> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ProveedorUsuario getAcceso(String usuario, String contrasena) {
		usuario = utilidad.Encriptar(usuario.toString());
		contrasena = utilidad.Encriptar(contrasena.toString());
		
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ProveedorUsuario.class, "proveedorUsuario");
		criteria.createAlias("proveedorUsuario.usuarios", "usuarios");
		criteria.add(Restrictions.eq("usuarios.benutzer", usuario));
		criteria.add(Restrictions.eq("usuarios.kennwort", contrasena));
		List<ProveedorUsuario> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}
}
