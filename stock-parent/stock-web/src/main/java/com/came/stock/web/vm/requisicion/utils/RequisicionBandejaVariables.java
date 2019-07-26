package com.came.stock.web.vm.requisicion.utils;

import java.util.List;

import com.came.stock.model.domain.Requisicion;
import com.came.stock.web.vm.BasicStructure;

public class RequisicionBandejaVariables extends BasicStructure {
	private static final long serialVersionUID = -2506064720678078791L;
	protected List<Requisicion> requisiciones;
	protected Requisicion requisicionSeleccionada;

	public void init() {
		this.requisicionSeleccionada = new Requisicion();
	}

	public List<Requisicion> getRequisiciones() {
		return this.requisiciones;
	}

	public void setRequisiciones(List<Requisicion> requisiciones) {
		this.requisiciones = requisiciones;
	}

	public Requisicion getRequisicionSeleccionada() {
		return this.requisicionSeleccionada;
	}

	public void setRequisicionSeleccionada(Requisicion requisicionSeleccionada) {
		this.requisicionSeleccionada = requisicionSeleccionada;
	}
}
