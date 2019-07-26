package com.came.stock.web.utils;

import java.util.HashMap;
import java.util.Map;

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

import com.came.stock.web.vm.BasicStructure;

@VariableResolver({ DelegatingVariableResolver.class })
public class WindowConfirmationVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#windowConfirmationModalDialog")
	private Window windowInformarionModalDialog;
	private String globalCommandName;

	private String title;
	private String content;
	private String icon;
	private Object value;

	/**
	 * @param titulo, titulo de la ventana
	 * @param contenido, contenido del mensaje
	 * @param icon, url del icono que desea colocar, tambien puede colocar una de las
	 * constantes ya creadas (ICON_WIN_INFORMATION, ICON_WIN_WARNING, ICON_WIN_ERROR)
	 * 
	 * **/
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("executeNameMethod") String executeNameMethod,
			@ExecutionArgParam("titulo") String titulo,
			@ExecutionArgParam("contenido") String contenido,
			@ExecutionArgParam("icon") String icon,
			@ExecutionArgParam("object") Object value) {
		Selectors.wireComponents(view, this, false);

		globalCommandName = executeNameMethod;
		title = titulo;
		content = contenido;
		this.icon = icon;
		this.value = value;
	}

	// -----------------------------------------------------------

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void confirm() {

		windowInformarionModalDialog.detach();
		Map<String, Object> args = new HashMap();
		args.put("accept", true);
		args.put("object", value);
		if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
			BindUtils.postGlobalCommand(null, null, globalCommandName, args);
		} else {
			BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void closeDialog() {
		if (windowInformarionModalDialog != null) {
			windowInformarionModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("accept", false);
			args.put("object", value);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}
	}

	// --------------------------------------------------------------------------------

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	

}
