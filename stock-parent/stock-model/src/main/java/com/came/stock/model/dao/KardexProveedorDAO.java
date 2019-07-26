package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.Proveedor;

public abstract interface KardexProveedorDAO {

	public void save(KardexProveedor kardexProveedor);

	public void delete(KardexProveedor kardexProveedor);

	public KardexProveedor getById(Long idKardexProveedor);

	public List<KardexProveedor> getAll();

	public List<KardexProveedor> getByEstatus(EstatusRequisicion estatus);

	public List<KardexProveedor> getByProveedorEstatus(Proveedor proveedor, EstatusRequisicion estatus);
}
