package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.OrdenCompraProductoDAO;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraProducto;
import com.came.stock.model.domain.Producto;

@Service
public class OrdenCompraProductoService {
	@Autowired
	private OrdenCompraProductoDAO ordenCompraProductoDAO;

	public void save(OrdenCompraProducto ordenCompraProducto) throws DataAccessException {
		ordenCompraProductoDAO.save(ordenCompraProducto);
	}

	public void delete(OrdenCompraProducto ordenCompraProducto) throws DataAccessException {
		ordenCompraProductoDAO.delete(ordenCompraProducto);
	}

	public OrdenCompraProducto getById(Long idOrdenCompraProducto) throws DataAccessException {
		return ordenCompraProductoDAO.getById(idOrdenCompraProducto);
	}

	public List<OrdenCompraProducto> getAll() throws DataAccessException {
		return ordenCompraProductoDAO.getAll();
	}

	public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra) throws DataAccessException {
		return ordenCompraProductoDAO.getByOrdenCopra(ordenCompra);
	}

	public List<OrdenCompraProducto> getByProducto(Producto producto) throws DataAccessException {
		return ordenCompraProductoDAO.getByProducto(producto);
	}
}
