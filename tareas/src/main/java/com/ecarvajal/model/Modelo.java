package com.ecarvajal.model;

public class Modelo {
	
	public int id;
	public String marca;
	public boolean activo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Modelo [id=" + id + ", marca=" + marca + ", activo=" + activo + "]";
	}
	
	

}
