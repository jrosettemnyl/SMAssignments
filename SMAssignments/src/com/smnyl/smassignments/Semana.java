package com.smnyl.smassignments;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Semana {
	
	Calendar inicioSemana = Calendar.getInstance();
	Calendar finSemana = Calendar.getInstance();
	double horas = 0;
	List<ProyectoXSemana> proyectoXSemana = new ArrayList<ProyectoXSemana>();
	
	
	public double getHoras() {
		return horas;
	}
	public void setHoras(double horas) {
		this.horas = horas;
	}
	public Calendar getInicioSemana() {
		return inicioSemana;
	}
	public void setInicioSemana(Calendar inicioSemana) {
		this.inicioSemana = inicioSemana;
	}
	public Calendar getFinSemana() {
		return finSemana;
	}
	public void setFinSemana(Calendar finSemana) {
		this.finSemana = finSemana;
	}
	public List<ProyectoXSemana> getProyectoXSemana() {
		return proyectoXSemana;
	}
	public void setProyectoXSemana(List<ProyectoXSemana> proyectoXSemana) {
		this.proyectoXSemana = proyectoXSemana;
	}
	

}
