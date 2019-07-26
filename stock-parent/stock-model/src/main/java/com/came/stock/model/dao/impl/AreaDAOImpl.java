package com.came.stock.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.AreaDAO;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class AreaDAOImpl extends DatabaseMetaclass implements AreaDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Area area) {
		sessionFactory.getCurrentSession().saveOrUpdate(area);
	}

	@Transactional
	public void update(Area area) {
		sessionFactory.getCurrentSession().update(area);
	}

	@Transactional
	public void delete(Area posicion) {
		sessionFactory.getCurrentSession().delete(posicion);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Area getById(Long idArea) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Area.class);

		criteria.add(Restrictions.eq("idArea", idArea));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Area> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Area) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Area> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Area.class);
		criteria.addOrder(Order.asc("clave"));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Area> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Area getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Area.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Area> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Area) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Area> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Area.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Area> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

//	@Transactional(readOnly = true)
//	public void updateAreaFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_unidades_responsables_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
