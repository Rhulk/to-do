package com.ecarvajal.model;

public class ProductoParaVender extends Producto {
	
	public int cantidad;


	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
    public void aumentarCantidad() {
        this.cantidad++;
    }


    // pendiente aplicar descuento.
    public Float getTotal() {
        return (float) (this.getPvp() * this.cantidad);
    }

}
