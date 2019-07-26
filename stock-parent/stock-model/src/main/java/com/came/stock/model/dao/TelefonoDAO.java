package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Telefono;

public abstract interface TelefonoDAO {
	public abstract void save(Telefono paramTelefono);

	public abstract void delete(Telefono paramTelefono);

	public abstract Telefono getById(Long paramLong);

	public abstract List<Telefono> getAll();

	public abstract Telefono getUltimoregistroEmail();
}
