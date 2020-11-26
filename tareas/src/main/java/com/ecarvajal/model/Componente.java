package com.ecarvajal.model;

public class Componente {
	
	public int id;
	public int id_marca;
	public int id_modelo;
	public int id_bike;
	public int id_cliente;
	public int id_mantenimiento;
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
	public int getId_bike() {
		return id_bike;
	}
	public void setId_bike(int id_bike) {
		this.id_bike = id_bike;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_mantenimiento() {
		return id_mantenimiento;
	}
	public void setId_mantenimiento(int id_mantenimiento) {
		this.id_mantenimiento = id_mantenimiento;
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
		return "Componente [id=" + id + ", id_marca=" + id_marca + ", id_modelo=" + id_modelo + ", id_bike=" + id_bike
				+ ", id_cliente=" + id_cliente + ", id_mantenimiento=" + id_mantenimiento + ", observaciones="
				+ observaciones + ", activo=" + activo + "]";
	}

}
