package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.LugarDAO;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Proyecto;

@Service
public class LugarService {
	@Autowired
	private LugarDAO lugarDAO;

	public void save(Lugar lugar) throws DataAccessException {
		lugarDAO.save(lugar);
	}

	public void delete(Lugar lugar) throws DataAccessException {
		lugarDAO.delete(lugar);
	}

	public Lugar getById(Long idLugar) throws DataAccessException {
		return lugarDAO.getById(idLugar);
	}

	public Lugar getByIdProyecto(Proyecto proyecto) throws DataAccessException {
		return lugarDAO.getByIdProyecto(proyecto);
	}

	public Lugar getByNombre(String nombre) throws DataAccessException {
		return lugarDAO.getByNombre(nombre);
	}

	public List<Lugar> getAll() throws DataAccessException {
		return lugarDAO.getAll();
	}
}
