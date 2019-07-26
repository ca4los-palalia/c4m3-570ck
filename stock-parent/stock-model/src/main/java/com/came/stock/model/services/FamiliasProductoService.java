package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.FamiliasProductoDAO;
import com.came.stock.model.domain.FamiliasProducto;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTipo;

@Service
public class FamiliasProductoService {
	@Autowired
	private FamiliasProductoDAO familiasProductoDAO;

	public void save(FamiliasProducto familiasProducto) {
		familiasProductoDAO.save(familiasProducto);
	}

	public void delete(FamiliasProducto familiasProducto) {
		familiasProductoDAO.delete(familiasProducto);
	}

	public FamiliasProducto getById(Long idFamiliasProducto) {
		return familiasProductoDAO.getById(idFamiliasProducto);
	}

	public List<FamiliasProducto> getAll() {
		return familiasProductoDAO.getAll();
	}

	public List<FamiliasProducto> getByProducto(Producto producto) {
		return familiasProductoDAO.getByProducto(producto);
	}

	public List<FamiliasProducto> getByFamilia(ProductoTipo productoTipo) {
		return familiasProductoDAO.getByFamilia(productoTipo);
	}
	public FamiliasProducto getByProductoProductoTipo(Producto producto, ProductoTipo productoTipo) {
		return familiasProductoDAO.getByProductoProductoTipo(producto, productoTipo);
	}
}
