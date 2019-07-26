package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Partida;

public abstract interface PartidaDAO {
	public abstract void save(Partida paramPartida);

	public abstract void delete(Partida paramPartida);

	public abstract Partida getById(Long paramLong);

	public abstract List<Partida> getAll();
}
