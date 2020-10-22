package com.ecarvajal.xdoc;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;

import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;

public class ODTProjectWithVelocity {

	/**
	 * Generamos Reporte
	 * 
	 */
  public  void xdox() {
    try {
    	File outFile = new File("ODTProjectWithVelocity_Outh.odt");
    System.out.println("Generamos reporte");
      // 1) Load ODT file by filling Velocity template engine and cache it to the registry
      InputStream in = ODTProjectWithVelocity.class.getResourceAsStream("ticket.odt");
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);
      System.out.println(" --- Tramitada la venta.");
      // 2) Create context Java model
      IContext context = report.createContext();
      Project project = new Project("XDocReport");
      context.put("project", project);

      // 3) Generate report by merging Java model with the ODT
      
      System.out.println("-- Ruta del fichero: |"+outFile.getAbsolutePath()+"|");
      OutputStream out = new FileOutputStream(outFile);
      report.process(context, out);
      System.out.println("Creado ticket");
    } catch (IOException e) {
      e.printStackTrace();
    } catch (XDocReportException e) {
      e.printStackTrace();
    }
  }
}
