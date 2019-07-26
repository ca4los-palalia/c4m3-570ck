package com.cplsystems.stock.app.vm.ordencompra;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.converters.ShortConverter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.ReportesBuild;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.ordencompra.utils.OrdenCompraMetaclass;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.KardexProveedor;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.ProductoPrecios;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.EstatusRequisicionService;

@VariableResolver({ DelegatingVariableResolver.class })
public class OrdenCompraVM extends OrdenCompraMetaclass {
	private static final long serialVersionUID = 999672890629004080L;

	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		
		checkBuscarNueva = true;
		EstatusRequisicion req = estatusRequisicionService.getByClave("OCN");
		List<EstatusRequisicion> input = new ArrayList<>();
		input.add(req);
		
		ordenesCompras = ordenCompraService.getOrdenesByEstatusAndFolioOr("", input);
		getStylesGlobal();
	}

	@Command
	@NotifyChange({ "ordenesCompras" })
	public void checkNueva() {
		checkBuscarNueva = true;
		ordenesCompras = buscarOrdenCompra();
		if (ordenesCompras == null || ordenesCompras.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron Ordenes de compra nuevas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarNueva = false;
		
	}

	@Command
	@NotifyChange({ "ordenesCompras" })
	public void checkCancelada() {
		checkBuscarCancelada = true;
		ordenesCompras = buscarOrdenCompra();
		if (ordenesCompras == null || ordenesCompras.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron Ordenes de compra Canceladas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarCancelada = false;
		
	}

	@Command
	@NotifyChange({ "ordenesCompras" })
	public void checkAceptada() {
		checkBuscarAceptada = true;
		ordenesCompras = buscarOrdenCompra();
		if (ordenesCompras == null || ordenesCompras.size() == 0) {
			StockUtils.showSuccessmessage("No se encontraron Ordenes de compra aceptadas", Clients.NOTIFICATION_TYPE_WARNING, 0,
					null);
		}
		checkBuscarAceptada = false;
		
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
	public void transferirOrdenCompraToFormulario(@BindingParam("index") Integer index) {
		if (index != null) {
			OrdenCompraInbox toLoad = (OrdenCompraInbox) ordenesCompraInbox.get(index.intValue());
			if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue())) {
				toLoad.setLeido(Boolean.valueOf(true));
				ordenCompraInboxService.save(toLoad);
				toLoad.setIcono("/images/toolbar/openedEmail.png");
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada)
				|| (checkBuscarAceptada)) {
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

	
	private List<OrdenCompra> buscarOrdenCompra() {
		List<OrdenCompra> listaExtraer = null;
		if ((checkBuscarNueva) || (checkBuscarCancelada) || (checkBuscarEnviada)
				|| (checkBuscarAceptada)
				|| ((requisicion != null) && (requisicion.getBuscarRequisicion() != null)
						&& (!requisicion.getBuscarRequisicion().isEmpty()))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
			listaExtraer = ordenCompraService
					.getOrdenesByEstatusAndFolioOr(requisicion.getBuscarRequisicion(), listaEstatus);
			if (cotizacionesList == null) {
			}
		} else {
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de ordenes de compra (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)",
					"warning", Integer.valueOf(0), null);
		}
		return listaExtraer;
	}

	@Command
	@NotifyChange({ "*" })
	public void mostrarProductosOrdenCompra() {
		
		cotizacionSelected = ordenCompra.getCotizacion();
		if (cotizacionSelected != null) {
			cotizacionesConProductos = cotizacionService.getByProveedorFolioCotizacionNueva(
					cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
					cotizacionSelected.getEstatusRequisicion());

			numeroProductos = Integer.valueOf(cotizacionesConProductos.size());
			precioTotal = Float.valueOf(0.0F);
			for (Cotizacion item : cotizacionesConProductos) {
				/*
				List<ProductoPrecios> precios = productoPreciosService.getByProductoOrderMostRecentDate(item.getProducto());
				Float precio = 0F;
				if(precios != null)
					precio = precios.get(0).getPrecioValue();
				*/
				Float precio = item.getProductoPrecios().getPrecioValue();
				
				item.getProducto().setPrecio(precio);
				
				item.getRequisicionProducto().setTotalProductoPorUnidad(
						Float.valueOf(item.getRequisicionProducto().getCantidad().floatValue()
								* precio));

				precioTotal = Float.valueOf(precioTotal.floatValue()
						+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Command
	@NotifyChange({ "*" })
	public void abrirVentanaCancelacion() {
		if (ordenCompra != null) {
			if (ordenCompra.getEstatusRequisicion().getClave().equals("OCN")) {
				@SuppressWarnings("unchecked")
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
		
				if(enviarXmlToConffya(construirXmlOrden(ordenCompra))){
					EstatusRequisicion estado = estatusRequisicionService.getByClave("OCT");

					ordenCompra.setEstatusRequisicion(estado);
					ordenCompraService.save(ordenCompra);
					
					
					buildKardexList(ordenCompra);
					
					//-----------------------------------------------------
					String mensaje = "La orden de compra " + ordenCompra.getCodigo() + " ha sido aceptada.";
					if (organizacion.getDevelopmentTool() != null) {
						List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
						if (privilegios != null && privilegios.size() > 0) {
							enviarCorreos(usuario, organizacion, privilegios, mensaje, "Orden de compra aceptada ", null);
						}
					} else
						mensaje += ". No se pudo enviar un email para la notificación";
					
					actualizarRequisicion("RQT");
					StockUtils.showSuccessmessage(mensaje + " ", Clients.NOTIFICATION_TYPE_INFO, 0, null);
				}else
					StockUtils.showSuccessmessage("No se puede salvar orden de compra, error con conffya ", Clients.NOTIFICATION_TYPE_ERROR, 0, null);
					
				
				//-----------------------------------------------------
			} else {
				StockUtils.showSuccessmessage(
						"La orden de compra [" + ordenCompra.getCodigo()
								+ "] no puede ser aceptada bajo este estatus ("
								+ ordenCompra.getEstatusRequisicion().getNombre() + ")",
								Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else {
			StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
		}
		
	}
	
	//---------------------------------------------------
	private List<Kardex> buildKardexList(OrdenCompra ordenCompra) {
		Cotizacion cotizacionItem = ordenCompra.getCotizacion();
		
		List<Cotizacion> listOrdenCompra = cotizacionService.getByProveedorFolioCotizacionNueva(
				cotizacionItem.getProveedor(), cotizacionItem.getFolioCotizacion(),
				cotizacionItem.getEstatusRequisicion());
		
		List<Kardex> tempList = null;
		if (listOrdenCompra != null) {
			tempList = new ArrayList<>();
			for (Cotizacion item : listOrdenCompra) {
				Kardex temObject = new Kardex();
				temObject.setFechaEntrada(stockUtils.convertirCalendarToDate(Calendar.getInstance()));
				temObject.setProducto(item.getProducto());
				temObject.setEntradaCantidad(Math.round(item.getRequisicionProducto().getCantidad()));
				Float debe = 0F;
				if(item.getProducto().getPrecio() != null && item.getRequisicionProducto().getCantidad() != null)
					debe = Integer.parseInt(String.valueOf(Math.round(item.getRequisicionProducto().getCantidad())))
							* item.getProducto().getPrecio();
				temObject.setDebe(debe);
				temObject.setIcon(stockUtils.Encriptar("/images/toolbar/infoxOrange16.png"));
				temObject.setPrecioUnitario(item.getProductoPrecios().getPrecioValue());
				temObject.setTypeFormat("E");
				tempList.add(temObject);
			}
			if(tempList != null && tempList.size() > 0){
				EstatusRequisicion estadoKardex = estatusRequisicionService.getByClave("KXN");
				
				KardexProveedor kardexProveedor = new KardexProveedor();
				kardexProveedor.setOrganizacion(organizacion);
				kardexProveedor.setUsuario(usuario);
				kardexProveedor.setProveedor(cotizacionItem.getProveedor());
				kardexProveedor.setEstatusRequisicion(estadoKardex);
				kardexProveedor.setOrdenCompra(ordenCompra);
				kardexProveedorService.save(kardexProveedor);
				
				for (Kardex kardexItem : tempList) {
					kardexItem.setEstatusRequisicion(estadoKardex);
					kardexItem.setUsuario(usuario);
					kardexItem.setOrganizacion(organizacion);
					kardexItem.setKardexProveedor(kardexProveedor);
					kardexService.save(kardexItem);
				}
				
			}
		}
		return tempList;
	}
	//---------------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Command
	public void imprimirOrdenCompra() {
		if (ordenCompra != null) {
			cotizacionSelected = ordenCompra.getCotizacion();
			if ((cotizacionSelected != null) && (cotizacionesConProductos != null)
					&& (cotizacionesConProductos.size() > 0)) {
				
				ReportesBuild reporteador = new ReportesBuild();
				
				Map<String, Object> inputParams = new HashMap();
				inputParams.put("executeNameMethod", "closeVisorPdf");
				inputParams.put("titulo", "Documento de Cotizacion " + cotizacionSelected.getFolioCotizacion());
				
				inputParams.put("fuente", reporteador.genererOrdenCompraPdf(ordenCompra, cotizacionSelected, organizacion, cotizacionesConProductos));
				
				Window windowModalView = stockUtils
						.createModelDialogWithParams("/modulos/utilidades/visorPdf.zul", inputParams);
				windowModalView.doModal();
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

	private void actualizarRequisicion(String clave){
		List<RequisicionProducto> listaProductosRequisicion = requisicionProductoService.getByRequisicion(ordenCompra.getCotizacion().getRequisicion());
		for (RequisicionProducto requisicionProducto : listaProductosRequisicion) {
			for (Cotizacion itemCotizacion : cotizacionesConProductos) {
				if(requisicionProducto.getProducto().getClave().equals(itemCotizacion.getProducto().getClave())){ 
					requisicionProducto.setEntregados(requisicionProducto.getCantidad().longValue());
				}
			}
		}
		
		EstatusRequisicion estatus = estatusRequisicionService.getByClave(clave);
		ordenCompra.getCotizacion().getRequisicion().setEstatusRequisicion(estatus);
		requisicionService.save(ordenCompra.getCotizacion().getRequisicion());
	}

	private boolean enviarXmlToConffya(String compromisoXml) {
		boolean ok = false;
		try {
			inicializarServicioWebService();
			short ejercicio = new Short(String.valueOf(organizacion.getEjercicio()));
			short numero = new Short(String.valueOf(organizacion.getNumero()));
			
			/*
			compromisoXml = "<?xml version='1.0' encoding='iso-8859-1' ?>"
					+"<conffya>"
			+"<compromisos>"
+"<totalElementos>1</totalElementos>"
+"<compromiso>"
+"<idPedido>11</idPedido>"
+"<rfcProveedor>RALL850325RT5</rfcProveedor>"
+"<nombreProveedor>Lauro Ramirez Lopez</nombreProveedor>"
+"<referencia>A11</referencia>"
+"<fecha>03/01/2015</fecha>"
+"<pedidos>"
+"<pedido>"
+"<referenciaM>Art125</referenciaM>"
+"<importeTotal>2500</importeTotal>"
+"<descripcionCompra>Compra de articulos varios</descripcionCompra>"
+"<claves>"
+"<clave>"
+"<claveMovimiento>216</claveMovimiento>"
+"<importe>1000</importe>"
+"<clavePresupuestal>3111104256S03E0062161211204REPRO</clavePresupuestal>"
+"</clave>"
+"<clave>"
+"<claveMovimiento>216</claveMovimiento>"
+"<importe>1500</importe>"
+"<clavePresupuestal>3111113311E13E0072161211204REPRO</clavePresupuestal>"
+"</clave>"
+"</claves>"
+"</pedido>"
+"</pedidos>"
+"</compromiso>"
+"</compromisos>"
+"</conffya>";
			numero = 2076;
			ejercicio = 2015;
			*/
			String xml = serviceWs.guardarCompromiso(numero, ejercicio, compromisoXml);
			if ( (xml.contains("<estado>1</estado>")) &&
					(xml.contains("Se inserto el compromiso") || xml.contains("Se modifico el compromiso")))
				ok = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok;
	}


}
