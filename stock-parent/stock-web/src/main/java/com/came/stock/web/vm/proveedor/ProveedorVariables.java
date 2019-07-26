package com.came.stock.web.vm.proveedor;

import java.util.Date;

import com.came.stock.model.domain.ProveedorProducto;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.BasicStructure;

public abstract class ProveedorVariables extends BasicStructure {
	private static final long serialVersionUID = -806765252363100225L;
	protected boolean guardadoEmailProveedor;
	protected boolean guardadoEmailContacto;
	protected boolean guardadoTelefonoProveedor;
	protected boolean guardadoTelefonoContacto;
	protected boolean guardadoContactoProveedor;
	protected boolean guardadoContactoContacto;
	protected boolean guardadoDireccionProveedor;
	protected boolean guardadorepResentanteLegalProveedor;
	protected boolean guardadoPersonaContacto;
	protected boolean guardadoNuevoProveedor;
	protected boolean guardadoCuentaPago;
	protected Date contratoVigenciaInicio;
	protected Date contratoVigenciaFin;
	protected ProveedorProducto nuevoProveedorProducto;

	public Date getContratoVigenciaInicio() {
		return this.contratoVigenciaInicio;
	}

	public void setContratoVigenciaInicio(Date contratoVigenciaInicio) {
		if (contratoVigenciaInicio != null) {
			this.contrato.setFechaVigenciaInicio(new StockUtils().convertirDateToCalendar(contratoVigenciaInicio));
		}
		this.contratoVigenciaInicio = contratoVigenciaInicio;
	}

	public Date getContratoVigenciaFin() {
		return this.contratoVigenciaFin;
	}

	public void setContratoVigenciaFin(Date contratoVigenciaFin) {
		if (contratoVigenciaFin != null) {
			this.contrato.setFechaVigenciaFin(new StockUtils().convertirDateToCalendar(contratoVigenciaFin));
		}
		this.contratoVigenciaFin = contratoVigenciaFin;
	}
}
