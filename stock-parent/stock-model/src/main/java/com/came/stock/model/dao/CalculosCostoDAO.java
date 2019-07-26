package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.CalculosCosto;

public abstract interface CalculosCostoDAO {
	
	public abstract void save(CalculosCosto calculosCosto);

	public abstract void delete(CalculosCosto calculosCosto);

	public abstract CalculosCosto getById(Long idCalculosCosto);

	public abstract List<CalculosCosto> getAll();
}
