package com.smnyl.smassignments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.mpxj.*;
import net.sf.mpxj.mpp.MPPReader;
import net.sf.mpxj.reader.ProjectReader;


public class SMMetrics {
	
	ProjectReader reader = new MPPReader();
	ProjectFile project;
	public String projectName = "D:\\Proyectos\\Metricas\\SMAssignments\\Comite_2015.mpp";
	public List<ProyectoSMNYL> proyectos = new ArrayList<ProyectoSMNYL>();
	
	public List<ProyectoSMNYL> proyectosSM1 = new ArrayList<ProyectoSMNYL>();
	public List<ProyectoSMNYL> proyectosSM2 = new ArrayList<ProyectoSMNYL>();
	public List<ProyectoSMNYL> proyectosSM3 = new ArrayList<ProyectoSMNYL>();
	public List<ProyectoSMNYL> proyectosSM4 = new ArrayList<ProyectoSMNYL>();
	public List<Semana> semanas = new ArrayList<Semana>();
	public HashMap<String, List<ProyectoSMNYL>> coordinadores = new HashMap<String, List<ProyectoSMNYL>>();
	public HashMap<String,ScrumMaster> scrums = new HashMap<String,ScrumMaster>();
	public final String SM1 = "Marco Colunga";
	public final String SM2 = "Gerardo Franco";
	public final String SM3 = "Luis Reyo";
	public final String SM4 = "Hugo Castillo";
	
	public void mppReader()
	{
		System.out.println("before reading");
		try 
		{
			project = reader.read(projectName);
		} catch (MPXJException e) 
				{
					e.printStackTrace();
				}
		System.out.println("after reading");
		
		
		
		/*for (Resource resource : project.getAllResources())
		{
			System.out.println("Resource: " + resource.getName() + " (Unique ID=" +resource.getUniqueID() + ")");
		}*/
		
		Task archivo = project.getTaskByID(0);
		for (Task proyecto : archivo.getChildTasks()){
			ProyectoSMNYL proyectoSMNYL = new ProyectoSMNYL();
			proyectoSMNYL.setNombre(proyecto.getName());
			System.out.println("NOmbre del proyecto" + proyecto.getName());
			proyectoSMNYL.setSm(proyecto.getText10());
			proyectoSMNYL.setInicio(proyecto.getStart());
			proyectoSMNYL.setFin(proyecto.getFinish());
			
			if (proyecto.getName().equals("Soportes")){
				for(Task tareas : proyecto.getChildTasks()){
					ProyectoSMNYL proyectoSMNYLSoporte = new ProyectoSMNYL();
					proyectoSMNYLSoporte.setNombre(proyecto.getName());
					proyectoSMNYLSoporte.setSm(tareas.getText10());
					proyectoSMNYLSoporte.setInicio(tareas.getStart());
					proyectoSMNYLSoporte.setFin(tareas.getFinish());
					this.proyectos.add(proyectoSMNYL);
				}
				
			}
			else{
				for(Task tareas : proyecto.getChildTasks()){
					//System.out.println("Task: " + tareas.getName() + " ID=" + tareas.getID() + " SM="+ tareas.getText10() );
					if (tareas.getChildTasks() != null && tareas.getChildTasks().size() > 0)
					{
						for(Task subtareas : tareas.getChildTasks()){
							proyectoSMNYL.tareas.add(subtareas);
						}
					}
					else{	
						proyectoSMNYL.tareas.add(tareas);
					}
				}
			this.proyectos.add(proyectoSMNYL);
			}
		}
		
		System.out.println("********************************************");
		System.out.println("Total de proyectos: " + this.proyectos.size());
		 
		
	}
	
