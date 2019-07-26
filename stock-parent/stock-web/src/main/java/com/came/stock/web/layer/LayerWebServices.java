package com.came.stock.web.layer;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.came.stock.web.services.AlmacenEntradaRest;
import com.came.stock.web.services.AlmacenRest;
import com.came.stock.web.services.AreaRest;
import com.came.stock.web.services.BancoRest;
import com.came.stock.web.services.BannerRest;
import com.came.stock.web.services.CalculosCostoRest;
import com.came.stock.web.services.ClaveArmonizadaRest;
import com.came.stock.web.services.CodigoBarrasProductoRest;
import com.came.stock.web.services.ConffyaFuenteFinanciamientoRest;
import com.came.stock.web.services.ConffyaPartidaGenericaRest;
import com.came.stock.web.services.ConffyaPresupuestoDesglosadoRest;
import com.came.stock.web.services.ConffyaProgRest;
import com.came.stock.web.services.ConffyaPyRest;
import com.came.stock.web.services.ConfiguracionCorreoRest;
import com.came.stock.web.services.ContactoRest;
import com.came.stock.web.services.ContratoRest;
import com.came.stock.web.services.CostosProductoRest;
import com.came.stock.web.services.CostosTiposRest;
import com.came.stock.web.services.CotizacionInboxRest;
import com.came.stock.web.services.CotizacionProductoRest;
import com.came.stock.web.services.CotizacionRest;
import com.came.stock.web.services.CuentasPagoRest;
import com.came.stock.web.services.DestinoRest;
import com.came.stock.web.services.DevelopmentToolRest;
import com.came.stock.web.services.DireccionEntregaRest;
import com.came.stock.web.services.DireccionRest;
import com.came.stock.web.services.EmailRest;
import com.came.stock.web.services.EstadoRest;
import com.came.stock.web.services.EstatusRequisicionRest;
import com.came.stock.web.services.FamiliasProductoRest;
import com.came.stock.web.services.GiroRest;
import com.came.stock.web.services.JustificacionRest;
import com.came.stock.web.services.KardexProveedorRest;
import com.came.stock.web.services.KardexRest;
import com.came.stock.web.services.LabelsModulosRest;
import com.came.stock.web.services.LugarRest;
import com.came.stock.web.services.ModulosOrganizacionRest;
import com.came.stock.web.services.ModulosRest;
import com.came.stock.web.services.MonedaRest;
import com.came.stock.web.services.MunicipioRest;
import com.came.stock.web.services.OrdenCompraInboxRest;
import com.came.stock.web.services.OrdenCompraProductoRest;
import com.came.stock.web.services.OrdenCompraRest;
import com.came.stock.web.services.OrganizacionRest;
import com.came.stock.web.services.PaisRest;
import com.came.stock.web.services.PartidaRest;
import com.came.stock.web.services.PersonaRest;
import com.came.stock.web.services.PosicionRest;
import com.came.stock.web.services.PresentacionRest;
import com.came.stock.web.services.PrivilegioRest;
import com.came.stock.web.services.ProductoCostosRest;
import com.came.stock.web.services.ProductoFactoresRest;
import com.came.stock.web.services.ProductoMargenRest;
import com.came.stock.web.services.ProductoNaturalezaRest;
import com.came.stock.web.services.ProductoPreciosRest;
import com.came.stock.web.services.ProductoRest;
import com.came.stock.web.services.ProductoTipoRest;
import com.came.stock.web.services.ProductoTipoSubFamiliaRest;
import com.came.stock.web.services.ProductoTopeRest;
import com.came.stock.web.services.ProveedorProductoRest;
import com.came.stock.web.services.ProveedorRest;
import com.came.stock.web.services.ProveedorUsuarioRest;
import com.came.stock.web.services.ProyectoRest;
import com.came.stock.web.services.RequisicionInboxRest;
import com.came.stock.web.services.RequisicionPartidaRest;
import com.came.stock.web.services.RequisicionProductoRest;
import com.came.stock.web.services.RequisicionProveedorRest;
import com.came.stock.web.services.RequisicionRest;
import com.came.stock.web.services.RestMetaclas;
import com.came.stock.web.services.TelefonoRest;
import com.came.stock.web.services.UnidadRest;
import com.came.stock.web.services.UsuarioRest;

public class LayerWebServices extends LayerWebTag implements Serializable {
	
	private static final long serialVersionUID = 1927215250713591705L;

