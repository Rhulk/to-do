package com.ecarvajal.model;

import java.util.Date;

public class Mantenimiento {
	
	public int id;
	public int id_client;
	public String descripcion;
	public Date falta;
	public Date falerta;
	
	public int km_recorridos;
	public int km_tope;
	public int id_bike;
	public int id_componente;
	
	
	
	public int getKm_recorridos() {
		return km_recorridos;
	}
	public void setKm_recorridos(int km_recorridos) {
		this.km_recorridos = km_recorridos;
	}
	public int getKm_tope() {
		return km_tope;
	}
	public void setKm_tope(int km_tope) {
		this.km_tope = km_tope;
	}
	public int getId_bike() {
		return id_bike;
	}
	public void setId_bike(int id_bike) {
		this.id_bike = id_bike;
	}
	public int getId_componente() {
		return id_componente;
	}
	public void setId_componente(int id_componente) {
		this.id_componente = id_componente;
	}
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
