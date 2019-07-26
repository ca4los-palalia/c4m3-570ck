package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Posicion;

public abstract interface PosicionDAO {
	public abstract void saveOrUpdate(Posicion paramPosicion);

	public abstract void update(Posicion paramPosicion);

	public abstract void save(Posicion paramPosicion);

	public abstract void delete(Posicion paramPosicion);

	public abstract Posicion getById(Long paramLong);

	public abstract List<Posicion> getAll();
}
