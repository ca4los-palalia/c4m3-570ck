package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Moneda;

public abstract interface MonedaDAO {
	public abstract void save(Moneda paramMoneda);

	public abstract void delete(Moneda paramMoneda);

	public abstract Moneda getById(Long paramLong);

	public abstract List<Moneda> getAll();
}
