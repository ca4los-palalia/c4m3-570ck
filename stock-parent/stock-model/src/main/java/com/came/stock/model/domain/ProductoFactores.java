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
public class ProductoFactores implements Serializable {
	private static final long serialVersionUID = 5993909174529175206L;

	private Long idProductoFactores;
	
	private Calendar actualizacion;
	private Integer factorValue;
	private String  factorDescripcion;
	private Integer index;
	//factor1 - factor9
	
	private Producto producto;
	private Organizacion organizacion;
	private Usuarios usuario;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProductoFactores() {
		return idProductoFactores;
	}
	public void setIdProductoFactores(Long idProductoFactores) {
		this.idProductoFactores = idProductoFactores;
	}
	@Column
	public Calendar getActualizacion() {
		return actualizacion;
	}
	public void setActualizacion(Calendar actualizacion) {
		this.actualizacion = actualizacion;
	}
	
	@Column
	public Integer getFactorValue() {
		return factorValue;
	}
	public void setFactorValue(Integer factorValue) {
		this.factorValue = factorValue;
	}
	
	@Column
	public String getFactorDescripcion() {
		return factorDescripcion;
	}
	public void setFactorDescripcion(String factorDescripcion) {
		this.factorDescripcion = factorDescripcion;
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
