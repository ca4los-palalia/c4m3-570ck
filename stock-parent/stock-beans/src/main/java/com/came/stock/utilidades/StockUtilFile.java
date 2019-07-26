package com.came.stock.utilidades;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Repository;

import com.came.stock.beans.CtrlError;

@Repository
public class StockUtilFile {

	private String exceptionToString(Exception e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
	
	public CtrlError readInputStream(InputStream inputStream) {
		CtrlError ctrl = new CtrlError();
		try {
			if (inputStream != null) {
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[1024];

				while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
					byteArrayOutputStream.write(data, 0, nRead);
				}
				byteArrayOutputStream.flush();
				ctrl.setMensaje(new String(byteArrayOutputStream.toByteArray(), StandardCharsets.UTF_8));
				ctrl.setOk(true);
			}
		} catch (Exception e) {
			ctrl.setExcepcion(e);
			ctrl.setMensaje(e.getMessage() + "\n" + exceptionToString(e));
			ctrl.setObjetoTratado(inputStream);
		}
		return ctrl;
	}
	
	
}
