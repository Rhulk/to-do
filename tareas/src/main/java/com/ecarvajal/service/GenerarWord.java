package com.ecarvajal.service;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hpsf.MarkUnsupportedException;
import org.apache.poi.hpsf.NoPropertySetStreamException;
import org.apache.poi.hpsf.PropertySetFactory;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderEvent;
import org.apache.poi.poifs.eventfilesystem.POIFSReaderListener;
import org.apache.poi.extractor.POIOLE2TextExtractor;

import com.ecarvajal.model.AnalizadorWord;

// add class master


public class GenerarWord implements POIFSReaderListener {

    private AnalizadorWord datos = null;

    public void setDatos(AnalizadorWord datos) {
    	this.datos = datos;
    }
    
    //    Clase que almacena las características estándar de un    documento.
    public void processPOIFSReaderEvent(POIFSReaderEvent event) {
    	SummaryInformation si = null;

    	try {
    		si = (SummaryInformation)PropertySetFactory.create(event.getStream());
		} catch (NoPropertySetStreamException | MarkUnsupportedException | IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*
    	Recogemos los datos que nos interesan y los almacenamos en la clase
    	AnalizadorWord.*/
    	
    	datos.setTitulo(si.getTitle());
    	datos.setAutor(si.getAuthor());
    	datos.setComentarios(si.getComments());
    	datos.setNumeroCaracteres(si.getCharCount());
    	datos.setNumeroPalabras(si.getWordCount());
    	datos.setNumeroPaginas(si.getPageCount());
    	/* Usamos la clase WordExtractor para obtener el texto del documento */
    	POIOLE2TextExtractor we = null;
    	//WordExtractor we = null;

    	


    }

}
