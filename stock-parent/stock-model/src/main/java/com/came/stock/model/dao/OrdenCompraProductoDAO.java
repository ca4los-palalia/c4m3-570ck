package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraProducto;
import com.came.stock.model.domain.Producto;

public abstract interface OrdenCompraProductoDAO {
	public abstract void save(OrdenCompraProducto paramOrdenCompraProducto);

	public abstract void delete(OrdenCompraProducto paramOrdenCompraProducto);

	public abstract OrdenCompraProducto getById(Long paramLong);

	public abstract List<OrdenCompraProducto> getAll();

	public abstract List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra paramOrdenCompra);

	public abstract List<OrdenCompraProducto> getByProducto(Producto paramProducto);
}