	public void generaProyectosXSm(){
		for (ProyectoSMNYL proy : this.proyectos){
			if (proy.getSm() != null){
				if (proy.getSm().equals(this.SM1))
					proyectosSM1.add(proy);
				else if (proy.getSm().equals(this.SM2))
					proyectosSM2.add(proy);
				else if (proy.getSm().equals(this.SM3))
					proyectosSM3.add(proy);
				else if (proy.getSm().equals(this.SM4))
					proyectosSM4.add(proy);
			}
		}

		
	}
	
	
	public void calculaFechasAnterior(){
		Calendar cal = Calendar.getInstance();
		for (int x=0; x<4; x++){
			Calendar first = (Calendar) cal.clone();
		    first.add(Calendar.DAY_OF_WEEK, 
		              (first.getFirstDayOfWeek()) - first.get(Calendar.DAY_OF_WEEK));
		    
		    Calendar last = (Calendar) first.clone();
		    last.add(Calendar.DAY_OF_YEAR,4);
		    
		    Semana sem = new Semana();
		    sem.setInicioSemana(first);
		    sem.setFinSemana(last);
		    
		    cal.add(Calendar.DAY_OF_YEAR, -7);
		    this.semanas.add(sem);
		}
		
	}
	
	public void calculaFechasPosterior(){
		Calendar cal = Calendar.getInstance();
		for (int x=0; x<4; x++){
			Calendar first = (Calendar) cal.clone();
		    first.add(Calendar.DAY_OF_WEEK, 
		              (first.getFirstDayOfWeek()) - first.get(Calendar.DAY_OF_WEEK));
		    
		    Calendar last = (Calendar) first.clone();
		    last.add(Calendar.DAY_OF_YEAR,4);
		    
		    Semana sem = new Semana();
		    sem.setInicioSemana(first);
		    sem.setFinSemana(last);
		    
		    cal.add(Calendar.DAY_OF_YEAR, 7);
		    this.semanas.add(sem);
		}
		
	}
	
