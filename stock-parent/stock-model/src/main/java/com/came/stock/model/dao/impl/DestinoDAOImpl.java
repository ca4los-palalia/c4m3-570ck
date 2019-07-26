package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.DestinoDAO;
import com.came.stock.model.domain.Destino;

@Repository
public class DestinoDAOImpl extends DatabaseMetaclass implements DestinoDAO {
	public void save(Destino destino) {
	}

	public void update(Destino destino) {
	}

	public Destino getById(Long idDestino) {
		return null;
	}

	public Destino getByNombre(String lugar) {
		return null;
	}

	public List<Destino> getAll() {
		return null;
	}
}
