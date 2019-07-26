package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProductoDAO;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Producto;

@Service
public class ProductoService {
	@Autowired
	private ProductoDAO productoDAO;

	public void save(Producto producto) throws DataAccessException {
		productoDAO.save(producto);
	}

	public void delete(Producto producto) throws DataAccessException {
		productoDAO.delete(producto);
	}

	public Producto getById(Long idProducto) throws DataAccessException {
		return productoDAO.getById(idProducto);
	}

	public List<Producto> getAll() throws DataAccessException {
		return productoDAO.getAll();
	}
	
	public List<Producto> getAllNativeSQL() throws DataAccessException {
		return productoDAO.getAllNativeSQL();
	}
	
	public List<Producto> getAllLimited() throws DataAccessException {
		return productoDAO.getAllLimited();
	}

	public List<Producto> getItemByKeyOrName(String claveProducto, String nombreProducto) {
		return productoDAO.getItemByKeyOrName(claveProducto, nombreProducto);
	}

	public List<Producto> getAllKeys() {
		return productoDAO.getAllKeys();
	}

	public List<Producto> getByClaveNombre(String buscarTexto) {
		return productoDAO.getByClaveNombre(buscarTexto);
	}

	public List<Producto> getPreciosMaximos() {
		return productoDAO.getPreciosMaximos();
	}

	public List<Producto> getPreciosMinimos() {
		return productoDAO.getPreciosMinimos();
	}

	public List<Producto> getPreciosPromedio() {
		return productoDAO.getPreciosPromedio();
	}

	public List<Producto> getByPrecio(String precio) {
		return productoDAO.getByPrecio(precio);
	}

	public Producto getByClaveNombrePrecioCosto(String buscarTexto) {
		return productoDAO.getByClaveNombrePrecioCosto(buscarTexto);
	}

	public Producto getByClave(String clave) {
		return productoDAO.getByClave(clave);
	}
	
	public List<Producto> getByClavePartidaGenerica(ConffyaPartidaGenerica conffyaPartidaGenerica){
		return productoDAO.getByClavePartidaGenerica(conffyaPartidaGenerica);
	}
}
