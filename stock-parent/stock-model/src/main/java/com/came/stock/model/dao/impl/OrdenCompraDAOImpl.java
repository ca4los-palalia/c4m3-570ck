package com.came.stock.model.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.OrdenCompraDAO;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.utilidades.SessionUtils;

@Repository
public class OrdenCompraDAOImpl extends DatabaseMetaclass implements OrdenCompraDAO {
	@Autowired
	private SessionUtils sessionUtils;

	private Organizacion getOrganizacion() {
		return (Organizacion) this.sessionUtils.getFromSession("FIRMA");
	}

	@Transactional
	public void save(OrdenCompra ordenCompra) {
		sessionFactory.getCurrentSession().saveOrUpdate(ordenCompra);
	}

	@Transactional
	public void delete(OrdenCompra ordenCompra) {
		sessionFactory.getCurrentSession().delete(ordenCompra);
	}

	@Transactional(readOnly = true)
	public OrdenCompra getById(Long idOrdenCompra) {
		List<OrdenCompra> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompra.class);

		criteria.add(Restrictions.eq("idOrdenCompra", idOrdenCompra));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? (OrdenCompra) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<OrdenCompra> getAll() {
		List<OrdenCompra> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompra.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion) {
		List<OrdenCompra> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompra.class);

		criteria.add(Restrictions.eq("cotizacion", cotizacion));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public OrdenCompra getByCodigo(String codigo) {
		List<OrdenCompra> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompra.class);

		criteria.add(Restrictions.eq("codigo", codigo));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? (OrdenCompra) lista.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<OrdenCompra> getByFechaOrden(Calendar fechaOrden) {
		List<OrdenCompra> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompra.class);

		criteria.add(Restrictions.eq("fechaOrden", fechaOrden));
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		lista = criteria.list();
		return lista.size() > 0 ? lista : null;
	}

	@Transactional(readOnly = true)
	public String getCodigoDeOrden() {
		String folio = null;

		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompra.class);

		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setProjection(Projections.rowCount());
		List<OrdenCompra> lista = criteria.list();
		if (lista != null) {
			String numeroLista = String.valueOf(Integer.parseInt(String.valueOf(lista.get(0))) + 1);
			switch (numeroLista.length()) {
			case 1:
				folio = "00000" + numeroLista;
				break;
			case 2:
				folio = "0000" + numeroLista;
				break;
			case 3:
				folio = "000" + numeroLista;
				break;
			case 4:
				folio = "00" + numeroLista;
				break;
			case 5:
				folio = "0" + numeroLista;
			}
		}
		return (folio != null) && (!folio.isEmpty()) ? folio : null;
	}

	@Transactional(readOnly = true)
	public List<OrdenCompra> getOrdenesByEstatusAndFolioOr(String folioOrdenCompra, List<EstatusRequisicion> estatus) {
		boolean realizarConsulta = true;

		List<OrdenCompra> lista = null;
		Criteria criteria = sessionFactory.getCurrentSession().getSessionFactory().openSession().createCriteria(OrdenCompra.class);
		if ((folioOrdenCompra != null) && (!folioOrdenCompra.isEmpty())) {
			if (!folioOrdenCompra.equals("*")) {
				criteria.add(Restrictions.like("codigo", "%" + folioOrdenCompra + "%"));
			} else {
				realizarConsulta = false;
			}
		}
		if ((estatus != null) && (estatus.size() > 0) && (realizarConsulta)) {
			criteria.add(Restrictions.in("estatusRequisicion", estatus));
		}
		criteria.add(Restrictions.eq("organizacion", getOrganizacion()));
		criteria.setMaxResults(100);
		lista = criteria.list();

		return lista.size() > 0 ? lista : null;
	}
}
