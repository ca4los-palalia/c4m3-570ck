package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorProducto;

public abstract interface ProveedorProductoDAO {
	public abstract void save(ProveedorProducto paramProveedorProducto);

	public abstract void delete(ProveedorProducto paramProveedorProducto);

	public abstract ProveedorProducto getById(Long paramLong);

	public abstract List<ProveedorProducto> getByProveedor(Proveedor paramProveedor);

	public abstract List<ProveedorProducto> getByProducto(Producto paramProducto);

	public abstract List<ProveedorProducto> getByProductoProveedor(Producto paramProducto, Proveedor paramProveedor);

	public abstract List<ProveedorProducto> getAll();
}
