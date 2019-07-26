package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.RequisicionPartidaDAO;
import com.came.stock.model.domain.Partida;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionPartida;

@Repository
public class RequisicionPartidaDAOImpl extends DatabaseMetaclass implements RequisicionPartidaDAO {
	public void save(RequisicionPartida requisicionPartida) {
	}

	public void update(RequisicionPartida requisicionPartida) {
	}

	public void delete(RequisicionPartida requisicionPartida) {
	}

	public RequisicionPartida getById(Long idRequisicionPartida) {
		return null;
	}

	public List<RequisicionPartida> getByPartida(Partida partida) {
		return null;
	}

	public List<RequisicionPartida> getByRequisicion(Requisicion requisicion) {
		return null;
	}

	public List<RequisicionPartida> getAll() {
		return null;
	}
}
