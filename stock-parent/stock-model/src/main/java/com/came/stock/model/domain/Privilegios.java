package com.came.stock.model.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.bind.annotation.Command;

import com.came.stock.utilidades.UserPrivileges;

@Entity
@Table
public class Privilegios implements Serializable {
	private static final long serialVersionUID = -1186727205643806890L;
	public static final String RQ_ICON = "/images/toolbar/text_enriched16.png";
	public static final String CN_ICON = "/images/toolbar/linedpaperplus32.png";
	public static final String CTAT_ICON = "/images/toolbar/notecheck32.png";
	public static final String OC_ICON = "/images/toolbar/shoppingcart32.png";
	public static final String REQUISION = "/modulos/requisicion/requisicion.zul";
	public static final String CONCENTRADO = "/modulos/requisicion/concentrado.zul";
	public static final String COTIZACION_AUTORIZACION = "/modulos/requisicion/cotizacion.zul";
	public static final String ORDEN_COMPRA = "/modulos/ordenCompra/ordenCompra.zul";
	private Long idPrivilegio;
	private UserPrivileges userPrivileges;
	private String icono;
	private Usuarios usuarios;
	private String pathLocationModule;
	private String nameTag;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getIdPrivilegio() {
		return this.idPrivilegio;
	}

	public void setIdPrivilegio(Long idPrivilegio) {
		this.idPrivilegio = idPrivilegio;
	}

	@Enumerated(EnumType.STRING)
	@Column
	public UserPrivileges getUserPrivileges() {
		return this.userPrivileges;
	}

	public void setUserPrivileges(UserPrivileges userPrivileges) {
		this.userPrivileges = userPrivileges;
	}

	@Column
	public String getIcono() {
		return this.icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario")
	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	@Command
	public String getPathLocationModule() {
		return this.pathLocationModule;
	}

	public void setPathLocationModule(String pathLocationModule) {
		this.pathLocationModule = pathLocationModule;
	}

	@SuppressWarnings("static-access")
	@Transient
	public String getNameTag() {
		if(userPrivileges != null){
			String cadena = String.valueOf(userPrivileges);
			if(cadena.toUpperCase().contains("REQUISION"))
				nameTag = "Requisiciones";
			else if(cadena.toUpperCase().contains("CONCENTRAR"))
				nameTag = "Concentrado";
			else if(cadena.toUpperCase().contains("ORDEN_COMPRA"))
				nameTag = "Ord. Compra";
			else if(cadena.toUpperCase().contains("COTIZAR_AUTORIZAR"))
				nameTag = "Cotizacion";
		}
		return nameTag;
	}

	public void setNameTag(String nameTag) {
		this.nameTag = nameTag;
	}
	
}
