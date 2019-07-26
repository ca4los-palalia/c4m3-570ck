package com.came.stock.model.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ContratoDAO;
import com.came.stock.model.domain.Contrato;

@Service
public class ContratoService {
	@Autowired
	private ContratoDAO contratoDAO;

	public void save(Contrato contrato) throws DataAccessException {
		contratoDAO.save(contrato);
	}

	public void delete(Contrato contrato) throws DataAccessException {
		contratoDAO.delete(contrato);
	}

	public Contrato getById(Long idContrato) throws DataAccessException {
		return contratoDAO.getById(idContrato);
	}

	public List<Contrato> getAll() throws DataAccessException {
		return contratoDAO.getAll();
	}
}
