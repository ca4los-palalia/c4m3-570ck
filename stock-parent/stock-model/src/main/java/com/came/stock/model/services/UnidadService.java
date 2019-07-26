package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.UnidadDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;

@Service
public class UnidadService {
	@Autowired
	private UnidadDAO unidadDAO;

	public void save(Unidad unidad) throws DataAccessException {
		unidadDAO.save(unidad);
	}

	public void update(Unidad unidad) throws DataAccessException {
		unidadDAO.update(unidad);
	}

	public void delete(Unidad unidad) throws DataAccessException {
		unidadDAO.delete(unidad);
	}

	public Unidad getById(Long idUnidad) throws DataAccessException {
		return unidadDAO.getById(idUnidad);
	}

	public List<Unidad> getAll() throws DataAccessException {
		return unidadDAO.getAll();
	}

	public Unidad getByNombre(String nombre) throws DataAccessException {
		return unidadDAO.getByNombre(nombre);
	}

	public List<Unidad> getByOrganizacion(Organizacion organizacion) {
		return unidadDAO.getByOrganizacion(organizacion);
	}

	public List<Unidad> getByUsuario(Usuarios usuarios) {
		return unidadDAO.getByUsuario(usuarios);
	}
}
