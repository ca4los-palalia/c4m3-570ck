package com.came.stock.model.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.AlmacenEntradaDAO;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.AlmacenEntrada;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;

@Repository
public class AlmacenEntradaDAOImpl extends DatabaseMetaclass implements AlmacenEntradaDAO {
//	@Autowired
//	private SessionUtils sessionUtils;

//	private Organizacion getOrganizacion() {
//		return (Organizacion) sessionUtils.getFromSession("FIRMA");
//	}

	@Transactional
	public void save(AlmacenEntrada almacen) {
		sessionFactory.getCurrentSession().saveOrUpdate(almacen);
	}

	@Transactional
	public void delete(AlmacenEntrada almacen) {
		sessionFactory.getCurrentSession().delete(almacen);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public AlmacenEntrada getById(Long idAlmacen, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);

		criteria.add(Restrictions.eq("idAlmacenEntrada", idAlmacen));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		criteria.setMaxResults(1);
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getAll(Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);

		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByArea(Area area, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("area", area));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByCotizacion(Cotizacion cotizacion, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("cotizacion", cotizacion));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByOrdenCompra(OrdenCompra ordenCompra, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("ordenCompra", ordenCompra));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByAlmacen(Almacen almacen, Organizacion organizacion) {
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		criteria.add(Restrictions.eq("almacen", almacen));
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AlmacenEntrada> getByOrdenCompraProductoProveedor(OrdenCompra ordenCompra, Producto producto,
			Proveedor proveedor, Organizacion organizacion) {
		
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(AlmacenEntrada.class);
		if(ordenCompra != null)
			criteria.add(Restrictions.eq("ordenCompra", ordenCompra));
		if(producto != null)
			criteria.add(Restrictions.eq("producto", producto));
		if(proveedor != null)
			criteria.add(Restrictions.eq("proveedor", proveedor));
		
		
		criteria.add(Restrictions.eq("organizacion", organizacion));
		List<AlmacenEntrada> lista = criteria.list();
		return (lista != null) && (!lista.isEmpty()) ? lista : null;
		
	}
}
