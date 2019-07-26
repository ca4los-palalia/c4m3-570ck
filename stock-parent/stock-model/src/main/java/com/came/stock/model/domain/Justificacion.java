package com.came.stock.model.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Justificacion implements Serializable {
	private static final long serialVersionUID = -7164588155326780268L;
	private Long idJustificacion;
	private String clave;
	private String nombre;
	private Calendar actualizacion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdJustificacion() {
		return idJustificacion;
	}
	public void setIdJustificacion(Long idJustificacion) {
		this.idJustificacion = idJustificacion;
	}
	
	@Column
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	@Column
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column
	public Calendar getActualizacion() {
		return actualizacion;
	}
	public void setActualizacion(Calendar actualizacion) {
		this.actualizacion = actualizacion;
	}
	
	
}
