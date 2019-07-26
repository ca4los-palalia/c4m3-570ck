package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProveedor;

public abstract interface RequisicionProveedorDAO {
	public abstract void save(RequisicionProveedor paramRequisicionProveedor);

	public abstract void delete(RequisicionProveedor paramRequisicionProveedor);

	public abstract RequisicionProveedor getById(Long paramLong);

	public abstract List<RequisicionProveedor> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<RequisicionProveedor> getByProveedor(Proveedor paramProveedor);

	public abstract List<RequisicionProveedor> getAll();
}
