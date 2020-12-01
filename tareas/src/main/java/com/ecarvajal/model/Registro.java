package com.ecarvajal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="registro")
public class Registro {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)		
	private int id;
	private int id_todo;
	private Date f_inicio;
	private Date f_fin;
	private String tipo_registro;
	private boolean activo;
	private String descripcion;

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
	public Date getF_inicio() {
		return f_inicio;
	}
	public void setF_inicio(Date f_inicio) {
		this.f_inicio = f_inicio;
	}
	public Date getF_fin() {
		return f_fin;
	}
	public void setF_fin(Date f_fin) {
		this.f_fin = f_fin;
	}
	public String getTipo_registro() {
		return tipo_registro;
	}
	public void setTipo_registro(String tipo_registro) {
		this.tipo_registro = tipo_registro;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "Registro [id=" + id + ", id_todo=" + id_todo + ", f_inicio=" + f_inicio + ", f_fin=" + f_fin
				+ ", tipo_registro=" + tipo_registro + ", activo=" + activo + ", descripcion=" + descripcion + "]";
	}
	
	


}
