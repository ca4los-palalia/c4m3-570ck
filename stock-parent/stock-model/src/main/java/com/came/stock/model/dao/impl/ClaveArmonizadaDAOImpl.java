package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ClaveArmonizadaDAO;
import com.came.stock.model.domain.ClaveArmonizada;

@Repository
public class ClaveArmonizadaDAOImpl extends DatabaseMetaclass implements ClaveArmonizadaDAO {
	@Transactional
	public void save(ClaveArmonizada claveArmonizada) {
		sessionFactory.getCurrentSession().saveOrUpdate(claveArmonizada);
	}

	@Transactional
	public void delete(ClaveArmonizada claveArmonizada) {
		sessionFactory.getCurrentSession().delete(claveArmonizada);
	}

	@Transactional(readOnly = true)
	public ClaveArmonizada getById(Long idClaveArmonizada) {
		return null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getByGrupo(Integer grupo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("grupo", grupo));
		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getBySubGrupo(Integer subGrupo) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("subGrupo", subGrupo));
		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<ClaveArmonizada> getByClase(Integer clase) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("clase", clase));
		List<ClaveArmonizada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@Transactional(readOnly = true)
	public ClaveArmonizada getByClave(String clave) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("clave", clave));
		List<ClaveArmonizada> lista = criteria.list();
		return lista.size() > 0 ? (ClaveArmonizada) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public ClaveArmonizada getByDescripcion(String descripcion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(ClaveArmonizada.class);

		criteria.add(Restrictions.eq("descripcion", descripcion));
		List<ClaveArmonizada> lista = criteria.list();
		return lista.size() > 0 ? (ClaveArmonizada) lista.get(0) : null;
	}
}
