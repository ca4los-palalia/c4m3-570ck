package com.came.stock.model.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Direccion implements Serializable {
	private static final long serialVersionUID = -46282038792540869L;
	private Long idDireccion;
	private String calle;
	private String colonia;
	private List<String>colonias;
	private String cp;
	private String cuidad;
	private String numExt;
	private String numInt;
	private Estado estado;
	private Municipio municipio;
	private String municipioTemporal;
	private Pais pais;
	
	private String direccion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdDireccion() {
		return this.idDireccion;
	}

	public void setIdDireccion(Long idDireccion) {
		this.idDireccion = idDireccion;
	}

	@Column
	public String getCalle() {
		return this.calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column
	public String getCuidad() {
		return this.cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	@Column
	public String getColonia() {
		return this.colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	@Column
	public String getCp() {
		return this.cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	@Column
	public String getNumExt() {
		return this.numExt;
	}

	public void setNumExt(String numExt) {
		this.numExt = numExt;
	}

	@Column
	public String getNumInt() {
		return this.numInt;
	}

	public void setNumInt(String numInt) {
		this.numInt = numInt;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estado")
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "municipio")
	public Municipio getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pais")
	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@Transient
	public String getDireccion() {
		String full = "";
		if(calle != null && !calle.equals(""))
			full += calle;
		if(numExt != null && !numExt.equals(""))
			full += " " + numExt;
		if(numInt != null && !numInt.equals(""))
			full += " int. " + numInt;
		if(colonia != null && !colonia.equals(""))
			full += ", " + colonia;
		if(cp != null && !cp.equals(""))
			full += ", " + cp;
		if(municipio != null)
			full += ", " + municipio.getNombre();
		if(estado != null)
			full += ", " + estado.getNombre();
		if(pais != null)
			full += ", " + pais.getNombre();
		
		direccion = full;
		
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Transient
	public List<String> getColonias() {
		return colonias;
	}

	public void setColonias(List<String> colonias) {
		this.colonias = colonias;
	}

	@Transient
	public String getMunicipioTemporal() {
		return municipioTemporal;
	}

	public void setMunicipioTemporal(String municipioTemporal) {
		this.municipioTemporal = municipioTemporal;
	}


	
	
	
	
}
