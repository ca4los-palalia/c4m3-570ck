package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoNaturalezaDAO;
import com.came.stock.model.domain.ProductoNaturaleza;

@Service
public class ProductoNaturalezaService {
	@Autowired
	private ProductoNaturalezaDAO productoNaturalezaDAO;

	public void save(ProductoNaturaleza productoNaturaleza) {
		productoNaturalezaDAO.save(productoNaturaleza);
	}

	public void update(ProductoNaturaleza productoNaturaleza) {
		productoNaturalezaDAO.update(productoNaturaleza);
	}

	public void delete(ProductoNaturaleza productoNaturaleza) {
		productoNaturalezaDAO.delete(productoNaturaleza);
	}

	public ProductoNaturaleza getById(Long idProductoNaturaleza) {
		return productoNaturalezaDAO.getById(idProductoNaturaleza);
	}

	public List<ProductoNaturaleza> getAll() {
		return productoNaturalezaDAO.getAll();
	}

	public ProductoNaturaleza getByNombre(String nombre) {
		return productoNaturalezaDAO.getByNombre(nombre);
	}

	public ProductoNaturaleza getBySimbolo(String simbolo) {
		return productoNaturalezaDAO.getBySimbolo(simbolo);
	}
}
