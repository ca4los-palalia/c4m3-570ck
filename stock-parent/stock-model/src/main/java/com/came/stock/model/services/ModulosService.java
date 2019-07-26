package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ModulosDAO;
import com.came.stock.model.domain.Modulos;

@Service
public class ModulosService {
	@Autowired
	private ModulosDAO modulosDAO;

	public void save(Modulos modulos) {
		modulosDAO.save(modulos);
	}

	public void delete(Modulos modulos) {
		modulosDAO.delete(modulos);
	}

	public List<Modulos> getAll() {
		return modulosDAO.getAll();
	}
}