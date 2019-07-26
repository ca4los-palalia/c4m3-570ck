package com.came.stock.core.controller;

import java.text.ParseException;
import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class UnidadController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_UNIDAD_SAVE }) 
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(Unidad itemObject) {
		// itemReturn = createResponseToUnidad(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_UNIDAD_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(Unidad itemObject) {

		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_UNIDAD_BY_ID_AND_ORG })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idUnidad, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idUnidad", idUnidad);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToUnidad(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_UNIDAD_ALL_BY_ORG })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll(Organizacion organizacion) {
		// itemReturn = createResponseToListUnidad(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_UNIDAD_BY_NOMBRE_AND_ORG })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByNombre(String nombre, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("nombreUnidad", nombre);
//		tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToUnidad(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_UNIDAD_BY_ORGANIZACION })
	@Produces("application/json")
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByOrganizacion(Organizacion organizacion) {
		// itemReturn = createResponseToListUnidad(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_UNIDAD_BY_USUARIO })
	@Produces("application/json")
	public String getByUsuario(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByUsuario(Usuarios usuario) {
		// itemReturn = createResponseToListUnidad(response);
		return "";
	}

}
