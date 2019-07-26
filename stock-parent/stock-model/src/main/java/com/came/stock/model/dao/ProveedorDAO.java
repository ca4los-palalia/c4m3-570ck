package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Proveedor;

public abstract interface ProveedorDAO {
	public abstract void save(Proveedor paramProveedor);

	public abstract void update(Proveedor paramProveedor);

	public abstract void delete(Proveedor paramProveedor);

	public abstract Proveedor getById(Long paramLong);

	public abstract List<Proveedor> getProveedoresById(List<Long> paramList);

	public abstract List<Proveedor> getByContacto(Contacto paramContacto);

	public abstract List<Proveedor> getByContrato(Contrato paramContrato);

	public abstract List<Proveedor> getByDireccionDevolucion(Direccion paramDireccion);

	public abstract List<Proveedor> getByDireccionFiscal(Direccion paramDireccion);

	public abstract List<Proveedor> getByGerenteFinanzas(Persona paramPersona);

	public abstract List<Proveedor> getByGerenteVentas(Persona paramPersona);

	public abstract List<Proveedor> getByRepresentanteLegal(Persona paramPersona);

	public abstract List<Proveedor> getByRepresentanteClientes(Persona paramPersona);

	public abstract List<Proveedor> getAll();

	public abstract List<Proveedor> getBysClaveNombreRfc(String paramString);

	public abstract List<Proveedor> getByNombre(String paramString);
}
