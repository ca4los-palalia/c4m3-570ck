package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Requisicion;

public abstract interface RequisicionDAO {
	public abstract void save(Requisicion requisicion);

	public abstract void update(Requisicion requisicion);

	public abstract void delete(Requisicion requisicion);

	public abstract Requisicion getById(Long idRequisicion, Organizacion organizacion);

	public abstract Requisicion getByPersona(Persona persona, Organizacion organizacion);

	public abstract String getUltimoFolio(Organizacion organizacion);

	public abstract List<Requisicion> getAll(Organizacion organizacion);

	public abstract List<Requisicion> getByEstatusRequisicion(EstatusRequisicion estatusRequisicion, Organizacion organizacion);

	public abstract Requisicion getByFolio(String folio, Organizacion organizacion);

	public abstract List<Requisicion> getByUnidadResponsable(Area area, Organizacion organizacion);

	public abstract List<Requisicion> getRequisicionesConListaDeEstatusFolioArea(List<EstatusRequisicion> paramList,
			String paramString, Area paramArea, Organizacion organizacion);
}
