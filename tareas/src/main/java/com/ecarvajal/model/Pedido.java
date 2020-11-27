package com.ecarvajal.model;

import java.util.Date;

public class Pedido {

	public int id;
	public int id_componente;
	public int id_producto;
	public Date alta;
	public Date pedido;
	public Date entrega;
	public String observaciones;
	int precio_compra;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_componente() {
		return id_componente;
	}
	public void setId_componente(int id_componente) {
		this.id_componente = id_componente;
	}
	public int getId_producto() {
		return id_producto;
	}
	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
	public Date getAlta() {
		return alta;
	}
	public void setAlta(Date alta) {
		this.alta = alta;
	}
	public Date getPedido() {
		return pedido;
	}
	public void setPedido(Date pedido) {
		this.pedido = pedido;
	}
	public Date getEntrega() {
		return entrega;
	}
	public void setEntrega(Date entrega) {
		this.entrega = entrega;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(int precio_compra) {
		this.precio_compra = precio_compra;
	}
	@Override
	public String toString() {
		return "Pedidos [id=" + id + ", id_componente=" + id_componente + ", id_producto=" + id_producto + ", alta="
				+ alta + ", pedido=" + pedido + ", entrega=" + entrega + ", observaciones=" + observaciones
				+ ", precio_compra=" + precio_compra + "]";
	}

	
}
