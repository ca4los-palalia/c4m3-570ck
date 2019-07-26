package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Modulos;

public abstract interface ModulosDAO {
	public abstract void save(Modulos paramModulos);

	public abstract void delete(Modulos paramModulos);

	public abstract List<Modulos> getAll();
}
