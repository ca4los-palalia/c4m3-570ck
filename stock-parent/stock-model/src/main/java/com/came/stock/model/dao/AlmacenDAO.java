package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;

public abstract interface AlmacenDAO {

	public void save(Almacen almacen);

	void delete(Almacen almacen);

	public Almacen getById(Long idAlmacen, Organizacion organizacion);

	public List<Almacen> getAll(Organizacion organizacion);

	public List<Almacen> getByArea(Area area);

}
