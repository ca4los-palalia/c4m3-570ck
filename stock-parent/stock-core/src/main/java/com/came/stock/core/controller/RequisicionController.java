package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;
import com.came.stock.model.domain.Organizacion;

@RestController
public class RequisicionController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_REQUISICION_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService save(Requisicion itemObject) {
		//itemReturn = createResponseToRequisicion(response);
		return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService delete(Requisicion itemObject) {
//		itemReturn.setSingle(response);
		return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getById(Long idRequisicion, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("idRequisicion", idRequisicion);
//		tranferParam.put("organizacion", organizacion);
		
		//itemReturn = createResponseToRequisicion(response);
		return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_BY_PERSONA })
	@Produces("application/json")
	public String getByPersona(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByPersona(Persona persona, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("persona", persona);
//		tranferParam.put("organizacion", organizacion);
		
		//itemReturn = createResponseToRequisicion(response);
				return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_ULTIMO_FOLIO })
	@Produces("application/json")
	public String getUltimoFolio(@RequestBody String jsonItem) throws ParseException {
		Organizacion itemProcess = gson.fromJson(jsonItem, Organizacion.class);
		return requisicionService.getUltimoFolio(itemProcess);
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getAll(Organizacion organizacion) {
		//itemReturn = createResponseToListRequisicion(response);
				return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_BY_ESTATUS_REQUISICION })
	@Produces("application/json")
	public String getByEstatusRequisicion(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByEstatusRequisicion(EstatusRequisicion estatusRequisicion, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("estatusRequisicion", estatusRequisicion);
//		tranferParam.put("organizacion", organizacion);
		
		//itemReturn = createResponseToListRequisicion(response);
				return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_BY_FOLIO })
	@Produces("application/json")
	public String getByFolio(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByFolio(String folio, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("folio", folio);
//		tranferParam.put("organizacion", organizacion);
		
		//itemReturn = createResponseToRequisicion(response);
				return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_BY_UNIDAD_RESPONSABLE })
	@Produces("application/json")
	public String getByUnidadResponsable(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getByUnidadResponsable(Area area, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("area", area);
//		tranferParam.put("organizacion", organizacion);
		
		//itemReturn = createResponseToListRequisicion(response);
				return "";
	}
	
	@PostMapping({ ConstPath.MAP_REQUISICION_REQUISICIONES_CON_LISTA_DE_ESTATUS_FOLIO_AREA })
	@Produces("application/json")
	public String getRequisicionesConListaDeEstatusFolioArea(@RequestBody String jsonItem) throws ParseException {
//	public CtrlRestService getRequisicionesConListaDeEstatusFolioArea(List<EstatusRequisicion> estatusRequisiciones, String folio, Area area, Organizacion organizacion) {
//		HashMap<String, Object> tranferParam = new HashMap<String, Object>();
//		tranferParam.put("estatusRequisiciones", estatusRequisiciones);
//		tranferParam.put("folio", folio);
//		tranferParam.put("area", area);
//		tranferParam.put("organizacion", organizacion);

		//itemReturn = createResponseToListRequisicion(response);
		return "";
	}
}
