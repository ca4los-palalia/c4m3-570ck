package com.came.stock.web.layer;

import java.io.Serializable;

import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.select.annotation.WireVariable;

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

@Repository
public abstract class LayerWebServices extends LayerWebTag implements Serializable {
	
	private static final long serialVersionUID = 1927215250713591705L;

	@WireVariable
	protected AlmacenEntradaRest almacenEntradaRest;
	@WireVariable
	protected AlmacenRest almacenRest;
	@WireVariable
	protected AreaRest areaRest;
	@WireVariable
	protected BancoRest bancoRest;
	@WireVariable
	protected BannerRest bannerRest;
	@WireVariable
	protected CalculosCostoRest calculosCostoRest;
	@WireVariable
	protected ClaveArmonizadaRest claveArmonizadaRest;
	@WireVariable
	protected CodigoBarrasProductoRest codigoBarrasProductoRest;
	@WireVariable
	protected ConffyaFuenteFinanciamientoRest conffyaFuenteFinanciamientoRest;
	@WireVariable
	protected ConffyaPartidaGenericaRest conffyaPartidaGenericaRest;
	@WireVariable
	protected ConffyaPresupuestoDesglosadoRest conffyaPresupuestoDesglosadoRest;
	@WireVariable
	protected ConffyaProgRest conffyaProgRest;
	@WireVariable
	protected ConffyaPyRest conffyaPyRest;
	@WireVariable
	protected ConfiguracionCorreoRest configuracionCorreoRest;
	@WireVariable
	protected ContactoRest contactoRest;
	@WireVariable
	protected ContratoRest contratoRest;
	@WireVariable
	protected CostosProductoRest costosProductoRest;;
	@WireVariable
	protected CostosTiposRest costosTiposRest;
	@WireVariable
	protected CotizacionInboxRest cotizacionInboxRest;
	@WireVariable
	protected CotizacionProductoRest cotizacionProductoRest;
	@WireVariable
	protected CotizacionRest cotizacionRest;
	@WireVariable
	protected CuentasPagoRest cuentasPagoRest;
	@WireVariable
	protected DestinoRest destinoRest;
	@WireVariable
	protected DevelopmentToolRest developmentToolRest;
	@WireVariable
	protected DireccionEntregaRest direccionEntregaRest;
	@WireVariable
	protected DireccionRest direccionRest;
	@WireVariable
	protected EmailRest emailRest;
	@WireVariable
	protected EstadoRest estadoRest;
	@WireVariable
	protected EstatusRequisicionRest estatusRequisicionRest;
	@WireVariable
	protected FamiliasProductoRest familiasProductoRest;
	@WireVariable
	protected GiroRest giroRest;
	@WireVariable
	protected JustificacionRest justificacionRest;
	@WireVariable
	protected KardexProveedorRest kardexProveedorRest;
	@WireVariable
	protected KardexRest kardexRest;
	@WireVariable
	protected LabelsModulosRest labelsModulosRest;
	@WireVariable
	protected LugarRest lugarRest;
	@WireVariable
	protected ModulosOrganizacionRest modulosOrganizacionRest;
	@WireVariable
	protected ModulosRest modulosRest;
	@WireVariable
	protected MonedaRest monedaRest;
	@WireVariable
	protected MunicipioRest municipioRest;
	@WireVariable
	protected OrdenCompraInboxRest ordenCompraInboxRest;
	@WireVariable
	protected OrdenCompraProductoRest ordenCompraProductoRest;
	@WireVariable
	protected OrdenCompraRest ordenCompraRest;
	@WireVariable
	protected OrganizacionRest organizacionRest;
	@WireVariable
	protected PaisRest paisRest;
	@WireVariable
	protected PartidaRest partidaRest;
	@WireVariable
	protected PersonaRest personaRest;
	@WireVariable
	protected PosicionRest posicionRest;
	@WireVariable
	protected PresentacionRest presentacionRest;
	@WireVariable
	protected PrivilegioRest privilegioRest;
	@WireVariable
	protected ProductoCostosRest productoCostosRest;
	@WireVariable
	protected ProductoFactoresRest productoFactoresRest;
	@WireVariable
	protected ProductoMargenRest productoMargenRest;
	@WireVariable
	protected ProductoNaturalezaRest productoNaturalezaRest;
	@WireVariable
	protected ProductoPreciosRest productoPreciosRest;
	@WireVariable
	protected ProductoRest productoRest;
	@WireVariable
	protected ProductoTipoRest productoTipoRest;
	@WireVariable
	protected ProductoTipoSubFamiliaRest productoTipoSubFamiliaRest;
	@WireVariable
	protected ProductoTopeRest productoTopeRest;
	@WireVariable
	protected ProveedorProductoRest proveedorProductoRest;
	@WireVariable
	protected ProveedorRest proveedorRest;
	@WireVariable
	protected ProveedorUsuarioRest proveedorUsuarioRest;
	@WireVariable
	protected ProyectoRest proyectoRest;
	@WireVariable
	protected RequisicionInboxRest requisicionInboxRest;
	@WireVariable
	protected RequisicionPartidaRest requisicionPartidaRest;
	@WireVariable
	protected RequisicionProductoRest requisicionProductoRest;
	@WireVariable
	protected RequisicionProveedorRest requisicionProveedorRest;
	@WireVariable
	protected RequisicionRest requisicionRest;
	@WireVariable
	protected RestMetaclas restMetaclas;
	@WireVariable
	protected TelefonoRest telefonoRest;
	@WireVariable
	protected UnidadRest unidadRest;
	@WireVariable
	protected UsuarioRest usuarioRest;
}
