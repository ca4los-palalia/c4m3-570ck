package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class AreaController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_AREA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(Area itemObject) {

		// itemReturn = createResponseToArea(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_AREA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(Area itemObject) {

		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_AREA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("idFinder", idFinder);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToArea(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_AREA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organinzacion) {

		// itemReturn = createResponseToListArea(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_AREA_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByNombre(String nombre, Organizacion organizacion)
		// {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("nombre", nombre);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToArea(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_AREA_BY_ORGANIZACION })
	@Produces("application/json")
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByOrganizacion(Organizacion organizacion) {

		// itemReturn = createResponseToListArea(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_AREA_UPDATE_FROM_CONFFYA })
	@Produces("application/json")
	public String updateAreaFromConffya(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService updateAreaFromConffya(Organizacion organizacion,
		// Usuarios usuario, String xml) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("usuario", usuario);
		// tranferParam.put("organizacion", organizacion);
		// tranferParam.put("xml", xml);

		// itemReturn.setSingle(response);
		return "";
	}

//	@Transactional(readOnly = true)
//	//public void updateAreaFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_unidades_responsables_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
