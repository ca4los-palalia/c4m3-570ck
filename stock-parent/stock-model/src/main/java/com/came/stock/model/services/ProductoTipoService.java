package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoTipoDAO;
import com.came.stock.model.domain.ProductoTipo;

@Service
public class ProductoTipoService {
	@Autowired
	private ProductoTipoDAO productoTipoDAO;

	public void saveOrUpdate(ProductoTipo productoTipo) throws DataAccessException {
		productoTipoDAO.saveOrUpdate(productoTipo);
	}

	public void save(ProductoTipo productoTipo) {
		productoTipoDAO.save(productoTipo);
	}

	public void update(ProductoTipo productoTipo) {
		productoTipoDAO.update(productoTipo);
	}

	public void delete(ProductoTipo productoTipo) throws DataAccessException {
		productoTipoDAO.delete(productoTipo);
	}

	public ProductoTipo getById(Long idProductoTipo) throws DataAccessException {
		return productoTipoDAO.getById(idProductoTipo);
	}

	public List<ProductoTipo> getAll() throws DataAccessException {
		return productoTipoDAO.getAll();
	}

	public ProductoTipo getByNombre(String nombre) throws DataAccessException {
		return productoTipoDAO.getByNombre(nombre);
	}
}
