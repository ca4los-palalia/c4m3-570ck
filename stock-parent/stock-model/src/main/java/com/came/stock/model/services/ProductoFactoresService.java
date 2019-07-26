package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoFactoresDAO;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoFactores;

@Service
public class ProductoFactoresService {
	@Autowired
	private ProductoFactoresDAO productoFactoresDAO;

	public void save(ProductoFactores productoFactores) throws DataAccessException {
		productoFactoresDAO.save(productoFactores);
	}

	public void delete(ProductoFactores productoFactores) throws DataAccessException {
		productoFactoresDAO.delete(productoFactores);
	}

	public ProductoFactores getById(Long idProductoFactores) throws DataAccessException {
		return productoFactoresDAO.getById(idProductoFactores);
	}

	public List<ProductoFactores> getAll() throws DataAccessException {
		return productoFactoresDAO.getAll();
	}
	
	public ProductoFactores getByDescripcionAndProducto(String descripcion, Producto producto) {
		return productoFactoresDAO.getByDescripcionAndProducto(descripcion, producto);
	}
	
	public List<ProductoFactores> getByProductoOrderMostRecentDate(Producto producto) {
		return productoFactoresDAO.getByProductoOrderMostRecentDate(producto);
	}
}
