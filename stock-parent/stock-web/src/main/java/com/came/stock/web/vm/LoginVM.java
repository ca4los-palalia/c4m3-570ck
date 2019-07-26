package com.came.stock.web.vm;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.beans.SistemaOperativo;
import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.ProveedorUsuario;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

@VariableResolver({ DelegatingVariableResolver.class })
public class LoginVM extends BasicStructure {
	private static final long serialVersionUID = -2184499179368861931L;

	private SistemaOperativo so = new SistemaOperativo();

	private AImage logotipoAImage;

	public LoginVM() {
		super.init();
		Usuarios user = (Usuarios) usuarioRest.getOwner().getSingle();
		if (user.getIdUsuario() != null) {
			try {
				footerNameOrg = user.getOrganizacion().getNombre();
				footerEmail = user.getOrganizacion().getContacto().getEmail().getEmail();
				footerUrlOrg = user.getOrganizacion().getContacto().getEmail().getWeb();
				footerTemefonoOrg = user.getOrganizacion().getContacto().getTelefono().getNumero();
				logotipoAImage = new AImage("logoByte", user.getOrganizacion().getLogotipo());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		DevelopmentTool cfg = (DevelopmentTool)developmentToolRest.getByNombre(StockConstants.SISTEMA_CONFIG_ACTIVA_PROVEEDOR).getSingle();
		if(cfg.getIdDevelopmentTool() != null) {
			try {
				JsonParser parser = new JsonParser();
				JsonObject jobject = parser.parse(stockUtils.Desencriptar(cfg.getValue())).getAsJsonObject();
				activarProveedorCheck = new Boolean(jobject.get("activado").toString());
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		cfg = (DevelopmentTool)developmentToolRest.getByNombre(StockConstants.SISTEMA_CONFIG_ENCABEZADO).getSingle();
		if(cfg.getIdDevelopmentTool() != null) {
			try {
				JsonParser parser = new JsonParser();
				JsonObject jobject = parser.parse(new StockUtils().Desencriptar(cfg.getValue())).getAsJsonObject();
				encabezadoLogin = stockUtilString.extraerMensajeSinComillas(jobject.get("encabezado").toString());
			} catch (JsonSyntaxException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		leerVariablesProperties();
		getStyles();

	}

	private void getStyles() {

		styleLoginFooter = "background: linear-gradient(to bottom, " + StockConstants.COLOR_LOGIN_START + " 0%,"
				+ StockConstants.COLOR_LOGIN_END + " 100%); color: " + StockConstants.COLOR_LOGIN_FONT
				+ "; display: table-cell; vertical-align: middle;";
		styleLoginHeader = "background: linear-gradient(to bottom, " + StockConstants.COLOR_LOGIN_START + " 0%,"
				+ StockConstants.COLOR_LOGIN_END + " 100%); color: " + StockConstants.COLOR_LOGIN_FONT
				+ "; display: table-cell; vertical-align: middle;";
		styleLoginContenedor = "padding-top: 8%; padding-bottom: 8%; padding-left: 40%; padding-right: 40%;";
	}

	@NotifyChange({ "user", "password" })
	@Command
	public void authenticateUser() {
		try {
			Usuarios usuario = (Usuarios) usuarioRest.getUsuarioByCredentials(stockUtils.Encriptar(user), stockUtils.Encriptar(password)).getSingle();
			if (usuario == null) {
				StockUtils.showSuccessmessage(GENERICMESSAGE_CREDENCIALES_INCORRECTAS, "error", 0, null);
				
				user = "";
				password = "";
				return;
			}

			if (usuario.getOrganizacion().getActivar() == true) {
				sessionUtils.addToSession("usuario", usuario);
				sessionUtils.addToSession("FIRMA", usuario.getOrganizacion());

				stockUtils.redirect("/home.zul");

				System.err.println(so.getDirectorioDeInicioDelUsuario() + "Slash: " + this.so.getSeparadorDeArchivos());
				File folder = new File(StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				folder = new File(StockConstants.CARPETA_ARCHIVOS_LOGOTIPOS);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				folder = new File(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				folder = new File(StockConstants.CARPETA_ARCHIVOS_PRODUCTOS);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				folder = new File(StockConstants.CARPETA_ARCHIVOS_PROVEEDORES);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				folder = new File(StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				folder = new File(StockConstants.CARPETA_ARCHIVOS_USUARIOS);
				if (!folder.exists()) {
					folder.mkdirs();
				}
				folder = new File(StockConstants.LAYOUT);
				if (!folder.exists()) {
					folder.mkdirs();
				}

				List<Organizacion> org = (List<Organizacion>) organizacionRest.getAll().getSingle();
				if ((org == null) || (org.size() == 0))
					cargarLayouts();
			} else {
				StockUtils.showSuccessmessage(GENERICMESSAGE_SERVICIO_NO_ACTIVADO, "error", 0, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarLayouts() {
		System.err.println("Precargando Layouts");
		Execution exec = Executions.getCurrent();
		List<String> layoutsXls = new ArrayList<>();
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_AREA);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_BANCO);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_GIRO);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_DIVISA);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_POSICION);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_PRODUCTO);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_PRODUCTO_TIPO);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_PROVEEDOR);
		layoutsXls.add(StockConstants.LAYOUT_EXCEL_UNIDAD);

		try {
			for (String path : layoutsXls) {
				byte[] array = Files.readAllBytes(new File(exec.toAbsoluteURI(generarUrlString(path), false)).toPath());
				DevelopmentTool tool = new DevelopmentTool();
				tool.setValue(path.substring(7));
				tool.setValueByte(array);
				developmentToolRest.save(tool);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void autenticarProveedor(@BindingParam("compAcces") Component comp) {
		if ((proveedorUser != null && !proveedorUser.equals(""))
				&& (proveedorContrasena != null && !proveedorContrasena.equals(""))) {
			
			ProveedorUsuario item = (ProveedorUsuario) proveedorUsuarioRest.getAcceso(stockUtils.Encriptar(proveedorUser),
					stockUtils.Encriptar(proveedorContrasena)).getSingle();
			if (item.getIdProveedorUsuario() != null) {
				sessionUtils.addToSession("usuario", item.getUsuarios());
				sessionUtils.addToSession("FIRMA", item.getUsuarios().getOrganizacion());
				stockUtils.redirect("/zuhause.zul");
			} else
				StockUtils.showSuccessmessage(GENERICMESSAGE_NO_EXISTE_USUARIO, Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			StockUtils.showSuccessmessage(GENERICMESSAGE_INGRESAR_VALOR_REGISTRO, Clients.NOTIFICATION_TYPE_ERROR, 0,
					comp);
	}

	public AImage getLogotipoAImage() {
		return logotipoAImage;
	}

	@Command
	public void registrarProveedor() {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateRecordFromRequisitionWithSelectedItem");

		Window modalViewer = this.stockUtils
				.createModelDialogWithParams("/modulos/productos/utils/registrarProveedor.zul", inputParams);

		modalViewer.doModal();
	}

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		TAG_LOGINVM_ESCRIBENOS = propiedades.getProperty("loginVM.escr");
		TAG_LOGINVM_USUARIO = propiedades.getProperty("loginVM.usu");
		TAG_LOGINVM_CONTRA = propiedades.getProperty("loginVM.contra");
		TAG_LOGINVM_ACCE = propiedades.getProperty("loginVM.acce");
		TAG_LOGINVM_AUTENT = propiedades.getProperty("loginVM.autent");
		TAG_LOGINVM_PROVEEDORES = propiedades.getProperty("menuVM.subTitle4.itemProveedores");
		GENERICMESSAGE_NO_EXISTE_USUARIO = propiedades.getProperty("genericMessage.no.existe.usuario");
		GENERICMESSAGE_INGRESAR_VALOR_REGISTRO = propiedades.getProperty("genericMessage.ingresar.valor.registro");
		GENERICMESSAGE_SERVICIO_NO_ACTIVADO = propiedades.getProperty("genericMessage.servicio.no.activado");
		GENERICMESSAGE_CREDENCIALES_INCORRECTAS = propiedades.getProperty("genericMessage.credenciales.incorrectas");
	}

	@Command
	public void openUrl() {
		if (!footerUrlOrg.contains("http://www.") || !!footerUrlOrg.contains("https://www.")) {
			footerUrlOrg = "http://www." + footerUrlOrg;
		}

		Executions.getCurrent().sendRedirect(footerUrlOrg, "_blank");
	}
}
