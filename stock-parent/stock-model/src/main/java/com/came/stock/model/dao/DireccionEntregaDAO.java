package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.DireccionEntrega;

public abstract interface DireccionEntregaDAO {
	public abstract void save(DireccionEntrega paramDireccionEntrega);

	public abstract void delete(DireccionEntrega paramDireccionEntrega);

	public abstract DireccionEntrega getById(Long paramLong);

	public abstract List<DireccionEntrega> getByDireccion(Direccion paramDireccion);

	public abstract List<DireccionEntrega> getAll();
}
