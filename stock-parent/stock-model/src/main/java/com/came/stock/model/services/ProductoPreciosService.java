package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoPreciosDAO;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoPrecios;

@Service
public class ProductoPreciosService {
	@Autowired
	private ProductoPreciosDAO productoPreciosDAO;

	public void save(ProductoPrecios productoPrecios) throws DataAccessException {
		productoPreciosDAO.save(productoPrecios);
	}

	public void delete(ProductoPrecios productoPrecios) throws DataAccessException {
		productoPreciosDAO.delete(productoPrecios);
	}

	public ProductoPrecios getById(Long idProductoPrecios) throws DataAccessException {
		return productoPreciosDAO.getById(idProductoPrecios);
	}

	public List<ProductoPrecios> getAll() throws DataAccessException {
		return productoPreciosDAO.getAll();
	}
	
	public ProductoPrecios getByDescripcionAndProducto(String clave, Producto producto) {
		return productoPreciosDAO.getByDescripcionAndProducto(clave, producto);
	}
	
	public List<ProductoPrecios> getByProductoOrderMostRecentDate(Producto producto) {
		return productoPreciosDAO.getByProductoOrderMostRecentDate(producto);
	}
}
