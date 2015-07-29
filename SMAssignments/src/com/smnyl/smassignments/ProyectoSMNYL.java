package com.smnyl.smassignments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.mpxj.*;

public class ProyectoSMNYL {
	
	public String nombre;
	public String sm;
	public List<Task> tareas = new ArrayList<Task>();
	public Date inicio = new Date();
	public Date fin = new Date();
	public List<Semana> conjuntoSemanas = new ArrayList<Semana>();
	
	
	
	public List<Semana> getConjuntoSemanas() {
		return conjuntoSemanas;
	}
	public void setConjuntoSemanas(List<Semana> conjuntoSemanas) {
		this.conjuntoSemanas = conjuntoSemanas;
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
	public List<Task> getTareas() {
		return tareas;
	}
	public void setTareas(List<Task> tareas) {
		this.tareas = tareas;
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
