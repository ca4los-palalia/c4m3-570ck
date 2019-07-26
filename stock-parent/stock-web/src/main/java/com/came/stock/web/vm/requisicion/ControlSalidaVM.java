package com.came.stock.web.vm.requisicion;

import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.came.stock.model.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlSalidaVM extends ControlMetaclass {
	private static final long serialVersionUID = -1363727052274326014L;
	
	@Init
	public void init() {
		super.init();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
	}
	
	
	
	
}
