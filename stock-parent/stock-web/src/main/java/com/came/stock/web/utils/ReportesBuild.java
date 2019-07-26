package com.came.stock.web.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.zkoss.util.media.AMedia;

import com.came.stock.beans.ControlVisorPdf;
import com.came.stock.beans.SistemaOperativo;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.Organizacion;
import com.came.stock.model.domain.Producto;
import com.came.stock.model.domain.ProductoTipo;
import com.came.stock.model.domain.Proveedor;
import com.came.stock.model.domain.Requisicion;
import com.came.stock.model.domain.RequisicionProducto;
import com.came.stock.utilidades.StockUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportesBuild {
	private JasperPrint print;
	private String readJasper;
	private boolean modoFile;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ControlVisorPdf genererRequisicionPdf(Requisicion requisicion,
			List<RequisicionProducto> requisicionProductos, Organizacion org) {
		readJasper = generarUrlString("jasperTemplates/requisicionFormato.jasper");
		InputStream imagenEmpresa = new ByteArrayInputStream(org.getLogotipo());
		
		HashMap mapa = new HashMap();
		mapa.put("folio", requisicion.getFolio());
		mapa.put("area", requisicion.getArea().getNombre());
		mapa.put("prog", requisicion.getCofiaProg().getNombre());
		mapa.put("py", requisicion.getCofiaPy().getNombre());
		mapa.put("fuente", requisicion.getCofiaFuenteFinanciamiento().getNombre());

		mapa.put("solicitante", requisicion.getPersona().getApellidoPaterno() + " "
				+ requisicion.getPersona().getApellidoMaterno() + " " + requisicion.getPersona().getNombre());

		mapa.put("puesto", requisicion.getPosicion().getNombre());
		mapa.put("descripcion", requisicion.getAdscripcion());
		mapa.put("justificacion", requisicion.getJustificacion());
		mapa.put("NoInventario", requisicion.getNumeroInventario());
		mapa.put("logotipo", imagenEmpresa);
		mapa.put("nombreEmpresa", org.getNombre());

		List<HashMap> listaHashsParametros = new ArrayList();
		listaHashsParametros.add(mapa);

		String path = "";
		if (modoFile)
			path = new SistemaOperativo().getDirectorioTemporal() + new SistemaOperativo().getSeparadorDeArchivos() + ""
					+ requisicion.getFolio() + "_requisicion";		
		return generarPdfRequisicionFromJasper(listaHashsParametros, requisicionProductos, path);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ControlVisorPdf genererCotizacionPdf(Cotizacion cotizacion,
			List<Cotizacion> cotizacionesConProductos, Organizacion org) {
		
		readJasper = generarUrlString("jasperTemplates/cotizacionFormato.jasper");
		InputStream imagenEmpresa = new ByteArrayInputStream(org.getLogotipo());
		
		HashMap mapa = new HashMap();

		mapa.put("nombreEmpresa", org.getNombre());
		mapa.put("proveedor", cotizacion.getProveedor().getNombre());

		mapa.put("direccion",
				cotizacion.getProveedor().getDireccionFiscal().getCalle() + "|"
						+ cotizacion.getProveedor().getDireccionFiscal().getNumExt() + "|"
						+ cotizacion.getProveedor().getDireccionFiscal().getColonia() + "|"
						+ cotizacion.getProveedor().getDireccionFiscal().getCuidad() + "|"
						+ cotizacion.getProveedor().getDireccionFiscal().getEstado().getNombre() + "|"
						+ cotizacion.getProveedor().getDireccionFiscal().getPais().getNombre());

		String telefonoMap = "";
		try {
			if ((cotizacion.getProveedor().getContacto() != null)
					&& (cotizacion.getProveedor().getContacto().getTelefono() != null)) {
				telefonoMap = cotizacion.getProveedor().getContacto().getTelefono().getNumero();
			}
			mapa.put("telefono", telefonoMap);
		} catch (Exception e) {
		}
		String representanteLegal = "";
		if (cotizacion.getProveedor().getRepresentanteLegal() != null) {
			representanteLegal = cotizacion.getProveedor().getRepresentanteLegal().getNombreCompleto();
		}
		mapa.put("atencion", representanteLegal);
		mapa.put("folio", cotizacion.getFolioCotizacion());

		Calendar fechaMap = Calendar.getInstance();
		if (cotizacion.getFechaEnvioCotizacion() != null) {
			fechaMap = cotizacion.getFechaEnvioCotizacion();
		}
		mapa.put("fecha", new StockUtils().convertirCalendarToStringFormato1(fechaMap));

		String emailMap = "";
		if ((cotizacion.getProveedor().getContacto() != null)
				&& (cotizacion.getProveedor().getContacto().getEmail() != null)) {
			emailMap = cotizacion.getProveedor().getContacto().getEmail().getEmail();
		}
		mapa.put("email", emailMap);

		Float total = Float.valueOf(0.0F);
		for (Cotizacion item : cotizacionesConProductos) {
			if (item.getRequisicionProducto().getTotalProductoPorUnidad() != null) {
				total = Float.valueOf(total.floatValue()
						+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
			}
		}
		mapa.put("total", String.valueOf(total));
		mapa.put("logotipo", imagenEmpresa);
		
		List<HashMap> listaHashsParametros = new ArrayList();
		listaHashsParametros.add(mapa);
		
		String path = "";
		if (modoFile)
			path = new SistemaOperativo().getDirectorioTemporal() + new SistemaOperativo().getSeparadorDeArchivos() + ""
					+ cotizacion.getFolioCotizacion() + "_cotizacion";
		return generarPdfCotizacionesFromJasper(listaHashsParametros, cotizacionesConProductos, path);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ControlVisorPdf genererOrdenCompraPdf(OrdenCompra ordenCompra, Cotizacion cotizacion, Organizacion org, List<Cotizacion> cotizacionesConProductos) {
		readJasper = generarUrlString("jasperTemplates/ordenCompraFormato.jasper");
		InputStream imagenEmpresa = new ByteArrayInputStream(org.getLogotipo());
		
		HashMap mapa = new HashMap();

		mapa.put("nombreEmpresa", org.getNombre());
		mapa.put("proveedor", cotizacion.getProveedor().getNombre());

		mapa.put("ur", cotizacion.getRequisicion().getArea().getNombre());

		mapa.put("comentarios", cotizacion.getDetallesExtras());

		mapa.put("ordenCompra", ordenCompra.getCodigo());
		mapa.put("fechaOC", new StockUtils().convertirCalendarToStringFormato1(ordenCompra.getFechaOrden()));

		mapa.put("claveCotizacion", cotizacion.getFolioCotizacion());

		Float total = Float.valueOf(0.0F);
		for (Cotizacion item : cotizacionesConProductos) {
			total = Float.valueOf(total.floatValue()
					+ item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
		}
		mapa.put("total", String.valueOf(total));

		mapa.put("entregarEn", "");
		mapa.put("dependencia", "");
		mapa.put("tiempoEntrega", "");
		mapa.put("logotipo", imagenEmpresa);
		
		List<HashMap> listaHashsParametros = new ArrayList();
		listaHashsParametros.add(mapa);
		
		String path = "";
		if (modoFile)
			path = new SistemaOperativo().getDirectorioTemporal() + new SistemaOperativo().getSeparadorDeArchivos() + ""
					+ cotizacion.getFolioCotizacion() + "_orden_compra";
		
		return generarPdfCotizacionesFromJasper(listaHashsParametros, cotizacionesConProductos, path);
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ControlVisorPdf generarReporteProductosPdf(List<Producto> productos, Organizacion org) {
		readJasper = generarUrlString("jasperTemplates/reportProduct.jasper");
		InputStream imagenEmpresa = new ByteArrayInputStream(org.getLogotipo());
		
		HashMap mapa = new HashMap();
		mapa.put("parameter1", "REPORTE DE PRODUCTOS");
		mapa.put("nombreEmpresa", org.getNombre());
		mapa.put("empresaTitle", org.getNombre());
		mapa.put("logotipo", imagenEmpresa);
		
		List<HashMap> listaHashsParametros = new ArrayList();
		listaHashsParametros.add(mapa);

		String path = "";
		if (modoFile)
			path = new SistemaOperativo().getDirectorioTemporal() + new SistemaOperativo().getSeparadorDeArchivos() + ""
					+ "REPORTE_DE_PRODUCTOS";		
		return generarProductosPdfPFromJasper(listaHashsParametros, productos, path);
	}
		
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ControlVisorPdf generarReporteProductosClasificacionPdf(ProductoTipo productoTipo, List<Producto> productos, Organizacion org) {
		readJasper = generarUrlString("jasperTemplates/reportProduct.jasper");
		InputStream imagenEmpresa = new ByteArrayInputStream(org.getLogotipo());
		
		HashMap mapa = new HashMap();
		mapa.put("parameter1",
				"REPORTE PRODUCTOS DE : ''" + productoTipo.getNombre().toUpperCase() + "''");
		mapa.put("empresaTitle", org.getNombre());
		mapa.put("nombreEmpresa", org.getNombre());
		mapa.put("logotipo", imagenEmpresa);

		List<HashMap> listaHashsParametros = new ArrayList();
		listaHashsParametros.add(mapa);
		

		String path = "";
		if (modoFile)
			path = new SistemaOperativo().getDirectorioTemporal() + new SistemaOperativo().getSeparadorDeArchivos() + ""
					+ "REPORTE_DE_PRODUCTOS_" + productoTipo.getNombre().toUpperCase();		
		return generarProductosPdfPFromJasper(listaHashsParametros, productos, path);
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ControlVisorPdf generarReporteProveedoresPdf(List<Proveedor> proveedores, Organizacion org) {
		readJasper = generarUrlString("jasperTemplates/reportProduct.jasper");
		InputStream imagenEmpresa = new ByteArrayInputStream(org.getLogotipo());
		
		HashMap mapa = new HashMap();
		mapa.put("parameter1", "REPORTE DE PROVEEDORES");
		mapa.put("empresaTitle", org.getNombre());
		mapa.put("nombreEmpresa", org.getNombre());
		mapa.put("logotipo", imagenEmpresa);

		List<HashMap> listaHashsParametros = new ArrayList();
		listaHashsParametros.add(mapa);
		

		String path = "";
		if (modoFile)
			path = new SistemaOperativo().getDirectorioTemporal() + new SistemaOperativo().getSeparadorDeArchivos() + ""
					+ "REPORTE_DE_PROVEEDORES";		
		return generarProveedoresPdfPFromJasper(listaHashsParametros, proveedores, path);
	}
	//*************** METODOS LOCALES ***********************

	/**
	 * generador pdf cotizaciones
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ControlVisorPdf generarPdfCotizacionesFromJasper(List<HashMap> listaHashsParametros, List<Cotizacion> lista,
			String pathSalida) {

		ControlVisorPdf control = new ControlVisorPdf();

		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			print = JasperFillManager.fillReport(readJasper, hashParametros, new JRBeanCollectionDataSource(lista));

			if (modoFile) {
				String resultadoRuta = pathSalida + ".pdf";
				JasperExportManager.exportReportToPdfFile(print, resultadoRuta);
				control.setFileContent(getFileContentFromFile(resultadoRuta));
				control.setMensaje(resultadoRuta);
			} else
				control.setFileContent(getFileContentFromByte(JasperExportManager.exportReportToPdf(print)));

		} catch (JRException e) {
			control.setMensaje("ERROR" + " " + e.getMessage());
			e.printStackTrace();
		}
		return control;
	}
	
	/**
	 * generador pdf requisiciones
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ControlVisorPdf generarPdfRequisicionFromJasper(List<HashMap> listaHashsParametros, List<RequisicionProducto> lista,
			String pathSalida) {

		ControlVisorPdf control = new ControlVisorPdf();

		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			print = JasperFillManager.fillReport(readJasper, hashParametros, new JRBeanCollectionDataSource(lista));

			if (modoFile) {
				String resultadoRuta = pathSalida + ".pdf";
				JasperExportManager.exportReportToPdfFile(print, resultadoRuta);
				control.setFileContent(getFileContentFromFile(resultadoRuta));
				control.setMensaje(resultadoRuta);
			} else
				control.setFileContent(getFileContentFromByte(JasperExportManager.exportReportToPdf(print)));

		} catch (JRException e) {
			control.setMensaje("ERROR" + " " + e.getMessage());
			e.printStackTrace();
		}
		return control;
	}

	/**
	 * generador pdf Proveedores
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ControlVisorPdf generarProductosPdfPFromJasper(List<HashMap> listaHashsParametros, List<Producto> lista,
			String pathSalida) {

		ControlVisorPdf control = new ControlVisorPdf();

		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			print = JasperFillManager.fillReport(readJasper, hashParametros, new JRBeanCollectionDataSource(lista));

			if (modoFile) {
				String resultadoRuta = pathSalida + ".pdf";
				JasperExportManager.exportReportToPdfFile(print, resultadoRuta);
				control.setFileContent(getFileContentFromFile(resultadoRuta));
				control.setMensaje(resultadoRuta);
			} else
				control.setFileContent(getFileContentFromByte(JasperExportManager.exportReportToPdf(print)));

		} catch (JRException e) {
			control.setMensaje("ERROR" + " " + e.getMessage());
			e.printStackTrace();
		}
		return control;
	}
	
	/**
	 * generador pdf Proveedores
	 * **/
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ControlVisorPdf generarProveedoresPdfPFromJasper(List<HashMap> listaHashsParametros, List<Proveedor> lista,
			String pathSalida) {

		ControlVisorPdf control = new ControlVisorPdf();

		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			print = JasperFillManager.fillReport(readJasper, hashParametros, new JRBeanCollectionDataSource(lista));

			if (modoFile) {
				String resultadoRuta = pathSalida + ".pdf";
				JasperExportManager.exportReportToPdfFile(print, resultadoRuta);
				control.setFileContent(getFileContentFromFile(resultadoRuta));
				control.setMensaje(resultadoRuta);
			} else
				control.setFileContent(getFileContentFromByte(JasperExportManager.exportReportToPdf(print)));

		} catch (JRException e) {
			control.setMensaje("ERROR" + " " + e.getMessage());
			e.printStackTrace();
		}
		return control;
	}
	//*****************************************************
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private HashMap construirHashMapParametros(List<HashMap> parametros) {
		HashMap parametrosGenerados = new HashMap();
		for (HashMap hashMap : parametros) {
			Iterator iterador = hashMap.keySet().iterator();
			while (iterador.hasNext()) {
				Object parametro = iterador.next();
				parametrosGenerados.put(parametro, hashMap.get(parametro));
			}
		}
		return parametrosGenerados;
	}

	private String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}

	private AMedia getFileContentFromFile(String filePath) {
		AMedia contenido = null;
		try {
			File archivoPdf = new File(filePath);
			byte[] buffer = new byte[(int) archivoPdf.length()];
			FileInputStream fs = new FileInputStream(archivoPdf);
			fs.read(buffer);
			fs.close();
			ByteArrayInputStream is = new ByteArrayInputStream(buffer);
			contenido = new AMedia("report", "pdf", "application/pdf", is);
		} catch (Exception e) {
			System.err.println("[line 122] ReportesBuild.java > getFileContentFromFile(String filePath)");
			e.printStackTrace();
		}
		return contenido;
	}

	private AMedia getFileContentFromByte(byte[] arrayBytes) {
		AMedia contenido = null;
		try {
			contenido = new AMedia("report", "pdf", "application/pdf", arrayBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contenido;
	}
}
