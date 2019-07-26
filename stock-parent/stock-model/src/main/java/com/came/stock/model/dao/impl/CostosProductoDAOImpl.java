package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.CostosProductoDAO;
import com.came.stock.model.domain.CostosProducto;
import com.came.stock.model.domain.Producto;

@Repository
public class CostosProductoDAOImpl extends DatabaseMetaclass implements CostosProductoDAO {
	@Transactional
	public void save(CostosProducto costosProducto) {
		sessionFactory.getCurrentSession().saveOrUpdate(costosProducto);
	}

	@Transactional
	public void delete(CostosProducto costosProducto) {
		sessionFactory.getCurrentSession().delete(costosProducto);
	}

	@Transactional(readOnly = true)
	public CostosProducto getById(Long idCostosProducto) {
		List<CostosProducto> codigos = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(CostosProducto.class);

		criteria.add(Restrictions.eq("idCostosProducto", idCostosProducto));
		codigos = criteria.list();
		return codigos.size() > 0 ? (CostosProducto) codigos.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CostosProducto> getAll() {
		List<CostosProducto> codigos = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(CostosProducto.class);

		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}

	@Transactional(readOnly = true)
	public CostosProducto getByProducto(Producto producto) {
		List<CostosProducto> codigos = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(CostosProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		codigos = criteria.list();
		return codigos.size() > 0 ? (CostosProducto) codigos.get(0) : null;
	}
}
