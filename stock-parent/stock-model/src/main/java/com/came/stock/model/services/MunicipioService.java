package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.MunicipioDAO;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Municipio;

@Service
public class MunicipioService {
	@Autowired
	private MunicipioDAO municipioDAO;

	public void save(Municipio estado) throws DataAccessException {
		municipioDAO.save(estado);
	}

	public void delete(Municipio estado) throws DataAccessException {
		municipioDAO.delete(estado);
	}

	public Municipio getById(Long idMunicipio) throws DataAccessException {
		return municipioDAO.getById(idMunicipio);
	}

	public List<Municipio> getAll() throws DataAccessException {
		return municipioDAO.getAll();
	}

	public List<Municipio> getByEstado(Estado estado) throws DataAccessException {
		return municipioDAO.getByEstado(estado);
	}
	
	public Municipio getByName(String name){
		return municipioDAO.getByName(name);
	}
}
