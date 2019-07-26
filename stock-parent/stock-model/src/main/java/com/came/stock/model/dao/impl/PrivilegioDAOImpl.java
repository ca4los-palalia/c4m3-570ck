package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.PrivilegioDAO;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.SessionUtils;
import com.came.stock.utilidades.UserPrivileges;

@Repository
public class PrivilegioDAOImpl extends DatabaseMetaclass implements PrivilegioDAO {
	@Autowired
	private SessionUtils sessionUtils;

	@Transactional
	public void save(Privilegios privilegios) {
		sessionFactory.getCurrentSession().saveOrUpdate(privilegios);
	}

	@Transactional
	public void delete(Privilegios privilegios) {
		sessionFactory.getCurrentSession().delete(privilegios);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Privilegios.class);
		criteria.add(Restrictions.eq("usuarios", usuarios));
		criteria.setFetchMode("usuarios", FetchMode.JOIN);
		criteria.setFetchMode("usuarios.usuarios", FetchMode.JOIN);
		List<Privilegios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
		/*
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Privilegios.class, "privilegiosTabla");
		criteria.createAlias("privilegiosTabla.usuarios", "usuarios ");
		criteria.add(Restrictions.eq("usuarios", usuarios));
		List<Privilegios> lista = criteria.list();
		
		
		List<Privilegios> privilegios = sessionFactory.getCurrentSession()
				.find("FROM Privilegios as p LEFT JOIN FETCH p.usuarios as u WHERE p.usuarios = ?", usuarios);

		return privilegios;
		*/
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Privilegios> getUsuariosByPrivilegio(UserPrivileges privilegio) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Privilegios.class, "privilegiosTabla");
		criteria.createAlias("privilegiosTabla.usuarios", "usuarios");
		criteria.createAlias("usuarios.persona", "persona");
		criteria.createAlias("persona.contacto", "contacto");
		criteria.createAlias("contacto.email", "email");
		
		criteria.add(Restrictions.eq("privilegiosTabla.userPrivileges", privilegio));
		criteria.add(Restrictions.eq("usuarios.organizacion", sessionUtils.getFromSession("FIRMA")));
		
		List<Privilegios> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
		
		
//		List<Privilegios> usuarios = sessionFactory.getCurrentSession().find(
//				"FROM Privilegios as p LEFT "
//				+ "JOIN FETCH p.usuarios as u "
//				+ "LEFT JOIN FETCH u.persona.contacto.email as e WHERE p.userPrivileges = ? AND u.organizacion = ?",
//				new Object[] { privilegio, (Organizacion) this.sessionUtils.getFromSession("FIRMA") });
//
//		return usuarios;
	}
}
