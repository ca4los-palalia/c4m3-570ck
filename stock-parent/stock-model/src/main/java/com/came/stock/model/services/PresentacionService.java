package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.PresentacionDAO;
import com.came.stock.model.domain.Presentacion;

@Service
public class PresentacionService {
	@Autowired
	private PresentacionDAO presentacionDAO;

	public void save(Presentacion presentacion) throws DataAccessException {
		presentacionDAO.save(presentacion);
	}

	public void delete(Presentacion Presentacion) throws DataAccessException {
		presentacionDAO.delete(Presentacion);
	}

	public List<Presentacion> getAll() throws DataAccessException {
		return presentacionDAO.getAll();
	}
	
	public Presentacion getById(Long idPresentacion) throws DataAccessException {
		return presentacionDAO.getById(idPresentacion);
	}	

	public List<Presentacion> getByNombre(String nombre) throws DataAccessException {
		return presentacionDAO.getByNombre(nombre);
	}
}
