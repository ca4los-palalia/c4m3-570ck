package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Municipio;

public abstract interface DireccionDAO {
	public abstract void save(Direccion paramDireccion);

	public abstract Direccion getById(Long paramLong);

	public abstract Direccion getUltimoRegistroDireccion();

	public abstract List<Direccion> getByCodigoPostalId(String paramString);

	public abstract List<Direccion> getByEstado(Estado paramEstado);

	public abstract List<Direccion> getByMunicipio(Municipio paramMunicipio);

	public abstract List<Direccion> getAll();
}
