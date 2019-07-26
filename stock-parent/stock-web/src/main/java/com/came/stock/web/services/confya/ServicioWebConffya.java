package com.came.stock.web.services.confya;

public interface ServicioWebConffya extends javax.xml.rpc.Service {
	public java.lang.String getServicioWebConffyaSoapAddress();

	public ServicioWebConffyaSoap getServicioWebConffyaSoap() throws javax.xml.rpc.ServiceException;

	public ServicioWebConffyaSoap getServicioWebConffyaSoap(java.net.URL portAddress)
			throws javax.xml.rpc.ServiceException;
}
