package com.came.stock.web.vm.perfil;

import org.zkoss.image.AImage;

import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.web.vm.BasicStructure;

public abstract class PerfilVariables extends BasicStructure {
	private static final long serialVersionUID = 7810843192652008397L;
	
	protected Usuarios usuario;
	protected Organizacion organizacion	;
	protected boolean habilitarPaisEstadoMunicipio;
	
	
	
	protected DevelopmentTool developmentConstruction;
	protected DevelopmentTool toolImagenLogo;
	protected boolean toolImagenLogoChecked;
	
	
	protected boolean isDireccionObtenida;
	
	protected String typePassword;
	protected boolean verPassword;
	protected boolean verOrganizacion;
	protected boolean desabilitarOrgUsuarios;
	protected boolean disabledCampos;
	protected AImage logotipoAImage;
	protected AImage bannerAImage;
	
	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public boolean isHabilitarPaisEstadoMunicipio() {
		return habilitarPaisEstadoMunicipio;
	}

	public void setHabilitarPaisEstadoMunicipio(boolean habilitarPaisEstadoMunicipio) {
		this.habilitarPaisEstadoMunicipio = habilitarPaisEstadoMunicipio;
	}

	public String getTypePassword() {
		return typePassword;
	}

	public void setTypePassword(String typePassword) {
		this.typePassword = typePassword;
	}

	public boolean isVerPassword() {
		return verPassword;
	}

	public void setVerPassword(boolean verPassword) {
		this.verPassword = verPassword;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public AImage getLogotipoAImage() {
		return logotipoAImage;
	}

	public void setLogotipoAImage(AImage logotipoAImage) {
		this.logotipoAImage = logotipoAImage;
	}

	
	public boolean isVerOrganizacion() {
		return verOrganizacion;
	}

	public void setVerOrganizacion(boolean verOrganizacion) {
		this.verOrganizacion = verOrganizacion;
	}

	public AImage getBannerAImage() {
		return bannerAImage;
	}

	public void setBannerAImage(AImage bannerAImage) {
		this.bannerAImage = bannerAImage;
	}

	public DevelopmentTool getToolImagenLogo() {
		return toolImagenLogo;
	}

	public void setToolImagenLogo(DevelopmentTool toolImagenLogo) {
		this.toolImagenLogo = toolImagenLogo;
	}

	public boolean isToolImagenLogoChecked() {
		return toolImagenLogoChecked;
	}

	public void setToolImagenLogoChecked(boolean toolImagenLogoChecked) {
		this.toolImagenLogoChecked = toolImagenLogoChecked;
	}

	public boolean isDisabledCampos() {
		return disabledCampos;
	}

	public void setDisabledCampos(boolean disabledCampos) {
		this.disabledCampos = disabledCampos;
	}

	public boolean isDesabilitarOrgUsuarios() {
		return desabilitarOrgUsuarios;
	}

	public void setDesabilitarOrgUsuarios(boolean desabilitarOrgUsuarios) {
		this.desabilitarOrgUsuarios = desabilitarOrgUsuarios;
	}

	
	
}
