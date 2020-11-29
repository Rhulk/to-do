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
		
		private Date fAlta;
		private Date fAlert;
		private String Descripcion;
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

		public Date getfAlta() {
			return fAlta;
		}

		public void setfAlta(Date fAlta) {
			this.fAlta = fAlta;
		}

		public Date getfAlert() {
			return fAlert;
		}

		public void setfAlert(Date fAlert) {
			this.fAlert = fAlert;
		}

		public String getDescripcion() {
			return Descripcion;
		}

		public void setDescripcion(String descripcion) {
			Descripcion = descripcion;
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
					+ ", fAlta=" + fAlta + ", fAlert=" + fAlert + ", Descripcion=" + Descripcion + ", status=" + status
					+ ", solucion=" + solucion + ", municipio=" + municipio + "]";
		}




		
		
}
