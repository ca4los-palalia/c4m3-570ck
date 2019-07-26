package com.came.stock.web.vm.perfil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Banner;
import com.came.stock.model.domain.ConfiguracionCorreo;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Pais;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Telefono;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@VariableResolver({ DelegatingVariableResolver.class })
public class PerfilVM extends PerfilMetaClass {
	private static final long serialVersionUID = 2445512192837362588L;

	@Init
	public void init() {

		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
		bannerList = (List<Banner>) bannerRest.getAll().getSingle();
		bannerNuevo = new Banner();
		bannerNuevo.setOrganizacion(organizacion);
		bannerNuevo.setUsuario(usuario);
		configuracionCorreos = (List<ConfiguracionCorreo>) configuracionCorreoRest.getAll().getSingle();
		if (configuracionCorreos != null)
			configuracionCorreo = configuracionCorreos.get(0);
		else
			configuracionCorreo = new ConfiguracionCorreo();

		typePassword = "password";

		habilitarPaisEstadoMunicipio = true;

		if (paises == null || paises.size() == 0)
			paises = (List<Pais>) paisRest.getAll().getSingle();
		if (estados == null || estados.size() == 0)
			estados = (List<Estado>) estadoRest.getAll().getSingle();
		if (municipios == null || municipios.size() == 0)
			municipios = (List<Municipio>) municipioRest.getAll().getSingle();

		if (usuario.getOwner()) {
			inicializarObjetosOrganizacion();
			verOrganizacion = true;
			desabilitarOrgUsuarios = true;
		}else if (usuario.getClient()) {
			disabledCampos = true;
			desabilitarOrgUsuarios = true;
		}else if(!usuario.getOwner() && !usuario.getClient() )
			desabilitarOrgUsuarios = false;
		
		
		inicializarObjetosUsuario();
		logotipoAImage = usuario.getOrganizacion().getLogotipoAImage();
		toolImagenLogo = (DevelopmentTool) developmentToolRest.getByNombre("transicion").getSingle();
		if (toolImagenLogo.getValue().equals("true")) {
			toolImagenLogoChecked = true;
		}
		recuperarModalidad();
		recuperarConfiguracionActivacionProveedor();
		recuperarMensajeEncabezadoLogin();
		recuperarVersionDelSistema();
		getStylesGlobal();
	}

	private void inicializarObjetosUsuario() {

		if (usuario.getPersona() == null)
			personaUsuario = new Persona();
		else
			personaUsuario = usuario.getPersona();

		if (personaUsuario.getDireccion() == null)
			direccionUsuario = new Direccion();
		else
			direccionUsuario = personaUsuario.getDireccion();

		if (direccionUsuario.getPais() == null)
			paisUsuario = new Pais();
		else
			paisUsuario = iteratorList.getPaisFromList(paises, direccionUsuario.getPais().getIdPais());

		if (direccionUsuario.getEstado() == null)
			estadoUsuario = new Estado();
		else
			estadoUsuario = iteratorList.getEstadoFromList(estados, direccionUsuario.getEstado().getIdEstado());

		if (direccionUsuario.getMunicipio() == null)
			municipioUsuario = new Municipio();
		else
			municipioUsuario = iteratorList.getMunicipioFromList(municipios, direccionUsuario.getMunicipio().getIdMunicipio());

		if (personaUsuario.getContacto() == null)
			contactoUsuario = new Contacto();
		else
			contactoUsuario = personaUsuario.getContacto();

		if (contactoUsuario.getEmail() == null)
			emailUsuario = new Email();
		else
			emailUsuario = contactoUsuario.getEmail();

		if (contactoUsuario.getTelefono() == null)
			telefonoUsuario = new Telefono();
		else
			telefonoUsuario = contactoUsuario.getTelefono();
	}

