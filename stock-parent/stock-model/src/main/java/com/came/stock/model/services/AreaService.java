package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.AreaDAO;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;

@Service
public class AreaService {
	@Autowired
	private AreaDAO areaDAO;

	public void save(Area area) throws DataAccessException {
		areaDAO.save(area);
	}

	public void update(Area area) throws DataAccessException {
		areaDAO.update(area);
	}

	public void delete(Area area) throws DataAccessException {
		areaDAO.delete(area);
	}

	public Area getById(Long idArea) throws DataAccessException {
		return areaDAO.getById(idArea);
	}

	public List<Area> getAll() throws DataAccessException {
		return areaDAO.getAll();
	}

	public Area getByNombre(String nombre) {
		return areaDAO.getByNombre(nombre);
	}
	
	public List<Area> getByOrganizacion(Organizacion organizacion) {
		return areaDAO.getByOrganizacion(organizacion);
	}
	
//	public void updateAreaFromConffya(String xml, Long usuario, Long organizacion){
//		areaDAO.updateAreaFromConffya(xml, usuario, organizacion);
//	}
}
