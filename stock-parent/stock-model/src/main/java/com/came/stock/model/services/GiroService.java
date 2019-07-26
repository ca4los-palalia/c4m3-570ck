package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.GiroDAO;
import com.came.stock.model.domain.Giro;

@Service
public class GiroService {
	@Autowired
	private GiroDAO giroDAO;

	public void save(Giro giro) {
		giroDAO.save(giro);
	}

	public void delete(Giro giro) {
		giroDAO.delete(giro);
	}

	public Giro getById(Long idGiro) {
		return giroDAO.getById(idGiro);
	}

	public List<Giro> getAll() {
		return giroDAO.getAll();
	}

	public Giro getByNombre(String nombre) {
		return giroDAO.getByNombre(nombre);
	}
}
