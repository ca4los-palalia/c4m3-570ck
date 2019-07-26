package com.came.stock.web.vm;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Banner;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Organizacion;

@VariableResolver({ DelegatingVariableResolver.class })
public class InicioVM extends BasicStructure{
	
	private static final long serialVersionUID = -6101726534539303566L;
	
	private Integer indexBanner;
	private boolean activarTransicion;
	private String urlImagen;
	
	@Init
	public void init() {
		bannerList = (List<Banner>) bannerRest.getAll().getSingle();
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		indexBanner = 0;
		if(bannerList != null){
			bannerGlobalAImage = bannerList.get(indexBanner).getLogotipoAImage();
			urlImagen = bannerList.get(indexBanner).getUrl();
		}	
		
		DevelopmentTool tool = (DevelopmentTool) developmentToolRest.getByNombre("transicion").getSingle();
		if(tool.getValue().equals("true")){
			activarTransicion = true;
		}else{
			if(organizacion.getContacto() != null && organizacion.getContacto().getEmail() != null)
				urlImagen = organizacion.getContacto().getEmail().getWeb();
			bannerGlobalAImage = organizacion.getLogotipoAImage();
		}
		getStyles();
	}	
	
	private void getStyles() {
		styleInicioBackground = "background: linear-gradient(to bottom, " + StockConstants.COLOR_HOME_BGR_MAIN_START + " 0%,"
				+ StockConstants.COLOR_HOME_BGR_MAIN_END + " 100%); vertical-align: middle;";
	}

	@Command
	@NotifyChange({ "bannerGlobalAImage", "urlImagen" })
	public void updateImage(){
		if(activarTransicion){
			if(bannerList != null){
				bannerGlobalAImage = bannerList.get(indexBanner).getLogotipoAImage();
				urlImagen = bannerList.get(indexBanner).getUrl();
				indexBanner ++;
				
				if(indexBanner >= bannerList.size())
					indexBanner = 0;
			}
		}
	}

	public String getUrlImagen() {
		return urlImagen;
	}

	public void setUrlImagen(String urlImagen) {
		this.urlImagen = urlImagen;
	}
	
	
}
