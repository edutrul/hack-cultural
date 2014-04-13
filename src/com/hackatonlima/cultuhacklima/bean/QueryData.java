package com.hackatonlima.cultuhacklima.bean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class QueryData {
	
	
	/**
	 * Get All lugar Names in Array of String.
	 * 
	 * @param List<Lugar> LugarListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of lugar names.
	 */
	public static String[] getLugaresNames(List<Lugar> lugarListData) {
		//String[] LugarNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Lugar> it = lugarListData.iterator();
		while(it.hasNext())
		{
		    Lugar lugar = it.next();
		    lista.add(lugar.getNombre());
		}
		return lista.toArray(new String[lista.size()]);
	}
	
	/**
	 * Get All Lugar Names filtered by Category.
	 * 
	 * @param List<Lugar> lugarListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of Lugar names filtered by Category.
	 */
	public static String[] getLugarNamesByCategory(List<Lugar> lugarListData, String category) {
		//String[] LugarNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Lugar> it = lugarListData.iterator();
		while(it.hasNext())
		{
		    Lugar lugar = it.next();
		    if (lugar.getCategoria().equalsIgnoreCase(category)) {
		    	lista.add(lugar.getNombre());
		    }
		}
		
		return lista.toArray(new String[lista.size()]);
	}
	
	/**
	 * Get All Lugar Names filtered by Category.
	 * 
	 * @param List<Lugar> lugarListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of Lugar names filtered by Category.
	 */
	public static List<Lugar> getLugaresByCategory(List<Lugar> lugarListData, String category) {
		//String[] LugarNames = null;
		List<Lugar> lista = new ArrayList<Lugar>();
		Iterator<Lugar> it = lugarListData.iterator();
		while(it.hasNext())
		{
		    Lugar lugar = it.next();
		    if (lugar.getCategoria().equalsIgnoreCase(category)) {
		    	lista.add(lugar);
		    }
		}
		
		return lista;
	}
	
	/**
	 * Get All Lugar Categories in Array of String.
	 * 
	 * @param List<Lugar> LugarListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of Lugar categories.
	 */
	public static String[] getLugarCategories(List<Lugar> lugarListData) {
		//String[] LugarNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Lugar> it = lugarListData.iterator();
		while(it.hasNext())
		{
		    Lugar lugar = it.next();
		    lista.add(lugar.getCategoria());
		}
		Set<String> set = new HashSet<String>(lista);
		return set.toArray(new String[0]);
	}
	
	/**
	 * Get Lugar data from Lugar name.
	 * 
	 * @param List<Lugar> LugarListData
	 *   Expects a List interface.
	 * 
	 * @param String LugarName
	 *   The name of the Lugar to search for.
	 * 
	 * @return
	 *   Array of strings of Lugar categories.
	 */
	public static Lugar getLugarFromLugarName(List<Lugar> lugarListData, String lugarName) {
		//String[] LugarNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Lugar> it = lugarListData.iterator();
		while(it.hasNext())
		{
			Lugar lugar = it.next();
		    lista.add(lugar.getNombre());
			if (lugar.getNombre().equalsIgnoreCase(lugarName)) {
			    return lugar;
			}
		}
		return null;
	}
	
	/**
	 * Get All evento Names in Array of String.
	 * 
	 * @param List<Evento> EventoListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of evento names.
	 */
	public static String[] getEventosNames(List<Evento> eventoListData) {
		//String[] EventoNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Evento> it = eventoListData.iterator();
		while(it.hasNext())
		{
		    Evento evento = it.next();
		    lista.add(evento.getNombre());
		}
		return lista.toArray(new String[lista.size()]);
	}
	
	/**
	 * Get All Evento Names filtered by Category.
	 * 
	 * @param List<Evento> eventoListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of Evento names filtered by Category.
	 */
	public static String[] getEventoNamesByCategory(List<Evento> eventoListData, String category) {
		//String[] EventoNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Evento> it = eventoListData.iterator();
		while(it.hasNext())
		{
		    Evento evento = it.next();
		    if (evento.getCategoria().equalsIgnoreCase(category)) {
		    	lista.add(evento.getNombre());
		    }
		}
		
		return lista.toArray(new String[lista.size()]);
	}
	
	/**
	 * Get All Evento Names filtered by Category.
	 * 
	 * @param List<Evento> eventoListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of Evento names filtered by Category.
	 */
	public static List<Evento> getEventosByCategory(List<Evento> eventoListData, String category) {
		//String[] EventoNames = null;
		List<Evento> lista = new ArrayList<Evento>();
		Iterator<Evento> it = eventoListData.iterator();
		while(it.hasNext())
		{
		    Evento evento = it.next();
		    if (evento.getCategoria().equalsIgnoreCase(category)) {
		    	lista.add(evento);
		    }
		}
		
		return lista;
	}
	
	/**
	 * Get All Evento Categories in Array of String.
	 * 
	 * @param List<Evento> EventoListData
	 *   Expects a List interface.
	 * 
	 * @return
	 *   Array of strings of Evento categories.
	 */
	public static String[] getEventoCategories(List<Evento> eventoListData) {
		//String[] EventoNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Evento> it = eventoListData.iterator();
		while(it.hasNext())
		{
		    Evento evento = it.next();
		    lista.add(evento.getCategoria());
		}
		Set<String> set = new HashSet<String>(lista);
		return set.toArray(new String[0]);
	}
	
	/**
	 * Get Evento data from Evento name.
	 * 
	 * @param List<Evento> EventoListData
	 *   Expects a List interface.
	 * 
	 * @param String EventoName
	 *   The name of the Evento to search for.
	 * 
	 * @return
	 *   Array of strings of Evento categories.
	 */
	public static Evento getEventoFromEventoName(List<Evento> eventoListData, String eventoName) {
		//String[] EventoNames = null;
		List<String> lista = new ArrayList<String>();
		Iterator<Evento> it = eventoListData.iterator();
		while(it.hasNext())
		{
			Evento evento = it.next();
		    lista.add(evento.getNombre());
			if (evento.getNombre().equalsIgnoreCase(eventoName)) {
			    return evento;
			}
		}
		return null;
	}
	
	/**
	 * Get All events from now on.
	 * 
	 * @param List<Evento> EventoListData
	 *   Expects a List interface.
	 *   
	 * @return List<Evento>
	 *   Array of strings of Eventos names.
	 */
	public static List<Evento> getEventosFromNowOn(List<Evento> eventoListData) {
		
		List<Evento> lista = new ArrayList<Evento>();
		Iterator<Evento> it = eventoListData.iterator();
		while(it.hasNext())
		{
			Evento evento = it.next();
			System.out.println(evento.getFechaInicio());
			System.out.println(System.currentTimeMillis() / 1000);
			if (evento.getFechaInicio() >= System.currentTimeMillis() / 1000) {
				lista.add(evento);
			}
		}
		return lista;
	}
}