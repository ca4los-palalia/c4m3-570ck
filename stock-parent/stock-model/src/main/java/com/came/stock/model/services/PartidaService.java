package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.PartidaDAO;
import com.came.stock.model.domain.Partida;

@Service
public class PartidaService {
	@Autowired
	private PartidaDAO partidaDAO;

	public void save(Partida partida) throws DataAccessException {
		partidaDAO.save(partida);
	}

	public void delete(Partida partida) throws DataAccessException {
		partidaDAO.delete(partida);
	}

	public Partida getById(Long idPartida) throws DataAccessException {
		return partidaDAO.getById(idPartida);
	}

	public List<Partida> getAll() throws DataAccessException {
		return partidaDAO.getAll();
	}
}
