package com.came.stock.constantes;

import com.came.stock.beans.SistemaOperativo;

public class StockConstants {
	public static final String MODAL_VIEW_PRODUCTOS = "/modulos/productos/utils/buscarProducto.zul";
	public static final String MODAL_VIEW_ENVIO_PRODUCTOS = "/modulos/productos/utils/configurarEnvioProductos.zul";
	public static final String MODAL_VIEW_BANDEJA = "/modulos//utilidades/bandeja.zul";
	public static final String MODAL_VIEW_DETALLES_REQUISICION = "/modulos/requisicion/requisicionDetalles.zul";
	public static final String CURRENCY_FORMAT = "###,###,###.00";
	public static final String TOOL_TIP_SAVE_AREA = "Actualizar/Guardar �rea";
	public static final String TOOL_TIP_SAVE_BANCO = "Actualizar/Guardar banco";
	public static final String TOOL_TIP_SAVE_CONFFYA = "Actualizar/Guardar catalogo conffya";
	public static final String TOOL_TIP_SAVE_PUESTO = "Actualizar/Guardar puesto";
	public static final String TOOL_TIP_SAVE_CONTRATO = "Actualizar/Guardar contrato";
	public static final String TOOL_TIP_SAVE_MONEDA = "Actualizar/Guardar divisa";
	public static final String TOOL_TIP_SAVE_PRODUCTO = "Actualizar/Guardar tipo de productos";
	public static final String TOOL_TIP_SAVE_UNIDAD = "Actualizar/Guardar unidad de medida";
	public static final String TOOL_TIP_SAVE_GIRO = "Actualizar/Guardar giro";
	public static final String TOOL_TIP_DELETE_AREA = "Eliminar �rea";
	public static final String TOOL_TIP_DELETE_PUESTO = "Eliminar puesto";
	public static final String TOOL_TIP_DELETE_BANCO = "Eliminar banco";
	public static final String TOOL_TIP_DELETE_MONEDA = "Eliminar divisa";
	public static final String TOOL_TIP_DELETE_PRODUCTO = "Eliminar productos";
	public static final String TOOL_TIP_DELETE_CONTRATO = "Eliminar contrato";
	public static final String TOOL_TIP_DELETE_UNIDAD = "Eliminar unidad de medida";
	public static final String TOOL_TIP_DELETE_GIRO = "Eliminar giro";
	public static final String TOOL_TIP_ROW_SELECTED_AREA = "Seleccionar un �rea";
	public static final String TOOL_TIP_ROW_SELECTED_PUESTO = "Seleccionar un puesto";
	public static final String TOOL_TIP_ROW_SELECTED_BANCO = "Seleccionar un banco";
	public static final String TOOL_TIP_ROW_SELECTED_MONEDA = "Seleccionar una moneda";
	public static final String TOOL_TIP_ROW_SELECTED_TIPO_PRODUCTO = "Seleccionar un tipo de producto";
	public static final String TOOL_TIP_ROW_EDICION_NOMBRE = "Clic sobre esta columna para editar nombre";
	public static final String ESTADO_REQUISICION_NUEVA = "RQN";
	public static final String ESTADO_REQUISICION_PENDIENTE = "RQP";
	public static final String ESTADO_REQUISICION_TERMINADA = "RQT";
	public static final String ESTADO_REQUISICION_CANCELADA = "RQC";
	public static final String ESTADO_ORDEN_COMPRA_TERMINADA = "OCT";
	public static final String ESTADO_ORDEN_COMPRA_NUEVA = "OCN";
	public static final String ESTADO_ORDEN_COMPRA_CANCELADA = "OCC";
	public static final String ESTADO_COTIZACION_NUEVA = "CON";
	public static final String ESTADO_COTIZACION_ENVIADA = "COE";
	public static final String ESTADO_COTIZACION_CANCELADA = "COC";
	public static final String ESTADO_COTIZACION_ACEPTADA = "COA";
	public static final String BUSCAR_TODO = "*";
	public static final String CLAVE_FOLIO_REQUISICION = "FRQ";
	public static final String CLAVE_FOLIO_COTIZACION = "FCO";
	public static final String CLAVE_FOLIO_ORDEN_COMPRA = "FOC";
	public static final String ARCHIVO_JASPER_REQUISICION_FORMATO = "jasperTemplates/requisicionFormato.jasper";
	public static final String ARCHIVO_JASPER_COTIZACION_FORMATO = "jasperTemplates/cotizacionFormato.jasper";
	public static final String ARCHIVO_JASPER_ORDEN_COMPRA_FORMATO = "jasperTemplates/ordenCompraFormato.jasper";
	public static final String REPORT_PROVEEDOR_NAME_FILE = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Proveedores"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "reportProveedores.pdf";
	public static final String REPORT_PROVEEDOR_PARAM1 = "parameter1";
	public static final String REPORT_PROVEEDOR_NOMBRE_EMPRESA = "empresaTitle";
	public static final String REPORT_VARIABLE_PRODUCTO_NAME_FILE = new SistemaOperativo()
			.getDirectorioDeInicioDelUsuario() + new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Productos"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "reportProductos.pdf";
	public static final String NOMBRE_TEMPORAL_USUARIO_SYSTEMA = "NOMBRE TEMPORAL DE LA EMPRESA";
	public static final Object BENUTZER = "BENUTZER";
	public static final String FIRMA = "FIRMA";
	public static final String CARPETA_ARCHIVOS_REQUISICIONES = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Requisiciones"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_COTIZACIONES = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Cotizaciones"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_ORDEN_COMPRA = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "OrdenCompra"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_LOGOTIPOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Logotipos"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_PRODUCTOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Productos"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_PROVEEDORES = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Proveedores"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String CARPETA_ARCHIVOS_USUARIOS = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Users"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String LAYOUT = new SistemaOperativo().getDirectorioDeInicioDelUsuario()
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Stock"
			+ new SistemaOperativo().getSeparadorDeArchivos() + "Layout"
			+ new SistemaOperativo().getSeparadorDeArchivos();
	public static final String EXTENCION_EXCEL = ".xls";
	public static final String EXTENCION_PDF = ".pdf";
	public static final String OS_WIN = "WIN";
	public static final String OS_LIN = "*";
	public static final String OS_MAC = "MAC";
	public static final String LAYOUT_EXCEL_AREA = "layout/LayoutArea.xlsx";
	public static final String LAYOUT_EXCEL_BANCO = "layout/LayoutBanco.xlsx";
	public static final String LAYOUT_EXCEL_GIRO = "layout/LayoutGiro.xlsx";
	public static final String LAYOUT_EXCEL_DIVISA = "layout/LayoutDivisas.xlsx";
	public static final String LAYOUT_EXCEL_POSICION = "layout/LayoutPosicion(Puestos).xlsx";
	public static final String LAYOUT_EXCEL_PRODUCTO = "layout/LayoutProductos.xlsx";
	public static final String LAYOUT_EXCEL_PRODUCTO_TIPO = "layout/LayoutProductoFamilia.xlsx";
	public static final String LAYOUT_EXCEL_NATURALEZA = "layout/LayoutNaturaleza.xlsx";
	public static final String LAYOUT_EXCEL_PROVEEDOR = "layout/LayoutProveedores.xlsx";
	public static final String LAYOUT_EXCEL_UNIDAD = "layout/LayoutUnidad.xlsx";
	public static final String LAYOUT_EXCEL_PRESENTACION = "layout/LayoutPresentaciones.xlsx";
	//public static final String LAYOUT_EXCEL_SCRIPT = "layout/Script.xlsx";
	//public static final String URL_WSDL = "http://legajo-virtual.com:82/ConffyaGubernamental/ServicioWebConffya.asmx?wsdl";
	public static final String URL_WSDL = "http://legajo-virtual.com:8081/ConffyaGubernamental/ServicioWebConffya.asmx?wsdl";
	
