package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProyectoDAO;
import com.came.stock.model.domain.Proyecto;

@Service
public class ProyectoService {
	@Autowired
	private ProyectoDAO proyectopAO;

	public void save(Proyecto proyecto) throws DataAccessException {
		proyectopAO.save(proyecto);
	}

	public Proyecto getById(Long proyecto) throws DataAccessException {
		return proyectopAO.getById(proyecto);
	}

	public List<Proyecto> getAll() throws DataAccessException {
		return proyectopAO.getAll();
	}
}
