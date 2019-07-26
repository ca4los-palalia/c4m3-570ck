package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorUsuario;
import com.came.stock.model.domain.Usuarios;

public abstract interface ProveedorUsuarioDAO {

	void save(ProveedorUsuario proveedorUsuario);

	void delete(ProveedorUsuario proveedorUsuario);

	ProveedorUsuario getById(Long idProveedorUsuario);

	List<ProveedorUsuario> getAll();

	ProveedorUsuario getByProveedor(Proveedor proveedor);

	ProveedorUsuario getByUsuario(Usuarios usuario);

	List<ProveedorUsuario> getLikeNombre(String nombre);

	ProveedorUsuario getByNombre(String nombre);

	ProveedorUsuario getAcceso(String usuario, String contrasena);
	
}
