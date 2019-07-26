package com.came.stock.web.vm.proveedor.dashboard;

import java.io.FileInputStream;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

import com.came.stock.constantes.StockConstants;

@VariableResolver({ DelegatingVariableResolver.class })
public class HomeDashboardVM {
	public String TAG_HOMEVM_TITLE;
	
	@Wire("#header")
	private Div header;
	@Wire("#menu")
	private Div menu;
	@Wire("#content")
	private Div content;
	@Wire("#footer")
	private Div footer;

	@Init
	public void init() {
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "*" })
	@GlobalCommand
	public void updateWorkArea(@BindingParam("pageToRender") String pageToRender) {
		List<Component> components = this.content.getChildren();
		if (components != null) {
			components.clear();
			this.content.appendChild(new Include(pageToRender));
		}
	}
	
	private void leerVariablesProperties(){
		Properties propiedades = getPropertiesFiles();
		TAG_HOMEVM_TITLE = propiedades.getProperty("homeVM.title");// = Menú
	}
	
	private Properties getPropertiesFiles(){
		Properties propiedades = new Properties();
		//String language = System.getProperty("user.country");
		//System.getProperty("user.language");
		
		try {
			propiedades
		     .load(new FileInputStream(
		    		 generarUrlString(StockConstants.LANGUAGE_ESP)));
		} catch (Exception e) {
		}
		return propiedades;
	}
	
	private String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}

	public String getTAG_HOMEVM_TITLE() {
		return TAG_HOMEVM_TITLE;
	}

	public void setTAG_HOMEVM_TITLE(String tAG_HOMEVM_TITLE) {
		TAG_HOMEVM_TITLE = tAG_HOMEVM_TITLE;
	}
	
	
}
