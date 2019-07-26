package com.came.stock.web.vm.ordencompra.utils;

import java.io.File;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.zkoss.bind.annotation.Init;

import com.came.stock.beans.SistemaOperativo;
import com.came.stock.model.domain.Cotizacion;
import com.came.stock.model.domain.OrdenCompra;
import com.came.stock.model.domain.OrdenCompraInbox;
import com.came.stock.model.domain.Organizacion;

public class OrdenCompraMetaclass extends OrdenCompraVariables {
	private static final long serialVersionUID = 5093877120990395398L;

	@Init
	public void init() {
		initObjects();
		initProperties();
	}

	public void initObjects() {
		loadOrdenesCompraInbox();
	}

	public void initProperties() {
		
	}

	private void loadOrdenesCompraInbox() {
		ordenesCompraInbox = (List<OrdenCompraInbox>) ordenCompraInboxRest
				.getAll((Organizacion) sessionUtils.getFromSession("FIRMA")).getSingle();

		ordenCompraInboxSeleccionada = new OrdenCompraInbox();
		for (OrdenCompraInbox compraInbox : ordenesCompraInbox) {
			if ((compraInbox.getLeido() != null) && (!compraInbox.getLeido().booleanValue())) {
				compraInbox.setIcono("/images/toolbar/newEmail.png");
			}
		}
	}
	
	public String construirXmlOrden(OrdenCompra ordenCompra){
		boolean writeFile = false;
		String xml = "";
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			
			Element conffya = doc.createElement("conffya");
			doc.appendChild(conffya);

			Element compromisos = doc.createElement("compromisos");
			conffya.appendChild(compromisos);
			
			Element totalElementos = doc.createElement("totalElementos");
			totalElementos.appendChild(doc.createTextNode("1"));
			compromisos.appendChild(totalElementos);
			
			Element compromiso = doc.createElement("compromiso");
			compromisos.appendChild(compromiso);
			
			
			
			
			
			Element idPedido = doc.createElement("idPedido");
			idPedido.appendChild(doc.createTextNode(ordenCompra.getIdOrdenCompra().toString()));
			compromiso.appendChild(idPedido);
			
			
			//********** RFC **************
			String rfc = "EMPTY";
			if(ordenCompra.getCotizacion().getProveedor().getRfc() != null)
				rfc = ordenCompra.getCotizacion().getProveedor().getRfc();
			
			Element rfcProveedor = doc.createElement("rfcProveedor");
			rfcProveedor.appendChild(doc.createTextNode(rfc));
			compromiso.appendChild(rfcProveedor);
			
			
			//********** PROVEEDOR NOMBRE **************
			Element nombreProveedor = doc.createElement("nombreProveedor");
			nombreProveedor.appendChild(doc.createTextNode(ordenCompra.getCotizacion().getProveedor().getNombre()));
			compromiso.appendChild(nombreProveedor);
			
			
			//********** REFERENCIA ********************
			Element referencia = doc.createElement("referencia");
			referencia.appendChild(doc.createTextNode(ordenCompra.getCotizacion().getRequisicion().getIdRequisicion().toString()));
			compromiso.appendChild(referencia);
			
			
			Calendar sameDate = Calendar.getInstance();
			//sameDate = ordenCompra.getCotizacion().getRequisicion().getFecha();
			sameDate.set(Calendar.YEAR, 2015);
			
			Element fecha = doc.createElement("fecha");
			fecha.appendChild(doc.createTextNode(stockUtils.convertirCalendarToStringFormato2(sameDate)));
			compromiso.appendChild(fecha);
			
			
			
			
			Element pedidos = doc.createElement("pedidos");
			compromiso.appendChild(pedidos);
			
			Element pedido = doc.createElement("pedido");
			pedidos.appendChild(pedido);
			
			if(cotizacionesConProductos != null){
				for (Cotizacion item : cotizacionesConProductos) {
					Element referenciaM = doc.createElement("referenciaM");
					referenciaM.appendChild(doc.createTextNode(item.getRequisicionProducto().getCofiaPartidaGenerica().getClave()));
					pedido.appendChild(referenciaM);
					
					Element importeTotal = doc.createElement("importeTotal");
					importeTotal.appendChild(doc.createTextNode(String.valueOf(item.getRequisicionProducto().getTotalProductoPorUnidad())));
					pedido.appendChild(importeTotal);
					
					Element descripcionCompra = doc.createElement("descripcionCompra");
					descripcionCompra.appendChild(doc.createTextNode(item.getProducto().getNombre()));
					pedido.appendChild(descripcionCompra);
					
					//*********************
					
					Element claves = doc.createElement("claves");
					pedido.appendChild(claves);
					
					Element clave = doc.createElement("clave");
					claves.appendChild(clave);
					
					Element claveMovimiento = doc.createElement("claveMovimiento");
					claveMovimiento.appendChild(doc.createTextNode(item.getRequisicionProducto().getCofiaPartidaGenerica().getClave()));
					clave.appendChild(claveMovimiento);
					
					Element importe = doc.createElement("importe");
					importe.appendChild(doc.createTextNode(String.valueOf(item.getRequisicionProducto().getTotalProductoPorUnidad())));
					clave.appendChild(importe);
					
					Element clavePresupuestal = doc.createElement("clavePresupuestal");
					clavePresupuestal.appendChild(doc.createTextNode(stockUtils.Desencriptar(item.getRequisicionProducto().getClavePre())));
					clave.appendChild(clavePresupuestal);
				}
			}
			
			if(writeFile){
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(
						new SistemaOperativo().getDirectorioDeInicioDelUsuario() 
						+ new SistemaOperativo().getSeparadorDeArchivos()
						+ "archivo.xml"));
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				transformer.transform(source, result);
			}else
				xml = xmlToString(doc);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	private String xmlToString(Document doc) {
	    try {
	        StringWriter sw = new StringWriter();
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

	        transformer.transform(new DOMSource(doc), new StreamResult(sw));
	        return sw.toString();
	    } catch (Exception ex) {
	        throw new RuntimeException("Error converting to String", ex);
	    }
	}
}
