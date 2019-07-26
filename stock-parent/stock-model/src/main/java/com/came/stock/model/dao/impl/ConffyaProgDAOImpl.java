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
import com.came.stock.model.dao.ConffyaProgDAO;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ConffyaProgDAOImpl extends DatabaseMetaclass implements ConffyaProgDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(ConffyaProg conffyaProg) {
		sessionFactory.getCurrentSession().saveOrUpdate(conffyaProg);
	}

	@Transactional
	public void delete(ConffyaProg cofiaProg) {
		sessionFactory.getCurrentSession().delete(cofiaProg);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ConffyaProg getById(Long idConffyaProg) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaProg.class);

		criteria.add(Restrictions.eq("idConffyaProg", idConffyaProg));

		List<ConffyaProg> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ConffyaProg) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaProg> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaProg.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.addOrder(Order.asc("nombre"));
		List<ConffyaProg> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaProg> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaProg.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.addOrder(Order.asc("nombre"));
		List<ConffyaProg> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaProg> getByClaveIn(List<String> listIn) {
		List<ConffyaProg> lista = null;
		
		if(listIn != null && listIn.size() > 0){
			Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaProg.class);
			criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
			criteria.add(Restrictions.in("clave", listIn));
			criteria.addOrder(Order.asc("nombre"));
			lista = criteria.list();
		}
		
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

//	@Transactional(readOnly = true)
//	public void updatePartidaGenericaFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_programa_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
