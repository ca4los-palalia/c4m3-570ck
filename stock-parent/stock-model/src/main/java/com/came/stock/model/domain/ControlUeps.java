package com.came.stock.model.domain;

public class ControlUeps {

	private Kardex kardex;
	private String descripcion;
	private boolean completo;
	private Integer cantidadExtraida;
	private Float precio;
	private Integer restan;
	private int restanEnKardex;

	public Kardex getKardex() {
		return kardex;
	}

	public void setKardex(Kardex kardex) {
		this.kardex = kardex;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isCompleto() {
		return completo;
	}

	public void setCompleto(boolean completo) {
		this.completo = completo;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Integer getRestan() {
		return restan;
	}

	public void setRestan(Integer restan) {
		this.restan = restan;
	}

	public Integer getCantidadExtraida() {
		return cantidadExtraida;
	}

	public void setCantidadExtraida(Integer cantidadExtraida) {
		this.cantidadExtraida = cantidadExtraida;
	}

	public int getRestanEnKardex() {
		return restanEnKardex;
	}

	public void setRestanEnKardex(int restanEnKardex) {
		this.restanEnKardex = restanEnKardex;
	}
	

	
}


