package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.DireccionDAO;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Municipio;

@Service
public class DireccionService {
	@Autowired
	private DireccionDAO direccionDAO;

	public void save(Direccion direccion) throws DataAccessException {
		direccionDAO.save(direccion);
	}

	public Direccion getById(Long direccion) throws DataAccessException {
		return direccionDAO.getById(direccion);
	}

	public Direccion getUltimoRegistroDireccion() {
		return direccionDAO.getUltimoRegistroDireccion();
	}

	public List<Direccion> getByCodigoPostalId(String codigoPostal) throws DataAccessException {
		return direccionDAO.getByCodigoPostalId(codigoPostal);
	}

	public List<Direccion> getByEstado(Estado estado) throws DataAccessException {
		return direccionDAO.getByEstado(estado);
	}

	public List<Direccion> getByMunicipio(Municipio municipio) throws DataAccessException {
		return direccionDAO.getByMunicipio(municipio);
	}

	public List<Direccion> getAll() throws DataAccessException {
		return direccionDAO.getAll();
	}
}
