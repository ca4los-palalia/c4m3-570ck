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
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class SelectProyectoVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#proyectoVMModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;

	private List<ConffyaPy> catalogoConffyaPy;
	private Area areaSeleccionada;
	private ConffyaProg conffyaProgSeleccionado;
	private ConffyaPy conffyaPy;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoConffyaPy") List<ConffyaPy> catalogoConffyaPy,
			@ExecutionArgParam("areaSeleccionada") Area areaSeleccionada,
			@ExecutionArgParam("conffyaProgSeleccionado") ConffyaProg conffyaProgSeleccionado) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		this.catalogoConffyaPy = catalogoConffyaPy;
		this.areaSeleccionada = areaSeleccionada;
		this.conffyaProgSeleccionado = conffyaProgSeleccionado;
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		if (conffyaPy != null) {
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("conffyaPySeleccionado", conffyaPy);
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

	public List<ConffyaPy> getCatalogoConffyaPy() {
		return catalogoConffyaPy;
	}

	public void setCatalogoConffyaPy(List<ConffyaPy> catalogoConffyaPy) {
		this.catalogoConffyaPy = catalogoConffyaPy;
	}

	public ConffyaPy getConffyaPy() {
		return conffyaPy;
	}

	public void setConffyaPy(ConffyaPy conffyaPy) {
		this.conffyaPy = conffyaPy;
	}

	private void leerVariablesProperties(){
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_PROYECTO_TITLE = propiedades.getProperty("window.select.proyecto.title");//Seleccionar Proyecto
		WINDOW_SELECT_PROYECTO_EMPTY = propiedades.getProperty("window.select.proyecto.empty");//Debe seleccionar previamente un programa
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
	}


	

}
