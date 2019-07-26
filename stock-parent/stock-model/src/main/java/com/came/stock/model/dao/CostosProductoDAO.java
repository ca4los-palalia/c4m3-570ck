package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.CostosProducto;
import com.came.stock.model.domain.Producto;

public abstract interface CostosProductoDAO {
	public abstract void save(CostosProducto paramCostosProducto);

	public abstract void delete(CostosProducto paramCostosProducto);

	public abstract CostosProducto getById(Long paramLong);

	public abstract List<CostosProducto> getAll();

	public abstract CostosProducto getByProducto(Producto paramProducto);
}
