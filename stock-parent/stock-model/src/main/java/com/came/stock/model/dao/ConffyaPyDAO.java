package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.Organizacion;

public abstract interface ConffyaPyDAO {
	public abstract void save(ConffyaPy conffyaPy);

	public abstract void delete(ConffyaPy conffyaPy);

	public abstract ConffyaPy getById(Long idConffyaPy);

	public abstract List<ConffyaPy> getAll();

	public abstract List<ConffyaPy> getByOrganizacion(Organizacion organizacion);

	public abstract List<ConffyaPy> getByClaveIn(List<String> listIn);

//	public abstract void updateProyectoFromConffya(String xml, Long usuario, Long organizacion);
}
