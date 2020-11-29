package com.ecarvajal.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {
	
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	public int id;
	public String codproducto;
	public String nombre;
	public String categoria;
	public String subcategoria;
	public String marca;
	public String modelo;
	public double precio;
	public double pvp;

	public int descuento;
	public boolean publicado;
	public int stock;
	
	@Override
	public String toString() {
		return "Producto [id=" + id + ", codProducto=" + codproducto + ", nombre=" + nombre + ", categoria=" + categoria
				+ ", subcategoria=" + subcategoria + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio
				+ ", pvp=" + pvp + ", publicado=" + publicado + ", stock=" + stock + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodProducto() {
		return codproducto;
	}
	public void setCodProducto(String codProducto) {
		this.codproducto = codProducto;
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
		return subcategoria;
	}
	public void setSubCategoria(String subcategoria) {
		this.subcategoria = subcategoria;
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
