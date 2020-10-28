package com.ecarvajal.ticketExcel;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

public class Ticket {

	public void generar(String codProducto, String nombre, String cantidad, int descuento, double precio
			, int posicion, boolean nueva) {
		String excelFilePath;
		if (nueva) {
			excelFilePath = "src/main/resources/static/doc/Ticket-plantilla.xls";
		}else {
			excelFilePath = "src/main/resources/static/doc/Ticket.xls";
		}
        try {
        	
        	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        	
        		//  Workbook workbook = WorkbookFactory.create(inputStream);
        	Workbook libro= WorkbookFactory.create(inputStream);
        		// XSSFWorkbook libro= new XSSFWorkbook();
        	Sheet sheet = libro.getSheetAt(0);
        	
        	
            Object[][] bookData = {{codProducto, nombre, cantidad, descuento, precio},};
 
            //int rowCount = sheet.getLastRowNum();
            int rowCount = posicion;
 
            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);
 
                int columnCount = 0;
	                 
                Cell cell = row.createCell(columnCount);
                //cell.setCellValue(rowCount);
	                 
                for (Object field : aBook) {
                    cell = row.createCell(++columnCount);
                    if (field instanceof String) {
                        cell.setCellValue((String) field);
                    } else if (field instanceof Integer) {
                        cell.setCellValue((Integer) field);
                    }
                }	 
            }
            inputStream.close();
            
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static/doc/Ticket.xls");
            

            libro.write(outputStream);
            libro.close();
            outputStream.close();
            System.out.println(" -- Grabado registro en Excel -- Ticket");
            System.out.println(" -- codProducto: "+codProducto+" nombre: "+nombre+" cantidad: "+cantidad+" descuento: "+descuento+" precio:"+precio);
	             
        } catch (IOException | EncryptedDocumentException ex) {
       		ex.printStackTrace();
       		
        }
    }
	/*
	 * Se limpia el excel Ticket para una nueva compra.
	 * 
	 */
	public void limpiar( ) {

		String excelFilePath = "src/main/resources/static/doc/Ticket-plantilla.xls";


        try {
        	
        	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        	
        		//  Workbook workbook = WorkbookFactory.create(inputStream);
        	Workbook libro= WorkbookFactory.create(inputStream);
        		// XSSFWorkbook libro= new XSSFWorkbook();
        	Sheet sheet = libro.getSheetAt(0);
        	


            inputStream.close();
            
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static/doc/Ticket.xls");
            

            libro.write(outputStream);
            libro.close();
            outputStream.close();
            System.out.println(" -- Se resetea el ticket");

	             
        } catch (IOException | EncryptedDocumentException ex) {
       		ex.printStackTrace();
       		
        }
    }
	/*
	 * Se guarda el excel de la compra
	 * 
	 * 
	 */
	public void tramita(String ficheroCompra ) {

		String excelFilePath = "src/main/resources/static/doc/Ticket.xls";


        try {
        	
        	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        	Workbook libro= WorkbookFactory.create(inputStream);
        	Sheet sheet = libro.getSheetAt(0);
        	
        	// estilos Pendiente.

        	HSSFWorkbook workbook = new HSSFWorkbook();
        	HSSFCellStyle style = workbook.createCellStyle();
        	
        	Row row = sheet.createRow(5);
        	Cell cellNcliente = row.createCell(1);
        	cellNcliente.setCellValue((String) "Nombre Cliente:");	
        	CellStyle styleNcliente = workbook.createCellStyle();
        	row.setRowStyle(styleNcliente);
        	  style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        	  
    /*    	
        	XSSFCellStyle styleNcliente = (XSSFCellStyle)cellNcliente.getCellStyle();
        	XSSFColor myColor = new XSSFColor(Color.RED);
        	styleNcliente.setFillForegroundColor(myColor);      	
       */ 	

        	
        	Cell cell = row.createCell(2);     	

        	cell.setCellValue((String) " Pepito");	
        	
            inputStream.close();
            
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static/doc/Ticket"+ficheroCompra+".xls");
            

            libro.write(outputStream);
            libro.close();
            outputStream.close();
            System.out.println(" -- Se resetea el ticket");

	             
        } catch (IOException | EncryptedDocumentException ex) {
       		ex.printStackTrace();
       		
        }
    }
}
