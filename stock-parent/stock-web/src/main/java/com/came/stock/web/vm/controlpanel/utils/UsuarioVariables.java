package com.came.stock.web.vm.controlpanel.utils;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;

import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.web.vm.BasicStructure;

public class UsuarioVariables extends BasicStructure {
	private static final long serialVersionUID = 1814036749677443421L;
	/*
	@Wire("#clientesWindowContainer")
	protected Window compClientesWindowContainer;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal") 
	protected Borderlayout compBorderLayoutProncipal;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #northMenuCommand")
	protected North compNorthMenuCommand;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #northMenuCommand, #windowMenuCommand")
	protected Window compWindowMenuCommand;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #northMenuCommand, #windowMenuCommand, #btnClienteNuevaOrg")
	protected Button compBtnClienteNuevaOrg;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #northMenuCommand, #windowMenuCommand, #btnClienteSaveOrg")
	protected Button compBtnClienteSaveOrg;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #northMenuCommand, #windowMenuCommand, #btnClienteDeleteOrg")
	protected Button compBtnClienteDeleteOrg;
	
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes")
	protected Center compCenterOperatingClientes;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating") 
	protected Borderlayout compBorderLayoutDetailOperating;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes") 
	protected Center compCenterBuscadorClientes;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda") 
	protected Borderlayout compBorderLayoutOrdenarBusqueda;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda, #northFiltrandoBusqueda") 
	protected North compNorthFiltrandoBusqueda;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda, #northFiltrandoBusqueda, #windowFilteringBusqueda") 
	protected Window compWindowFilteringBusqueda;*/
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda, #northFiltrandoBusqueda, #windowFilteringBusqueda, #txtBuscarClientes") 
	protected Textbox compTxtBuscarClientes;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda, #northFiltrandoBusqueda, #windowFilteringBusqueda, #btnBuscrClientes") 
	protected Button compBtnBuscrClientes;
	/*
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda, #centerResultQuery") 
	protected Center compCenterResultQuery;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda, #centerResultQuery, #windowResultQuery") 
	protected Window compWindowResultQuery;*/
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #centerBuscadorClientes, #borderLayoutOrdenarBusqueda, #centerResultQuery, #windowResultQuery, #listResultadosClientes") 
	protected Listbox compListResultadosClientes;
	/*
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient") 
	protected East compEastDetailInfoClient;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient") 
	protected Window compWindowDetailInfoClient;*/
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtNombreOrganizacion") 
	protected Textbox compTxtNombreOrganizacion;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #dateBoxFechaCaducidad") 
	protected Datebox compDateBoxFechaCaducidad;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtCalleOrg")
	protected Textbox compTxtCalleOrg;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtCp")
	protected Textbox compTxtCp;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtRfc")
	protected Textbox compTxtRfc;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtNombreUsuario")
	protected Textbox compTxtNombreUsuario;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtUsuarioAlias")
	protected Textbox compTxtUsuarioAlias;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtContrasena1")
	protected Textbox compTxtContrasena1;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #txtContrasena2")
	protected Textbox compTxtContrasena2;
	@Wire("#clientesWindowContainer, #borderLayoutProncipal, #centerOperatingClientes, #borderLayoutDetailOperating, #eastDetailInfoClient, #windowDetailInfoClient, #checkViewContrasena")
	protected Checkbox compCheckViewContrasena;
	
	
	
	
	protected String busquedaFilter;
	protected String typePassword;
	protected boolean checkPass;
	protected int indiceSelected;

	public void init() {
		
		usuario = new Usuarios();
		Organizacion org = new Organizacion();
		org.setDireccion(new Direccion());
		usuario.setOrganizacion(org);
		usuario.setPersona(new Persona());
		
		typePassword = "password";
		checkPass = false;
	}
	public String getBusquedaFilter() {
		return busquedaFilter;
	}

	public void setBusquedaFilter(String busquedaFilter) {
		this.busquedaFilter = busquedaFilter;
	}

	public String getTypePassword() {
		return typePassword;
	}

	public void setTypePassword(String typePassword) {
		this.typePassword = typePassword;
	}

	public boolean isCheckPass() {
		return checkPass;
	}

	public void setCheckPass(boolean checkPass) {
		this.checkPass = checkPass;
	}
	public Textbox getCompTxtBuscarClientes() {
		return compTxtBuscarClientes;
	}
	public Button getCompBtnBuscrClientes() {
		return compBtnBuscrClientes;
	}
	public Listbox getCompListResultadosClientes() {
		return compListResultadosClientes;
	}
	public Textbox getCompTxtNombreOrganizacion() {
		return compTxtNombreOrganizacion;
	}
	public Datebox getCompDateBoxFechaCaducidad() {
		return compDateBoxFechaCaducidad;
	}
	public Textbox getCompTxtCalleOrg() {
		return compTxtCalleOrg;
	}
	public Textbox getCompTxtCp() {
		return compTxtCp;
	}
	public Textbox getCompTxtRfc() {
		return compTxtRfc;
	}
	public Textbox getCompTxtNombreUsuario() {
		return compTxtNombreUsuario;
	}
	public Textbox getCompTxtUsuarioAlias() {
		return compTxtUsuarioAlias;
	}
	public Textbox getCompTxtContrasena1() {
		return compTxtContrasena1;
	}
	public Textbox getCompTxtContrasena2() {
		return compTxtContrasena2;
	}
	public Checkbox getCompCheckViewContrasena() {
		return compCheckViewContrasena;
	}
	

	
	
}
