package com.came.stock.model.dao;

import java.util.List;

import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Usuarios;

public abstract interface UsuarioDAO {
	public abstract Usuarios getUsuarioByCredentials(String paramString1, String paramString2);

	public abstract void save(Usuarios paramUsuarios);

	public abstract void delete(Usuarios paramUsuarios);

	public abstract Usuarios getUsuarioById(Long idUsuario);

	public abstract List<Usuarios> getUsuariosByOrganizacion(Organizacion paramOrganizacion);

	public abstract boolean verificarNombreUsuario(String paramString, Long paramLong);

	public abstract Usuarios getClienteByOrganizacion(Organizacion paramOrganizacion);

	public abstract Usuarios getOwner();

	public abstract List<Usuarios> getUsuariosByOrganizacionAll(Organizacion paramOrganizacion);

	public abstract List<Usuarios> getAll();

	public abstract List<Usuarios> getUsuariosClienteAll();
	
	public abstract List<Usuarios> getUsuariosClienteAllActiveUsrs(boolean activo);

	public abstract List<Usuarios> getUsuariosClienteAllActiveUsrsByOrg(Organizacion org, boolean activo);

	public abstract List<Usuarios> getUsuariosLikeNombreOrganizacion(String nombreOrganizacion);

	public abstract List<Usuarios> getUsuariosLikeRfcOrganizacion(String rfcOrganizacion);

	public abstract List<Usuarios> getUsuariosLikeNumeroOrganizacion(Long numeroOrganizacion);

}
