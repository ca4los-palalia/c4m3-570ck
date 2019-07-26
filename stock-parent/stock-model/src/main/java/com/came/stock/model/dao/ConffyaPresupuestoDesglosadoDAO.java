package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.Organizacion;

public abstract interface ConffyaPresupuestoDesglosadoDAO {

	void save(ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado);

	void delete(ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado);

	ConffyaPresupuestoDesglosado getById(Long idConffyaPresupuestoDesglosado);

	List<ConffyaPresupuestoDesglosado> getAll();

	List<ConffyaPresupuestoDesglosado> getByOrganizacion(Organizacion organizacion);
	
	List<ConffyaPresupuestoDesglosado> getByUr(String ur);
	
	Integer getCountRows();

	List<ConffyaPresupuestoDesglosado> getPartidaGenericaFiltrado(String ur, String programa, String proyecto,
			String fuenteFinanciamiento);

	List<ConffyaPresupuestoDesglosado> getPartidaGenericaFiltradoConPartidaGenerica(String ur, String programa,
			String proyecto, String fuenteFinanciamiento, String partidaGenerica);

//	void updatePresupuestoFromConffya(String xml, Long usuario, Long organizacion);
	
}
