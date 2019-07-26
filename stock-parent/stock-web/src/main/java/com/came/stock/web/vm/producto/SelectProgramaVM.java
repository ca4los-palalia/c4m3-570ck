package com.cplsystems.stock.app.vm.producto;

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

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.ConffyaProg;

@VariableResolver({ DelegatingVariableResolver.class })
public class SelectProgramaVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#programaModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;

	private List<ConffyaProg> catalogoConffyaProg;
	private ConffyaProg conffyaProg;
	private Area areaSeleccionada;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoConffyaProg") List<ConffyaProg> catalogoConffyaProg,
			@ExecutionArgParam("areaSeleccionada") Area areaSeleccionada) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		this.catalogoConffyaProg = catalogoConffyaProg;
		this.areaSeleccionada = areaSeleccionada;
		leerVariablesProperties();
	}

	

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		if (conffyaProg != null) {
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("conffyaProgSeleccionada", conffyaProg);
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

	public List<ConffyaProg> getCatalogoConffyaProg() {
		return catalogoConffyaProg;
	}

	public void setCatalogoConffyaProg(List<ConffyaProg> catalogoConffyaProg) {
		this.catalogoConffyaProg = catalogoConffyaProg;
	}

	public ConffyaProg getConffyaProg() {
		return conffyaProg;
	}

	public void setConffyaProg(ConffyaProg conffyaProg) {
		this.conffyaProg = conffyaProg;
	}

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_PROGRAMA_TITLE = propiedades.getProperty("window.select.programa.title");//Seleccionar Programa
		WINDOW_SELECT_PROGRAMA_EMPTY = propiedades.getProperty("window.select.programa.empty");//No existen programas en el catalogo
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
	}

}
