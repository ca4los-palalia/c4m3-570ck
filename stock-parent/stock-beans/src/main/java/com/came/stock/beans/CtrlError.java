package com.came.stock.beans;

import java.io.Serializable;

public class CtrlError implements Serializable {

	private static final long serialVersionUID = 7010506227137145135L;

	private Object objetoTratado;
	private Exception excepcion;
	private String mensaje;
	private boolean ok;

	public Object getObjetoTratado() {
		return objetoTratado;
	}

	public void setObjetoTratado(Object objetoTratado) {
		this.objetoTratado = objetoTratado;
	}

	public Exception getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(Exception excepcion) {
		this.excepcion = excepcion;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

}
