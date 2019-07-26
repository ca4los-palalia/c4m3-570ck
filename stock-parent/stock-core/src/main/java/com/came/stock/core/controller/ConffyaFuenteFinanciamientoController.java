package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ConffyaFuenteFinanciamientoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CONFFYA_FUENTE_FINANCIAMIENTO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(ConffyaFuenteFinanciamiento itemObject) {
		// itemReturn = createResponseToConffyaFuenteFinanciamiento(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_FUENTE_FINANCIAMIENTO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(ConffyaFuenteFinanciamiento itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_FUENTE_FINANCIAMIENTO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToConffyaFuenteFinanciamiento(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_FUENTE_FINANCIAMIENTO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organinzacion) {
		// itemReturn = createResponseToListConffyaFuenteFinanciamiento(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_FUENTE_FINANCIAMIENTO_BY_ORGANIZACION })
	@Produces("application/json")
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByOrganizacion(Organizacion organizacion) {

		// itemReturn = createResponseToListConffyaFuenteFinanciamiento(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_FUENTE_FINANCIAMIENTO_BY_CLAVE_IN })
	@Produces("application/json")
	public String getByClaveIn(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByClaveIn(List<String> listIn, Organizacion
		// organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("clave", listIn);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListConffyaFuenteFinanciamiento(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_FUENTE_FINANCIAMIENTO_UPDATE_FROM_CONFFYA })
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
//	//public void updateFuenteFinanciamientoFromConffya(String xml, Long usuario, Long organizacion) {
//		try {
//			Connection con = getSession().connection();
//			CallableStatement statement = con.prepareCall("EXEC sp_fuente_financiamiento_xml_to_db '" + xml + "', " + organizacion + ", " + usuario + ";");
//			statement.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}
