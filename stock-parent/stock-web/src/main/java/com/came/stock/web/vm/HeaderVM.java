package com.came.stock.web.vm;

import java.util.Properties;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class HeaderVM extends BasicStructure {
	private static final long serialVersionUID = -1635442587326363484L;

	private int totalTransacciones;

	@Init
	public void init() {
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
		leerVariablesProperties();
		getStyles();
	}

	private void getStyles() {
		styleHomeHeader = "background: linear-gradient(to bottom, " + StockConstants.COLOR_LOGIN_START + " 0%,"
				+ StockConstants.COLOR_LOGIN_END + " 100%); color: " + StockConstants.COLOR_LOGIN_FONT
				+ "; display: table-cell; vertical-align: middle;";
	}

	@Command
	public void logOut() {
		showWindowConfirmationMessage(TAG_HEADERVM_CODE_TITLE, TAG_HEADERVM_CODE_MESSAGE, "exitSystem",
				StockConstants.ICON_WIN_CONFIRM, null);
	}

	@GlobalCommand
	public void exitSystem(@BindingParam("accept") boolean ok, @BindingParam("object") Object value) {
		if (ok) {
			sessionUtils.logOut();
			stockUtils.redirect("/login.zul");
		}
	}

	public int getTotalTransacciones() {
		return totalTransacciones;
	}

	public void setTotalTransacciones(int totalTransacciones) {
		this.totalTransacciones = totalTransacciones;
	}

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		TAG_HEADERVM_TOOLTIP_CERRAR = propiedades.getProperty("headerVM.tooltipCerrar");
		TAG_HEADERVM_CODE_MESSAGE = propiedades.getProperty("headerVM.codeMessague");
		TAG_HEADERVM_CODE_TITLE = propiedades.getProperty("headerVM.codeTitle");
	}

	@Command
	public void openUrl() {

		if (organizacion != null && organizacion.getContacto() != null && organizacion.getContacto().getEmail() != null
				&& (organizacion.getContacto().getEmail().getWeb() != null
						&& !organizacion.getContacto().getEmail().getWeb().isEmpty())) {
			if (!organizacion.getContacto().getEmail().getWeb().contains("http://www.")
					|| !!organizacion.getContacto().getEmail().getWeb().contains("https://www."))
				organizacion.getContacto().getEmail()
						.setWeb("http://www." + organizacion.getContacto().getEmail().getWeb());
			Executions.getCurrent().sendRedirect(organizacion.getContacto().getEmail().getWeb(), "_blank");
		}
	}

	@Command
	public void goHome() {
		Executions.getCurrent()
				.sendRedirect(Executions.getCurrent().getScheme() + "://" + Executions.getCurrent().getServerName()
						+ ":" + Executions.getCurrent().getServerPort() + Executions.getCurrent().getContextPath());
	}

}
