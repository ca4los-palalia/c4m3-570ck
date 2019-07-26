package com.came.stock.model.services;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.OrdenCompraDAO;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.OrdenCompra;

@Service
public class OrdenCompraService {
	@Autowired
	private OrdenCompraDAO ordenCompraDAO;

	public void save(OrdenCompra ordenCompra) throws DataAccessException {
		ordenCompraDAO.save(ordenCompra);
	}

	public void delete(OrdenCompra ordenCompra) throws DataAccessException {
		ordenCompraDAO.delete(ordenCompra);
	}

	public OrdenCompra getById(Long idOrdenCompra) throws DataAccessException {
		return ordenCompraDAO.getById(idOrdenCompra);
	}

	public List<OrdenCompra> getAll() throws DataAccessException {
		return ordenCompraDAO.getAll();
	}

	public List<OrdenCompra> getByCotizacion(Cotizacion cotizacion) throws DataAccessException {
		return ordenCompraDAO.getByCotizacion(cotizacion);
	}

	public OrdenCompra getByCodigo(String codigo) {
		return ordenCompraDAO.getByCodigo(codigo);
	}

	public List<OrdenCompra> getByFechaOrden(Calendar fechaOrden) {
		return ordenCompraDAO.getByFechaOrden(fechaOrden);
	}

	public String getCodigoDeOrden() {
		return ordenCompraDAO.getCodigoDeOrden();
	}

	public List<OrdenCompra> getOrdenesByEstatusAndFolioOr(String folioOrdenCompra, List<EstatusRequisicion> estatus) {
		return ordenCompraDAO.getOrdenesByEstatusAndFolioOr(folioOrdenCompra, estatus);
	}
}
