package com.hackatonlima.cultuhacklima.bean;

public class EventoBean {

	private int imagen;
	private String nombre;
	private String fecha;
	
	public EventoBean(int imagen, String nombre, String fecha) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
		this.fecha = fecha;
	}

	public EventoBean() {
		super();
		
		this.imagen = 0;
		this.nombre = "nombre";
		this.fecha = "fecha";
	}

	public int getImagen() {
		return imagen;
	}

	public void setImagen(int imagen) {
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	
}
