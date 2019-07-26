package com.came.stock.web;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Estado;
import com.came.stock.web.services.EstadoRest;

public class ModalUtils extends SelectorComposer<Component> {
	private static final long serialVersionUID = 7430264817059488060L;
	
	@WireVariable
	private EstadoRest estadoRest;

	@Listen("onClick = #dirButton")
	public void showModal(Event e) {
		Window window = (Window) Executions.createComponents("/modulos/ShowModalDetails.zul", null, null);
		window.doModal();
	}

	@Listen("onClick = #contactoId")
	public void getInfoormacion(Event e) {
		Estado estado = (Estado) estadoRest.getById(1L).getSingle();
		System.err.println(estado.getNombre());
	}
}
