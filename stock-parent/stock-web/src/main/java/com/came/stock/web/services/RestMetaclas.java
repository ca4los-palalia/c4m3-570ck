package com.came.stock.web.services;

import java.net.UnknownHostException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.management.MalformedObjectNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.came.stock.beans.CfgCore;
import com.came.stock.beans.CtrlRestService;
import com.came.stock.constantes.ConstAtributos;
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
import com.came.stock.model.domain.CotizacionInbox;
import com.came.stock.model.domain.CotizacionProducto;
import com.came.stock.model.domain.CuentaPago;
import com.came.stock.model.domain.Destino;
import com.came.stock.model.domain.DevelopmentTool;
import com.came.stock.model.domain.Direccion;
import com.came.stock.model.domain.DireccionEntrega;
import com.came.stock.model.domain.Email;
import com.came.stock.model.domain.Estado;
import com.came.stock.model.domain.EstatusRequisicion;
import com.came.stock.model.domain.FamiliasProducto;
import com.came.stock.model.domain.Giro;
import com.came.stock.model.domain.Justificacion;
import com.came.stock.model.domain.Kardex;
import com.came.stock.model.domain.KardexProveedor;
import com.came.stock.model.domain.LabelsModulos;
import com.came.stock.model.domain.Lugar;
import com.came.stock.model.domain.Modulos;
import com.came.stock.model.domain.ModulosOrganizacion;
import com.came.stock.model.domain.Moneda;
import com.came.stock.model.domain.Municipio;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.OrdenCompraProducto;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Pais;
import com.came.stock.model.domain.Partida;
import com.came.stock.model.domain.Persona;
import com.came.stock.model.domain.Posicion;
import com.came.stock.model.domain.Presentacion;
import com.came.stock.model.domain.Privilegios;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoCostos;
import com.came.stock.model.domain.ProductoFactores;
import com.came.stock.model.domain.ProductoMargen;
import com.came.stock.model.domain.ProductoNaturaleza;
import com.came.stock.model.domain.ProductoPrecios;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.ProductoTipoSubFamilia;
import com.came.stock.model.domain.ProductoTope;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.ProveedorProducto;
import com.came.stock.model.domain.ProveedorUsuario;
import com.came.stock.model.domain.Proyecto;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionInbox;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.model.domain.RequisicionProveedor;
import com.came.stock.model.domain.Telefono;
import com.came.stock.model.domain.Unidad;
import com.came.stock.model.domain.Usuarios;
import com.came.stock.utilidades.StockUtilString;
import com.came.stock.utilidades.StockUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Repository
public class RestMetaclas {

	@Autowired
	protected StockUtils stockUtils;
	@Autowired
	protected StockUtilString stockUtilString;

	protected CfgCore cfg;
	protected Gson gson;

