package com.came.stock.web.vm.requisicion;

import java.util.Calendar;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.CotizacionInbox;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.requisicion.utils.RequisicionVariables;

@VariableResolver({ DelegatingVariableResolver.class })
public class CancelarCotizacionVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";
	@Wire("#modalDialog")
	private Window win;

	private Component componente;
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("cotizacion") Cotizacion ct,
			@ExecutionArgParam("componente") Component comp) {
		super.init();
		if (this.cotizacionSelected == null) {
			this.cotizacionSelected = new Cotizacion();
		}
		this.cotizacionSelected = ct;
		this.componente = comp;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		if ((this.cotizacionSelected != null) && (this.cotizacionSelected.getCancelarDescripcion() != null)
				&& (!this.cotizacionSelected.getCancelarDescripcion().isEmpty())) {
			try {
				EstatusRequisicion estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("COC").getSingle();

				cotizacionSelected.setEstatusRequisicion(estado);
				cotizacionSelected = (Cotizacion) cotizacionRest.save(cotizacionSelected).getSingle();
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setFechaRegistro(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				inbox.setCotizacion(this.cotizacionSelected);
				inbox.setLeido(Boolean.valueOf(false));
				inbox = (CotizacionInbox) cotizacionInboxRest.save(inbox).getSingle();
				
				requisicionProductos = (List<RequisicionProducto>) requisicionProductoRest
						.getByRequisicion(cotizacionSelected.getRequisicion(), organizacion).getSingle();
				for (RequisicionProducto item : this.requisicionProductos) {
					item.setDescripcion("Cotizaci�n cancelada");
					item = (RequisicionProducto) requisicionProductoRest.save(item).getSingle();
				}
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
