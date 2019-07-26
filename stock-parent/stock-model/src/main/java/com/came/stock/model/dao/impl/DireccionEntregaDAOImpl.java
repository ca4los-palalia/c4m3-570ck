package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.DireccionEntregaDAO;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.DireccionEntrega;

@Repository
public class DireccionEntregaDAOImpl extends DatabaseMetaclass implements DireccionEntregaDAO {
	public void save(DireccionEntrega direccion) {
	}

	public void update(DireccionEntrega direccion) {
	}

	public void delete(DireccionEntrega direccion) {
	}

	public DireccionEntrega getById(Long direccion) {
		return null;
	}

	public List<DireccionEntrega> getByDireccion(Direccion direccion) {
		return null;
	}

	public List<DireccionEntrega> getAll() {
		return null;
	}
}
