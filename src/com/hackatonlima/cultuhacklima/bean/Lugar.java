package com.hackatonlima.cultuhacklima.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lugar implements Serializable{
	private int id;
	private String categoria;
	private String subcategoria;
	private String nombre;
	private String ubicacion;
	private String direccion;
	private double latitud;
	private double longitud;
	private String horarios;
	private String tarifa;
	private String telefonoContacto;
	private String email;
	private String paginaWeb;
	private int imagenA;
	private int imagenB;
	private int imagenC;
	private List<Evento> eventos;
	
	public Lugar() {
		super();
		this.eventos = new ArrayList<Evento>();
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getSubcategoria() {
		return subcategoria;
	}
	public void setSubcategoria(String subcategoria) {
		this.subcategoria = subcategoria;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public String getHorarios() {
		return horarios;
	}
	public void setHorarios(String horarios) {
		this.horarios = horarios;
	}
	public String getTarifa() {
		return tarifa;
	}
	public void setTarifa(String tarifa) {
		this.tarifa = tarifa;
	}
	public String getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	public void addEvento(Evento evento) {
		this.eventos.add(evento);
		evento.setLugar(this);
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	public List<Evento> getEventos() {
		return this.eventos;
	}

	public int getImagenA() {
		return imagenA;
	}

	public void setImagenA(int imagenA) {
		this.imagenA = imagenA;
	}

	public int getImagenB() {
		return imagenB;
	}

	public void setImagenB(int imagenB) {
		this.imagenB = imagenB;
	}

	public int getImagenC() {
		return imagenC;
	}

	public void setImagenC(int imagenC) {
		this.imagenC = imagenC;
	}
	
	
}
