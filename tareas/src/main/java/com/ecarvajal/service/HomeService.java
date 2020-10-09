package com.ecarvajal.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.jopendocument.dom.OOUtils;
import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
	
	public void prueba() {
		
	try {
	 // Load the file.
		 File file = new File("src/main/resources/holaExcel.ods");
		 System.out.println(" Ruta --- "+file.getAbsolutePath());
		 System.out.println(" -- existe ?? " +file.exists());
		 Sheet sheet= SpreadSheet.createFromFile(file).getSheet(0);
	
		 // Change date.
		 sheet.getCellAt("I10").setValue(new Date());
		 // Change strings.
		 sheet.setValueAt("Hola mundo", 1, 1);
		 sheet.getCellAt("B27").setValue("On site support");
		 // Change number.
		               //  C:\git\toDo\to-do\tareas\src\main\resources
		 sheet.getCellAt("F24").setValue(3);
		 // Or better yet use a named range
		 // (relative to the first cell of the range, wherever it might be).
	//	 sheet.getSpreadSheet().getTableModel("Products").setValueAt(1, 5, 4);
		 // Save to file and open it.
		// File outputFile = new File("fillingTest.ods");
		 OOUtils.open(sheet.getSpreadSheet().saveAs(file));
	 
		 System.out.println(" Excel ---");
	} catch (IOException e) {
		
		e.printStackTrace();
	}	 
	 
	}
	public void prueba2(){
		
		  // Create the data to save.
		try {
		  final Object[][] data = new Object[6][2];
		  data[0] = new Object[] { "January", 1 };
		  data[1] = new Object[] { "February", 3 };
		  data[2] = new Object[] { "March", 8 };
		  data[3] = new Object[] { "April", 10 };
		  data[4] = new Object[] { "May", 15 };
		  data[5] = new Object[] { "June", 18 };

		  String[] columns = new String[] { "Month", "Temp" };

		  TableModel model = new DefaultTableModel(data, columns);  
		    
		  // Save the data to an ODS file and open it.
		  final File file = new File("src/main/resources/static/doc/holaExcel.ods");
		  SpreadSheet.createEmpty(model).saveAs(file);
		    
		  OOUtils.open(file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}			  
		  
	}

}
