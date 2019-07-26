package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoTopeDAO;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTope;

@Service
public class ProductoTopeService {
	@Autowired
	private ProductoTopeDAO productoTopeDAO;

	public void save(ProductoTope productoTope) throws DataAccessException {
		productoTopeDAO.save(productoTope);
	}

	public void delete(ProductoTope productoTope) throws DataAccessException {
		productoTopeDAO.delete(productoTope);
	}

	public ProductoTope getById(Long idProductoTope) throws DataAccessException {
		return productoTopeDAO.getById(idProductoTope);
	}

	public List<ProductoTope> getAll() throws DataAccessException {
		return productoTopeDAO.getAll();
	}

	public List<ProductoTope> getByProducto(Producto producto) throws DataAccessException {
		return productoTopeDAO.getByProducto(producto);
	}

	public List<ProductoTope> getByLugar(Lugar lugar) throws DataAccessException {
		return productoTopeDAO.getByLugar(lugar);
	}
}
