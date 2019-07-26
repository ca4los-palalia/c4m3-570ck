package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.RequisicionProveedorDAO;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProveedor;

@Service
public class RequisicionProveedorService {
	@Autowired
	private RequisicionProveedorDAO requisicionProveedorDAO;

	public void save(RequisicionProveedor requisicionProveedor) throws DataAccessException {
		requisicionProveedorDAO.save(requisicionProveedor);
	}

	public void delete(RequisicionProveedor requisicionProveedor) throws DataAccessException {
		requisicionProveedorDAO.delete(requisicionProveedor);
	}

	public RequisicionProveedor getById(Long idRequisicionProveedor) throws DataAccessException {
		return requisicionProveedorDAO.getById(idRequisicionProveedor);
	}

	public List<RequisicionProveedor> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return requisicionProveedorDAO.getByRequisicion(requisicion);
	}

	public List<RequisicionProveedor> getByProveedor(Proveedor Proveedor) throws DataAccessException {
		return requisicionProveedorDAO.getByProveedor(Proveedor);
	}

	public List<RequisicionProveedor> getAll() throws DataAccessException {
		return requisicionProveedorDAO.getAll();
	}
}
