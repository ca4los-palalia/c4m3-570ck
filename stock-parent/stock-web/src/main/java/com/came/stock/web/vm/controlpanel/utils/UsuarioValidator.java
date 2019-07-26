package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.UsuarioVM;
import java.io.Serializable;
import java.util.Map;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.util.Clients;

public class UsuarioValidator extends AbstractValidator implements Serializable {
	private static final long serialVersionUID = 876922188396805982L;
	private Map<String, Property> beanProps;

	public void validate(ValidationContext ctx) {
		this.beanProps = ctx.getProperties(ctx.getProperty().getBase());
		UsuarioVM usuarioVM = (UsuarioVM) ((Property) this.beanProps.get(".")).getBase();
		
		if (usuarioVM.getUsuario().getBenDec() == null || usuarioVM.getUsuario().getBenDec().isEmpty()) {
			addInvalidMessage(ctx, "");
			StockUtils.showSuccessmessage("Nombre de usuario requerido", Clients.NOTIFICATION_TYPE_WARNING, 0,
					usuarioVM.getCompTxtUsuarioAlias());			
			return;
		}
		if(usuarioVM.getUsuario().getKenDec() == null || usuarioVM.getUsuario().getKenDec().isEmpty()){
			addInvalidMessage(ctx, "");
			StockUtils.showSuccessmessage("Contraseña requerida", Clients.NOTIFICATION_TYPE_WARNING, 0,
					usuarioVM.getCompTxtContrasena1());
			return;
		}
		
		if(usuarioVM.getUsuario().getRetypeKennwort() == null || usuarioVM.getUsuario().getRetypeKennwort().isEmpty()){
			addInvalidMessage(ctx, "");
			StockUtils.showSuccessmessage("Contraseña requerida", Clients.NOTIFICATION_TYPE_WARNING, 0,
					usuarioVM.getCompTxtContrasena2());
			return;
		}
		
		
		if(!usuarioVM.getUsuario().getKenDec().equals(usuarioVM.getUsuario().getRetypeKennwort())){
			addInvalidMessage(ctx, "");
			StockUtils.showSuccessmessage("La contraseña no coinciden, verifique y vuelva a intentar", Clients.NOTIFICATION_TYPE_WARNING, 0,
					usuarioVM.getCompTxtContrasena2());
			return;
		}
	}
}
