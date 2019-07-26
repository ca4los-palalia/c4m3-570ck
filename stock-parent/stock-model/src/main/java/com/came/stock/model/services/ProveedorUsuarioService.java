package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ProveedorUsuarioDAO;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorUsuario;
import com.came.stock.model.domain.Usuarios;

@Service
public class ProveedorUsuarioService {
	@Autowired
	private ProveedorUsuarioDAO proveedorUsuarioDAO;

	public void save(ProveedorUsuario proveedorUsuario) throws DataAccessException {
		proveedorUsuarioDAO.save(proveedorUsuario);
	}

	public void delete(ProveedorUsuario proveedorUsuario) throws DataAccessException {
		proveedorUsuarioDAO.delete(proveedorUsuario);
	}
	
	public ProveedorUsuario getById(Long idProveedorUsuario) throws DataAccessException {
		return proveedorUsuarioDAO.getById(idProveedorUsuario);
	}
	
	public List<ProveedorUsuario> getAll() throws DataAccessException {
		return proveedorUsuarioDAO.getAll();
	}
	
	public ProveedorUsuario getByProveedor(Proveedor proveedor) throws DataAccessException {
		return proveedorUsuarioDAO.getByProveedor(proveedor);
	}
	
	public ProveedorUsuario getByUsuario(Usuarios usuario) throws DataAccessException {
		return proveedorUsuarioDAO.getByUsuario(usuario);
	}
	
	public List<ProveedorUsuario> getLikeNombre(String nombre) throws DataAccessException {
		return proveedorUsuarioDAO.getLikeNombre(nombre);
	}
	
	public ProveedorUsuario getByNombre(String nombre) throws DataAccessException {
		return proveedorUsuarioDAO.getByNombre(nombre);
	}
	
	public ProveedorUsuario getAcceso(String usuario, String contrasena) throws DataAccessException {
		return proveedorUsuarioDAO.getAcceso(usuario, contrasena);
	}
}
