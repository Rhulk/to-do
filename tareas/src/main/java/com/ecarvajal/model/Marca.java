package com.ecarvajal.model;

public class Marca {
	
	public int id;
	public int id_marca;
	public String modelo;
	public boolean activo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_marca() {
		return id_marca;
	}
	public void setId_marca(int id_marca) {
		this.id_marca = id_marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Marca [id=" + id + ", id_marca=" + id_marca + ", modelo=" + modelo + ", activo=" + activo + "]";
	}

	
}
