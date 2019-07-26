package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.ModulosOrganizacionDAO;
import com.came.stock.model.domain.ModulosOrganizacion;
import com.came.stock.model.domain.Organizacion;

@Service
public class ModulosOrganizacionService {
	@Autowired
	private ModulosOrganizacionDAO modulosOrganizacionDAO;

	public void save(ModulosOrganizacion modulosOrganizacion) {
		modulosOrganizacionDAO.save(modulosOrganizacion);
	}

	public void delete(ModulosOrganizacion modulosOrganizacion) {
		modulosOrganizacionDAO.delete(modulosOrganizacion);
	}

	public List<ModulosOrganizacion> getModulosByOrganizacion(Organizacion organizacion) {
		return modulosOrganizacionDAO.getModulosByOrganizacion(organizacion);
	}
}
