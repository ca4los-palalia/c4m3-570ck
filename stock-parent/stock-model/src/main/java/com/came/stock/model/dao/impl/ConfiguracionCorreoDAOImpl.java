package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ConfiguracionCorreoDAO;
import com.came.stock.model.domain.ConfiguracionCorreo;

@Repository
public class ConfiguracionCorreoDAOImpl extends DatabaseMetaclass implements ConfiguracionCorreoDAO {
	@Transactional
	public void save(ConfiguracionCorreo developmentTool) {
		sessionFactory.getCurrentSession().saveOrUpdate(developmentTool);
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public ConfiguracionCorreo getById(Long developmentTool) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConfiguracionCorreo.class);

		criteria.add(Restrictions.eq("idConfiguracionCorreo", developmentTool));
		List<ConfiguracionCorreo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (ConfiguracionCorreo) lista.get(0) : null;
	}


	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<ConfiguracionCorreo> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(ConfiguracionCorreo.class);
		List<ConfiguracionCorreo> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
