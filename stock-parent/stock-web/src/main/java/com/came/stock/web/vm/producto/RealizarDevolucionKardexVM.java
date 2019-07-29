package com.came.stock.web.vm.producto;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class RealizarDevolucionKardexVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#realizarDevolucionKardexVMModalDialog")
	private Window realizarDevolucionKardexVMModalDialog;
	private String globalCommandName;
	
	private Integer cantidad;
	private Integer referencia;
	private String titleWindow;
	private boolean devolcionGlobal;
	private boolean devolucionCompra;
	private boolean devolucionSalida;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromModal") String updateCommandFromItemFinder) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		
		leerVariablesProperties();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transferProduct() {
		if(!devolucionCompra && !devolucionSalida){
			StockUtils.showSuccessmessage("Debe seleccionar el tipo de devolucion",
					Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}else{
			if(cantidad != null){
				
				boolean esNumerico = false;
				String valorAConvertir = String.valueOf(cantidad);
				try {
					Integer.parseInt(valorAConvertir);
					valorAConvertir = String.valueOf(referencia);
					Integer.parseInt(valorAConvertir);
					esNumerico = true;
				} catch (Exception e) {
				}
				
				if(esNumerico){
					realizarDevolucionKardexVMModalDialog.detach();
					Map<String, Object> args = new HashMap();
					args.put("cantidad", cantidad);
					args.put("referencia", referencia);
					args.put("devolucionCompraSalida", devolcionGlobal);
					if ((globalCommandName != null) && (!globalCommandName.isEmpty()))
						BindUtils.postGlobalCommand(null, null, globalCommandName, args);
					else
						BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
				}else
					StockUtils.showSuccessmessage("Valor incorrecto (" + valorAConvertir + "). Debe ingresar un valor numerico",
							Clients.NOTIFICATION_TYPE_ERROR, 0,
							null);
			}else
				StockUtils.showSuccessmessage("debe ingresar un valor para procesar una salida de mercancia",
						Clients.NOTIFICATION_TYPE_WARNING, 0,
						null);
		}
		
	}
	
	@Command
	public void closeDialog() {
		if (realizarDevolucionKardexVMModalDialog != null)
			realizarDevolucionKardexVMModalDialog.detach();
	}
	
	/*
	@Command
	public void selectRadioCompra(){
		devolcionGlobal = true;
	}
	
	@Command
	public void selectRadioSalida(){
		devolcionGlobal = false;
	}
	*/

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
	
	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getReferencia() {
		return referencia;
	}

	public void setReferencia(Integer referencia) {
		this.referencia = referencia;
	}

	public boolean isDevolucionCompra() {
		return devolucionCompra;
	}

	public void setDevolucionCompra(boolean devolucionCompra) {
		devolcionGlobal = true;
		this.devolucionCompra = devolucionCompra;
	}

	public boolean isDevolucionSalida() {
		return devolucionSalida;
	}

	public void setDevolucionSalida(boolean devolucionSalida) {
		devolcionGlobal = false;
		this.devolucionSalida = devolucionSalida;
	}	
}
