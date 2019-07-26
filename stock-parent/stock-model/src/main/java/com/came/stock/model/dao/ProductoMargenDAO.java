package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoMargen;

public abstract interface ProductoMargenDAO {

	void save(ProductoMargen productoMargen);

	void delete(ProductoMargen productoMargen);

	ProductoMargen getById(Long idProductoMargen);

	List<ProductoMargen> getAll();

	ProductoMargen getByDescripcionAndProducto(String descripcion, Producto producto);

	List<ProductoMargen> getByProductoOrderMostRecentDate(Producto producto);
	
}
