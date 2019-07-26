package com.came.stock.web.vm.controlpanel.utils;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.North;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.web.vm.BasicStructure;

public class UsuariosClientesVariables extends BasicStructure {
	private static final long serialVersionUID = 8459358422760322689L;
	protected List<Usuarios> usuarios;
	protected Usuarios usuarioSeleccionado;
	protected Organizacion organizacion;
	protected Boolean privilegioRequision = Boolean.valueOf(false);
	protected Boolean privilegioConcentrado = Boolean.valueOf(false);
	protected Boolean privilegioCotizacionAutorizacion = Boolean.valueOf(false);
	protected Boolean privilegioOrdenCompra = Boolean.valueOf(false);
	protected Privilegios toRemove = null;
	protected String typePassword = "password";
	protected boolean verPassword;
	
	@Wire("#userClientWindow")
	protected Window compWindowMain;
	
	@Wire("#userClientWindow, #borderlayoutContainer") 
	protected Borderlayout compBorderlayoutContainer;
	
	@Wire("#userClientWindow, #borderlayoutContainer, #northContainer") 
	protected North compNorthContainer;
	
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer")
	protected Center compCenterContainer;
	
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario")
	protected Window compWindowFormulario;
	
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputNombre")
	protected Textbox compInputNombre;
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputPaterno")
	protected Textbox compInputPaterno;
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputMaterno")
	protected Textbox compInputMaterno;
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputUsuario")
	protected Textbox compInputUsuario;
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputPassword")
	protected Textbox compInputPassword;
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputPasswordRetype")
	protected Textbox compInputPasswordRetype;
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputEmail")
	protected Textbox compInputEmail;
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #inputUr")
	protected Textbox compInputUr;
	
	@Wire("#userClientWindow, #borderlayoutContainer, #centerContainer, #windowFormulario, #filaPermisos")
	protected Row compInputFilaPermisos;
		

	public void init() {
		organizacion = ((Organizacion) sessionUtils.getFromSession("FIRMA"));
		
		usuarios = (List<Usuarios>) usuarioRest.getUsuariosByOrganizacion(organizacion).getSingle();
		areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
		usuarioSeleccionado = new Usuarios();
		Persona persona = new Persona();
		Contacto contacto = new Contacto();
		contacto.setEmail(new Email());
		persona.setContacto(contacto);
		usuarioSeleccionado.setPersona(persona);
		usuarioSeleccionado.setOrganizacion(organizacion);
		usuarioSeleccionado.setArea(new Area());
		privilegioOrdenCompra = false;
		privilegioCotizacionAutorizacion = false;
		privilegioConcentrado = false;
		privilegioRequision = false;
		
		if (usuarios == null) {
			usuarios = new ArrayList();
		} else {
			for (Usuarios usuario : usuarios) {
				if (usuario.getPersona().getContacto() != null) {
					usuario.getPersona().setContacto((Contacto)
							contactoRest.getByEmail(usuario.getPersona().getContacto().getEmail()).getSingle());
				} else {
					Email email = new Email();
					contacto = new Contacto();
					contacto.setEmail(email);
					usuario.getPersona().setContacto(contacto);
				}
				usuario.setRetypeKennwort(usuario.getKennwort());
				usuario.setPrivilegios((List<Privilegios>)privilegioRest.getPrivilegiosByUsuario(usuario).getSingle());
			}
		}
	}

