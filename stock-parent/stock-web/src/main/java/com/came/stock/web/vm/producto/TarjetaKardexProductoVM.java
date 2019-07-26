package com.cplsystems.stock.app.vm.producto;

import java.util.ArrayList;
import java.util.Date;
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
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.DevelopmentTool;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class TarjetaKardexProductoVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#tarjetaKardexModalDialog")
	private Window tarjetaKardexModalDialog;
	
	private Listbox listItems;
	
	private String globalCommandName;
	private boolean general;
	private String title;
	
	

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandReturn") String updateCommandFromItemFinder,
			@ExecutionArgParam("productoList") List<Kardex> kardexList,
			@ExecutionArgParam("modalidadCalculo") DevelopmentTool modalidadCalculo,
			@ExecutionArgParam("formato") boolean formato) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		kardexListInside = kardexList;
		//competarInformacionProductosKardex();
		general = formato;
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		
		leerVariablesProperties();
		kardexRecuperarModalidadCalculoCosto(modalidadCalculo);
		kardexCargarProductosConCalculoCosto();
		
		if(general){
			listaProductosKardex = kardexService.getAllProductosNoRepetidos();
		}else
			title = "Tarjeta de kardex para " + kardexList.get(0).getProducto().getNombre();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		
		tarjetaKardexModalDialog.detach();
		Map<String, Object> args = new HashMap();
		args.put("fileSelected", "closable");
		if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		} else {
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
		}
		
		
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
	

	@NotifyChange({ "kardexListInside" })
	@Command
	public void registrarDevolucion(){
		if(checkPeps){
			
		}else if(checkUeps){
			crearDevolucionAlmacenPorPeps();
		}else if(checkPromedio){
			
		}
			
	}
	
	@NotifyChange({ "kardexListInside" })
	@Command
	public void registrarVenta(){
		if(checkPeps)
			crearNuevaVentaPorPeps();
		else if(checkUeps)
			crearNuevaVentaPorUeps();
		else if(checkPromedio)
			crearNuevaVentaPorPromedio();
	}
	
	
	private void crearNuevaVentaPorPromedio(){
		if(kardexListInside != null && kardexListInside.size() > 0){
			Kardex ultimoRegistroProducto = kardexListInside.get(kardexListInside.size() - 1); 
			if(ultimoRegistroProducto.getExistenciaCantidad() > 0){
				Kardex salidaKardex = new Kardex();
				salidaKardex.setOrganizacion(ultimoRegistroProducto.getOrganizacion());
				salidaKardex.setUsuario(usuario);
				salidaKardex.setProducto(ultimoRegistroProducto.getProducto());
				salidaKardex.setPrecioUnitario(ultimoRegistroProducto.getCostoPromedio());
				salidaKardex.setFechaEntrada(new Date());
				salidaKardex.setTypeFormat("S");
				salidaKardex.setSalidaId(String.valueOf(kardexListInside.size()));
				salidaKardex.setEnableSaveBotton(true);
				salidaKardex.setReferenciaKardex(ultimoRegistroProducto.getIdKardex());
				kardexListInside.add(salidaKardex);
			}else
				StockUtils.showSuccessmessage("No hay suficiente mercancia para realizar una salida",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			
		}else
			StockUtils.showSuccessmessage("No esta permitido la salida de mercancia sin antes haber existido una entrada de mercancia",
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}
	
	private void crearNuevaVentaPorUeps(){
		
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromModal", "actualizarListaKardex");
		
		Window productoModalView = this.stockUtils.createModelDialogWithParams(
				"/modulos/productos/utils/realizarVentaKardex.zul", inputParams);
		productoModalView.doModal();
		
	}
	
	public void crearDevolucionAlmacenPorPeps(){
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromModal", "actualizarListaKardexDevolucion");
		
		Window productoModalView = this.stockUtils.createModelDialogWithParams(
				"/modulos/productos/utils/realizarDevolucionKardex.zul", inputParams);
		productoModalView.doModal();
	}
	
	
	private void crearNuevaVentaPorPeps(){
	}
	
	
	
	@NotifyChange({ "kardexListInside" })
	@Command
	public void insertarCantidadSalidas(@BindingParam("index") Integer index){
		if(checkPeps)
			calcularCantidadSalidasPeps(index);
		else if(checkUeps)
			calcularCantidadSalidasUeps(index);
		else if(checkPromedio)
			calcularCantidadSalidasPromedio(index);
	}
	
	private void calcularCantidadSalidasPeps(Integer index){
		
		
	}
	
	private void calcularCantidadSalidasUeps(Integer index){
		Kardex kardexEnConstruccion = kardexListInside.get(index);
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromModal", "actualizarListaKardex");
		inputParams.put("cancelCommandModal", "cancelacionDeVenta");
		inputParams.put("listaKardex", kardexListInside);
		inputParams.put("kardexTrabajando", kardexEnConstruccion);
		
		Window productoModalView = this.stockUtils.createModelDialogWithParams(
				"/modulos/productos/utils/realizarVentaKardex.zul", inputParams);
		productoModalView.doModal();
	}
	
	@GlobalCommand
	@NotifyChange({ "kardexListInside"})
	public void actualizarListaKardex(
			@BindingParam("cantidad") Integer cantidad){
		Kardex kardexDeVenta = crearRegistroDeventaKardex(cantidad);
		List<Kardex> desgloseList = calcularVentaDesgloseUeps(cantidad, kardexDeVenta, (kardexListInside.size() - 1));
		for (Kardex kardex : desgloseList)
			kardexListInside.add(kardex);
	}
	
	@GlobalCommand
	@NotifyChange({ "kardexListInside"})
	public void actualizarListaKardexDevolucion(
			@BindingParam("cantidad") Integer cantidad,
			@BindingParam("referencia") Integer referencia,
			@BindingParam("devolucionCompraSalida") boolean devolucionType){
		
		//Buscar kardex de venta (referencia)
		List<Kardex> kardexReferenciasDeVenta = new ArrayList<>();
		
		for (Kardex item : kardexListInside) {
			if(item.getTypeFormat().equals("S"))
				if(item.getIdKardexClonacion() != null && (item.getIdKardexClonacion().toString().equals(String.valueOf(referencia)))){
					item = calcularDesgloceDevolucion(item);
					kardexReferenciasDeVenta.add(item);
				}
					
		}
		//------------------------------------
		
		if(kardexReferenciasDeVenta.size() > 0){
			int restan = 0;
			
			if(kardexReferenciasDeVenta.get(kardexReferenciasDeVenta.size() - 1).getUepsRestan() != null)
				restan = kardexReferenciasDeVenta.get(kardexReferenciasDeVenta.size() - 1).getUepsRestan();
			
			for (int i = kardexReferenciasDeVenta.size() - 1; i > -1; i--) {
				Kardex kardexOperacion = kardexReferenciasDeVenta.get(i);
				
				if(cantidad > kardexOperacion.getSalidaCantidad()){
					kardexOperacion.setUepsRestan(kardexOperacion.getSalidaCantidad());
				}else{
					
				}
				//kardexOperacion.getSalidaCantidad()
			}
		}
		
		if(devolucionType){//Devolucion de compra con proveedor
			
		}else{//Devolucion de Salida de almacen o venta
			
		}
		
		System.err.println(cantidad + " " + referencia);
		/*
		Kardex kardexDeVenta = crearRegistroDeventaKardex(cantidad);
		List<Kardex> desgloseList = calcularVentaDesgloseUeps(cantidad, kardexDeVenta, (kardexListInside.size() - 1));
		for (Kardex kardex : desgloseList)
			kardexListInside.add(kardex);
		*/
	}
	
	
	private Kardex calcularDesgloceDevolucion(Kardex kardexInput){
		
		return kardexInput;
	}
	
	
	private Kardex crearRegistroDeventaKardex(Integer cantidad){
		
		Kardex salidaKardex = new Kardex();
		salidaKardex.setUsuario(usuario);
		salidaKardex.setOrganizacion(usuario.getOrganizacion());
		salidaKardex.setFechaEntrada(new Date());
		salidaKardex.setTypeFormat("S");
		salidaKardex.setSalidaCantidad(cantidad);
		salidaKardex.setProducto(kardexListInside.get(0).getProducto());
		
		salidaKardex.setEntradaLoteReadOnly(true);
		salidaKardex.setEntradaReadOnly(true);
		salidaKardex.setEntradaEditVisible(false);
		
		salidaKardex.setSalidaCantidadEditVisible(false);
		salidaKardex.setSalidaReadOnly(true);
		salidaKardex.setSalidaLoteReadOnly(true);
		
		salidaKardex.setEnableSaveBotton(false);
		
		kardexService.save(salidaKardex);
		kardexListInside.add(salidaKardex);
		
		return salidaKardex;
	}
	
	
	private void calcularCantidadSalidasPromedio(Integer index){
		if(kardexListInside != null && kardexListInside.size() > 0){
			Kardex ultimoRegistroProducto = kardexListInside.get(index - 1);
			Kardex kardextrabajando = kardexListInside.get(index);
			
			kardexListInside.get(index).setExistenciaCantidad(ultimoRegistroProducto.getExistenciaCantidad() - kardextrabajando.getSalidaCantidad());
			kardexListInside.get(index).setHaber(kardextrabajando.getSalidaCantidad() * kardextrabajando.getPrecioUnitario());
			kardexListInside.get(index).setSaldo(ultimoRegistroProducto.getSaldo() - kardextrabajando.getHaber());
			kardexListInside.get(index).setCostoPromedio(kardextrabajando.getSaldo() / kardextrabajando.getExistenciaCantidad());
		}
	}
	
	
	@NotifyChange({ "kardexListInside" })
	@Command
	public void salvarCambiosRowKardex(@BindingParam("index") Integer index,
			@BindingParam("compSaveData") Component comp){
		if(checkPeps)
			salvarCambiosPeps(index);
		else if(checkUeps)
			salvarCambiosUeps(index);
		else if(checkPromedio)
			salvarCambiosPromedio(index);
	}
	
	
	private void salvarCambiosPeps(Integer index){
		
	}


	private void salvarCambiosUeps(Integer index){
		
	}
	
	private void salvarCambiosPromedio(Integer index){
		String mensaje = "";
		boolean ok = true;
		listItems = (Listbox) tarjetaKardexModalDialog.getFellow("tarjetaKardexModalDialog").getFellow("winProductos").getFellow("listItems");
		if(kardexListInside.get(index).getEntradaCantidad() != null){
			kardexService.save(kardexListInside.get(index));
			if(kardexListInside.get(index).getEntradaLote() != null && kardexListInside.get(index).getExistenciaLote() != null)
				kardexListInside.get(index).setEnableSaveBotton(false);
			mensaje += "Se ha modificado informacion en la entrada del producto";
		}else{
			if(kardexListInside.get(index).getSalidaLote() != null){
				kardexService.save(kardexListInside.get(index));
				if(kardexListInside.get(index).getSalidaLote() != null && kardexListInside.get(index).getExistenciaLote() != null)
					kardexListInside.get(index).setEnableSaveBotton(false);
				mensaje += "Se ha modificado informacion en la salida de mercancia";
			}else{
				ok = false;
				//comp = listItems.getItemAtIndex(index).getFellow(kardexListInside.get(index).getSalidaId());
				StockUtils.showSuccessmessage("Debe ingresar un valor",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			}
		}
		if(ok)
			StockUtils.showSuccessmessage(mensaje,
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@NotifyChange({ "kardexListInside", "title" })
	@Command
	public void verTarjetaProductoSeleccionado(){
		kardexListInside = kardexService.getByProducto(productosKardexSelected);
		kardexCargarProductosConCalculoCosto();
		title = "Tarjeta de kardex para " + productosKardexSelected.getNombre();
	}
	
	
	public String getTitle() {
		return title;
	}

	public boolean isGeneral() {
		return general;
	}
	
	
	
}
