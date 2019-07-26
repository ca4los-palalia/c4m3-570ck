package com.came.stock.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.ReconstruccionNativeSQL;
import com.came.stock.model.dao.ConffyaPartidaGenericaDAO;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ConffyaPartidaGenericaDAOImpl extends DatabaseMetaclass implements ConffyaPartidaGenericaDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ConffyaPartidaGenerica conffyaPartidaGenerica) {
		sessionFactory.getCurrentSession().saveOrUpdate(conffyaPartidaGenerica);
	}

	@Transactional
	public void delete(ConffyaPartidaGenerica cofiaPartidaGenerica) {
		sessionFactory.getCurrentSession().delete(cofiaPartidaGenerica);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ConffyaPartidaGenerica getById(Long idConffyaPartidaGenerica) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaPartidaGenerica.class);

		criteria.add(Restrictions.eq("idConffyaPartidaGenerica", idConffyaPartidaGenerica));

		List<ConffyaPartidaGenerica> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ConffyaPartidaGenerica) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPartidaGenerica> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaPartidaGenerica.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ConffyaPartidaGenerica> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ConffyaPartidaGenerica getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaPartidaGenerica.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.add(Restrictions.eq("nombre", nombre));
		List<ConffyaPartidaGenerica> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ConffyaPartidaGenerica) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPartidaGenerica> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaPartidaGenerica.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<ConffyaPartidaGenerica> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings({ "unchecked", "unused" })
	public List<ConffyaPartidaGenerica> getAbsolutyAllSqlNative() {
		
		
		List<ConffyaPartidaGenerica> lista = new ArrayList<ConffyaPartidaGenerica>();
		ReconstruccionNativeSQL reconstruccion = new ReconstruccionNativeSQL();

		Session session = sessionFactory.getCurrentSession();

		Transaction tx = session.beginTransaction();

		SQLQuery query = session.createSQLQuery("SELECT " +
			"cpg.idConffyaPartidaGenerica, " +
			"cpg.nombre, " +
			"cpg.organizacion, " +
			"org.nombre  as nomOrg " +
			"FROM ConffyaPartidaGenerica as cpg " +
			"JOIN Organizacion as org On cpg.organizacion = org.idOrganizacion ");
		
		List<Object[]> allItems = query.list();

		for (Object[] item : allItems) {
			ConffyaPartidaGenerica p = reconstruccion.getConffyaPartidaGenerica(item);
			lista.add(p);
		}
		return lista.size() > 0 ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPartidaGenerica> getByClaveIn(List<String> listIn) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaPartidaGenerica.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.add(Restrictions.in("clave", listIn));
		criteria.addOrder(Order.asc("nombre"));
		List<ConffyaPartidaGenerica> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public int getRowCount() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaPartidaGenerica.class);
		criteria.add( Restrictions.isNotNull("idConffyaPartidaGenerica"));
		criteria.setProjection(Projections.rowCount()).uniqueResult();
		Integer count = Integer.parseInt(String.valueOf(criteria.uniqueResult()));
		return count;
	}

//	@Transactional(readOnly = true)
//	public void updatePartidaGenericaFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_partida_generica_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
