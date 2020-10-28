package com.ecarvajal.model;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Tarea {

		private int id;
		private String tarea;
		private String prioridad;
		private String cliente;
		
		private Date fAlta;
		private Date fAlert;
		private String Descripcion;
		private String status;
		private String solucion;

		
		
		
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
		@Override
		public String toString() {
			return "Tarea [id=" + id + ", tarea=" + tarea + ", prioridad=" + prioridad + ", cliente=" + cliente
					+ ", fAlta=" + fAlta + ", fAlert=" + 
					fAlert+
					", Descripcion=" + Descripcion + ", status=" + status
					+ "]";
		}
		
		public Date getfAlert() {
			return fAlert;
		}

		public void setfAlert(Date fAlert) {
			this.fAlert = fAlert;
			
		}
		public String getSolucion() {
			return solucion;
		}
		public void setSolucion(String solucion) {
			this.solucion = solucion;
		}


		
		
}
