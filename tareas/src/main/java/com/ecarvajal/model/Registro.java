package com.ecarvajal.model;

import java.util.Date;

public class Registro {
	
	private int id;
	private int id_todo;
	private Date f_inicioEspera;
	private Date f_finEspera;
	private Date f_inicioActivo;
	private Date f_finActivo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_todo() {
		return id_todo;
	}
	public void setId_todo(int id_todo) {
		this.id_todo = id_todo;
	}
	public Date getF_inicioEspera() {
		return f_inicioEspera;
	}
	public void setF_inicioEspera(Date f_inicioEspera) {
		this.f_inicioEspera = f_inicioEspera;
	}
	public Date getF_finEspera() {
		return f_finEspera;
	}
	public void setF_finEspera(Date f_finEspera) {
		this.f_finEspera = f_finEspera;
	}
	public Date getF_inicioActivo() {
		return f_inicioActivo;
	}
	public void setF_inicioActivo(Date f_inicioActivo) {
		this.f_inicioActivo = f_inicioActivo;
	}
	public Date getF_finActivo() {
		return f_finActivo;
	}
	public void setF_finActivo(Date f_finActivo) {
		this.f_finActivo = f_finActivo;
	}
	@Override
	public String toString() {
		return "Registro [id=" + id + ", id_todo=" + id_todo + ", f_inicioEspera=" + f_inicioEspera + ", f_finEspera="
				+ f_finEspera + ", f_inicioActivo=" + f_inicioActivo + ", f_finActivo=" + f_finActivo + "]";
	}

}
