package com.came.stock.model.domain;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class ConffyaFuenteFinanciamiento {
	private Long idConffyaFuenteFinanciamiento;
	private String nombre;
	private String clave;
	private Calendar ultimaActualizacion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;
	private String claveMasNombre;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdConffyaFuenteFinanciamiento() {
		return this.idConffyaFuenteFinanciamiento;
	}

	public void setIdConffyaFuenteFinanciamiento(Long idConffyaFuenteFinanciamiento) {
		this.idConffyaFuenteFinanciamiento = idConffyaFuenteFinanciamiento;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public Calendar getUltimaActualizacion() {
		return this.ultimaActualizacion;
	}

	public void setUltimaActualizacion(Calendar ultimaActualizacion) {
		this.ultimaActualizacion = ultimaActualizacion;
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

	@Column
	public String getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@Column
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Transient
	public String getClaveMasNombre() {
		if(clave != null && nombre != null)
			claveMasNombre = clave + " " + nombre;
		return claveMasNombre;
	}

	public void setClaveMasNombre(String claveMasNombre) {
		this.claveMasNombre = claveMasNombre;
	}
	
}
