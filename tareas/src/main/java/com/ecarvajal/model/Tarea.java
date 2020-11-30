package com.ecarvajal.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tarea")
public class Tarea {

		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)	
		private int id;
		private String tarea;
		private String prioridad;
		private String cliente;
		
		private Date f_alta;
		private Date f_alert;
		private String descripcion;
		private String status;
		private String solucion;
		
		private String municipio;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getTarea() {
			return tarea;
		}

		public void setTarea(String tarea) {
			this.tarea = tarea;
		}

		public String getPrioridad() {
			return prioridad;
		}

		public void setPrioridad(String prioridad) {
			this.prioridad = prioridad;
		}

		public String getCliente() {
			return cliente;
		}

		public void setCliente(String cliente) {
			this.cliente = cliente;
		}

		public Date getf_alta() {
			return f_alta;
		}

		public void setf_alta(Date f_alta) {
			this.f_alta = f_alta;
		}

		public Date getf_alert() {
			return f_alert;
		}

		public void setf_alert(Date f_alert) {
			this.f_alert = f_alert;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public void setDescripcion(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getSolucion() {
			return solucion;
		}

		public void setSolucion(String solucion) {
			this.solucion = solucion;
		}

		public String getMunicipio() {
			return municipio;
		}

		public void setMunicipio(String municipio) {
			this.municipio = municipio;
		}

		@Override
		public String toString() {
			return "Tarea [id=" + id + ", tarea=" + tarea + ", prioridad=" + prioridad + ", cliente=" + cliente
					+ ", f_alta=" + f_alta + ", f_alert=" + f_alert + ", Descripcion=" + descripcion + ", status=" + status
					+ ", solucion=" + solucion + ", municipio=" + municipio + "]";
		}




		
		
}
