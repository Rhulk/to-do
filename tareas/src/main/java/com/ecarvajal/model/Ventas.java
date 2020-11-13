package com.ecarvajal.model;

import java.util.Date;

public class Ventas {
	
	public int id;
	public int id_cliente;
	public int id_producto;
	public int precio_venta;
	public int precio_compra;
	public Date fecha_venta;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public int getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(int precio_venta) {
		this.precio_venta = precio_venta;
	}
	public int getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(int precio_compra) {
		this.precio_compra = precio_compra;
	}
	public Date getFecha_venta() {
		return fecha_venta;
	}
	public void setFecha_venta(Date fecha_venta) {
		this.fecha_venta = fecha_venta;
	}
	@Override
	public String toString() {
		return "Ventas [id=" + id + ", id_cliente=" + id_cliente + ", id_producto=" + id_producto + ", precio_venta="
				+ precio_venta + ", precio_compra=" + precio_compra + ", fecha_venta=" + fecha_venta + "]";
	}
	
	
	

}
