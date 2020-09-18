package com.ecarvajal.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


 
public class CrearFicherosExcel {
 
	public static void inicial() {
		
		String nombreArchivo="Registro-.xlsx";
		String rutaArchivo= "src/main/resources/static/doc/"+nombreArchivo;
		String hoja="Hoja1";
		
		XSSFWorkbook libro= new XSSFWorkbook();
		XSSFSheet hoja1 = libro.createSheet(hoja);
		//cabecera de la hoja de excel
		String [] header= new String[]{"Código", "Producto","Precio","Unidades"};
		
		//contenido de la hoja de excel
		String [][] document= new String [][]{
				{"AP150","ACCESS POINT TP-LINK TL-WA901ND 450Mbps Wireless N 1RJ45 10-100 3Ant.","112.00","50"},
				{"RTP150","ROUTER TP-LINK TL-WR940ND 10-100Mbpps LAN WAN 2.4 - 2.4835Ghz","19.60","25"},
				{"TRT300","TARJETA DE RED TPLINK TL-WN881ND 300Mpbs Wire-N PCI-Exp.","10.68","15"},
				{"TRT300","DE RED TPLINK TL-WN881ND 300Mpbs Wire-N PCI-Exp.","10.68","15"},
				{"TR0","DE RED TPLINK TL-WN881ND 300Mpbs Wire-N PCI-Exp.","10.68","15"},
				{"TR0","DE RED TPLINK TL-WN881ND 300Mpbs Wire-N PCI-Exp.","10.68","15"}
		};
		
		//poner negrita a la cabecera
		CellStyle style = libro.createCellStyle();
        Font font = libro.createFont();
        font.setBold(true);
        style.setFont(font);
        
        
		//generar los datos para el documento
		for (int i = 0; i <= document.length; i++) {
			XSSFRow row=hoja1.createRow(i);//se crea las filas
			for (int j = 0; j <header.length; j++) {
				if (i==0) {//para la cabecera
						XSSFCell cell= row.createCell(j);//se crea las celdas para la cabecera, junto con la posición
						cell.setCellStyle(style); // se añade el style crea anteriormente 
						cell.setCellValue(header[j]);//se añade el contenido					
				}else{//para el contenido
					XSSFCell cell= row.createCell(j);//se crea las celdas para la contenido, junto con la posición
					cell.setCellValue(document[i-1][j]); //se añade el contenido
				}				
			}
		}
		Row row = hoja1.createRow(0);
		
		
		
		File file;
		file = new File(rutaArchivo);
		try (FileOutputStream fileOuS = new FileOutputStream(file)){						
			if (file.exists()) {// si el archivo existe se elimina
				file.delete();
				System.out.println("Archivo eliminado");
			}
			libro.write(fileOuS);
			fileOuS.flush();
			fileOuS.close();
			System.out.println("Archivo Creado");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void nextRegistro_old() {
		String nombreArchivo="Registro-.xlsx";
		String rutaArchivo= "src/main/resources/static/doc/"+nombreArchivo;
		String hoja="Hoja1";
		
		XSSFWorkbook libro= new XSSFWorkbook();
		XSSFSheet hoja1 = libro.createSheet(hoja);	
		
		// Decide which rows to process
		int rowStart = Math.min(15, hoja1.getFirstRowNum());
		int rowEnd = Math.max(1400, hoja1.getLastRowNum());
		for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {
		   //Row r = hoja1.getRow(rowNum);
		   if (hoja1.getRow(rowNum) == null) {
		      // This whole row is empty
		      // Handle it as needed
			   
		      break;
		   }	
	  
		}	
	}
	


		 
	public static void nextRecord() {
        String excelFilePath = "src/main/resources/static/doc/Registro.xlsx";
         
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
          //  Workbook workbook = WorkbookFactory.create(inputStream);
            Workbook libro= WorkbookFactory.create(inputStream);
           // XSSFWorkbook libro= new XSSFWorkbook();
 
            Sheet sheet = libro.getSheetAt(0);
	 
            Object[][] bookData = {
                    {"The Passionate Programmer", "Chad Fowler", 16},
                    {"Software Craftmanship", "Pete McBreen", 26},
                    {"The Art of Agile Development", "James Shore", 32},
                    {"Continuous Delivery", "Jez Humble", 41},
            };
 
            int rowCount = sheet.getLastRowNum();
 
            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);
 
                int columnCount = 0;
	                 
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
	 
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static/doc/Registro.xlsx");
            libro.write(outputStream);
            libro.close();
            outputStream.close();
	             
        } catch (IOException | EncryptedDocumentException ex) {
       		ex.printStackTrace();
        }
    }
	
	public static void nextRecord_Falta_() {
        String excelFilePath = "src/main/resources/static/doc/Registro.xlsx";
         
        try {
            FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
          //  Workbook workbook = WorkbookFactory.create(inputStream);
            Workbook libro= WorkbookFactory.create(inputStream);
           // XSSFWorkbook libro= new XSSFWorkbook();
 
            Sheet sheet = libro.getSheetAt(0);
	 
            Object[][] bookData = {
                    {"The Passionate Programmer", "Chad Fowler", 16},
                    {"Software Craftmanship", "Pete McBreen", 26},
                    {"The Art of Agile Development", "James Shore", 32},
                    {"Continuous Delivery", "Jez Humble", 41},
            };
 
            int rowCount = sheet.getLastRowNum();
 
            for (Object[] aBook : bookData) {
                Row row = sheet.createRow(++rowCount);
 
                int columnCount = 0;
	                 
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
	 
            FileOutputStream outputStream = new FileOutputStream("src/main/resources/static/doc/Registro.xlsx");
            libro.write(outputStream);
            libro.close();
            outputStream.close();
	             
        } catch (IOException | EncryptedDocumentException ex) {
       		ex.printStackTrace();
        }
    }

	
	public static void lectura() {
		String nombreArchivo="Registro.xlsx";
		String rutaArchivo= "src/main/resources/static/doc/"+nombreArchivo;
		String hoja = "Hoja1";
 
		try (FileInputStream file = new FileInputStream(new File(rutaArchivo))) {
			// leer archivo excel
			XSSFWorkbook worbook = new XSSFWorkbook(file);
			//obtener la hoja que se va leer
			XSSFSheet sheet = worbook.getSheetAt(0);
			//obtener todas las filas de la hoja excel
			Iterator<Row> rowIterator = sheet.iterator();
 
			Row row;
			// se recorre cada fila hasta el final
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				//se obtiene las celdas por fila
				Iterator<Cell> cellIterator = row.cellIterator();
				Cell cell;
				//se recorre cada celda
				while (cellIterator.hasNext()) {
					// se obtiene la celda en específico y se la imprime
					cell = cellIterator.next();
					System.out.print(cell.getStringCellValue()+" | ");
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
 
}
