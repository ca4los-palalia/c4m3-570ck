package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Justificacion;

public abstract interface JustificacionDAO {

	void save(Justificacion justificacion);

	void delete(Justificacion justificacion);

	Justificacion getById(Long idJustificacion);

	Justificacion getByNombre(String nombre);

	List<Justificacion> getAll();
	
}
