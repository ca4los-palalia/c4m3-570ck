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
import com.came.stock.model.dao.ConffyaPyDAO;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ConffyaPyDAOImpl extends DatabaseMetaclass implements ConffyaPyDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ConffyaPy conffyaPy) {
		sessionFactory.getCurrentSession().saveOrUpdate(conffyaPy);
	}

	@Transactional
	public void delete(ConffyaPy conffyaPy) {
		sessionFactory.getCurrentSession().delete(conffyaPy);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ConffyaPy getById(Long idConffyaPy) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPy.class);
		criteria.add(Restrictions.eq("idConffyaPy", idConffyaPy));
		List<ConffyaPy> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ConffyaPy) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPy> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPy.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));

		List<ConffyaPy> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPy> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPy.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));

		List<ConffyaPy> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPy> getByClaveIn(List<String> listIn) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPy.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.add(Restrictions.in("clave", listIn));
		criteria.addOrder(Order.asc("nombre"));
		List<ConffyaPy> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

//	@Transactional(readOnly = true)
//	public void updateProyectoFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = sessionFactory.getCurrentSession().getSessionFactory().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_proyecto_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
