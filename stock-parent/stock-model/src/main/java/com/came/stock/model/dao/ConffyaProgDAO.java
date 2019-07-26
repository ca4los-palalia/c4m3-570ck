package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.Organizacion;

public abstract interface ConffyaProgDAO {
	public abstract void save(ConffyaProg conffyaProg);

	public abstract void delete(ConffyaProg conffyaProg);

	public abstract ConffyaProg getById(Long idConffyaProg);

	public abstract List<ConffyaProg> getAll();

	public abstract List<ConffyaProg> getByOrganizacion(Organizacion organizacion);

	public abstract List<ConffyaProg> getByClaveIn(List<String> listIn);

//	public abstract void updatePartidaGenericaFromConffya(String xml, Long usuario, Long organizacion);
}
