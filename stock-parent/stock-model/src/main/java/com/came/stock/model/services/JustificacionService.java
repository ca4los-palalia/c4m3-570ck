package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.JustificacionDAO;
import com.came.stock.model.domain.Justificacion;

@Service
public class JustificacionService {
	@Autowired
	private JustificacionDAO justificacionDAO;

	public void save(Justificacion justificacion) {
		justificacionDAO.save(justificacion);
	}

	public void delete(Justificacion justificacion) {
		justificacionDAO.delete(justificacion);
	}

	public Justificacion getById(Long idJustificacion) {
		return justificacionDAO.getById(idJustificacion);
	}

	public List<Justificacion> getAll() {
		return justificacionDAO.getAll();
	}

	public Justificacion getByNombre(String nombre) {
		return justificacionDAO.getByNombre(nombre);
	}
}
