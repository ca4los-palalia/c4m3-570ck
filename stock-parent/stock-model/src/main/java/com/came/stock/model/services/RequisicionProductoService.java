package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.RequisicionProductoDAO;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;

@Service
public class RequisicionProductoService {
	@Autowired
	private RequisicionProductoDAO requisicionProductoDAO;

	public void save(RequisicionProducto requisicionProducto) throws DataAccessException {
		requisicionProductoDAO.save(requisicionProducto);
	}

	public void delete(RequisicionProducto requisicionProducto) throws DataAccessException {
		requisicionProductoDAO.delete(requisicionProducto);
	}

	public RequisicionProducto getById(Long idRequisicionProducto) throws DataAccessException {
		return requisicionProductoDAO.getById(idRequisicionProducto);
	}

	public List<RequisicionProducto> getByProducto(Producto producto) throws DataAccessException {
		return requisicionProductoDAO.getByProducto(producto);
	}

	public List<RequisicionProducto> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return requisicionProductoDAO.getByRequisicion(requisicion);
	}

	public List<RequisicionProducto> getByProveedor(Proveedor proveedor) throws DataAccessException {
		return requisicionProductoDAO.getByProveedor(proveedor);
	}

	public List<RequisicionProducto> getByLugar(Lugar lugar) throws DataAccessException {
		return requisicionProductoDAO.getByLugar(lugar);
	}

	public List<RequisicionProducto> getAll() throws DataAccessException {
		return requisicionProductoDAO.getAll();
	}

	public List<RequisicionProducto> getAllRequisiciones() throws DataAccessException {
		return requisicionProductoDAO.getAllRequisiciones();
	}

	public List<RequisicionProducto> getRequisicionesConEstadoEspecifico(EstatusRequisicion estatusRequisicion)
			throws DataAccessException {
		return requisicionProductoDAO.getRequisicionesConEstadoEspecifico(estatusRequisicion);
	}

	public List<Proveedor> getAllDistinctByProveedor() {
		return requisicionProductoDAO.getAllDistinctByProveedor();
	}

	public List<RequisicionProducto> getByConfiaPartidaGenerica(ConffyaPartidaGenerica cofiaPartidaGenerica) {
		return requisicionProductoDAO.getByConfiaPartidaGenerica(cofiaPartidaGenerica);
	}

	public List<RequisicionProducto> getByCotizacion(Cotizacion cotizacion) {
		return requisicionProductoDAO.getByCotizacion(cotizacion);
	}
}
