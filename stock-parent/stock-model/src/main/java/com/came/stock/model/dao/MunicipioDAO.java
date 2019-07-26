package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Municipio;

public abstract interface MunicipioDAO {
	public abstract void save(Municipio paramMunicipio);

	public abstract void delete(Municipio paramMunicipio);

	public abstract Municipio getById(Long paramLong);
	
	public abstract Municipio getByName(String name);

	public abstract List<Municipio> getAll();

	public abstract List<Municipio> getByEstado(Estado paramEstado);
}
