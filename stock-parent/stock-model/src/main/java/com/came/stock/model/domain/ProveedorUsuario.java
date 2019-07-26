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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class ProveedorUsuario implements Serializable {
	private static final long serialVersionUID = 5955369590591544443L;
	private Long idProveedorUsuario;
	private Calendar fechaActualizacion;
	private Proveedor proveedor;
	private Organizacion organizacion;
	private Usuarios usuarios;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdProveedorUsuario() {
		return idProveedorUsuario;
	}
	public void setIdProveedorUsuario(Long idProveedorUsuario) {
		this.idProveedorUsuario = idProveedorUsuario;
	}
	
	@Temporal(TemporalType.DATE)
	@Column
	public Calendar getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Calendar fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "proveedor")
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
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
	@JoinColumn(name = "usuarios")
	public Usuarios getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	
	
	
	
}
