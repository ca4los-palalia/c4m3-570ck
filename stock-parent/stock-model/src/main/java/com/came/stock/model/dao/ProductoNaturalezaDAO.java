package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ProductoNaturaleza;

public abstract interface ProductoNaturalezaDAO {
	public abstract void save(ProductoNaturaleza paramProductoNaturaleza);

	public abstract void update(ProductoNaturaleza paramProductoNaturaleza);

	public abstract void delete(ProductoNaturaleza paramProductoNaturaleza);

	public abstract ProductoNaturaleza getById(Long paramLong);

	public abstract List<ProductoNaturaleza> getAll();

	public abstract ProductoNaturaleza getByNombre(String paramString);

	public abstract ProductoNaturaleza getBySimbolo(String paramString);
}
