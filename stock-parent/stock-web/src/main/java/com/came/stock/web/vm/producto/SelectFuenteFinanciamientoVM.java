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

import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class SelectFuenteFinanciamientoVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#fuenteFinanciamientoVMModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;

	private List<ConffyaFuenteFinanciamiento> catalogoConffyaFuenteFinanciamiento;
	private Area selectedArea;
	private ConffyaProg selectedConffyaProg;
	private ConffyaPy selectedConffyaPy;
	private ConffyaFuenteFinanciamiento conffyaFuenteFinanciamiento;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoConffyaFuenteFinanciamiento") List<ConffyaFuenteFinanciamiento> catalogoConffyaFuenteFinanciamiento,
			@ExecutionArgParam("selectedArea") Area selectedArea,
			@ExecutionArgParam("selectedConffyaProg") ConffyaProg selectedConffyaProg,
			@ExecutionArgParam("selectedConffyaPy") ConffyaPy selectedConffyaPy) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		this.catalogoConffyaFuenteFinanciamiento = catalogoConffyaFuenteFinanciamiento;
		this.selectedArea = selectedArea;
		this.selectedConffyaProg = selectedConffyaProg;
		this.selectedConffyaPy = selectedConffyaPy;
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		if (conffyaFuenteFinanciamiento != null) {
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("conffyaFuenteFinanciamientoSeleccionado", conffyaFuenteFinanciamiento);
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

	public List<ConffyaFuenteFinanciamiento> getCatalogoConffyaFuenteFinanciamiento() {
		return catalogoConffyaFuenteFinanciamiento;
	}

	public void setCatalogoConffyaFuenteFinanciamiento(
			List<ConffyaFuenteFinanciamiento> catalogoConffyaFuenteFinanciamiento) {
		this.catalogoConffyaFuenteFinanciamiento = catalogoConffyaFuenteFinanciamiento;
	}

	public ConffyaFuenteFinanciamiento getConffyaFuenteFinanciamiento() {
		return conffyaFuenteFinanciamiento;
	}

	public void setConffyaFuenteFinanciamiento(ConffyaFuenteFinanciamiento conffyaFuenteFinanciamiento) {
		this.conffyaFuenteFinanciamiento = conffyaFuenteFinanciamiento;
	}

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_FUENTE_FINANCIAMIENTO_TITLE = propiedades.getProperty("window.select.fuente.financiamiento.title");//Seleccionar Fuente de financiamiento
		WINDOW_SELECT_FUENTE_FINANCIAMIENTO_EMPTY = propiedades.getProperty("window.select.fuente.financiamiento.empty");//Debe seleccionar previamente un proyecto
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
	}

}
