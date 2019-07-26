package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Giro;

public abstract interface GiroDAO {
	public abstract void save(Giro paramGiro);

	public abstract void delete(Giro paramGiro);

	public abstract Giro getById(Long paramLong);

	public abstract List<Giro> getAll();

	public abstract Giro getByNombre(String paramString);
}
