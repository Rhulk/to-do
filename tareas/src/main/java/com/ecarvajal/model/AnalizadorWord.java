package com.ecarvajal.model;

public class AnalizadorWord {

	   private	   int numeroCaracteres;
	   private	   int numeroPalabras;
	   private	   int numeroPaginas;
	   private	   String titulo;
	   private	   String autor;
	   private	   String comentarios;
	   private	   String texto;
	   private	   String nombreFichero;

	   public	   AnalizadorWord(String nombreFichero) {
		   this.nombreFichero = nombreFichero;

	   }

	public int getNumeroCaracteres() {
		return numeroCaracteres;
	}

	public void setNumeroCaracteres(int numeroCaracteres) {
		this.numeroCaracteres = numeroCaracteres;
	}

	public int getNumeroPalabras() {
		return numeroPalabras;
	}

	public void setNumeroPalabras(int numeroPalabras) {
		this.numeroPalabras = numeroPalabras;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public void setNumeroPaginas(int numeroPaginas) {
		this.numeroPaginas = numeroPaginas;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getNombreFichero() {
		return nombreFichero;
	}

	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

}
