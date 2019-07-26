package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Organizacion;

public abstract interface OrganizacionDAO {
	public abstract void save(Organizacion paramOrganizacion);

	public abstract void delete(Organizacion paramOrganizacion);

	public abstract Organizacion getById(Long paramLong);

	public abstract List<Organizacion> getOrganizaciones();

	public abstract List<Organizacion> getCompaniasByNombreRFC(String paramString1, String paramString2);

	public abstract List<Organizacion> getCompaniasByNombre(String paramString);

	public abstract List<Organizacion> getCompaniasByRFC(String paramString);

	public abstract List<Organizacion> getAll();

	public abstract List<Organizacion> getAllProveedores();
	
	public Integer getCountRows();
}
