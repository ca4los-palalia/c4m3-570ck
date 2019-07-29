package com.came.stock.web.vm.producto;

import java.util.HashMap;
import java.util.List;
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
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Justificacion;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class SelectJustificacionVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#justificacionVMModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;

	private List<Justificacion> catalogoJustificacion;
	private Justificacion justificacion;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoJustificacion") List<Justificacion> catalogoJustificacion) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		this.catalogoJustificacion = catalogoJustificacion;
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		if (justificacion != null) {
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("justificacionSeleccionado", justificacion);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}
	}

	@Command
	public void closeDialog() {
		if (windowModalDialog != null) {
			windowModalDialog.detach();
		}
	}

	public List<Justificacion> getCatalogoJustificacion() {
		return catalogoJustificacion;
	}

	public void setCatalogoJustificacion(List<Justificacion> catalogoJustificacion) {
		this.catalogoJustificacion = catalogoJustificacion;
	}

	public Justificacion getJustificacion() {
		return justificacion;
	}

	public void setJustificacion(Justificacion justificacion) {
		this.justificacion = justificacion;
	}

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_JUSTIFICACION_TITLE = propiedades.getProperty("window.select.justificacion.title");//Seleccionar justificacion de la requisición
		WINDOW_SELECT_JUSTIFICACION_EMPTY = propiedades.getProperty("window.select.justificacion.empty");//Catalogo de justificaciones vacio
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
	}
	

}
