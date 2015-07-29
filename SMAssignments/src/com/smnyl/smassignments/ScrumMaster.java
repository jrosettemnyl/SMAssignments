package com.smnyl.smassignments;

import java.util.ArrayList;
import java.util.List;

public class ScrumMaster {
	
	private String nombre;
	private List<Semana> semanas = new ArrayList<Semana>();
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<Semana> getSemanas() {
		return semanas;
	}
	public void setSemanas(List<Semana> semanas) {
		this.semanas = semanas;
	}
	
	

}
