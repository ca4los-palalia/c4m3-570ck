package com.came.stock.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.came.stock.model.dao.UsuarioDAO;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Usuarios;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioDAO usuarioDAO;

	public Usuarios getUsuarioByCredentials(String usuario, String password) throws DataAccessException {
		return usuarioDAO.getUsuarioByCredentials(usuario, password);
	}

	public void save(Usuarios usuarios) throws DataAccessException  {
		usuarioDAO.save(usuarios);
	}

	public void delete(Usuarios usuario) throws DataAccessException  {
		usuarioDAO.delete(usuario);
	}

	public List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion) {
		return usuarioDAO.getUsuariosByOrganizacion(organizacion);
	}

	public boolean verificarNombreUsuario(String benutzer, Long idUsuario) {
		return usuarioDAO.verificarNombreUsuario(benutzer, idUsuario);
	}

	public Usuarios getClienteByOrganizacion(Organizacion organizacion) {
		return usuarioDAO.getClienteByOrganizacion(organizacion);
	}

	public Usuarios getOwner() {
		return usuarioDAO.getOwner();
	}

	public List<Usuarios> getUsuariosByOrganizacionAll(Organizacion organizacion) {
		return usuarioDAO.getUsuariosByOrganizacionAll(organizacion);
	}

	public List<Usuarios> getAll() {
		return usuarioDAO.getAll();
	}
	
	public List<Usuarios> getUsuariosClienteAll(){
		return usuarioDAO.getUsuariosClienteAll();
	}
	
	public List<Usuarios> getUsuariosClienteAllActiveUsrs(boolean activo){
		return usuarioDAO.getUsuariosClienteAllActiveUsrs(activo);
	}
	
	public List<Usuarios> getUsuariosClienteAllActiveUsrsByOrg(Organizacion org, boolean activo){
		return usuarioDAO.getUsuariosClienteAllActiveUsrsByOrg(org, activo);
	}
	
	public List<Usuarios> getUsuariosLikeNombreOrganizacion(String nombreOrganizacion){
		return usuarioDAO.getUsuariosLikeNombreOrganizacion(nombreOrganizacion);
	}
	
	public List<Usuarios> getUsuariosLikeRfcOrganizacion(String rfcOrganizacion){
		return usuarioDAO.getUsuariosLikeRfcOrganizacion(rfcOrganizacion);
	}
	
	public List<Usuarios> getUsuariosLikeNumeroOrganizacion(Long numeroOrganizacion){
		return usuarioDAO.getUsuariosLikeNumeroOrganizacion(numeroOrganizacion);
	}
	
	public Usuarios getUsuarioById(Long idUsuario){
		return usuarioDAO.getUsuarioById(idUsuario);
	}
}
