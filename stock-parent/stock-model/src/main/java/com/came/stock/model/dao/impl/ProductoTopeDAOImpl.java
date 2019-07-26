package com.came.stock.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.came.stock.model.DatabaseMetaclass;
import com.came.stock.model.dao.ProductoTopeDAO;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTope;

@Repository
public class ProductoTopeDAOImpl extends DatabaseMetaclass implements ProductoTopeDAO {
	public void save(ProductoTope productoTope) {
	}

	public void update(ProductoTope productoTope) {
	}

	public void delete(ProductoTope productoTope) {
	}

	public ProductoTope getById(Long idProductoTope) {
		return null;
	}

	public List<ProductoTope> getAll() {
		return null;
	}

	public List<ProductoTope> getByProducto(Producto producto) {
		return null;
	}

	public List<ProductoTope> getByLugar(Lugar lugar) {
		return null;
	}
}
