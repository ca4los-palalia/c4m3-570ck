package com.came.stock.model.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ConffyaPresupuestoDesglosadoDAO;
import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class ConffyaPresupuestoDesglosadoDAOImpl extends DatabaseMetaclass implements ConffyaPresupuestoDesglosadoDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}
	
	@Transactional
	public void save(ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado) {
		sessionFactory.getCurrentSession().saveOrUpdate(conffyaPresupuestoDesglosado);
	}

	@Transactional
	public void delete(ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado) {
		sessionFactory.getCurrentSession().delete(conffyaPresupuestoDesglosado);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ConffyaPresupuestoDesglosado getById(Long idConffyaPresupuestoDesglosado) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPresupuestoDesglosado.class);
		criteria.add(Restrictions.eq("idConffyaPresupuestoDesglosado", idConffyaPresupuestoDesglosado));
		List<ConffyaPresupuestoDesglosado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ConffyaPresupuestoDesglosado) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPresupuestoDesglosado> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPresupuestoDesglosado.class);
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));

		List<ConffyaPresupuestoDesglosado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPresupuestoDesglosado> getByOrganizacion(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPresupuestoDesglosado.class);
		criteria.add(Restrictions.eq("organizacion", organizacion));

		List<ConffyaPresupuestoDesglosado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPresupuestoDesglosado> getByUr(String ur) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPresupuestoDesglosado.class);
		criteria.add(Restrictions.eq("UR", ur));

		List<ConffyaPresupuestoDesglosado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public Integer getCountRows() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPresupuestoDesglosado.class);
		criteria.add( Restrictions.isNotNull("idConffyaPresupuestoDesglosado"));
		criteria.setProjection(Projections.rowCount());
		Integer count = Integer.parseInt(String.valueOf(criteria.uniqueResult()));
		return count;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPresupuestoDesglosado> getPartidaGenericaFiltrado(String ur, String programa, String proyecto,
			String fuenteFinanciamiento) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPresupuestoDesglosado.class);
		criteria.add(Restrictions.eq("UR", ur));
		criteria.add(Restrictions.eq("PP", programa));
		if(proyecto != null && !proyecto.isEmpty())
			criteria.add(Restrictions.eq("PYC", proyecto));
		criteria.add(Restrictions.eq("FF1", fuenteFinanciamiento));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ConffyaPresupuestoDesglosado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConffyaPresupuestoDesglosado> getPartidaGenericaFiltradoConPartidaGenerica(String ur, String programa,
			String proyecto, String fuenteFinanciamiento, String partidaGenerica) {
		Long pa = new Long(partidaGenerica);
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConffyaPresupuestoDesglosado.class);
		criteria.add(Restrictions.eq("UR", ur));
		criteria.add(Restrictions.eq("PP", programa));
		if(proyecto != null && !proyecto.isEmpty())
			criteria.add(Restrictions.eq("PYC", proyecto));
		criteria.add(Restrictions.eq("FF1", fuenteFinanciamiento));
		criteria.add(Restrictions.eq("PA", pa));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<ConffyaPresupuestoDesglosado> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

//	@Transactional(readOnly = true)
//	public void updatePresupuestoFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_presupuesto_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
}
