package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoPrecios;

public abstract interface ProductoPreciosDAO {

	void save(ProductoPrecios productoPrecios);

	void delete(ProductoPrecios productoPrecios);

	ProductoPrecios getById(Long idProductoPrecios);

	List<ProductoPrecios> getAll();

	ProductoPrecios getByDescripcionAndProducto(String clave, Producto producto);

	List<ProductoPrecios> getByProductoOrderMostRecentDate(Producto producto);

}
