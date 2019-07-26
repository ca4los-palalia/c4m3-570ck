package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProyectoDAO;
import com.came.stock.model.domain.Proyecto;

@Repository
public class ProyectoDAOImpl extends DatabaseMetaclass implements ProyectoDAO {
	public void save(Proyecto proyecto) {
	}

	public void update(Proyecto proyecto) {
	}

	public void delete(Proyecto proyecto) {
	}

	public Proyecto getById(Long idProyecto) {
		return null;
	}

	public List<Proyecto> getAll() {
		return null;
	}

	public Proyecto getById(String nombre) {
		return null;
	}
}
