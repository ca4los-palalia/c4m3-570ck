package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Pais;

public abstract interface PaisDAO {
	public abstract void save(Pais paramPais);

	public abstract void delete(Pais paramPais);

	public abstract List<Pais> getAll();

	public abstract Pais findById(Long paramLong);
}
