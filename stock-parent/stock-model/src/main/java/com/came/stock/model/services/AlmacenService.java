package com.came.stock.model.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.AlmacenDAO;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Organizacion;

@Service
public class AlmacenService {
	@Autowired
	private AlmacenDAO almacenDAO;

	public void save(Almacen almacen) throws DataAccessException {
		almacenDAO.save(almacen);
	}

	public void delete(Almacen almacen) throws DataAccessException {
		almacenDAO.delete(almacen);
	}

	public Almacen getById(Long idAlmacen, Organizacion organizacion) throws DataAccessException {
		return almacenDAO.getById(idAlmacen, organizacion);
	}

	public List<Almacen> getAll(Organizacion organizacion) throws DataAccessException {
		return almacenDAO.getAll(organizacion);
	}
	public List<Almacen> getByArea(Area area) throws DataAccessException {
		return almacenDAO.getByArea(area);
	}
}
