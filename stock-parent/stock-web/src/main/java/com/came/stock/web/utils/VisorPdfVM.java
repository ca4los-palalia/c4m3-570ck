package com.came.stock.web.utils;

import java.io.IOException;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

import com.came.stock.beans.ControlVisorPdf;

@VariableResolver({ DelegatingVariableResolver.class })
public class VisorPdfVM extends VisorPdfVariables {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#visorPdfVMModalDialog")
	private Window visorPdfVMModalDialog;
	private String globalCommandName;
	

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("executeNameMethod") String executeNameMethod,
			@ExecutionArgParam("titulo") String titulo,
			@ExecutionArgParam("fuente") ControlVisorPdf control) {
		Selectors.wireComponents(view, this, false);

		globalCommandName = executeNameMethod;
		title = titulo;
		if(control != null && control.getFileContent() != null){
			fileuploaded = true;
			fileContent = control.getFileContent();
			filePath = control.getMensaje();
		}
			
	}

	// -----------------------------------------------------------

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@Command
	public void confirm() {
		if(filePath != null && !filePath.equals("")){
			if (archivoPdf != null && archivoPdf.exists())
				if (archivoPdf.delete()){
					System.err.println("reporte temporal eliminado");
				}
			visorPdfVMModalDialog.detach();
		}else
			visorPdfVMModalDialog.detach();
	}

	// --------------------------------------------------------------------------------
	
	@Command
	@NotifyChange("fileContent")
	public void downloadPdf() throws IOException {
		
		Filedownload.save(fileContent.getByteData(), "application/pdf",
				"requisicion.pdf");

	}
	
	

}
