package com.came.stock.model.domain;

import java.io.Serializable;
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

import com.came.stock.utilidades.StockUtils;


@Entity
@Table
public class ProductoCostos implements Serializable {
	private static final long serialVersionUID = 5993909174529175206L;

	private Long idProductoCostos;
	
	private Calendar actualizacion;
	private Float costoValue;
	private String costoDescripcion;
	private Integer index;
	private CostosTipos costosTipos;
	private String fecha;
	
	/*
	private Float	costoCapa;
	private Float	costoMaximo;
	private Float	costoPromedio;
	private Float	costoReposicion;
	private Float	costoUltimo;

	*/
	
	
	private Producto producto;
	private Organizacion organizacion;
	private Usuarios usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProductoCostos() {
		return idProductoCostos;
	}
	public void setIdProductoCostos(Long idProductoCostos) {
		this.idProductoCostos = idProductoCostos;
	}
	@Column
	public Calendar getActualizacion() {
		return actualizacion;
	}
	public void setActualizacion(Calendar actualizacion) {
		this.actualizacion = actualizacion;
	}
	
	@Column
	public Float getCostoValue() {
		return costoValue;
	}
	public void setCostoValue(Float costoValue) {
		this.costoValue = costoValue;
	}
	
	@Column
	public String getCostoDescripcion() {
		return costoDescripcion;
	}
	public void setCostoDescripcion(String costoDescripcion) {
		this.costoDescripcion = costoDescripcion;
	}
	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	@OneToOne
	@JoinColumn(name = "costosTipos")
	public CostosTipos getCostosTipos() {
		return costosTipos;
	}
	public void setCostosTipos(CostosTipos costosTipos) {
		this.costosTipos = costosTipos;
	}
	
	@Transient
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	@Transient
	public String getFecha() {
		if(actualizacion != null){
			StockUtils util = new StockUtils();
			fecha = util.convertirCalendarToStringFormato1(actualizacion);
		}
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
}
