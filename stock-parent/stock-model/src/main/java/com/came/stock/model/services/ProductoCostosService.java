package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoCostosDAO;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;


@Service
public class ProductoCostosService {
	@Autowired
	private ProductoCostosDAO productoCostosDAO;

	public void save(ProductoCostos productoCostos) throws DataAccessException {
		productoCostosDAO.save(productoCostos);
	}

	public void delete(ProductoCostos productoCostos) throws DataAccessException {
		productoCostosDAO.delete(productoCostos);
	}

	public ProductoCostos getById(Long idProductoCostos) throws DataAccessException {
		return productoCostosDAO.getById(idProductoCostos);
	}

	public List<ProductoCostos> getAll() throws DataAccessException {
		return productoCostosDAO.getAll();
	}
	
	public ProductoCostos getByDescripcionAndProducto(String descripcion, Producto producto) {
		return productoCostosDAO.getByDescripcionAndProducto(descripcion, producto);
	}
	
	public List<ProductoCostos> getByProductoOrderMostRecentDate(Producto producto) {
		return productoCostosDAO.getByProductoOrderMostRecentDate(producto);
	}
}
