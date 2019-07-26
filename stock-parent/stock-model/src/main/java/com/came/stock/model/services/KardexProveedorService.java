package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.KardexProveedorDAO;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.Proveedor;

@Service
public class KardexProveedorService {
	@Autowired
	private KardexProveedorDAO kardexProveedorProveedorDAO;

	public void save(KardexProveedor kardexProveedor) throws DataAccessException {
		kardexProveedorProveedorDAO.save(kardexProveedor);
	}

	public void delete(KardexProveedor kardexProveedor) throws DataAccessException {
		kardexProveedorProveedorDAO.delete(kardexProveedor);
	}

	public KardexProveedor getById(Long idKardexProveedor) throws DataAccessException {
		return kardexProveedorProveedorDAO.getById(idKardexProveedor);
	}

	public List<KardexProveedor> getAll() throws DataAccessException {
		return kardexProveedorProveedorDAO.getAll();
	}
	public List<KardexProveedor> getByEstatus(EstatusRequisicion estatus) throws DataAccessException {
		return kardexProveedorProveedorDAO.getByEstatus(estatus);
	}
	public List<KardexProveedor> getByProveedorEstatus(Proveedor proveedor, EstatusRequisicion estatus) throws DataAccessException {
		return kardexProveedorProveedorDAO.getByProveedorEstatus(proveedor, estatus);
	}
}
