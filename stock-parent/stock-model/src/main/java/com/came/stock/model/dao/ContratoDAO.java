package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Contrato;

public abstract interface ContratoDAO {
	public abstract void save(Contrato paramContrato);

	public abstract void delete(Contrato paramContrato);

	public abstract Contrato getById(Long paramLong);

	public abstract List<Contrato> getAll();
}
