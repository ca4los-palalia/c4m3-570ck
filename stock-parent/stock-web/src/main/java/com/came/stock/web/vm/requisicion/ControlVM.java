package com.came.stock.web.vm.requisicion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.AlmacenEntrada;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;

@VariableResolver({ DelegatingVariableResolver.class })
public class ControlVM extends ControlMetaclass {
	
	private static final long serialVersionUID = -5173601216372375544L;

	@Wire("#winMain")
	private Window winMain;

	@Wire("#winMain listbox")
	private Listbox listItems;
	
	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
		areas = (List<Area>) areaRest.getAll(organizacion).getSingle();
		area = new Area();

		checkBuscarAceptada = true;
		ordenesCompras = buscarOrdenCompra();//Lista de ordenes de compra
		checkBuscarAceptada = false;
		
		
		almacenEntrada = new AlmacenEntrada();
		almacenesList = (List<Almacen>) almacenRest.getAll(organizacion).getSingle();

		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();
		
		estadoKardex = (EstatusRequisicion) estatusRequisicionRest.getByClave("KXN").getSingle();
		
		kardexProveedorList = (List<KardexProveedor>) kardexProveedorRest.getByEstatus(estadoKardex, organizacion).getSingle();
		
		getStylesGlobal();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	@Command
	@NotifyChange({ "kardexList", "kardexProveedor"})
	public void mostrarProductosOrdenCompra() {
		
		kardexList = (List<Kardex>) kardexRest.getByKardexProveedorEstatus(kardexProveedor, estadoKardex, organizacion).getSingle();
		if(kardexList != null){
			
			for (Kardex kardexItem : kardexList) {
				//Obtener precio del producto
				/*
				List<ProductoPrecios> precios = productoPreciosService.getByProductoOrderMostRecentDate(kardexItem.getProducto());
				if(precios != null)
					kardexItem.getProducto().setPrecio(precios.get(0).getPrecioValue());
				*/
					
				//Asignar Existencia total del producto en existencia
				kardexItem.setExistenciaCantidad(getExistenciaProducto(kardexItem));
				
				//Asignar subtotal (debe)
				if(kardexItem.getPrecioUnitario() != null)
					kardexItem.setDebe(kardexItem.getEntradaCantidad() * kardexItem.getPrecioUnitario());
				else
					kardexItem.setDebe(0f);
				
				
			}
		}
		
		//kardexList.get(0).getProducto().getPrecio()
		
		/*
		cotizacionSelected = ordenCompra.getCotizacion();
		if (cotizacionSelected != null) {
			cotizacionesConProductos = cotizacionService.getByProveedorFolioCotizacionNueva(
					cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
					cotizacionSelected.getEstatusRequisicion());

			numeroProductos = cotizacionesConProductos.size();
			precioTotal = 0.0F;

			kardexList = buildKardexList(cotizacionesConProductos);
		}
		*/
	}
	
	private Integer getExistenciaProducto(Kardex kardexVar){
		List<Kardex> lista = (List<Kardex>) kardexRest.getByProducto(kardexVar.getProducto(), organizacion).getSingle();
		Integer existencia = 0;
		if(lista != null){
			for (Kardex kardex : lista) {
				if(kardex.getEntradaCantidad() != null)
					existencia += kardex.getEntradaCantidad();
				else
					existencia -= kardex.getSalidaCantidad();
			}
		}
		return existencia;
	}

