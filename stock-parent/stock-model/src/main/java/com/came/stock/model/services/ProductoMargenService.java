package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoMargenDAO;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoMargen;

@Service
public class ProductoMargenService {
	@Autowired
	private ProductoMargenDAO productoMargenDAO;

	public void save(ProductoMargen productoMargen) throws DataAccessException {
		productoMargenDAO.save(productoMargen);
	}

	public void delete(ProductoMargen productoMargen) throws DataAccessException {
		productoMargenDAO.delete(productoMargen);
	}

	public ProductoMargen getById(Long idProductoMargen) throws DataAccessException {
		return productoMargenDAO.getById(idProductoMargen);
	}

	public List<ProductoMargen> getAll() throws DataAccessException {
		return productoMargenDAO.getAll();
	}
	
	public ProductoMargen getByDescripcionAndProducto(String descripcion, Producto producto) {
		return productoMargenDAO.getByDescripcionAndProducto(descripcion, producto);
	}
	
	public List<ProductoMargen> getByProductoOrderMostRecentDate(Producto producto) {
		return productoMargenDAO.getByProductoOrderMostRecentDate(producto);
	}
}
