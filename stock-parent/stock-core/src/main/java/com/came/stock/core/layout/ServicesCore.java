package com.came.stock.core.layout;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.came.stock.model.services.AlmacenEntradaService;
import com.came.stock.model.services.AlmacenService;
import com.came.stock.model.services.AreaService;
import com.came.stock.model.services.BancoService;
import com.came.stock.model.services.BannerService;
import com.came.stock.model.services.CalculosCostoService;
import com.came.stock.model.services.ClaveArmonizadaService;
import com.came.stock.model.services.CodigoBarrasProductoService;
import com.came.stock.model.services.ConffyaFuenteFinanciamientoService;
import com.came.stock.model.services.ConffyaPartidaGenericaService;
import com.came.stock.model.services.ConffyaPresupuestoDesglosadoService;
import com.came.stock.model.services.ConffyaProgService;
import com.came.stock.model.services.ConffyaPyService;
import com.came.stock.model.services.ConfiguracionCorreoService;
import com.came.stock.model.services.ContactoService;
import com.came.stock.model.services.ContratoService;
import com.came.stock.model.services.CostosProductoService;
import com.came.stock.model.services.CostosTiposService;
import com.came.stock.model.services.CotizacionInboxService;
import com.came.stock.model.services.CotizacionProductoService;
import com.came.stock.model.services.CotizacionService;
import com.came.stock.model.services.CuentasPagoService;
import com.came.stock.model.services.DestinoService;
import com.came.stock.model.services.DevelopmentToolService;
import com.came.stock.model.services.DireccionEntregaService;
import com.came.stock.model.services.DireccionService;
import com.came.stock.model.services.EmailService;
import com.came.stock.model.services.EstadoService;
import com.came.stock.model.services.EstatusRequisicionService;
import com.came.stock.model.services.FamiliasProductoService;
import com.came.stock.model.services.GiroService;
import com.came.stock.model.services.JustificacionService;
import com.came.stock.model.services.KardexProveedorService;
import com.came.stock.model.services.KardexService;
import com.came.stock.model.services.LabelsModulosService;
import com.came.stock.model.services.LugarService;
import com.came.stock.model.services.ModulosOrganizacionService;
import com.came.stock.model.services.ModulosService;
import com.came.stock.model.services.MonedaService;
import com.came.stock.model.services.MunicipioService;
import com.came.stock.model.services.OrdenCompraInboxService;
import com.came.stock.model.services.OrdenCompraProductoService;
import com.came.stock.model.services.OrdenCompraService;
import com.came.stock.model.services.OrganizacionService;
import com.came.stock.model.services.PaisService;
import com.came.stock.model.services.PartidaService;
import com.came.stock.model.services.PersonaService;
import com.came.stock.model.services.PosicionService;
import com.came.stock.model.services.PresentacionService;
import com.came.stock.model.services.PrivilegioService;
import com.came.stock.model.services.ProductoCostosService;
import com.came.stock.model.services.ProductoFactoresService;
import com.came.stock.model.services.ProductoMargenService;
import com.came.stock.model.services.ProductoNaturalezaService;
import com.came.stock.model.services.ProductoPreciosService;
import com.came.stock.model.services.ProductoService;
import com.came.stock.model.services.ProductoTipoService;
import com.came.stock.model.services.ProductoTipoSubFamiliaService;
import com.came.stock.model.services.ProductoTopeService;
import com.came.stock.model.services.ProveedorProductoService;
import com.came.stock.model.services.ProveedorService;
import com.came.stock.model.services.ProveedorUsuarioService;
import com.came.stock.model.services.ProyectoService;
import com.came.stock.model.services.RequisicionInboxService;
import com.came.stock.model.services.RequisicionPartidaService;
import com.came.stock.model.services.RequisicionProductoService;
import com.came.stock.model.services.RequisicionProveedorService;
import com.came.stock.model.services.RequisicionService;
import com.came.stock.model.services.TelefonoService;
import com.came.stock.model.services.UnidadService;
import com.came.stock.model.services.UsuarioService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ServicesCore {

	private static final Logger logger = Logger.getLogger(ServicesCore.class);
	
	protected Gson gson = new Gson();
	
	@Autowired
	protected AlmacenEntradaService almacenEntradaService;
	@Autowired
	protected AlmacenService almacenService;
	@Autowired
	protected AreaService areaService;
	@Autowired
	protected BancoService bancoService;
	@Autowired
	protected BannerService bannerService;
	@Autowired
	protected CalculosCostoService calculosCostoService;
	@Autowired
	protected ClaveArmonizadaService claveArmonizadaService;
	@Autowired
	protected CodigoBarrasProductoService codigoBarrasProductoService;
	@Autowired
	protected ConffyaFuenteFinanciamientoService conffyaFuenteFinanciamientoService;
	@Autowired
	protected ConffyaPartidaGenericaService conffyaPartidaGenericaService;
	@Autowired
	protected ConffyaPresupuestoDesglosadoService conffyaPresupuestoDesglosadoService;
	@Autowired
	protected ConffyaProgService conffyaProgService;
	@Autowired
	protected ConffyaPyService conffyaPyService;
	@Autowired
	protected ConfiguracionCorreoService configuracionCorreoService;
	@Autowired
	protected ContactoService contactoService;
	@Autowired
	protected ContratoService contratoService;
	@Autowired
	protected CostosProductoService costosProductoService;
	@Autowired
	protected CostosTiposService costosTiposService;
	@Autowired
	protected CotizacionInboxService cotizacionInboxService;
	@Autowired
	protected CotizacionProductoService cotizacionProductoService;
	@Autowired
	protected CotizacionService cotizacionService;
	@Autowired
	protected CuentasPagoService cuentasPagoService;
	@Autowired
	protected DestinoService destinoService;
	@Autowired
	protected DevelopmentToolService developmentToolService;
	@Autowired
	protected DireccionEntregaService direccionEntregaService;
	@Autowired
	protected DireccionService direccionService;
	@Autowired
	protected EmailService emailService;
	@Autowired
	protected EstadoService estadoService;
	@Autowired
	protected EstatusRequisicionService estatusRequisicionService;
	@Autowired
	protected FamiliasProductoService familiasProductoService;
	@Autowired
	protected GiroService giroService;
	@Autowired
	protected JustificacionService justificacionService;
	@Autowired
	protected KardexProveedorService kardexProveedorService;
	@Autowired
	protected KardexService kardexService;
	@Autowired
	protected LabelsModulosService labelsModulosService;
	@Autowired
	protected LugarService lugarService;
	@Autowired
	protected ModulosOrganizacionService modulosOrganizacionService;
	@Autowired
	protected ModulosService modulosService;
	@Autowired
	protected MonedaService monedaService;
	@Autowired
	protected MunicipioService municipioService;
	@Autowired
	protected OrdenCompraInboxService ordenCompraInboxService;
	@Autowired
	protected OrdenCompraProductoService ordenCompraProductoService;
	@Autowired
	protected OrdenCompraService ordenCompraService;
	@Autowired
	protected OrganizacionService organizacionService;
	@Autowired
	protected PaisService paisService;
	@Autowired
	protected PartidaService partidaService;
	@Autowired
	protected PersonaService personaService;
	@Autowired
	protected PosicionService posicionService;
	@Autowired
	protected PresentacionService presentacionService;
	@Autowired
	protected PrivilegioService privilegioService;
	@Autowired
	protected ProductoCostosService productoCostosService;
	@Autowired
	protected ProductoFactoresService productoFactoresService;
	@Autowired
	protected ProductoMargenService productoMargenService;
	@Autowired
	protected ProductoNaturalezaService productoNaturalezaService;
	@Autowired
	protected ProductoPreciosService productoPreciosService;
	@Autowired
	protected ProductoService productoService;
	@Autowired
	protected ProductoTipoService productoTipoService;
	@Autowired
	protected ProductoTipoSubFamiliaService productoTipoSubFamiliaService;
	@Autowired
	protected ProductoTopeService productoTopeService;
	@Autowired
	protected ProveedorProductoService proveedorProductoService;
	@Autowired
	protected ProveedorService proveedorService;
	@Autowired
	protected ProveedorUsuarioService proveedorUsuarioService;
	@Autowired
	protected ProyectoService proyectoService;
	@Autowired
	protected RequisicionInboxService requisicionInboxService;
	@Autowired
	protected RequisicionPartidaService requisicionPartidaService;
	@Autowired
	protected RequisicionProductoService requisicionProductoService;
	@Autowired
	protected RequisicionProveedorService requisicionProveedorService;
	@Autowired
	protected RequisicionService requisicionService;
	@Autowired
	protected TelefonoService telefonoService;
	@Autowired
	protected UnidadService unidadService;
	@Autowired
	protected UsuarioService usuarioService;
	
	
	public Map<String, Object> jsonToHashMap(String json) {
		Map<String, Object> map = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(json, Map.class);
		} catch (JsonParseException e) {
			logger.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return map;
	}
}
