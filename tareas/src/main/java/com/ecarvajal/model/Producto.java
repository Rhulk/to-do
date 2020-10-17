package com.ecarvajal.model;

public class Producto {
	
	public int id;
	public String codProducto;
	public String nombre;
	public String categoria;
	public String subCategoria;
	public double precio;
	public boolean publicado;
	public int stock;
	
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", codProducto=" + codProducto + ", nombre=" + nombre + ", Categoria=" + categoria
				+ ", SubCategoria=" + subCategoria + ", precio=" + precio + ", publicado=" + publicado + ", stock="
				+ stock + "]";
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

}
