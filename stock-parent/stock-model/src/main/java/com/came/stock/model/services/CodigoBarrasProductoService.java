package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CodigoBarrasProductoDAO;
import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.Producto;

@Service
public class CodigoBarrasProductoService {
	@Autowired
	private CodigoBarrasProductoDAO codigoBarrasProductoDAO;

	public void save(CodigoBarrasProducto codigoBarrasProducto) {
		codigoBarrasProductoDAO.save(codigoBarrasProducto);
	}

	public void delete(CodigoBarrasProducto codigoBarrasProducto) {
		codigoBarrasProductoDAO.delete(codigoBarrasProducto);
	}

	public CodigoBarrasProducto getById(Long idCodigoBarrasProducto) {
		return codigoBarrasProductoDAO.getById(idCodigoBarrasProducto);
	}

	public List<CodigoBarrasProducto> getAll() {
		return codigoBarrasProductoDAO.getAll();
	}

	public List<CodigoBarrasProducto> getByCodigo(String codigo) {
		return codigoBarrasProductoDAO.getByCodigo(codigo);
	}

	public List<CodigoBarrasProducto> getByProducto(Producto producto) {
		return codigoBarrasProductoDAO.getByProducto(producto);
	}
}
