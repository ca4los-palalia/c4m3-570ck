package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.came.stock.model.domain.PrivilegioDAO;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.UserPrivileges;

@Service
public class PrivilegioService {
	@Autowired
	private PrivilegioDAO privilegioDAO;

	public void save(Privilegios privilegios) {
		privilegioDAO.save(privilegios);
	}

	public void delete(Privilegios privilegios) {
		privilegioDAO.delete(privilegios);
	}

	public List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios) {
		return privilegioDAO.getPrivilegiosByUsuario(usuarios);
	}

	public List<Privilegios> getUsuariosByPrivilegio(UserPrivileges cotizarAutorizar) {
		return privilegioDAO.getUsuariosByPrivilegio(cotizarAutorizar);
	}
}
