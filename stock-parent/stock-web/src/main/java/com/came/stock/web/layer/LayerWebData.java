package com.came.stock.web.layer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.zul.Window;

import com.came.stock.beans.SistemaOperativo;
import com.came.stock.model.IteratorList;
import com.came.stock.model.domain.Almacen;
import com.came.stock.model.domain.AlmacenEntrada;
import com.came.stock.model.domain.Area;
import com.came.stock.model.domain.Banco;
import com.came.stock.model.domain.Banner;
import com.came.stock.model.domain.CalculosCosto;
import com.came.stock.model.domain.ClaveArmonizada;
import com.came.stock.model.domain.CodigoBarrasProducto;
import com.came.stock.model.domain.ConffyaFuenteFinanciamiento;
import com.came.stock.model.domain.ConffyaPartidaGenerica;
import com.came.stock.model.domain.ConffyaPresupuestoDesglosado;
import com.came.stock.model.domain.ConffyaProg;
import com.came.stock.model.domain.ConffyaPy;
import com.came.stock.model.domain.ConfiguracionCorreo;
import com.came.stock.model.domain.Contacto;
import com.came.stock.model.domain.Contrato;
import com.came.stock.model.domain.CostosProducto;
import com.came.stock.model.domain.CostosTipos;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.CuentaPago;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.FamiliasProducto;
import com.came.stock.model.domain.Giro;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Pais;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Posicion;
import com.came.stock.model.domain.Presentacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;
import com.came.stock.model.domain.ProductoFactores;
import com.came.stock.model.domain.ProductoMargen;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorProducto;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.Telefono;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.SessionUtils;
import com.came.stock.utilidades.StockUtilFile;
import com.came.stock.utilidades.StockUtilString;
import com.came.stock.utilidades.StockUtils;
import com.came.stock.web.services.confya.ServicioWebConffyaSoapStub;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class LayerWebData extends LayerWebServices implements Serializable {
	
	private static final long serialVersionUID = -828756372536148348L;

	@Autowired
	protected StockUtils stockUtils;
	@Autowired
	protected StockUtilFile stockUtilFile;
	@Autowired
	protected StockUtilString stockUtilString;
	@Autowired
	protected IteratorList iteratorList;
	@Autowired
	protected SessionUtils sessionUtils;
	
	protected Integer controlAcumulativoExistencia = 0;
	protected Float controlAcumulativoSaldo = 0f;
	protected JsonElement datosGlobalesJSON;;
	protected JsonParser parser;

	protected String keyJSon;
	protected JsonElement valueJSon;
	protected JsonObject obj;
	protected JsonArray array;
	protected JsonPrimitive valor;

	protected Direccion direccionJSon;
	protected int contadorCamposJson;
	protected int contadorCamposCodigoPostal;
	
	protected DevelopmentTool toolModalidadCalculo;
	protected boolean checkUeps;
	protected boolean checkPeps;
	protected boolean checkPromedio;
	
	
	protected Contacto contactoUsuario;
	protected Contacto contactoOrganizacion;
	protected Email emailUsuario;
	protected Email emailOrganizacion;
	protected Telefono telefonoUsuario;
	protected Telefono telefonoOrganizacion;
	protected Pais paisUsuario;
	protected Pais paisOrganizacion;
	protected Estado estadoUsuario;
	protected Estado estadoOrganizacion;
	protected Municipio municipioUsuario;
	protected Municipio municipioOrganizacion;
	protected Direccion direccionUsuario;
	protected Direccion direccionOrganizacion;
	protected Persona personaUsuario;
	protected Email emailDevelopment;
	protected Banner banner;
	protected Banner bannerNuevo;
	

	protected Banco bancoSeleccionado;
	protected Contacto contactoContacto;
	protected Contacto contactoProveedor;
	protected Cotizacion cotizacionSelected;
	protected Cotizacion cotizacion;
	protected Contrato contrato;
	protected CuentaPago cuentaPago;
	protected Direccion direccionProveedor;
	protected Email emailContacto;
	protected Email emailProveedor;
	protected Estado estadoProveedor;
	/*
	protected MenuButtonsActivated botonMuenu1;
	protected MenuButtonsActivated botonMuenu2;
	protected MenuButtonsActivated botonMuenu3;
	protected MenuButtonsActivated botonMuenu4;
	protected MenuButtonsActivated botonMuenu5;
	protected MenuButtonsActivated botonMuenu6;
	protected MenuButtonsActivated botonMuenu7;
	protected MenuButtonsActivated botonMuenu8;
	*/
	protected Moneda monedaSeleccionada;
	protected Municipio municipioProveedor;
	protected Pais pais;
	protected Proveedor buscarProveedor;
	protected Proveedor buscarProveedorAsociar;
	protected Proveedor nuevoProveedor;
	protected Pais paisProveedor;
	protected Persona personaContacto;
	protected Producto producto;
	protected Producto buscarProducto;
	protected ProductoTipo productoTipoSelected;
	protected Proveedor proveedorSelected;
	protected Proveedor proveedoresAsociacionSelected;
	protected Persona representanteLegal;
	protected Requisicion requisicion;
	protected ConffyaPartidaGenerica cofiaPartidaGenerica;
	protected ConffyaProg cofiaProg;
	protected ConffyaPy cofiaPy;
	protected ConffyaFuenteFinanciamiento cofiaFuenteFinanciamiento;
	protected ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado;
	protected Telefono telefonoContacto;
	protected Telefono telefonoProveedor;
	protected Usuarios usuarioProveedor;
	protected Unidad unidadSelected;
	protected ProveedorProducto proveedorProducto;
	protected RequisicionProducto requisicionProducto;
	protected Area area;
	protected Area areaBuscar;
	protected Unidad unidad;
	protected Presentacion presentacion;
	protected List<Presentacion> presentaciones;
	protected OrdenCompra ordenCompra;
	protected Posicion posicion;
	protected FamiliasProducto familiasProducto;
	protected ProductoNaturaleza productoNaturaleza;
	protected CodigoBarrasProducto codigoBarrasProducto;
	protected CostosProducto costosProducto;
	protected CostosProducto costosProductoNuevo;
	protected Giro giro;
	protected EstatusRequisicion estatusRequisicion;
	protected SistemaOperativo sistemaOperativo;
	protected ClaveArmonizada claveArmonizada;
	protected HSSFWorkbook libroHssf;
	protected XSSFWorkbook libroXssf;
	protected FileInputStream fileInputStream;
	protected FileOutputStream fileOutputStream;
	protected String readJasper;
	protected String readLayoutProductos;
	protected String readLayoutProveedores;
	protected JasperPrint print;
	protected JasperViewer jviewer;
	//protected AImage imagenProducto;
	protected ConfiguracionCorreo configuracionCorreo;
	protected List<ConfiguracionCorreo> configuracionCorreos;
	protected List<ConffyaFuenteFinanciamiento> cofiaFuenteFinanciamientos;
	protected List<ConffyaPartidaGenerica> cofiaPartidaGenericas;
	protected List<ConffyaPartidaGenerica> catalogoPartidaGenericas;
	protected List<ConffyaProg> cofiaProgs;
	protected List<ConffyaPy> cofiaPys;
	protected List<Cotizacion> cotizacionesList;
	protected List<Cotizacion> cotizacionesConProductos;
	protected List<CostosProducto> costosProductos;
	protected List<CodigoBarrasProducto> codigosBarrasProductos;
	protected List<FamiliasProducto> familiasProductos;
	protected List<ProductoTipo> productoTipoDB;
	protected List<ProductoTipo> productoTipoClasificaciones;
	protected List<Proveedor> proveedoresLista;
	protected List<Proveedor> proveedoresAsociacion;
	protected List<Banco> bancosDB;
	protected List<ProductoTipo> productoTipo;
	protected List<Moneda> monedasDB;
	protected List<ProveedorProducto> proveedorProductos;
	protected List<Unidad> unidadesDB;
	protected List<EstatusRequisicion> estatusRequisiciones;
	protected List<Contrato> contratos;
	protected List<Estado> estados;
	protected List<Pais> paises;
	protected List<Municipio> municipios;
	protected List<Producto> productosDB;
	protected List<RequisicionProducto> requisicionProductos;
	protected List<Requisicion> requisiciones;
	protected List<Area> areas;
	protected List<Posicion> posiciones;
	protected List<ProductoNaturaleza> productosNaturalezas;
	protected List<Giro> giros;
	protected List<OrdenCompra> ordenesCompras;
	protected List<ClaveArmonizada> claveArmonizadaList;
	protected List<Direccion> direccionesList;
	protected List<Almacen> almacenesList;
	protected List<ConffyaPresupuestoDesglosado> conffyaPresupuestoDesglosadoList;
	protected List<Kardex> kardexListInside;
	protected Almacen almacenSelected;
	protected AlmacenEntrada almacenEntrada;
	// protected List<AlmacenEntrada> almacenEntradaList;
	protected CalculosCosto calculosCosto;
	protected List<CalculosCosto> calculosCostoList;
	protected Usuarios usuario;
	protected List<Usuarios> usuarioList;
	protected Organizacion organizacion;
	protected List<Organizacion> organizaciones; 
	protected Kardex kardex;
	protected List<Kardex> kardexList;
	protected KardexProveedor kardexProveedor;
	protected List<KardexProveedor> kardexProveedorList;
	protected EstatusRequisicion estadoKardex;
	protected boolean visualizarControlesCame;
	protected boolean visibleContenido;
	protected boolean visibleConstruccion;
	protected ServicioWebConffyaSoapStub serviceWs;
	
	protected List<ProductoPrecios> productoPrecios;
	protected List<ProductoCostos> productoCostos;
	protected List<ProductoFactores> productoFactores;
	protected List<ProductoMargen> productoMargenes;
	protected ProductoCostos productoCosto;
	protected ProductoFactores productoFactor;
	protected ProductoMargen productoMargen;
	protected ProductoPrecios productoPrecio;
	protected List<CostosTipos> costosTiposLista;
	protected CostosTipos costosTipos;
	protected DevelopmentTool toolACtivacionProveedores;
	protected DevelopmentTool toolMensajeEncabezado;
	protected DevelopmentTool toolVersionSistema;
	protected boolean activarProveedorCheck;
	protected String encabezadoLogin;
	protected String versionSistema;
	//protected boolean bandBoxAreaOpen;
	//protected boolean bandBoxJustificacionOpen;
	protected boolean bandBoxEstadoOpen;
	protected boolean bandBoxProgOpen;
	protected boolean bandBoxProyectoOpen;
	protected boolean bandBoxFuenteFinanciamientoOpen;
	//protected boolean bandBoxPartidaGenericaOpen;
	protected DevelopmentTool developmentTool;
	protected List<DevelopmentTool> developmentToolList;
	protected List<Banner> bannerList;
	protected AImage bannerGlobalAImage;
	protected String footerNameOrg;
	protected String footerUrlOrg;
	protected String footerEmail;
	protected String footerTemefonoOrg;
	
	
	
	protected String user;
	protected String password;
	protected String proveedorUser;
	protected String proveedorContrasena;
	protected String proveedorContrasenaRepetir;
	protected String porcentajeWidth = "17%" ;
	protected String styleLoginContenedor;
	protected String styleLoginHeader;
	protected String styleHomeHeader;
	protected String styleLoginFooter;
	protected String styleInicioBackground;
	protected String styleGlobalColorFont;
	protected String styleGlobalLabelFont;
	protected String styleGlobalLabelTableContentFont;
	protected String styleGlobalLabelTableHeaderFont;
	protected String styleFontMenu;
	protected String styleFontSubMenu;
	protected String styleModuloFontTitle;
	
	
	protected Window mainWindow;
	
	
	protected List<Producto> listaProductosKardex;
	protected Producto productosKardexSelected;
	
	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Proveedor> getProveedoresLista() {
		return this.proveedoresLista;
	}

	public void setProveedoresLista(List<Proveedor> proveedoresLista) {
		this.proveedoresLista = proveedoresLista;
	}

	public Contrato getContrato() {
		return this.contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public Proveedor getProveedorSelected() {
		return this.proveedorSelected;
	}

	public void setProveedorSelected(Proveedor proveedorSelected) {
		this.proveedorSelected = proveedorSelected;
	}

	public Requisicion getRequisicion() {
		return this.requisicion;
	}

	public void setRequisicion(Requisicion requisicion) {
		this.requisicion = requisicion;
	}

	public Municipio getMunicipioProveedor() {
		return this.municipioProveedor;
	}

	public void setMunicipioProveedor(Municipio municipioProveedor) {
		this.municipioProveedor = municipioProveedor;
	}

	public Estado getEstadoProveedor() {
		return this.estadoProveedor;
	}

	public void setEstadoProveedor(Estado estadoProveedor) {
		this.estadoProveedor = estadoProveedor;
	}

	public Pais getPaisProveedor() {
		return this.paisProveedor;
	}

	public void setPaisProveedor(Pais paisProveedor) {
		this.paisProveedor = paisProveedor;
	}

	public Telefono getTelefonoProveedor() {
		return this.telefonoProveedor;
	}

	public void setTelefonoProveedor(Telefono telefonoProveedor) {
		this.telefonoProveedor = telefonoProveedor;
	}

	public Email getEmailProveedor() {
		return this.emailProveedor;
	}

	public void setEmailProveedor(Email emailProveedor) {
		this.emailProveedor = emailProveedor;
	}

	public Contacto getContactoProveedor() {
		return this.contactoProveedor;
	}

	public void setContactoProveedor(Contacto contactoProveedor) {
		this.contactoProveedor = contactoProveedor;
	}

	public Direccion getDireccionProveedor() {
		return this.direccionProveedor;
	}

	public void setDireccionProveedor(Direccion direccionProveedor) {
		this.direccionProveedor = direccionProveedor;
	}

	public Proveedor getNuevoProveedor() {
		return this.nuevoProveedor;
	}

	public void setNuevoProveedor(Proveedor nuevoProveedor) {
		this.nuevoProveedor = nuevoProveedor;
	}

	public Persona getRepresentanteLegal() {
		return this.representanteLegal;
	}

	public void setRepresentanteLegal(Persona representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	public Persona getPersonaContacto() {
		return this.personaContacto;
	}

	public void setPersonaContacto(Persona personaContacto) {
		this.personaContacto = personaContacto;
	}

	public Email getEmailContacto() {
		return this.emailContacto;
	}

	public void setEmailContacto(Email emailContacto) {
		this.emailContacto = emailContacto;
	}

	public Telefono getTelefonoContacto() {
		return this.telefonoContacto;
	}

	public void setTelefonoContacto(Telefono telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public Usuarios getUsuarioProveedor() {
		return this.usuarioProveedor;
	}

	public void setUsuarioProveedor(Usuarios usuarioProveedor) {
		this.usuarioProveedor = usuarioProveedor;
	}

	public Contacto getContactoContacto() {
		return this.contactoContacto;
	}

	public void setContactoContacto(Contacto contactoContacto) {
		this.contactoContacto = contactoContacto;
	}

	public List<ProductoTipo> getProductoTipoDB() {
		return this.productoTipoDB;
	}

	public void setProductoTipoDB(List<ProductoTipo> productoTipoDB) {
		this.productoTipoDB = productoTipoDB;
	}

	public ProductoTipo getProductoTipoSelected() {
		return this.productoTipoSelected;
	}

	public void setProductoTipoSelected(ProductoTipo productoTipoSelected) {
		this.productoTipoSelected = productoTipoSelected;
	}

	public Banco getBancoSeleccionado() {
		return this.bancoSeleccionado;
	}

	public void setBancoSeleccionado(Banco bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}

	public Moneda getMonedaSeleccionada() {
		return this.monedaSeleccionada;
	}

	public void setMonedaSeleccionada(Moneda monedaSeleccionada) {
		this.monedaSeleccionada = monedaSeleccionada;
	}

	public List<Banco> getBancosDB() {
		return bancosDB;
	}

	public void setBancosDB(List<Banco> bancosDB) {
		this.bancosDB = bancosDB;
	}

	public List<Moneda> getMonedasDB() {
		return this.monedasDB;
	}

	public void setMonedasDB(List<Moneda> monedasDB) {
		this.monedasDB = monedasDB;
	}

	public CuentaPago getCuentaPago() {
		return this.cuentaPago;
	}

	public void setCuentaPago(CuentaPago cuentaPago) {
		this.cuentaPago = cuentaPago;
	}

	public List<Contrato> getContratos() {
		return this.contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	public List<Estado> getEstados() {
		return this.estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}

	public List<Pais> getPaises() {
		return this.paises;
	}

	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}

	public List<Municipio> getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	public List<Producto> getProductosDB() {
		return this.productosDB;
	}

	public void setProductosDB(List<Producto> productosDB) {
		this.productosDB = productosDB;
	}

	public List<Proveedor> getProveedoresAsociacion() {
		return this.proveedoresAsociacion;
	}

	public void setProveedoresAsociacion(List<Proveedor> proveedoresAsociacion) {
		this.proveedoresAsociacion = proveedoresAsociacion;
	}

	public Proveedor getProveedoresAsociacionSelected() {
		return this.proveedoresAsociacionSelected;
	}

	public void setProveedoresAsociacionSelected(Proveedor proveedoresAsociacionSelected) {
		this.proveedoresAsociacionSelected = proveedoresAsociacionSelected;
	}

	public Proveedor getBuscarProveedor() {
		return this.buscarProveedor;
	}

	public void setBuscarProveedor(Proveedor buscarProveedor) {
		this.buscarProveedor = buscarProveedor;
	}

	public Producto getBuscarProducto() {
		return this.buscarProducto;
	}

	public void setBuscarProducto(Producto buscarProducto) {
		this.buscarProducto = buscarProducto;
	}

	public ProveedorProducto getProveedorProducto() {
		return this.proveedorProducto;
	}

	public void setProveedorProducto(ProveedorProducto proveedorProducto) {
		this.proveedorProducto = proveedorProducto;
	}

	public List<ProveedorProducto> getProveedorProductos() {
		return this.proveedorProductos;
	}

	public void setProveedorProductos(List<ProveedorProducto> proveedorProductos) {
		this.proveedorProductos = proveedorProductos;
	}

	public Proveedor getBuscarProveedorAsociar() {
		return this.buscarProveedorAsociar;
	}

	public void setBuscarProveedorAsociar(Proveedor buscarProveedorAsociar) {
		this.buscarProveedorAsociar = buscarProveedorAsociar;
	}

	/*
	public MenuButtonsActivated getBotonMuenu1() {
		return this.botonMuenu1;
	}

	public void setBotonMuenu1(MenuButtonsActivated botonMuenu1) {
		this.botonMuenu1 = botonMuenu1;
	}

	public MenuButtonsActivated getBotonMuenu2() {
		return this.botonMuenu2;
	}

	public void setBotonMuenu2(MenuButtonsActivated botonMuenu2) {
		this.botonMuenu2 = botonMuenu2;
	}

	public MenuButtonsActivated getBotonMuenu3() {
		return this.botonMuenu3;
	}

	public void setBotonMuenu3(MenuButtonsActivated botonMuenu3) {
		this.botonMuenu3 = botonMuenu3;
	}

	public MenuButtonsActivated getBotonMuenu4() {
		return this.botonMuenu4;
	}

	public void setBotonMuenu4(MenuButtonsActivated botonMuenu4) {
		this.botonMuenu4 = botonMuenu4;
	}

	public MenuButtonsActivated getBotonMuenu5() {
		return this.botonMuenu5;
	}

	public void setBotonMuenu5(MenuButtonsActivated botonMuenu5) {
		this.botonMuenu5 = botonMuenu5;
	}

	public MenuButtonsActivated getBotonMuenu6() {
		return this.botonMuenu6;
	}

	public void setBotonMuenu6(MenuButtonsActivated botonMuenu6) {
		this.botonMuenu6 = botonMuenu6;
	}

	public MenuButtonsActivated getBotonMuenu7() {
		return this.botonMuenu7;
	}

	public void setBotonMuenu7(MenuButtonsActivated botonMuenu7) {
		this.botonMuenu7 = botonMuenu7;
	}

	public MenuButtonsActivated getBotonMuenu8() {
		return this.botonMuenu8;
	}

	public void setBotonMuenu8(MenuButtonsActivated botonMuenu8) {
		this.botonMuenu8 = botonMuenu8;
	}
	*/
	public Area getArea() {
		return this.area;
	}	
	public void setArea(Area area) {
		this.area = area;
	}

	public Posicion getPosicion() {
		return this.posicion;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public List<Area> getAreas() {
		return this.areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

	public List<Posicion> getPosiciones() {
		return this.posiciones;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public String getReadJasper() {
		return this.readJasper;
	}

	public void setReadJasper(String readJasper) {
		this.readJasper = readJasper;
	}

	public List<ProductoTipo> getProductoTipo() {
		return this.productoTipo;
	}

	public void setProductoTipo(List<ProductoTipo> productoTipo) {
		this.productoTipo = productoTipo;
	}

	public JasperPrint getPrint() {
		return this.print;
	}

	public void setPrint(JasperPrint print) {
		this.print = print;
	}

	public JasperViewer getJviewer() {
		return this.jviewer;
	}

	public void setJviewer(JasperViewer jviewer) {
		this.jviewer = jviewer;
	}

	public List<RequisicionProducto> getRequisicionProductos() {
		return this.requisicionProductos;
	}

	public void setRequisicionProductos(List<RequisicionProducto> requisicionProductos) {
		this.requisicionProductos = requisicionProductos;
	}

	public RequisicionProducto getRequisicionProducto() {
		return this.requisicionProducto;
	}

	public void setRequisicionProducto(RequisicionProducto requisicionProducto) {
		this.requisicionProducto = requisicionProducto;
	}

	public List<Requisicion> getRequisiciones() {
		return this.requisiciones;
	}

	public void setRequisiciones(List<Requisicion> requisiciones) {
		this.requisiciones = requisiciones;
	}

	public Unidad getUnidadSelected() {
		return this.unidadSelected;
	}

	public void setUnidadSelected(Unidad unidadSelected) {
		this.unidadSelected = unidadSelected;
	}

	public List<Unidad> getUnidadesDB() {
		return this.unidadesDB;
	}

	public void setUnidadesDB(List<Unidad> unidadesDB) {
		this.unidadesDB = unidadesDB;
	}

	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public ProductoNaturaleza getProductoNaturaleza() {
		return this.productoNaturaleza;
	}

	public void setProductoNaturaleza(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturaleza = productoNaturaleza;
	}

	public List<ProductoNaturaleza> getProductosNaturalezas() {
		return this.productosNaturalezas;
	}

	public void setProductosNaturalezas(List<ProductoNaturaleza> productosNaturalezas) {
		this.productosNaturalezas = productosNaturalezas;
	}

	public FamiliasProducto getFamiliasProducto() {
		return this.familiasProducto;
	}

	@NotifyChange({ "producto" })
	public void setFamiliasProducto(FamiliasProducto familiasProducto) {
		if (familiasProducto != null) {
			this.producto = familiasProducto.getProducto();
		}
		this.familiasProducto = familiasProducto;
	}

	public List<FamiliasProducto> getFamiliasProductos() {
		return this.familiasProductos;
	}

	public void setFamiliasProductos(List<FamiliasProducto> familiasProductos) {
		this.familiasProductos = familiasProductos;
	}

	public CodigoBarrasProducto getCodigoBarrasProducto() {
		return this.codigoBarrasProducto;
	}

	public void setCodigoBarrasProducto(CodigoBarrasProducto codigoBarrasProducto) {
		this.codigoBarrasProducto = codigoBarrasProducto;
	}

	public List<CodigoBarrasProducto> getCodigosBarrasProductos() {
		return this.codigosBarrasProductos;
	}

	public void setCodigosBarrasProductos(List<CodigoBarrasProducto> codigosBarrasProductos) {
		this.codigosBarrasProductos = codigosBarrasProductos;
	}

	public CostosProducto getCostosProducto() {
		return this.costosProducto;
	}

	public void setCostosProducto(CostosProducto costosProducto) {
		this.costosProducto = costosProducto;
	}

	public List<CostosProducto> getCostosProductos() {
		return this.costosProductos;
	}

	public void setCostosProductos(List<CostosProducto> costosProductos) {
		this.costosProductos = costosProductos;
	}

	public CostosProducto getCostosProductoNuevo() {
		return this.costosProductoNuevo;
	}

	public void setCostosProductoNuevo(CostosProducto costosProductoNuevo) {
		this.costosProductoNuevo = costosProductoNuevo;
	}
	public Giro getGiro() {
		return this.giro;
	}

	public void setGiro(Giro giro) {
		this.giro = giro;
	}

	public List<Giro> getGiros() {
		return this.giros;
	}

	public void setGiros(List<Giro> giros) {
		this.giros = giros;
	}

	public ConffyaPartidaGenerica getCofiaPartidaGenerica() {
		return this.cofiaPartidaGenerica;
	}

	public void setCofiaPartidaGenerica(ConffyaPartidaGenerica cofiaPartidaGenerica) {
		this.cofiaPartidaGenerica = cofiaPartidaGenerica;
	}

	public ConffyaProg getCofiaProg() {
		return this.cofiaProg;
	}

	public void setCofiaProg(ConffyaProg cofiaProg) {
		this.cofiaProg = cofiaProg;
	}

	public ConffyaPy getCofiaPy() {
		return this.cofiaPy;
	}

	public void setCofiaPy(ConffyaPy cofiaPy) {
		this.cofiaPy = cofiaPy;
	}

	public List<ConffyaPartidaGenerica> getCofiaPartidaGenericas() {
		return this.cofiaPartidaGenericas;
	}

	public void setCofiaPartidaGenericas(List<ConffyaPartidaGenerica> cofiaPartidaGenericas) {
		this.cofiaPartidaGenericas = cofiaPartidaGenericas;
	}

	public List<ConffyaProg> getCofiaProgs() {
		return this.cofiaProgs;
	}

	public void setCofiaProgs(List<ConffyaProg> cofiaProgs) {
		this.cofiaProgs = cofiaProgs;
	}

	public List<ConffyaPy> getCofiaPys() {
		return this.cofiaPys;
	}

	public void setCofiaPys(List<ConffyaPy> cofiaPys) {
		this.cofiaPys = cofiaPys;
	}

	public ConffyaFuenteFinanciamiento getCofiaFuenteFinanciamiento() {
		return this.cofiaFuenteFinanciamiento;
	}

	public void setCofiaFuenteFinanciamiento(ConffyaFuenteFinanciamiento cofiaFuenteFinanciamiento) {
		this.cofiaFuenteFinanciamiento = cofiaFuenteFinanciamiento;
	}

	public List<ConffyaFuenteFinanciamiento> getCofiaFuenteFinanciamientos() {
		return this.cofiaFuenteFinanciamientos;
	}

	public void setCofiaFuenteFinanciamientos(List<ConffyaFuenteFinanciamiento> cofiaFuenteFinanciamientos) {
		this.cofiaFuenteFinanciamientos = cofiaFuenteFinanciamientos;
	}

	public Area getAreaBuscar() {
		return this.areaBuscar;
	}

	public void setAreaBuscar(Area areaBuscar) {
		this.areaBuscar = areaBuscar;
	}

	public Cotizacion getCotizacionSelected() {
		return this.cotizacionSelected;
	}

	public void setCotizacionSelected(Cotizacion cotizacionSelected) {
		this.cotizacionSelected = cotizacionSelected;
	}

	public List<Cotizacion> getCotizacionesList() {
		return this.cotizacionesList;
	}

	public void setCotizacionesList(List<Cotizacion> cotizacionesList) {
		this.cotizacionesList = cotizacionesList;
	}

	public OrdenCompra getOrdenCompra() {
		return this.ordenCompra;
	}

	public void setOrdenCompra(OrdenCompra ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public List<OrdenCompra> getOrdenesCompras() {
		return this.ordenesCompras;
	}

	public void setOrdenesCompras(List<OrdenCompra> ordenesCompras) {
		this.ordenesCompras = ordenesCompras;
	}

	public List<Cotizacion> getCotizacionesConProductos() {
		return this.cotizacionesConProductos;
	}

	public void setCotizacionesConProductos(List<Cotizacion> cotizacionesConProductos) {
		this.cotizacionesConProductos = cotizacionesConProductos;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public ClaveArmonizada getClaveArmonizada() {
		return this.claveArmonizada;
	}

	public void setClaveArmonizada(ClaveArmonizada claveArmonizada) {
		this.claveArmonizada = claveArmonizada;
	}

	public List<ClaveArmonizada> getClaveArmonizadaList() {
		return this.claveArmonizadaList;
	}

	public void setClaveArmonizadaList(List<ClaveArmonizada> claveArmonizadaList) {
		this.claveArmonizadaList = claveArmonizadaList;
	}

	public List<Direccion> getDireccionesList() {
		return direccionesList;
	}

	public void setDireccionesList(List<Direccion> direccionesList) {
		this.direccionesList = direccionesList;
	}

	public EstatusRequisicion getEstatusRequisicion() {
		return estatusRequisicion;
	}

	public void setEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}

	public SistemaOperativo getSistemaOperativo() {
		return sistemaOperativo;
	}

	public void setSistemaOperativo(SistemaOperativo sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}

	public HSSFWorkbook getLibro() {
		return libroHssf;
	}

	public void setLibro(HSSFWorkbook libro) {
		this.libroHssf = libro;
	}

	public FileInputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(FileInputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

	public void setFileOutputStream(FileOutputStream fileOutputStream) {
		this.fileOutputStream = fileOutputStream;
	}

	public String getReadLayoutProductos() {
		return readLayoutProductos;
	}

	public void setReadLayoutProductos(String readLayoutProductos) {
		this.readLayoutProductos = readLayoutProductos;
	}

	public String getReadLayoutProveedores() {
		return readLayoutProveedores;
	}

	public void setReadLayoutProveedores(String readLayoutProveedores) {
		this.readLayoutProveedores = readLayoutProveedores;
	}

	public List<EstatusRequisicion> getEstatusRequisiciones() {
		return estatusRequisiciones;
	}

	public void setEstatusRequisiciones(List<EstatusRequisicion> estatusRequisiciones) {
		this.estatusRequisiciones = estatusRequisiciones;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public List<Almacen> getAlmacenesList() {
		return almacenesList;
	}

	public void setAlmacenesList(List<Almacen> almacenesList) {
		this.almacenesList = almacenesList;
	}

	public Almacen getAlmacenSelected() {
		return almacenSelected;
	}

	public void setAlmacenSelected(Almacen almacenSelected) {
		this.almacenSelected = almacenSelected;
	}

	public AlmacenEntrada getAlmacenEntrada() {
		return almacenEntrada;
	}

	public void setAlmacenEntrada(AlmacenEntrada almacenEntrada) {
		this.almacenEntrada = almacenEntrada;
	}
	/*
	 * public List<AlmacenEntrada> getAlmacenEntradaList() { return
	 * almacenEntradaList; }
	 * 
	 * public void setAlmacenEntradaList(List<AlmacenEntrada>
	 * almacenEntradaList) { this.almacenEntradaList = almacenEntradaList; }
	 */

	public Cotizacion getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(Cotizacion cotizacion) {
		this.cotizacion = cotizacion;
	}

	public CalculosCosto getCalculosCosto() {
		return calculosCosto;
	}

	public void setCalculosCosto(CalculosCosto calculosCosto) {
		this.calculosCosto = calculosCosto;
	}

	public List<CalculosCosto> getCalculosCostoList() {
		return calculosCostoList;
	}

	public void setCalculosCostoList(List<CalculosCosto> calculosCostoList) {
		this.calculosCostoList = calculosCostoList;
	}

	public Kardex getKardex() {
		return kardex;
	}

	public void setKardex(Kardex kardex) {
		this.kardex = kardex;
	}

	public List<Kardex> getKardexList() {
		return kardexList;
	}

	public void setKardexList(List<Kardex> kardexList) {
		this.kardexList = kardexList;
	}

	public KardexProveedor getKardexProveedor() {
		return kardexProveedor;
	}

	public void setKardexProveedor(KardexProveedor kardexProveedor) {
		this.kardexProveedor = kardexProveedor;
	}

	public List<KardexProveedor> getKardexProveedorList() {
		return kardexProveedorList;
	}

	public void setKardexProveedorList(List<KardexProveedor> kardexProveedorList) {
		this.kardexProveedorList = kardexProveedorList;
	}

	public JsonElement getDatosGlobalesJSON() {
		return datosGlobalesJSON;
	}

	public void setDatosGlobalesJSON(JsonElement datosGlobalesJSON) {
		this.datosGlobalesJSON = datosGlobalesJSON;
	}

	public JsonParser getParser() {
		return parser;
	}

	public void setParser(JsonParser parser) {
		this.parser = parser;
	}

	public String getKeyJSon() {
		return keyJSon;
	}

	public void setKeyJSon(String keyJSon) {
		this.keyJSon = keyJSon;
	}

	public JsonElement getValueJSon() {
		return valueJSon;
	}

	public void setValueJSon(JsonElement valueJSon) {
		this.valueJSon = valueJSon;
	}

	public JsonObject getObj() {
		return obj;
	}

	public void setObj(JsonObject obj) {
		this.obj = obj;
	}

	public JsonArray getArray() {
		return array;
	}

	public void setArray(JsonArray array) {
		this.array = array;
	}

	public JsonPrimitive getValor() {
		return valor;
	}

	public void setValor(JsonPrimitive valor) {
		this.valor = valor;
	}

	public Direccion getDireccionJSon() {
		return direccionJSon;
	}

	public void setDireccionJSon(Direccion direccionJSon) {
		this.direccionJSon = direccionJSon;
	}

	public int getContadorCamposJson() {
		return contadorCamposJson;
	}

	public void setContadorCamposJson(int contadorCamposJson) {
		this.contadorCamposJson = contadorCamposJson;
	}

	public Contacto getContactoUsuario() {
		return contactoUsuario;
	}

	public void setContactoUsuario(Contacto contactoUsuario) {
		this.contactoUsuario = contactoUsuario;
	}

	public Contacto getContactoOrganizacion() {
		return contactoOrganizacion;
	}

	public void setContactoOrganizacion(Contacto contactoOrganizacion) {
		this.contactoOrganizacion = contactoOrganizacion;
	}

	public Email getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(Email emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Email getEmailOrganizacion() {
		return emailOrganizacion;
	}

	public void setEmailOrganizacion(Email emailOrganizacion) {
		this.emailOrganizacion = emailOrganizacion;
	}

	public Telefono getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(Telefono telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public Telefono getTelefonoOrganizacion() {
		return telefonoOrganizacion;
	}

	public void setTelefonoOrganizacion(Telefono telefonoOrganizacion) {
		this.telefonoOrganizacion = telefonoOrganizacion;
	}

	public Pais getPaisUsuario() {
		return paisUsuario;
	}

	public void setPaisUsuario(Pais paisUsuario) {
		this.paisUsuario = paisUsuario;
	}

	public Pais getPaisOrganizacion() {
		return paisOrganizacion;
	}

	public void setPaisOrganizacion(Pais paisOrganizacion) {
		this.paisOrganizacion = paisOrganizacion;
	}

	public Estado getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(Estado estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Estado getEstadoOrganizacion() {
		return estadoOrganizacion;
	}

	public void setEstadoOrganizacion(Estado estadoOrganizacion) {
		this.estadoOrganizacion = estadoOrganizacion;
	}

	public Municipio getMunicipioUsuario() {
		return municipioUsuario;
	}

	public void setMunicipioUsuario(Municipio municipioUsuario) {
		this.municipioUsuario = municipioUsuario;
	}

	public Municipio getMunicipioOrganizacion() {
		return municipioOrganizacion;
	}

	public void setMunicipioOrganizacion(Municipio municipioOrganizacion) {
		this.municipioOrganizacion = municipioOrganizacion;
	}

	public Direccion getDireccionUsuario() {
		return direccionUsuario;
	}

	public void setDireccionUsuario(Direccion direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}

	public Direccion getDireccionOrganizacion() {
		return direccionOrganizacion;
	}

	public void setDireccionOrganizacion(Direccion direccionOrganizacion) {
		this.direccionOrganizacion = direccionOrganizacion;
	}

	public Persona getPersonaUsuario() {
		return personaUsuario;
	}

	public void setPersonaUsuario(Persona personaUsuario) {
		this.personaUsuario = personaUsuario;
	}

	public Email getEmailDevelopment() {
		return emailDevelopment;
	}

	public void setEmailDevelopment(Email emailDevelopment) {
		this.emailDevelopment = emailDevelopment;
	}

	public EstatusRequisicion getEstadoKardex() {
		return estadoKardex;
	}

	public void setEstadoKardex(EstatusRequisicion estadoKardex) {
		this.estadoKardex = estadoKardex;
	}

	public List<Organizacion> getOrganizaciones() {
		return organizaciones;
	}

	public void setOrganizaciones(List<Organizacion> organizaciones) {
		this.organizaciones = organizaciones;
	}

	public boolean isVisualizarControlesCame() {
		return visualizarControlesCame;
	}

	public void setVisualizarControlesCame(boolean visualizarControlesCame) {
		this.visualizarControlesCame = visualizarControlesCame;
	}

	public List<ProductoTipo> getProductoTipoClasificaciones() {
		return productoTipoClasificaciones;
	}

	public void setProductoTipoClasificaciones(List<ProductoTipo> productoTipoClasificaciones) {
		this.productoTipoClasificaciones = productoTipoClasificaciones;
	}

	public ConffyaPresupuestoDesglosado getConffyaPresupuestoDesglosado() {
		return conffyaPresupuestoDesglosado;
	}

	public void setConffyaPresupuestoDesglosado(ConffyaPresupuestoDesglosado conffyaPresupuestoDesglosado) {
		this.conffyaPresupuestoDesglosado = conffyaPresupuestoDesglosado;
	}

	public List<ConffyaPresupuestoDesglosado> getConffyaPresupuestoDesglosadoList() {
		return conffyaPresupuestoDesglosadoList;
	}

	public void setConffyaPresupuestoDesglosadoList(List<ConffyaPresupuestoDesglosado> conffyaPresupuestoDesglosadoList) {
		this.conffyaPresupuestoDesglosadoList = conffyaPresupuestoDesglosadoList;
	}

	public List<ProductoPrecios> getProductoPrecios() {
		return productoPrecios;
	}

	public void setProductoPrecios(List<ProductoPrecios> productoPrecios) {
		this.productoPrecios = productoPrecios;
	}

	public List<ProductoCostos> getProductoCostos() {
		return productoCostos;
	}

	public void setProductoCostos(List<ProductoCostos> productoCostos) {
		this.productoCostos = productoCostos;
	}

	public List<ProductoFactores> getProductoFactores() {
		return productoFactores;
	}

	public void setProductoFactores(List<ProductoFactores> productoFactores) {
		this.productoFactores = productoFactores;
	}

	public List<ProductoMargen> getProductoMargenes() {
		return productoMargenes;
	}

	public void setProductoMargenes(List<ProductoMargen> productoMargenes) {
		this.productoMargenes = productoMargenes;
	}

	public ProductoCostos getProductoCosto() {
		return productoCosto;
	}

	public void setProductoCosto(ProductoCostos productoCosto) {
		this.productoCosto = productoCosto;
	}

	public ProductoFactores getProductoFactor() {
		return productoFactor;
	}

	public void setProductoFactor(ProductoFactores productoFactor) {
		this.productoFactor = productoFactor;
	}

	public ProductoMargen getProductoMargen() {
		return productoMargen;
	}

	public void setProductoMargen(ProductoMargen productoMargen) {
		this.productoMargen = productoMargen;
	}

	public ProductoPrecios getProductoPrecio() {
		return productoPrecio;
	}

	public void setProductoPrecio(ProductoPrecios productoPrecio) {
		this.productoPrecio = productoPrecio;
	}

	public List<CostosTipos> getCostosTiposLista() {
		return costosTiposLista;
	}

	public void setCostosTiposLista(List<CostosTipos> costosTiposLista) {
		this.costosTiposLista = costosTiposLista;
	}

	public CostosTipos getCostosTipos() {
		return costosTipos;
	}

	public void setCostosTipos(CostosTipos costosTipos) {
		this.costosTipos = costosTipos;
	}
/*
	public boolean isBandBoxAreaOpen() {
		return bandBoxAreaOpen;
	}

	public void setBandBoxAreaOpen(boolean bandBoxAreaOpen) {
		this.bandBoxAreaOpen = bandBoxAreaOpen;
	}
*/
	public boolean isBandBoxProgOpen() {
		return bandBoxProgOpen;
	}

	public void setBandBoxProgOpen(boolean bandBoxProgOpen) {
		this.bandBoxProgOpen = bandBoxProgOpen;
	}

	public boolean isBandBoxProyectoOpen() {
		return bandBoxProyectoOpen;
	}

	public void setBandBoxProyectoOpen(boolean bandBoxProyectoOpen) {
		this.bandBoxProyectoOpen = bandBoxProyectoOpen;
	}

	public boolean isBandBoxFuenteFinanciamientoOpen() {
		return bandBoxFuenteFinanciamientoOpen;
	}

	public void setBandBoxFuenteFinanciamientoOpen(boolean bandBoxFuenteFinanciamientoOpen) {
		this.bandBoxFuenteFinanciamientoOpen = bandBoxFuenteFinanciamientoOpen;
	}
/*
	public boolean isBandBoxPartidaGenericaOpen() {
		return bandBoxPartidaGenericaOpen;
	}

	public void setBandBoxPartidaGenericaOpen(boolean bandBoxPartidaGenericaOpen) {
		this.bandBoxPartidaGenericaOpen = bandBoxPartidaGenericaOpen;
	}
*/
	public boolean isBandBoxEstadoOpen() {
		return bandBoxEstadoOpen;
	}

	public void setBandBoxEstadoOpen(boolean bandBoxEstadoOpen) {
		this.bandBoxEstadoOpen = bandBoxEstadoOpen;
	}

	public DevelopmentTool getDevelopmentTool() {
		return developmentTool;
	}

	public void setDevelopmentTool(DevelopmentTool developmentTool) {
		this.developmentTool = developmentTool;
	}

	public List<DevelopmentTool> getDevelopmentToolList() {
		return developmentToolList;
	}

	public void setDevelopmentToolList(List<DevelopmentTool> developmentToolList) {
		this.developmentToolList = developmentToolList;
	}

	public Banner getBanner() {
		return banner;
	}

	public void setBanner(Banner banner) {
		this.banner = banner;
	}

	public List<Banner> getBannerList() {
		return bannerList;
	}

	public void setBannerList(List<Banner> bannerList) {
		this.bannerList = bannerList;
	}

	public Banner getBannerNuevo() {
		return bannerNuevo;
	}

	public void setBannerNuevo(Banner bannerNuevo) {
		this.bannerNuevo = bannerNuevo;
	}

	public AImage getBannerGlobalAImage() {
		return bannerGlobalAImage;
	}

	public void setBannerGlobalAImage(AImage bannerGlobalAImage) {
		this.bannerGlobalAImage = bannerGlobalAImage;
	}

	public ConfiguracionCorreo getConfiguracionCorreo() {
		return configuracionCorreo;
	}

	public void setConfiguracionCorreo(ConfiguracionCorreo configuracionCorreo) {
		this.configuracionCorreo = configuracionCorreo;
	}

	public List<ConfiguracionCorreo> getConfiguracionCorreos() {
		return configuracionCorreos;
	}

	public void setConfiguracionCorreos(List<ConfiguracionCorreo> configuracionCorreos) {
		this.configuracionCorreos = configuracionCorreos;
	}

	public String getFooterNameOrg() {
		return footerNameOrg;
	}

	public void setFooterNameOrg(String footerNameOrg) {
		this.footerNameOrg = footerNameOrg;
	}

	public String getFooterUrlOrg() {
		return footerUrlOrg;
	}

	public void setFooterUrlOrg(String footerUrlOrg) {
		this.footerUrlOrg = footerUrlOrg;
	}

	public String getFooterEmail() {
		return footerEmail;
	}

	public void setFooterEmail(String footerEmail) {
		this.footerEmail = footerEmail;
	}

	public String getFooterTemefonoOrg() {
		return footerTemefonoOrg;
	}

	public void setFooterTemefonoOrg(String footerTemefonoOrg) {
		this.footerTemefonoOrg = footerTemefonoOrg;
	}

	public boolean isCheckUeps() {
		return checkUeps;
	}

	public void setCheckUeps(boolean checkUeps) {
		this.checkUeps = checkUeps;
	}

	public boolean isCheckPeps() {
		return checkPeps;
	}

	public void setCheckPeps(boolean checkPeps) {
		this.checkPeps = checkPeps;
	}

	public boolean isCheckPromedio() {
		return checkPromedio;
	}

	public void setCheckPromedio(boolean checkPromedio) {
		this.checkPromedio = checkPromedio;
	}

	public List<Kardex> getKardexListInside() {
		return kardexListInside;
	}

	public void setKardexListInside(List<Kardex> kardexListInside) {
		this.kardexListInside = kardexListInside;
	}

	public boolean isVisibleContenido() {
		return visibleContenido;
	}

	public void setVisibleContenido(boolean visibleContenido) {
		this.visibleContenido = visibleContenido;
	}

	public boolean isVisibleConstruccion() {
		return visibleConstruccion;
	}

	public void setVisibleConstruccion(boolean visibleConstruccion) {
		this.visibleConstruccion = visibleConstruccion;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getProveedorUser() {
		return proveedorUser;
	}

	public void setProveedorUser(String proveedorUser) {
		this.proveedorUser = proveedorUser;
	}

	public String getProveedorContrasena() {
		return proveedorContrasena;
	}

	public void setProveedorContrasena(String proveedorContrasena) {
		this.proveedorContrasena = proveedorContrasena;
	}

	public String getProveedorContrasenaRepetir() {
		return proveedorContrasenaRepetir;
	}

	public void setProveedorContrasenaRepetir(String proveedorContrasenaRepetir) {
		this.proveedorContrasenaRepetir = proveedorContrasenaRepetir;
	}

	public String getPorcentajeWidth() {
		return porcentajeWidth;
	}

	public void setPorcentajeWidth(String porcentajeWidth) {
		this.porcentajeWidth = porcentajeWidth;
	}

	public List<Producto> getListaProductosKardex() {
		return listaProductosKardex;
	}

	public void setListaProductosKardex(List<Producto> listaProductosKardex) {
		this.listaProductosKardex = listaProductosKardex;
	}

	public Producto getProductosKardexSelected() {
		return productosKardexSelected;
	}

	public void setProductosKardexSelected(Producto productosKardexSelected) {
		this.productosKardexSelected = productosKardexSelected;
	}

	public List<Usuarios> getUsuarioList() {
		return usuarioList;
	}

	public void setUsuarioList(List<Usuarios> usuarioList) {
		this.usuarioList = usuarioList;
	}

	public List<ConffyaPartidaGenerica> getCatalogoPartidaGenericas() {
		return catalogoPartidaGenericas;
	}

	public void setCatalogoPartidaGenericas(List<ConffyaPartidaGenerica> catalogoPartidaGenericas) {
		this.catalogoPartidaGenericas = catalogoPartidaGenericas;
	}

	public boolean isActivarProveedorCheck() {
		return activarProveedorCheck;
	}

	public void setActivarProveedorCheck(boolean activarProveedorCheck) {
		this.activarProveedorCheck = activarProveedorCheck;
	}

	public String getEncabezadoLogin() {
		return encabezadoLogin;
	}

	public void setEncabezadoLogin(String encabezadoLogin) {
		this.encabezadoLogin = encabezadoLogin;
	}

	public DevelopmentTool getToolVersionSistema() {
		return toolVersionSistema;
	}

	public void setToolVersionSistema(DevelopmentTool toolVersionSistema) {
		this.toolVersionSistema = toolVersionSistema;
	}

	public String getVersionSistema() {
		return versionSistema;
	}

	public void setVersionSistema(String versionSistema) {
		this.versionSistema = versionSistema;
	}

	public String getStyleLoginContenedor() {
		return styleLoginContenedor;
	}

	public String getStyleLoginHeader() {
		return styleLoginHeader;
	}

	public String getStyleLoginFooter() {
		return styleLoginFooter;
	}

	public String getStyleHomeHeader() {
		return styleHomeHeader;
	}

	public String getStyleInicioBackground() {
		return styleInicioBackground;
	}

	public String getStyleGlobalColorFont() {
		return styleGlobalColorFont;
	}

	public String getStyleFontMenu() {
		return styleFontMenu;
	}

	public String getStyleFontSubMenu() {
		return styleFontSubMenu;
	}

	public String getStyleModuloFontTitle() {
		return styleModuloFontTitle;
	}

	public String getStyleGlobalLabelFont() {
		return styleGlobalLabelFont;

	}

	public String getStyleGlobalLabelTableContentFont() {
		return styleGlobalLabelTableContentFont;
	}

	public String getStyleGlobalLabelTableHeaderFont() {
		return styleGlobalLabelTableHeaderFont;
	}

	public Presentacion getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(Presentacion presentacion) {
		this.presentacion = presentacion;
	}

	public List<Presentacion> getPresentaciones() {
		return presentaciones;
	}

	public void setPresentaciones(List<Presentacion> presentaciones) {
		this.presentaciones = presentaciones;
	}

	
	
}
