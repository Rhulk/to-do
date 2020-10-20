package com.ecarvajal.model;

public class ProductoParaVender extends Producto {
	
	
	
	public int cantidad;

	public double total;
	
	public void setTotal(double total) {
		this.total = total;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
    public void aumentarCantidad() {
        this.cantidad++;
    }
    public double getTotal() {
        return total;
    }

    // %/100*precio
    // Calcula asigna y debuelve el total
    public double calTotal() {
    	double porcentaje = (descuento * pvp) / 100;
    	this.total = (pvp - porcentaje) * cantidad;
        return total;
    }

}