	private void inicializarObjetosOrganizacion() {
		if (organizacion.getDireccion() == null)
			direccionOrganizacion = new Direccion();
		else
			direccionOrganizacion = organizacion.getDireccion();

		if (direccionOrganizacion.getPais() == null)
			paisOrganizacion = new Pais();
		else
			paisOrganizacion = iteratorList.getPaisFromList(paises, direccionOrganizacion.getPais().getIdPais());

		if (direccionOrganizacion.getEstado() == null)
			estadoOrganizacion = new Estado();
		else
			estadoOrganizacion = iteratorList.getEstadoFromList(estados, direccionOrganizacion.getEstado().getIdEstado());

		if (direccionOrganizacion.getMunicipio() == null)
			municipioOrganizacion = new Municipio();
		else
			municipioOrganizacion = iteratorList.getMunicipioFromList(municipios, direccionOrganizacion.getMunicipio().getIdMunicipio());

		if (organizacion.getContacto() == null)
			contactoOrganizacion = new Contacto();
		else
			contactoOrganizacion = organizacion.getContacto();

		if (contactoOrganizacion.getEmail() == null)
			emailOrganizacion = new Email();
		else
			emailOrganizacion = contactoOrganizacion.getEmail();

		if (contactoOrganizacion.getTelefono() == null)
			telefonoOrganizacion = new Telefono();
		else
			telefonoOrganizacion = contactoOrganizacion.getTelefono();

		if (organizacion.getLogotipo() != null)
			logotipoAImage = organizacion.getLogotipoAImage();

		if (organizacion.getDevelopmentTool() != null) {
			DevelopmentTool tool = organizacion.getDevelopmentTool();

			try {
				parser = new JsonParser();
				JsonObject jobject = parser.parse(stockUtils.Desencriptar(tool.getValue())).getAsJsonObject();

				emailDevelopment = new Email();
				emailDevelopment.setEmail(suprimirComillas(String.valueOf(jobject.get("user"))));
				emailDevelopment.setTipo(suprimirComillas(String.valueOf(jobject.get("pass"))));
				emailDevelopment.setContacto(suprimirComillas(String.valueOf(jobject.get("servidor"))));
				emailDevelopment.setWeb(suprimirComillas(String.valueOf(jobject.get("puerto"))));

				if (emailDevelopment.getContacto() != null && emailDevelopment.getContacto().equals("null"))
					emailDevelopment.setContacto("");
				if (emailDevelopment.getWeb() != null && emailDevelopment.getWeb().equals("null"))
					emailDevelopment.setWeb("");

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			emailDevelopment = new Email();

	}

	@Command
	@NotifyChange({ "municipios" })
	public void getMunicipiosSelectedEstadoItem() {
		municipios = (List<Municipio>) municipioRest.getByEstado(usuario.getPersona().getDireccion().getEstado()).getSingle();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "direccionUsuario", "paisUsuario", "estadoUsuario", "municipioUsuario",
			"habilitarPaisEstadoMunicipio" })
	public void getInformacionDireccionFromCPUsuario(@BindingParam("compUserCp") Component comp) {
		if (direccionUsuario.getCp() != null && !direccionUsuario.getCp().equals("")) {
			datosGlobalesJSON = inicializarConexionJsonUrl(direccionUsuario.getCp());
			direccionJSon = new Direccion();
			contadorCamposCodigoPostal = 0;

			dumpCodigoPostalJSONElement(datosGlobalesJSON);

			if (contadorCamposCodigoPostal == 4) {
				if (direccionJSon.getColonias().size() > 1) {
					Map<String, Object> inputParams = new HashMap();
					inputParams.put("itemFinder", "updateCodigoPostalSeleccionadoUsuario");
					inputParams.put("direccionConstruida", direccionJSon);
					inputParams.put("componente", comp);

					Window cpModalView = stockUtils.createModelDialogWithParams(
							"/modulos/productos/utils/seleccionarCodigoPostal.zul", inputParams);
					cpModalView.doModal();
				} else {
					direccionUsuario.setCuidad(direccionJSon.getMunicipio().getNombre());
					direccionUsuario.setColonia(direccionJSon.getColonias().get(0));
					direccionUsuario.setEstado(direccionJSon.getEstado());
					direccionUsuario.setMunicipio(direccionJSon.getMunicipio());

					paisUsuario = iteratorList.getPaisFromList(paises, 157L);
					estadoUsuario = direccionJSon.getEstado();
					municipioUsuario = direccionJSon.getMunicipio();
					habilitarPaisEstadoMunicipio = true;
				}
			} else {
				habilitarPaisEstadoMunicipio = false;
				StockUtils.showSuccessmessage("Codigo postal no encontrado", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
			}
		} else {
			StockUtils.showSuccessmessage("Ingrese un codigo postal", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	@GlobalCommand
	@NotifyChange({ "direccionUsuario", "paisUsuario", "estadoUsuario", "municipioUsuario",
			"habilitarPaisEstadoMunicipio" })
	public void updateCodigoPostalSeleccionadoUsuario(@BindingParam("direccionReturn") Direccion itemRecibido,
			@BindingParam("componenteReturn") Component comp) {

		if (itemRecibido != null) {
			direccionUsuario.setCuidad(itemRecibido.getMunicipio().getNombre());
			direccionUsuario.setColonia(itemRecibido.getColonia());
			direccionUsuario.setEstado(itemRecibido.getEstado());
			direccionUsuario.setMunicipio(itemRecibido.getMunicipio());

			paisUsuario = iteratorList.getPaisFromList(paises, 157L);
			estadoUsuario = itemRecibido.getEstado();
			municipioUsuario = itemRecibido.getMunicipio();
			habilitarPaisEstadoMunicipio = true;

			StockUtils.showSuccessmessage("Direccion del codigo postal encontrado", Clients.NOTIFICATION_TYPE_INFO, 0,
					comp);
		}
	}

	@Command
	@NotifyChange({ "direccionOrganizacion", "paisOrganizacion", "estadoOrganizacion", "municipioOrganizacion" })
	public void getInformacionDireccionFromCPOrganizacion(@BindingParam("compOrgCp") Component comp) {

		if (direccionOrganizacion.getCp() != null && !direccionOrganizacion.getCp().equals("")) {
			datosGlobalesJSON = inicializarConexionJsonUrl(direccionOrganizacion.getCp());
			direccionJSon = new Direccion();
			contadorCamposCodigoPostal = 0;

			dumpCodigoPostalJSONElement(datosGlobalesJSON);

			if (contadorCamposCodigoPostal == 4) {

				if (direccionJSon.getColonias().size() > 1) {
					Map<String, Object> inputParams = new HashMap();
					inputParams.put("itemFinder", "updateCodigoPostalSeleccionadoOrganizacion");
					inputParams.put("direccionConstruida", direccionJSon);
					inputParams.put("componente", comp);

					Window productoModalView = stockUtils.createModelDialogWithParams(
							"/modulos/productos/utils/seleccionarCodigoPostal.zul", inputParams);
					productoModalView.doModal();
				} else {
					direccionOrganizacion.setCuidad(direccionJSon.getMunicipio().getNombre());
					direccionOrganizacion.setColonia(direccionJSon.getColonias().get(0));
					direccionOrganizacion.setEstado(direccionJSon.getEstado());
					direccionOrganizacion.setMunicipio(direccionJSon.getMunicipio());

					paisOrganizacion = iteratorList.getPaisFromList(paises, 157L);
					estadoOrganizacion = direccionJSon.getEstado();
					municipioOrganizacion = direccionJSon.getMunicipio();
				}
			} else
				StockUtils.showSuccessmessage("Codigo postal no encontrado", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			StockUtils.showSuccessmessage("Ingrese un codigo postal", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@GlobalCommand
	@NotifyChange({ "direccionOrganizacion", "paisOrganizacion", "estadoOrganizacion", "municipioOrganizacion" })
	public void updateCodigoPostalSeleccionadoOrganizacion(@BindingParam("direccionReturn") Direccion itemRecibido,
			@BindingParam("componenteReturn") Component comp) {

		if (itemRecibido != null) {
			direccionOrganizacion.setCuidad(direccionJSon.getMunicipio().getNombre());
			direccionOrganizacion.setColonia(itemRecibido.getColonia());
			direccionOrganizacion.setEstado(direccionJSon.getEstado());
			direccionOrganizacion.setMunicipio(direccionJSon.getMunicipio());

			paisOrganizacion = iteratorList.getPaisFromList(paises, 157L);
			estadoOrganizacion = direccionJSon.getEstado();
			municipioOrganizacion = direccionJSon.getMunicipio();

			StockUtils.showSuccessmessage("Direccion del codigo postal encontrado", Clients.NOTIFICATION_TYPE_INFO, 0,
					comp);

		}
	}

	@Command
	@NotifyChange("logotipoAImage")
	public void uploadLogo(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
			@BindingParam("compOrgCp") Component comp) {

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			// int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				logotipoAImage = (AImage) media;
				usuario.getOrganizacion().setLogotipo(logotipoAImage.getByteData());
				usuario.setOrganizacion((Organizacion) organizacionRest.save(usuario.getOrganizacion()).getSingle());
				StockUtils.showSuccessmessage("la imagen se ha cargado con exito", Clients.NOTIFICATION_TYPE_INFO, 0,
						comp);
				// }
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@Command
	@NotifyChange("typePassword")
	public void changeTypePassword() {
		if (verPassword) {
			typePassword = "text";
		} else
			typePassword = "password";
	}

	@Command
	@NotifyChange({ "organizacion", "usuario" })
	public void guardarCambios(@BindingParam("compSaveData") Component comp) {

		String mensaje = validarFormularioUsuario();

		if (mensaje.equals("")) {

			if (mensaje.equals("")) {
				String continuar = "";
				if (verOrganizacion) {

					/*
					 * if (developmentConstruction == null)
					 * developmentConstruction = new DevelopmentTool();
					 * developmentConstruction =
					 * crearCorreoDeServicioEmail(emailDevelopment,
					 * developmentConstruction); if
					 * (developmentConstruction.getDescripcion() == null ||
					 * developmentConstruction.getDescripcion().equals("")) {
					 * continuar = validarEnvioDeCorreo(developmentConstruction,
					 * emailDevelopment); if (continuar.equals("")) {
					 * 
					 * } } else continuar =
					 * developmentConstruction.getDescripcion();
					 */
				}

				if (continuar.equals("")) {
					emailUsuario = (Email) emailRest.save(emailUsuario).getSingle();
					telefonoUsuario = (Telefono) telefonoRest.save(telefonoUsuario).getSingle();

					contactoUsuario.setEmail(emailUsuario);
					contactoUsuario.setTelefono(telefonoUsuario);
					contactoUsuario = (Contacto) contactoRest.save(contactoUsuario).getSingle();

					direccionUsuario.setPais(paisUsuario);
					direccionUsuario.setEstado(estadoUsuario);
					direccionUsuario.setMunicipio(municipioUsuario);
					direccionUsuario = (Direccion) direccionRest.save(direccionUsuario).getSingle();

					personaUsuario.setContacto(contactoUsuario);
					personaUsuario.setDireccion(direccionUsuario);
					personaUsuario = (Persona) personaRest.save(personaUsuario).getSingle();

					usuario.setPersona(personaUsuario);
					usuario = (Usuarios) usuarioRest.save(usuario).getSingle();
//					ConexionManual con = new ConexionManual();
//					con.saveOrUpdateUsuario(usuario);
//					con.cerrar();
					// usuarioService.save(usuario);

					// ------------------------------------------------
					if (verOrganizacion) {
						emailOrganizacion = (Email) emailRest.save(emailOrganizacion).getSingle();
						telefonoOrganizacion = (Telefono) telefonoRest.save(telefonoOrganizacion).getSingle();

						contactoOrganizacion.setEmail(emailOrganizacion);
						contactoOrganizacion.setTelefono(telefonoOrganizacion);
						contactoOrganizacion = (Contacto) contactoRest.save(contactoOrganizacion).getSingle();

						direccionOrganizacion.setPais(paisOrganizacion);
						direccionOrganizacion.setEstado(estadoOrganizacion);
						direccionOrganizacion.setMunicipio(municipioOrganizacion);

						if (direccionOrganizacion.getEstado() != null
								&& direccionOrganizacion.getEstado().getIdEstado() == null)
							direccionOrganizacion.setEstado(null);
						if (direccionOrganizacion.getMunicipio() != null
								&& direccionOrganizacion.getMunicipio().getIdMunicipio() == null)
							direccionOrganizacion.setMunicipio(null);
						if (direccionOrganizacion.getPais() != null
								&& direccionOrganizacion.getPais().getIdPais() == null)
							direccionOrganizacion.setPais(null);

						direccionOrganizacion = (Direccion) direccionRest.save(direccionOrganizacion).getSingle();

						if (developmentConstruction != null)
							developmentConstruction = (DevelopmentTool) developmentToolRest.save(developmentConstruction).getSingle();

						if (developmentConstruction != null && developmentConstruction.getIdDevelopmentTool() == null)
							organizacion.setDevelopmentTool(null);

						organizacion.setDevelopmentTool(developmentConstruction);

						organizacion.setContacto(contactoOrganizacion);
						organizacion.setDireccion(direccionOrganizacion);
						if (logotipoAImage != null)
							organizacion.setLogotipo(logotipoAImage.getByteData());
						organizacion = (Organizacion) organizacionRest.save(organizacion).getSingle();
					}
					StockUtils.showSuccessmessage("Informaciòn almacenada", Clients.NOTIFICATION_TYPE_INFO, 0, comp);
				} else
					StockUtils.showSuccessmessage(continuar, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
			} else
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		} else
			StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange("bannerAImage")
	public void uploadBanner(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
			@BindingParam("compOrgCp") Component comp) {

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			// int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				/*
				 * if (lengthofImage > 500 * 1024) { Messagebox.show(
				 * "Please Select a Image of size less than 500Kb."); return; }
				 * else {
				 */
				bannerAImage = (AImage) media;
				if (bannerNuevo != null)
					bannerNuevo.setLogotipo(bannerAImage.getByteData());

				// usuario.getOrganizacion().setLogotipo(usuario.getOrganizacion().getLogotipoAImage().getByteData());
				StockUtils.showSuccessmessage("la imagen se ha cargado con exito", Clients.NOTIFICATION_TYPE_INFO, 0,
						comp);
				// }
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "bannerNuevo", "bannerAImage" })
	public void agregarNuevoBanner() {

		bannerNuevo = new Banner();
		bannerNuevo.setOrganizacion(organizacion);
		bannerNuevo.setUsuario(usuario);
		bannerAImage = null;
		/*
		 * if (bannerList == null) { bannerList = new ArrayList(); }
		 * bannerList.add(nueoItem);
		 */
	}

	@SuppressWarnings("unchecked")
	@Command
	@NotifyChange({ "bannerList", "bannerNuevo", "bannerAImage" })
	public void saveBannerList() {
		if (bannerNuevo != null) {
			if (bannerNuevo.getNombre() != null && bannerNuevo.getLogotipo() != null) {
				bannerNuevo.setOrganizacion(organizacion);
				bannerNuevo.setUsuario(usuario);
				bannerNuevo = (Banner) bannerRest.save(bannerNuevo).getSingle();
				StockUtils.showSuccessmessage("El banner " + bannerNuevo.getNombre() + " ha sido agregado",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
				if (bannerList == null)
					bannerList = new ArrayList();
				if (bannerNuevo.getIdBanner() != null)
					bannerList.remove(bannerNuevo);

				bannerList.add(bannerNuevo);
				bannerNuevo = new Banner();
				bannerAImage = null;
			} else {
				StockUtils.showSuccessmessage("Asegurese de ingresar un nombre al Banner y una imagen",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		}
	}

	@Command
	@NotifyChange({ "bannerNuevo", "bannerAImage" })
	public void selectBanner() {
		bannerNuevo = banner;
		bannerAImage = banner.getLogotipoAImage();
	}

	@Command
	public void mostrarEnPantallaLogotipo(@ContextParam(ContextType.TRIGGER_EVENT) CheckEvent event) {
		if (toolImagenLogo != null) {
			if (event.isChecked()) {
				toolImagenLogo.setValue("true");
			} else
				toolImagenLogo.setValue("false");
			toolImagenLogo = (DevelopmentTool) developmentToolRest.save(toolImagenLogo).getSingle();
		} else
			StockUtils.showSuccessmessage("La configuracion de la imagen no fue cargada con exito",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);

	}

	@Command
	@NotifyChange({ "emailDevelopment", "organizacion" })
	public void testConfiguracionCorreo() {

		developmentConstruction = validarCamposCorreo(emailDevelopment, developmentConstruction);
		if (developmentConstruction.getDescripcion().equals("")) {
			String mensaje = validarEnvioDeCorreo(developmentConstruction, emailDevelopment);
			// String mensaje = "";
			if (mensaje.equals("")) {

				developmentConstruction = (DevelopmentTool) developmentToolRest.save(developmentConstruction).getSingle();
				if (organizacion.getDevelopmentTool() == null) {
					organizacion.setDevelopmentTool(developmentConstruction);
					organizacion = (Organizacion) organizacionRest.save(organizacion).getSingle();
				}
				StockUtils.showSuccessmessage("Test OK", Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} else {
				emailDevelopment.setContacto("");
				emailDevelopment.setWeb("");
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_ERROR, 0, null);
			}

		} else
			StockUtils.showSuccessmessage(developmentConstruction.getDescripcion(), Clients.NOTIFICATION_TYPE_WARNING,
					0, null);

	}

	private DevelopmentTool validarCamposCorreo(Email email, DevelopmentTool dev) {

		try {
			DevelopmentTool tool = organizacion.getDevelopmentTool();
			parser = new JsonParser();
			JsonObject jobject = parser.parse(stockUtils.Desencriptar(tool.getValue())).getAsJsonObject();
			System.err.println("sfsfsd");// suprimirComillas(String.valueOf(jobject.get("")))
		} catch (Exception e) {
		}

		if (dev == null) {
			dev = organizacion.getDevelopmentTool();
			if (dev == null)
				dev = new DevelopmentTool();
		}

		if ((email.getEmail() != null && !email.getEmail().equals(""))
				&& (email.getTipo() != null && !email.getTipo().equals(""))
				&& (email.getContacto() != null && !email.getContacto().equals(""))
				&& (email.getWeb() != null && !email.getWeb().equals(""))) {
			if (email.getEmail().contains("@")) {
				JsonObject object = crearJsonObjectEmailDevelopment(email);
				dev.setValue(stockUtils.Encriptar(object.toString()));
				dev.setDescripcion("");
				dev.setNombre(StockConstants.SISTEMA_CONFIG_EMAIL);
			} else
				dev.setDescripcion("El correo debe tener un dominio google para el funcionamiento del servicio");
		} else
			dev.setDescripcion("Verifique los campos requeridos para el correo");
		return dev;
	}

	@Command
	@NotifyChange({ "checkPeps", "checkUeps", "checkPromedio" })
	public void checkPromedio() {
		checkPeps = false;
		checkUeps = false;
		checkPromedio = true;
		salvarModalidadCalculo();
	}

	@Command
	@NotifyChange({ "checkPeps", "checkUeps", "checkPromedio" })
	public void checkUeps() {
		checkPeps = false;
		checkUeps = true;
		checkPromedio = false;
		salvarModalidadCalculo();
	}

	@Command
	@NotifyChange({ "checkPeps", "checkUeps", "checkPromedio" })
	public void checkPeps() {
		checkPeps = true;
		checkUeps = false;
		checkPromedio = false;
		salvarModalidadCalculo();
	}

	private void salvarModalidadCalculo() {
		String mensaje = "Modalidad de calculo del costo asignado como";
		if (toolModalidadCalculo == null)
			toolModalidadCalculo = new DevelopmentTool();

		toolModalidadCalculo.setDescripcion(StockConstants.SISTEMA_CONFIG_MODE);

		if (checkPeps) {
			mensaje += " " + StockConstants.SISTEMA_PEPS;
			toolModalidadCalculo.setValue(StockConstants.SISTEMA_PEPS);
		} else if (checkUeps) {
			toolModalidadCalculo.setValue(StockConstants.SISTEMA_UEPS);
			mensaje += " " + StockConstants.SISTEMA_UEPS;
		} else if (checkPromedio) {
			mensaje += " " + StockConstants.SISTEMA_PROMEDIO;
			toolModalidadCalculo.setValue(StockConstants.SISTEMA_PROMEDIO);
		}

		toolModalidadCalculo = (DevelopmentTool) developmentToolRest.save(toolModalidadCalculo).getSingle();

		StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@Command
	public void configuracionActivaProveedor() {
		if (toolACtivacionProveedores == null)
			toolACtivacionProveedores = new DevelopmentTool();

		toolACtivacionProveedores.setNombre(StockConstants.SISTEMA_CONFIG_ACTIVA_PROVEEDOR);

		JsonObject object = new JsonObject();
		object.addProperty("activado", activarProveedorCheck);

		toolACtivacionProveedores.setValue(stockUtils.Encriptar(object.toString()));
		toolACtivacionProveedores = (DevelopmentTool) developmentToolRest.save(toolACtivacionProveedores).getSingle();

		String mensaje = "Acceso a proveedores Activado";
		if (!activarProveedorCheck)
			mensaje = "Acceso a proveedores Desactivado";
		StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@Command
	public void salvarMensajeEncabezadoLogin() {
		if (toolMensajeEncabezado == null)
			toolMensajeEncabezado = new DevelopmentTool();

		toolMensajeEncabezado.setNombre(StockConstants.SISTEMA_CONFIG_ENCABEZADO);

		JsonObject object = new JsonObject();
		object.addProperty("encabezado", encabezadoLogin);

		toolMensajeEncabezado.setValue(stockUtils.Encriptar(object.toString()));
		toolMensajeEncabezado = (DevelopmentTool) developmentToolRest.save(toolMensajeEncabezado).getSingle();

		String mensaje = "Encabezado Actualizado";
		StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@SuppressWarnings("unchecked")
	@Command
	public void removerBanner(@BindingParam("index") final Integer index) {
		banner = bannerList.get(index);
		showWindowConfirmationMessage("Eliminar Banner \"" + banner.getNombre() + "\"",
				"¿Desea continuar con la eliminacion del banner \"" + banner.getNombre() + "\"?.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 0);
	}

	@GlobalCommand
	@NotifyChange({ "bannerList", "banner" })
	public void acceptButtonWindowInformation(@BindingParam("accept") boolean ok,
			@BindingParam("object") Object value) {
		if (ok){
			bannerList.remove(banner);
			bannerRest.delete(banner);
			banner = null;
		}
			
	}

	@Command
	@NotifyChange({ "versionSistema" })
	public void updateVersionSistema(){
		if(versionSistema != null && !versionSistema.isEmpty()){
			if (toolVersionSistema == null)
				toolVersionSistema = new DevelopmentTool();

			toolVersionSistema.setNombre(StockConstants.SISTEMA_CONFIG_VERSION);

			JsonObject object = new JsonObject();
			object.addProperty(StockConstants.SISTEMA_CONFIG_VERSION, versionSistema);

			toolVersionSistema.setValue(stockUtils.Encriptar(object.toString()));
			toolVersionSistema = (DevelopmentTool) developmentToolRest.save(toolVersionSistema).getSingle();

			StockUtils.showSuccessmessage("Version del sistem actualizado a " + versionSistema,
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
		}else
			StockUtils.showSuccessmessage("Ingrese una version para el sistema",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		
	}
}
