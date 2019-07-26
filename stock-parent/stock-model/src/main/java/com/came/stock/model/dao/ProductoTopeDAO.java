package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTope;

public abstract interface ProductoTopeDAO {
	public abstract void save(ProductoTope paramProductoTope);

	public abstract void delete(ProductoTope paramProductoTope);

	public abstract ProductoTope getById(Long paramLong);

	public abstract List<ProductoTope> getAll();

	public abstract List<ProductoTope> getByProducto(Producto paramProducto);

	public abstract List<ProductoTope> getByLugar(Lugar paramLugar);
}