	@Autowired
	protected AlmacenEntradaRest almacenEntradaRest;
	@Autowired
	protected AlmacenRest almacenRest;
	@Autowired
	protected AreaRest areaRest;
	@Autowired
	protected BancoRest bancoRest;
	@Autowired
	protected BannerRest bannerRest;
	@Autowired
	protected CalculosCostoRest calculosCostoRest;
	@Autowired
	protected ClaveArmonizadaRest claveArmonizadaRest;
	@Autowired
	protected CodigoBarrasProductoRest codigoBarrasProductoRest;
	@Autowired
	protected ConffyaFuenteFinanciamientoRest conffyaFuenteFinanciamientoRest;
	@Autowired
	protected ConffyaPartidaGenericaRest conffyaPartidaGenericaRest;
	@Autowired
	protected ConffyaPresupuestoDesglosadoRest conffyaPresupuestoDesglosadoRest;
	@Autowired
	protected ConffyaProgRest conffyaProgRest;
	@Autowired
	protected ConffyaPyRest conffyaPyRest;
	@Autowired
	protected ConfiguracionCorreoRest configuracionCorreoRest;
	@Autowired
	protected ContactoRest contactoRest;
	@Autowired
	protected ContratoRest contratoRest;
	@Autowired
	protected CostosProductoRest costosProductoRest;;
	@Autowired
	protected CostosTiposRest costosTiposRest;
	@Autowired
	protected CotizacionInboxRest cotizacionInboxRest;
	@Autowired
	protected CotizacionProductoRest cotizacionProductoRest;
	@Autowired
	protected CotizacionRest cotizacionRest;
	@Autowired
	protected CuentasPagoRest cuentasPagoRest;
	@Autowired
	protected DestinoRest destinoRest;
	@Autowired
	protected DevelopmentToolRest developmentToolRest;
	@Autowired
	protected DireccionEntregaRest direccionEntregaRest;
	@Autowired
	protected DireccionRest direccionRest;
	@Autowired
	protected EmailRest emailRest;
	@Autowired
	protected EstadoRest estadoRest;
	@Autowired
	protected EstatusRequisicionRest estatusRequisicionRest;
	@Autowired
	protected FamiliasProductoRest familiasProductoRest;
	@Autowired
	protected GiroRest giroRest;
	@Autowired
	protected JustificacionRest justificacionRest;
	@Autowired
	protected KardexProveedorRest kardexProveedorRest;
	@Autowired
	protected KardexRest kardexRest;
	@Autowired
	protected LabelsModulosRest labelsModulosRest;
	@Autowired
	protected LugarRest lugarRest;
	@Autowired
	protected ModulosOrganizacionRest modulosOrganizacionRest;
	@Autowired
	protected ModulosRest modulosRest;
	@Autowired
	protected MonedaRest monedaRest;
	@Autowired
	protected MunicipioRest municipioRest;
	@Autowired
	protected OrdenCompraInboxRest ordenCompraInboxRest;
	@Autowired
	protected OrdenCompraProductoRest ordenCompraProductoRest;
	@Autowired
	protected OrdenCompraRest ordenCompraRest;
	@Autowired
	protected OrganizacionRest organizacionRest;
	@Autowired
	protected PaisRest paisRest;
	@Autowired
	protected PartidaRest partidaRest;
	@Autowired
	protected PersonaRest personaRest;
	@Autowired
	protected PosicionRest posicionRest;
	@Autowired
	protected PresentacionRest presentacionRest;
	@Autowired
	protected PrivilegioRest privilegioRest;
	@Autowired
	protected ProductoCostosRest productoCostosRest;
	@Autowired
	protected ProductoFactoresRest productoFactoresRest;
	@Autowired
	protected ProductoMargenRest productoMargenRest;
	@Autowired
	protected ProductoNaturalezaRest productoNaturalezaRest;
	@Autowired
	protected ProductoPreciosRest productoPreciosRest;
	@Autowired
	protected ProductoRest productoRest;
	@Autowired
	protected ProductoTipoRest productoTipoRest;
	@Autowired
	protected ProductoTipoSubFamiliaRest productoTipoSubFamiliaRest;
	@Autowired
	protected ProductoTopeRest productoTopeRest;
	@Autowired
	protected ProveedorProductoRest proveedorProductoRest;
	@Autowired
	protected ProveedorRest proveedorRest;
	@Autowired
	protected ProveedorUsuarioRest proveedorUsuarioRest;
	@Autowired
	protected ProyectoRest proyectoRest;
	@Autowired
	protected RequisicionInboxRest requisicionInboxRest;
	@Autowired
	protected RequisicionPartidaRest requisicionPartidaRest;
	@Autowired
	protected RequisicionProductoRest requisicionProductoRest;
	@Autowired
	protected RequisicionProveedorRest requisicionProveedorRest;
	@Autowired
	protected RequisicionRest requisicionRest;
	@Autowired
	protected RestMetaclas restMetaclas;
	@Autowired
	protected TelefonoRest telefonoRest;
	@Autowired
	protected UnidadRest unidadRest;
	@Autowired
	protected UsuarioRest usuarioRest;
}
