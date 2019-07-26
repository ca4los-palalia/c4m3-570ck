package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.CodigoBarrasProductoDAO;
import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.Producto;

@Repository
public class CodigoBarrasProductoDAOImpl extends DatabaseMetaclass implements CodigoBarrasProductoDAO {
	@Transactional
	public void save(CodigoBarrasProducto codigoBarrasProducto) {
		sessionFactory.getCurrentSession().saveOrUpdate(codigoBarrasProducto);
	}

	@Transactional
	public void delete(CodigoBarrasProducto codigoBarrasProducto) {
		sessionFactory.getCurrentSession().delete(codigoBarrasProducto);
	}

	@Transactional(readOnly = true)
	public CodigoBarrasProducto getById(Long idCodigoBarrasProducto) {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		criteria.add(Restrictions.eq("idCodigoBarrasProducto", idCodigoBarrasProducto));
		codigos = criteria.list();
		return codigos.size() > 0 ? (CodigoBarrasProducto) codigos.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<CodigoBarrasProducto> getAll() {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}

	@Transactional(readOnly = true)
	public List<CodigoBarrasProducto> getByCodigo(String codigo) {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		criteria.add(Restrictions.eq("codigo", codigo));
		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}

	@Transactional(readOnly = true)
	public List<CodigoBarrasProducto> getByProducto(Producto producto) {
		List<CodigoBarrasProducto> codigos = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession()
				.createCriteria(CodigoBarrasProducto.class);

		criteria.add(Restrictions.eq("producto", producto));
		codigos = criteria.list();
		return codigos.size() > 0 ? codigos : null;
	}
}