	public static final String ICON_WIN_INFORMATION = "/images/toolbar/information48.png";
	public static final String ICON_WIN_WARNING = "/images/toolbar/warning48.png";
	public static final String ICON_WIN_ERROR = "/images/toolbar/error48.png";
	
	public static final String ICON_WIN_CONFIRM = "/images/toolbar/question512.png";

	
	public static final String LANGUAGE_ESP = "properties/ES.properties";
	
	public static final String SISTEMA_CONFIG_MODE = "CONFIG_MODE";
	public static final String SISTEMA_CONFIG_EMAIL = "CONFIG_EMAIL";
	public static final String SISTEMA_CONFIG_ACTIVA_PROVEEDOR = "CONFIG_ACTIVA_PROVEEDOR";
	public static final String SISTEMA_CONFIG_ENCABEZADO = "CONFIG_CONFIG_ENCABEZADO";
	public static final String SISTEMA_CONFIG_VERSION = "CONFIG_VERSION";
	public static final String SISTEMA_PEPS= "PEPS";
	public static final String SISTEMA_UEPS= "UEPS";
	public static final String SISTEMA_PROMEDIO= "PROMEDIO";
	public static final String SISTEMA_BUSSY = "bussy";
	
	
	
	public static final String COLOR_LOGIN_START = "#0da7b9";
	public static final String COLOR_LOGIN_END = "#006899";
	public static final String COLOR_LOGIN_FONT = "#ffffff";
	
	public static final String COLOR_HOME_BGR_MAIN_START = "#f2f6f8";
	public static final String COLOR_HOME_BGR_MAIN_END = "#e0eff9";
	
	public static final String COLOR_FONT_GLOBAL = "#666666";
	
	
	public static final class GLOBAL_PAGES {
		public static final String HOME_URL = "/home.zul";
		public static final String LOGIN_URL = "/login.zul";
		public static final String PRODUCTOS = "/modulos/productos/productos.zul";
		public static final String PRODUCTOS_BUSCADOR = "/modulos/productos/productosBuscador.zul";
		public static final String PROVEEDORES_BUSCADOR = "/modulos/proveedores/proveedoresBuscador.zul";
		public static final String REQUISICION = "/modulos/requisicion/requisicion.zul";
		public static final String REQUISICION_BUSCADOR = "/modulos/requisicion/requisicionBuscador.zul";
		public static final String CONCENTRADO = "/modulos/requisicion/concentrado.zul";
		public static final String CONTROL = "/modulos/requisicion/control.zul";
		public static final String COTIZACION = "/modulos/requisicion/cotizacion.zul";
		public static final String COTIZACION_BUSCADOR = "/modulos/requisicion/cotizacionBuscador.zul";
		public static final String USUARIOS = "/modulos/usuarios/usuario.zul";
		public static final String CONTROL_PANEL = "/modulos/controlPanel/controlPanel.zul";
		public static final String REPORTS = "/modulos/reportes/reportes.zul";
		public static final String ORDERS = "/modulos/ordenCompra/ordenCompra.zul";
		public static final String CONTROL_PANEL_COFIGURACION_USUARIO = "/modulos/controlPanel/usuario.zul";
		public static final String MODAL_VIEW_COMPANIA = "/modulos/controlPanel/utils/buscarOrganizaciones.zul";
		public static final String CONTROL_PANEL_USUARIOS_NEGOCIO = "/modulos/controlPanel/usuariosCliente.zul";
	}
}

