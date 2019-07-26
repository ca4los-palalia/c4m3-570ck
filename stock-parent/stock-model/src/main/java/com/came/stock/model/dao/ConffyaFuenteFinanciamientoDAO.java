package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.Organizacion;

public abstract interface ConffyaFuenteFinanciamientoDAO {
	public abstract void save(ConffyaFuenteFinanciamiento conffyaFuenteFinanciamiento);

	public abstract void delete(ConffyaFuenteFinanciamiento conffyaFuenteFinanciamiento);

	public abstract ConffyaFuenteFinanciamiento getById(Long idConffyaFuenteFinanciamiento);

	public abstract List<ConffyaFuenteFinanciamiento> getAll();

	public abstract List<ConffyaFuenteFinanciamiento> getByOrganizacion(Organizacion organizacion);

	public abstract List<ConffyaFuenteFinanciamiento> getByClaveIn(List<String> listIn);

//	public abstract void updateFuenteFinanciamientoFromConffya(String xml, Long usuario, Long organizacion);
}
