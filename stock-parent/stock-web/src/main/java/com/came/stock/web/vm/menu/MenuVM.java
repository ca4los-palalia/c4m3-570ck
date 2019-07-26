package com.came.stock.web.vm.menu;

import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Banner;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class MenuVM extends MenuMetaclass {
	private static final long serialVersionUID = -2153432633385920494L;
	private static final String PROCESSING_TEXT = "Processing...";

	private Window homeWindow;
	private Page paginaHome;

	@Wire("#mainWin")
	private Window mainWin;
	@Wire("#mainWindow")
	private Window main;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view) {
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
		super.init();
		args = new HashMap();
		loadPrivileges();
		bannerList = (List<Banner>) bannerRest.getAll().getSingle();
		indexBanner = 0;
		if (bannerList != null) {
			bannerGlobalAImage = bannerList.get(indexBanner).getLogotipoAImage();
			urlBAnner = bannerList.get(indexBanner).getUrl();
		}
		recuperarVersionDelSistema();
		getStyles();
	}
	
	private void getStyles(){
		styleGlobalColorFont = "color: " + StockConstants.COLOR_FONT_GLOBAL + ";";
		styleFontSubMenu  = "color: " + StockConstants.COLOR_FONT_GLOBAL + "; font-size: 80%;";
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		/*
		 * imgOne.setSrc("/images/toolbar/accept16.png");
		 * imgTwo.setSrc("/images/toolbar/arrowright32.png");
		 * imgThree.setSrc("/images/toolbar/buy16.png");
		 * Clients.evalJavaScript("setContent('" + getContentString() + "')");
		 */
		Page paginaHome = view.getPage();
		homeWindow = (Window) paginaHome.getFellow("mainWindow");
	}

	@NotifyChange({ "mostrarPanelControl", "mostrarConcentrados", "mostrarCotizacionesAutorizaciones",
			"mostrarOrdenesCompra", "mostrarRequisiones" })
	public void loadPrivileges() {
		usuario = ((Usuarios) sessionUtils.getFromSession("usuario"));
		if (usuario != null) {
			if ((usuario.getClient() != null) && (usuario.getClient().booleanValue())) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarPanelControl = true;
				ownerOptions = false;
				clientOptions = true;
				mostrarPerfil = true;
				return;
			}
			if ((usuario.getOwner() != null) && (usuario.getOwner().booleanValue())) {
				mostrarConcentrados = true;
				mostrarCotizacionesAutorizaciones = true;
				mostrarOrdenesCompra = true;
				mostrarRequisiones = true;
				mostrarPanelControl = true;
				ownerOptions = true;
				clientOptions = true;
				mostrarPerfil = true;
				return;
			}else if(!usuario.getOwner() && !usuario.getClient())
				mostrarPerfil = true;
			List<Privilegios> privilegios = (List<Privilegios>)privilegioRest.getPrivilegiosByUsuario(usuario).getSingle();
			if (privilegios != null) {
				for (Privilegios privilegio : privilegios) {
					switch (privilegio.getUserPrivileges()) {
					case CONCENTRAR:
						mostrarConcentrados = true;
						break;
					case COTIZAR_AUTORIZAR:
						mostrarCotizacionesAutorizaciones = true;
						break;
					case ORDEN_COMPRA:
						mostrarOrdenesCompra = true;
						break;
					case REQUISION:
						mostrarRequisiones = true;
						break;
					case PANEL_CONTROL:
						mostrarPanelControl = true;
					case PERFIL:
						mostrarPerfil = true;
					}
				}
			}
		}
	}

	@Command
	public void showProducts() {
		args.put("pageToRender", "/modulos/productos/productos.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showBuscadorProductos() {
		args.put("pageToRender", "/modulos/productos/productosBuscador.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);

	}

	@GlobalCommand
	@NotifyChange({ "*" })
	public void updateRecordFromRequisitionWithSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		System.err.println("retornando");
	}

	@Command
	public void showProvidersSearch() {
		args.put("pageToRender", "/modulos/proveedores/proveedoresBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	public void showRequisitions() {
		args.put("pageToRender", "/modulos/requisicion/requisicion.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showRequsicionBuscador() {
		args.put("pageToRender", "/modulos/requisicion/requisicionBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showConcentrado() {
		args.put("pageToRender", "/modulos/requisicion/concentrado.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showControl() {
		args.put("pageToRender", "/modulos/requisicion/control.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}
	
	@Command
	public void showKardex() {
		args.put("pageToRender", "/modulos/requisicion/kardex.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacion() {
		args.put("pageToRender", "/modulos/requisicion/cotizacion.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showCotizacionBuscador() {
		args.put("pageToRender", "/modulos/requisicion/cotizacionBuscador.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showOrders() {
		args.put("pageToRender", "/modulos/ordenCompra/ordenCompra.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	private void showPerfiles() {
		args.put("pageToRender", "/modulos/perfil/perfil.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void showControlPanel() {
		args.put("pageToRender", "/modulos/controlPanel/controlPanel.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void mostrarConfiguracionUsuario() {
		args.put("pageToRender", "/modulos/controlPanel/usuario.zul");

		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	public void configurarUsuariosNegocio() {
		args.put("pageToRender", "/modulos/controlPanel/usuariosCliente.zul");
		BindUtils.postGlobalCommand(null, null, "updateWorkArea", args);
	}

	@Command
	@NotifyChange({ "bannerGlobalAImage", "urlBAnner" })
	public void updateImage() {
		if (bannerList != null) {
			bannerGlobalAImage = bannerList.get(indexBanner).getLogotipoAImage();
			urlBAnner = bannerList.get(indexBanner).getUrl();
			indexBanner++;

			if (indexBanner >= bannerList.size())
				indexBanner = 0;
		}
	}

	@Command
	@NotifyChange({ "moduloCotizacionSelected", "moduloOrdenesCompraSelected", "moduloPanelControlSelected",
			"moduloPerfilSelected" })
	public void menuRequisicionSelected(@BindingParam("index") Integer index,
			@BindingParam("compItemSelect") Component comp) {
		switch (index) {
		case 0:
			showRequisitions();
			break;
		case 1:
			showConcentrado();
			break;
		}
		moduloCotizacionSelected = null;
		moduloOrdenesCompraSelected = null;
		moduloPanelControlSelected = null;
		moduloPerfilSelected = null;
	}

	@Command
	@NotifyChange({ "moduloRequisicionSelected", "moduloOrdenesCompraSelected", "moduloPanelControlSelected",
			"moduloPerfilSelected" })
	public void menuCotizacionItemSelected(@BindingParam("index") Integer index) {
		switch (index) {
		case 0:
			showCotizacion();
			break;
		}
		moduloRequisicionSelected = null;
		moduloOrdenesCompraSelected = null;
		moduloPanelControlSelected = null;
		moduloPerfilSelected = null;
	}

	@Command
	@NotifyChange({ "moduloRequisicionSelected", "moduloCotizacionSelected", "moduloPanelControlSelected",
			"moduloPerfilSelected" })
	public void menuOrdenCompraSelected(@BindingParam("index") Integer index) {
		switch (index) {
		case 0:
			showOrders();
			break;
		case 1:
			showControl();
			break;
		case 2:
			showKardex();
			break;
		}
		moduloRequisicionSelected = null;
		moduloCotizacionSelected = null;
		moduloPanelControlSelected = null;
		moduloPerfilSelected = null;
	}

	@Command
	@NotifyChange({ "moduloRequisicionSelected", "moduloCotizacionSelected", "moduloOrdenesCompraSelected",
			"moduloPerfilSelected" })
	public void menuPanelControlSelected(@BindingParam("index") Integer index) {
		
		if(moduloPanelControlSelected.getNombre().equals(TAG_MENUVM_SUBTITLE4_ITEM_CATA)){
			showControlPanel();
		}else if(moduloPanelControlSelected.getNombre().equals(TAG_MENUVM_SUBTITLE4_ITEM_PRODUC)){
			showBuscadorProductos();
		}else if(moduloPanelControlSelected.getNombre().equals(TAG_MENUVM_SUBTITLE4_ITEM_PROVEEDOR)){
			showProvidersSearch();
		}else if(moduloPanelControlSelected.getNombre().equals(TAG_MENUVM_SUBTITLE4_ITEM_CLIENTES)){
			mostrarConfiguracionUsuario();
		}else if(moduloPanelControlSelected.getNombre().equals(TAG_MENUVM_SUBTITLE4_ITEM_USUARIO)){
			configurarUsuariosNegocio();
		}
		moduloRequisicionSelected = null;
		moduloCotizacionSelected = null;
		moduloOrdenesCompraSelected = null;
		moduloPerfilSelected = null;
	}

	@Command
	@NotifyChange({ "moduloRequisicionSelected", "moduloCotizacionSelected", "moduloOrdenesCompraSelected",
			"moduloPanelControlSelected" })
	public void menuPerfilSelected(@BindingParam("index") Integer index) {
		switch (index) {
		case 0:
			showPerfiles();
			break;
		case 1:
			break;
		}
		moduloRequisicionSelected = null;
		moduloCotizacionSelected = null;
		moduloOrdenesCompraSelected = null;
		moduloPanelControlSelected = null;

	}

}
