package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;

public abstract interface KardexDAO {

	public void save(Kardex kardex);

	void delete(Kardex kardex);

	public Kardex getById(Long idKardex, Organizacion organizacion);

	public List<Kardex> getAll(Organizacion organizacion);
	public List<Producto> getAllProductosNoRepetidos(Organizacion organizacion);
	public List<Kardex> getByEstatus(EstatusRequisicion estatus, Organizacion organizacion);
	public List<Kardex> getByKardexProveedorEstatus(KardexProveedor kardexProveedor, EstatusRequisicion estatus, Organizacion organizacion);
	public List<Kardex> getByProducto(Producto producto, Organizacion organizacion);

	public List<Kardex> getKardexOrderByDateMasReciente(List<Long> listaDesordenada, Organizacion organizacion);
	
}
