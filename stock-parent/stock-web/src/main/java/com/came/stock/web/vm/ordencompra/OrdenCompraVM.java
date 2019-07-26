package com.came.stock.web.vm.ordencompra;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.utilidades.UserPrivileges;
import com.came.stock.web.utils.ReportesBuild;
import com.came.stock.web.vm.ordencompra.utils.OrdenCompraMetaclass;

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
		EstatusRequisicion req = (EstatusRequisicion) estatusRequisicionRest.getByClave("OCN").getSingle();
		List<EstatusRequisicion> input = new ArrayList<>();
		input.add(req);
		
		ordenesCompras = (List<OrdenCompra>) ordenCompraRest.getByEstatusAndFolioOr("", input, organizacion).getSingle();
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
				toLoad = (OrdenCompraInbox) ordenCompraInboxRest.save(toLoad).getSingle();
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
			if (checkBuscarNueva)
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("OCN").getSingle());
			if (checkBuscarCancelada)
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("OCC").getSingle());
			if (checkBuscarAceptada)
				lista.add((EstatusRequisicion) estatusRequisicionRest.getByClave("OCT").getSingle());
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
			listaExtraer = (List<OrdenCompra>) ordenCompraRest
					.getByEstatusAndFolioOr(requisicion.getBuscarRequisicion(), listaEstatus, organizacion).getSingle();
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
			cotizacionesConProductos = (List<Cotizacion>) cotizacionRest.getByProveedorFolioCotizacionNueva(
					cotizacionSelected.getProveedor(), cotizacionSelected.getFolioCotizacion(),
					cotizacionSelected.getEstatusRequisicion(), organizacion).getSingle();

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
					EstatusRequisicion estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("OCT").getSingle();

					ordenCompra.setEstatusRequisicion(estado);
					ordenCompra = (OrdenCompra) ordenCompraRest.save(ordenCompra).getSingle();
					
					
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
		
		List<Cotizacion> listOrdenCompra = (List<Cotizacion>) cotizacionRest.getByProveedorFolioCotizacionNueva(
				cotizacionItem.getProveedor(), cotizacionItem.getFolioCotizacion(),
				cotizacionItem.getEstatusRequisicion(), organizacion).getSingle();
		
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
				EstatusRequisicion estadoKardex = (EstatusRequisicion) estatusRequisicionRest.getByClave("KXN").getSingle();
				
				KardexProveedor kardexProveedor = new KardexProveedor();
				kardexProveedor.setOrganizacion(organizacion);
				kardexProveedor.setUsuario(usuario);
				kardexProveedor.setProveedor(cotizacionItem.getProveedor());
				kardexProveedor.setEstatusRequisicion(estadoKardex);
				kardexProveedor.setOrdenCompra(ordenCompra);
				kardexProveedor = (KardexProveedor) kardexProveedorRest.save(kardexProveedor).getSingle();
				
				for (Kardex kardexItem : tempList) {
					kardexItem.setEstatusRequisicion(estadoKardex);
					kardexItem.setUsuario(usuario);
					kardexItem.setOrganizacion(organizacion);
					kardexItem.setKardexProveedor(kardexProveedor);
					kardexItem = (Kardex) kardexRest.save(kardexItem).getSingle();
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
		List<Privilegios> usuarios = (List<Privilegios>) privilegioRest.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR, organizacion).getSingle();

		return usuarios;
	}

	private void actualizarRequisicion(String clave){
		List<RequisicionProducto> listaProductosRequisicion = (List<RequisicionProducto>) requisicionProductoRest.getByRequisicion(ordenCompra.getCotizacion().getRequisicion(), organizacion).getSingle();
		for (RequisicionProducto requisicionProducto : listaProductosRequisicion) {
			for (Cotizacion itemCotizacion : cotizacionesConProductos) {
				if(requisicionProducto.getProducto().getClave().equals(itemCotizacion.getProducto().getClave())){ 
					requisicionProducto.setEntregados(requisicionProducto.getCantidad().longValue());
				}
			}
		}
		
		EstatusRequisicion estatus = (EstatusRequisicion) estatusRequisicionRest.getByClave(clave).getSingle();
		ordenCompra.getCotizacion().getRequisicion().setEstatusRequisicion(estatus);
		ordenCompra.getCotizacion().setRequisicion((Requisicion) requisicionRest.save(ordenCompra.getCotizacion().getRequisicion()).getSingle());
		
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
