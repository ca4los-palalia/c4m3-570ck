package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CalculosCostoDAO;
import com.came.stock.model.domain.CalculosCosto;

@Service
public class CalculosCostoService {
	@Autowired
	private CalculosCostoDAO calculosCostoDAO;

	public void save(CalculosCosto calculosCosto) throws DataAccessException {
		calculosCostoDAO.save(calculosCosto);
	}

	public void delete(CalculosCosto calculosCosto) throws DataAccessException {
		calculosCostoDAO.delete(calculosCosto);
	}

	public CalculosCosto getById(Long idCalculosCosto) throws DataAccessException {
		return calculosCostoDAO.getById(idCalculosCosto);
	}

	public List<CalculosCosto> getAll() throws DataAccessException {
		return calculosCostoDAO.getAll();
	}
}
