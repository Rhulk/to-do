package com.ecarvajal.ticketExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Ticket {

	public static void generar(String codProducto, String nombre, String cantidad, String descuento, String precio, int posicion) {
        String excelFilePath = "src/main/resources/static/doc/Ticket-de-compra.xlsx";
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
 
                int columnCount = 1;
	                 
                Cell cell = row.createCell(columnCount);
                cell.setCellValue(rowCount);
	                 
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
            
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static/doc/Ticket.xlsx");
            

            libro.write(outputStream);
            libro.close();
            outputStream.close();
            System.out.println(" -- Grabado registro en Excel -- Ticket");
            System.out.println(" -- codProducto: "+codProducto+" nombre: "+nombre+" cantidad: "+cantidad+" descuento: "+descuento+" precio:"+precio);
	             
        } catch (IOException | EncryptedDocumentException ex) {
       		ex.printStackTrace();
       		
        }
    }
}
