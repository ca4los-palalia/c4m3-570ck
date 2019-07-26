package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.BannerDAO;
import com.came.stock.model.domain.Banner;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class BannerDAOImpl extends DatabaseMetaclass implements BannerDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(Banner banner) {
		sessionFactory.getCurrentSession().saveOrUpdate(banner);
	}

	@Transactional
	public void delete(Banner banner) {
		sessionFactory.getCurrentSession().delete(banner);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Banner getById(Long idBanner) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Banner.class);

		criteria.add(Restrictions.eq("idBanner", idBanner));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(1);
		List<Banner> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Banner) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Banner> getAll() {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Banner.class);

		criteria.addOrder(Order.asc("nombre"));
		//criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		List<Banner> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
