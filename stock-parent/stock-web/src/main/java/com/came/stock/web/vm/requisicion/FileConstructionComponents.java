package com.came.stock.web.vm.requisicion;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileConstructionComponents {

	private File file;
	private FileOutputStream fileOutputStream;
	private byte[] bytes;

	public FileConstructionComponents(XSSFWorkbook xSSFWorkbook ) {
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			xSSFWorkbook.write(bos);
			bytes = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public FileOutputStream getFileOutputStream() {
		return fileOutputStream;
	}

	public void setFileOutputStream(FileOutputStream fileOutputStream) {
		this.fileOutputStream = fileOutputStream;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	
	
}
