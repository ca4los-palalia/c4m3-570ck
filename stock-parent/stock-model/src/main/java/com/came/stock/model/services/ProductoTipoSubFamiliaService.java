package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoTipoSubFamiliaDAO;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.ProductoTipoSubFamilia;

@Service
public class ProductoTipoSubFamiliaService {
	@Autowired
	private ProductoTipoSubFamiliaDAO productoTipoSubFamiliaDAO;

	public void save(ProductoTipoSubFamilia productoTipoSubFamilia) throws DataAccessException {
		productoTipoSubFamiliaDAO.save(productoTipoSubFamilia);
	}

	public void delete(ProductoTipoSubFamilia productoTipoSubFamilia) throws DataAccessException {
		productoTipoSubFamiliaDAO.delete(productoTipoSubFamilia);
	}

	public ProductoTipoSubFamilia getById(Long idProductoTipoSubFamilia) throws DataAccessException {
		return productoTipoSubFamiliaDAO.getById(idProductoTipoSubFamilia);
	}

	public List<ProductoTipoSubFamilia> getAll() throws DataAccessException {
		return productoTipoSubFamiliaDAO.getAll();
	}

	public ProductoTipoSubFamilia getByNombre(String nombre) throws DataAccessException {
		return productoTipoSubFamiliaDAO.getByNombre(nombre);
	}
	
	public List<ProductoTipoSubFamilia> getByProductoTipo(ProductoTipo productoTipo) throws DataAccessException {
		return productoTipoSubFamiliaDAO.getByProductoTipo(productoTipo);
	}
}
