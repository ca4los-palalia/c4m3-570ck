package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ClaveArmonizadaDAO;
import com.came.stock.model.domain.ClaveArmonizada;

@Service
public class ClaveArmonizadaService {
	@Autowired
	private ClaveArmonizadaDAO claveArmonizadaDAO;

	public void save(ClaveArmonizada claveArmonizada) throws DataAccessException {
		claveArmonizadaDAO.save(claveArmonizada);
	}

	public void delete(ClaveArmonizada claveArmonizada) throws DataAccessException {
		claveArmonizadaDAO.delete(claveArmonizada);
	}

	public ClaveArmonizada getById(Long idClaveArmonizada) {
		return claveArmonizadaDAO.getById(idClaveArmonizada);
	}

	public List<ClaveArmonizada> getAll() {
		return claveArmonizadaDAO.getAll();
	}

	public List<ClaveArmonizada> getByGrupo(Integer grupo) {
		return claveArmonizadaDAO.getByGrupo(grupo);
	}

	public List<ClaveArmonizada> getBySubGrupo(Integer subGrupo) {
		return claveArmonizadaDAO.getBySubGrupo(subGrupo);
	}

	public List<ClaveArmonizada> getByClase(Integer clase) {
		return claveArmonizadaDAO.getByClase(clase);
	}

	public ClaveArmonizada getByClave(String clave) {
		return claveArmonizadaDAO.getByClave(clave);
	}

	public ClaveArmonizada getByDescripcion(String descripcion) {
		return claveArmonizadaDAO.getByDescripcion(descripcion);
	}
}
