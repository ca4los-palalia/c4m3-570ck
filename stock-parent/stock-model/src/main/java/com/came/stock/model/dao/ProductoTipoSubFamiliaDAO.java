package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.ProductoTipoSubFamilia;

public abstract interface ProductoTipoSubFamiliaDAO {
	public abstract void save(ProductoTipoSubFamilia productoTipoSubFamilia);

	public abstract void delete(ProductoTipoSubFamilia productoTipoSubFamilia);

	public abstract ProductoTipoSubFamilia getById(Long idProductoTipoSubFamilia);

	public abstract List<ProductoTipoSubFamilia> getAll();

	public abstract ProductoTipoSubFamilia getByNombre(String nombre);

	public abstract List<ProductoTipoSubFamilia> getByProductoTipo(ProductoTipo productoTipo);
}
