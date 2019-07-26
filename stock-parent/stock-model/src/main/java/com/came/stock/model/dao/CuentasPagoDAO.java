package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.CuentaPago;
import com.came.stock.model.domain.Proveedor;

public abstract interface CuentasPagoDAO {
	public abstract void save(CuentaPago paramCuentaPago);

	public abstract void delete(CuentaPago paramCuentaPago);

	public abstract CuentaPago getById(Long paramLong);

	public abstract List<CuentaPago> getAll();

	public abstract List<CuentaPago> getByProveedor(Proveedor paramProveedor);
}
