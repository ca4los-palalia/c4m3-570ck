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
public class ConffyaPartidaGenerica {
	private Long idConffyaPartidaGenerica;
	private String nombre;
	
	private Integer grupo;
	private Integer subgrupo;
	private Integer clase;
	private String subClase;
	
	private String clasificacionId;
	private String clasificacionNombre;
	private String clave;
	private String descripcion;
	private String claveConDescripcion;
	
	
	
	private Calendar ultimaActualizacion;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdConffyaPartidaGenerica() {
		return this.idConffyaPartidaGenerica;
	}

	public void setIdConffyaPartidaGenerica(Long idConffyaPartidaGenerica) {
		this.idConffyaPartidaGenerica = idConffyaPartidaGenerica;
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
	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	@Column
	public Integer getSubgrupo() {
		return subgrupo;
	}

	public void setSubgrupo(Integer subgrupo) {
		this.subgrupo = subgrupo;
	}

	@Column
	public Integer getClase() {
		return clase;
	}

	public void setClase(Integer clase) {
		this.clase = clase;
	}

	@Column
	public String getSubClase() {
		return subClase;
	}

	public void setSubClase(String subClase) {
		this.subClase = subClase;
	}

	@Column
	public String getClasificacionId() {
		return clasificacionId;
	}

	public void setClasificacionId(String clasificacionId) {
		this.clasificacionId = clasificacionId;
	}

	@Column
	public String getClasificacionNombre() {
		return clasificacionNombre;
	}

	public void setClasificacionNombre(String clasificacionNombre) {
		this.clasificacionNombre = clasificacionNombre;
	}

	
	
	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

	@Column
	public String getClave() {
		/*
		if(clave == null || clave.equals("")){
			String temp = "";
			if(grupo != null)
				temp += String.valueOf(grupo);
			if(subgrupo != null)
				temp += String.valueOf(subgrupo);
			if(clase != null)
				temp += String.valueOf(clase);
			
			clave = String.valueOf(temp);
		}
		*/
		return clave;
	}
	
	public void setClave(String clave) {
		this.clave = clave;
	}

	@Transient
	public String getClaveConDescripcion() {
		/*
		String temp = "";
		if(grupo != null)
			temp += String.valueOf(grupo);
		if(subgrupo != null)
			temp += String.valueOf(subgrupo);
		if(clase != null)
			temp += String.valueOf(clase);
		
		clave = String.valueOf(temp);
		
		clave += " ";
		
		if(descripcion != null && !descripcion.equals(""))
			clave += descripcion;
		claveConDescripcion = clave;
		return claveConDescripcion;
		*/
		return clave + " " + descripcion;
	}
	
	
}
