package com.cplsystems.stock.app.vm.producto;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Area;
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
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class SelectUrVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#urModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;

	private List<Area> catalogoAreas;
	private Area area;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("catalogoAreas") List<Area> catalogoAreas) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		this.catalogoAreas = catalogoAreas;
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transfer() {
		if (area != null) {
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("areaSeleccionada", area);
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

	public List<Area> getCatalogoAreas() {
		return catalogoAreas;
	}

	public void setCatalogoAreas(List<Area> catalogoAreas) {
		this.catalogoAreas = catalogoAreas;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	private void leerVariablesProperties(){
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_UR_TITLE = propiedades.getProperty("window.select.ur.title");//Seleccionar Area (Unidad responsable)
		WINDOW_SELECT_UR_EMPTY = propiedades.getProperty("window.select.ur.empty");//No existen unidades responsables en el catalogo
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");//Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");//Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");//Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");//Aceptar
	}
	
	
}
