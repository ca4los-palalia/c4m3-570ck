package com.came.stock.web.vm.ordencompra.utils;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.requisicion.utils.RequisicionVariables;

public class CancelarOrdenCompraVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";
	@Wire("#modalDialog")
	private Window win;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view, @ExecutionArgParam("orden") OrdenCompra ct) {
		super.init();
		if (this.ordenCompra == null) {
			this.ordenCompra = new OrdenCompra();
		}
		this.ordenCompra = ct;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		if ((this.ordenCompra != null) && (this.ordenCompra.getCancelarDescripcion() != null)
				&& (!this.ordenCompra.getCancelarDescripcion().isEmpty())) {
			try {
				EstatusRequisicion estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("OCC").getSingle();

				ordenCompra.setEstatusRequisicion(estado);
				ordenCompra = (OrdenCompra) ordenCompraRest.save(ordenCompra).getSingle();
				
				actualizarRequisicion("RQT");

				this.win.detach();
			} catch (Exception e) {
			}
		} else {
			StockUtils.showSuccessmessage("Por favor ingrese el motivo de cancelaci�n", "warning", Integer.valueOf(0),
					null);
		}
	}

	@Command
	public void discart() {
		if (this.win != null) {
			this.win.detach();
		}
	}
	
	private void actualizarRequisicion(String clave){
		EstatusRequisicion estatus = (EstatusRequisicion) estatusRequisicionRest.getByClave(clave).getSingle();
		cotizacionSelected.getRequisicion().setEstatusRequisicion(estatus);
		cotizacionSelected.setRequisicion((Requisicion) requisicionRest.save(cotizacionSelected.getRequisicion()).getSingle());
		
	}
}
