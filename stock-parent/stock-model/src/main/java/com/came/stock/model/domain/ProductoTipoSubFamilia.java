package com.came.stock.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class ProductoTipoSubFamilia {
	
	private Long idProductoTipoSubFamilia;
	private String nombre;
	private String descripcion;
	private ProductoTipo productoTipo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdProductoTipoSubFamilia() {
		return idProductoTipoSubFamilia;
	}
	public void setIdProductoTipoSubFamilia(Long idProductoTipoSubFamilia) {
		this.idProductoTipoSubFamilia = idProductoTipoSubFamilia;
	}
	
	@Column
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToOne
	@JoinColumn(name = "productoTipo")
	public ProductoTipo getProductoTipo() {
		return productoTipo;
	}
	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
	}
	@Column
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//@Transient	
}
