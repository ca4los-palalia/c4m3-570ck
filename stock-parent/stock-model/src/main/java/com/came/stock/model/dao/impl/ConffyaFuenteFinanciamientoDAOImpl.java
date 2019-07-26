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
import com.came.stock.model.dao.ConffyaFuenteFinanciamientoDAO;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ConffyaFuenteFinanciamientoDAOImpl extends DatabaseMetaclass implements ConffyaFuenteFinanciamientoDAO {
	@Autowired
	private SessionUtils sessionUtils;
		
	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ConffyaFuenteFinanciamiento conffyaFuenteFinanciamiento) {
		sessionFactory.getCurrentSession().saveOrUpdate(conffyaFuenteFinanciamiento);
	}

	@Transactional
	public void delete(ConffyaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		sessionFactory.getCurrentSession().delete(cofiaFuenteFinanciamiento);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ConffyaFuenteFinanciamiento getById(Long idConffyaFuenteFinanciamiento) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaFuenteFinanciamiento.class);

		criteria.add(Restrictions.eq("idConffyaFuenteFinanciamiento", idConffyaFuenteFinanciamiento));

		List<ConffyaFuenteFinanciamiento> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ConffyaFuenteFinanciamiento) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaFuenteFinanciamiento> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaFuenteFinanciamiento.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ConffyaFuenteFinanciamiento> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaFuenteFinanciamiento> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaFuenteFinanciamiento.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<ConffyaFuenteFinanciamiento> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaFuenteFinanciamiento> getByClaveIn(List<String> listIn) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ConffyaFuenteFinanciamiento.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.add(Restrictions.in("clave", listIn));
		criteria.addOrder(Order.asc("nombre"));
		List<ConffyaFuenteFinanciamiento> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

//	@Transactional(readOnly = true)
//	public void updateFuenteFinanciamientoFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_fuente_financiamiento_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
