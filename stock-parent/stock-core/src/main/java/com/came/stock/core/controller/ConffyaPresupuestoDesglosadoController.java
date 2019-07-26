package com.came.stock.core.controller;

import java.text.ParseException;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.came.stock.constantes.ConstPath;
import com.came.stock.core.layout.ServicesCore;

@RestController
public class ConffyaPresupuestoDesglosadoController extends ServicesCore {

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_SAVE })
	@Produces("application/json")
	public String save(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService save(ConffyaPresupuestoDesglosado itemObject) {
		// itemReturn = createResponseToConffyaPresupuestoDesglosado(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_DELETE })
	@Produces("application/json")
	public String delete(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService delete(ConffyaPresupuestoDesglosado itemObject) {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_ID })
	@Produces("application/json")
	public String getById(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getById(Long idFinder) {
		// itemReturn = createResponseToConffyaPresupuestoDesglosado(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_ALL })
	@Produces("application/json")
	public String getAll(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getAll(Organizacion organizacion) {
		// itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_ORGANIZACION })
	@Produces("application/json")
	public String getByOrganizacion(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByOrganizacion(Organizacion organizacion) {

		// itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_UR })
	@Produces("application/json")
	public String getByUr(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getByUr(String ur) {
		// itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_COUNT_ROWS })
	@Produces("application/json")
	public String getCountRows(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getCountRows() {
		// itemReturn.setSingle(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_PARTIDA_GENERICA_FILTRADO })
	@Produces("application/json")
	public String getPartidaGenericaFiltrado(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getPartidaGenericaFiltrado(String ur, String programa,
		// String proyecto,String fuenteFinanciamiento, Organizacion organizacion) {
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("ur", ur);
		// tranferParam.put("programa", programa);
		// tranferParam.put("fuenteFinanciamiento", fuenteFinanciamiento);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_BY_PARTIDA_GENERICA_FILTRADO_CON_PARTIDA_GENERICA })
	@Produces("application/json")
	public String getPartidaGenericaFiltradoConPartidaGenerica(@RequestBody String jsonItem) throws ParseException {
		// public CtrlRestService getPartidaGenericaFiltradoConPartidaGenerica(String
		// ur, String programa,String proyecto, String fuenteFinanciamiento, String
		// partidaGenerica, Organizacion organizacion) {
		
		// HashMap<String, Object> tranferParam = new //HashMap<String, Object>();
		// tranferParam.put("ur", ur);
		// tranferParam.put("programa", programa);
		// tranferParam.put("proyecto", proyecto);
		// tranferParam.put("fuenteFinanciamiento", fuenteFinanciamiento);
		// tranferParam.put("partidaGenerica", partidaGenerica);
		// tranferParam.put("organizacion", organizacion);

		// itemReturn = createResponseToListConffyaPresupuestoDesglosado(response);
		return "";
	}

	@PostMapping({ ConstPath.MAP_CONFFYA_PRESUPUESTO_DESGLOZADO_UPDATE_FROM_CONFFYA })
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
}