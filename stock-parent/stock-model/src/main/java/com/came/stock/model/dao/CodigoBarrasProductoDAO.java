package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.Producto;

public abstract interface CodigoBarrasProductoDAO {
	public abstract void save(CodigoBarrasProducto paramCodigoBarrasProducto);

	public abstract void delete(CodigoBarrasProducto paramCodigoBarrasProducto);

	public abstract CodigoBarrasProducto getById(Long paramLong);

	public abstract List<CodigoBarrasProducto> getAll();

	public abstract List<CodigoBarrasProducto> getByCodigo(String paramString);

	public abstract List<CodigoBarrasProducto> getByProducto(Producto paramProducto);

}
