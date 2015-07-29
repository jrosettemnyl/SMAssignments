package com.smnyl.smassignments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.mpxj.*;

public class ProyectoXSemana {
	
	public String nombre;
	public String sm;
	public double horas;
	public Date inicio = new Date();
	public Date fin = new Date();
	
	
	
	public double getHoras() {
		return horas;
	}
	public void setHoras(double horas) {
		this.horas = horas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSm() {
		return sm;
	}
	public void setSm(String sm) {
		this.sm = sm;
	}
	
	public Date getInicio() {
		return inicio;
	}
	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}
	public Date getFin() {
		return fin;
	}
	public void setFin(Date fin) {
		this.fin = fin;
	}

}
