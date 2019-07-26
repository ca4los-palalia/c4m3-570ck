package com.came.stock.utilidades;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

import com.came.stock.beans.CtrlError;

@Repository
public class StockUtilString {

	
	public CtrlError formatXmlPretty(String xml) {
		CtrlError ctrl = new CtrlError();
		
		if(xml != null && !xml.isEmpty()) {
			try {
				final InputSource src = new InputSource(new StringReader(xml));
				final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
						.getDocumentElement();
				final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));
				
				final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
				final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
				final LSSerializer writer = impl.createLSSerializer();

				writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
				writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);
				
				ctrl.setMensaje(writer.writeToString(document));
				ctrl.setOk(true);
			} catch (Exception e) {
				ctrl.setExcepcion(e);
				ctrl.setMensaje(e.getMessage() + "\n" + exceptionToString(e));
				ctrl.setObjetoTratado(xml);
			}
		} else
			ctrl.setMensaje("XML Empty: " + xml);
		return ctrl;
	}
	
	public String removerPuntoCero(String valor) {
		String salida = "";
		for (int i = 0; i < valor.length(); i++) {
			String caracter = valor.substring(i, i + 1);
			if (caracter.equals(".")) {
				break;
			}
			salida = salida + caracter;
		}
		return salida;
	}
	
	public String removerPuntoCeroDeCadena(String valor) {
		String salida = "";
		for (int i = 0; i < valor.length(); i++) {
			String caracter = valor.substring(i, i + 1);
			if (!caracter.equals("."))
				salida = salida + caracter;
		}
		return salida;
	}
	
	public String exceptionToString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public String extraerMensajeSinComillas(String stringTotal) {
		String reconstruccion = "";
		String charString = "";
		for (int i = 0; i < stringTotal.length(); i++) {
			charString = stringTotal.substring(i, (i + 1));
			if (!charString.contains("\"")) {
				reconstruccion += charString;
			}
		}
		return reconstruccion;
	}
	
}
