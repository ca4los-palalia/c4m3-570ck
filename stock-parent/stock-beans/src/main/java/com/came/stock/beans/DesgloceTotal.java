package com.came.stock.beans;

import java.io.Serializable;

public class DesgloceTotal implements Serializable {

	private static final long serialVersionUID = 1685033108617582557L;

	private Float subtotal;
	private Float iva;
	private Float total;

	public Float getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getIva() {
		return this.iva;
	}

	public void setIva(Float iva) {
		this.iva = iva;
	}

	public Float getTotal() {
		return this.total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
}
