package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.OrdenCompraProductoDAO;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraProducto;
import com.came.stock.model.domain.Producto;

@Repository
public class OrdenCompraProductoDAOImpl extends DatabaseMetaclass implements OrdenCompraProductoDAO {
	public void save(OrdenCompraProducto ordenCompraProducto) {
	}

	public void update(OrdenCompraProducto ordenCompraProducto) {
	}

	public void delete(OrdenCompraProducto ordenCompraProducto) {
	}

	public OrdenCompraProducto getById(Long idOrdenCompraProducto) {
		return null;
	}

	public List<OrdenCompraProducto> getAll() {
		return null;
	}

	public List<OrdenCompraProducto> getByOrdenCopra(OrdenCompra ordenCompra) {
		return null;
	}

	public List<OrdenCompraProducto> getByProducto(Producto producto) {
		return null;
	}
}
