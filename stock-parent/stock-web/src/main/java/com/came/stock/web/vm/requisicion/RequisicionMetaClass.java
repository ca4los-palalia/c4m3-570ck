package com.came.stock.web.vm.requisicion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.json.XML;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.SimpleListModel;

import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Justificacion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Posicion;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.requisicion.utils.RequisicionVariables;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public abstract class RequisicionMetaClass extends RequisicionVariables {
	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		getStylesGlobal();
	}

	@Command
	@NotifyChange({ "*" })
	public void checkNueva() {
		checkBuscarNueva = true;
		List<Requisicion> tempList = buscadorRequisicion();
		if (tempList == null || tempList.size() == 0)
			StockUtils.showSuccessmessage(GLOBAL_INFO_MESSAGE1 + " " + GLOBAL_INFO_STATUS_NUEVO, "warning",
					Integer.valueOf(0), null);
		checkBuscarNueva = false;
	}

	@Command
	@NotifyChange({ "*" })
	public void checkCancelada() {
		checkBuscarCancelada = true;
		try {
			List<Requisicion> tempList = buscadorRequisicion();
			if (tempList == null || tempList.size() == 0)
				StockUtils.showSuccessmessage(GLOBAL_INFO_MESSAGE1 + " " + GLOBAL_INFO_STATUS_CANCELADA, "warning",
						Integer.valueOf(0), null);
			checkBuscarCancelada = false;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Command
	@NotifyChange({ "*" })
	public void checkAceptada() {
		this.checkBuscarAceptada = true;
		List<Requisicion> tempList = buscadorRequisicion();
		if (tempList == null || tempList.size() == 0)
			StockUtils.showSuccessmessage(GLOBAL_INFO_MESSAGE1 + " " + GLOBAL_INFO_STATUS_ACEPTADO, "warning",
					Integer.valueOf(0), null);
		checkBuscarAceptada = false;
	}
	
	@Command
	@NotifyChange({ "requisiciones" })
	public void buscaRequisicionesPendientes() {
		EstatusRequisicion estatus = (EstatusRequisicion)estatusRequisicionRest.getByClave("RQP").getSingle();
		requisiciones = (List<Requisicion>)requisicionRest.getByEstatusRequisicion(estatus, organizacion).getSingle();
	}

	@Command
	@NotifyChange({ "*" })
	public void buscarPorArea() {
		List<Requisicion> tempList = buscadorRequisicion();
		if (tempList == null || tempList.size() == 0)
			StockUtils.showSuccessmessage(GLOBAL_INFO_MESSAGE1 + " " + areaBuscar.getNombre(),
					"warning", Integer.valueOf(0), null);
		areaBuscar = null;
	}

	@Command
	@NotifyChange({ "*" })
	public void buscarPorCadenaTexto() {
		List<Requisicion> tempList = buscadorRequisicion();
		if (tempList == null || tempList.size() == 0) {
			String cadenaBuscada = "VACIO";
			if (requisicion.getBuscarRequisicion() != null)
				cadenaBuscada = requisicion.getBuscarRequisicion();
			StockUtils.showSuccessmessage(GLOBAL_INFO_MESSAGE1 + " ''" + cadenaBuscada + "''",
					"warning", Integer.valueOf(0), null);
		}

	}

	public List<Requisicion> buscadorRequisicion() {

		if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada)
				|| (this.checkBuscarAceptada)
				|| ((this.requisicion != null) && (((this.requisicion.getBuscarRequisicion() != null)
						&& (!this.requisicion.getBuscarRequisicion().isEmpty()))
						|| ((this.areaBuscar != null) && (this.areaBuscar.getNombre() != null)
								&& (!this.areaBuscar.getNombre().isEmpty()))))) {
			
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
			requisiciones = (List<Requisicion>)requisicionRest.getRequisicionesConListaDeEstatusFolioArea(listaEstatus,
					requisicion.getBuscarRequisicion(), areaBuscar, organizacion).getSingle();
			if (this.requisiciones == null) {
				limpiarFormulario();
			}
		} else {
			/*
			 * StockUtils.showSuccessmessage(
			 * "Debe elegir algun criterio para realizar la busqueda de requisiciones (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)"
			 * , "warning", Integer.valueOf(0), null);
			 */
		}
		return requisiciones;
	}

	@Command
	@NotifyChange({ "*" })
	public void limpiarFormulario() {
		this.requisicionProductos = new ArrayList();
		this.requisicion = new Requisicion();
		loadItemsKeys();
		initDefaultValues();
		readOnly = false;
		requisiciones = new ArrayList();
		requisicion.setFolio("FRQ" + requisicionRest.getUltimoFolio(organizacion).getSingle().toString());
	}

	public void loadItemsKeys() {
		productosTemporalModel = (List<String>) productoRest.getAllKeys().getSingle();
		if (productosTemporalModel != null) {
			productosModel = new SimpleListModel(productosTemporalModel);
		}
	}

	@NotifyChange({ "*" })
	public void initDefaultValues() {
		catalogoPartidaGenericas = (List<ConffyaPartidaGenerica>)conffyaPartidaGenericaRest.getAll(organizacion).getSingle();
		areas = (List<Area>)areaRest.getAll(organizacion).getSingle();

		Usuarios usuarioSesion = (Usuarios) this.sessionUtils.getFromSession("usuario");
		Persona personaCaptura = usuarioSesion.getPersona();
		requisicion.setPersona(personaCaptura);
		if ((areas != null) && (areas.size() > 0)) {
			// requisicion.setArea((Area) areas.get(0));
		}
		if ((cofiaProgs != null) && (cofiaProgs.size() > 0)) {
			requisicion.setCofiaProg((ConffyaProg) cofiaProgs.get(0));
		}
		if ((cofiaPys != null) && (cofiaPys.size() > 0)) {
			requisicion.setCofiaPy((ConffyaPy) cofiaPys.get(0));
		}
		if ((cofiaFuenteFinanciamientos != null) && (cofiaFuenteFinanciamientos.size() > 0)) {
			requisicion.setCofiaFuenteFinanciamiento((ConffyaFuenteFinanciamiento) cofiaFuenteFinanciamientos.get(0));
		}
		if ((posiciones != null) && (posiciones.size() > 0)) {
			requisicion.setPosicion((Posicion) posiciones.get(0));
		}
		addNewItemToBill();
		requisicion.setFolio("FRQ" + requisicionRest.getUltimoFolio(organizacion).getSingle().toString());
	}

	@NotifyChange({ "requisicionProductos", "itemsOnList" })
	@Command
	public void addNewItemToBill() {
		RequisicionProducto objeto = new RequisicionProducto();
		objeto.setCofiaPartidaGenerica(new ConffyaPartidaGenerica());
		if (this.requisicionProductos == null) {
			this.requisicionProductos = new ArrayList();
		}
		this.requisicionProductos.add(objeto);
		this.itemsOnList = Integer.valueOf(this.requisicionProductos.size());
	}

	public List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada)
				|| (this.checkBuscarAceptada)) {
			lista = new ArrayList();
			if (this.checkBuscarNueva) {
				lista.add((EstatusRequisicion)estatusRequisicionRest.getByClave("RQN").getSingle());
			}
			if (this.checkBuscarCancelada) {
				lista.add((EstatusRequisicion)estatusRequisicionRest.getByClave("RQC").getSingle());
			}
			if (this.checkBuscarAceptada) {
				lista.add((EstatusRequisicion)estatusRequisicionRest.getByClave("RQT").getSingle());
			}
		}
		return lista;
	}


	public void cargarJustificacionesWs() {
		Calendar hoy = Calendar.getInstance();
		justificaciones = (List<Justificacion>)justificacionRest.getAll().getSingle();
		
		try {
			if (justificaciones != null) {
				long diferencia = stockUtils.diferenciaEnDias(stockUtils.convertirCalendarToDate(hoy),
						stockUtils.convertirCalendarToDate(justificaciones.get(0).getActualizacion()));
				if (diferencia > 29){
					for (Justificacion item : justificaciones) {
						justificacionRest.delete(item);
					}
					generarJustificaciones(organizacion);
				}
					
			} else
				generarJustificaciones(organizacion);
		} catch (Exception e) {
		}
		justificacionSelected = new Justificacion();
	}

	private int generarJustificaciones(Organizacion orgIn) {
		inicializarServicioWebService();
		String xml = "";
		int count = 0;
		try {
			xml = serviceWs.catalogoJustificacionCompromiso(longToShort(orgIn.getNumero()),
					longToShort(orgIn.getEjercicio()));
			if (!xml.equals(""))
				count = exportarXmlToJustificaciones(xml, orgIn);
		} catch (Exception e) {
		}
		return count;
	}

	public int exportarXmlToJustificaciones(String xmlString, Organizacion orgIn) {
		int count = 0;
		String code = XML.toJSONObject(xmlString).toString();
		JsonElement json = new JsonParser().parse(code);
		justificacionSelected = new Justificacion();
		justificaciones = new ArrayList<>();
		contadorCamposJson = 0;

		dumpJustificacionesJSONElement(json);
		if (justificaciones != null) {
			for (Justificacion item : justificaciones) {
				item.setActualizacion(Calendar.getInstance());
				item = (Justificacion)justificacionRest.save(item).getSingle();
				count++;
			}
		}
		return count;
	}

	public void dumpJustificacionesJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();

				if (keyJSon.equals("elementos")) {
					extraer = true;
				}
				if (extraer)
					justificacionSelected = construirJustificacion(entrada.getKey(), entrada.getValue(),
							justificacionSelected);
				if (contadorCamposJson == 2) {
					// area.setOrganizacion(geto);
					justificaciones.add(justificacionSelected);
					justificacionSelected = new Justificacion();
					contadorCamposJson = 0;
				}

				dumpJustificacionesJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpJustificacionesJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}

	public Justificacion construirJustificacion(String key, JsonElement value, Justificacion justificacionBuiding) {
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		if (extraer) {
			if (key.equals("descripcion")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						justificacionBuiding.setNombre(stringOut);
						contadorCamposJson++;
					}

				}
			}
			if (key.equals("clave")) {
				if (!stringOut.equals("\"\"")) {
					if (!stringOut.equals("")) {
						justificacionBuiding.setClave(stringOut);
						contadorCamposJson++;
					}

				}
			}
		}
		return justificacionBuiding;
	}
}
