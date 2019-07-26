package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Persona;

public abstract interface PersonaDAO {
	public abstract void save(Persona paramPersona);

	public abstract void delete(Persona paramPersona);

	public abstract List<Persona> getByDireccion(Direccion paramDireccion);

	public abstract List<Persona> getByContacto(Contacto paramContacto);

	public abstract Persona getById(Long paramLong);

	public abstract List<Persona> getAll();

	public abstract List<Persona> getBySexo(Long paramLong);

	public abstract Persona getUltimoRegistroPersona();
}
