package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProveedorDAO;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Proveedor;

@Service
public class ProveedorService {
	@Autowired
	private ProveedorDAO proveedorDAO;

	public void save(Proveedor proveedor) throws DataAccessException {
		proveedorDAO.save(proveedor);
	}

	public void update(Proveedor proveedor) throws DataAccessException {
		proveedorDAO.update(proveedor);
	}

	public void delete(Proveedor proveedor) throws DataAccessException {
		proveedorDAO.delete(proveedor);
	}

	public Proveedor getById(Long idProveedor) {
		return proveedorDAO.getById(idProveedor);
	}

	public List<Proveedor> getByContacto(Contacto contacto) throws DataAccessException {
		return proveedorDAO.getByContacto(contacto);
	}

	public List<Proveedor> getByContrato(Contrato contrato) throws DataAccessException {
		return proveedorDAO.getByContrato(contrato);
	}

	public List<Proveedor> getByDireccionDevolucion(Direccion direccion) throws DataAccessException {
		return proveedorDAO.getByDireccionDevolucion(direccion);
	}

	public List<Proveedor> getByDireccionFiscal(Direccion direccion) throws DataAccessException {
		return proveedorDAO.getByDireccionFiscal(direccion);
	}

	public List<Proveedor> getByGerenteFinanzas(Persona persona) throws DataAccessException {
		return proveedorDAO.getByGerenteFinanzas(persona);
	}

	public List<Proveedor> getByGerenteVentas(Persona persona) throws DataAccessException {
		return proveedorDAO.getByGerenteVentas(persona);
	}

	public List<Proveedor> getByRepresentanteLegal(Persona persona) throws DataAccessException {
		return proveedorDAO.getByRepresentanteLegal(persona);
	}

	public List<Proveedor> getByRepresentanteClientes(Persona persona) throws DataAccessException {
		return proveedorDAO.getByRepresentanteClientes(persona);
	}

	public List<Proveedor> getAll() throws DataAccessException {
		return proveedorDAO.getAll();
	}

	public List<Proveedor> getBysClaveNombreRfc(String buscarTexto) {
		return proveedorDAO.getBysClaveNombreRfc(buscarTexto);
	}

	public List<Proveedor> getByNombre(String nombre) {
		return proveedorDAO.getByNombre(nombre);
	}

	public List<Proveedor> getProveedoresById(List<Long> idsProveedores) {
		return proveedorDAO.getProveedoresById(idsProveedores);
	}
}
