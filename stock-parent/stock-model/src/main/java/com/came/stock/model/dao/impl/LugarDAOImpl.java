package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.LugarDAO;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Proyecto;

@Repository
public class LugarDAOImpl extends DatabaseMetaclass implements LugarDAO {
	public void save(Lugar lugar) {
	}

	public void update(Lugar lugar) {
	}

	public void delete(Lugar lugar) {
	}

	public Lugar getById(Long idLugar) {
		return null;
	}

	public Lugar getByIdProyecto(Proyecto proyecto) {
		return null;
	}

	public Lugar getByNombre(String nombre) {
		return null;
	}

	public List<Lugar> getAll() {
		return null;
	}
}
