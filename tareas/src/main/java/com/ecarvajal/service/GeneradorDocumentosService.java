package com.ecarvajal.service;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import fr.opensagres.xdocreport.converter.ConverterTypeTo;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.images.FileImageProvider;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;

public class GeneradorDocumentosService {

    /**
     * Toma la ruta de fichero y devuelve un stream abierto para su lectura.
     * 
     * @param filePath
     * @return
     * @throws IOException
     */
    public InputStream loadDocumentAsStream(String filePath) throws IOException {
        URL url = new File(filePath).toURI().toURL();
        InputStream documentAsStream = url.openStream();
        return documentAsStream;
    }

    /**
     * Carga en el objeto context el valor de las variables.
     * 
     * @param report
     * @param variablesToBeReplaced
     * @return
     * @throws XDocReportException
     */
    public IContext cargarVariables(Map<String, Object> variables, IContext context) throws XDocReportException {
        for (Map.Entry<String, Object> variable : variables.entrySet()) {
            context.put(variable.getKey(), variable.getValue());
        }

        return context;
    }

    /**
     * Carga en el objeto context las imágenes a partir de las rutas dadas y guarda en
     * el objeto report los metadatos de las imágenes.
     * 
     * @param report
     * @param variablesToBeReplaced
     * @param context
     */
    public void cargarImagenes(IXDocReport report, Map<String, String> variables, IContext context) {
        FieldsMetadata metadata = new FieldsMetadata();
        for (Map.Entry<String, String> variable : variables.entrySet()) {
            metadata.addFieldAsImage(variable.getKey());
            context.put(variable.getKey(), new FileImageProvider(new File(variable.getValue()), true));
        }

        report.setFieldsMetadata(metadata);
    }

    /**
     * Toma la plantilla y devuelve un documento de salida como byte[]
     * 
     * @param rutaPlantilla
     * @param templateEngine
     *            Template Engine: Freemarker or Velocity
     * @param variablesMap
     * @param imagenesPathMap
     * @param convertirPdf
     *            Indica si se quiere convertir el documento a PDF
     * @return
     * @throws IOException
     * @throws XDocReportException
     */
    public byte[] generarDocumento(String rutaPlantilla, TemplateEngineKind templateEngine,
            Map<String, Object> variablesMap, Map<String, String> imagenesPathMap, boolean convertirPdf)
                    throws IOException, XDocReportException {
        // Cargar el fichero y configurar el Template Engine
        InputStream inputStream = loadDocumentAsStream(rutaPlantilla);
        IXDocReport xdocReport = XDocReportRegistry.getRegistry().loadReport(inputStream, templateEngine);

        // Se crea el contexto y se cargan las variables de reemplazo
        IContext context = xdocReport.createContext();
        cargarVariables(variablesMap, context);
        cargarImagenes(xdocReport, imagenesPathMap, context);

        // Se genera la salida a partir de la plantilla.
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        if (convertirPdf) {
            // Se configura PDF como el formato de salida del conversor
            Options options = Options.getTo(ConverterTypeTo.PDF);

            // Se genera el documento remplazando las variables y convirtiendo
            // la salida a PDF.
            xdocReport.convert(context, options, out);
        } else {
            // Se genera el documento remplazando las variables manteniendo el
            // formato original.
            xdocReport.process(context, out);
        }

        return out.toByteArray();
    }
}
