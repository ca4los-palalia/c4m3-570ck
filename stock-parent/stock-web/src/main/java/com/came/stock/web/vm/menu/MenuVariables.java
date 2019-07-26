package com.came.stock.web.vm.menu;

import java.util.List;
import java.util.Map;

import com.came.stock.model.domain.ItemMenu;
import com.came.stock.web.vm.BasicStructure;

public class MenuVariables extends BasicStructure {

	private static final long serialVersionUID = 3599338863971941993L;

	protected String PAGE_TO_RENDER;
	protected Map<String, Object> args;
	protected boolean mostrarRequisiones;
	protected boolean mostrarConcentrados;
	protected boolean mostrarCotizacionesAutorizaciones;
	protected boolean mostrarOrdenesCompra;
	protected boolean mostrarPanelControl;
	protected boolean mostrarPerfil;
	protected boolean ownerOptions;
	protected boolean clientOptions;
	protected Integer indexBanner;
	protected String urlBAnner;
	
	protected List<ItemMenu> moduloRequisiciones;
	protected List<ItemMenu> moduloCotizaciones;
	protected List<ItemMenu> moduloOrdenesCompra;
	protected List<ItemMenu> moduloPanelControl;
	protected List<ItemMenu> moduloPerfiles;
	protected ItemMenu moduloRequisicionSelected;
	protected ItemMenu moduloCotizacionSelected;
	protected ItemMenu moduloOrdenesCompraSelected;
	protected ItemMenu moduloPanelControlSelected;
	protected ItemMenu moduloPerfilSelected;

	public boolean isMostrarRequisiones() {
		return mostrarRequisiones;
	}

	public void setMostrarRequisiones(boolean mostrarRequisiones) {
		this.mostrarRequisiones = mostrarRequisiones;
	}

	public boolean isMostrarConcentrados() {
		return mostrarConcentrados;
	}

	public void setMostrarConcentrados(boolean mostrarConcentrados) {
		this.mostrarConcentrados = mostrarConcentrados;
	}

	public boolean isMostrarCotizacionesAutorizaciones() {
		return mostrarCotizacionesAutorizaciones;
	}

	public void setMostrarCotizacionesAutorizaciones(boolean mostrarCotizacionesAutorizaciones) {
		this.mostrarCotizacionesAutorizaciones = mostrarCotizacionesAutorizaciones;
	}

	public boolean isMostrarOrdenesCompra() {
		return mostrarOrdenesCompra;
	}

	public void setMostrarOrdenesCompra(boolean mostrarOrdenesCompra) {
		this.mostrarOrdenesCompra = mostrarOrdenesCompra;
	}

	public boolean isMostrarPanelControl() {
		return mostrarPanelControl;
	}

	public void setMostrarPanelControl(boolean mostrarPanelControl) {
		this.mostrarPanelControl = mostrarPanelControl;
	}

	public boolean isOwnerOptions() {
		return ownerOptions;
	}

	public void setOwnerOptions(boolean ownerOptions) {
		this.ownerOptions = ownerOptions;
	}

	public boolean isClientOptions() {
		return clientOptions;
	}

	public void setClientOptions(boolean clientOptions) {
		this.clientOptions = clientOptions;
	}

	public String getUrlBAnner() {
		return urlBAnner;
	}

	public void setUrlBAnner(String urlBAnner) {
		this.urlBAnner = urlBAnner;
	}

	public List<ItemMenu> getModuloRequisiciones() {
		return moduloRequisiciones;
	}

	public void setModuloRequisiciones(List<ItemMenu> moduloRequisiciones) {
		this.moduloRequisiciones = moduloRequisiciones;
	}

	public List<ItemMenu> getModuloCotizaciones() {
		return moduloCotizaciones;
	}

	public void setModuloCotizaciones(List<ItemMenu> moduloCotizaciones) {
		this.moduloCotizaciones = moduloCotizaciones;
	}

	public List<ItemMenu> getModuloOrdenesCompra() {
		return moduloOrdenesCompra;
	}

	public void setModuloOrdenesCompra(List<ItemMenu> moduloOrdenesCompra) {
		this.moduloOrdenesCompra = moduloOrdenesCompra;
	}

	public List<ItemMenu> getModuloPanelControl() {
		return moduloPanelControl;
	}

	public void setModuloPanelControl(List<ItemMenu> moduloPanelControl) {
		this.moduloPanelControl = moduloPanelControl;
	}

	public List<ItemMenu> getModuloPerfiles() {
		return moduloPerfiles;
	}

	public void setModuloPerfiles(List<ItemMenu> moduloPerfiles) {
		this.moduloPerfiles = moduloPerfiles;
	}

	public ItemMenu getModuloRequisicionSelected() {
		return moduloRequisicionSelected;
	}

	public void setModuloRequisicionSelected(ItemMenu moduloRequisicionSelected) {
		this.moduloRequisicionSelected = moduloRequisicionSelected;
	}

	public ItemMenu getModuloCotizacionSelected() {
		return moduloCotizacionSelected;
	}

	public void setModuloCotizacionSelected(ItemMenu moduloCotizacionSelected) {
		this.moduloCotizacionSelected = moduloCotizacionSelected;
	}

	public ItemMenu getModuloOrdenesCompraSelected() {
		return moduloOrdenesCompraSelected;
	}

	public void setModuloOrdenesCompraSelected(ItemMenu moduloOrdenesCompraSelected) {
		this.moduloOrdenesCompraSelected = moduloOrdenesCompraSelected;
	}

	public ItemMenu getModuloPanelControlSelected() {
		return moduloPanelControlSelected;
	}

	public void setModuloPanelControlSelected(ItemMenu moduloPanelControlSelected) {
		this.moduloPanelControlSelected = moduloPanelControlSelected;
	}

	public ItemMenu getModuloPerfilSelected() {
		return moduloPerfilSelected;
	}

	public void setModuloPerfilSelected(ItemMenu moduloPerfilSelected) {
		this.moduloPerfilSelected = moduloPerfilSelected;
	}

	public boolean isMostrarPerfil() {
		return mostrarPerfil;
	}

	public void setMostrarPerfil(boolean mostrarPerfil) {
		this.mostrarPerfil = mostrarPerfil;
	}

	
	
	
}
