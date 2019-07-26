package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class PrivilegioController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_PRIVILEGIOS_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Privilegios itemObject) {

		// itemReturn = createResponseToPrivilegio(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRIVILEGIOS_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//public CtrlRestService delete(Privilegios itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRIVILEGIOS_BY_PRIVILEGIOS_BY_USUARIO })
	@Produces("application/json")
	public String getPrivilegiosByUsuario(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getPrivilegiosByUsuario(Usuarios usuarios) {

		// itemReturn = createResponseToListPrivilegio(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_PRIVILEGIOS_BY_USUARIOS_BY_PRIVILEGIO })
	@Produces("application/json")
	public String getUsuariosByPrivilegio(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getUsuariosByPrivilegio(UserPrivileges privilegio,
		// Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("privilegio", privilegio);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListPrivilegio(response);
		return "";
	}
}
