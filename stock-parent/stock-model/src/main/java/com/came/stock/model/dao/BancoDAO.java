package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Banco;

public abstract interface BancoDAO {
	public abstract void save(Banco paramBanco);

	public abstract void delete(Banco paramBanco);

	public abstract Banco getById(Long paramLong);

	public abstract List<Banco> getAll();
}
