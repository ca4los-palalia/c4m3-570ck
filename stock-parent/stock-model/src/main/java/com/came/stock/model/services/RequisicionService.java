package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.RequisicionDAO;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Requisicion;

@Service
public class RequisicionService {
	@Autowired
	private RequisicionDAO requisicionDAO;

	public void save(Requisicion requisicion) {
		requisicionDAO.save(requisicion);
	}

	public void update(Requisicion requisicion) {
		requisicionDAO.update(requisicion);
	}

	public void delete(Requisicion requisicion) {
		requisicionDAO.delete(requisicion);
	}

	public Requisicion getById(Long idRequisicion, Organizacion organizacion) {
		return requisicionDAO.getById(idRequisicion, organizacion);
	}

	public Requisicion getByPersona(Persona persona, Organizacion organizacion) {
		return requisicionDAO.getByPersona(persona, organizacion);
	}

	public String getUltimoFolio(Organizacion organizacion) {
		return requisicionDAO.getUltimoFolio(organizacion);
	}

	public List<Requisicion> getAll(Organizacion organizacion) {
		return requisicionDAO.getAll(organizacion);
	}

	public List<Requisicion> getByEstatusRequisicion(EstatusRequisicion estatusRequisicion, Organizacion organizacion) {
		return requisicionDAO.getByEstatusRequisicion(estatusRequisicion, organizacion);
	}

	public Requisicion getByFolio(String folio, Organizacion organizacion) {
		return requisicionDAO.getByFolio(folio, organizacion);
	}

	public List<Requisicion> getByUnidadResponsable(Area area, Organizacion organizacion) {
		return requisicionDAO.getByUnidadResponsable(area, organizacion);
	}

	public List<Requisicion> getRequisicionesConListaDeEstatusFolioArea(List<EstatusRequisicion> estatusRequisiciones,
			String folio, Area area, Organizacion organizacion) {
		return requisicionDAO.getRequisicionesConListaDeEstatusFolioArea(estatusRequisiciones, folio, area, organizacion);
	}
}
