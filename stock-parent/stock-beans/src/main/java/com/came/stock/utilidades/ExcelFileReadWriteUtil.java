package com.came.stock.utilidades;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.regex.ParseException;

public class ExcelFileReadWriteUtil {

	@SuppressWarnings({ "rawtypes", "resource", "unchecked" })
	public List readXLSXFile(InputStream streamReading, int indiceSheet) throws ParseException {
		List cellDataList = new ArrayList();
		try {
			XSSFWorkbook workBook = new XSSFWorkbook(streamReading);
			XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
			Iterator rowIterator = hssfSheet.rowIterator();
			
			while (rowIterator.hasNext()) {
				XSSFRow hssfRow = (XSSFRow) rowIterator.next();
				Iterator iterator = hssfRow.cellIterator();
				List cellTempList = new ArrayList();
				while (iterator.hasNext()) {
					XSSFCell hssfCell = (XSSFCell) iterator.next();
					cellTempList.add(hssfCell);
				}
				cellDataList.add(cellTempList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Leer(cellDataList);
		
		return cellDataList;
	}

	@SuppressWarnings({ "rawtypes", "unused" })
	private void Leer(List cellDataList) {
		for (int i = 0; i < cellDataList.size(); i++) {
			List cellTempList = (List) cellDataList.get(i);
			for (int j = 0; j < cellTempList.size(); j++) {
				XSSFCell hssfCell = (XSSFCell) cellTempList.get(j);
				String stringCellValue = hssfCell.toString();
				System.out.print(stringCellValue + " ");
			}
			System.out.println();
		}
	}
}
