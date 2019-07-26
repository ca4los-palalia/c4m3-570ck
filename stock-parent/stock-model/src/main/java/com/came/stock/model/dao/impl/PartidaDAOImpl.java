package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.PartidaDAO;
import com.came.stock.model.domain.Partida;

@Repository
public class PartidaDAOImpl extends DatabaseMetaclass implements PartidaDAO {
	public void save(Partida partida) {
	}

	public void update(Partida partida) {
	}

	public void delete(Partida partida) {
	}

	public Partida getById(Long idPartida) {
		return null;
	}

	public List<Partida> getAll() {
		return null;
	}
}
