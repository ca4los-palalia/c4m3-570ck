package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;

public abstract interface UnidadDAO {
	public abstract void save(Unidad paramUnidad);

	public abstract void update(Unidad paramUnidad);

	public abstract void delete(Unidad paramUnidad);

	public abstract Unidad getById(Long paramLong);

	public abstract List<Unidad> getAll();

	public abstract Unidad getByNombre(String paramString);

	public abstract List<Unidad> getByOrganizacion(Organizacion paramOrganizacion);

	public abstract List<Unidad> getByUsuario(Usuarios paramUsuarios);
}