    /** 
     * Returns the maximum of two dates. A null date is treated as being less
     * than any non-null date. 
     */
    public  Date max(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.after(d2)) ? d1 : d2;
    }
    
    /** 
     * Returns the minimum of two dates. A null date is treated as being greater
     * than any non-null date. 
     */
    public Date min(Date d1, Date d2) {
        if (d1 == null && d2 == null) return null;
        if (d1 == null) return d2;
        if (d2 == null) return d1;
        return (d1.before(d2)) ? d1 : d2;
    }
    
    public long getDays(Date inicioTarea, Date finTarea, Date inicioSemana, Date finSemana){
    	long days =0; 
    	long millisInDay = 60 * 60 * 24 * 1000;
    	Date inicio = this.max(inicioTarea, inicioSemana);
    	 Date fin= this.min(finSemana, finTarea);
    	 //System.out.println("De:" +inicioTarea  + "   A  " + inicioSemana + "        -------------Inicio: " + inicio);
    	 //System.out.println("Fin" + fin);
    	 days = ((fin.getTime() / millisInDay) * millisInDay) - ((inicio.getTime() / millisInDay) * millisInDay);
    	 days = days / 1000 / 60 / 60 / 24;
    	 days = days + 1 ;
    	
    	return days;
    }

	
	public void calculaHorasXSemanaSM1(){
		double horasXRecurso = 0;
		ScrumMaster sm = new ScrumMaster();
		sm.setNombre(this.SM1);
		List<Semana> semanas = new ArrayList<Semana>();
		for (Semana s: this.semanas){
			List<ProyectoXSemana> proyectoXSemana = new ArrayList<ProyectoXSemana>();
			for (Iterator<ProyectoSMNYL> iterator = this.proyectosSM1.iterator(); iterator.hasNext();) {
				double horasXProyecto = 0;
				ProyectoSMNYL proy = iterator.next();
				ProyectoXSemana proySem = new ProyectoXSemana();
				for (Task tarea : proy.getTareas()) {
					long days = 1;
					days = getDays(tarea.getStart(), tarea.getFinish(), s.inicioSemana.getTime(), s.finSemana.getTime());
					if (days >= 0){
						for(ResourceAssignment ra: tarea.getResourceAssignments()){
							if (ra.getUnits() != null && ra.getUnits().doubleValue() != 0){
								double horsXdia = (ra.getUnits().doubleValue()*8)/100;
								horasXRecurso = horasXRecurso + (horsXdia * days);
								horasXProyecto = horasXProyecto + (horsXdia * days);
								//System.out.println("days:" + days + "  horasXdia:" + horsXdia );
								
							}
						}
					}
				}
				
				if (horasXProyecto > 0)
				{
					//System.out.println(proy.getNombre() + "   :  " + horasXProyecto);
					proySem.setNombre(proy.getNombre());
					proySem.setSm(this.SM1);
					proySem.setHoras(horasXProyecto);
					proyectoXSemana.add(proySem);
					horasXProyecto = 0;
				}
			}
			//System.out.println("Proyectos por semana:" + proyectoXSemana.size());
			Semana sem = new Semana();
			sem.setInicioSemana(s.getInicioSemana());
			sem.setFinSemana(s.getFinSemana());
			sem.setHoras(horasXRecurso);
			sem.setProyectoXSemana(proyectoXSemana);
			semanas.add(sem);
			horasXRecurso = 0;
		}
		
		sm.setSemanas(semanas);
		this.scrums.put(this.SM1,sm);
	}
	
	public void calculaHorasXSemanaSM2(){
		double horasXRecurso = 0;
		ScrumMaster sm = new ScrumMaster();
		sm.setNombre(this.SM2);
		List<Semana> semanas = new ArrayList<Semana>();
		for (Semana s: this.semanas){
			List<ProyectoXSemana> proyectoXSemana = new ArrayList<ProyectoXSemana>();
			for (Iterator<ProyectoSMNYL> iterator = this.proyectosSM2.iterator(); iterator.hasNext();) {
				double horasXProyecto = 0;
				ProyectoSMNYL proy = iterator.next();
				ProyectoXSemana proySem = new ProyectoXSemana();
				for (Task tarea : proy.getTareas()) {
					long days = 1;
					days = getDays(tarea.getStart(), tarea.getFinish(), s.inicioSemana.getTime(), s.finSemana.getTime());
					if (days >= 0){
						for(ResourceAssignment ra: tarea.getResourceAssignments()){
							if (ra.getUnits() != null && ra.getUnits().doubleValue() != 0){
								double horsXdia = (ra.getUnits().doubleValue()*8)/100;
								horasXRecurso = horasXRecurso + (horsXdia * days);
								horasXProyecto = horasXProyecto + (horsXdia * days);
								//System.out.println("days:" + days + "  horasXdia:" + horsXdia );
								
							}
						}
					}
				}
				
				if (horasXProyecto > 0)
				{
					//System.out.println(proy.getNombre() + "   :  " + horasXProyecto);
					proySem.setNombre(proy.getNombre());
					proySem.setSm(this.SM2);
					proySem.setHoras(horasXProyecto);
					proyectoXSemana.add(proySem);
					horasXProyecto = 0;
				}
			}
			//System.out.println("Proyectos por semana:" + proyectoXSemana.size());
			Semana sem = new Semana();
			sem.setInicioSemana(s.getInicioSemana());
			sem.setFinSemana(s.getFinSemana());
			sem.setHoras(horasXRecurso);
			sem.setProyectoXSemana(proyectoXSemana);
			semanas.add(sem);
			horasXRecurso = 0;
		}
		
		sm.setSemanas(semanas);
		this.scrums.put(this.SM2,sm);
	}
	
	public void calculaHorasXSemanaSM3(){
		double horasXRecurso = 0;
		ScrumMaster sm = new ScrumMaster();
		sm.setNombre(this.SM3);
		List<Semana> semanas = new ArrayList<Semana>();
		for (Semana s: this.semanas){
			List<ProyectoXSemana> proyectoXSemana = new ArrayList<ProyectoXSemana>();
			for (Iterator<ProyectoSMNYL> iterator = this.proyectosSM3.iterator(); iterator.hasNext();) {
				double horasXProyecto = 0;
				ProyectoSMNYL proy = iterator.next();
				ProyectoXSemana proySem = new ProyectoXSemana();
				for (Task tarea : proy.getTareas()) {
					long days = 1;
					days = getDays(tarea.getStart(), tarea.getFinish(), s.inicioSemana.getTime(), s.finSemana.getTime());
					if (days >= 0){
						for(ResourceAssignment ra: tarea.getResourceAssignments()){
							if (ra.getUnits() != null && ra.getUnits().doubleValue() != 0){
								double horsXdia = (ra.getUnits().doubleValue()*8)/100;
								horasXRecurso = horasXRecurso + (horsXdia * days);
								horasXProyecto = horasXProyecto + (horsXdia * days);
								//System.out.println("days:" + days + "  horasXdia:" + horsXdia );
								
							}
						}
					}
				}
				
				if (horasXProyecto > 0)
				{
					//System.out.println(proy.getNombre() + "   :  " + horasXProyecto);
					proySem.setNombre(proy.getNombre());
					proySem.setSm(this.SM3);
					proySem.setHoras(horasXProyecto);
					proyectoXSemana.add(proySem);
					horasXProyecto = 0;
				}
			}
			//System.out.println("Proyectos por semana:" + proyectoXSemana.size());
			Semana sem = new Semana();
			sem.setInicioSemana(s.getInicioSemana());
			sem.setFinSemana(s.getFinSemana());
			sem.setHoras(horasXRecurso);
			sem.setProyectoXSemana(proyectoXSemana);
			semanas.add(sem);
			horasXRecurso = 0;
		}
		
		sm.setSemanas(semanas);
		this.scrums.put(this.SM3,sm);
	}
	
	public void calculaHorasXSemanaSM4(){
		double horasXRecurso = 0;
		ScrumMaster sm = new ScrumMaster();
		sm.setNombre(this.SM4);
		List<Semana> semanas = new ArrayList<Semana>();
		for (Semana s: this.semanas){
			List<ProyectoXSemana> proyectoXSemana = new ArrayList<ProyectoXSemana>();
			for (Iterator<ProyectoSMNYL> iterator = this.proyectosSM4.iterator(); iterator.hasNext();) {
				double horasXProyecto = 0;
				ProyectoSMNYL proy = iterator.next();
				ProyectoXSemana proySem = new ProyectoXSemana();
				for (Task tarea : proy.getTareas()) {
					long days = 1;
					days = getDays(tarea.getStart(), tarea.getFinish(), s.inicioSemana.getTime(), s.finSemana.getTime());
					if (days >= 0){
						for(ResourceAssignment ra: tarea.getResourceAssignments()){
							if (ra.getUnits() != null && ra.getUnits().doubleValue() != 0){
								double horsXdia = (ra.getUnits().doubleValue()*8)/100;
								horasXRecurso = horasXRecurso + (horsXdia * days);
								horasXProyecto = horasXProyecto + (horsXdia * days);
								//System.out.println("days:" + days + "  horasXdia:" + horsXdia );
								
							}
						}
					}
				}
				
				if (horasXProyecto > 0)
				{
					//System.out.println(proy.getNombre() + "   :  " + horasXProyecto);
					proySem.setNombre(proy.getNombre());
					proySem.setSm(this.SM4);
					proySem.setHoras(horasXProyecto);
					proyectoXSemana.add(proySem);
					horasXProyecto = 0;
				}
			}
			//System.out.println("Proyectos por semana:" + proyectoXSemana.size());
			Semana sem = new Semana();
			sem.setInicioSemana(s.getInicioSemana());
			sem.setFinSemana(s.getFinSemana());
			sem.setHoras(horasXRecurso);
			sem.setProyectoXSemana(proyectoXSemana);
			semanas.add(sem);
			horasXRecurso = 0;
		}
		
		sm.setSemanas(semanas);
		this.scrums.put(this.SM4,sm);
	}
	
	
	public static void main(String[] args) {
		
		SMMetrics fileReader = new SMMetrics();
		
		
		System.out.println("Ingresa 'A' para ejecutar las 4 semana anteriores (incluida la actual  o");
		System.out.println("Ingresa 'P' para ejecutar las 4 semana posteriores (incluida la actual):      ");
		 
	      //  open up standard input
	      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	 
	      String opcion = null;
	 
	      //  read the username from the command-line; need to use try/catch with the
	      //  readLine() method
	      try {
	         opcion = br.readLine();
	      } catch (IOException ioe) {
	         System.out.println("IO error trying to read your option!");
	         System.exit(1);
	      }
	 
	      
	 
		
		
		
		
		fileReader.mppReader();
		fileReader.generaProyectosXSm();
	
		
		
		
		if (opcion.equals("P"))
			fileReader.calculaFechasPosterior();
		else
			fileReader.calculaFechasAnterior();
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd");
		
		for (Semana s: fileReader.semanas){
			
			
		    System.out.println(df.format(s.getInicioSemana().getTime()) + " -> " + 
		                       df.format(s.getFinSemana().getTime()));
			
		}
		
		fileReader.calculaHorasXSemanaSM1();
		fileReader.calculaHorasXSemanaSM2();
		fileReader.calculaHorasXSemanaSM3();
		fileReader.calculaHorasXSemanaSM4();
		
		
		ScrumMaster SM_1 = fileReader.scrums.get(fileReader.SM1);
		if(SM_1 != null && SM_1.getSemanas() != null && SM_1.getSemanas().size() > 0){
			System.out.println("For :" + fileReader.SM1);
			for (Semana s : SM_1.getSemanas()){
				System.out.println(df.format(s.getInicioSemana().getTime()) + "  -> "  + df.format(s.getFinSemana().getTime()));
				System.out.println(s.getHoras());
				System.out.println(" PROYECTOS");
				for (ProyectoXSemana pXs : s.getProyectoXSemana())
				  System.out.println("    " +pXs.getNombre() + "    Horas x Semana: " + pXs.getHoras());
			}
		}
		
		System.out.println(" *********************** 2 ------------------------------------");
		
		ScrumMaster SM_2 = fileReader.scrums.get(fileReader.SM2);
		if(SM_2 != null && SM_2.getSemanas() != null && SM_2.getSemanas().size() > 0){
			System.out.println("For :" + fileReader.SM2);
			for (Semana s : SM_2.getSemanas()){
				System.out.println(df.format(s.getInicioSemana().getTime()) + "  -> "  + df.format(s.getFinSemana().getTime()));
				System.out.println(s.getHoras());
				System.out.println(" PROYECTOS");
				for (ProyectoXSemana pXs : s.getProyectoXSemana())
				  System.out.println("    " +pXs.getNombre() + "    Horas x Semana: " + pXs.getHoras());
			}
		}
		
		System.out.println(" *********************** 3 ------------------------------------");
		
		ScrumMaster SM_3 = fileReader.scrums.get(fileReader.SM3);
		if(SM_3 != null && SM_3.getSemanas() != null && SM_3.getSemanas().size() > 0){
			System.out.println("For :" + fileReader.SM3);
			for (Semana s : SM_3.getSemanas()){
				System.out.println(df.format(s.getInicioSemana().getTime()) + "  -> "  + df.format(s.getFinSemana().getTime()));
				System.out.println(s.getHoras());
				System.out.println(" PROYECTOS");
				for (ProyectoXSemana pXs : s.getProyectoXSemana())
				  System.out.println("    " +pXs.getNombre() + "    Horas x Semana: " + pXs.getHoras());
			}
		}
		
		System.out.println(" *********************** 4 ------------------------------------");
		
		ScrumMaster SM_4 = fileReader.scrums.get(fileReader.SM4);
		if(SM_4 != null && SM_4.getSemanas() != null && SM_4.getSemanas().size() > 0){
			System.out.println("For :" + fileReader.SM4);
			for (Semana s : SM_4.getSemanas()){
				System.out.println(df.format(s.getInicioSemana().getTime()) + "  -> "  + df.format(s.getFinSemana().getTime()));
				System.out.println(s.getHoras());
				System.out.println(" PROYECTOS");
				for (ProyectoXSemana pXs : s.getProyectoXSemana())
				  System.out.println("    " +pXs.getNombre() + "    Horas x Semana: " + pXs.getHoras());
			}
		}
		
	
	}
	
	

}