	public List<Usuarios> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuarios> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuarios getUsuarioSeleccionado() {
		return this.usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public Boolean getPrivilegioRequision() {
		return this.privilegioRequision;
	}

	public void setPrivilegioRequision(Boolean privilegioRequision) {
		this.privilegioRequision = privilegioRequision;
	}

	public Boolean getPrivilegioConcentrado() {
		return this.privilegioConcentrado;
	}

	public void setPrivilegioConcentrado(Boolean privilegioConcentrado) {
		this.privilegioConcentrado = privilegioConcentrado;
	}

	public Boolean getPrivilegioCotizacionAutorizacion() {
		return this.privilegioCotizacionAutorizacion;
	}

	public void setPrivilegioCotizacionAutorizacion(Boolean privilegioCotizacionAutorizacion) {
		this.privilegioCotizacionAutorizacion = privilegioCotizacionAutorizacion;
	}

	public Boolean getPrivilegioOrdenCompra() {
		return this.privilegioOrdenCompra;
	}

	public void setPrivilegioOrdenCompra(Boolean privilegioOrdenCompra) {
		this.privilegioOrdenCompra = privilegioOrdenCompra;
	}

	public Window getCompWindowMain() {
		return compWindowMain;
	}

	public void setCompWindowMain(Window compWindowMain) {
		this.compWindowMain = compWindowMain;
	}

	public Borderlayout getCompBorderlayoutContainer() {
		return compBorderlayoutContainer;
	}

	public void setCompBorderlayoutContainer(Borderlayout compBorderlayoutContainer) {
		this.compBorderlayoutContainer = compBorderlayoutContainer;
	}

	public North getCompNorthContainer() {
		return compNorthContainer;
	}

	public void setCompNorthContainer(North compNorthContainer) {
		this.compNorthContainer = compNorthContainer;
	}

	public Center getCompCenterContainer() {
		return compCenterContainer;
	}

	public void setCompCenterContainer(Center compCenterContainer) {
		this.compCenterContainer = compCenterContainer;
	}

	public Window getCompWindowFormulario() {
		return compWindowFormulario;
	}

	public void setCompWindowFormulario(Window compWindowFormulario) {
		this.compWindowFormulario = compWindowFormulario;
	}

	public Textbox getCompInputNombre() {
		return compInputNombre;
	}

	public void setCompInputNombre(Textbox compInputNombre) {
		this.compInputNombre = compInputNombre;
	}

	public Textbox getCompInputPaterno() {
		return compInputPaterno;
	}

	public void setCompInputPaterno(Textbox compInputPaterno) {
		this.compInputPaterno = compInputPaterno;
	}

	public Textbox getCompInputMaterno() {
		return compInputMaterno;
	}

	public void setCompInputMaterno(Textbox compInputMaterno) {
		this.compInputMaterno = compInputMaterno;
	}

	public Textbox getCompInputUsuario() {
		return compInputUsuario;
	}

	public void setCompInputUsuario(Textbox compInputUsuario) {
		this.compInputUsuario = compInputUsuario;
	}

	public Textbox getCompInputPassword() {
		return compInputPassword;
	}

	public void setCompInputPassword(Textbox compInputPassword) {
		this.compInputPassword = compInputPassword;
	}

	public Textbox getCompInputPasswordRetype() {
		return compInputPasswordRetype;
	}

	public void setCompInputPasswordRetype(Textbox compInputPasswordRetype) {
		this.compInputPasswordRetype = compInputPasswordRetype;
	}

	public Textbox getCompInputEmail() {
		return compInputEmail;
	}

	public void setCompInputEmail(Textbox compInputEmail) {
		this.compInputEmail = compInputEmail;
	}

	public Textbox getCompInputUr() {
		return compInputUr;
	}

	public void setCompInputUr(Textbox compInputUr) {
		this.compInputUr = compInputUr;
	}

	public String getTypePassword() {
		return typePassword;
	}

	public void setTypePassword(String typePassword) {
		this.typePassword = typePassword;
	}

	public boolean isVerPassword() {
		return verPassword;
	}

	public void setVerPassword(boolean verPassword) {
		this.verPassword = verPassword;
	}

	public Row getCompInputFilaPermisos() {
		return compInputFilaPermisos;
	}

	public void setCompInputFilaPermisos(Row compInputFilaPermisos) {
		this.compInputFilaPermisos = compInputFilaPermisos;
	}


	
}
