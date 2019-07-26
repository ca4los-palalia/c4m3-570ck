package com.came.stock.web.services.confya;

import java.util.HashSet;
import java.util.Iterator;

@SuppressWarnings("serial")
public class ServicioWebConffyaLocator extends org.apache.axis.client.Service implements ServicioWebConffya {

    public ServicioWebConffyaLocator() {
    }


    public ServicioWebConffyaLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ServicioWebConffyaLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ServicioWebConffyaSoap
    private java.lang.String ServicioWebConffyaSoap_address = "http://legajo-virtual.com:82/ConffyaGubernamental/ServicioWebConffya.asmx";

    public java.lang.String getServicioWebConffyaSoapAddress() {
        return ServicioWebConffyaSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ServicioWebConffyaSoapWSDDServiceName = "ServicioWebConffyaSoap";

    public java.lang.String getServicioWebConffyaSoapWSDDServiceName() {
        return ServicioWebConffyaSoapWSDDServiceName;
    }

    public void setServicioWebConffyaSoapWSDDServiceName(java.lang.String name) {
        ServicioWebConffyaSoapWSDDServiceName = name;
    }

    public ServicioWebConffyaSoap getServicioWebConffyaSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ServicioWebConffyaSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getServicioWebConffyaSoap(endpoint);
    }

    public ServicioWebConffyaSoap getServicioWebConffyaSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ServicioWebConffyaSoapStub _stub = new ServicioWebConffyaSoapStub(portAddress, this);
            _stub.setPortName(getServicioWebConffyaSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setServicioWebConffyaSoapEndpointAddress(java.lang.String address) {
        ServicioWebConffyaSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ServicioWebConffyaSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                ServicioWebConffyaSoapStub _stub = new ServicioWebConffyaSoapStub(new java.net.URL(ServicioWebConffyaSoap_address), this);
                _stub.setPortName(getServicioWebConffyaSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    @SuppressWarnings("rawtypes")
	public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("ServicioWebConffyaSoap".equals(inputPortName)) {
            return getServicioWebConffyaSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("CatalogosConffya/", "ServicioWebConffya");
    }

    @SuppressWarnings("rawtypes")
	private HashSet ports = null;

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("CatalogosConffya/", "ServicioWebConffyaSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ServicioWebConffyaSoap".equals(portName)) {
            setServicioWebConffyaSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
