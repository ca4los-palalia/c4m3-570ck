package com.came.stock.web.vm.requisicion;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.Justificacion;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Posicion;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionInbox;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.utilidades.UserPrivileges;
import com.came.stock.web.services.confya.Informacion_Empresa;
import com.came.stock.web.utils.ReportesBuild;

@VariableResolver({ DelegatingVariableResolver.class })
public class RequisicionVM extends RequisicionMetaClass {
	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		
		areaBuscar = new Area();

		contratos = (List<Contrato>) contratoRest.getAll(organizacion);
		proveedoresLista = (List<Proveedor>)proveedorRest.getAll(organizacion).getSingle();
		if (requisicion == null) {
			requisicion = new Requisicion();
		}
		requisicion.setFecha(Calendar.getInstance());
		posiciones = (List<Posicion>) posicionRest.getAll(organizacion).getSingle();

		

		loadItemsKeys();
		initDefaultValues();
		loadRequisionesInbox();

		// productosDB = productoService.getAllLimited();

		List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
		if (privilegios != null)
			for (Privilegios privilegios2 : privilegios) {
				System.err.println(privilegios2);
			}

		cargarJustificacionesWs();
		leerVariablesProperties();
	}
	
	

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
		Page paginaHome = view.getPage();
		Window homeWindow = (Window) paginaHome.getFellow("mainWindow");
		Clients.clearBusy(homeWindow);
	}

	private void loadRequisionesInbox() {
		requisicionesInbox = (List<RequisicionInbox>) requisicionInboxRest
				.getAllNews((Organizacion) this.sessionUtils.getFromSession("FIRMA")).getSingle();
		for (RequisicionInbox rqInbox : this.requisicionesInbox) {
			if ((rqInbox.getLeido() != null) && (!rqInbox.getLeido().booleanValue())) {
				rqInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void transferirRequsicionToFormular(@BindingParam("index") Integer index) {
		if (index != null) {
			RequisicionInbox requisicionInbox = (RequisicionInbox) requisicionesInbox.get(index.intValue());
			if (!requisicionInbox.getLeido().booleanValue()) {
				requisicionInbox.setLeido(true);
				requisicionInbox.setIcono("/images/toolbar/openedEmail.png");
				requisicionInboxSeleccionada = (RequisicionInbox)requisicionInboxRest.save(requisicionInboxSeleccionada).getSingle();
			}
			Requisicion buscarRequisicion = requisicionInbox.getRequisicion();
			if (buscarRequisicion != null) {
				buscarRequisicion.setBuscarRequisicion(buscarRequisicion.getFolio());

				requisicion = buscarRequisicion;
				requisicionProductos = (List<RequisicionProducto>) requisicionProductoRest.getByRequisicion(buscarRequisicion, organizacion).getSingle();

				readOnly = true;
			}
		}
	}

	@NotifyChange({ "*" })
	@Command
	public void saveChanges() {
		if (this.readOnly) {
			StockUtils.showSuccessmessage(REQUISICIONVM_MESSAGE1_CONTENT, "info", Integer.valueOf(3500), null);

			return;
		}
		String validacion = validarCapturaRequisicion();
		if (validacion.isEmpty()) {
			/*
			 * if(requisicion.getCofiaPy() == null){
			 * if(requisicion.getCofiaPy().getIdConffyaPy() == null);
			 * requisicion.setCofiaPy(null); }
			 */
			if (validateBill()) {
				if (this.estatusRequisicion == null) {
					this.estatusRequisicion = new EstatusRequisicion();
				}
				if (this.requisicion.getIdRequisicion() == null) {

					String validaListaProductos = validarListaProductos();
					if (validaListaProductos.equals("")) {
						estatusRequisicion = (EstatusRequisicion) estatusRequisicionRest.getByClave("RQN").getSingle();

						requisicion.setEstatusRequisicion(estatusRequisicion);
						requisicion.setOrganizacion((Organizacion) sessionUtils.getFromSession("FIRMA"));

						requisicion.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));

						requisicion.setFecha(Calendar.getInstance());
						requisicion.setPersona((Persona)personaRest.save(requisicion.getPersona()).getSingle());
						requisicion = (Requisicion) requisicionRest.save(requisicion).getSingle();

						RequisicionInbox inbox = new RequisicionInbox();
						inbox.setRequisicion(this.requisicion);
						inbox.setLeido(Boolean.valueOf(false));
						inbox.setFechaRegistro(this.stockUtils.convertirCalendarToDate(Calendar.getInstance()));

						inbox = (RequisicionInbox) requisicionInboxRest.save(inbox).getSingle();

						String productosNoGuardados = "";
						for (int i = 0; i < this.requisicionProductos.size(); i++) {
							RequisicionProducto requisicionProducto = (RequisicionProducto) this.requisicionProductos
									.get(i);

							requisicionProducto.setRequisicion(this.requisicion);
							if ((requisicionProducto.getProducto() != null)
									&& (requisicionProducto.getProducto().getIdProducto() != null)) {
								requisicionProducto.setEntregados(Long.valueOf(0L));
								requisicionProducto
										.setOrganizacion((Organizacion) this.sessionUtils.getFromSession("FIRMA"));

								requisicionProducto.setUsuario((Usuarios) this.sessionUtils.getFromSession("usuario"));
								requisicionProducto = (RequisicionProducto) requisicionProductoRest.save(requisicionProducto).getSingle();
							} else {
								List<Producto> p = (List<Producto>) productoRest
										.getByClaveNombre(requisicionProducto.getProducto().getClave(), organizacion).getSingle();
								if (p != null) {
									requisicionProducto.setProducto((Producto) p.get(0));
									requisicionProducto.setEntregados(Long.valueOf(0L));
									requisicionProducto.setOrganizacion(organizacion);

									requisicionProducto.setUsuario(usuario);

									requisicionProducto = (RequisicionProducto) requisicionProductoRest.save(requisicionProducto).getSingle();
								} else {
									productosNoGuardados = productosNoGuardados + "||"
											+ requisicionProducto.getProducto().getClave() + "|| ";
								}
							}
						}

						String mensajeError = "";
						if (!productosNoGuardados.isEmpty()) {
							mensajeError = REQUISICIONVM_MESSAGE2_CONTENT1 + "\n" + productosNoGuardados + ". "
									+ REQUISICIONVM_MESSAGE2_CONTENT2;
						}

						String mensaje = REQUISICIONVM_MESSAGE3_CONTENT1 + " [" + requisicion.getFolio() + "] "
								+ REQUISICIONVM_MESSAGE3_CONTENT2;

						if (organizacion.getDevelopmentTool() != null) {
							List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
							if (privilegios != null) {
								enviarCorreos(usuario, organizacion, privilegios, mensaje,
										REQUISICIONVM_MESSAGE_EMAIL_SUBJECT_NUEVA_REQ, null);
								mensaje += " " + REQUISICIONVM_MESSAGE_EMAIL_MESSAGE_NUEVA_REQ;
							}

						} else
							mensaje += ". " + REQUISICIONVM_MESSAGE_EMAIL_FAIL_REQ;

						StockUtils.showSuccessmessage(mensaje + " " + mensajeError, "info", Integer.valueOf(0), null);
						limpiarFormulario();
					} else
						StockUtils.showSuccessmessage(validaListaProductos, "warning", Integer.valueOf(0), null);

				} else {
					String validaListaProductos = validarListaProductos();
					if (validaListaProductos.equals("")) {
						requisicion.setFecha(Calendar.getInstance());
						requisicion = (Requisicion) requisicionRest.save(requisicion).getSingle();

						String productosNoGuardados = "";
						for (int i = 0; i < requisicionProductos.size(); i++) {
							RequisicionProducto requisicionProducto = (RequisicionProducto) requisicionProductos.get(i);

							requisicionProducto.setRequisicion(requisicion);
							if ((requisicionProducto.getProducto() != null)
									&& (requisicionProducto.getProducto().getIdProducto() != null)) {
								requisicionProducto = (RequisicionProducto) requisicionProductoRest.save(requisicionProducto).getSingle();
							} else {
								List<Producto> p = (List<Producto>) productoRest
										.getByClaveNombre(requisicionProducto.getProducto().getClave(), organizacion).getSingle();
								if (p != null) {
									requisicionProducto.setProducto((Producto) p.get(0));
									requisicionProducto = (RequisicionProducto) requisicionProductoRest.save(requisicionProducto).getSingle();
								} else {
									productosNoGuardados = productosNoGuardados + "||"
											+ requisicionProducto.getProducto().getClave() + "|| ";
								}
							}
						}
						String mensajeError = "";
						if (!productosNoGuardados.isEmpty()) {
							mensajeError = REQUISICIONVM_MESSAGE2_CONTENT1 + "\n" + productosNoGuardados + ". "
									+ REQUISICIONVM_MESSAGE2_CONTENT2;
						}
						StockUtils.showSuccessmessage(
								REQUISICIONVM_MESSAGE3_CONTENT1 + " [" + requisicion.getFolio() + "] "
										+ REQUISICIONVM_MESSAGE3_CONTENT3 + " " + mensajeError,
								"info", Integer.valueOf(0), null);

						this.requisicion = new Requisicion();
						this.requisicionProductos = new ArrayList();
						addNewItemToBill();
					} else
						StockUtils.showSuccessmessage(validaListaProductos, "warning", Integer.valueOf(0), null);

				}
			}
		} else {
			StockUtils.showSuccessmessage(validacion, "warning", Integer.valueOf(0), null);
		}
	}

	private String validarCapturaRequisicion() {
		String mensaje = "";
		if (this.requisicion.getArea() != null) {
			
			/*
			if (this.requisicion.getCofiaProg() != null) {
				if (this.requisicion.getCofiaPy() != null) {
					if (this.requisicion.getCofiaFuenteFinanciamiento() != null) {
						
						*/
						
						
						if ((this.requisicion.getPersona() != null)
								&& (this.requisicion.getPersona().getNombre() != null)
								&& (!this.requisicion.getPersona().getNombre().isEmpty())
								&& (this.requisicion.getPersona().getApellidoPaterno() != null)
								&& (!this.requisicion.getPersona().getApellidoPaterno().isEmpty())
								&& (this.requisicion.getPersona().getApellidoMaterno() != null)
								&& (!this.requisicion.getPersona().getApellidoMaterno().isEmpty())) {
							if (this.requisicion.getPosicion() != null) {
								if (this.requisicion.getJustificacion() != null) {
									if ((this.requisicionProductos != null) && (this.requisicionProductos.size() > 0)) {
										boolean verificar = true;
										for (RequisicionProducto item : this.requisicionProductos) {
											if ((item.getProducto() == null) || (item.getCantidad() == null)) {
												verificar = false;
												break;
											}
										}
										if (!verificar) {
											mensaje = REQUISICIONVM_MESSAGE1_VALIDA_PRODUCTO;
										}
									} else {
										mensaje = REQUISICIONVM_MESSAGE1_VALIDA_PRODUCTO;
									}
								} else {
									mensaje = REQUISICIONVM_MESSAGE2_VALIDA_PRODUCTO;
								}
							} else {
								mensaje = REQUISICIONVM_MESSAGE3_VALIDA_PRODUCTO + " "
										+ requisicion.getPersona().getNombre() + " "
										+ requisicion.getPersona().getApellidoPaterno() + " "
										+ requisicion.getPersona().getApellidoMaterno() + " "
										+ REQUISICIONVM_MESSAGE4_VALIDA_PRODUCTO;
							}
						} else {
							mensaje = REQUISICIONVM_MESSAGE5_VALIDA_PRODUCTO;
							
							
						}
						
						/*
					} else {
						mensaje = REQUISICIONVM_MESSAGE6_VALIDA_PRODUCTO;
					}
				} else {
					mensaje = REQUISICIONVM_MESSAGE7_VALIDA_PRODUCTO;
				}
			} else {
				mensaje = REQUISICIONVM_MESSAGE8_VALIDA_PRODUCTO;
			}*/
		} else {
			mensaje = REQUISICIONVM_MESSAGE9_VALIDA_PRODUCTO;
		}
		return mensaje;
	}

	private String validarListaProductos() {
		String mensaje = "";
		for (RequisicionProducto item : requisicionProductos) {
			if (item.getProducto() == null) {
				mensaje = REQUISICIONVM_MESSAGE10_CONTENT1;
				break;
			}
			if (item.getCofiaPartidaGenerica() == null) {
				mensaje = REQUISICIONVM_MESSAGE11_CONTENT1 + " " + item.getProducto().getNombre() + " "
						+ REQUISICIONVM_MESSAGE11_CONTENT2;
				break;
			} else {
				if (item.getCofiaPartidaGenerica().getIdConffyaPartidaGenerica() == null) {
					mensaje = REQUISICIONVM_MESSAGE11_CONTENT1 + " " + item.getProducto().getNombre() + " "
							+ REQUISICIONVM_MESSAGE11_CONTENT2;
					break;
				}
			}
			if (item.getCantidad() == null) {
				mensaje = REQUISICIONVM_MESSAGE12_CONTENT1 + " " + item.getProducto().getNombre() + " "
						+ REQUISICIONVM_MESSAGE12_CONTENT2;
				break;
			}
		}
		return mensaje;
	}

	@NotifyChange({ "requisicionProductos", "itemsOnList", "importeTotal" })
	@Command
	public void removeElementFromBill() {
		if ((requisicionProductos != null) && (!requisicionProductos.isEmpty())
				&& (requisicionProductoSeleccionado != null)
				&& (requisicionProductos.contains(requisicionProductoSeleccionado))) {
			if (requisicion.getIdRequisicion() != null) {
				requisicionProductoRest.delete(requisicionProductoSeleccionado);
			}
			requisicionProductos.remove(requisicionProductoSeleccionado);

			itemsOnList = Integer.valueOf(requisicionProductos.size());
			updateTotal();
		}
	}

	private void updateTotal() {
		if (requisicionProductos != null) {
			Double total = Double.valueOf(0.0D);
			for (RequisicionProducto requisicionProducto : requisicionProductos) {
				if (requisicionProducto.getImporte() != null) {
					total = Double.valueOf(total.doubleValue() + requisicionProducto.getImporte().floatValue());
				}
			}
			importeTotal = stockUtils.formatCurrency(total);
		}
	}

	@Command
	public void search(@BindingParam("index") Integer index) {
		if (index != null) {
			requisicionProducto = requisicionProductos.get(index);
			if (requisicionProducto != null && requisicionProducto.getCofiaPartidaGenerica() != null
					&& (requisicionProducto.getCofiaPartidaGenerica().getNombre() != null
							&& !requisicionProducto.getCofiaPartidaGenerica().getNombre().isEmpty())) {
				Map<String, Object> inputParams = new HashMap();
				inputParams.put("updateCommandFromItemFinder", "updateRecordFromRequisitionWithSelectedItem");
				inputParams.put("catalogoProductos", productosDB);
				inputParams.put("partidaGenerica", requisicionProducto.getCofiaPartidaGenerica());
				Window productoModalView = this.stockUtils
						.createModelDialogWithParams("/modulos/productos/utils/buscarProducto.zul", inputParams);

				productoModalView.doModal();
				requisicionProductoSeleccionado = ((RequisicionProducto) this.requisicionProductos
						.get(index.intValue()));
			} else
				StockUtils.showSuccessmessage("Debe seleccionar la partida generica previamente", "warning", 0, null);

		}
	}

	@GlobalCommand
	@NotifyChange({ "*" })
	public void updateRecordFromRequisitionWithSelectedItem(
			@BindingParam("productoSeleccionado") Producto productoSeleccionado) {
		if (productoSeleccionado != null) {
			if (!verifyItemsInRequisition(productoSeleccionado)) {
				if (requisicionProductoSeleccionado == null) {
					requisicionProductoSeleccionado = new RequisicionProducto();
				}
				List<ProductoPrecios> precios = (List<ProductoPrecios>) productoPreciosRest
						.getByProductoOrderMostRecentDate(productoSeleccionado, organizacion).getSingle();
				if (precios != null) {
					productoSeleccionado.setPrecio(precios.get(0).getPrecioValue());
				}

				requisicionProductoSeleccionado.setProducto(productoSeleccionado);
				requisicionProductoSeleccionado.setDescripcion(productoSeleccionado.getNombre());

			} else {
				StockUtils.showSuccessmessage(REQUISICIONVM_MESSAGE13_PRODUCTO_DUPLICADO, "warning",
						Integer.valueOf(4000), null);
			}
		}
	}

	private boolean validateBill() {
		boolean continuar = true;
		for (RequisicionProducto requisicionProducto : this.requisicionProductos) {
			if (!verifyItemsInRequisition(requisicionProducto.getProducto())) {
				StockUtils.showSuccessmessage(
						REQUISICIONVM_MESSAGE13_PRODUCTO_DUPLICADO + requisicionProducto.getProducto().getClave(),
						"warning", Integer.valueOf(4000), null);

				continuar = true;
				break;
			}
		}
		return continuar;
	}

	private boolean verifyItemsInRequisition(Producto productoSeleccionado) {
		for (RequisicionProducto requisicionProducto : this.requisicionProductos) {
			if ((requisicionProducto.getProducto().getIdProducto() != null)
					&& (requisicionProducto.getProducto().getClave() != null) && (requisicionProducto.getProducto()
							.getClave().equalsIgnoreCase(productoSeleccionado.getClave()))) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void imprimirRequisicion() {

		if ((requisicion != null) && (requisicion.getIdRequisicion() != null)) {
			if (requisicionProductos == null)
				requisicionProductos = new ArrayList();

			if (requisicionProductos != null) {
				ReportesBuild reporteador = new ReportesBuild();

				Map<String, Object> inputParams = new HashMap();
				inputParams.put("executeNameMethod", "closeVisorPdf");
				inputParams.put("titulo", "Documento de requisicion " + requisicion.getFolio());
				inputParams.put("fuente",
						reporteador.genererRequisicionPdf(requisicion, requisicionProductos, organizacion));

				Window windowModalView = stockUtils.createModelDialogWithParams("/modulos/utilidades/visorPdf.zul",
						inputParams);
				windowModalView.doModal();

			} else
				StockUtils.showSuccessmessage(REQUISICIONVM_MESSAGE14_RESULTADO_VACIO_PDF, "error", Integer.valueOf(0),
						null);
		} else
			StockUtils.showSuccessmessage("Seleccioner una requisicion para visualizar la informacion",
					Clients.NOTIFICATION_TYPE_WARNING, 0, null);
	}

	public void closeVisorPdf() {

	}

	@Command
	@NotifyChange({ "*" })
	public void seleccionarOpcionBuscarArea() {
		this.requisicion.setBuscarRequisicion("");
	}

	@Command
	@NotifyChange({ "*" })
	public void selectedResultadoRequisiciones() {
		if (this.requisicionProductos == null) {
			this.requisicionProductos = new ArrayList();
		}
		requisicionProductos = (List<RequisicionProducto>) requisicionProductoRest.getByRequisicion(requisicion, organizacion).getSingle();
	}

	private List<Privilegios> getEmailsUsuariosCotizacion() {
		List<Privilegios> usuarios = (List<Privilegios>) privilegioRest.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR, organizacion).getSingle();

		return usuarios;
	}

	private List<String> generarInPrograma(List<ConffyaPresupuestoDesglosado> loopList) {
		List<String> list = new ArrayList<>();
		if (loopList != null) {
			for (ConffyaPresupuestoDesglosado item : loopList) {
				boolean salvar = true;

				if (item.getPP() != null) {
					for (String itemString : list) {
						if (itemString.equals(item.getPP())) {
							salvar = false;
							break;
						}
					}
				} else
					salvar = false;
				if (salvar)
					list.add(item.getPP());
			}
		}
		return list;
	}

	private List<String> generarInProyecto(List<ConffyaPresupuestoDesglosado> loopList) {
		List<String> list = new ArrayList<>();
		if (loopList != null) {
			for (ConffyaPresupuestoDesglosado item : loopList) {
				boolean salvar = true;

				if (item.getPYC() != null) {
					for (String itemString : list) {
						if (itemString.equals(item.getPYC())) {
							salvar = false;
							break;
						}
					}
				} else
					salvar = false;
				if (salvar)
					if (!item.getPYC().equals(""))
						list.add(item.getPYC());

			}
		}
		return list;
	}

	private List<String> generarInFuenteFinanciamiento(List<ConffyaPresupuestoDesglosado> loopList) {
		List<String> list = new ArrayList<>();
		if (loopList != null) {
			for (ConffyaPresupuestoDesglosado item : loopList) {
				boolean salvar = true;

				if (item.getFF1() != null) {
					for (String itemString : list) {
						if (itemString.equals(item.getFF1())) {
							salvar = false;
							break;
						}
					}
				} else
					salvar = false;
				if (salvar)
					if (!item.getFF1().equals(""))
						list.add(item.getFF1());
			}
		}
		return list;
	}
	
	
/*
	private List<String> generarInPartidaGenerica(List<ConffyaPresupuestoDesglosado> loopList) {
		List<String> list = new ArrayList<>();
		if (loopList != null) {
			for (ConffyaPresupuestoDesglosado item : loopList) {
				boolean salvar = true;

				if (item.getPA() != null) {
					for (String itemString : list) {
						String clave = String.valueOf(item.getPA());
						if (itemString.equals(clave)) {
							salvar = false;
							break;
						}
					}
				} else
					salvar = false;
				if (salvar)
					list.add(String.valueOf(item.getPA()));
			}
		}
		return list;
	}*/

	public void getAlmacenesArea() {
		requisicion.setCofiaProg(new ConffyaProg());
		requisicion.setCofiaPy(new ConffyaPy());
		requisicion.setCofiaFuenteFinanciamiento(new ConffyaFuenteFinanciamiento());

		cofiaProgs = new ArrayList<>();
		cofiaPys = new ArrayList<>();
		cofiaFuenteFinanciamientos = new ArrayList<>();
		cofiaPartidaGenericas = new ArrayList<>();

		almacenesList = (List<Almacen>) almacenRest.getByArea(requisicion.getArea()).getSingle();

		conffyaPresupuestoDesglosadoList = (List<ConffyaPresupuestoDesglosado>) conffyaPresupuestoDesglosadoRest
				.getByUr(requisicion.getArea().getClave()).getSingle();
		if (conffyaPresupuestoDesglosadoList != null) {
			List<String> inProgramas = generarInPrograma(conffyaPresupuestoDesglosadoList);
			/*
			 * List<String> inProyectos =
			 * generarInProyecto(conffyaPresupuestoDesglosadoList); List<String>
			 * inFuenteFInanciamiento =
			 * generarInFuenteFinanciamiento(conffyaPresupuestoDesglosadoList);
			 * List<String> inPartidaGenerica =
			 * generarInPartidaGenerica(conffyaPresupuestoDesglosadoList);
			 */
			if (inProgramas.size() > 0)
				cofiaProgs = (List<ConffyaProg>) conffyaProgRest.getByClaveIn(inProgramas, organizacion);
			/*
			 * if (inProyectos.size() > 0) cofiaPys =
			 * conffyaPyService.getByClaveIn(inProyectos); if
			 * (inFuenteFInanciamiento.size() > 0) cofiaFuenteFinanciamientos =
			 * conffyaFuenteFinanciamientoService.getByClaveIn(
			 * inFuenteFInanciamiento); if (inPartidaGenerica.size() > 0)
			 * cofiaPartidaGenericas =
			 * conffyaPartidaGenericaService.getByClaveIn(inPartidaGenerica);
			 */
		} else
			Messagebox.show(REQUISICIONVM_MESSAGE15_CONTENT1_ALMACEN_AREA + "\n\t " + requisicion.getArea().getClave()
					+ " " + requisicion.getArea().getNombre() + "\n\n" + REQUISICIONVM_MESSAGE15_CONTENT2_ALMACEN_AREA);

		try {
			Informacion_Empresa info = new Informacion_Empresa(organizacion.getSucursalId(), null,
					organizacion.getNumero(), organizacion.getEjercicio(), null, organizacion.getRfc());

		} catch (Exception e) {
		}
	}

	@Command
	@NotifyChange({ "requisicionProductos", "importeTotal" })
	public void calculaImporte(@BindingParam("index") Integer index) {
		requisicionProductoSeleccionado = requisicionProductos.get(index);

		if (requisicionProductoSeleccionado.getCantidad() != null) {
			float precio = ((List<ProductoPrecios>) productoPreciosRest.getByProductoOrderMostRecentDate(requisicionProductoSeleccionado.getProducto(), organizacion).getSingle()).get(0).getPrecioValue();
			requisicionProductoSeleccionado.setImporte(requisicionProductoSeleccionado.getCantidad() * precio);
			updateTotal();
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void openURSelectWindow() {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateAreaSelected");
		inputParams.put("catalogoAreas", areas);
		Window windowModalView = stockUtils.createModelDialogWithParams("/modulos/productos/utils/selectUr.zul",
				inputParams);

		windowModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "requisicion", "almacenesList", "cofiaProgs", "cofiaPys", "cofiaFuenteFinanciamientos",
			"cofiaPartidaGenericas" })
	public void updateAreaSelected(@BindingParam("areaSeleccionada") Area itemSeleccionado) {
		if (itemSeleccionado != null) {
			if (requisicion != null) {
				requisicion.setArea(itemSeleccionado);
				getAlmacenesArea();
			}

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void openConffyaProgSelectWindow() {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateConffyaProgSelected");
		inputParams.put("catalogoConffyaProg", cofiaProgs);
		inputParams.put("areaSeleccionada", requisicion.getArea());
		Window windowModalView = stockUtils.createModelDialogWithParams("/modulos/productos/utils/selectPrograma.zul",
				inputParams);

		windowModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "requisicion", "cofiaPys", "cofiaFuenteFinanciamientos", "cofiaPartidaGenericas" })
	public void updateConffyaProgSelected(@BindingParam("conffyaProgSeleccionada") ConffyaProg itemSeleccionado) {
		if (itemSeleccionado != null) {
			if (requisicion != null) {
				requisicion.setCofiaProg(itemSeleccionado);

				if (conffyaPresupuestoDesglosadoList != null) {
					List<String> inProyectos = generarInProyecto(conffyaPresupuestoDesglosadoList);

					if (inProyectos.size() > 0)
						cofiaPys = (List<ConffyaPy>) conffyaPyRest.getByClaveIn(inProyectos, organizacion).getSingle();
					else{
						List<String> inFuenteFInanciamiento = generarInFuenteFinanciamiento(
								conffyaPresupuestoDesglosadoList);
						if (inFuenteFInanciamiento.size() > 0)
							cofiaFuenteFinanciamientos = (List<ConffyaFuenteFinanciamiento>) conffyaFuenteFinanciamientoRest
									.getByClaveIn(inFuenteFInanciamiento, organizacion).getSingle();
					}
				}
			}

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void openConffyaPySelectWindow() {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateConffyaPySelected");
		inputParams.put("catalogoConffyaPy", cofiaPys);
		inputParams.put("areaSeleccionada", requisicion.getArea());
		inputParams.put("conffyaProgSeleccionado", requisicion.getCofiaProg());
		Window windowModalView = stockUtils.createModelDialogWithParams("/modulos/productos/utils/selectProyecto.zul",
				inputParams);

		windowModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "requisicion", "cofiaPys", "cofiaFuenteFinanciamientos", "cofiaPartidaGenericas" })
	public void updateConffyaPySelected(@BindingParam("conffyaPySeleccionado") ConffyaPy itemSeleccionado) {
		if (itemSeleccionado != null) {
			if (requisicion != null) {
				requisicion.setCofiaPy(itemSeleccionado);

				if (conffyaPresupuestoDesglosadoList != null) {
					List<String> inFuenteFInanciamiento = generarInFuenteFinanciamiento(
							conffyaPresupuestoDesglosadoList);
					if (inFuenteFInanciamiento.size() > 0)
						cofiaFuenteFinanciamientos = (List<ConffyaFuenteFinanciamiento>) conffyaFuenteFinanciamientoRest
								.getByClaveIn(inFuenteFInanciamiento, organizacion).getSingle();
				}
			}

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void openupdateConffyaFuenteFinanciamientoSelectedWindow() {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateConffyaFuenteFinanciamientoSelected");
		inputParams.put("catalogoConffyaFuenteFinanciamiento", cofiaFuenteFinanciamientos);
		inputParams.put("selectedArea", requisicion.getArea());
		inputParams.put("selectedConffyaProg", requisicion.getCofiaProg());
		inputParams.put("selectedConffyaPy", requisicion.getCofiaPy());
		Window windowModalView = stockUtils
				.createModelDialogWithParams("/modulos/productos/utils/selectFuenteFinanciamiento.zul", inputParams);

		windowModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "requisicion", "cofiaFuenteFinanciamientos", "cofiaPartidaGenericas" })
	public void updateConffyaFuenteFinanciamientoSelected(
			@BindingParam("conffyaFuenteFinanciamientoSeleccionado") ConffyaFuenteFinanciamiento itemSeleccionado) {
		if (itemSeleccionado != null) {
			if (requisicion != null) {
				requisicion.setCofiaFuenteFinanciamiento(itemSeleccionado);
				if (conffyaPresupuestoDesglosadoList != null)
					cofiaPartidaGenericas = correspondeAFiltro();
			}
		}
	}

	private List<ConffyaPartidaGenerica> correspondeAFiltro() {
		List<ConffyaPresupuestoDesglosado> desglosadoFiltrado = (List<ConffyaPresupuestoDesglosado>) conffyaPresupuestoDesglosadoRest
				.getPartidaGenericaFiltrado(requisicion.getArea().getClave(), requisicion.getCofiaProg().getClave(),
						requisicion.getCofiaPy().getClave(), requisicion.getCofiaFuenteFinanciamiento().getClave(), organizacion).getSingle();

		List<ConffyaPartidaGenerica> returnList = new ArrayList<>();
		if (desglosadoFiltrado != null && desglosadoFiltrado.size() > 0) {
			for (ConffyaPresupuestoDesglosado loop : desglosadoFiltrado) {
				for (ConffyaPartidaGenerica pgItem : catalogoPartidaGenericas) {
					if (pgItem.getClave() != null && loop.getPA() != null) {
						if (pgItem.getClave().equals(loop.getPA().toString())) {
							boolean add = true;
							for (ConffyaPartidaGenerica itemLoopreturn : returnList) {
								if (itemLoopreturn.getClave().equals(pgItem.getClave())) {
									add = false;
									break;
								}
							}
							if (add) {
								returnList.add(pgItem);
								break;
							}
						}
					}
				}
			}
		}
		return returnList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void openUpdateConffyaPartidaGenericaWindow(@BindingParam("index") Integer index) {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateConffyaPartidaGenericaSelected");
		inputParams.put("catalogoConffyaPartidaGenerica", cofiaPartidaGenericas);
		inputParams.put("selectedArea", requisicion.getArea());
		inputParams.put("selectedConffyaProg", requisicion.getCofiaProg());
		inputParams.put("selectedConffyaPy", requisicion.getCofiaPy());
		inputParams.put("selectedConffyaFuenteFinanciamiento", requisicion.getCofiaFuenteFinanciamiento());
		inputParams.put("indexProducto", index);
		Window windowModalView = stockUtils
				.createModelDialogWithParams("/modulos/productos/utils/selectPartidaGenerica.zul", inputParams);

		windowModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "requisicion", "requisicionProductos" })
	public void updateConffyaPartidaGenericaSelected(
			@BindingParam("conffyaPartidaGenericaSeleccionado") ConffyaPartidaGenerica itemSeleccionado,
			@BindingParam("indexProducto") Integer index) {
		if (itemSeleccionado != null) {
			if (requisicion != null) {
				List<ConffyaPresupuestoDesglosado> clavePresupuestaria = (List<ConffyaPresupuestoDesglosado>) conffyaPresupuestoDesglosadoRest
						.getPartidaGenericaFiltradoConPartidaGenerica(requisicion.getArea().getClave(), requisicion.getCofiaProg().getClave(), requisicion.getCofiaPy().getClave(), requisicion.getCofiaFuenteFinanciamiento().getClave(), itemSeleccionado.getClave(), organizacion).getSingle();
				if (clavePresupuestaria != null)
					requisicionProductos.get(index)
							.setClavePre(stockUtils.Encriptar(clavePresupuestaria.get(0).getClave()));
				requisicionProductos.get(index).setCofiaPartidaGenerica(itemSeleccionado);
			}

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void openJustificacionWindow(@BindingParam("index") Integer index) {
		Map<String, Object> inputParams = new HashMap();
		inputParams.put("updateCommandFromItemFinder", "updateJustificacionSelected");
		inputParams.put("catalogoJustificacion", justificaciones);
		Window windowModalView = stockUtils
				.createModelDialogWithParams("/modulos/productos/utils/selectJustificacion.zul", inputParams);

		windowModalView.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "requisicion" })
	public void updateJustificacionSelected(@BindingParam("justificacionSeleccionado") Justificacion itemSeleccionado) {
		if (itemSeleccionado != null)
			if (requisicion != null)
				requisicion.setJustificacion(itemSeleccionado.getNombre());
	}

	private void leerVariablesProperties() {
		Properties propiedades = getPropertiesFiles();
		REQUISICIONZUL_TITLE = propiedades.getProperty("requisicionZUL.title");// Requisiciones
																				// |
																				// Requisición:
		REQUISICIONZUL_TITLE_LISTAPRODUCTOS = propiedades.getProperty("requisicionZUL.title.listaProductos");// Lista
																												// de
																												// productos
		REQUISICIONZUL_TITLE_BANDEJA_REQUISICION = propiedades.getProperty("requisicionZUL.title.bandeja.requisicion");// Bandeja
																														// de
																														// requisiciones
		REQUISICIONZUL_LABEL_AREASOLICITANTE = propiedades.getProperty("requisicionZUL.label.areaSolicitante");// Área
																												// Solicitante
																												// (UR):
		REQUISICIONZUL_LABEL_PROGRAMA = propiedades.getProperty("requisicionZUL.label.programa");// Programa:
		REQUISICIONZUL_LABEL_PROYECTO = propiedades.getProperty("requisicionZUL.label.proyecto");// Proyecto:
		REQUISICIONZUL_LABEL_FUENTE_FINANCIAMIENTO = propiedades
				.getProperty("requisicionZUL.label.fuente.financiamiento");// Fuente
																			// de
																			// financiamiento
		REQUISICIONZUL_LABEL_PUESTO = propiedades.getProperty("requisicionZUL.label.puesto");// Puesto:
		REQUISICIONZUL_LABEL_JUSTIFICACION = propiedades.getProperty("requisicionZUL.label.justificacion");// Justificación:
		REQUISICIONZUL_LABEL_NUMERO_INVENTARIO = propiedades.getProperty("requisicionZUL.label.numero.inventario");// Número
																													// de
																													// inventario:
		REQUISICIONZUL_LABEL_PRODUCTOS_COUNT = propiedades.getProperty("requisicionZUL.label.productos.count");// Productos:
		REQUISICIONZUL_LABEL_CONTRATO = propiedades.getProperty("requisicionZUL.label.contrato");// Contrato:
		REQUISICIONZUL_LABEL_ALMACEN = propiedades.getProperty("requisicionZUL.label.almacen");// Almacén:
		REQUISICIONZUL_LABEL_CONSUMO = propiedades.getProperty("requisicionZUL.label.consumo");// Consumo:

		REQUISICIONZUL_TIP_REQUISICION_NUEVA = propiedades.getProperty("requisicionZUL.tip.requisicion.nueva");// Requisiciones
																												// nuevas
		REQUISICIONZUL_TIP_REQUISICION_CANCELADA = propiedades.getProperty("requisicionZUL.tip.requisicion.cancelada");// Requisiciones
																														// canceladas
		REQUISICIONZUL_TIP_REQUISICION_ACEPTADA = propiedades.getProperty("requisicionZUL.tip.requisicion.aceptada");// Requisiciones
																														// aceptadas
		REQUISICIONZUL_TIP_BUSCA_REQUISICION = propiedades.getProperty("requisicionZUL.tip.busca.requisicion");// Buscar
																												// requisición

		REQUISICIONZUL_TIP_COMMAND_REQUISICIONNUEVA = propiedades
				.getProperty("requisicionZUL.tip.command.requisicionNueva");// Nueva
																			// requisición
		REQUISICIONZUL_TIP_BUSCAR_AREA = propiedades.getProperty("requisicionZUL.tip.buscar.area");// Buscar
																									// Área
																									// (UR)
		REQUISICIONZUL_TIP_BUSCAR_PROGRAMA = propiedades.getProperty("requisicionZUL.tip.buscar.programa");// Buscar
																											// programa
		REQUISICIONZUL_TIP_BUSCAR_PROYECTO = propiedades.getProperty("requisicionZUL.tip.buscar.proyecto");// Buscar
																											// proyecto
		REQUISICIONZUL_TIP_BUSCAR_FUENTE_FINANCIAMIENTO = propiedades
				.getProperty("requisicionZUL.tip.buscar.fuente.financiamiento");// Buscar
																				// fuente
																				// de
																				// financiamiento
		REQUISICIONZUL_TIP_BUSCAR_JUSTIFICACION = propiedades.getProperty("requisicionZUL.tip.buscar.justificacion");// Buscar
																														// justificación
		REQUISICIONZUL_TIP_BUSCAR_PRODUCTOS = propiedades.getProperty("requisicionZUL.tip.buscar.productos");// Asistente
																												// para
																												// búsqueda
																												// de
																												// productos
		REQUISICIONZUL_TIP_BUSCAR_PARTIDA_GENERICA = propiedades
				.getProperty("requisicionZUL.tip.buscar.partida.generica");// Buscar
																			// partida
																			// genérica
		REQUISICIONZUL_TIP_PRODUCTOS_ENTREGADOS = propiedades.getProperty("requisicionZUL.tip.productos.entregados");// Productos
																														// entregados
		REQUISICIONZUL_TIP_ADD_PRODUCTO = propiedades.getProperty("requisicionZUL.tip.add.producto");// Agregar
																										// producto
																										// a
																										// la
																										// lista
		REQUISICIONZUL_TIP_REMOVE_PRODUCTO = propiedades.getProperty("requisicionZUL.tip.remove.producto");// Quitar
																											// producto
																											// de
																											// la
																											// lista
		REQUISICIONZUL_BUSQUEDA_VACIA = propiedades.getProperty("requisicionZUL.busqueda.vacia");
		REQUISICIONZUL_BANDEJA_VACIA = propiedades.getProperty("requisicionZUL.bandeja.vacia");

		REQUISICIONVM_MESSAGE1_CONTENT = propiedades.getProperty("requisicionVM.message1.content");
		REQUISICIONVM_MESSAGE2_CONTENT1 = propiedades.getProperty("requisicionVM.message2.content1");
		REQUISICIONVM_MESSAGE2_CONTENT2 = propiedades.getProperty("requisicionVM.message2.content2");
		REQUISICIONVM_MESSAGE3_CONTENT1 = propiedades.getProperty("requisicionVM.message3.content1");
		REQUISICIONVM_MESSAGE3_CONTENT2 = propiedades.getProperty("requisicionVM.message3.content2");
		REQUISICIONVM_MESSAGE3_CONTENT3 = propiedades.getProperty("requisicionVM.message3.content3");
		REQUISICIONVM_MESSAGE_EMAIL_SUBJECT_NUEVA_REQ = propiedades
				.getProperty("requisicionVM.message.email.subject.nueva.req");
		REQUISICIONVM_MESSAGE_EMAIL_MESSAGE_NUEVA_REQ = propiedades
				.getProperty("requisicionVM.message.email.message.nueva.req");
		REQUISICIONVM_MESSAGE_EMAIL_FAIL_REQ = propiedades.getProperty("requisicionVM.message.email.fail.req");
		REQUISICIONVM_MESSAGE1_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message1.valida.producto");
		REQUISICIONVM_MESSAGE2_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message2.valida.producto");
		REQUISICIONVM_MESSAGE3_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message3.valida.producto");
		REQUISICIONVM_MESSAGE4_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message4.valida.producto");
		REQUISICIONVM_MESSAGE5_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message5.valida.producto");
		REQUISICIONVM_MESSAGE6_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message6.valida.producto");
		REQUISICIONVM_MESSAGE7_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message7.valida.producto");
		REQUISICIONVM_MESSAGE8_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message8.valida.producto");
		REQUISICIONVM_MESSAGE9_VALIDA_PRODUCTO = propiedades.getProperty("requisicionVM.message9.valida.producto");
		REQUISICIONVM_MESSAGE10_CONTENT1 = propiedades.getProperty("requisicionVM.message10.content1");
		REQUISICIONVM_MESSAGE11_CONTENT1 = propiedades.getProperty("requisicionVM.message11.content1");
		REQUISICIONVM_MESSAGE11_CONTENT2 = propiedades.getProperty("requisicionVM.message11.content2");
		REQUISICIONVM_MESSAGE12_CONTENT1 = propiedades.getProperty("requisicionVM.message12.content1");
		REQUISICIONVM_MESSAGE12_CONTENT2 = propiedades.getProperty("requisicionVM.message12.content2");
		REQUISICIONVM_MESSAGE13_PRODUCTO_DUPLICADO = propiedades
				.getProperty("requisicionVM.message13.producto.duplicado");
		REQUISICIONVM_MESSAGE14_RESULTADO_VACIO_PDF = propiedades
				.getProperty("requisicionVM.message14.resultado.vacio.pdf");
		REQUISICIONVM_MESSAGE15_CONTENT1_ALMACEN_AREA = propiedades
				.getProperty("requisicionVM.message15.content1.almacen.area");
		REQUISICIONVM_MESSAGE15_CONTENT2_ALMACEN_AREA = propiedades
				.getProperty("requisicionVM.message15.content2.almacen.area");
		REQUISICIONZUL_TIP_REQUISICION_PENDIENTE = propiedades.getProperty("requisicionZUL.tip.requisicion.pendiente");// Requisiciones
																														// pendientes
		REQUISICIONZUL_LIST1 = propiedades.getProperty("requisicionZUL.list1");// en
																				// la
																				// requisición

		GLOBAL_INFO_MESSAGE1 = propiedades.getProperty("global.info.message1");
		GLOBAL_INFO_STATUS_NUEVO = propiedades.getProperty("global.info.status.nuevo");
		GLOBAL_INFO_STATUS_CANCELADA = propiedades.getProperty("global.info.status.cancelada");
		GLOBAL_INFO_STATUS_ACEPTADO = propiedades.getProperty("global.info.status.aceptado");
		GLOBAL_INFO_PDF_GENERADO = propiedades.getProperty("global.info.pdf.generado");
		GLOBAL_INFO_PDF_CONFIRMATION = propiedades.getProperty("global.info.pdf.confirmation");

		GENERICZUL_LABEL_BUSCADOR = propiedades.getProperty("genericZUL.label.buscador");
		GENERICZUL_LABEL_AREA_UR = propiedades.getProperty("genericZUL.label.area.ur");// Área
																						// (UR):
		GENERICZUL_COLUMN_AREA = propiedades.getProperty("genericZUL.column.area");
		GENERICZUL_COLUMN_FOLIO = propiedades.getProperty("genericZUL.column.folio");// Folio
		GENERICZUL_COLUMN_AREA = propiedades.getProperty("genericZUL.column.area");// Área
		GENERICZUL_TIP_GUARDAR_CAMBIOS = propiedades.getProperty("genericZUL.tip.guardar.cambios");// Guardar
																									// cambios
		GENERICZUL_TIP_REQUISICIONPDF = propiedades.getProperty("genericZUL.tip.requisicionPDF");// Exportar
																									// a
																									// PDF
		REQUISICIONZUL_TIP_SALVAR_CAMBIOS = propiedades.getProperty("requisicionZUL.tip.salvar.cambios");// Salvar
																											// cambios
																											// en
																											// requisicion
		GENERICZUL_TIP_BUSCADOR = propiedades.getProperty("genericZUL.tip.buscador");
		GENERICZUL_LABEL_FOLIO = propiedades.getProperty("genericZUL.label.folio");// Folio:
		GENERICZUL_LABEL_NOMBRE = propiedades.getProperty("genericZUL.label.nombre");// Nombre:
		GENERICZUL_LABEL_APATERNO_ABR = propiedades.getProperty("genericZUL.label.apaterno.abr");// A.
																									// paterno:
		GENERICZUL_LABEL_AMATERNO_ABR = propiedades.getProperty("genericZUL.label.amaterno.abr");// A.
																									// materno:
		GENERICZUL_LABEL_DESCRIPCION = propiedades.getProperty("genericZUL.label.descripcion");// Descripción:

		GENERICZUL_COLUMN_CLAVE = propiedades.getProperty("genericZUL.column.clave");// Clave
		GENERICZUL_COLUMN_CANTIDAD = propiedades.getProperty("genericZUL.column.cantidad");// Cantidad
		GENERICZUL_COLUMN_DESCRIPCION = propiedades.getProperty("genericZUL.column.descripcion");// Descripción
		GENERICZUL_COLUMN_IMPORTE = propiedades.getProperty("genericZUL.column.importe");// Importe
		GENERICZUL_COLUMN_PARTIDA_GENERICA = propiedades.getProperty("genericZUL.column.partida.generica");// Partida
																											// genérica
		GENERICZUL_LABEL_FECHA = propiedades.getProperty("genericZUL.label.fecha");// Fecha:
		GENERICZUL_LABEL_PROVEEDOR = propiedades.getProperty("genericZUL.label.proveedor");// Proveedor:
		GENERICZUL_LABEL_CLAVE = propiedades.getProperty("genericZUL.label.clave");// Clave:
		// @bind(rvm.)
	}

}
