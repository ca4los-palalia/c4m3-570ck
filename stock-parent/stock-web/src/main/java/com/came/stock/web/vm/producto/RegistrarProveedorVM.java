package com.came.stock.web.vm.producto;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorUsuario;
import com.came.stock.model.domain.Telefono;
import com.came.stock.model.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class RegistrarProveedorVM extends RegistrarProveedorMetaClass {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#registrarProveedorModalDialog")
	private Window windowModalDialog;
	private String globalCommandName;
	private boolean usuarioValido;
	private boolean contraValido;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder) {

		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		leerVariablesProperties();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	@NotifyChange({ "porcentajeWidth", "proveedorSelected", "proveedorUser", "proveedorContrasena",
			"proveedorContrasenaRepetir" })
	public void transfer(@BindingParam("compRegistra") Component comp) {
		String registro = registrarNuevoProveedor();
		if (registro.isEmpty()) {
			stockUtils.showSuccessmessage("Registro exitoso", Clients.NOTIFICATION_TYPE_INFO, 0, comp);
			windowModalDialog.detach();
			Map<String, Object> args = new HashMap();
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		} else
			stockUtils.showSuccessmessage(registro, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	public void closeDialog() {
		if (windowModalDialog != null) {
			windowModalDialog.detach();
		}
	}

	@Command
	@NotifyChange({ "proveedorSelected", "municipios" })
	public void getInformacionDireccionFromCp(@BindingParam("compOrgCp") Component comp) {

		if (proveedorSelected.getDireccionFiscal().getCp() != null
				&& !proveedorSelected.getDireccionFiscal().getCp().equals("")) {
			datosGlobalesJSON = inicializarConexionJsonUrl(proveedorSelected.getDireccionFiscal().getCp());
			direccionJSon = new Direccion();
			contadorCamposCodigoPostal = 0;

			dumpCodigoPostalJSONElement(datosGlobalesJSON);

			if (contadorCamposCodigoPostal == 4) {

				if (direccionJSon.getColonias().size() > 1) {
					Map<String, Object> inputParams = new HashMap();
					inputParams.put("itemFinder", "updateCodigoPostalSeleccionado");
					inputParams.put("direccionConstruida", direccionJSon);
					inputParams.put("componente", comp);

					Window productoModalView = stockUtils.createModelDialogWithParams(
							"/modulos/productos/utils/seleccionarCodigoPostal.zul", inputParams);
					productoModalView.doModal();
				} else {
					proveedorSelected.getDireccionFiscal().setCuidad(direccionJSon.getMunicipio().getNombre());
					proveedorSelected.getDireccionFiscal().setColonia(direccionJSon.getColonias().get(0));
					proveedorSelected.getDireccionFiscal().setEstado(direccionJSon.getEstado());
					proveedorSelected.getDireccionFiscal().setMunicipio(direccionJSon.getMunicipio());
					proveedorSelected.getDireccionFiscal().setPais(iteratorList.getPaisFromList(paises, 157L));

				}
			} else
				stockUtils.showSuccessmessage("Codigo postal no encontrado", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			stockUtils.showSuccessmessage("Ingrese un codigo postal", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@GlobalCommand
	@NotifyChange({ "proveedorSelected", "municipios" })
	public void updateCodigoPostalSeleccionado(@BindingParam("direccionReturn") Direccion itemRecibido,
			@BindingParam("componenteReturn") Component comp) {

		if (itemRecibido != null) {
			proveedorSelected.getDireccionFiscal().setCuidad(direccionJSon.getMunicipio().getNombre());
			proveedorSelected.getDireccionFiscal().setColonia(itemRecibido.getColonia());
			proveedorSelected.getDireccionFiscal().setEstado(direccionJSon.getEstado());
			// proveedorSelected.getDireccionFiscal().setMunicipio(direccionJSon.getMunicipio());

			proveedorSelected.getDireccionFiscal().setPais(iteratorList.getPaisFromList(paises, 157L));
			proveedorSelected.getDireccionFiscal().setEstado(direccionJSon.getEstado());
			proveedorSelected.getDireccionFiscal().setMunicipio(direccionJSon.getMunicipio());

			stockUtils.showSuccessmessage("Direccion del codigo postal asignado", Clients.NOTIFICATION_TYPE_INFO, 0,
					comp);

		}
	}

	@Command
	@NotifyChange({ "municipios" })
	public void getMunicipiosSelectedEstadoItem() {
		municipios = (List<Municipio>) municipioRest.getByEstado(proveedorSelected.getDireccionFiscal().getEstado()).getSingle();
	}

	@Command
	public void comprobarDisponibilidad(@BindingParam("compDisponible") Component comp) {
		String mensaje = validarUsuario();
		if (mensaje.equals("")) {
			usuarioValido = true;
		} else {
			stockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	private String validarUsuario() {
		if (proveedorUser != null && !proveedorUser.isEmpty()) {
			List<ProveedorUsuario> user = (List<ProveedorUsuario>) proveedorUsuarioRest.getLikeNombre(proveedorUser).getSingle();
			if (user == null || user.size() == 0) {
				return "";
			} else
				return "Ya existe el usuario " + proveedorUser;
		} else
			return "Ingrese un nombre de usuario";
	}

	@Command
	public void comprobarContrasena(@BindingParam("compContra") Component comp) {
		String mensaje = validarContrasena();
		if (mensaje.equals("")) {
			contraValido = true;
		} else {
			stockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	private String validarContrasena() {
		if (!proveedorContrasena.equals(proveedorContrasenaRepetir))
			return "La contraseña no concuerda";
		else
			return "";
	}

	private String registrarNuevoProveedor() {
		String registroExitoso = "";

		String validar = validarFormularioRegistro();
		if (validar.equals("")) {
			if (usuarioValido) {
				if (contraValido) {
					Persona p = new Persona();
					p = (Persona) personaRest.save(p).getSingle();

					Usuarios user = new Usuarios();
					String passCodificado = stockUtils.Encriptar(proveedorContrasena.toString());
					String userCodificado = stockUtils.Encriptar(proveedorUser.toString());

					user.setBenutzer(userCodificado);
					user.setKennwort(passCodificado);
					user.setOwner(false);
					user.setPersona(p);

					Organizacion org = new Organizacion();
					org.setNombre(proveedorSelected.getNombre());
					org.setRfc(proveedorSelected.getRfc());
					org.setActivar(true);
					org.setDisableActiv(true);
					org.setProveedor(true);

					AImage aimagen = getAImageOrganizacionInicial("layout/companyProfile.png");
					org.setLogotipo(aimagen.getByteData());
					org = (Organizacion) organizacionRest.save(org).getSingle();
					user.setOrganizacion(org);

					user = (Usuarios)usuarioRest.save(user).getSingle();

					proveedorSelected.setDireccionFiscal( (Direccion) direccionRest.save(proveedorSelected.getDireccionFiscal()).getSingle());
					proveedorSelected.getContacto().setTelefono((Telefono)telefonoRest.save(proveedorSelected.getContacto().getTelefono()).getSingle());					
					proveedorSelected.getContacto().setEmail((Email) emailRest.save(proveedorSelected.getContacto().getEmail()).getSingle());
					proveedorSelected.setContacto((Contacto) contactoRest.save(proveedorSelected.getContacto()).getSingle());
					proveedorSelected = (Proveedor) proveedorRest.save(proveedorSelected).getSingle();

					ProveedorUsuario proveedorUsuario = new ProveedorUsuario();
					proveedorUsuario.setFechaActualizacion(Calendar.getInstance());
					proveedorUsuario.setProveedor(proveedorSelected);
					proveedorUsuario.setUsuarios(user);
					proveedorUsuario = (ProveedorUsuario) proveedorUsuarioRest.save(proveedorUsuario).getSingle();
					limpiarFormularioRegistro();
				} else
					registroExitoso = "La contraseña no concuerda";
			} else
				registroExitoso = "Verifique la disponibilidad del usuario";
		} else
			registroExitoso = validar;
		return registroExitoso;
	}

	private String validarFormularioRegistro() {
		String mensaje = "";
		if (proveedorSelected.getNombre() != null && !proveedorSelected.getNombre().isEmpty()) {
			if (proveedorSelected.getRfc() != null && !proveedorSelected.getRfc().isEmpty()) {
				if (proveedorSelected.getDireccionFiscal().getCalle() != null
						&& !proveedorSelected.getDireccionFiscal().getCalle().isEmpty()) {
					if (proveedorSelected.getDireccionFiscal().getNumExt() != null
							&& !proveedorSelected.getDireccionFiscal().getNumExt().isEmpty()) {
						if (proveedorSelected.getContacto().getEmail().getEmail() != null
								&& !proveedorSelected.getContacto().getEmail().getEmail().isEmpty()) {

						} else
							mensaje = "Debe especificar correo electronico del proveedor";
					} else
						mensaje = "Debe especificar el numero exterior del proveedor";
				} else
					mensaje = "Debe especificar el nombre de la calle del proveedor";
			} else
				mensaje = "Es requerido el RFC del proveedor";
		} else
			mensaje = "Debe especificar el nombre del proveedor";

		return mensaje;
	}

	private void limpiarFormularioRegistro() {
		proveedorUser = "";
		proveedorContrasena = "";
		proveedorContrasenaRepetir = "";

		porcentajeWidth = "40%";
		proveedorSelected = new Proveedor();
		proveedorSelected.setDireccionFiscal(new Direccion());

		Contacto c = new Contacto();
		c.setEmail(new Email());
		c.setTelefono(new Telefono());
		proveedorSelected.setContacto(c);
		usuarioValido = false;
		contraValido = false;
	}

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		WINDOW_SELECT_FUENTE_FINANCIAMIENTO_TITLE = propiedades
				.getProperty("window.select.fuente.financiamiento.title");// Seleccionar Fuente de financiamiento
		WINDOW_SELECT_FUENTE_FINANCIAMIENTO_EMPTY = propiedades
				.getProperty("window.select.fuente.financiamiento.empty");// Debe seleccionar previamente un proyecto
		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");// Clave
		GENERICZUL_COLUMN_NOMBRE = propiedades.getProperty("genericZUL.column.nombre");// Nombre
		GENERICZUL_COMMAND_CANCELAR = propiedades.getProperty("genericZUL.command.cancelar");// Cancelar
		GENERICZUL_COMMAND_ACEPTAR = propiedades.getProperty("genericZUL.command.aceptar");// Aceptar
	}

}
