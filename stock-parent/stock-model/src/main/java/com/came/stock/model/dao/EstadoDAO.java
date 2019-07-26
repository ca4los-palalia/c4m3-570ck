package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Estado;

public abstract interface EstadoDAO {
	public abstract void save(Estado paramEstado);

	public abstract void delete(Estado paramEstado);

	public abstract Estado getById(Long paramLong);

	public abstract List<Estado> getAll();

	public abstract Estado getByName(String name);
}
