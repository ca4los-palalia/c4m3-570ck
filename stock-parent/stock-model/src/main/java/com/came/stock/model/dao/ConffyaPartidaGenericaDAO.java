package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Organizacion;

public abstract interface ConffyaPartidaGenericaDAO {
	public abstract void save(ConffyaPartidaGenerica conffyaPartidaGenerica);

	public abstract void delete(ConffyaPartidaGenerica conffyaPartidaGenerica);

	public abstract ConffyaPartidaGenerica getById(Long idConffyaPartidaGenerica);

	public abstract List<ConffyaPartidaGenerica> getAll();

	public abstract ConffyaPartidaGenerica getByNombre(String nombre);

	public abstract List<ConffyaPartidaGenerica> getByOrganizacion(Organizacion organizacion);

	public abstract List<ConffyaPartidaGenerica> getAbsolutyAllSqlNative();

	public abstract List<ConffyaPartidaGenerica> getByClaveIn(List<String> listIn);

	public abstract int getRowCount();

//	public abstract void updatePartidaGenericaFromConffya(String xml, Long usuario, Long organizacion);

}
