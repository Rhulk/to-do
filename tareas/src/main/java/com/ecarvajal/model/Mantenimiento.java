package com.ecarvajal.model;

import java.util.Date;

public class Mantenimiento {
	
	public int id;
	public int id_client;
	public String descripcion;
	public Date falta;
	public Date falerta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFalta() {
		return falta;
	}
	public void setFalta(Date falta) {
		this.falta = falta;
	}
	public Date getFalerta() {
		return falerta;
	}
	public void setFalerta(Date falerta) {
		this.falerta = falerta;
	}
	@Override
	public String toString() {
		return "Mantenimiento [id=" + id + ", id_client=" + id_client + ", descripcion=" + descripcion + ", falta="
				+ falta + ", falerta=" + falerta + "]";
	}
	
	

}
