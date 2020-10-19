package com.ecarvajal.model;

public class Producto {
	
	public int id;
	public String codProducto;
	public String nombre;
	public String categoria;
	public String subCategoria;
	public String marca;
	public String modelo;
	public double precio;
	public double pvp;

	public int descuento;
	public boolean publicado;
	public int stock;
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", codProducto=" + codProducto + ", nombre=" + nombre + ", categoria=" + categoria
				+ ", subCategoria=" + subCategoria + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio
				+ ", pvp=" + pvp + ", publicado=" + publicado + ", stock=" + stock + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(String subCategoria) {
		this.subCategoria = subCategoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public boolean isPublicado() {
		return publicado;
	}
	public void setPublicado(boolean publicado) {
		this.publicado = publicado;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public double getPvp() {
		return pvp;
	}

	public void setPvp(double pvp) {
		this.pvp = pvp;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

}
