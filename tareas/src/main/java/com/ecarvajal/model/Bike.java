package com.ecarvajal.model;

public class Bike {
	
	public int id;
	public int id_marca;
	public int id_modelo;
	public int id_partebike;
	public int id_cliente;
	public int id_mantinimiento;
	public String observaciones;
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
	public int getId_modelo() {
		return id_modelo;
	}
	public void setId_modelo(int id_modelo) {
		this.id_modelo = id_modelo;
	}
	public int getId_partebike() {
		return id_partebike;
	}
	public void setId_partebike(int id_partebike) {
		this.id_partebike = id_partebike;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_mantinimiento() {
		return id_mantinimiento;
	}
	public void setId_mantinimiento(int id_mantinimiento) {
		this.id_mantinimiento = id_mantinimiento;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Bike [id=" + id + ", id_marca=" + id_marca + ", id_modelo=" + id_modelo + ", id_partebike="
				+ id_partebike + ", id_cliente=" + id_cliente + ", id_mantinimiento=" + id_mantinimiento
				+ ", observaciones=" + observaciones + ", activo=" + activo + "]";
	}
	
}
