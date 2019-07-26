package com.came.stock.web.vm.controlpanel.utils;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.util.Clients;

import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.controlpanel.UsuariosClientesVM;

public class UsuarioClientesValidator extends AbstractValidator {
	private Map<String, Property> beanProps;
	private boolean usuarioExistente;

	public void validate(ValidationContext ctx) {
		beanProps = ctx.getProperties(ctx.getProperty().getBase());
		UsuariosClientesVM usuariosClientesVM = (UsuariosClientesVM) ((Property) this.beanProps.get(".")).getBase();
		if (usuariosClientesVM != null) {

			if (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getNombre() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona().getNombre().isEmpty()) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("Nombre requerido", Clients.NOTIFICATION_TYPE_WARNING, 0,
						usuariosClientesVM.getCompInputNombre());
				return;
			}
			if (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoPaterno() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoPaterno().isEmpty()) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("Apellido paterno requerido", Clients.NOTIFICATION_TYPE_WARNING, 0,
						usuariosClientesVM.getCompInputPaterno());
				return;
			}
			if (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoMaterno() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoMaterno().isEmpty()) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("Apellido materno requerido", Clients.NOTIFICATION_TYPE_WARNING, 0,
						usuariosClientesVM.getCompInputMaterno());
				return;
			}

			if (usuariosClientesVM.getUsuarioSeleccionado().getBenDec() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getBenDec().isEmpty()) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("Usuario requerido", Clients.NOTIFICATION_TYPE_WARNING, 0,
						usuariosClientesVM.getCompInputUsuario());
				return;
			}
			if ((usuariosClientesVM.getUsuarioSeleccionado().getKenDec() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getKenDec().isEmpty())
					|| (usuariosClientesVM.getUsuarioSeleccionado().getRetypeKennwort() == null
							|| usuariosClientesVM.getUsuarioSeleccionado().getRetypeKennwort().isEmpty())) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("Contraseña requerida", Clients.NOTIFICATION_TYPE_WARNING, 0,
						usuariosClientesVM.getCompInputPassword());
				return;
			}
			if (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getContacto().getEmail().getEmail() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona().getContacto().getEmail().getEmail()
							.isEmpty()) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("Correo electronico requerido", Clients.NOTIFICATION_TYPE_WARNING, 0,
						usuariosClientesVM.getCompInputEmail());
				return;
			}
			if (usuariosClientesVM.getUsuarioSeleccionado().getArea().getClaveMasNombre() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getArea().getClaveMasNombre().isEmpty()) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("UR requerida", Clients.NOTIFICATION_TYPE_WARNING, 0,
						usuariosClientesVM.getCompInputUr());
				return;
			}
			if (!usuariosClientesVM.getUsuarioSeleccionado().getKenDec()
					.equals(usuariosClientesVM.getUsuarioSeleccionado().getRetypeKennwort())) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("La contraseña no coinciden, verifique y vuelva a intentar",
						Clients.NOTIFICATION_TYPE_WARNING, 0, usuariosClientesVM.getCompInputPasswordRetype());
				return;
			}
			if (!usuariosClientesVM.privilegioRequision && !usuariosClientesVM.privilegioConcentrado
					&& !usuariosClientesVM.privilegioCotizacionAutorizacion
					&& !usuariosClientesVM.privilegioOrdenCompra) {
				addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage("Debe seleccionar al menos una restriccion para los privilegios",
						Clients.NOTIFICATION_TYPE_WARNING, 0, usuariosClientesVM.getCompInputFilaPermisos());
				return;
			}

			/*
			 * usuarioExistente =
			 * usuariosClientesVM.getUsuarioService().verificarNombreUsuario(
			 * usuariosClientesVM.getUsuarioSeleccionado().getBenDec(),
			 * usuariosClientesVM.getUsuarioSeleccionado().getIdUsuario()); if
			 * (usuarioExistente) { StockUtils.showSuccessmessage(
			 * "El nombre de usuario que ha especificado ya se encuentra en uso, por favor ingrese uno diferente"
			 * , "warning", Integer.valueOf(0),
			 * usuariosClientesVM.getCompInputUsuario());
			 * 
			 * addInvalidMessage(ctx, ""); return; }
			 */
		}
	}
}
