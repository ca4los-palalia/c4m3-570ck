package com.came.stock.web.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Direccion;
import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class SeleccionarCodigoPostalVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#fileModalDialog")
	private Window filesModalDialog;
	private String globalCommandName;

	private List<String> colonias;
	private String coloniaSeleccionada;
	private String titleWindow;
	private Direccion direccionReturn;
	private Component comp;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("itemFinder") String itemFinder,
			@ExecutionArgParam("direccionConstruida") Direccion cotizacionSeleccionada,
			@ExecutionArgParam("componente") Component componenteInput) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = itemFinder;
		colonias = cotizacionSeleccionada.getColonias();
		direccionReturn = cotizacionSeleccionada;
		comp = componenteInput;
		titleWindow = "Seleccionar una Colonia para el codigo postal " + cotizacionSeleccionada.getCp();
	}

	// -----------------------------------------------------------

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "productos" })
	@Command
	public void searchItemByKeyOrName() {

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transferProduct() {
		if (coloniaSeleccionada != null) {
			direccionReturn.setColonia(coloniaSeleccionada);

			filesModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("direccionReturn", direccionReturn);
			args.put("componenteReturn", comp);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}

	}

	@Command
	public void closeDialog() {
		if (filesModalDialog != null) {
			filesModalDialog.detach();
		}
	}

	public List<String> getColonias() {
		return colonias;
	}

	public void setColonias(List<String> colonias) {
		this.colonias = colonias;
	}

	public String getTitleWindow() {
		return titleWindow;
	}

	public void setTitleWindow(String titleWindow) {
		this.titleWindow = titleWindow;
	}

	public String getColoniaSeleccionada() {
		return coloniaSeleccionada;
	}

	public void setColoniaSeleccionada(String coloniaSeleccionada) {
		this.coloniaSeleccionada = coloniaSeleccionada;
	}
}
