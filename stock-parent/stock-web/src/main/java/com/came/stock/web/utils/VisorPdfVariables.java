package com.came.stock.web.utils;

import java.io.File;

import org.zkoss.util.media.AMedia;

import com.came.stock.web.vm.BasicStructure;

public class VisorPdfVariables  extends BasicStructure {

	private static final long serialVersionUID = -6785462887080039766L;
	protected String title;
	protected String filePath;
	protected boolean fileuploaded = false;
	protected AMedia fileContent;
	protected File archivoPdf;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isFileuploaded() {
		return fileuploaded;
	}

	public void setFileuploaded(boolean fileuploaded) {
		this.fileuploaded = fileuploaded;
	}

	public AMedia getFileContent() {
		return fileContent;
	}

	public void setFileContent(AMedia fileContent) {
		this.fileContent = fileContent;
	}	
	
}
