package com.came.stock.model.domain;

import java.io.Serializable;
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
public class FamiliasProducto implements Serializable {
	private static final long serialVersionUID = -7955290534021913746L;
	private Long idFamiliasProducto;
	private ProductoTipo productoTipo;
	private Producto producto;
	private boolean eliminar;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdFamiliasProducto() {
		return this.idFamiliasProducto;
	}

	public void setIdFamiliasProducto(Long idFamiliasProducto) {
		this.idFamiliasProducto = idFamiliasProducto;
	}

	@OneToOne
	@JoinColumn(name = "productoTipo")
	public ProductoTipo getProductoTipo() {
		return this.productoTipo;
	}

	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
	}

	@OneToOne
	@JoinColumn(name = "producto")
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	@Transient
	public boolean isEliminar() {
		return eliminar;
	}

	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	
}
