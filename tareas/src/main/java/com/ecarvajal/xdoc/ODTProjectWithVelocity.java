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
      // 1) Load ODT file by filling Velocity template engine and cache it to the registry
      InputStream in = ODTProjectWithVelocity.class.getResourceAsStream("ODT.odt");
      IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in,TemplateEngineKind.Velocity);

      // 2) Create context Java model
      IContext context = report.createContext();
      Project project = new Project("XDocReport");
      context.put("project", project);

      // 3) Generate report by merging Java model with the ODT
      OutputStream out = new FileOutputStream(new File("ODTProjectWithVelocity_Out.odt"));
      report.process(context, out);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (XDocReportException e) {
      e.printStackTrace();
    }
  }
}
