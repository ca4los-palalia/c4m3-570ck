package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.CostosTipos;

public abstract interface CostosTiposDAO {

	void save(CostosTipos costosTipos);

	void delete(CostosTipos costosTipos);

	CostosTipos getById(Long idCostosTipos);

	List<CostosTipos> getAll(boolean limitarOrganizacion);
	
}
