package com.came.stock.model.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.EstadoDAO;
import com.came.stock.model.domain.Estado;

@Service
public class EstadoService {
	@Autowired
	private EstadoDAO estadoDAO;

	public void save(Estado estado) throws DataAccessException {
		estadoDAO.save(estado);
	}

	public void delete(Estado estado) throws DataAccessException {
		estadoDAO.delete(estado);
	}

	public Estado getById(Long estado) throws DataAccessException {
		return estadoDAO.getById(estado);
	}

	public List<Estado> getAll() throws DataAccessException {
		return estadoDAO.getAll();
	}
	
	public Estado getByName(String name) throws DataAccessException {
		return estadoDAO.getByName(name);
	}
}
