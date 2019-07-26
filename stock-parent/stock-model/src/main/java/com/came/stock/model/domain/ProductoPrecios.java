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

@Entity
@Table
public class ProductoPrecios implements Serializable {
	private static final long serialVersionUID = 5993909174529175206L;

	private Long idProductoPrecios;
	
	private Calendar actualizacion;
	private Float precioValue;
	private String precioDescripcion;
	
	//precio1 - precio10, precioPublico
		
	private Producto producto;
	private Organizacion organizacion;
	private Usuarios usuario;
	private Integer index;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProductoPrecios() {
		return idProductoPrecios;
	}
	public void setIdProductoPrecios(Long idProductoPrecios) {
		this.idProductoPrecios = idProductoPrecios;
	}
	@Column
	public Calendar getActualizacion() {
		return actualizacion;
	}
	public void setActualizacion(Calendar actualizacion) {
		this.actualizacion = actualizacion;
	}
	
	@Column
	public Float getPrecioValue() {
		return precioValue;
	}
	public void setPrecioValue(Float precioValue) {
		this.precioValue = precioValue;
	}
	
	@Column
	public String getPrecioDescripcion() {
		return precioDescripcion;
	}
	public void setPrecioDescripcion(String precioDescripcion) {
		this.precioDescripcion = precioDescripcion;
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
	
	@Transient
	public Integer getIndex() {
		return index;
	}
	public void setIndex(Integer index) {
		this.index = index;
	}
	
}
