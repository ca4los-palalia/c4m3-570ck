package com.came.stock.web.vm.controlpanel;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CheckEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.beans.TabControlSelected;
import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Banco;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.Giro;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Posicion;
import com.came.stock.model.domain.Presentacion;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.ProductoTipoSubFamilia;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.services.confya.Informacion_Empresa;
import com.came.stock.web.utils.ConexionManual;
import com.came.stock.web.vm.controlpanel.utils.ControlPanelMetaclass;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlPanelVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;
	
	private String contentType;
	private short numeroEmpresa;
	private short ejercicio;

	@Init
	public void init() {
		
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		if (usuario.getOwner())
			visualizarControlesCame = true;
		
		areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
		getStylesGlobal();
		controlBotoArea = new TabControlSelected();
		controlBotoArea.setModulo(true);
		controlBotoArea.setSubmodulo(true);
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		System.err.println(view);
	}
	// ------------ COMANDOS PARA TAB AREA -------------
	@Command
	@NotifyChange({ "areas", "controlBotoArea" })
	public void selectAreaTab() {
		controlBotoArea.setModulo(true);
		controlBotoArea.setSubmodulo(true);
		areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
	}

	@Command
	@NotifyChange({ "almacenesList", "controlBotoArea" })
	public void getALmacenesDeArea() {
		almacenesList = (List<Almacen>) almacenRest.getByArea(area).getSingle();
		if(almacenesList == null)
			agregarNuevoAlmacen();
		
		controlBotoArea.setModulo(true);
		controlBotoArea.setSubmodulo(false);
	}

	@Command
	@NotifyChange({ "areas", "area", "controlBotoArea" })
	public void saveArea(@BindingParam("compSaveArea") Component comp) {
		if(area != null && !area.getNombre().isEmpty()){
			if (areas == null)
				areas = new ArrayList();
			
			boolean addToArray = false;
			String mensaje = "";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/dd/M");
			String date = sdf.format(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
			area.setFechaActualizacion(date);
			if(area.getIdArea() == null){
				area.setOrganizacion(organizacion);
				area.setUsuario(usuario);
				addToArray = true;
				mensaje = area.getNombre() + " ha sido creado";
			}else
				mensaje = area.getNombre() + " ha sido actualizado";
			
			area = (Area) areaRest.save(area).getSingle();
			
			controlBotoArea.setModulo(true);
			controlBotoArea.setSubmodulo(true);
			
			if(addToArray)
				areas.add(area);
			StockUtils.showSuccessmessage(mensaje, "info", 0, comp);
		}else
			StockUtils.showSuccessmessage("Seleccione una area o cree un nuevo catalogo para guardar cambios",
				Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}
	
	@Command
	@NotifyChange({ "almacenesList", "almacenSelected" })
	public void salvarAlmacenes() {
		if (area != null) {
			
			if (almacenSelected != null) {
				if (validarCamposALmacenes()) {
					if(almacenSelected.getIdAlmacen() == null){
						almacenSelected.setArea(area);
						almacenSelected.setOrganizacion(organizacion);
						almacenesList.add(almacenSelected);
					}
					almacenSelected.setDireccion((Direccion) direccionRest.save(almacenSelected.getDireccion()).getSingle());
					
					almacenSelected = (Almacen)almacenRest.save(almacenSelected).getSingle();
					StockUtils.showSuccessmessage(
							"Se han almacenado " + almacenesList.size() + " Almacenes para " + area.getNombre(),
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No hay almacenes que salvar", Clients.NOTIFICATION_TYPE_WARNING, 0,
						null);
		} else
			StockUtils.showSuccessmessage("Debe seleccionar un área primero", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
	}

	@Command
	@NotifyChange({ "areas" })
	public void eliminarAreaIndex(@BindingParam("index") Integer index) {
		area = ((Area) areas.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (areas != null) {
			try {
				area = (Area) areaRest.delete(area).getSingle();
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				areas.clear();
				areas = (List<Area>) areaRest.getAll(organizacion).getSingle();

				StockUtils.showSuccessmessage("El �rea -" + area.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Área -" + area.getNombre() + "- esta siendo utilizado. No puede ser eliminado", "error",
						Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un área para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "area", "controlBotoArea" })
	public void agregarNuevaArea() {
		area = crearColumnaVaciaAreaEstandar();
		StockUtils.showSuccessmessage("Ingrese el nombre del area", Clients.NOTIFICATION_TYPE_INFO, 0,
				compTxtAreaNueva);
		
		controlBotoArea.setModulo(false);
		controlBotoArea.setSubmodulo(true);
	}

	@NotifyChange({ "areas", "almacenesList" })
	@Command
	public void onUploadExcelArea(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		//------------------------
		List datos = getDataExcel(getStreamMediaExcel(ctx), 0);
		if(datos != null)
			areas = leerDatosDesdeExcelArea(datos);
		//------------------------
		
		//areas = leerDatosDesdeExcelArea(getDataExcel(getStreamMediaExcel(ctx), 0));
		if (datos != null && areas.size() > 0) {
			for (Area item : areas) {
				item = (Area) areaRest.save(item).getSingle();
			}
			
			//-----------------
			datos = getDataExcel(getStreamMediaExcel(ctx), 1);
			if(datos != null)
			almacenesList = leerDatosDesdeExcelAlmacenes(datos);
			//----------------
			//almacenesList = leerDatosDesdeExcelAlmacenes(getDataExcel(getStreamMediaExcel(ctx), 1));
			
			
			if (almacenesList.size() > 0) {
				for (Almacen item : almacenesList) {
					item.setOrganizacion(organizacion);
					item = (Almacen) almacenRest.save(item).getSingle();
				}
			}
			Messagebox.show(areas.size() + " Areas Importadas con sus respectivos almacenes");
		} else
			Messagebox.show("No se importaron areas. El documento esta vacio");
	}

	@Command
	@NotifyChange({ "almacenesList" })
	public void agregarNuevoAlmacen() {
		if (area != null && area.getIdArea() != null) {
			if (almacenesList == null)
				almacenesList = new ArrayList<>();

			almacenSelected = crearColumnaVaciaAlmacenEstandar();
			//almacenesList.add(crearColumnaVaciaAlmacenEstandar());
			
			StockUtils.showSuccessmessage("Ingrese el nombre del Almacen", Clients.NOTIFICATION_TYPE_INFO, 0,
					compTxtAlmacenNombre);

		} else
			StockUtils.showSuccessmessage("Debe seleccionar un área primero", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);

	}

	@Command
	@NotifyChange({ "almacenesList" })
	public void getInformacionDireccionFromCPAlmacen() {
		if(almacenSelected != null){
			
			if (almacenSelected.getDireccion().getCp() != null && !almacenSelected.getDireccion().getCp().equals("")) {
				if (municipios == null || municipios.size() == 0)
					municipios = (List<Municipio>) municipioRest.getAll().getSingle();
				if (estados == null || estados.size() == 0)
					estados = (List<Estado>) estadoRest.getAll().getSingle();

				datosGlobalesJSON = inicializarConexionJsonUrl(almacenSelected.getDireccion().getCp());
				direccionJSon = new Direccion();
				contadorCamposCodigoPostal = 0;

				dumpCodigoPostalJSONElement(datosGlobalesJSON);

				if (contadorCamposCodigoPostal == 4) {
					if (direccionJSon.getColonias().size() > 1) {
						Map<String, Object> inputParams = new HashMap();
						inputParams.put("itemFinder", "updateCodigoPostalSeleccionado");
						inputParams.put("direccionConstruida", direccionJSon);
						inputParams.put("componente", null);

						Window cpModalView = stockUtils.createModelDialogWithParams(
								"/modulos/productos/utils/seleccionarCodigoPostal.zul", inputParams);
						cpModalView.doModal();
					}else{
						almacenSelected.getDireccion().setCuidad(direccionJSon.getMunicipio().getNombre());
						almacenSelected.getDireccion().setColonia(direccionJSon.getColonias().get(0));
						almacenSelected.getDireccion().setEstado(direccionJSon.getEstado());
						almacenSelected.getDireccion().setMunicipio(direccionJSon.getMunicipio());

						paisUsuario = iteratorList.getPaisFromList(paises, 157L);
						estadoUsuario = direccionJSon.getEstado();
						municipioUsuario = direccionJSon.getMunicipio();
						StockUtils.showSuccessmessage("Codigo Aceptado:" + almacenSelected.getDireccion().getMunicipio().getNombre()
								+ ", " + almacenSelected.getDireccion().getEstado().getNombre(), Clients.NOTIFICATION_TYPE_INFO, 0,
								compTxtAlmacenCp);
					}
				} else
					StockUtils.showSuccessmessage("Codigo postal no encontrado", Clients.NOTIFICATION_TYPE_WARNING, 0,
							compTxtAlmacenCp);
			} else
				StockUtils.showSuccessmessage("Ingrese un codigo postal", Clients.NOTIFICATION_TYPE_WARNING, 0, compTxtAlmacenCp);
			
		}else
			StockUtils.showSuccessmessage("Clic en el boton nuevo Almacen y posteriormente capture la informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, compBtnAddAlmacen);
	}
	
	@GlobalCommand
	@NotifyChange({ "almacenesList", "almacenSelected"})
	public void updateCodigoPostalSeleccionado(@BindingParam("direccionReturn") Direccion itemRecibido,
			@BindingParam("componenteReturn") Component comp) {

		if (itemRecibido != null) {
			almacenSelected.getDireccion().setCuidad(itemRecibido.getMunicipio().getNombre());
			almacenSelected.getDireccion().setColonia(itemRecibido.getColonia());
			almacenSelected.getDireccion().setEstado(itemRecibido.getEstado());
			almacenSelected.getDireccion().setMunicipio(itemRecibido.getMunicipio());

			paisUsuario = iteratorList.getPaisFromList(paises, 157L);
			estadoUsuario = itemRecibido.getEstado();
			municipioUsuario = itemRecibido.getMunicipio();

			StockUtils.showSuccessmessage("Direccion del codigo postal encontrado", Clients.NOTIFICATION_TYPE_INFO, 0,
					compTxtAlmacenCp);
		}
	}

	// ------------------- FIN COMANDOS PARA TAB AREA
	// -------------------------------

	// --------------- COMANDOS PARA TAB BANCOS ---------------------------
	@Command
	@NotifyChange({ "bancosDB", "bancoSeleccionado" })
	public void selectBancosTab() {
		bancosDB = (List<Banco>) bancoRest.getAll(organizacion).getSingle();
		bancoSeleccionado = crearColumnaVaciaBanco();
		if (bancosDB == null){
			bancosDB = new ArrayList();
			StockUtils.showSuccessmessage("Ingrese el nombre del banco",
					Clients.NOTIFICATION_TYPE_INFO,
					0, compTxtBancoNombre); 
		}
			
	}

	@Command
	@NotifyChange({ "bancosDB" })
	public void saveBancos() {
		guardarBanco();
	}

	@Command
	@NotifyChange({ "bancosDB", "bancoSeleccionado" })
	public void eliminarBanco(@BindingParam("index") Integer index) {
		bancoSeleccionado = ((Banco) bancosDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (bancosDB != null) {
			try {
				bancoRest.delete(bancoSeleccionado);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				bancosDB.clear();
				bancosDB = (List<Banco>) bancoRest.getAll(organizacion).getSingle();

				StockUtils.showSuccessmessage("El Banco -" + bancoSeleccionado.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("El Banco -" + bancoSeleccionado.getNombre()
						+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un banco para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "bancosDB", "bancoSeleccionado" })
	public void agregarNuevoBanco() {
		if (bancosDB == null)
			bancosDB = new ArrayList();
		bancoSeleccionado = crearColumnaVaciaBanco();
		StockUtils.showSuccessmessage("Ingrese el nombre del banco",
				Clients.NOTIFICATION_TYPE_INFO,
				0, compTxtBancoNombre);
		//bancosDB.add(crearColumnaVaciaBanco());
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "bancosDB" })
	public void onUploadExcelBanco(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		bancosDB = leerDatosDesdeExcelBanco(getStreamMediaExcel(ctx), 0);
		if (bancosDB.size() > 0) {
			for (Banco item : bancosDB) {
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				item = (Banco) bancoRest.save(item).getSingle();
			}
			Messagebox.show(bancosDB.size() + " Bancos Importados");
		} else
			Messagebox.show("No se importaron bancos. El documento esta vacio");
	}
	// ------------------- FIN COMANDOS PARA TAB BANCOS
	// -------------------------------

	// ------------------- COMANDOS PARA TAB CONFFYA
	// -------------------------------
	@Command
	@NotifyChange({ "contratos", "conffyaListUpdateLista", "organizaciones", "areas" })
	public void selectConffyaTab() {
		if(visualizarControlesCame){
			organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
			numeroEmpresa = longToShort(organizacion.getNumero());
			ejercicio = longToShort(organizacion.getEjercicio());

			inicializarServicioWebService();

			// test de conexion
			try {
				Informacion_Empresa info = new Informacion_Empresa(organizacion.getSucursalId(), null, numeroEmpresa,
						ejercicio, null, organizacion.getRfc());
				System.out.println("Valida emprealternativasa: " + serviceWs.validarEmpresa(info));
			} catch (Exception e) {
				StockUtils.showSuccessmessage(e.getMessage() + ". Si necesita actualizar catalos intente mas tarde", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
				System.err.println();
			}
			obtenerEmpresasDeWebService(false);
		}else
			areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
	}

	private void obtenerEmpresasDeWebService(boolean record) {
		String xml = "";
		try {
			xml = serviceWs.catalogoEmpresas();
			if (!xml.equals("")) {
				if (record) {
					List<Organizacion> list = exportarXmlToObjectListEmpresas(xml);
					int count = recordOrganizaciones(list);

					if (count > 0) {
						StockUtils.showSuccessmessage("Se importaron " + count + " Empresas",
								Clients.NOTIFICATION_TYPE_INFO, 0, null);
						organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
					} else
						StockUtils.showSuccessmessage("No se encontraron elementos", Clients.NOTIFICATION_TYPE_WARNING,
								0, null);
				} else
					organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();

			} else
				StockUtils.showSuccessmessage("Error de conexion con webservice", Clients.NOTIFICATION_TYPE_ERROR, 0,
						null);
		} catch (Exception e) {
		}
	}

	@Command
	public void validarEmpresa() {
		try {
			Informacion_Empresa info = new Informacion_Empresa(0, null, 1020, 2015, null, "MAB850101541");
			System.out.println("Valida empresa: " + serviceWs.validarEmpresa(info));

		} catch (Exception e) {
			System.err.println("Error al validar empresa");
			e.printStackTrace();
		}
	}


	// ****************************************


	// ---------------------------------
	private int generarUnidadResponsable(Organizacion orgIn) {
		String xml = "";
		int count = 0;
		try {
			xml = serviceWs.catalogoUnidadResponsable(longToShort(orgIn.getNumero()),
					longToShort(orgIn.getEjercicio()));
			if (!xml.equals("")){
				areaRest.updateAreaFromConffya(orgIn, usuario, xml);
				//count = exportarXmlToDBUnidadResponsable(xml, orgIn);
			}
				
		} catch (Exception e) {
		}
		return count;
	}

	private int generarFuenteFinanciamiento(Organizacion orgIn) {
		String xml = "";
		int count = 0;
		try {
			xml = serviceWs.catalogoFuenteFinanciamiento(longToShort(orgIn.getNumero()),
					longToShort(orgIn.getEjercicio()));
			if (!xml.equals("")){
				//conffyaFuenteFinanciamientoService.updateFuenteFinanciamientoFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
				conffyaFuenteFinanciamientoRest.updateFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
			}
				
		} catch (Exception e) {
		}
		return count;
	}

	private int generarPartidaGenerica(Organizacion orgIn) {
		String xml = "";
		int count = 0;
		try {
			xml = serviceWs.catalogoPartidaGenerica(longToShort(orgIn.getNumero()), longToShort(orgIn.getEjercicio()));
			if (!xml.equals("")){
				//conffyaPartidaGenericaService.updatePartidaGenericaFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
				conffyaPartidaGenericaRest.updateFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
				//count = exportarXmlToDBPartidaGenerica(xml, orgIn);
			}
				
		} catch (Exception e) {
		}
		return count;
	}

	private int generarProgramas(Organizacion orgIn) {
		String xml = "";
		int count = 0;
		try {
			xml = serviceWs.catalogoProgramas(longToShort(orgIn.getNumero()), longToShort(orgIn.getEjercicio()));
			if (!xml.equals("")){
				//count = exportarXmlToDBCofiaPrograma(xml, orgIn);
				//conffyaProgService.updatePartidaGenericaFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
				conffyaProgRest.updateFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
			}
				
		} catch (Exception e) {
		}
		return count;
	}

	private int generarProyectos(Organizacion orgIn) {
		String xml = "";
		int count = 0;
		try {
			xml = serviceWs.catalogoProyecto(longToShort(orgIn.getNumero()), longToShort(orgIn.getEjercicio()));
			if (!xml.equals("")){
//				conffyaPyService.updateProyectoFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
				conffyaPyRest.updateFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
			}
				
		} catch (Exception e) {
		}
		return count;
	}

	@SuppressWarnings("static-access")
	private int generarPresupuestoEgresosDeslgosado(Organizacion orgIn) {
		String xml = "";
		int count = 0;
		String[] parts = stockUtils.getFechaActualConHora().split("-");
		try {

			xml = serviceWs.presupuestodeEgresos(longToShort(orgIn.getNumero()), longToShort(orgIn.getEjercicio()),
					longToShort(Long.parseLong(parts[1])));
			escribirTexto(xml);
			if (!xml.equals("")){
				conffyaPresupuestoDesglosadoRest.updateFromConffya(xml, usuario.getIdUsuario(), orgIn.getIdOrganizacion());
				//count = exportarXmlToDBConffyaPresupuestoEgresosDeslgosado(xml, orgIn);
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	// ------------------- FIN COMANDOS PARA TAB CONFFYA
	// -------------------------------

	// ------------------- COMANDOS PARA TAB CONTRATO
	// -------------------------------
	@Command
	@NotifyChange({ "contratos", "contrato" })
	public void selectContratoTab() {
		contratos = (List<Contrato>) contratoRest.getAll(organizacion).getSingle();
		if(contratos == null){
			contratos = new ArrayList<>();
			StockUtils.showSuccessmessage("¡Lista de contratos vacia!",
					Clients.NOTIFICATION_TYPE_INFO, 0, compListContratosCatalogo);
			StockUtils.showSuccessmessage("Ingrese el nombre del contrato para comenzar",
					Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreContrato);
		}
		contrato = crearColumnaVaciaContrato();
	}

	@Command
	@NotifyChange({ "contratos", "contrato" })
	public void saveContratos() {
		guardarContratos();
	}

	@Command
	@NotifyChange({ "contratos" })
	public void eliminarContrato(@BindingParam("index") Integer index) {
		contrato = ((Contrato) contratos.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (contratos != null) {
			try {
				contratoRest.delete(contrato);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				contratos.clear();
				contratos = (List<Contrato>) contratoRest.getAll(organizacion).getSingle();
				StockUtils.showSuccessmessage("El contrato -" + contrato.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Contrato -" + contrato.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
						"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un contrato para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "contratos", "contrato" })
	public void agregarNuevoContrato() {
		if (contratos == null) {
			contratos = new ArrayList();
		}
		contrato = crearColumnaVaciaContrato();
		StockUtils.showSuccessmessage("Ingrese el nombre del contrato",
				Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreContrato);
		
		//contratos.add(crearColumnaVaciaContrato());
	}
	// ------------------- FIN COMANDOS PARA TAB CONTRATO
	// -------------------------------

	// ------------------- COMANDOS PARA TAB MONEDA
	// -------------------------------
	@Command
	@NotifyChange({ "monedasDB", "monedaSeleccionada" })
	public void selectMonedaTab() {
		monedasDB = (List<Moneda>)monedaRest.getAll(organizacion).getSingle();
		if(monedasDB == null){
			monedasDB = new ArrayList<>();
			StockUtils.showSuccessmessage("Catalogo de Divisas vacio", Clients.NOTIFICATION_TYPE_INFO, 0, compListDivisasCatalogo);
			StockUtils.showSuccessmessage("Ingresar nombre de la divisa", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreDivisa);
		}
		monedaSeleccionada = new Moneda();
	}

	@Command
	@NotifyChange({ "monedasDB", "monedaSeleccionada" })
	public void saveMoneda() {
		guardarMoneda();
	}

	@Command
	@NotifyChange({ "monedasDB" })
	public void eliminarMoneda(@BindingParam("index") Integer index) {
		monedaSeleccionada = ((Moneda) monedasDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (monedasDB != null) {
			try {
				monedaRest.delete(monedaSeleccionada);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				monedasDB.clear();
				monedasDB = (List<Moneda>) monedaRest.getAll(organizacion).getSingle();

				StockUtils.showSuccessmessage("La divisa -" + monedaSeleccionada.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils
						.showSuccessmessage(
								"La divisa -" + monedaSeleccionada.getSimbolo() + " " + monedaSeleccionada.getNombre()
										+ "- esta siendo utilizado. No puede ser eliminado",
								"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar una divisa para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "monedaSeleccionada", "monedasDB" })
	public void agregarNuevaDivisa() {
		if (monedasDB == null)
			monedasDB = new ArrayList();
		monedaSeleccionada = crearColumnaVaciaMonedas();
		StockUtils.showSuccessmessage("Ingresar nombre de la divisa", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreDivisa);
		StockUtils.showSuccessmessage("Ingresar simbolo de divisa (Opcional)", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtSimboloDivisa);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "monedasDB" })
	public void onUploadExcelMoneda(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		monedasDB = leerDatosDesdeExcelMoneda(getStreamMediaExcel(ctx), 0);
		if (monedasDB.size() > 0) {
			for (Moneda item : monedasDB) {
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				item = (Moneda) monedaRest.save(item).getSingle();
			}
			Messagebox.show(monedasDB.size() + " Divisas Importadas");
		} else
			Messagebox.show("No se importaron divisas. El documento esta vacio");
	}
	// ------------------- FIN COMANDOS PARA TAB MONEDA
	// -------------------------------

	// ------------------- COMANDOS PARA TAB PUESTO
	// -------------------------------
	@Command
	@NotifyChange({ "posiciones", "posicion" })
	public void selectPuestoTab() {
		posiciones = (List<Posicion>) posicionRest.getAll(organizacion).getSingle();
		if(posiciones == null){
			posiciones = new ArrayList<>();
			StockUtils.showSuccessmessage("Catalogo de Puestos vacio", Clients.NOTIFICATION_TYPE_INFO, 0, compListPuestosCatalogo);
			StockUtils.showSuccessmessage("Ingresar nombre del Puesto", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombrePosicion);
		}
		posicion = new Posicion();
		posicion.setNuevoRegistro(true);
		posicion.setNombre("");
		posicion.setToolTipIndice("Seleccionar una posicion");
		posicion.setToolTipNombre("Clic sobre esta columna para editar nombre");
		posicion.setOrganizacion(organizacion);
		posicion.setUsuario(usuario);
	}

	@Command
	@NotifyChange({ "posiciones", "posicion" })
	public void savePuesto() {
		guardarPuesto();
	}

	@Command
	@NotifyChange({ "posiciones" })
	public void eliminarPuesto(@BindingParam("index") Integer index) {
		posicion = ((Posicion) posiciones.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (posicion != null) {
			try {
				posicionRest.delete(posicion);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				posiciones.clear();
				posiciones = (List<Posicion>) posicionRest.getAll(organizacion).getSingle();
						
				StockUtils.showSuccessmessage(posicion.getNombre() + " ha sido eliminado", "info", Integer.valueOf(0),
						null);
			} else {
				StockUtils.showSuccessmessage(
						"El puesto -" + posicion.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
						"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un puesto para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "posiciones", "posicion" })
	public void agregarNuevoPuesto() {
		if (posiciones == null) {
			posiciones = new ArrayList();
		}
		posicion = new Posicion();
		posicion.setNuevoRegistro(true);
		posicion.setNombre("");
		posicion.setToolTipIndice("Seleccionar una posicion");
		posicion.setToolTipNombre("Clic sobre esta columna para editar nombre");
		posicion.setOrganizacion(organizacion);
		posicion.setUsuario(usuario);
		//posicion = crearColumnaVaciaP();
		StockUtils.showSuccessmessage("Ingresar nombre del Puesto", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombrePosicion);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "posiciones" })
	public void onUploadExcelPosicion(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		posiciones = leerDatosDesdeExcelPosicion(getStreamMediaExcel(ctx), 0);
		if (posiciones.size() > 0) {
			for (Posicion item : posiciones) {
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				item = (Posicion) posicionRest.save(item).getSingle();
			}
			Messagebox.show(posiciones.size() + " Puestos Importados");
		} else
			Messagebox.show("No se importaron posiciones. El documento esta vacio");
	}
	// ------------------- FIN COMANDOS PARA TAB PUESTO
	// -------------------------------

	// ------------------- COMANDOS PARA TAB FAMILIAS
	// -------------------------------
	@Command
	@NotifyChange({ "productoTipoDB", "productoTipoSelected" })
	public void selectTipoProductoTab() {
		productoTipoDB = (List<ProductoTipo>) productoTipoRest.getAll(organizacion);
		if(productoTipoDB == null){
			productoTipoDB = new ArrayList<>();
			StockUtils.showSuccessmessage("Catalogo de Tipo de productos vacio", Clients.NOTIFICATION_TYPE_INFO, 0, compListTipoProductosCatalogo);
			StockUtils.showSuccessmessage("Ingresar nombre del Tipo de producto", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreTipoProductos);
		}
		productoTipoSelected = new ProductoTipo();
		productoTipoSelected.setNuevoRegistro(true);
		productoTipoSelected.setNombre("");
		productoTipoSelected.setToolTipIndice("Seleccionar un tipo de producto");
		productoTipoSelected.setToolTipNombre("Clic sobre esta columna para editar nombre");
		productoTipoSelected.setOrganizacion(organizacion);
		productoTipoSelected.setUsuario(usuario);
		productoTipoSelected.setProductoTipoSubFamiliaSelected(new ProductoTipoSubFamilia());
	}

	@Command
	@NotifyChange({ "productoTipoDB", "productoTipoSelected" })
	public void saveTipoProducto() {
		guardarProductoTipo();
	}

	@Command
	@NotifyChange({ "productoTipoDB" })
	public void eliminarTipoProducto(@BindingParam("index") Integer index) {
		productoTipoSelected = ((ProductoTipo) productoTipoDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (productoTipoDB != null) {
			try {
				productoTipoRest.delete(productoTipoSelected);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				productoTipoDB.clear();
				productoTipoDB = (List<ProductoTipo>) productoTipoRest.getAll(organizacion).getSingle();

				StockUtils.showSuccessmessage(productoTipoSelected.getNombre() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La familia -" + productoTipoSelected.getNombre()
						+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un tipo de producto para proceder con la eliminaci�n",
					"warning", Integer.valueOf(0), null);
		}
	}
	
	@Command
	@NotifyChange({ "productoTipoSelected" })
	public void agregarNuevaSubfamilia(){
		if(productoTipoSelected != null && productoTipoSelected.getIdProductoTipo() != null){
			productoTipoSelected.setProductoTipoSubFamiliaSelected(new ProductoTipoSubFamilia());
			if(productoTipoSelected.getProductoTipoSubFamiliaList() == null 
					|| productoTipoSelected.getProductoTipoSubFamiliaList().size() == 0)
				productoTipoSelected.setProductoTipoSubFamiliaList(new ArrayList<ProductoTipoSubFamilia>());
			
		}else
			StockUtils.showSuccessmessage("Seleccione previamente una familia para poder agregar subfamilias",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}
	
	@Command
	@NotifyChange({ "productoTipoSelected" })
	public void cargarSubfamilias(){
		productoTipoSelected.setProductoTipoSubFamiliaList((List<ProductoTipoSubFamilia>) productoTipoSubFamiliaRest.getByProductoTipo(productoTipoSelected).getSingle());
		if(productoTipoSelected == null || (productoTipoSelected.getProductoTipoSubFamiliaList() == null || productoTipoSelected.getProductoTipoSubFamiliaList().size() == 0) )
			productoTipoSelected.setProductoTipoSubFamiliaSelected(new ProductoTipoSubFamilia());
		productoTipoSelected.setProductoTipoSubFamiliaSelected(new ProductoTipoSubFamilia());
	}
	
	@Command
	@NotifyChange({ "productoTipoSelected" })
	public void saveTipoProductoSubFamilia(){
		if(productoTipoSelected != null && productoTipoSelected.getIdProductoTipo() != null){
			if(productoTipoSelected.getProductoTipoSubFamiliaSelected() != null){
				if(productoTipoSelected.getProductoTipoSubFamiliaSelected().getNombre() != null && !productoTipoSelected.getProductoTipoSubFamiliaSelected().getNombre().isEmpty()){
					productoTipoSelected.getProductoTipoSubFamiliaSelected().setProductoTipo(productoTipoSelected);
					productoTipoSelected.setProductoTipoSubFamiliaSelected((ProductoTipoSubFamilia) productoTipoSubFamiliaRest.save(productoTipoSelected.getProductoTipoSubFamiliaSelected()).getSingle());
					
					
					if(productoTipoSelected.getProductoTipoSubFamiliaList() == null 
							|| productoTipoSelected.getProductoTipoSubFamiliaList().size() == 0)
						productoTipoSelected.setProductoTipoSubFamiliaList(new ArrayList<ProductoTipoSubFamilia>());
					
					String mensaje = "Subfamilia " + productoTipoSelected.getProductoTipoSubFamiliaSelected().getNombre() + " ha sido actualizada en la familia " + productoTipoSelected.getNombre();
					if(!productoTipoSelected.getProductoTipoSubFamiliaList().contains(productoTipoSelected.getProductoTipoSubFamiliaSelected())){
						mensaje = "Subfamilia " + productoTipoSelected.getProductoTipoSubFamiliaSelected().getNombre() + " ha sido agregada a la familia " + productoTipoSelected.getNombre();
						productoTipoSelected.getProductoTipoSubFamiliaList().add(productoTipoSelected.getProductoTipoSubFamiliaSelected());
					}
					StockUtils.showSuccessmessage(mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				}else
					StockUtils.showSuccessmessage("Nombre de subfamilia es requerido",
							Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}else
				StockUtils.showSuccessmessage("Clic previamente en nuevoa subfamilia",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}else
			StockUtils.showSuccessmessage("Seleccione previamente una familia para poder salvar cambios",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "productoTipoDB", "productoTipoSelected" })
	public void agregarNuevoTipoProducto() {
		if (productoTipoDB == null) {
			productoTipoDB = new ArrayList();
		}
		productoTipoSelected = new ProductoTipo();
		productoTipoSelected.setNuevoRegistro(true);
		productoTipoSelected.setNombre("");
		productoTipoSelected.setToolTipIndice("Seleccionar un tipo de producto");
		productoTipoSelected.setToolTipNombre("Clic sobre esta columna para editar nombre");
		productoTipoSelected.setOrganizacion(organizacion);
		productoTipoSelected.setUsuario(usuario);
		productoTipoSelected.setProductoTipoSubFamiliaSelected(new ProductoTipoSubFamilia());
		
		StockUtils.showSuccessmessage("Ingresar nombre del Tipo de producto", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreTipoProductos);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange({ "productoTipoDB" })
	public void onUploadExcelTipoProducto(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productoTipoDB = leerDatosDesdeExcelTipoProductos(getStreamMediaExcel(ctx), 0);
		if (productoTipoDB.size() > 0) {
			for (ProductoTipo item : productoTipoDB) {
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				item = (ProductoTipo) productoTipoRest.save(item).getSingle();
			}
			Messagebox.show(productoTipoDB.size() + " Tipos de producto Importados");
		} else
			Messagebox.show("No se importaron Tipos de producto. El documento esta vacio");
	}
	// ------------------- FIN COMANDOS PARA TAB FAMILIAS
	// -------------------------------

	// ------------------- COMANDOS PARA TAB UNIDADES DE MEDIDA
	// -------------------------------
	@Command
	@NotifyChange({ "unidadesDB", "unidad" })
	public void selectUnidadesMedidaTab() {
		unidadesDB = (List<Unidad>) unidadRest.getAll(organizacion).getSingle();
		if(unidadesDB == null){
			unidadesDB = new ArrayList<>();
			StockUtils.showSuccessmessage("Catalogo de Unidades vacio", Clients.NOTIFICATION_TYPE_INFO, 0, compListUnidadesCatalogo);
			StockUtils.showSuccessmessage("Ingresar nombre de la unidad", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreUnidades);
		}
		unidad = new Unidad();
	}

	@Command
	@NotifyChange({ "unidad" })
	public void selectedUnidadMedidaList(){
		unidad.setDisabled(true);		
	}
	
	@Command
	@NotifyChange({ "unidadesDB", "unidad", "unidadPorPresentacionSelected" })
	public void saveUnidadesMedida() {
		guardarUnidades();
	}

	@Command
	@NotifyChange({ "unidadesDB" })
	public void removerUnidad(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		unidad = ((Unidad) unidadesDB.get(index.intValue()));
		try {
			unidadRest.delete(unidad);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			unidadesDB.remove(unidad);
			StockUtils.showSuccessmessage("La unidad de medida ha sido eliminada", "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"La unidad de medida -" + unidad.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
					"error", Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "unidadesDB", "unidad" })
	public void agregarNuevaUnidad() {
		if (unidadesDB == null) {
			unidadesDB = new ArrayList();
		}
		unidad = new Unidad();
		StockUtils.showSuccessmessage("Ingresar nombre de la unidad", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreUnidades);
		StockUtils.showSuccessmessage("Ingresar abreviatura de la unidad (Opcional)", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtAbreviaturaUnidades);
	}

	@Command
	@NotifyChange({ "unidadesDB" })
	public void onUploadExcelUnidadMedida(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		unidadesDB = leerDatosDesdeExcelUnidadMedida(getStreamMediaExcel(ctx), 0);
		if (unidadesDB.size() > 0) {
			for (Unidad item : unidadesDB) {
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				item = (Unidad) unidadRest.save(item).getSingle();
			}
			Messagebox.show(unidadesDB.size() + " Unidades de medida Importados");
		} else
			Messagebox.show("No se importaron Unidades de medida. El documento esta vacio");
	}
	// ------------------- FIN COMANDOS PARA TAB UNIDADES DE MEDIDA

	//--------------------- COMANDOS PRESENTACION ----------------------------
	@NotifyChange({ "presentaciones", "presentacion" })
	@Command
	public void selectPresentacionTab(){
		presentaciones = (List<Presentacion>) presentacionRest.getAll(organizacion).getSingle();
		if(presentaciones == null)
			presentaciones = new ArrayList<>();
		presentacion = new Presentacion();
	}
	
	@NotifyChange({ "presentaciones", "presentacion" })
	@Command
	public void agregarNuevaPresentacion(){
		if(presentaciones == null)
			presentaciones = new ArrayList<>();
		presentacion = new Presentacion();
	}
	
	@NotifyChange({ "presentaciones" })
	@Command
	public void onUploadExcelPresentacion(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx){
		presentaciones = leerDatosDesdeExcelPresentacion(getStreamMediaExcel(ctx), 0);
		if (presentaciones.size() > 0) {
			for (Presentacion item : presentaciones) {
				item.setOrganizacion(organizacion);
				item = (Presentacion) presentacionRest.save(item).getSingle();
			}
			Messagebox.show(presentaciones.size() + " Presentaciones han sido Importados");
		} else
			Messagebox.show("No se importaron Presentaciones. El documento esta vacio");
	}
	
	@NotifyChange({ "presentaciones" })
	@Command
	public void savePresentacion(){
		if(presentacion != null){
			if(presentacion.getNombre() != null && !presentacion.getNombre().isEmpty()){
				presentacion.setOrganizacion(organizacion);
				presentacion = (Presentacion) presentacionRest.save(presentacion).getSingle();
				
				String mensaje = "Se ha creado una nueva presentacion";
				if(!presentaciones.contains(presentacion)){
					presentaciones.add(presentacion);
					mensaje = "Se ha actualizado la presentacion " + presentacion.getNombre();
				}
				StockUtils.showSuccessmessage(mensaje, "info",
						Integer.valueOf(0), null);
			}else
				StockUtils.showSuccessmessage("Debe ingresar un nombre a la presentacion", "info",
						Integer.valueOf(0), null);
		}else
			StockUtils.showSuccessmessage("Seleccione y cree una nueva presentacion para poder salvar los cambios",
					"info", Integer.valueOf(0), null);
			
	}
	
	@NotifyChange({ "presentaciones" })
	@Command
	public void selectedPresentacionesList(){
		
	}
	//--------------------- FIN COMANDOS PRESENTACION ----------------------------
	
	// ------------------- COMANDOS PARA TAB GIROS
	// -------------------------------
	@Command
	@NotifyChange({ "giros","giro" })
	public void selectGirosTab() {
		giros = (List<Giro>) giroRest.getAll(organizacion);
		if(giros == null){
			giros = new ArrayList<>();
			StockUtils.showSuccessmessage("Catalogo de Giros vacio", Clients.NOTIFICATION_TYPE_INFO, 0, compListGirosCatalogo);
			StockUtils.showSuccessmessage("Ingresar nombre del Giro", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreGiros);
		}
		giro = new Giro();
	}

	@Command
	@NotifyChange({ "giros","giro" })
	public void saveGiros() {
		guardarGiros();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "giros" })
	public void removerGiro(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		giro = ((Giro) giros.get(index.intValue()));
		try {
			giroRest.delete(giro).getSingle();
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			giros.remove(giro);
			giros = new ArrayList();
			giros = (List<Giro>) giroRest.getAll(organizacion).getSingle();
			StockUtils.showSuccessmessage("El giro -" + giro.getNombre() + "- ha sido eliminado del catalogo", "info",
					Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"El giro -" + giro.getNombre() + "- esta siendo utilizado. No puede ser eliminado", "error",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "giros","giro" })
	public void agregarNuevoGiro() {
		if (giros == null) {
			giros = new ArrayList();
		}
		giro = new Giro();
		StockUtils.showSuccessmessage("Ingresar nombre del giro", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreGiros);
	}

	@Command
	@NotifyChange({ "giros" })
	public void onUploadExcelGiro(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		giros = leerDatosDesdeExcelGiros(getStreamMediaExcel(ctx), 0);
		if (giros.size() > 0) {
			for (Giro item : giros) {
				item.setOrganizacion(organizacion);
				item = (Giro) giroRest.save(item).getSingle();
			}
			Messagebox.show(giros.size() + " Giros Importados");
		} else
			Messagebox.show("No se importaron Giros. El documento esta vacio");
	}
	// ------------------- FIN COMANDOS PARA TAB GIROS
	// -------------------------------

	// ------------------- COMANDOS PARA TAB PRODUCTO NATURALEZA
	// -------------------------------
	@Command
	@NotifyChange({ "productosNaturalezas", "productoNaturaleza" })
	public void selectProductoNaturalezaTab() {
		productosNaturalezas = (List<ProductoNaturaleza>) productoNaturalezaRest.getAll().getSingle();
		if(productosNaturalezas == null){
			productosNaturalezas = new ArrayList<>();
			StockUtils.showSuccessmessage("Catalogo de Giros vacio", Clients.NOTIFICATION_TYPE_INFO, 0, compListProductoNaturalezaCatalogo);
			StockUtils.showSuccessmessage("Ingresar nombre del Giro", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreProductoNaturaleza);
		}
		productoNaturaleza = new ProductoNaturaleza();
	}

	@Command
	@NotifyChange({ "productosNaturalezas", "productoNaturaleza" })
	public void saveProductoNatraleza() {
		guardarProductoNaturaleza();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "productosNaturalezas", "productoNaturaleza" })
	public void removerNaturaleza(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		productoNaturaleza = ((ProductoNaturaleza) productosNaturalezas.get(index.intValue()));
		try {
			productoNaturalezaRest.delete(productoNaturaleza);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			productosNaturalezas.remove(productoNaturaleza);
			productosNaturalezas = new ArrayList();
			productosNaturalezas = (List<ProductoNaturaleza>) productoNaturalezaRest.getAll().getSingle();
			StockUtils.showSuccessmessage(
					"La naturaleza producto -" + productoNaturaleza.getNombre() + "- ha sido eliminado del catalogo",
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("La naturaleza producto -" + productoNaturaleza.getNombre()
					+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "productosNaturalezas", "productoNaturaleza" })
	public void agregarNuevaNaturaleza() {
		if (productosNaturalezas == null) {
			productosNaturalezas = new ArrayList();
		}
		productoNaturaleza = new ProductoNaturaleza();
		StockUtils.showSuccessmessage("Ingresar nombre del producto naturaleza", Clients.NOTIFICATION_TYPE_INFO, 0, compTxtNombreProductoNaturaleza);
	}

	@Command
	@NotifyChange({ "productosNaturalezas" })
	public void onUploadExcelNaturaleza(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productosNaturalezas = leerDatosDesdeExcelNaturaleza(getStreamMediaExcel(ctx), 0);
		if (productosNaturalezas.size() > 0) {
			for (ProductoNaturaleza item : productosNaturalezas) {
				item = (ProductoNaturaleza) productoNaturalezaRest.save(item).getSingle();
			}
			Messagebox.show(productosNaturalezas.size() + " Naturalezas de producto Importados");
		} else
			Messagebox.show("No se importaron Naturalezas de producto. El documento esta vacio");
	}
	// ------------------- FIN COMANDOS PARA TAB PRODUCTO NATURALEZA
	// -------------------------------

	// ------------------- COMANDOS PARA TAB LAYOUTS
	// -------------------------------
	@Command
	public void selectLAyoutTab() {
		contentType = "application/vnd.ms-excel";
	}

	@Command
	public void onUploadLayoutArea(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {

			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_AREA);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Areas ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_AREA.substring(7));
					mensaje = "El layout Areas ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutAreas() {

		/*
		 * Map<String, Object> inputParams = new HashMap();
		 * inputParams.put("updateCommandFromItemFinder",
		 * "updateConffyaPartidaGenericaSelected"); Window windowModalView =
		 * stockUtils .createModelDialogWithParams(
		 * "/modulos/productos/utils/validarUsuarioAccion.zul", inputParams);
		 * 
		 * windowModalView.doModal();
		 */

		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_AREA);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Areas", Clients.NOTIFICATION_TYPE_INFO,
							0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Areas",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutBanco(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_BANCO);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Banco ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_BANCO.substring(7));
					mensaje = "El layout Banco ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutBancos() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_BANCO);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Bancos",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Bancos",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutGiro(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_GIRO);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Giros ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_GIRO.substring(7));
					mensaje = "El layout Giros ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutGiros() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_GIRO);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Giros", Clients.NOTIFICATION_TYPE_INFO,
							0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Giros",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutDivisas(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_DIVISA);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Divisas ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_DIVISA.substring(7));
					mensaje = "El layout Divisas ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutMonedas() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_DIVISA);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Divisas",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Monedas",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutPosiciones(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_POSICION);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Posiciones ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_POSICION.substring(7));
					mensaje = "El layout Prosiciones ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutPosiciones() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_POSICION);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Posiciones",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Posiciones",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutProducto(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_PRODUCTO);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Productos ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_PRODUCTO.substring(7));
					mensaje = "El layout Productos ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutProducto() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_PRODUCTO);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Productos",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Productos",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutProductoTipo(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_PRODUCTO_TIPO);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Tipos de producto ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_PRODUCTO_TIPO.substring(7));
					mensaje = "El layout Tipos de producto ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutProductoTipo() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_PRODUCTO_TIPO);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Tipos de Productos",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Tipo de Productos",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutProveedores(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_PROVEEDOR);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Proveedores ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_PROVEEDOR.substring(7));
					mensaje = "El layout Proveedor ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutProveedores() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_PROVEEDOR);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Proveedores",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else {
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Proveedores",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);
				}
			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Command
	public void onUploadLayoutUnidades(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_UNIDAD);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Unidades de medida ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_UNIDAD.substring(7));
					mensaje = "El layout Unidades ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutUnidades() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_UNIDAD);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Unidades de medida",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para Unidades",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);

			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Command
	public void openLayoutPresentaciones() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_PRESENTACION);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Presentaciones",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para presentaciones",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);

			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Command
	public void onUploadLayoutNaturaleza(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		if (validarExtension(ctx, "xlsx")) {
			DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_NATURALEZA);
			try {
				String mensaje = "";
				if (temp != null) {
					temp.setValueByte(getBytesMediaExcel(ctx));
					mensaje = "El layout Naturaleza de producto ha sido actualizado";
				} else {
					temp = new DevelopmentTool();
					temp.setValueByte(getBytesMediaExcel(ctx));
					temp.setValue(StockConstants.LAYOUT_EXCEL_NATURALEZA.substring(7));
					mensaje = "El layout Naturaleza de producto ha sido Asignado";
				}
				temp = (DevelopmentTool) developmentToolRest.save(temp).getSingle();
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Messagebox.show("El archivo no puede ser leido, asegurese de que sea un archivo XLSX.");
		}
	}

	@Command
	public void openLayoutNaturaleza() {
		DevelopmentTool temp = getLayout(StockConstants.LAYOUT_EXCEL_NATURALEZA);
		try {
			if (temp != null) {
				if (temp.getValueByte() != null) {
					Filedownload.save(temp.getValueByte(), contentType, temp.getValue());
					StockUtils.showSuccessmessage("Se ha descargado el layout de Naturaleza de producto",
							Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else
					StockUtils.showSuccessmessage("No ha sido asignado un Layout para naturaleza de producto",
							Clients.NOTIFICATION_TYPE_ERROR, 0, null);

			} else
				StockUtils.showSuccessmessage("No se encuentra disponible este Layout", Clients.NOTIFICATION_TYPE_ERROR,
						0, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DevelopmentTool getLayout(String layoutName) {
		DevelopmentTool temp = new DevelopmentTool();
		temp = (DevelopmentTool) developmentToolRest.getByValue(layoutName.substring(7)).getSingle();
		return temp;
	}
	// ------------------- FIN COMANDOS PARA TAB LAYOUTS
	// -------------------------------

	@GlobalCommand
	public void updateValidacionUSuario(@BindingParam("credenciales") List<String> itemSeleccionado) {
		if (itemSeleccionado != null) {
			if (itemSeleccionado.get(0).equals(usuario.getBenutzer())
					&& itemSeleccionado.get(1).equals(usuario.getKennwort())) {
				System.err.println("OK");
			}

		}
	}
	
	@Command
	public void updateEntidad(){
		showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
				"¿Desea continuar con la actualizacion de la informacion para el catalogo Entidades?."
						+ "\nEl proceso puede demorar algunos minutos.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 0);
	}
	@Command
	public void updateAreas(){
		showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
				"¿Desea continuar con la actualizacion de la informacion para el catalogo Areas (UR)?."
						+ "\nEl proceso puede demorar algunos minutos.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 1);
	}
	@Command
	public void updateFuenteFinanciamiento(){
		showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
				"¿Desea continuar con la actualizacion de la informacion para el catalogo Fuente de financiamiento?."
						+ "\nEl proceso puede demorar algunos minutos.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 2);
	}
	@Command
	public void updatePartidaGenerica(){
		showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
				"¿Desea continuar con la actualizacion de la informacion para el catalogo Partida Generica?."
						+ "\nEl proceso puede demorar algunos minutos.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 3);
	}
	
	@Command
	public void updateProgramas(){
		showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
				"¿Desea continuar con la actualizacion de la informacion para el catalogo Programas?."
						+ "\nEl proceso puede demorar algunos minutos.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 4);
	}
	public void updateProyectos(){
		showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
				"¿Desea continuar con la actualizacion de la informacion para el catalogo Proyectos?."
						+ "\nEl proceso puede demorar algunos minutos.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 5);
	}
	public void updatePresupuesto(){
		showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
				"¿Desea continuar con la actualizacion de la informacion para el catalogo Presupuesto?."
						+ "\nEl proceso puede demorar algunos minutos.",
				"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 6);
	}

	@Command
	@NotifyChange({"organizaciones"})
	public void conffyaEntidad(){
		if (organizaciones == null || organizaciones.size() == 0)
			organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
		else{
			if(visualizarControlesCame){
				if(organizaciones.size() < 2){
					showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
							"¿Desea continuar con la actualizacion de la informacion para el catalogo Entidades?."
									+ "\nEl proceso puede demorar algunos minutos.",
							"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 0);
				}
			}
			
		}
	}
	
	@Command
	@NotifyChange({"areas"})
	public void conffyaAreas(){
		if(visualizarControlesCame){
			if (organizaciones == null || organizaciones.size() == 0)
				organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
			
			areas = new ArrayList<>();
			for (Organizacion itemOrg : organizaciones) {
				List<Area> areastemp = (List<Area>) areaRest.getByOrganizacion(itemOrg).getSingle();
				if (areastemp != null)
					areas.addAll(areastemp);
			}
			if(areas.size() == 0){
				showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
						"¿Desea continuar con la actualizacion de la informacion para el catalogo Areas (UR)?."
								+ "\nEl proceso puede demorar algunos minutos.",
						"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 1);
			}
		}else
			areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
	}
	
	
	@Command
	@NotifyChange({"cofiaFuenteFinanciamientos"})
	public void conffyaFuentes(){
		if(visualizarControlesCame){
			if (organizaciones == null || organizaciones.size() == 0)
				organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
			cofiaFuenteFinanciamientos = new ArrayList<>();
			for (Organizacion itemOrg : organizaciones) {
				List<ConffyaFuenteFinanciamiento> listTemp = (List<ConffyaFuenteFinanciamiento>) conffyaFuenteFinanciamientoRest.getByOrganizacion(itemOrg).getSingle();
				if (listTemp != null)
					cofiaFuenteFinanciamientos.addAll(listTemp);
			}
			if(cofiaFuenteFinanciamientos.size() == 0)
				showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
						"¿Desea continuar con la actualizacion de la informacion para el catalogo Fuentes de financiamiento?."
								+ "\nEl proceso puede demorar algunos minutos.",
						"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 2);
		}else
			cofiaFuenteFinanciamientos = (List<ConffyaFuenteFinanciamiento>) conffyaFuenteFinanciamientoRest.getAll(organizacion).getSingle();
		
		
	}
	
	@Command
	@NotifyChange({"cofiaPartidaGenericas"})
	public void conffyaPartida(){
		if(visualizarControlesCame){
			int rows = (Integer) conffyaPartidaGenericaRest.getRowCount().getSingle();
			if(rows == 0)
				showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
						"¿Desea continuar con la actualizacion de la informacion para el catalogo Partida generica?."
								+ "\nEl proceso puede demorar algunos minutos.",
						"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 3);
			else{
				ConexionManual con = new ConexionManual();
				cofiaPartidaGenericas = con.getAllPartidagenericaByOrg(organizacion, true);
				con.cerrar();
			}
				
				//cofiaPartidaGenericas = conffyaPartidaGenericaService.getAbsolutyAllSqlNative();
		}else{
			ConexionManual con = new ConexionManual();
			cofiaPartidaGenericas = con.getAllPartidagenericaByOrg(organizacion, false);
			con.cerrar();
			//cofiaPartidaGenericas = conffyaPartidaGenericaService.getAll();
		}
	}
	
	@Command
	@NotifyChange({"cofiaProgs"})
	public void conffyaProgramas(){
		if(visualizarControlesCame){
			if (organizaciones == null || organizaciones.size() == 0)
				organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
			cofiaProgs = new ArrayList<>();
			for (Organizacion itemOrg : organizaciones) {
				List<ConffyaProg> listTemp = (List<ConffyaProg>) conffyaProgRest.getByOrganizacion(itemOrg).getSingle();
				if (listTemp != null)
					cofiaProgs.addAll(listTemp);
			}
			if(cofiaProgs.size() == 0)
				showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
						"¿Desea continuar con la actualizacion de la informacion para el catalogo Programas?."
								+ "\nEl proceso puede demorar algunos minutos.",
						"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 4);
		}else
			cofiaProgs = (List<ConffyaProg>) conffyaProgRest.getAll(organizacion).getSingle();
	}
	
	@Command
	@NotifyChange({"cofiaPys"})
	public void conffyaProyectos(){
		if(visualizarControlesCame){
			if (organizaciones == null || organizaciones.size() == 0)
				organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
			cofiaPys = new ArrayList<>();
			for (Organizacion itemOrg : organizaciones) {
				List<ConffyaPy> listTemp = (List<ConffyaPy>) conffyaPyRest.getByOrganizacion(itemOrg).getSingle();
				if (listTemp != null)
					cofiaPys.addAll(listTemp);
			}
			if(cofiaPys.size() == 0)
				showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
						"¿Desea continuar con la actualizacion de la informacion para el catalogo Proyectos?."
								+ "\nEl proceso puede demorar algunos minutos.",
						"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 5);
		}else
			cofiaPys = (List<ConffyaPy>) conffyaPyRest.getAll(organizacion).getSingle();
	}
	
	@Command
	@NotifyChange({"conffyaPresupuestoDesglosadoList"})
	public void conffyaPresupuesto(){
		if(visualizarControlesCame){
			int rows = (Integer) conffyaPresupuestoDesglosadoRest.getCountRows().getSingle();
			if(rows == 0){
				conffyaPresupuestoDesglosadoList = new ArrayList<>();
				for (Organizacion itemOrg : organizaciones) {
					List<ConffyaPresupuestoDesglosado> listTemp = (List<ConffyaPresupuestoDesglosado>) conffyaPresupuestoDesglosadoRest.getByOrganizacion(itemOrg).getSingle();
					if (listTemp != null)
						conffyaPresupuestoDesglosadoList.addAll(listTemp);
				}
				if(conffyaPresupuestoDesglosadoList.size() == 0){
					showWindowConfirmationMessage("Actualizacion informacion desde Conffya ",
							"¿Desea continuar con la actualizacion de la informacion para el catalogo Proyectos?."
									+ "\nEl proceso puede demorar algunos minutos.",
							"acceptButtonWindowInformation", StockConstants.ICON_WIN_CONFIRM, 6);
				}
			}
		}
		
	}
	
	@GlobalCommand
	@NotifyChange({ "organizaciones", "areas", "cofiaFuenteFinanciamientos", "cofiaPartidaGenericas", "cofiaProgs", "cofiaPys", "conffyaPresupuestoDesglosadoList"})
	public void acceptButtonWindowInformation(@BindingParam("accept") boolean ok, @BindingParam("object") Object value) {
		if(ok){
			switch (Integer.parseInt(String.valueOf(value))) {
			case 0:
				obtenerEmpresasDeWebService(true);
				break;
			case 1:
				
				try {
					int count = 0;
					for (Organizacion itemOrg : organizaciones)
						count += generarUnidadResponsable(itemOrg);

					if (count > 0) {
						areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
						StockUtils.showSuccessmessage("Se importaron " + count + " Unidades responsables",
								Clients.NOTIFICATION_TYPE_INFO, 0, null);
					} else
						StockUtils.showSuccessmessage("No se encontraron elementos", Clients.NOTIFICATION_TYPE_WARNING, 0,
								null);
				} catch (Exception e) {
				}
				
				break;
			case 2:
				try {
					int count = 0;
					for (Organizacion itemOrg : organizaciones)
						count += generarFuenteFinanciamiento(itemOrg);

					if (count > 0) {
						cofiaFuenteFinanciamientos = (List<ConffyaFuenteFinanciamiento>) conffyaFuenteFinanciamientoRest.getAll(organizacion).getSingle();
						StockUtils.showSuccessmessage("Se importaron " + count + " Fuentes de financiamiento",
								Clients.NOTIFICATION_TYPE_INFO, 0, null);
					} else
						StockUtils.showSuccessmessage("No se encontraron elementos", Clients.NOTIFICATION_TYPE_WARNING, 0,
								null);
				} catch (Exception e) {
				}
				break;
			case 3:
				try {
					int count = 0;
					for (Organizacion itemOrg : organizaciones)
						count += generarPartidaGenerica(itemOrg);

					if (count > 0) {
						//cofiaPartidaGenericas = conffyaPartidaGenericaService.getAll();
						cofiaPartidaGenericas = (List<ConffyaPartidaGenerica>) conffyaPartidaGenericaRest.getAll(organizacion).getSingle();
						StockUtils.showSuccessmessage("Se importaron " + count + " Partidas genericas",
								Clients.NOTIFICATION_TYPE_INFO, 0, null);
					} else
						StockUtils.showSuccessmessage("No se encontraron elementos", Clients.NOTIFICATION_TYPE_WARNING, 0,
								null);
				} catch (Exception e) {
				}
				break;
			case 4:
				try {
					int count = 0;
					for (Organizacion itemOrg : organizaciones)
						count += generarProgramas(itemOrg);

					if (count > 0) {
						cofiaProgs = (List<ConffyaProg>) conffyaProgRest.getAll(organizacion).getSingle();
						StockUtils.showSuccessmessage("Se importaron " + count + " Programas", Clients.NOTIFICATION_TYPE_INFO,
								0, null);
					} else
						StockUtils.showSuccessmessage("No se encontraron elementos", Clients.NOTIFICATION_TYPE_WARNING, 0,
								null);
				} catch (Exception e) {
				}
				break;
			case 5:
				try {
					int count = 0;
					for (Organizacion itemOrg : organizaciones)
						count += generarProyectos(itemOrg);

					if (count > 0) {
						cofiaPys = (List<ConffyaPy>) conffyaPyRest.getAll(organizacion).getSingle();
						StockUtils.showSuccessmessage("Se importaron " + count + " Proyectos", Clients.NOTIFICATION_TYPE_INFO,
								0, null);
					} else
						StockUtils.showSuccessmessage("No se encontraron elementos", Clients.NOTIFICATION_TYPE_WARNING, 0,
								null);
				} catch (Exception e) {
				}
				break;

			case 6:
				try {
					int count = 0;
					for (Organizacion itemOrg : organizaciones)
						count += generarPresupuestoEgresosDeslgosado(itemOrg);

					if (count > 0) {
						StockUtils.showSuccessmessage("Se importaron " + count + " Presupuestos de egresos desglosados",
								Clients.NOTIFICATION_TYPE_INFO, 0, null);
					} else
						StockUtils.showSuccessmessage("No se encontraron elementos", Clients.NOTIFICATION_TYPE_WARNING, 0,
								null);
				} catch (Exception e) {
				}
				break;
			}
		}
		pasarGarbageCollector();
		
	}
	
	public void pasarGarbageCollector(){
		 
        Runtime garbage = Runtime.getRuntime();
        System.out.println("Memoria libre antes de limpieza:" + garbage.freeMemory());
        garbage.gc();
 
        System.out.println("Memoria libre tras la limpieza:" + garbage.freeMemory());
    }
	

	@Command
	@NotifyChange({ "organizaciones", "organizacionSelected" })
	public void activarEntidad(@BindingParam("index") Integer index, @ContextParam(ContextType.TRIGGER_EVENT) CheckEvent event){
		organizacionSelected = organizaciones.get(index);
		String mensaje = "";
		//Usuarios u = usuarioService.getOwner(organizacionSelected);
		Usuarios u = (Usuarios) usuarioRest.getOwner().getSingle();
		/*
		if(u.getClient() == true){
			*/
			if(event.isChecked()){
				organizacionSelected.setActivar(true);
				organizacionSelected.setChecked(true);
				mensaje = "La entidad " + organizacionSelected.getNombre() + " ha sido activada";
			}else{
				organizacionSelected.setChecked(false);
				organizacionSelected.setActivar(false);
				mensaje = "La entidad " + organizacion.getNombre() + " ha sido Desactivada";
			}
			organizacionSelected = (Organizacion) organizacionRest.save(organizacionSelected).getSingle();
			StockUtils.showSuccessmessage(mensaje,
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
	/*	
	}else{
			showWindowInformationMessage("Activación de Entidad",
					"La entidad " + organizacionSelected.getNombre() + 
							" no puede ser activada o desactivada pues pertenece al propietario",
					"acceptButtonEntidad",
					StockConstants.ICON_WIN_INFORMATION);
		}
		*/
		
	}
	
	@GlobalCommand
	@NotifyChange({ "organizacionSelected", "organizaciones"})
	public void acceptButtonEntidad(@BindingParam("accept") boolean ok) {
		if(ok){
			organizacionSelected.setChecked(false);
		}
	}
	
	
	@Command
	 public void ctrlKeyClick(@org.zkoss.bind.annotation.BindingParam("item") String ctekKeyCode ){
	   int keyCode =Integer.parseInt(ctekKeyCode);
	         String s = "";
	         switch(keyCode){
	             case 65: s = "Ctrl+A";break;
	             case 119: s = "F8";break;
	             case 83:s="Ctrl+S";break;
	             case 68:s="Ctrl+D";break;
	         }
	         Messagebox.show(s+" is pressed", "CtrlKey",
	                 Messagebox.OK, Messagebox.EXCLAMATION);
	  System.out.println("I am clicked");
	 }

	@Command
	public void actualizarTodoConfya(@ContextParam(ContextType.VIEW) Component view, @org.zkoss.bind.annotation.BindingParam("code") String ctrlKeyCode){
		int keyCode = Integer.parseInt(ctrlKeyCode);
	    String ctrlKey = "";
	    switch (keyCode) {
	    case 65:
	        ctrlKey = "CTRL+A";
	        break;
	    case 81:
	        ctrlKey = "CTRL+Q";
	        break;
	    case 82:
	        ctrlKey = "CTRL+R";
	        break;
	    case 83:
	        ctrlKey = "CTRL+S";
	        break;
	    case 68:
	        ctrlKey = "CTRL+D";
	        break;
	    }
		
		
		System.err.println("Inicio: " + String.valueOf(new Date()));
		
		obtenerEmpresasDeWebService(true);
		if (organizaciones == null || organizaciones.size() == 0)
			organizaciones = (List<Organizacion>) organizacionRest.getAll().getSingle();
		
		for (Organizacion itemOrg : organizaciones){
			generarUnidadResponsable(itemOrg);
			generarFuenteFinanciamiento(itemOrg);
			generarPartidaGenerica(itemOrg);
			generarProgramas(itemOrg);
			generarProyectos(itemOrg);
			generarPresupuestoEgresosDeslgosado(itemOrg);
		}
		
		areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
		cofiaFuenteFinanciamientos = (List<ConffyaFuenteFinanciamiento>) conffyaFuenteFinanciamientoRest.getAll(organizacion);
		cofiaPartidaGenericas = (List<ConffyaPartidaGenerica>) conffyaPartidaGenericaRest.getAll(organizacion).getSingle();
		cofiaProgs = (List<ConffyaProg>) conffyaProgRest.getAll(organizacion).getSingle();
		cofiaPys = (List<ConffyaPy>) conffyaPyRest.getAll(organizacion).getSingle();
		
		System.err.println("\nFin: " + String.valueOf(new Date()));
		
		Messagebox.show("se ha actualizado todos los registros conffya\n"
				+ "Inicio: " + String.valueOf(new Date())
				+"\nFin: " + String.valueOf(new Date()));
	}

	

	private void escribirTexto(String cadenAescribir){
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("D:/prueba.txt");
            pw = new PrintWriter(fichero);
            pw.println(cadenAescribir);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}
	}