	@SuppressWarnings("static-access")
	@PostConstruct
	public void init() {
		gson = new Gson();

		try {
			cfg = stockUtils.getInstanciaDelServidor();
		} catch (MalformedObjectNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String procesarRestFULL(String url, String parametros) {
		String response = "";
		try {
			RestTemplate restTemplate = new RestTemplate();
			response = restTemplate.postForObject(url, parametros, String.class);
		} catch (Exception e) {
			response = ConstAtributos.ERROR_EXCEPTION + ": " + e.getMessage() + " (Intente mas tarde)\nURL:" + url
					+ "\nParametros: " + parametros + "\n" + stockUtilString.exceptionToString(e);
		}
		return response;
	}

	public CtrlRestService createResponseToListAlmacen(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Almacen>> token = new TypeToken<List<Almacen>>() {
		};
		List<Almacen> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToAlmacen(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Almacen responseObject = gson.fromJson(response, Almacen.class);
		itemReturn.setSingle((responseObject.getIdAlmacen() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListAlmacenEntrada(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<AlmacenEntrada>> token = new TypeToken<List<AlmacenEntrada>>() {
		};
		List<AlmacenEntrada> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToAlmacenEntrada(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		AlmacenEntrada responseObject = gson.fromJson(response, AlmacenEntrada.class);
		itemReturn.setSingle((responseObject.getIdAlmacenEntrada() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListArea(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Area>> token = new TypeToken<List<Area>>() {
		};
		List<Area> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToArea(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Area responseObject = gson.fromJson(response, Area.class);
		itemReturn.setSingle((responseObject.getIdArea() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListBanco(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Banco>> token = new TypeToken<List<Banco>>() {
		};
		List<Banco> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToBanco(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Banco responseObject = gson.fromJson(response, Banco.class);
		itemReturn.setSingle((responseObject.getIdBanco() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListBanner(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Banner>> token = new TypeToken<List<Banner>>() {
		};
		List<Banner> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToBanner(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Banner responseObject = gson.fromJson(response, Banner.class);
		itemReturn.setSingle((responseObject.getIdBanner() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListCalculosCosto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<CalculosCosto>> token = new TypeToken<List<CalculosCosto>>() {
		};
		List<CalculosCosto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCalculosCosto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		CalculosCosto responseObject = gson.fromJson(response, CalculosCosto.class);
		itemReturn.setSingle((responseObject.getIdCalculosCosto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListClaveArmonizada(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ClaveArmonizada>> token = new TypeToken<List<ClaveArmonizada>>() {
		};
		List<ClaveArmonizada> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToClaveArmonizada(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ClaveArmonizada responseObject = gson.fromJson(response, ClaveArmonizada.class);
		itemReturn.setSingle((responseObject.getIdClaveArmonizada() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListCodigoBarrasProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<CodigoBarrasProducto>> token = new TypeToken<List<CodigoBarrasProducto>>() {
		};
		List<CodigoBarrasProducto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCodigoBarrasProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		CodigoBarrasProducto responseObject = gson.fromJson(response, CodigoBarrasProducto.class);
		itemReturn.setSingle((responseObject.getIdCodigoBarrasProducto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListConffyaFuenteFinanciamiento(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ConffyaFuenteFinanciamiento>> token = new TypeToken<List<ConffyaFuenteFinanciamiento>>() {
		};
		List<ConffyaFuenteFinanciamiento> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToConffyaFuenteFinanciamiento(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ConffyaFuenteFinanciamiento responseObject = gson.fromJson(response, ConffyaFuenteFinanciamiento.class);
		itemReturn.setSingle((responseObject.getIdConffyaFuenteFinanciamiento() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListConffyaPartidaGenerica(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ConffyaPartidaGenerica>> token = new TypeToken<List<ConffyaPartidaGenerica>>() {
		};
		List<ConffyaPartidaGenerica> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToConffyaPartidaGenerica(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ConffyaPartidaGenerica responseObject = gson.fromJson(response, ConffyaPartidaGenerica.class);
		itemReturn.setSingle((responseObject.getIdConffyaPartidaGenerica() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListConffyaPresupuestoDesglosado(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ConffyaPresupuestoDesglosado>> token = new TypeToken<List<ConffyaPresupuestoDesglosado>>() {
		};
		List<ConffyaPresupuestoDesglosado> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToConffyaPresupuestoDesglosado(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ConffyaPresupuestoDesglosado responseObject = gson.fromJson(response, ConffyaPresupuestoDesglosado.class);
		itemReturn.setSingle((responseObject.getIdConffyaPresupuestoDesglosado() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListConffyaProg(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ConffyaProg>> token = new TypeToken<List<ConffyaProg>>() {
		};
		List<ConffyaProg> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToConffyaProg(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ConffyaProg responseObject = gson.fromJson(response, ConffyaProg.class);
		itemReturn.setSingle((responseObject.getIdConffyaProg() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListConffyaPy(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ConffyaPy>> token = new TypeToken<List<ConffyaPy>>() {
		};
		List<ConffyaPy> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToConffyaPy(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ConffyaPy responseObject = gson.fromJson(response, ConffyaPy.class);
		itemReturn.setSingle((responseObject.getIdConffyaPy() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListConfiguracionCorreo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ConfiguracionCorreo>> token = new TypeToken<List<ConfiguracionCorreo>>() {
		};
		List<ConfiguracionCorreo> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToConfiguracionCorreo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ConfiguracionCorreo responseObject = gson.fromJson(response, ConfiguracionCorreo.class);
		itemReturn.setSingle((responseObject.getIdConfiguracionCorreo() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListContacto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Contacto>> token = new TypeToken<List<Contacto>>() {
		};
		List<Contacto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToContacto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Contacto responseObject = gson.fromJson(response, Contacto.class);
		itemReturn.setSingle((responseObject.getIdContacto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListContrato(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Contrato>> token = new TypeToken<List<Contrato>>() {
		};
		List<Contrato> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToContrato(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Contrato responseObject = gson.fromJson(response, Contrato.class);
		itemReturn.setSingle((responseObject.getIdContrato() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListCostosProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<CostosProducto>> token = new TypeToken<List<CostosProducto>>() {
		};
		List<CostosProducto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCostosProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		CostosProducto responseObject = gson.fromJson(response, CostosProducto.class);
		itemReturn.setSingle((responseObject.getIdCostosProducto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListCostosTipos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<CostosTipos>> token = new TypeToken<List<CostosTipos>>() {
		};
		List<CostosTipos> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCostosTipos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		CostosTipos responseObject = gson.fromJson(response, CostosTipos.class);
		itemReturn.setSingle((responseObject.getIdCostosTipos() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListCotizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Cotizacion>> token = new TypeToken<List<Cotizacion>>() {
		};
		List<Cotizacion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCotizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Cotizacion responseObject = gson.fromJson(response, Cotizacion.class);
		itemReturn.setSingle((responseObject.getIdCotizacion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListCotizacionInbox(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<CotizacionInbox>> token = new TypeToken<List<CotizacionInbox>>() {
		};
		List<CotizacionInbox> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCotizacionInbox(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		CotizacionInbox responseObject = gson.fromJson(response, CotizacionInbox.class);
		itemReturn.setSingle((responseObject.getIdCotizacion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListCotizacionProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<CotizacionProducto>> token = new TypeToken<List<CotizacionProducto>>() {
		};
		List<CotizacionProducto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCotizacionProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		CotizacionProducto responseObject = gson.fromJson(response, CotizacionProducto.class);
		itemReturn.setSingle((responseObject.getIdCotizacionProducto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListCuentaPago(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<CuentaPago>> token = new TypeToken<List<CuentaPago>>() {
		};
		List<CuentaPago> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToCuentaPago(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		CuentaPago responseObject = gson.fromJson(response, CuentaPago.class);
		itemReturn.setSingle((responseObject.getIdCuentaPago() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListDestino(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Destino>> token = new TypeToken<List<Destino>>() {
		};
		List<Destino> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToDestino(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Destino responseObject = gson.fromJson(response, Destino.class);
		itemReturn.setSingle((responseObject.getIdDestino() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListDevelopmentTool(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<DevelopmentTool>> token = new TypeToken<List<DevelopmentTool>>() {
		};
		List<DevelopmentTool> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToDevelopmentTool(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		DevelopmentTool responseObject = gson.fromJson(response, DevelopmentTool.class);
		itemReturn.setSingle((responseObject.getIdDevelopmentTool() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListDireccion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Direccion>> token = new TypeToken<List<Direccion>>() {
		};
		List<Direccion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToDireccion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Direccion responseObject = gson.fromJson(response, Direccion.class);
		itemReturn.setSingle((responseObject.getIdDireccion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListDireccionEntrega(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<DireccionEntrega>> token = new TypeToken<List<DireccionEntrega>>() {
		};
		List<DireccionEntrega> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToDireccionEntrega(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		DireccionEntrega responseObject = gson.fromJson(response, DireccionEntrega.class);
		itemReturn.setSingle((responseObject.getIdDireccionEntrega() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListEmail(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Email>> token = new TypeToken<List<Email>>() {
		};
		List<Email> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToEmail(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Email responseObject = gson.fromJson(response, Email.class);
		itemReturn.setSingle((responseObject.getIdEmails() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListEstado(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Estado>> token = new TypeToken<List<Estado>>() {
		};
		List<Estado> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToEstado(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Estado responseObject = gson.fromJson(response, Estado.class);
		itemReturn.setSingle((responseObject.getIdEstado() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListEstatusRequisicion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<EstatusRequisicion>> token = new TypeToken<List<EstatusRequisicion>>() {
		};
		List<EstatusRequisicion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToEstatusRequisicion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		EstatusRequisicion responseObject = gson.fromJson(response, EstatusRequisicion.class);
		itemReturn.setSingle((responseObject.getIdEstatusRequisicion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListFamiliasProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<FamiliasProducto>> token = new TypeToken<List<FamiliasProducto>>() {
		};
		List<FamiliasProducto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToFamiliasProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		FamiliasProducto responseObject = gson.fromJson(response, FamiliasProducto.class);
		itemReturn.setSingle((responseObject.getIdFamiliasProducto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListGiro(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Giro>> token = new TypeToken<List<Giro>>() {
		};
		List<Giro> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToGiro(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Giro responseObject = gson.fromJson(response, Giro.class);
		itemReturn.setSingle((responseObject.getIdGiro() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListJustificacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Justificacion>> token = new TypeToken<List<Justificacion>>() {
		};
		List<Justificacion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToJustificacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Justificacion responseObject = gson.fromJson(response, Justificacion.class);
		itemReturn.setSingle((responseObject.getIdJustificacion()== null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListKardex(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Kardex>> token = new TypeToken<List<Kardex>>() {
		};
		List<Kardex> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToKardex(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Kardex responseObject = gson.fromJson(response, Kardex.class);
		itemReturn.setSingle((responseObject.getIdKardex() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListKardexProveedor(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<KardexProveedor>> token = new TypeToken<List<KardexProveedor>>() {
		};
		List<KardexProveedor> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToKardexProveedor(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		KardexProveedor responseObject = gson.fromJson(response, KardexProveedor.class);
		itemReturn.setSingle((responseObject.getIdKardexProveedor() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListLabelsModulos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<LabelsModulos>> token = new TypeToken<List<LabelsModulos>>() {
		};
		List<LabelsModulos> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToLabelsModulos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		LabelsModulos responseObject = gson.fromJson(response, LabelsModulos.class);
		itemReturn.setSingle((responseObject.getIdLabelsModulos() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListLugar(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Lugar>> token = new TypeToken<List<Lugar>>() {
		};
		List<Lugar> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToLugar(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Lugar responseObject = gson.fromJson(response, Lugar.class);
		itemReturn.setSingle((responseObject.getIdLugar() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListModulos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Modulos>> token = new TypeToken<List<Modulos>>() {
		};
		List<Modulos> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToModulos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Modulos responseObject = gson.fromJson(response, Modulos.class);
		itemReturn.setSingle((responseObject.getIdModulo() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListModulosOrganizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ModulosOrganizacion>> token = new TypeToken<List<ModulosOrganizacion>>() {
		};
		List<ModulosOrganizacion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToModulosOrganizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ModulosOrganizacion responseObject = gson.fromJson(response, ModulosOrganizacion.class);
		itemReturn.setSingle((responseObject.getIdModulosOrganizacion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListMoneda(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Moneda>> token = new TypeToken<List<Moneda>>() {
		};
		List<Moneda> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToMoneda(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Moneda responseObject = gson.fromJson(response, Moneda.class);
		itemReturn.setSingle((responseObject.getIdMoneda() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListMunicipio(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Municipio>> token = new TypeToken<List<Municipio>>() {
		};
		List<Municipio> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToMunicipio(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Municipio responseObject = gson.fromJson(response, Municipio.class);
		itemReturn.setSingle((responseObject.getIdMunicipio() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListOrdenCompra(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<OrdenCompra>> token = new TypeToken<List<OrdenCompra>>() {
		};
		List<OrdenCompra> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToOrdenCompra(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		OrdenCompra responseObject = gson.fromJson(response, OrdenCompra.class);
		itemReturn.setSingle((responseObject.getIdOrdenCompra() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListOrdenCompraInbox(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<OrdenCompraInbox>> token = new TypeToken<List<OrdenCompraInbox>>() {
		};
		List<OrdenCompraInbox> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToOrdenCompraInbox(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		OrdenCompraInbox responseObject = gson.fromJson(response, OrdenCompraInbox.class);
		itemReturn.setSingle((responseObject.getIdOrdenCompraInbox() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListOrdenCompraProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<OrdenCompraProducto>> token = new TypeToken<List<OrdenCompraProducto>>() {
		};
		List<OrdenCompraProducto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToOrdenCompraProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		OrdenCompraProducto responseObject = gson.fromJson(response, OrdenCompraProducto.class);
		itemReturn.setSingle((responseObject.getIdOrdenCompraProdcuto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListOrganizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Organizacion>> token = new TypeToken<List<Organizacion>>() {
		};
		List<Organizacion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToOrganizacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Organizacion responseObject = gson.fromJson(response, Organizacion.class);
		itemReturn.setSingle((responseObject.getIdOrganizacion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListPais(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Pais>> token = new TypeToken<List<Pais>>() {
		};
		List<Pais> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToPais(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Pais responseObject = gson.fromJson(response, Pais.class);
		itemReturn.setSingle((responseObject.getIdPais() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListPartida(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Partida>> token = new TypeToken<List<Partida>>() {
		};
		List<Partida> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToPartida(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Partida responseObject = gson.fromJson(response, Partida.class);
		itemReturn.setSingle(responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListPersona(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Persona>> token = new TypeToken<List<Persona>>() {
		};
		List<Persona> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToPersona(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Persona responseObject = gson.fromJson(response, Persona.class);
		itemReturn.setSingle((responseObject.getIdPersona() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListPosicion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Posicion>> token = new TypeToken<List<Posicion>>() {
		};
		List<Posicion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToPosicion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Posicion responseObject = gson.fromJson(response, Posicion.class);
		itemReturn.setSingle((responseObject.getIdPosicion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListPresentacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Presentacion>> token = new TypeToken<List<Presentacion>>() {
		};
		List<Presentacion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToPresentacion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Presentacion responseObject = gson.fromJson(response, Presentacion.class);
		itemReturn.setSingle((responseObject.getIdPresentacion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListPrivilegio(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Privilegios>> token = new TypeToken<List<Privilegios>>() {
		};
		List<Privilegios> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToPrivilegio(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Privilegios responseObject = gson.fromJson(response, Privilegios.class);
		itemReturn.setSingle((responseObject.getIdPrivilegio() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProductoCostos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoCostos>> token = new TypeToken<List<ProductoCostos>>() {
		};
		List<ProductoCostos> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoCostos(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoCostos responseObject = gson.fromJson(response, ProductoCostos.class);
		itemReturn.setSingle((responseObject.getIdProductoCostos() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Producto>> token = new TypeToken<List<Producto>>() {
		};
		List<Producto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Producto responseObject = gson.fromJson(response, Producto.class);
		itemReturn.setSingle((responseObject.getIdProducto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListProductoFactores(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoFactores>> token = new TypeToken<List<ProductoFactores>>() {
		};
		List<ProductoFactores> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoFactores(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoFactores responseObject = gson.fromJson(response, ProductoFactores.class);
		itemReturn.setSingle((responseObject.getIdProductoFactores() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProductoMargen(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoMargen>> token = new TypeToken<List<ProductoMargen>>() {
		};
		List<ProductoMargen> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoMargen(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoMargen responseObject = gson.fromJson(response, ProductoMargen.class);
		itemReturn.setSingle((responseObject.getIdProductoMargen() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProductoNaturaleza(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoNaturaleza>> token = new TypeToken<List<ProductoNaturaleza>>() {
		};
		List<ProductoNaturaleza> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoNaturaleza(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoNaturaleza responseObject = gson.fromJson(response, ProductoNaturaleza.class);
		itemReturn.setSingle((responseObject.getIdProductoNaturaleza() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProductoPrecios(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoPrecios>> token = new TypeToken<List<ProductoPrecios>>() {
		};
		List<ProductoPrecios> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoPrecios(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoPrecios responseObject = gson.fromJson(response, ProductoPrecios.class);
		itemReturn.setSingle((responseObject.getIdProductoPrecios() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProductoTipo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoTipo>> token = new TypeToken<List<ProductoTipo>>() {
		};
		List<ProductoTipo> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoTipo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoTipo responseObject = gson.fromJson(response, ProductoTipo.class);
		itemReturn.setSingle((responseObject.getIdProductoTipo() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProductoTipoSubFamilia(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoTipoSubFamilia>> token = new TypeToken<List<ProductoTipoSubFamilia>>() {
		};
		List<ProductoTipoSubFamilia> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoTipoSubFamilia(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoTipoSubFamilia responseObject = gson.fromJson(response, ProductoTipoSubFamilia.class);
		itemReturn.setSingle((responseObject.getIdProductoTipoSubFamilia() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListProductoTope(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProductoTope>> token = new TypeToken<List<ProductoTope>>() {
		};
		List<ProductoTope> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProductoTope(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProductoTope responseObject = gson.fromJson(response, ProductoTope.class);
		itemReturn.setSingle((responseObject.getIdProductoTopo() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProveedor(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Proveedor>> token = new TypeToken<List<Proveedor>>() {
		};
		List<Proveedor> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToProveedor(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Proveedor responseObject = gson.fromJson(response, Proveedor.class);
		itemReturn.setSingle((responseObject.getIdProveedor() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
		
	public CtrlRestService createResponseToListProveedorProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProveedorProducto>> token = new TypeToken<List<ProveedorProducto>>() {
		};
		List<ProveedorProducto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToProveedorProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProveedorProducto responseObject = gson.fromJson(response, ProveedorProducto.class);
		itemReturn.setSingle((responseObject.getIdProveedorProdcuto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListProveedorUsuario(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<ProveedorUsuario>> token = new TypeToken<List<ProveedorUsuario>>() {
		};
		List<ProveedorUsuario> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToProveedorUsuario(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		ProveedorUsuario responseObject = gson.fromJson(response, ProveedorUsuario.class);
		itemReturn.setSingle((responseObject.getIdProveedorUsuario() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	
	public CtrlRestService createResponseToListProyecyo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Proyecto>> token = new TypeToken<List<Proyecto>>() {
		};
		List<Proyecto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToProyecyo(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Proyecto responseObject = gson.fromJson(response, Proyecto.class);
		itemReturn.setSingle((responseObject.getIdProyecto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListRequisicionInbox(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<RequisicionInbox>> token = new TypeToken<List<RequisicionInbox>>() {
		};
		List<RequisicionInbox> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToRequisicionInbox(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		RequisicionInbox responseObject = gson.fromJson(response, RequisicionInbox.class);
		itemReturn.setSingle((responseObject.getIdRequsicionInbox() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListRequisicionProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<RequisicionProducto>> token = new TypeToken<List<RequisicionProducto>>() {
		};
		List<RequisicionProducto> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToRequisicionProducto(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		RequisicionProducto responseObject = gson.fromJson(response, RequisicionProducto.class);
		itemReturn.setSingle((responseObject.getIdRequisionProducto() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListRequisicionProveedor(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<RequisicionProveedor>> token = new TypeToken<List<RequisicionProveedor>>() {
		};
		List<RequisicionProveedor> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToRequisicionProveedor(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		RequisicionProveedor responseObject = gson.fromJson(response, RequisicionProveedor.class);
		itemReturn.setSingle((responseObject.getIdRequisicionProveedor() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
	
	public CtrlRestService createResponseToListRequisicion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Requisicion>> token = new TypeToken<List<Requisicion>>() {
		};
		List<Requisicion> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToRequisicion(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Requisicion responseObject = gson.fromJson(response, Requisicion.class);
		itemReturn.setSingle((responseObject.getIdRequisicion() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListTelefono(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Telefono>> token = new TypeToken<List<Telefono>>() {
		};
		List<Telefono> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToTelefono(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Telefono responseObject = gson.fromJson(response, Telefono.class);
		itemReturn.setSingle((responseObject.getIdTelefono() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListUnidad(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Unidad>> token = new TypeToken<List<Unidad>>() {
		};
		List<Unidad> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToUnidad(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Unidad responseObject = gson.fromJson(response, Unidad.class);
		itemReturn.setSingle((responseObject.getIdUnidad() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToListUsuario(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		TypeToken<List<Usuarios>> token = new TypeToken<List<Usuarios>>() {
		};
		List<Usuarios> responseObject = gson.fromJson(response, token.getType());
		itemReturn.setSingle((responseObject.size() == 0) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}

	public CtrlRestService createResponseToUsuario(String response) {
		CtrlRestService itemReturn = new CtrlRestService();
		Usuarios responseObject = gson.fromJson(response, Usuarios.class);
		itemReturn.setSingle((responseObject.getIdUsuario() == null) ? null : responseObject);
		itemReturn.setOk(true);
		return itemReturn;
	}
}
