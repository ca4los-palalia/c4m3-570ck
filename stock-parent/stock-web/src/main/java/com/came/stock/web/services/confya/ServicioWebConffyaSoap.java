package com.came.stock.web.services.confya;

public interface ServicioWebConffyaSoap extends java.rmi.Remote {
	public java.lang.String catalogoUnidadResponsable(short numeroEmpresa, short ejercicio)
			throws java.rmi.RemoteException;

	public java.lang.String catalogoFuenteFinanciamiento(short numeroEmpresa, short ejercicio)
			throws java.rmi.RemoteException;

	public java.lang.String catalogoPartidaGenerica(short numeroEmpresa, short ejercicio)
			throws java.rmi.RemoteException;

	public java.lang.String catalogoProgramas(short numeroEmpresa, short ejercicio) throws java.rmi.RemoteException;

	public java.lang.String catalogoProyecto(short numeroEmpresa, short ejercicio) throws java.rmi.RemoteException;

	public java.lang.String catalogoEmpresas() throws java.rmi.RemoteException;

	public java.lang.String catalogoEmpresasEjercicio(short numEjercicio) throws java.rmi.RemoteException;

	public java.lang.String catalogoClavesMovimiento(short numeroEmpresa, short ejercicio)
			throws java.rmi.RemoteException;

	public java.lang.String catalogoJustificacionCompromiso(short numeroEmpresa, short ejercicio)
			throws java.rmi.RemoteException;

	public java.lang.String presupuestodeEgresos(short numeroEmpresa, short ejercicio, short mes)
			throws java.rmi.RemoteException;

	public java.lang.String definicionPresupuestodeEgresos(short numeroEmpresa, short ejercicio)
			throws java.rmi.RemoteException;

	public java.lang.String guardarCompromiso(short numeroEmpresa, short ejercicio, java.lang.String compromisos)
			throws java.rmi.RemoteException;

	public java.lang.String validarEmpresa(Informacion_Empresa datosEmpresa) throws java.rmi.RemoteException;

	public java.lang.String holaPrueba(Informacion_Empresa datosEmpresa) throws java.rmi.RemoteException;
}
