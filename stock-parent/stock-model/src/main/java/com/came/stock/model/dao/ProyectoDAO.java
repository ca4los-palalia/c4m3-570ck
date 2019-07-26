package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Proyecto;

public abstract interface ProyectoDAO {
	public abstract void save(Proyecto paramProyecto);

	public abstract void delete(Proyecto paramProyecto);

	public abstract Proyecto getById(Long paramLong);

	public abstract List<Proyecto> getAll();

	public abstract Proyecto getById(String paramString);
}
