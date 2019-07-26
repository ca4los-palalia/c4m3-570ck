package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.EstatusRequisicionDAO;
import com.came.stock.model.domain.EstatusRequisicion;

@Repository
public class EstatusRequisicionDAOImpl extends DatabaseMetaclass implements EstatusRequisicionDAO {
	@Transactional
	public void save(EstatusRequisicion EstatusRequisicion) {
		sessionFactory.getCurrentSession().saveOrUpdate(EstatusRequisicion);
	}

	@Transactional
	public void delete(EstatusRequisicion EstatusRequisicion) {
		sessionFactory.getCurrentSession().delete(EstatusRequisicion);
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getById(Long idEstatusRequisicion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		criteria.add(Restrictions.eq("idEstatusRequisicion", idEstatusRequisicion));
		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (EstatusRequisicion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<EstatusRequisicion> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getByNombre(String nombre) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		criteria.add(Restrictions.eq("nombre", nombre));
		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (EstatusRequisicion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getByClave(String clave) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(EstatusRequisicion.class);

		criteria.add(Restrictions.eq("clave", clave));
		List<EstatusRequisicion> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (EstatusRequisicion) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public EstatusRequisicion getByEstado(boolean estado) {
		return null;
	}
}
