package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.BancoDAO;
import com.came.stock.model.domain.Banco;

@Service
public class BancoService {
	@Autowired
	private BancoDAO bancoDAO;

	public void save(Banco banco) throws DataAccessException {
		bancoDAO.save(banco);
	}

	public void delete(Banco banco) throws DataAccessException {
		bancoDAO.delete(banco);
	}

	public Banco getById(Long idBanco) throws DataAccessException {
		return bancoDAO.getById(idBanco);
	}

	public List<Banco> getAll() throws DataAccessException {
		return bancoDAO.getAll();
	}
}
