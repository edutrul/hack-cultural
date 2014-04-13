package com.hackatonlima.cultuhacklima.bean;

import java.io.Serializable;

public class Evento implements Serializable{
	private int id;
	private String categoria;
	private String nombre;
	private long fechaInicio;
	private long fechaFin;
	private String horaInicio;
	private String horaFin;
	private String horario;
	private String artistaExpositor;
	private String linkEvento;
	private String lugarVenta;
	private String observaciones;
	private int imagenA;
	private int imagenB;
	private int imagenC;
	private Lugar lugar;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(long fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public long getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(long fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getArtistaExpositor() {
		return artistaExpositor;
	}
	public void setArtistaExpositor(String artistaExpositor) {
		this.artistaExpositor = artistaExpositor;
	}
	public String getLinkEvento() {
		return linkEvento;
	}
	public void setLinkEvento(String linkEvento) {
		this.linkEvento = linkEvento;
	}
	public String getLugarVenta() {
		return lugarVenta;
	}
	public void setLugarVenta(String lugarVenta) {
		this.lugarVenta = lugarVenta;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Lugar getLugar() {
		return lugar;
	}
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
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
