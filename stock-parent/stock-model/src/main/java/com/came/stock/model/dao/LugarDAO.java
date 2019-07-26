package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Proyecto;

public abstract interface LugarDAO {
	public abstract void save(Lugar paramLugar);

	public abstract void delete(Lugar paramLugar);

	public abstract Lugar getById(Long paramLong);

	public abstract Lugar getByIdProyecto(Proyecto paramProyecto);

	public abstract Lugar getByNombre(String paramString);

	public abstract List<Lugar> getAll();
}
