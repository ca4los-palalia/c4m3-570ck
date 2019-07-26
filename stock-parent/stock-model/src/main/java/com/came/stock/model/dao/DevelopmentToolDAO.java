package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.DevelopmentTool;

public abstract interface DevelopmentToolDAO {
	public abstract void save(DevelopmentTool developmentTool);

	public abstract DevelopmentTool getById(Long idDevelopmentTool);

	public abstract List<DevelopmentTool> getAll();

	public abstract DevelopmentTool getByValue(String value);

	public abstract DevelopmentTool getByDescripcion(String descripcion);

	public abstract List<DevelopmentTool> getCountLayouts();

	public abstract DevelopmentTool getByNombre(String nombre);

	public abstract DevelopmentTool getBussy(String descripcion);
}
