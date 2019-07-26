package com.came.stock.model.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.ReconstruccionNativeSQL;
import com.came.stock.model.dao.UsuarioDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Usuarios;

@Repository
public class UsuarioDAOImpl extends DatabaseMetaclass implements UsuarioDAO {
	
	@Transactional
	public void save(Usuarios usuarios) {
		sessionFactory.getCurrentSession().saveOrUpdate(usuarios);
	}

	@Transactional
	public void delete(Usuarios usuario) {
		sessionFactory.getCurrentSession().delete(usuario);
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Usuarios getUsuarioByCredentials(String usuario, String password) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class);
		criteria.add(Restrictions.eq("benutzer", usuario));
		criteria.add(Restrictions.eq("kennwort", password));
		criteria.setFetchMode("organizacion", FetchMode.JOIN);
		criteria.setFetchMode("organizacion.organizacion", FetchMode.JOIN);
		List<Usuarios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Usuarios getUsuarioById(Long idUsuario) {
		List<Usuarios> list = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class);

		criteria.add(Restrictions.eq("idUsuario", idUsuario));
		list = criteria.list();
		return list.size() > 0 ? list.get(0) : null;
	}
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion) {
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.add(Restrictions.eq("client", false));
		criteria.add(Restrictions.eq("owner", false));
		criteria.setFetchMode("organizacion", FetchMode.JOIN);
		criteria.setFetchMode("organizacion.organizacion", FetchMode.JOIN);
		List<Usuarios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosByOrganizacionAll(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.setFetchMode("organizacion", FetchMode.JOIN);
		criteria.setFetchMode("organizacion.organizacion", FetchMode.JOIN);
		List<Usuarios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		/*
		List<Usuarios> usuarios = sessionFactory.getCurrentSession()
				.find("FROM Usuarios as u LEFT JOIN FETCH u.organizacion as o WHERE u.organizacion = ?", organizacion);

		return usuarios.size() > 0 ? usuarios : null;
		*/
	}

	@Transactional(readOnly = true)
	public boolean verificarNombreUsuario(String benutzer, Long idUsuario) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class);

		criteria.add(Restrictions.like("benutzer", "%" + benutzer + "%"));
		criteria.add(Restrictions.ne("idUsuario", idUsuario));
		return criteria.list().size() > 0;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Usuarios getClienteByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class, "usuariosTabla");
		criteria.createAlias("usuariosTabla.organizacion", "organizacion");
		criteria.add(Restrictions.eq("usuariosTabla.organizacion", organizacion));
		criteria.add(Restrictions.eq("usuariosTabla.cliente", true));
		
		List<Usuarios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
		
//		List<Usuarios> usuarios = sessionFactory.getCurrentSession().find(
//				"FROM Usuarios as u WHERE u.organizacion = ? AND u.client = ?",
//				new Object[] { organizacion, Boolean.valueOf(true) });
//
//		return usuarios.size() > 0 ? (Usuarios) usuarios.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Usuarios getOwner() {
		
		List<Usuarios> list = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class);

		criteria.add(Restrictions.eq("owner", true));
		list = criteria.list();
		return list.size() > 0 ? list.get(0) : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class);
		List<Usuarios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosClienteAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Usuarios.class, "usuariosTabla");
		criteria.createAlias("usuariosTabla.organizacion", "organizacion");
		criteria.add(Restrictions.eq("usuariosTabla.client", true));
		criteria.add(Restrictions.eq("organizacion.activar", true));
		
		List<Usuarios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosLikeNombreOrganizacion(String nombreOrganizacion) {
		List<Usuarios> lista = new ArrayList<Usuarios>();
		ReconstruccionNativeSQL reconstruccion = new ReconstruccionNativeSQL();

		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery query = session.createSQLQuery("SELECT " 
			+ "us.idUsuario, us.benutzer, org.nombre "
			+ "FROM Usuarios AS us "
			+ "INNER JOIN Organizacion AS org ON org.idOrganizacion = us.organizacion "
			+ "WHERE us.client = 1 AND org.activar = 1 AND org.nombre LIKE '%" + nombreOrganizacion + "%' "
			+ "ORDER BY organizacion ASC");
		
		List<Object[]> allProductos = query.list();

		for (Object[] item : allProductos) {
			Usuarios p = reconstruccion.getUsuario(item);
			lista.add(p);
		}
		
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosLikeRfcOrganizacion(String rfcOrganizacion) {
		List<Usuarios> lista = new ArrayList<Usuarios>();
		ReconstruccionNativeSQL reconstruccion = new ReconstruccionNativeSQL();

		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery query = session.createSQLQuery("SELECT " 
			+ "us.idUsuario, us.benutzer, org.nombre "
			+ "FROM Usuarios AS us "
			+ "INNER JOIN Organizacion AS org ON org.idOrganizacion = us.organizacion "
			+ "WHERE us.client = 1 AND org.activar = 1 AND org.rfc LIKE '%" + rfcOrganizacion + "%' "
			+ "ORDER BY organizacion ASC");
		
		List<Object[]> allProductos = query.list();

		for (Object[] item : allProductos) {
			Usuarios p = reconstruccion.getUsuario(item);
			lista.add(p);
		}
		
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosLikeNumeroOrganizacion(Long numeroOrganizacion) {
		List<Usuarios> lista = new ArrayList<Usuarios>();
		ReconstruccionNativeSQL reconstruccion = new ReconstruccionNativeSQL();

		Session session = sessionFactory.getCurrentSession();
		
		SQLQuery query = session.createSQLQuery("SELECT " 
			+ "us.idUsuario, us.benutzer, org.nombre "
			+ "FROM Usuarios AS us "
			+ "INNER JOIN Organizacion AS org ON org.idOrganizacion = us.organizacion "
			+ "WHERE us.client = 1 AND org.activar = 1 AND org.numero LIKE '%" + numeroOrganizacion + "%' "
			+ "ORDER BY organizacion ASC");
		
		List<Object[]> allProductos = query.list();

		for (Object[] item : allProductos) {
			Usuarios p = reconstruccion.getUsuario(item);
			lista.add(p);
		}
		
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosClienteAllActiveUsrs(boolean activo) {
		return null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<Usuarios> getUsuariosClienteAllActiveUsrsByOrg(Organizacion org, boolean activo) {
		// TODO Auto-generated method stub
		return null;
	}
}
