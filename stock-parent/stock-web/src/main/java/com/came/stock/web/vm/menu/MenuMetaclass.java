package com.came.stock.web.vm.menu;

import java.util.ArrayList;
import java.util.Properties;

import org.zkoss.bind.annotation.Init;

import com.came.stock.model.domain.ItemMenu;

public class MenuMetaclass extends MenuVariables {
	private static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		PAGE_TO_RENDER = "pageToRender";
		
		moduloRequisiciones = new ArrayList<>();
		moduloCotizaciones = new ArrayList<>();
		moduloOrdenesCompra = new ArrayList<>();
		moduloPanelControl = new ArrayList<>();
		moduloPerfiles = new ArrayList<>();
	}

	public void initProperties() {
		leerVariablesProperties();
		
		ItemMenu requisicion = new ItemMenu();
		requisicion.setCsrIcon("/images/toolbar/text_enriched16.png");
		requisicion.setNombre(TAG_MENUVM_SUBTITLE1_ITEM_REQ);
		
		ItemMenu concentrado = new ItemMenu();
		concentrado.setCsrIcon("/images/toolbar/filter_list16.png");
		concentrado.setNombre(TAG_MENUVM_SUBTITLE1_ITEM_CONCENTR);
		
		moduloRequisiciones.add(requisicion);
		moduloRequisiciones.add(concentrado);
		
		//-----------------------
		
		ItemMenu cotizacion = new ItemMenu();
		cotizacion.setCsrIcon("/images/toolbar/list16.png");
		cotizacion.setNombre(TAG_MENUVM_SUBTITLE2_ITEM_COT);
		moduloCotizaciones.add(cotizacion);
		
		//-----------------------
		
		
		ItemMenu ordenCompra = new ItemMenu();
		ordenCompra.setCsrIcon("/images/toolbar/buy16.png");
		ordenCompra.setNombre(TAG_MENUVM_SUBTITLE3_ITEM_ORDENCOMPRA);
		
		ItemMenu control = new ItemMenu();
		control.setCsrIcon("/images/toolbar/control16.png");
		control.setNombre(TAG_MENUVM_SUBTITLE3_ITEM_CONTROL);
		
		ItemMenu kardexItem = new ItemMenu();
		kardexItem.setCsrIcon("/images/toolbar/control16.png");
		kardexItem.setNombre(TAG_MENUVM_SUBTITLE3_ITEM_KARDEX);
		
		moduloOrdenesCompra.add(ordenCompra);
		moduloOrdenesCompra.add(control);
		moduloOrdenesCompra.add(kardexItem);
		
		//----------------------------
		
		ItemMenu catalogos = new ItemMenu();
		catalogos.setCsrIcon("/images/toolbar/catalog16.png");
		catalogos.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_CATA);
		
		ItemMenu productos = new ItemMenu();
		productos.setCsrIcon("/images/toolbar/product16.png");
		productos.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_PRODUC);
		
		ItemMenu proveedores = new ItemMenu();
		proveedores.setCsrIcon("/images/toolbar/process.png");
		proveedores.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_PROVEEDOR);
		
		ItemMenu clientes = new ItemMenu();
		clientes.setCsrIcon("/images/toolbar/clients16.png");
		clientes.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_CLIENTES);
		
		ItemMenu usuarios = new ItemMenu();
		usuarios.setCsrIcon("/images/toolbar/user16.png");
		usuarios.setNombre(TAG_MENUVM_SUBTITLE4_ITEM_USUARIO);
		
		moduloPanelControl.add(catalogos);
		moduloPanelControl.add(productos);
		moduloPanelControl.add(proveedores);
		if(usuario.getOwner())
			moduloPanelControl.add(clientes);
		moduloPanelControl.add(usuarios);
		
		//------------------------------
		ItemMenu perfil = new ItemMenu();
		perfil.setCsrIcon("/images/toolbar/report.png");
		perfil.setNombre(TAG_MENUVM_SUBTITLE5_ITEM_PERFIL);
		moduloPerfiles.add(perfil);
		
	
	}
	
	private void leerVariablesProperties(){
		Properties propiedades = getPropertiesFiles();
		TAG_MENUVM_SUBTITLE1_REQ = propiedades.getProperty("menuVM.subTitle1.requiciones");// = Requisiciones
		TAG_MENUVM_SUBTITLE1_ITEM_REQ = propiedades.getProperty("menuVM.subTitle1.itemRequisicion");// = Requisición
		TAG_MENUVM_SUBTITLE1_ITEM_CONCENTR = propiedades.getProperty("menuVM.subTitle1.itemConcentrado");// = Concentrado
		TAG_MENUVM_SUBTITLE2_COTIZACIONES = propiedades.getProperty("menuVM.subTitle2.cotizaciones");// = Cotizaciones
		TAG_MENUVM_SUBTITLE2_ITEM_COT = propiedades.getProperty("menuVM.subTitle2.itemCotizacion");// = Cotización 
		TAG_MENUVM_SUBTITLE3_ORDENCOMPRA = propiedades.getProperty("menuVM.subTitle3.ordenCompra");// = Órdenes de compra
		TAG_MENUVM_SUBTITLE3_ITEM_ORDENCOMPRA = propiedades.getProperty("menuVM.subTitle3.itemOrdenCompra");// = Orden de compra
		TAG_MENUVM_SUBTITLE3_ITEM_CONTROL = propiedades.getProperty("menuVM.subTitle3.itemControl");// = Control de compras
		TAG_MENUVM_SUBTITLE3_ITEM_KARDEX = propiedades.getProperty("menuVM.subTitle3.itemKardex");// = Kardex
		
		
		TAG_MENUVM_SUBTITLE4_PANELCONTROL = propiedades.getProperty("menuVM.subTitle4.panelControl");// = Panel de control
		TAG_MENUVM_SUBTITLE4_ITEM_CATA = propiedades.getProperty("menuVM.subTitle4.itemCatalogo");// = Catálogos
		TAG_MENUVM_SUBTITLE4_ITEM_PRODUC = propiedades.getProperty("menuVM.subTitle4.itemProductos");// = Productos
		TAG_MENUVM_SUBTITLE4_ITEM_PROVEEDOR = propiedades.getProperty("menuVM.subTitle4.itemProveedores");// = Proveedores
		TAG_MENUVM_SUBTITLE4_ITEM_CLIENTES = propiedades.getProperty("menuVM.subTitle4.itemClientes");// = Clientes
		TAG_MENUVM_SUBTITLE4_ITEM_USUARIO = propiedades.getProperty("menuVM.subTitle4.itemUsuarios");// = Usuarios
		TAG_MENUVM_SUBTITLE5_PERFIL = propiedades.getProperty("menuVM.subTitle5.perfil");// = Perfil
		TAG_MENUVM_SUBTITLE5_ITEM_PERFIL = propiedades.getProperty("menuVM.subTitle5.itemMiPerfil");// = Mi Perfil
	}

}
