package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.ModulosOrganizacion;
import com.came.stock.model.domain.Organizacion;

public abstract interface ModulosOrganizacionDAO {
	public abstract void delete(ModulosOrganizacion paramModulosOrganizacion);

	public abstract List<ModulosOrganizacion> getModulosByOrganizacion(Organizacion paramOrganizacion);

	public abstract void save(ModulosOrganizacion paramModulosOrganizacion);
}
