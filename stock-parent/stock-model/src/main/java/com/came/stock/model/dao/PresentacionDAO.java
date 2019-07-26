package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Presentacion;

public abstract interface PresentacionDAO {

	void save(Presentacion presentacion);

	void delete(Presentacion presentacion);

	public List<Presentacion> getAll();

	public Presentacion getById(Long idPresentacion);

	public List<Presentacion> getByNombre(String nombre);

}
