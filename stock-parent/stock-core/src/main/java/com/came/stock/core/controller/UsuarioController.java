package com.came.stock.core.controller;

import java.text.ParseException;
import java.util.Map;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstAtributos;
import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;
import com.came.stock.model.domain.Usuarios;

@RestController
public class UsuarioController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_USUARIO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		Usuarios usr = gson.fromJson(jsonItem, Usuarios.class);
		usuarioService.save(usr);
		return gson.toJson(usr);
	}

	@PostMapping({ ConstPath.MAP_USUARIO_DELETE })
	@Produces({ "application/json" })
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(Usuarios itemObject) {

//itemReturn.setSingle(response);

		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_BY_ID })
	@Produces({ "application/json" })
	public String getUsuarioByCredentials(@RequestBody String jsonItem) throws ParseException {
		Map<String, Object> map = jsonToHashMap(jsonItem);
		if (map == null)
			return ConstAtributos.ERROR_EXCEPTION + "Error al parsear JSON a Map";
		Usuarios itemProcess = usuarioService.getUsuarioByCredentials(
				String.valueOf(map.get("usuario")),
				String.valueOf(map.get("password")));
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Usuarios());
	}

	@PostMapping({ ConstPath.MAP_USUARIO_BY_ID })
	@Produces({ "application/json" })
	public String getUsuarioById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUsuarioById(Long idUsuario) {
		// itemReturn = createResponseToUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getUsuariosByOrganizacion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUsuariosByOrganizacion(Organizacion organizacion) {
		// itemReturn = createResponseToListUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_BY_ORGANIZACION_ALL })
	@Produces({ "application/json" })
	public String getUsuariosByOrganizacionAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUsuariosByOrganizacionAll(Organizacion organizacion) {
		// itemReturn = createResponseToListUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_VERIFICAR_NOMBRE_USUARIO })
	@Produces({ "application/json" })
	public String verificarNombreUsuario(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService verificarNombreUsuario(String benutzer, Long idUsuario) {
//			itemReturn.setSingle(new Boolean(response));
//			itemReturn.setOk(true);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_CLIENTE_BY_ORGANIZACION })
	@Produces({ "application/json" })
	public String getClienteByOrganizacion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getClienteByOrganizacion(Organizacion organizacion) {
		// itemReturn = createResponseToUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_OWNER })
	@Produces({ "application/json" })
	public String getOwner(@RequestBody String jsonItem) throws ParseException {
		Usuarios itemProcess = usuarioService.getOwner();
		return itemProcess != null ? gson.toJson(itemProcess) : gson.toJson(new Usuarios());
	}

	@PostMapping({ ConstPath.MAP_USUARIO_ALL })
	@Produces({ "application/json" })
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll() {
		// itemReturn = createResponseToListUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_CLIENTE_ALL })
	@Produces({ "application/json" })
	public String getUsuariosClienteAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUsuariosClienteAll() {
//itemReturn = createResponseToListUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_LIKE_NOMBRE_ORGANIZACION })
	@Produces({ "application/json" })
	public String getUsuariosLikeNombreOrganizacion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUsuariosLikeNombreOrganizacion(String nombreOrganizacion) {
//itemReturn = createResponseToListUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_LIKE_RFC_ORGANIZACION })
	@Produces({ "application/json" })
	public String getUsuariosLikeRfcOrganizacion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUsuariosLikeRfcOrganizacion(String rfcOrganizacion) {
//itemReturn = createResponseToListUsuario(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_USUARIO_LIKE_NUMERO_ORGANIZACION })
	@Produces({ "application/json" })
	public String getUsuariosLikeNumeroOrganizacion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getUsuariosLikeNumeroOrganizacion(Long numeroOrganizacion) {
////itemReturn = createResponseToListUsuario(response);
		return "";
	}
}
