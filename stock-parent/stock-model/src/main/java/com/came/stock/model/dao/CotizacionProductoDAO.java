package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.CotizacionProducto;

public abstract interface CotizacionProductoDAO {
	public abstract void save(CotizacionProducto paramCotizacionProducto);

	public abstract void delete(CotizacionProducto paramCotizacionProducto);

	public abstract CotizacionProducto getById(Long paramLong);

	public abstract List<CotizacionProducto> getAll();
}
