package com.came.stock.web.vm.requisicion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.came.stock.constantes.StockConstants;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.CotizacionInbox;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.ProveedorProducto;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.SessionUtils;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.vm.requisicion.utils.RequisicionVariables;

@VariableResolver({ DelegatingVariableResolver.class })
public class concentradoVM extends RequisicionVariables {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	List<RequisicionProducto> clonRequisicionProductos;
	
	@Init
	public void init() {
		super.init();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		requisicion = new Requisicion();
		areas = (List<Area>)areaRest.getAll(organizacion).getSingle();
		areaBuscar = new Area();
		
		clonRequisicionProductos = (List<RequisicionProducto>)requisicionProductoRest.getAll(organizacion).getSingle();
		requisicionProductos = clonRequisicionProductos;
		leerVariablesProperties();
		getStylesGlobal();
	}

	@Command
	@NotifyChange("proveedorProductos")
	public void cargarProveedoresProducto() {
		if (requisicionProductoSeleccionado != null) {
			
			boolean deshabilitarCheckBox = deshabilitarProductoConcluidoEnrequisicion();
			
			if (requisicionProductoSeleccionado.getProducto() != null)
				proveedorProductos = (List<ProveedorProducto>) proveedorProductoRest
						.getByProducto(requisicionProductoSeleccionado.getProducto());

			if (proveedorProductos != null) {// nuevo codigo
				for (ProveedorProducto item : proveedorProductos) {
					item.setDeshabilitar(deshabilitarCheckBox);
					
					Cotizacion itemCotizacion = (Cotizacion) cotizacionRest.getByRequisicionProveedorAndProducto(
							requisicionProductoSeleccionado.getRequisicion(), item.getProveedor(), item.getProducto(), organizacion).getSingle();
					if (itemCotizacion != null)
						item.setSeleccionar(true);
					
					List<ProductoPrecios> precios = (List<ProductoPrecios>) productoPreciosRest.getByProductoOrderMostRecentDate(item.getProducto(), organizacion).getSingle();
					if(precios != null)
						item.getProducto().setPrecio(precios.get(0).getPrecioValue());

					for (Cotizacion item2 : cotizacionesList) {
						if (item.getProducto().getClave().equals(item2.getProducto().getClave())
								&& item.getProveedor().getNombre().equals(item2.getProveedor().getNombre())) {
							item.setSeleccionar(true);
							break;
						}
					}
				}
			} else {
				StockUtils.showSuccessmessage(CONCENTRADOVM_MESSAGE1_CONTENT,
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
				// END nuevo codigo
			}
			
			
		}
	}
	
	private boolean deshabilitarProductoConcluidoEnrequisicion(){
		if(requisicionProductoSeleccionado.getRequisicion().getEstatusRequisicion().getClave().equals("RQN"))
			return false;
		else return true;
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
	public void seleccionarProveedor() {
		if (proveedorProducto != null) {
			for (RequisicionProducto item : requisicionProductos) {
				if (requisicionProductoSeleccionado.equals(item)) {
					item.setProveedor(proveedorProducto.getProveedor());
					break;
				}
			}
		}
	}

	private List<RequisicionProducto> buscarPorClaveProducto(String buscar) {
		List<RequisicionProducto> rp = null;
		Producto pr = (Producto) productoRest.getByClave(buscar, organizacion).getSingle();
		if (pr != null)
			rp = (List<RequisicionProducto>) requisicionProductoRest.getByProducto(pr, organizacion).getSingle();
		return rp;
	}

	private List<RequisicionProducto> buscarPorFolioRequisicion(String buscar) {
		List<RequisicionProducto> rp = null;
		Requisicion rq = (Requisicion) requisicionRest.getByFolio(buscar, organizacion).getSingle();
		if (rq != null)
			rp = (List<RequisicionProducto>) requisicionProductoRest.getByRequisicion(rq, organizacion).getSingle();
		return rp;
	}

	private List<RequisicionProducto> buscarPorPartidaGenerica(String buscar) {
		List<RequisicionProducto> rp = null;
		ConffyaPartidaGenerica cpg = (ConffyaPartidaGenerica) conffyaPartidaGenericaRest.getByNombre(buscar, organizacion).getSingle();
		if (cpg != null)
			rp = (List<RequisicionProducto>) requisicionProductoRest.getByConfiaPartidaGenerica(cpg, organizacion).getSingle();
		return rp;
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
	public void buscarPorArearequisicion() {
		List<Requisicion> requisicionesTemp = (List<Requisicion>) requisicionRest.getByUnidadResponsable(areaBuscar, organizacion).getSingle();
		if (requisicionesTemp != null) {
			requisicionProductos = new ArrayList<RequisicionProducto>();
			List<RequisicionProducto> productosTemp = new ArrayList<RequisicionProducto>();
			for (Requisicion item : requisicionesTemp) {
				productosTemp = (List<RequisicionProducto>) requisicionProductoRest.getByRequisicion(item, organizacion).getSingle();
				if (productosTemp != null) {
					for (RequisicionProducto item2 : productosTemp) {
						requisicionProductos.add(item2);
					}
				}
			}
			if (requisicionProductos.size() == 0) {
				requisicionProductos = new ArrayList<RequisicionProducto>();
				StockUtils.showSuccessmessage(CONCENTRADOVM_MESSAGE2_CONTENT,
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}
		} else
			showWindowInformationMessage(
					CONCENTRADOVM_MESSAGE3_TITLE, 
					CONCENTRADOVM_MESSAGE3_CONTENT1 + ": " + areaBuscar.getNombre(),
					"acceptButtonWindowInformation", StockConstants.ICON_WIN_INFORMATION);
		
	}
	
	@GlobalCommand
	@NotifyChange({ "requisicionProductos"})
	public void acceptButtonWindowInformation(@BindingParam("accept") boolean ok) {
		if(ok){
			requisicionProductos = clonRequisicionProductos; 
		}
	}

	@Command
	@NotifyChange({ "requisicionProductos" })
	public void filtrarProductoPorRequisicion() {
		if (requisicion == null) {
			requisicion = new Requisicion();
		}
		if ((requisicion.getBuscarRequisicion() != null) && (!requisicion.getBuscarRequisicion().isEmpty())) {
			requisicionProductos = buscarPorClaveProducto(requisicion.getBuscarRequisicion());
			if (requisicionProductos == null) {
				requisicionProductos = buscarPorFolioRequisicion(requisicion.getBuscarRequisicion());
				if (requisicionProductos == null) {
					requisicionProductos = buscarPorPartidaGenerica(requisicion.getBuscarRequisicion());
				}
			}
			if (requisicionProductos == null) {
				showWindowInformationMessage(CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_TITLE,
						CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_CONTENT1 + 
						" -" + requisicion.getBuscarRequisicion() + "- " + CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_CONTENT2,
						"acceptButtonWindowInformation", StockConstants.ICON_WIN_INFORMATION);
			}
		} else {
			showWindowInformationMessage(CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_TITLE,
					CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_CONTENT3,
					"acceptButtonWindowInformation", StockConstants.ICON_WIN_INFORMATION);
		}
	}

	@Command
	@NotifyChange({ "*" })
	public void removerProductoDeListaGeneralDeProductos(@BindingParam("index") Integer index) {
		if (requisicionProductoSeleccionado == null) {
			requisicionProductoSeleccionado = ((RequisicionProducto) requisicionProductos
					.get(index.intValue()));
		}
		requisicionProductoRest.delete(requisicionProductoSeleccionado).getSingle();
		requisicionProductos.remove(requisicionProductoSeleccionado);

		StockUtils.showSuccessmessage(
				CONCENTRADOVM_MESSAGE5_REMOVE_CONTENT1 + 
				" -" + requisicionProductoSeleccionado.getProducto().getNombre()
						+ "- " + CONCENTRADOVM_MESSAGE5_REMOVE_CONTENT2
						+ requisicionProductoSeleccionado.getRequisicion().getFolio() + "-",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@Command
	@NotifyChange({ "*" })
	public void cancelarRequisicion() {
		EstatusRequisicion estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("RQC").getSingle();

		Requisicion rq = requisicionProductoSeleccionado.getRequisicion();
		rq.setEstatusRequisicion(estado);
		rq = (Requisicion) requisicionRest.save(rq).getSingle();

		StockUtils.showSuccessmessage(CONCENTRADOVM_MESSAGE6_CANCELAR_CONTENT + " -"
				+ requisicionProductoSeleccionado.getRequisicion().getFolio() + "- " + 
				CONCENTRADOVM_MESSAGE6_CANCELAR_CONTENT2, "info",
				Integer.valueOf(0), null);

		estado = (EstatusRequisicion) estatusRequisicionRest.getByClave("RQN").getSingle();
		requisiciones = (List<Requisicion>) requisicionRest.getByEstatusRequisicion(estado, organizacion).getSingle();
		requisicionProductos = (List<RequisicionProducto>) requisicionProductoRest.getRequisicionesConEstadoEspecifico(estado, organizacion).getSingle();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@NotifyChange({ "proveedorProducto" })
	@Command
	public void proveedorCheckBox(@BindingParam("index") Integer index) {
		ProveedorProducto pp = (ProveedorProducto) proveedorProductos.get(index.intValue());
		if (cotizacionesList == null) {
			cotizacionesList = new ArrayList();
		}
		Cotizacion nuevaCotizacion = new Cotizacion();
		nuevaCotizacion.setRequisicion(requisicionProductoSeleccionado.getRequisicion());

		nuevaCotizacion.setProveedor(pp.getProveedor());
		nuevaCotizacion.setProducto(requisicionProductoSeleccionado.getProducto());

		nuevaCotizacion.setRequisicionProducto(requisicionProductoSeleccionado);

		boolean agregar = true;
		if (pp.isSeleccionar()) {
			for (Cotizacion item : cotizacionesList) {
				if ((item.getProveedor().equals(nuevaCotizacion.getProveedor()))
						&& (item.getRequisicion().equals(nuevaCotizacion.getRequisicion()))
						&& (nuevaCotizacion.getProducto().equals(requisicionProductoSeleccionado.getProducto()))) {
					agregar = false;
					break;
				}
			}
		}
		if ((agregar) && (pp.isSeleccionar())) {
			cotizacionesList.add(nuevaCotizacion);
		}
		if (!pp.isSeleccionar()) {
			for (Cotizacion item : cotizacionesList) {
				if ((item.getProveedor().getNombre().equals(nuevaCotizacion.getProveedor().getNombre()))
						&& (item.getRequisicion().getFolio().equals(nuevaCotizacion.getRequisicion().getFolio()))
						&& (nuevaCotizacion.getProducto().getNombre()
								.equals(requisicionProductoSeleccionado.getProducto().getNombre()))) {
					cotizacionesList.remove(item);
					break;
				}
			}
		}
	}

	private List<Cotizacion> salvarCotizacion() {
		List<Cotizacion> cotizacionReturn = null;
		if (requisicionProductos != null && requisicionProductos.size() > 0) {
			cotizacionReturn = new ArrayList<Cotizacion>();
			
			for (RequisicionProducto item : requisicionProductos){
				// SALVAR CANTIDAD DE PRODUCTO
				if(!item.isDeshabilitar())
					item = (RequisicionProducto) requisicionProductoRest.save(item).getSingle();
			}
																	
				

			if (cotizacionesList != null && cotizacionesList.size() > 0) {
				List<Cotizacion> cotizacionesInbox = new ArrayList<Cotizacion>();

				// GENERAR COTIZACIONES (PROVEDORES NO DUPLICADOS) + FOLIO
				List<Cotizacion> preCotizacionConFolio = new ArrayList<Cotizacion>();
				String folio = generarFolioCotizacion(new Long((String) cotizacionRest.countRows(organizacion).getSingle()));
				Long i = 1L;
				for (Cotizacion item : cotizacionesList) {
					boolean agregar = true;
					for (Cotizacion ctzConFolio : preCotizacionConFolio) {
						if (item.getProveedor().getNombre().equals(ctzConFolio.getProveedor().getNombre())) {
							agregar = false;
							break;
						}
					}
					if (agregar) {
						item.setFolioCotizacion(folio);
						preCotizacionConFolio.add(item);
						folio = generarFolioCotizacion(i + 1);
						i++;
					}
				} // FIN GENERAR COTIZACIONES (PROVEDORES NO DUPLICADOS) + FOLIO

				// SALVAR COTIZACIONES
				for (Cotizacion item : cotizacionesList) {
					Cotizacion verificarCotizacion = (Cotizacion) cotizacionRest.getByRequisicionProveedorAndProducto(
							item.getRequisicion(), item.getProveedor(), item.getProducto(), organizacion).getSingle();

					if (verificarCotizacion == null) {
						EstatusRequisicion estado = (EstatusRequisicion) estatusRequisicionRest
								.getByClave(StockConstants.ESTADO_COTIZACION_NUEVA).getSingle();

						item.setFolioCotizacion(obtenerFolio(preCotizacionConFolio, item));
						item.setOrganizacion((Organizacion) sessionUtils.getFromSession(SessionUtils.FIRMA));
						item.setUsuario((Usuarios) sessionUtils.getFromSession(SessionUtils.USUARIO));
						item.setEstatusRequisicion(estado);
						item.setFechaEnvioCotizacion(Calendar.getInstance());
						item = (Cotizacion) cotizacionRest.save(item).getSingle();
						cotizacionesInbox.add(item);
					}
				} // FIN SALVAR COTIZACIONES

				// GENERAR INBOX DE COTIZACIONES
				for (Cotizacion cotizacion2 : cotizacionesInbox) {
					boolean salvar = true;
					for (Cotizacion cotizacionInbox : cotizacionReturn) {
						if (cotizacion2.getProveedor().getNombre().equals(cotizacionInbox.getProveedor().getNombre())) {
							salvar = false;
							break;
						}
					}
					if (salvar) {
						CotizacionInbox inbox = new CotizacionInbox();
						inbox.setLeido(false);
						inbox.setCotizacion(cotizacion2);
						inbox.setFechaRegistro(new StockUtils().convertirCalendarToDate(Calendar.getInstance()));
						inbox = (CotizacionInbox) cotizacionInboxRest.save(inbox).getSingle();
						cotizacionReturn.add(cotizacion2);
					}
				} // FIN GENERAR INBOX DE COTIZACIONES
				
				StockUtils.showSuccessmessage(CONCENTRADOVM_MESSAGE7_SAVE_COTIZACION_CONTENT1 
						+ " " + cotizacionesList.size() + " " 
						+ CONCENTRADOVM_MESSAGE7_SAVE_COTIZACION_CONTENT2 , Clients.NOTIFICATION_TYPE_INFO, 0, null);
			}
		}
		return cotizacionReturn;
	}

	private String obtenerFolio(List<Cotizacion> listaCotizacionesFolio, Cotizacion cotizacionEntrada) {
		String folio = "";
		for (Cotizacion item : listaCotizacionesFolio) {
			if (item.getProveedor().getNombre().equals(cotizacionEntrada.getProveedor().getNombre())) {
				folio = item.getFolioCotizacion();
				break;
			}
		}
		return folio;
	}

	private String generarFolioCotizacion(Long countRows) {
		String folio = null;
		if (countRows != null) {
			folio = "FCO";
			if (String.valueOf(countRows.toString().length()).equals("1")) {
				folio = folio + "00" + countRows;
			} else if (String.valueOf(countRows.toString().length()).equals("2")) {
				folio = folio + "0" + countRows;
			} else if (String.valueOf(countRows.toString().length()).equals("3")) {
				folio = folio + countRows;
			}
		}
		return folio;
	}

	@Command
	public void autorizar() {
		salvarCotizacion();
	}

	@Command
	public void guardarCambios() {
		for (RequisicionProducto item : requisicionProductos) {
			item = (RequisicionProducto) requisicionProductoRest.save(item).getSingle();
		}
		StockUtils.showSuccessmessage(CONCENTRADOVM_MESSAGE8_UPDATE,
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	private void leerVariablesProperties(){
		Properties propiedades = getPropertiesFiles();
		
		GENERICZUL_LABEL_AREA_UR = propiedades.getProperty("genericZUL.label.area.ur");//Área (UR):
		GENERICZUL_TIP_BUSCADOR =  propiedades.getProperty("genericZUL.tip.buscador");
		GENERICZUL_LABEL_BUSCADOR = propiedades.getProperty("genericZUL.label.buscador");
		REQUISICIONZUL_BUSQUEDA_VACIA = propiedades.getProperty("requisicionZUL.busqueda.vacia");
		GENERICZUL_COLUMN_FOLIO_REQ = propiedades.getProperty("genericZUL.column.folio.req");
		GENERICZUL_COLUMN_UR = propiedades.getProperty("genericZUL.column.ur");
		GENERICZUL_COLUMN_CVL_PR = propiedades.getProperty("genericZUL.column.cvl.pr");
		GENERICZUL_TIP_CLAVE_PRODUCTO = propiedades.getProperty("genericZUL.tip.clave.producto");
		GENERICZUL_COLUMN_CANTIDAD_ABR = propiedades.getProperty("genericZUL.column.cantidad.abr");
		
		GLOBAL_INFO_MESSAGE1 = propiedades.getProperty("global.info.message1");
		GLOBAL_INFO_STATUS_NUEVO = propiedades.getProperty("global.info.status.nuevo");
		GLOBAL_INFO_STATUS_CANCELADA = propiedades.getProperty("global.info.status.cancelada");
		GLOBAL_INFO_STATUS_ACEPTADO = propiedades.getProperty("global.info.status.aceptado");
		GLOBAL_INFO_PDF_GENERADO = propiedades.getProperty("global.info.pdf.generado");
		GLOBAL_INFO_PDF_CONFIRMATION = propiedades.getProperty("global.info.pdf.confirmation");
		REQUISICIONZUL_TIP_REMOVE_PRODUCTO = propiedades.getProperty("requisicionZUL.tip.remove.producto");
		GENERICZUL_TIP_FOLIO_REQUISICION = propiedades.getProperty("genericZUL.tip.folio.requisicion");
		GENERICZUL_TIP_CLAVE_AREA  = propiedades.getProperty("genericZUL.tip.clave.area");
		GENERICZUL_TIP_CANTIDAD_PRODUCTO = propiedades.getProperty("genericZUL.tip.cantidad.producto");
		GENERICZUL_TIP_GUARDAR_CAMBIOS = propiedades.getProperty("genericZUL.tip.guardar.cambios");
		CONCENTRADOZUL_TIP_GENERAR_COTIZACION = propiedades.getProperty("concentradoZUL.tip.generar.cotizacion");
		CONCENTRADOZUL_TITLE_MAIN_PANEL = propiedades.getProperty("concentradoZUL.title.main.panel");
		GENERICZUL_COLUMN_PROVEEDOR = propiedades.getProperty("genericZUL.column.proveedor");// = Proveedor");
		GENERICZUL_COLUMN_ARTICULO = propiedades.getProperty("genericZUL.column.articulo");// = Artículo
		GENERICZUL_COLUMN_PRECIO = propiedades.getProperty("genericZUL.column.precio");// = Precio
		GENERICZUL_COLUMN_STOCK = propiedades.getProperty("genericZUL.column.stock");// = Stock
		
		CONCENTRADOVM_MESSAGE1_CONTENT = propiedades.getProperty("concentradoZUL.message1.content");
		CONCENTRADOVM_MESSAGE2_CONTENT = propiedades.getProperty("concentradoZUL.message2.content");
		CONCENTRADOVM_MESSAGE3_TITLE = propiedades.getProperty("concentradoZUL.message3.title");
		CONCENTRADOVM_MESSAGE3_CONTENT1 = propiedades.getProperty("concentradoZUL.message3.content1");
		
		CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_TITLE = propiedades.getProperty("concentradoVM.message4.buscar.producto.title");
		CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_CONTENT1 = propiedades.getProperty("concentradoVM.message4.buscar.producto.content1");
		CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_CONTENT2 = propiedades.getProperty("concentradoVM.message4.buscar.producto.content2");
		CONCENTRADOVM_MESSAGE4_BUSCAR_PRODUCTO_CONTENT3 = propiedades.getProperty("concentradoVM.message4.buscar.producto.content3");
		CONCENTRADOVM_MESSAGE5_REMOVE_CONTENT1 = propiedades.getProperty("concentradoVM.message5.remove.content1");
		CONCENTRADOVM_MESSAGE5_REMOVE_CONTENT2 = propiedades.getProperty("concentradoVM.message5.remove.content2");
		CONCENTRADOVM_MESSAGE6_CANCELAR_CONTENT = propiedades.getProperty("concentradoVM.message6.cancelar.content");
		CONCENTRADOVM_MESSAGE6_CANCELAR_CONTENT2 = propiedades.getProperty("concentradoVM.message6.cancelar.content2");
		CONCENTRADOVM_MESSAGE7_SAVE_COTIZACION_CONTENT1 = propiedades.getProperty("concentradoVM.message7.save.cotizacion.content1");
		CONCENTRADOVM_MESSAGE7_SAVE_COTIZACION_CONTENT2 = propiedades.getProperty("concentradoVM.message7.save.cotizacion.content2");
		CONCENTRADOVM_MESSAGE8_UPDATE = propiedades.getProperty("concentradoVM.message8.update");

	}
	


}
