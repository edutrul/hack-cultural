package com.hackatonlima.cultuhacklima.bean;

public class EventoLocalBean {

	private int imagen;
	private String nombre;
	
	public EventoLocalBean(int imagen, String nombre) {
		super();
		this.imagen = imagen;
		this.nombre = nombre;
	}
	
	public EventoLocalBean() {
		super();
		this.imagen = 0;
		this.nombre = "";
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
	
	
}
