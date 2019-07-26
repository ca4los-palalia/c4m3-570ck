package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CostosTiposDAO;
import com.came.stock.model.domain.CostosTipos;

@Service
public class CostosTiposService {
	@Autowired
	private CostosTiposDAO costosTiposDAO;

	public void save(CostosTipos costosTipos) {
		costosTiposDAO.save(costosTipos);
	}

	public void delete(CostosTipos costosTipos) {
		costosTiposDAO.delete(costosTipos);
	}

	public CostosTipos getById(Long idCostosTipos) {
		return costosTiposDAO.getById(idCostosTipos);
	}

	public List<CostosTipos> getAll(boolean limitarOrganizacion) {
		return costosTiposDAO.getAll(limitarOrganizacion);
	}

}
