package com.ecarvajal.model;

public class ProductoParaVender extends Producto {
	
	public int cantidad;

	public Float total;
	
	public void setTotal(Float total) {
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


    
    public Float getTotal() {
        return (float) (this.getPvp() - ( ( (this.getDescuento()  / this.getPvp()) )* 100) * this.cantidad ) ;
    }

}
