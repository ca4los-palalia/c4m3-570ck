package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.FamiliasProducto;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTipo;

public abstract interface FamiliasProductoDAO {
	public abstract void save(FamiliasProducto paramFamiliasProducto);

	public abstract void update(FamiliasProducto paramFamiliasProducto);

	public abstract void delete(FamiliasProducto paramFamiliasProducto);

	public abstract FamiliasProducto getById(Long paramLong);

	public abstract List<FamiliasProducto> getAll();

	public abstract List<FamiliasProducto> getByProducto(Producto paramProducto);

	public abstract List<FamiliasProducto> getByFamilia(ProductoTipo paramProductoTipo);

	public abstract FamiliasProducto getByProductoProductoTipo(Producto producto, ProductoTipo productoTipo);
}
