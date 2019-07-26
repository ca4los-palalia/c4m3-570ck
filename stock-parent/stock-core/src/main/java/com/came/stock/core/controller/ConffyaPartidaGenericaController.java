package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ConffyaPartidaGenericaController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(ConffyaPartidaGenerica itemObject) {
		// itemReturn = createResponseToConffyaPartidaGenerica(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(ConffyaPartidaGenerica itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToConffyaPartidaGenerica(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organinzacion) {
		// itemReturn = createResponseToListConffyaPartidaGenerica(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_BY_NOMBRE })
	@Produces("application/json")
	public String getByNombre(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByNombre(String nombre, Organizacion organizacion)
		// {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("nombre", nombre);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToConffyaPartidaGenerica(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_BY_ORGANIZACION })
	@Produces("application/json")
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByOrganizacion(Organizacion organizacion) {

		// itemReturn = createResponseToListConffyaPartidaGenerica(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_ABSOLUTY_ALL_SQL_NATIVE })
	@Produces("application/json")
	public String getAbsolutyAllSqlNative(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAbsolutyAllSqlNative() {

		// itemReturn = createResponseToListConffyaPartidaGenerica(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_BY_CLAVE_IN })
	@Produces("application/json")
	public String getByClaveIn(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByClaveIn(List<String> listIn, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("clave", listIn);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListConffyaPartidaGenerica(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_ROW_COUNT })
	@Produces("application/json")
	public String getRowCount(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getRowCount() {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PARTIDA_GENERICA_UPDATE_FROM_CONFFYA })
	@Produces("application/json")
	public String updateFromConffya(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService updateFromConffya(String xml, Long usuario, Long
		// organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("xml", xml);
		// tranferParam.put("idUsuario", usuario);
		// tranferParam.put("idOrganizacion", organizacion);

		// itemReturn.setSingle(response);
		return "";
	}

//	@Transactional(readOnly = true)
//	//public void updatePartidaGenericaFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_partida_generica_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
