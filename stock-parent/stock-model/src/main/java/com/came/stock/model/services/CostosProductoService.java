package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.CostosProductoDAO;
import com.came.stock.model.domain.CostosProducto;
import com.came.stock.model.domain.Producto;

@Service
public class CostosProductoService {
	@Autowired
	private CostosProductoDAO costosProductoDAO;

	public void save(CostosProducto costosProducto) {
		costosProductoDAO.save(costosProducto);
	}

	public void delete(CostosProducto costosProducto) {
		costosProductoDAO.delete(costosProducto);
	}

	public CostosProducto getById(Long idCodigoBarrasProducto) {
		return costosProductoDAO.getById(idCodigoBarrasProducto);
	}

	public List<CostosProducto> getAll() {
		return costosProductoDAO.getAll();
	}

	public CostosProducto getByProducto(Producto producto) {
		return costosProductoDAO.getByProducto(producto);
	}
}
