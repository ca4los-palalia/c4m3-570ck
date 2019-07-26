package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.RequisicionPartidaDAO;
import com.came.stock.model.domain.Partida;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionPartida;

@Service
public class RequisicionPartidaService {
	@Autowired
	private RequisicionPartidaDAO requisicionPartidaDAO;

	public void save(RequisicionPartida requisicionPartida) throws DataAccessException {
		requisicionPartidaDAO.save(requisicionPartida);
	}

	public void delete(RequisicionPartida requisicionPartida) throws DataAccessException {
		requisicionPartidaDAO.delete(requisicionPartida);
	}

	public RequisicionPartida getById(Long idRequisicionPartida) throws DataAccessException {
		return requisicionPartidaDAO.getById(idRequisicionPartida);
	}

	public List<RequisicionPartida> getByPartida(Partida partida) throws DataAccessException {
		return requisicionPartidaDAO.getByPartida(partida);
	}

	public List<RequisicionPartida> getByRequisicion(Requisicion requisicion) throws DataAccessException {
		return requisicionPartidaDAO.getByRequisicion(requisicion);
	}

	public List<RequisicionPartida> getAll() throws DataAccessException {
		return requisicionPartidaDAO.getAll();
	}
}
