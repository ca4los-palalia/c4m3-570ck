package com.came.stock.web.vm;

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
import org.zkoss.zul.Window;

import com.came.stock.constantes.StockConstants;

@VariableResolver({ DelegatingVariableResolver.class })
public class HomeVM  extends BasicStructure{
	public String TAG_HOMEVM_TITLE;
	public String TAG_MENUVM_SUBTITLE1_REQ;
	
	@Wire("#mainWindow")
	private Window main;
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
		mainWindow = main;
		styleFontMenu = "color: " + StockConstants.COLOR_FONT_GLOBAL + "; font-size: 100%;";
		
		
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
		TAG_MENUVM_SUBTITLE1_REQ = propiedades.getProperty("menuVM.subTitle1.itemRequisicion");// = Requisición 
	}

	public String getTAG_HOMEVM_TITLE() {
		return TAG_HOMEVM_TITLE;
	}

	public void setTAG_HOMEVM_TITLE(String tAG_HOMEVM_TITLE) {
		TAG_HOMEVM_TITLE = tAG_HOMEVM_TITLE;
	}

	public String getTAG_MENUVM_SUBTITLE1_REQ() {
		return TAG_MENUVM_SUBTITLE1_REQ;
	}

	
}
