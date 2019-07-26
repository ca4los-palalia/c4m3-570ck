package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.DevelopmentToolDAO;
import com.came.stock.model.domain.DevelopmentTool;

@Service
public class DevelopmentToolService {
	@Autowired
	private DevelopmentToolDAO developmentToolDAO;

	public void save(DevelopmentTool developmentTool) throws DataAccessException {
		developmentToolDAO.save(developmentTool);
	}

	public DevelopmentTool getById(Long idDevelopmentTool) throws DataAccessException {
		return developmentToolDAO.getById(idDevelopmentTool);
	}

	public List<DevelopmentTool> getAll() throws DataAccessException {
		return developmentToolDAO.getAll();
	}
	
	public DevelopmentTool getByValue(String value) throws DataAccessException {
		return developmentToolDAO.getByValue(value);
	}
	
	public DevelopmentTool getByNombre(String nombre) throws DataAccessException {
		return developmentToolDAO.getByNombre(nombre);
	}
	
	public DevelopmentTool getByDescripcion(String descripcion) throws DataAccessException {
		return developmentToolDAO.getByDescripcion(descripcion);
	}
	public List<DevelopmentTool> getCountLayouts() throws DataAccessException {
		return developmentToolDAO.getCountLayouts();
	}
	public DevelopmentTool getBussy(String descripcion) throws DataAccessException {
		return developmentToolDAO.getBussy(descripcion);
	}
}
