package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.AlmacenEntrada;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;

public abstract interface AlmacenEntradaDAO {

	public void save(AlmacenEntrada almacenEntrada);

	void delete(AlmacenEntrada almacenEntrada);

	public AlmacenEntrada getById(Long idAlmacenEntrada, Organizacion organizacion);

	public List<AlmacenEntrada> getAll(Organizacion organizacion);

	public List<AlmacenEntrada> getByArea(Area area, Organizacion organizacion);

	public List<AlmacenEntrada> getByCotizacion(Cotizacion cotizacion, Organizacion organizacion);

	public List<AlmacenEntrada> getByOrdenCompra(OrdenCompra ordenCompra, Organizacion organizacion);

	public List<AlmacenEntrada> getByAlmacen(Almacen almacen, Organizacion organizacion);

	public List<AlmacenEntrada> getByOrdenCompraProductoProveedor(OrdenCompra ordenCompra, Producto producto,
			Proveedor proveedor, Organizacion organizacion);

}
