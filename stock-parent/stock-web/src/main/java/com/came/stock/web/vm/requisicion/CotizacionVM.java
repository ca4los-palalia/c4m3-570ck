package com.came.stock.web.vm.requisicion;

import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.CotizacionInbox;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.utilidades.UserPrivileges;
import com.came.stock.web.utils.ReportesBuild;
import com.came.stock.web.vm.requisicion.utils.RequisicionVariables;

@VariableResolver({ DelegatingVariableResolver.class })
public class CotizacionVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";
	@Wire
	private Checkbox c1;

	@Init
	public void init() {
		super.init();
		loadCotizacionesInbox();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		getStylesGlobal();
	}

	private void loadCotizacionesInbox() {
		cotizacionesInbox = (List<CotizacionInbox>) cotizacionInboxRest.getAllNews(organizacion).getSingle();
		for (CotizacionInbox ctInbox : cotizacionesInbox) {
			if ((ctInbox.getLeido() != null) && (!ctInbox.getLeido().booleanValue())) {
				ctInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void transferirCotizacionToFormular(@BindingParam("index") Integer index) {
		if (index != null) {
			CotizacionInbox toLoad = (CotizacionInbox) cotizacionesInbox.get(index.intValue());
			if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue())) {
				toLoad.setLeido(Boolean.valueOf(true));
				toLoad = (CotizacionInbox) cotizacionInboxRest.save(toLoad).getSingle();
				toLoad.setIcono("/images/toolbar/openedEmail.png");
			}
			cotizacionesList.clear();
			cotizacionesList.add(toLoad.getCotizacion());

			Cotizacion cotizacion = (Cotizacion) cotizacionRest.getById(toLoad.getCotizacion().getIdCotizacion(), organizacion).getSingle();

			requisicionProductos = (List<RequisicionProducto>) requisicionProductoRest.getByCotizacion(cotizacion, organizacion).getSingle();
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void obtenerListaDeProductosProveedorSeleccionado() {
		if (proveedorSelected != null) {
			requisicionProductos = (List<RequisicionProducto>) requisicionProductoRest.getByProveedor(proveedorSelected, organizacion).getSingle();
		}
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkNueva() {
		checkBuscarNueva = true;

		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones nuevas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarNueva = false;
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkCancelada() {
		checkBuscarCancelada = true;
		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones canceladas",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
		checkBuscarCancelada = false;
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkEnviada() {
		checkBuscarEnviada = true;
		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones enviadas", Clients.NOTIFICATION_TYPE_WARNING,
					0, null);
		}
		checkBuscarEnviada = false;

	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void checkAceptada() {
		checkBuscarAceptada = true;
		cotizacionesList = buscarPCotizacion();
		if (cotizacionesList == null || cotizacionesList.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron cotizaciones Aceptadas", Clients.NOTIFICATION_TYPE_WARNING,
					0, null);
		}
		checkBuscarAceptada = false;
	}

	@Command
	@NotifyChange({ "cotizacionesList" })
	public void buscaraCotizacionCadena() {
		if (requisicion.getBuscarRequisicion() != null && !requisicion.getBuscarRequisicion().equals("")) {
			cotizacionesList = buscarPCotizacion();
			if (cotizacionesList == null || cotizacionesList.size() == 0) {
				StockUtils.showSuccessmessage("No se encontraron cotizaciones con: "
						+ requisicion.getBuscarRequisicion() + ". Intente nuevamente",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else
			StockUtils.showSuccessmessage("Ingrese un criterio de busqueda.", Clients.NOTIFICATION_TYPE_ERROR, 0, null);
	}

	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)) {
			lista = new ArrayList();
			if (checkBuscarNueva) {
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("CON").getSingle());
			}
			if (checkBuscarCancelada) {
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("COC").getSingle());
			}
			if (checkBuscarEnviada) {
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("COE").getSingle());
			}
			if (checkBuscarAceptada) {
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("COA").getSingle());
			}
		}
		return lista;
	}

	private List<Cotizacion> buscarPCotizacion() {
		List<Cotizacion> cotizacionesArreglo = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)
				|| ((requisicion != null) && (requisicion.getBuscarRequisicion() != null)
						&& (!requisicion.getBuscarRequisicion().isEmpty()))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();

			cotizacionesList = (List<Cotizacion>) cotizacionRest.getByEstatusRequisicionAndFolioOrProveedorByFolio(
					requisicion.getBuscarRequisicion(), null, listaEstatus, organizacion).getSingle();

			cotizacionesArreglo = new ArrayList();
			if (cotizacionesList != null) {
				for (Cotizacion cotizacionArreglo : cotizacionesList) {
					boolean agregar = true;
					for (Cotizacion item : cotizacionesArreglo) {
						if (item.getProveedor().getNombre().equals(cotizacionArreglo.getProveedor().getNombre())) {
							agregar = false;
							break;
						}
					}
					if (agregar) {
						cotizacionesArreglo.add(cotizacionArreglo);
					}
				}
				// cotizacionesList.clear();
			}
			if ((cotizacionesList != null) && (requisicionProductos != null)) {
				requisicionProductos.clear();
			}
		} else {
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de cotizaciones (nueva, cancelada, enviada o aceptada) o (ingresar palabra en el buscador)",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
		return cotizacionesArreglo;
	}

	@Command
	@NotifyChange({ "*" })
	public void mostrarProductosCotizacion() {
		if (cotizacionSelected != null) {
			getProductosCotizacion();
		}
	}

	private void getProductosCotizacion() {
		if (cotizacionesConProductos == null) {
			cotizacionesConProductos = new ArrayList();
		}
		cotizacionesConProductos = (List<Cotizacion>) cotizacionRest.getByProveedorFolioCotizacionNueva(
				cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
				cotizacionSelected.getEstatusRequisicion(), organizacion).getSingle();
	}

	private void actualizarRequisicion(String clave){
		EstatusRequisicion estatus = (EstatusRequisicion) estatusRequisicionRest.getByClave(clave).getSingle();
		cotizacionSelected.getRequisicion().setEstatusRequisicion(estatus);
		cotizacionSelected.setRequisicion((Requisicion) requisicionRest.save(cotizacionSelected.getRequisicion()).getSingle());
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "cotizacionSelected" })
	public void enviarCotizacion(@BindingParam("compSendCot") Component comp) {
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave().equals("CON")) {
				
				final FileConstructionComponents fileConstructionComponents = crearArchivoExcel();
				Messagebox.show(
						"¿Quiere descargar una copia de la cotizacion (Archivo xlsx)?",
						"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
							public void onEvent(Event event) throws Exception {
								if (((Integer) event.getData()).intValue() == 1) {									
									Filedownload.save(fileConstructionComponents.getBytes(), "xlsx/png",
											cotizacionSelected.getProveedor().getNombre() + ".xlsx");
								}
							}
						});
				
				CotizacionInbox inbox = new CotizacionInbox();
				inbox.setCotizacion(cotizacionSelected);
				inbox.setLeido(Boolean.valueOf(false));
				inbox.setFechaRegistro(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				inbox = (CotizacionInbox) cotizacionInboxRest.save(inbox).getSingle();
				String mensaje = "";
				// -------------------------------------------------------------------------------
				
				
				if (fileConstructionComponents.getFile() != null) {
					List<String> mensajesEnvioCorreo = null;
					if (organizacion.getDevelopmentTool() != null) {
						List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
						if (privilegios != null && privilegios.size() > 0) {
							mensaje = " ha generado una cotización con folio " + cotizacionSelected.getFolioCotizacion() + " al proveedor " + cotizacionSelected.getProveedor().getNombre();
							mensajesEnvioCorreo = enviarCorreos(usuario, organizacion, privilegios, mensaje, "Solicitud de cotizacion", fileConstructionComponents.getFile());
							mensaje = "";
							if(mensajesEnvioCorreo.size() > 0){
								String encabezado = "";
								String content = "";
								for (String item : mensajesEnvioCorreo) {
									if(item.contains("ERROR")){
										encabezado = "Hubo un error en el envio de correo.";
									}else
										encabezado = "Se ha enviado un correo para su notificación";
									content += item + "\n";
								}
								mensaje += encabezado + "\n" + content;
							}
						}

					} else
						mensaje += "No existen usuarios para notificar via email";
					if (fileConstructionComponents.getFile().exists())
						if (fileConstructionComponents.getFile().delete())
							System.err.println("Archivo temporal eliminado");
				}

				// -------------------------------------------------------------------------------
				StockUtils.showSuccessmessage("La cotizacion con folio [" + cotizacionSelected.getFolioCotizacion()
						+ "] ha sido generada " + mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, comp);
				
			} else {
				StockUtils.showSuccessmessage("La cotizacion con folio [" + cotizacionSelected.getFolioCotizacion()
						+ "] no puede ser reenviada nuevamente", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	public boolean comprobarConexionAInternet() {
		String dirWeb = "www.legajo-virtual.com";
		boolean centinela = false;
		int puerto = 80;
		try {
			Socket s = new Socket(dirWeb, puerto);
			centinela = true;
		} catch (Exception e) {
		}
		return centinela;
	}

	@Command
	@NotifyChange({ "*" })
	public void aceptarCotizacion(@BindingParam("compAceptCot") Component comp) {
		if (cotizacionSelected != null) {
			if (cotizacionSelected.getEstatusRequisicion().getClave().equals("COE")) {
				EstatusRequisicion estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("COA").getSingle();
				cotizacionSelected.setEstatusRequisicion(estado);
				for (int i = 0; i < cotizacionesConProductos.size(); i++) {
					Cotizacion item = (Cotizacion) cotizacionesConProductos.get(i);
					item.setEstatusRequisicion(estado);
					item = (Cotizacion) cotizacionRest.save(item).getSingle();
				}
				OrdenCompra compra = new OrdenCompra();

				estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("OCN").getSingle();

				compra.setEstatusRequisicion(estado);
				compra.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

				compra.setUsuario((Usuarios) sessionUtils.getFromSession("usuario"));

				compra.setCodigo("FOC" + ordenCompraRest.getCodigoDeOrden().getResponse());

				compra.setCotizacion(cotizacionSelected);
				compra.setFechaOrden(Calendar.getInstance());
				compra = (OrdenCompra) ordenCompraRest.save(compra).getSingle();
				
				OrdenCompraInbox inbox = new OrdenCompraInbox();
				inbox.setOrdenCompra(compra);
				inbox.setLeido(Boolean.valueOf(false));
				inbox.setFechaCreacion(new StockUtils().convertirCalendarToDate(Calendar.getInstance()));

				inbox = (OrdenCompraInbox) ordenCompraInboxRest.save(inbox).getSingle();
				
				actualizarRequisicion("RQP");

				String mensaje = "Cotizacion [" + cotizacionSelected.getFolioCotizacion() + "] ha sído aceptada ";
				// --------------------------------------------------------

				if (organizacion.getDevelopmentTool() != null) {
					if (comprobarConexionAInternet()) {
						List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
						if (privilegios != null) {
							enviarCorreos(usuario, organizacion, privilegios, mensaje,
									"Cotización Aceptada " + cotizacionSelected.getProveedor().getNombre(), null);
							mensaje += " y se ha enviado un email para su notificación ";
						}
					} else
						mensaje += ". No es posible enviar email. Compruebe su conexion a internet";
				} else
					mensaje += ". No se pudo enviar un email para la notificación";

				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_INFO, 0, comp);
				// --------------------------------------------------------

			} else {
				StockUtils.showSuccessmessage(
						"La cotizacion con folio [" + cotizacionSelected.getFolioCotizacion()
								+ "] nu puede ser aceptada bajo este estatus ("
								+ cotizacionSelected.getEstatusRequisicion().getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void abrirVentanaCancelacion(@BindingParam("compCancelCot") Component comp) {
		if (cotizacionSelected != null) {
			if ((cotizacionSelected.getEstatusRequisicion().getClave().equals("COE"))
					|| (cotizacionSelected.getEstatusRequisicion().getClave().equals("CON"))) {
				HashMap<String, Object> map = new HashMap();
				map.put("cotizacion", cotizacionSelected);
				map.put("componente", comp);
				Executions.createComponents("/modulos/requisicion/cancelacionCotizacion.zul", null, map);
			} else {
				StockUtils.showSuccessmessage(
						"La cotizaci�n con folio [" + cotizacionSelected.getFolioCotizacion()
								+ "] no puede ser cancelada bajo este estatus ("
								+ cotizacionSelected.getEstatusRequisicion().getNombre() + ")",
						Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	private FileConstructionComponents crearArchivoExcel() {

		FileConstructionComponents componenteFile = null;
		if(libroXssf == null)
			libroXssf = new XSSFWorkbook();
		File file = null;
		//HSSFSheet hoja = libroHssf.createSheet();
		XSSFSheet hoja = libroXssf.createSheet("Productos a cotizar");

		//HSSFRow row = hoja.createRow(0);
		Row row = hoja.createRow(0);
		
		//HSSFCell cell = null;
		Cell cell = null;
		
		//HSSFRichTextString value = null;
		String value = "";
		for (int j = 0; j < 9; j++) {
			switch (j) {
			case 0:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("No");
				value = "No";
				break;
			case 1:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("Clave");
				value = "Clave";
				break;
			case 2:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("Producto");
				value = "Producto";
				break;
			case 3:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("Cantidad");
				value = "Cantidad";
				
				break;
			case 4:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("Modelo");
				value = "Modelo";
				break;
			case 5:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("Marca");
				value = "Marca";
				break;
			case 6:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("Descripcion");
				value = "Descripcion";
				break;
			case 7:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("Precio unitario");
				value = "Precio unitario";
				break;
			case 8:
				cell = row.createCell((short) j);
				//value = new HSSFRichTextString("TOTAL");
				value = "TOTAL";
			}
			cell.setCellValue(value);
		}
		for (int i = 0; i < cotizacionesConProductos.size(); i++) {
			Cotizacion item = (Cotizacion) cotizacionesConProductos.get(i);

			Row fila = hoja.createRow(i + 1);
			Cell celda = null;
			//HSSFRichTextString texto = null;
			String texto = "";
			for (int j = 0; j < 9; j++) {
				switch (j) {
				case 0:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString(String.valueOf(i + 1));
					texto = String.valueOf(i + 1);
					break;
				case 1:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString(item.getProducto().getClave());
					texto = item.getProducto().getClave();
					break;
				case 2:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString(item.getProducto().getNombre());
					texto = item.getProducto().getNombre();
					break;
				case 3:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString(item.getRequisicionProducto().getCantidad().toString());
					texto = item.getRequisicionProducto().getCantidad().toString();
					break;
				case 4:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString("");
					texto = "NULL";
					break;
				case 5:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString("");
					texto = "NULL";
					break;
				case 6:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString("");
					texto = "NULL";
					break;
				case 7:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString(String.valueOf(""));
					texto = String.valueOf("");
					break;
				case 8:
					celda = fila.createCell((short) j);
					//texto = new HSSFRichTextString(String.valueOf(""));
					texto = "NULL";
				}
				celda.setCellValue(texto);
			}
		}
		try {
			//String encryptName = stockUtils.Encriptar(String.valueOf(cotizacionSelected.getIdCotizacion()));
			
			Date fechaActual = new Date();
			DateFormat formatoHora = new SimpleDateFormat("HH.mm.ss");
	        DateFormat formatoFecha = new SimpleDateFormat("yyyy.MM.dd");
	        String encryptName = cotizacionSelected.getProveedor().getNombre() + " (" + formatoFecha.format(fechaActual) + ")[" + formatoHora.format(fechaActual) + "]";
	        
			String hash = StockConstants.CARPETA_ARCHIVOS_COTIZACIONES + encryptName + ".xlsx";
			fileOutputStream = new FileOutputStream(hash);
			libroXssf.write(fileOutputStream);
			file = new File(hash);
			fileOutputStream.close();
			
			componenteFile = new FileConstructionComponents(libroXssf);
			componenteFile.setFile(file);
			componenteFile.setFileOutputStream(fileOutputStream);

			EstatusRequisicion estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("COE").getSingle();

			cotizacionSelected.setEstatusRequisicion(estado);
			cotizacionSelected.setExcelFile(hash);
			for (int i = 0; i < cotizacionesConProductos.size(); i++) {
				Cotizacion item = (Cotizacion) cotizacionesConProductos.get(i);
				item.setEstatusRequisicion(estado);
				item.setExcelFile(hash);
				item = (Cotizacion) cotizacionRest.save(item).getSingle();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return componenteFile;
	}

	// ----------------------------------------------

	public static final String COTIZACION_GLOBALCOMMAND_NAsME_FOR_UPDATE = "itemFinder";

	/*
	 * @SuppressWarnings("rawtypes")
	 * 
	 * @Command public void onUploadFile(@ContextParam(ContextType.BIND_CONTEXT)
	 * BindContext ctx){ Iterator iterator =
	 * getDataExcel(getStreamMediaExcel(ctx), 0); while (iterator.hasNext()) {
	 * Object object = (Object) iterator.next();
	 * 
	 * System.err.println(object);
	 * 
	 * } }
	 * 
	 */

	@Command
	public void openFileChooser(@BindingParam("index") Integer index, @BindingParam("compUserCp") Component comp) {
		cotizacionSelected = ((Cotizacion) cotizacionesList.get(index.intValue()));
		if (index != null) {
			getProductosCotizacion();
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("itemFinder", "updateRecordFromFileWithSelectedItem");
			inputParams.put("cotizacionSelected", cotizacionSelected);
			inputParams.put("listProductosSystem", cotizacionesConProductos);

			Window productoModalView = stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/fileExplorer.zul", inputParams);
			productoModalView.doModal();
		}
	}

	// "requisicionProductos"
	@GlobalCommand
	@NotifyChange({ "cotizacionesList", "cotizacionSelected", "cotizacionesConProductos" })
	public void updateRecordFromFileWithSelectedItem(@BindingParam("fileSelected") List<Producto> productosImportados) {
		if (productosImportados != null) {
			actualizarPreciosProductosLeidos(productosImportados);

			StockUtils.showSuccessmessage(
					"Se han importado " + productosImportados.size()
							+ " Producto(s) cotizados. Clic en aceptar cotizacion para continuar con el proceso",
					Clients.NOTIFICATION_TYPE_INFO, 0, null);

		}
	}

	private void actualizarPreciosProductosLeidos(List<Producto> listaExcel) {
		Integer i = 0;
		if (cotizacionesConProductos == null) {
			getProductosCotizacion();
		}
		
		for (Cotizacion item : cotizacionesConProductos) {
			Producto rowExcel = listaExcel.get(i);
			item.getRequisicionProducto().setCantidad(Float.parseFloat(String.valueOf(rowExcel.getEnExistencia())));
			if (rowExcel.getEnExistencia() != null)
				item.getRequisicionProducto()
						.setTotalProductoPorUnidad(Float.parseFloat(String.valueOf(rowExcel.getEnExistencia())));
			else
				item.getRequisicionProducto().setTotalProductoPorUnidad(0F);
			Producto producto = item.getProducto();
			
			if (rowExcel.getPrecio() != null)
				producto.setPrecio(rowExcel.getPrecio());
			else
				producto.setPrecio(0F);

			
			//******************************
			int indice = 1;
			List<ProductoPrecios> preciosProducto = (List<ProductoPrecios>) productoPreciosRest.getByProductoOrderMostRecentDate(producto, organizacion).getSingle();
			if(preciosProducto != null)
				indice = preciosProducto.size() + 1;
			
			ProductoPrecios nuevoPrecio = new ProductoPrecios();
			nuevoPrecio.setPrecioValue(rowExcel.getPrecio());
			nuevoPrecio.setActualizacion(Calendar.getInstance());
			nuevoPrecio.setPrecioDescripcion(String.valueOf(indice));
			nuevoPrecio = (ProductoPrecios) productoPreciosRest.save(nuevoPrecio).getSingle();
			
			item.setProductoPrecios(nuevoPrecio);
			//******************************
			
			/*
			List<ProductoPrecios> precios = productoPreciosService.getByProductoOrderMostRecentDate(producto);
			if (precios != null) {
				ProductoPrecios precioItem = precios.get(0);
				precioItem.setPrecioValue(rowExcel.getPrecio());
				precioItem.setActualizacion(Calendar.getInstance());
				productoPreciosService.save(precioItem);
			}
			*/
			item.setRequisicionProducto((RequisicionProducto) requisicionProductoRest.save(item.getRequisicionProducto()).getSingle());
			item = (Cotizacion) cotizacionRest.save(item).getSingle();

			i++;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void imprimirCotizacion(@BindingParam("compPrintCot") Component comp) {
		if (cotizacionSelected != null) {
			if ((cotizacionesConProductos != null) && (cotizacionesConProductos.size() > 0)) {
				
				ReportesBuild reporteador = new ReportesBuild();
				
				Map<String, Object> inputParams = new HashMap();
				inputParams.put("executeNameMethod", "closeVisorPdf");
				inputParams.put("titulo", "Documento de Cotizacion " + cotizacionSelected.getFolioCotizacion());
				inputParams.put("fuente", reporteador.genererCotizacionPdf(cotizacionSelected, cotizacionesConProductos, organizacion));
				
				Window windowModalView = stockUtils
						.createModelDialogWithParams("/modulos/utilidades/visorPdf.zul", inputParams);
				windowModalView.doModal();
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una cotizaci�n",
					Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	@Command
	public void abrirPDF(@BindingParam("index") Integer index) {
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = (List<Privilegios>) privilegioRest.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR, organizacion).getSingle();

		return usuarios;
	}

	@Command
	public void notificacionBandeja(@BindingParam("index") Integer index) {
		Map<String, Object> inputParams = new HashMap();
		Window productoModalView = stockUtils.createModelDialogWithParams("/modulos//utilidades/bandeja.zul",
				inputParams);

		productoModalView.doModal();
	}
}
