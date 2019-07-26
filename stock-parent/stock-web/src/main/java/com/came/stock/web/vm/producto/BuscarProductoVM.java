package com.came.stock.web.vm.producto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Producto;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class BuscarProductoVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#productosModalDialog")
	private Window productosModalDialog;
	private String claveProducto;
	private String nombreProducto;
	private Producto productoSeleccionado;
	private List<Producto> productos;
	private List<Producto> initProductos;
	private String globalCommandName;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoProductos") List<Producto> catalogoProductos,
			@ExecutionArgParam("partidaGenerica") ConffyaPartidaGenerica partidaGenerica) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		
		//productos = catalogoProductos;
		productos = (List<Producto>) productoRest.getByClavePartidaGenerica(partidaGenerica, organizacion).getSingle();
		initProductos = catalogoProductos;
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "productos" })
	@Command
	public void searchItemByKeyOrName(@BindingParam("compUserCp") Component comp) {
		productos = (List<Producto>) productoRest.getItemByKeyOrName(claveProducto, nombreProducto, organizacion).getSingle();
		if (productos == null && ( (claveProducto != null && !claveProducto.equals("")) || (nombreProducto != null && !nombreProducto.equals("")))) {
			String msj = "";
			if(claveProducto != null && !claveProducto.equals(""))
				msj = claveProducto;
			else 
				msj = nombreProducto;
			showWindowInformationMessage(WINDOW_MESSAGE1_TITLE,
					WINDOW_MESSAGE1_CONTENT + msj + "''",
					"acceptButtonWindowInformation", StockConstants.ICON_WIN_INFORMATION);
			
		}else if (productos == null && ((claveProducto != null && !claveProducto.equals("")) && (nombreProducto == null || nombreProducto.equals("")))) {
			productos = (List<Producto>) productoRest.getAllLimited(organizacion).getSingle();
		}else{
			if(productos == null)
				showWindowInformationMessage(WINDOW_MESSAGE1_TITLE,
						WINDOW_MESSAGE2_CONTENT,
						"acceptButtonWindowInformation",
						StockConstants.ICON_WIN_INFORMATION);
		}
			
	}
	
	
	@GlobalCommand
	@NotifyChange({ "productos", "claveProducto", "nombreProducto"})
	public void acceptButtonWindowInformation(@BindingParam("accept") boolean ok) {
		if(ok){
			productos = initProductos;
			claveProducto = "";
			nombreProducto = "";
		}
	}
	
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transferProduct() {
		if (productoSeleccionado != null) {
			productosModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("productoSeleccionado", productoSeleccionado);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}
	}

	@Command
	public void closeDialog() {
		if (this.productosModalDialog != null) {
			this.productosModalDialog.detach();
		}
	}

	public String getClaveProducto() {
		return this.claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	private void leerVariablesProperties(){
		Properties propiedades = getPropertiesFiles();
		WINDOW_BUSCADOR_PRODUCTOS_TITLE = propiedades.getProperty("window.buscador.productos.title");//Buscar Productos
		WINDOW_BUSCADOR_PRODUCTOS_EMPTY = propiedades.getProperty("window.buscador.productos.empty");//Catálogo de productos vacío
		
		GENERICZUL_LABEL_CLAVE = propiedades.getProperty("genericZUL.label.clave");//Clave:
		GENERICZUL_LABEL_NOMBRE = propiedades.getProperty("genericZUL.label.nombre");//Nombre:
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COLUMN_MARCA = propiedades.getProperty("genericZUL.column.marca");//Marca
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
		WINDOW_MESSAGE1_TITLE = propiedades.getProperty("window.message1.title");//Producto no encontrado
		WINDOW_MESSAGE1_CONTENT = propiedades.getProperty("window.message1.content");// No se obtubieron resultados bajo el parametro de "
		WINDOW_MESSAGE2_CONTENT = propiedades.getProperty("window.message2.content");// Los parametros especificados no generaron ningún resultado
	}
	
	
}
