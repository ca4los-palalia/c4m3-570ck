package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;

public abstract interface ProductoCostosDAO {

	void save(ProductoCostos productoCostos);

	void delete(ProductoCostos productoCostos);

	ProductoCostos getById(Long idProductoCostos);

	List<ProductoCostos> getAll();

	ProductoCostos getByDescripcionAndProducto(String descripcion, Producto producto);

	List<ProductoCostos> getByProductoOrderMostRecentDate(Producto producto);
	
}
