package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Partida;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionPartida;

public abstract interface RequisicionPartidaDAO {
	public abstract void save(RequisicionPartida paramRequisicionPartida);

	public abstract void delete(RequisicionPartida paramRequisicionPartida);

	public abstract RequisicionPartida getById(Long paramLong);

	public abstract List<RequisicionPartida> getByPartida(Partida paramPartida);

	public abstract List<RequisicionPartida> getByRequisicion(Requisicion paramRequisicion);

	public abstract List<RequisicionPartida> getAll();
}
