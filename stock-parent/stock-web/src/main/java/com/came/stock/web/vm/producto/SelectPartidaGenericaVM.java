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
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class SelectPartidaGenericaVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#partidaGenericaVMModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;

	private List<ConffyaPartidaGenerica> catalogoConffyaPartidaGenerica;
	private ConffyaPartidaGenerica conffyaPartidaGenerica;
	private Area selectedArea;
	private ConffyaProg selectedConffyaProg;
	private ConffyaPy selectedConffyaPy;
	private ConffyaFuenteFinanciamiento selectedConffyaFuenteFinanciamiento;
	private Integer indexProducto;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoConffyaPartidaGenerica") List<ConffyaPartidaGenerica> catalogoConffyaPartidaGenerica,
			@ExecutionArgParam("selectedArea") Area selectedArea,
			@ExecutionArgParam("selectedConffyaProg") ConffyaProg selectedConffyaProg,
			@ExecutionArgParam("selectedConffyaPy") ConffyaPy selectedConffyaPy,
			@ExecutionArgParam("selectedConffyaFuenteFinanciamiento") ConffyaFuenteFinanciamiento selectedConffyaFuenteFinanciamiento,
			@ExecutionArgParam("indexProducto") Integer index) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		this.catalogoConffyaPartidaGenerica = catalogoConffyaPartidaGenerica;
		this.selectedArea = selectedArea;
		this.selectedConffyaProg = selectedConffyaProg;
		this.selectedConffyaPy = selectedConffyaPy;
		this.selectedConffyaFuenteFinanciamiento = selectedConffyaFuenteFinanciamiento;
		this.indexProducto = index;
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		if (conffyaPartidaGenerica != null) {
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("conffyaPartidaGenericaSeleccionado", conffyaPartidaGenerica);
			args.put("indexProducto", indexProducto);
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

	public List<ConffyaPartidaGenerica> getCatalogoConffyaPartidaGenerica() {
		return catalogoConffyaPartidaGenerica;
	}

	public void setCatalogoConffyaPartidaGenerica(List<ConffyaPartidaGenerica> catalogoConffyaPartidaGenerica) {
		this.catalogoConffyaPartidaGenerica = catalogoConffyaPartidaGenerica;
	}

	public ConffyaPartidaGenerica getConffyaPartidaGenerica() {
		return conffyaPartidaGenerica;
	}

	public void setConffyaPartidaGenerica(ConffyaPartidaGenerica conffyaPartidaGenerica) {
		this.conffyaPartidaGenerica = conffyaPartidaGenerica;
	}

	
	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_PARTIDA_GENERICA_TITLE = propiedades.getProperty("window.select.partida.generica.title");//Seleccionar partida genérica
		WINDOW_SELECT_PARTIDA_GENERICA_EMPTY = propiedades.getProperty("window.select.partida.generica.empty");//Debe seleccionar previamente una fuente de financiamiento
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
	}

}