	private List<Kardex> buildKardexList(List<Cotizacion> listOrdenCompra) {
		List<Kardex> tempList = null;
		if (listOrdenCompra != null) {
			tempList = new ArrayList<>();
			for (Cotizacion item : listOrdenCompra) {
				Kardex temObject = new Kardex();
				
				ProductoPrecios  precio = (ProductoPrecios) productoPreciosRest.getByDescripcionAndProducto("precio1", item.getProducto(), organizacion).getSingle();
				
				temObject.setFechaEntrada(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				temObject.setProducto(item.getProducto());
				temObject.setEntradaCantidad(Math.round(item.getRequisicionProducto().getCantidad()));
				
				if(precio != null)
					temObject.setDebe(
							Integer.parseInt(String.valueOf(Math.round(item.getRequisicionProducto().getCantidad())))
									* precio.getPrecioValue());
				
				
				if(temObject.getIcon() == null)
					temObject.setIcon("/images/toolbar/infoxOrange16.png");
				else
					try {
						temObject.setIcon(stockUtils.Desencriptar(temObject.getIcon()));
					} catch (Exception e) {}
				
				tempList.add(temObject);
			}
		}
		return tempList;
	}

	@Command
	@NotifyChange({ "almacenEntrada" })
	public void agreagarAlmacen(@BindingParam("index2") Integer index) {
		almacenEntrada.setActivarCantidad(false);
		cotizacionSelected.setAlmacen(almacenEntrada.getAlmacen());
	}
	
	@Command
	public void transferirOrdenCompraToFormulario(@BindingParam("index") Integer index) {
		if (index != null) {
			OrdenCompraInbox toLoad = (OrdenCompraInbox) ordenesCompraInbox.get(index.intValue());
			if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue())) {
				toLoad.setLeido(Boolean.valueOf(true));
				toLoad = (OrdenCompraInbox) ordenCompraInboxRest.save(toLoad).getSingle();
				toLoad.setIcono("/images/toolbar/openedEmail.png");
			}
		}
	}

	/*
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)) {
			lista = new ArrayList();
			if (checkBuscarNueva) {
				lista.add(estatusRequisicionService.getByClave("OCN"));
			}
			if (checkBuscarCancelada) {
				lista.add(estatusRequisicionService.getByClave("OCC"));
			}
			if (checkBuscarAceptada) {
				lista.add(estatusRequisicionService.getByClave("OCT"));
			}
		}
		return lista;
	}

	@Command
	public void checkNueva() {
		if (!checkBuscarNueva) {
			checkBuscarNueva = true;
		} else {
			checkBuscarNueva = false;
		}
	}

	@Command
	public void checkCancelada() {
		if (!checkBuscarCancelada) {
			checkBuscarCancelada = true;
		} else {
			checkBuscarCancelada = false;
		}
	}

	@Command
	public void checkAceptada() {
		if (!checkBuscarAceptada) {
			checkBuscarAceptada = true;
		} else {
			checkBuscarAceptada = false;
		}
	}

	@Command
	@NotifyChange({ "ordenesCompras" })
	public void fidByString() {
		if (requisicion.getBuscarRequisicion() != null && !requisicion.getBuscarRequisicion().equals("")) {
			ordenesCompras = buscarOrdenCompra();
			if (ordenesCompras == null || ordenesCompras.size() == 0) {
				StockUtils.showSuccessmessage("No se encontraron Ordenes de compra con: "
						+ requisicion.getBuscarRequisicion() + ". Intente nuevamente",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else
			StockUtils.showSuccessmessage("Ingrese un criterio de busqueda.", Clients.NOTIFICATION_TYPE_ERROR, 0, null);

	}

	

	
	
	
	@Command
	@NotifyChange({ "*" })
	public void abrirVentanaCancelacion() {
		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave().equals("OCN")) {
				HashMap<String, Object> map = new HashMap();
				map.put("orden", ordenCompra);
				Executions.createComponents("/modulos/ordenCompra/cancelacionOrdenCompra.zul", null, map);
			} else {
				StockUtils.showSuccessmessage(
						"La cotizacion con folio -" + ordenCompra.getCodigo()
								+ "- nu puede ser cancelada bajo este estatus ("
								+ ordenCompra.getEstatusRequisicion().getNombre() + ")",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning",
					Integer.valueOf(0), null);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void aceptarOrdenCompra() {
		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave().equals("OCN")) {
				EstatusRequisicion estado = estatusRequisicionService.getByClave("OCT");

				ordenCompra.setEstatusRequisicion(estado);
				ordenCompraService.save(ordenCompra);

				List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
				for (Privilegios privilegio : privilegios) {
					if (privilegio.getUsuarios().getPersona().getContacto() != null) {
						mailService.sendMail(privilegio.getUsuarios().getPersona().getContacto().getEmail().getEmail(),
								"1nn3rgy@gmail.com", "Orden de compra aceptada",
								"La cotizacion " + ordenCompra.getCodigo() + " ha sido aceptada");
					}
				}
				StockUtils.showSuccessmessage("La orden de compra [" + ordenCompra.getCodigo() + "] ha sido Aceptada",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"La orden de compra [" + ordenCompra.getCodigo() + "] no puede ser aceptada bajo este estatus ("
								+ ordenCompra.getEstatusRequisicion().getNombre() + ")",
						"warning", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "rawtypes", "rawtypes", "rawtypes" })
	@Command
	public void imprimirOrdenCompra() {
		if (ordenCompra != null) {
			cotizacionSelected = ordenCompra.getCotizacion();
			if ((cotizacionSelected != null) && (cotizacionesConProductos != null)
					&& (cotizacionesConProductos.size() > 0)) {
				@SuppressWarnings("rawtypes")
				Organizacion org = (Organizacion) sessionUtils.getFromSession("FIRMA");

				HashMap mapa = new HashMap();
				// mapa.put("logotipo",
				// stockUtils.getLogotipoDeOrganizacionParaJasper(org.getLogotipo()));

				mapa.put("nombreEmpresa", org.getNombre());
				mapa.put("proveedor", cotizacionSelected.getProveedor().getNombre());

				mapa.put("ur", cotizacionSelected.getRequisicion().getArea().getNombre());

				mapa.put("comentarios", cotizacionSelected.getDetallesExtras());

				mapa.put("ordenCompra", ordenCompra.getCodigo());
				mapa.put("fechaOC", stockUtils.convertirCalendarToString(ordenCompra.getFechaOrden()));

				mapa.put("claveCotizacion", cotizacionSelected.getFolioCotizacion());

				Float total = Float.valueOf(0.0F);
				for (Cotizacion item : cotizacionesConProductos) {
					total = Float.valueOf(total.floatValue()
							+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
				}
				mapa.put("total", String.valueOf(total));

				mapa.put("entregarEn", "");
				mapa.put("dependencia", "");
				mapa.put("tiempoEntrega", "");

				List<HashMap> listaHashsParametros = new ArrayList();
				listaHashsParametros.add(mapa);

				List<AplicacionExterna> aplicaciones = new ArrayList();
				AplicacionExterna aplicacion = new AplicacionExterna();
				aplicacion.setNombre("PDFXCview");
				aplicaciones.add(aplicacion);
				StockUtils.showSuccessmessage(
						generarOrdenCompraJasper(listaHashsParametros, aplicaciones, cotizacionesConProductos), "info",
						Integer.valueOf(0), null);

				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getClave();
				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getNombre();
				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getUnidad().getNombre();

				((Cotizacion) cotizacionesConProductos.get(0)).getProducto().getPrecio();
				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getCofiaPartidaGenerica()
						.getNombre();

				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getCantidad();

				((Cotizacion) cotizacionesConProductos.get(0)).getRequisicionProducto().getTotalProductoPorUnidad();
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning",
					Integer.valueOf(0), null);
		}
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = privilegioService.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);

		return usuarios;
	}
	
	*/

	@Command
	public void showModal(Event e) {
		areas = (List<Area>) areaRest.getAll(organizacion).getSingle();

		Map<String, Object> inputParams = new HashMap();
		inputParams.put("listaKey", cotizacionesConProductos);

		inputParams.put("areasKey", areas);

		Window window = (Window) Executions.createComponents("/modulos/productos/utils/configurarEnvioProductos.zul",
				null, inputParams);

		window.doModal();
	}

	private List<AlmacenEntrada> prepararAlmacenList(List<AlmacenEntrada> lista) {
		for (AlmacenEntrada item2 : lista) {
			item2.setOrdenCompra(kardexProveedor.getOrdenCompra());
			item2.setOrganizacion(organizacion);
			item2.setUsuario(usuario);
			item2.setProducto(kardex.getProducto());
			item2.setProveedor(kardexProveedor.getProveedor());
		}
		kardex.setIcon(stockUtils.Encriptar("/images/toolbar/infoxGreen16.png"));
		kardex.setConf(true);
		return lista;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void configEnvio(@BindingParam("index") Integer index) {
		kardex = kardexList.get(index);
		if (kardex.getEntradaCantidad() != null) {
			kardex.getProducto().setEnExistencia(kardex.getEntradaCantidad());
			kardex.setAlmacenEntradaList((List<AlmacenEntrada>) almacenEntradaRest.getByOrdenCompraProductoProveedor(kardexProveedor.getOrdenCompra(),
					kardex.getProducto(), kardexProveedor.getProveedor(), organizacion).getSingle());

			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandFromItemFinder", "getListaEnvioItemsProductos");
			inputParams.put("kardexInput", kardex);
			inputParams.put("kardexProveedor", kardexProveedor);
			
			Window productoModalView = this.stockUtils.createModelDialogWithParams(
					"/modulos/productos/utils/kardexConfiguracionEnvioProducto.zul", inputParams);
			productoModalView.doModal();
		} else
			StockUtils.showSuccessmessage("debe ingresar cantidad de productos para configurar el envio",
					Clients.NOTIFICATION_TYPE_WARNING, 0, listItems);

	}

	@GlobalCommand
	@NotifyChange({ "kardexList", "kardex" })
	public void getListaEnvioItemsProductos(
			@BindingParam("almacenEntradaList") List<AlmacenEntrada> almacenEntradaList) {
		try {
			almacenEntradaList = prepararAlmacenList(almacenEntradaList);
			for (AlmacenEntrada item2 : almacenEntradaList)
				item2 = (AlmacenEntrada) almacenEntradaRest.save(item2).getSingle();
			kardex = (Kardex) kardexRest.save(kardex).getSingle();
			StockUtils.showSuccessmessage("Los cambios han sido guardados", Clients.NOTIFICATION_TYPE_INFO, 0,
					listItems);
		} catch (Exception e) {
			StockUtils.showSuccessmessage("Ha habido un error al escribir en la base de datos: " + e.getMessage(),
					Clients.NOTIFICATION_TYPE_ERROR, 0, listItems);
		}	
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void search(@BindingParam("index") Integer index) {
		if (index != null) {
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandFromItemFinder", "updateRecordFromSearchSelectedItem");
			Window productoModalView = this.stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/buscarProducto.zul", inputParams);
			productoModalView.doModal();
			kardex = kardexList.get(index);
		}
	}

	@GlobalCommand
	@NotifyChange({ "kardexList", "kardex" })
	public void updateRecordFromSearchSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			kardex.setProducto(productoSeleccionado);
			productoSeleccionado.getNombre();
		}
	}
	
	
	@Command
	public void save(){
		if(kardexList != null && kardexList.size() > 0){
			for (Kardex item : kardexList) {
				item.setUsuario(usuario);
				item.setOrganizacion(organizacion);
				item = (Kardex) kardexRest.save(item).getSingle();
			}
			StockUtils.showSuccessmessage("Los cambios han sido guardados", Clients.NOTIFICATION_TYPE_INFO, 0,
					listItems);
		}
	}

	@NotifyChange({ "kardexList" })
	@Command
	public void calcularDebe(@BindingParam("index") Integer index) {
		Kardex item = kardexList.get(index);
		
		ProductoPrecios precio = (ProductoPrecios) productoPreciosRest.getByDescripcionAndProducto("precio1", item.getProducto(), organizacion).getSingle();
		
		if(precio != null){
			if (item.getEntradaCantidad() != null
					&& (item.getProducto() != null && precio.getPrecioValue() != null)) {

				int i = 0;
				for (Kardex loop : kardexList) {
					if (i == index) {
						Float multi = item.getEntradaCantidad() * precio.getPrecioValue();
						loop.setDebe(multi);
						loop.setSaldo(multi);
						break;
					}
					i++;
				}
			}
		}
		
	}

	@NotifyChange({ "kardexList" })
	@Command
	public void agregarItem() {
		if (kardexList == null)
			kardexList = new ArrayList<>();

		if (hayItemsVacios())
			kardexList.add(crearKardexVacio());
		else
			StockUtils.showSuccessmessage("Aun hay elementos en blanco. No se puede agregar nuevo item",
					Clients.NOTIFICATION_TYPE_WARNING, 0, listItems);
	}

	private boolean hayItemsVacios() {
		boolean exit = true;
		if (kardexList != null && kardexList.size() > 0) {
			for (Kardex item : kardexList) {
				if (item.getProducto() == null) {
					exit = false;
					break;
				} else {
					if (item.getProducto().getNombre() == null) {
						exit = false;
						break;
					} else {
						if (item.getProducto().getNombre().equals("")) {
							exit = false;
							break;
						}
					}
				}
			}
		}
		return exit;
	}

	private Kardex crearKardexVacio() {
		Kardex item = new Kardex();
		item.setFechaEntrada(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
		item.setBotonBuscarProducto(true);
		return item;
	}

	private List<OrdenCompra> buscarOrdenCompra() {
		List<OrdenCompra> listaExtraer = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)
				|| ((requisicion != null) && (requisicion.getBuscarRequisicion() != null)
						&& (!requisicion.getBuscarRequisicion().isEmpty()))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
			listaExtraer = (List<OrdenCompra>) ordenCompraRest.getByEstatusAndFolioOr(requisicion.getBuscarRequisicion(),
					listaEstatus, organizacion).getSingle();
			if (cotizacionesList == null) {
			}
		} else {
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de ordenes de compra (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)",
					"warning", Integer.valueOf(0), null);
		}
		return listaExtraer;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada) || (checkBuscarAceptada)) {
			lista = new ArrayList();
			if (checkBuscarNueva) {
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("OCN").getSingle());
			}
			if (checkBuscarCancelada) {
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("OCC").getSingle());
			}
			if (checkBuscarAceptada) {
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("OCT").getSingle());
			}
		}
		return lista;
	}

	@Command
	public void eliminarItem(){
		System.err.println("Eliminar item de kardex");
	}
	
	@Command
	public void salvarCambiosRowKardex(@BindingParam("index") Integer index){
		kardex = kardexList.get(index);
		kardex = (Kardex) kardexRest.save(kardex).getSingle();
		StockUtils.showSuccessmessage("Los cambios del producto " +kardex.getProducto().getNombre() + " han sido guardados",
				Clients.NOTIFICATION_TYPE_INFO, 0,
				null);
	}
	
	@Command
	public void verTarjetaProductoKardex(@BindingParam("index") Integer index){
		kardex = kardexList.get(index);
		abrirTarjetaKardexProducto(kardex.getProducto());
	}
	
	@GlobalCommand
	@NotifyChange({ "kardexProveedor", "productosKardexSelected" })
	public void modalTarjetaProductoCerrado() {
		kardexProveedor = null;
		productosKardexSelected = null;
	}
	
	@Command
	public void verTarjetaProductoSeleccionado(){
		abrirTarjetaKardexProducto(productosKardexSelected);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void abrirTarjetaKardexProducto(Producto productoSending){
		DevelopmentTool modo = (DevelopmentTool) developmentToolRest.getByDescripcion(StockConstants.SISTEMA_CONFIG_MODE).getSingle();
		if(modo != null){
			List<Kardex> list = (List<Kardex>) kardexRest.getByProducto(productoSending, organizacion).getSingle();
			
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandReturn", "modalTarjetaProductoCerrado");
			inputParams.put("productoList", list);
			inputParams.put("modalidadCalculo", modo);
			inputParams.put("formato", false);
			
			
			Window productoModalView = this.stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/tarjetaKardexProducto.zul", inputParams);
			productoModalView.doModal();
		}else
			Messagebox.show("El sistema no encontro la configuracion de modalidad para el calculo del costo\n"
					+ "PEPS, UEPS o PROMEDIO.\n\n"
					+ "Se recomienda verificar en: \n"
					+ "Perfil>Organizacion>Configuracion>Modalidad de calculo del costo");
		
	}
	
	@Command
	public void abrirKardex(){
		DevelopmentTool modo = (DevelopmentTool) developmentToolRest.getByDescripcion(StockConstants.SISTEMA_CONFIG_MODE).getSingle();
		if(modo != null){
			
			
			Map<String, Object> inputParams = new HashMap();
			inputParams.put("updateCommandReturn", "modalTarjetaProductoCerrado");
			inputParams.put("productoList", kardexList);
			inputParams.put("modalidadCalculo", modo);
			inputParams.put("formato", true);
			Window productoModalView = this.stockUtils
					.createModelDialogWithParams("/modulos/productos/utils/tarjetaKardexProducto.zul", inputParams);
			productoModalView.doModal();
		}else
			Messagebox.show("El sistema no encontro la configuracion de modalidad para el calculo del costo\n"
					+ "PEPS, UEPS o PROMEDIO.\n\n"
					+ "Se recomienda verificar en: \n"
					+ "Perfil>Organizacion>Configuracion>Modalidad de calculo del costo");
	}
}
