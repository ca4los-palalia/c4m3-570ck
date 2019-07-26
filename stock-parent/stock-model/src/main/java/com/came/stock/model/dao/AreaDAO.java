package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;

public abstract interface AreaDAO {
	public abstract void save(Area paramArea);

	public abstract void update(Area paramArea);

	public abstract void delete(Area paramArea);

	public abstract Area getById(Long paramLong);

	public abstract List<Area> getAll();

	public abstract Area getByNombre(String paramString);

	public abstract List<Area> getByOrganizacion(Organizacion organizacion);

//	public abstract void updateAreaFromConffya(String xml, Long usuario, Long organizacion);
}
