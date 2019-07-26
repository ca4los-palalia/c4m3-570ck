package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Destino;

public abstract interface DestinoDAO {
	public abstract void save(Destino paramDestino);

	public abstract Destino getById(Long paramLong);

	public abstract Destino getByNombre(String paramString);

	public abstract List<Destino> getAll();
}
