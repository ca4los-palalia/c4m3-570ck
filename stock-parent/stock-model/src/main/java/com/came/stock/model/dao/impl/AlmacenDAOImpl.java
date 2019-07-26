package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.AlmacenDAO;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;

@Repository
public class AlmacenDAOImpl extends DatabaseMetaclass implements AlmacenDAO {
//	@Autowired
//	private SessionUtils sessionUtils;
//
//	private Organizacion getOrganizacion() {
//		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
//	}

	@Transactional
	public void save(Almacen almacen) {
		sessionFactory.getCurrentSession().saveOrUpdate(almacen);
	}

	@Transactional
	public void delete(Almacen almacen) {
		sessionFactory.getCurrentSession().delete(almacen);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Almacen getById(Long idAlmacen, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Almacen.class);

		criteria.add(Restrictions.eq("idAlmacen", idAlmacen));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.setMaxResults(1);
		List<Almacen> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? (Almacen) lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Almacen> getAll(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Almacen.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<Almacen> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Almacen> getByArea(Area area) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(Almacen.class);

		criteria.addOrder(Order.asc("nombre"));
		criteria.add(Restrictions.eq("area", area));
		List<Almacen> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}
}
