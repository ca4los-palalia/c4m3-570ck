package com.came.stock.model.domain;

import java.io.IOException;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.zkoss.image.AImage;

@Entity
@Table
public class Banner implements Serializable {
	private static final long serialVersionUID = -7164588155326780268L;
	private Long idBanner;
	private String nombre;
	private String descripcion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private byte[] logotipo;
	private AImage logotipoAImage;
	private String url;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdBanner() {
		return this.idBanner;
	}

	public void setIdBanner(Long idBanner) {
		this.idBanner = idBanner;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	@Transient
	public AImage getLogotipoAImage() {
		if (logotipo != null) {
			try {
				logotipoAImage = new AImage("logoByte", logotipo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return logotipoAImage;
	}

	public void setLogotipoAImage(AImage logotipoAImage) {
		this.logotipoAImage = logotipoAImage;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	@Lob
	public byte[] getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(byte[] logotipo) {
		this.logotipo = logotipo;
	}

	@Column
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	

}
