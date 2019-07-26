package com.came.stock.beans;

import java.io.Serializable;

public class CfgCore implements Serializable {

	private static final long serialVersionUID = -811276154960151082L;
	private Long id;
	private String atributo;
	private String valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
