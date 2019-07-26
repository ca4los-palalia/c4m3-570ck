package com.came.stock.model.domain;

import java.util.List;

import com.came.stock.utilidades.UserPrivileges;

public abstract interface PrivilegioDAO {
	public abstract void save(Privilegios paramPrivilegios);

	public abstract void delete(Privilegios paramPrivilegios);

	public abstract List<Privilegios> getPrivilegiosByUsuario(Usuarios paramUsuarios);

	public abstract List<Privilegios> getUsuariosByPrivilegio(UserPrivileges paramUserPrivileges);
}
