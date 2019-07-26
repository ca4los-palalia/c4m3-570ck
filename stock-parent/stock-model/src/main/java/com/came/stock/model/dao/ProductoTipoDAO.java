package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ProductoTipo;

public abstract interface ProductoTipoDAO {
	public abstract void saveOrUpdate(ProductoTipo paramProductoTipo);

	public abstract void save(ProductoTipo paramProductoTipo);

	public abstract void update(ProductoTipo paramProductoTipo);

	public abstract void delete(ProductoTipo paramProductoTipo);

	public abstract ProductoTipo getById(Long paramLong);

	public abstract List<ProductoTipo> getAll();

	public abstract ProductoTipo getByNombre(String paramString);
}
