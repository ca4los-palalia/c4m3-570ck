package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoFactores;

public abstract interface ProductoFactoresDAO {

	void save(ProductoFactores productoFactores);

	void delete(ProductoFactores productoFactores);
	
	ProductoFactores getById(Long idProductoFactores);

	List<ProductoFactores> getAll();

	ProductoFactores getByDescripcionAndProducto(String descripcion, Producto producto);

	List<ProductoFactores> getByProductoOrderMostRecentDate(Producto producto);	
}
