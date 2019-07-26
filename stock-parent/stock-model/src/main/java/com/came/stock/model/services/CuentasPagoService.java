package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CuentasPagoDAO;
import com.came.stock.model.domain.CuentaPago;
import com.came.stock.model.domain.Proveedor;

@Service
public class CuentasPagoService {
	@Autowired
	private CuentasPagoDAO cuentasPagoDAO;

	public void save(CuentaPago cuentaPago) throws DataAccessException {
		cuentasPagoDAO.save(cuentaPago);
	}

	public void delete(CuentaPago cuentaPago) throws DataAccessException {
		cuentasPagoDAO.delete(cuentaPago);
	}

	public CuentaPago getById(Long idCuentaPago) throws DataAccessException {
		return cuentasPagoDAO.getById(idCuentaPago);
	}

	public List<CuentaPago> getAll() throws DataAccessException {
		return cuentasPagoDAO.getAll();
	}

	public List<CuentaPago> getByProveedor(Proveedor proveedor) throws DataAccessException {
		return cuentasPagoDAO.getByProveedor(proveedor);
	}
}
