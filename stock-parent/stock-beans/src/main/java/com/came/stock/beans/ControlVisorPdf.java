package com.came.stock.beans;

import java.io.Serializable;

import org.zkoss.util.media.AMedia;

public class ControlVisorPdf implements Serializable {

	private static final long serialVersionUID = -1412698947263033059L;
	
	private AMedia fileContent;
	private String mensaje;

	public AMedia getFileContent() {
		return fileContent;
	}

	public void setFileContent(AMedia fileContent) {
		this.fileContent = fileContent;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
