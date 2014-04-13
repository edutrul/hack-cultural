package com.hackatonlima.cultuhacklima.gui;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import au.com.bytecode.opencsv.CSVReader;

import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.baseadapter.EventoBaseAdapter;
import com.hackatonlima.cultuhacklima.bean.Evento;
import com.hackatonlima.cultuhacklima.bean.EventoBean;
import com.hackatonlima.cultuhacklima.bean.Lugar;
import com.hackatonlima.cultuhacklima.bean.QueryData;
import com.hackatonlima.cultuhacklima.location.MyLocation;
import com.hackatonlima.cultuhacklima.location.MyLocation.LocationResult;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;



public class DashboardActivity extends SlidingFragmentActivity   implements OnItemClickListener {

	public List<Lugar> lugarListData;
	public List<Evento> eventoListData;
	public List<Evento> eventosProximo;
	List<Evento> eventosFilteredByCategory;
	List<Lugar> lugaresFiltradosPorCategoria;

	public final static String EVENTOS_LISTA_DATA = "com.androidexample.listview.EVENTOS_LISTA_DATA";
	public final static String EVENTOS_LISTA_DATA_BUNDLE = "com.androidexample.listview.EVENTOS_LISTA_DATA_BUNDLE";
	public final static String EVENTOS_NOMBRE= "com.androidexample.listview.EVENTOS_NOMBRE";
	
	public final static String LUGAR_LISTA_DATA = "com.androidexample.listview.LUGAR_LISTA_DATA";
	public final static String LUGAR_LISTA_DATA_BUNDLE = "com.androidexample.listview.LUGAR_LISTA_DATA_BUNDLE";
	public final static String LUGAR_NOMBRE= "com.androidexample.listview.LUGAR_NOMBRE";

//	public final static String LUGAR_NOMBRE= "com.androidexample.listview.EVENTOS_NOMBRE";
	
	ListView listView;
	List<EventoBean> eventos;
	public static final int BASE_REQUEST_CODE = 1000;
	private boolean ocultarMenuIzquierdo = false;
	static String lat="";
	static String lon="";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_dashboard);
		obtenerDireccion();
		loadDataEventos("eventos-hacks-mvp.csv");
		
		// LoadData.
		loadData("lugares-hacks-mvp.csv");
		
		crearMenuIzquierdo();
		
		String str_filtro_por = getIntent().getStringExtra(EventosLocalesActivity.FILTRO_POR);

		if (str_filtro_por.equalsIgnoreCase("lugares")) {
			Log.v("DASHBOARD", "LUGARES");
			String valuePassed = getIntent().getStringExtra(EventosLocalesActivity.VALUE_PASSED);
			cargarLugares(valuePassed);
		}
		else if (str_filtro_por.equalsIgnoreCase("eventos")) {
			String valuePassed = getIntent().getStringExtra(EventosLocalesActivity.VALUE_PASSED);
			Log.v("DASHBOARD", "EVENTOS");
			cargarEventos(valuePassed);
		}
		else if (str_filtro_por.equalsIgnoreCase("splash")) {
			cargarEventosFromNowOn();
		}
		
	}
	
	public void obtenerDireccion(){
		mostrarTiempoIndeterminado();
		MyLocation myLocation = new MyLocation();
        myLocation.getLocation(getApplicationContext(), locationResult);
	}
	
	
	LocationResult locationResult = new LocationResult(){
        @Override
        public void gotLocation(Location location){
        	ocultarTiempoIndeterminado();
        	lat = ""+location.getLatitude();
        	lon = ""+location.getLongitude();
        	Log.v("Look", "lat: " + location.getLatitude()+" lon: "+location.getLongitude());
        	 
        }
    };
    
    protected void mostrarTiempoIndeterminado() {
		setSupportProgressBarIndeterminateVisibility(true);
	}

	protected void ocultarTiempoIndeterminado() {
		setSupportProgressBarIndeterminateVisibility(false);
	}
	
	private void crearMenuIzquierdo() {

		setBehindContentView(R.layout.slide_menu);

		SlidingMenu sm = getSlidingMenu();
		setSlidingActionBarEnabled(false);
		sm.setBehindScrollScale(0.0f);
		sm.setBehindCanvasTransformer(null);

		// customize the SlidingMenu
		sm.setShadowWidthRes(R.dimen.shadow_width);
		sm.setShadowDrawable(R.drawable.shadow);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		if (ocultarMenuIzquierdo) {
			getSlidingMenu().setSlidingEnabled(false);
		}

		
	}
	public void setOcultarMenuIzquierdo(boolean ocultarMenuIzquierdo) {
		this.ocultarMenuIzquierdo = ocultarMenuIzquierdo;
	}
	
	public void goBusquedaAd(View view){
		Intent intent = new Intent(getApplicationContext(),BusquedaActivity.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(LUGAR_LISTA_DATA, (Serializable) lugarListData);
	    intent.putExtra(LUGAR_LISTA_DATA_BUNDLE, bundle);
		startActivity(intent);
	}
	
	public void goLugares(View view){
		Intent intent = new Intent(getApplicationContext(),EventosLocalesActivity.class);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable(LUGAR_LISTA_DATA, (Serializable) lugarListData);
	    intent.putExtra(LUGAR_LISTA_DATA_BUNDLE, bundle);
		
		intent.putExtra("go", "Local");
		startActivity(intent);
	}
	
	public void goEvento(View view){
		Intent intent = new Intent(getApplicationContext(),EventosLocalesActivity.class);
		
		Bundle bundle = new Bundle();
		bundle.putSerializable(EVENTOS_LISTA_DATA, (Serializable) eventoListData);
	    intent.putExtra(EVENTOS_LISTA_DATA_BUNDLE, bundle);
		
		intent.putExtra("go", "Evento");
		startActivity(intent);
	}
	
	public void cargarEventosFromNowOn(){
		
		eventos =  new ArrayList<EventoBean>();
		eventosProximo = QueryData.getEventosFromNowOn(eventoListData);
		
		for (Evento evento : eventosProximo) {
		    eventos.add(new EventoBean(
		    		R.drawable.logo, 
		    		evento.getNombre(),
		    		new SimpleDateFormat("yyyy-MM-dd").format(
		    				new Date(evento.getFechaInicio() * 1000))));
		}
		
		listView = (ListView) findViewById(R.id.lts_eventos);
		EventoBaseAdapter adapter = new EventoBaseAdapter(this,eventos);
		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				  
					// ListView Clicked item value
					String  itemValue    = eventosProximo.get(position).getNombre();
					Log.v("Dashboard", itemValue);
					//intent.putExtra("mylist", museumListData);
					Bundle bundle = new Bundle();
					bundle.putSerializable(EVENTOS_LISTA_DATA, (Serializable) eventoListData);
					Intent intent = new Intent(getApplicationContext(), DetalleEventoActivity.class);
					intent.putExtra("evento", "" + position);
					intent.putExtra("DESDE", "1");
				    intent.putExtra(EVENTOS_LISTA_DATA_BUNDLE, bundle);
				    intent.putExtra(EVENTOS_NOMBRE, itemValue);
					startActivityForResult(intent, BASE_REQUEST_CODE);
			  }
			
		});
	}
	
	/**
	 **/
	
	public void cargarEventos(String category){
		
		eventos =  new ArrayList<EventoBean>();
		eventosFilteredByCategory = QueryData.getEventosByCategory(eventoListData, category);
		for (Evento evento : eventosFilteredByCategory) {
		    eventos.add(new EventoBean(
		    		R.drawable.logo, 
		    		evento.getNombre(),
		    		new SimpleDateFormat("yyyy-MM-dd").format(
		    				new Date(evento.getFechaInicio() * 1000))));
		}
		
		listView = (ListView) findViewById(R.id.lts_eventos);
		EventoBaseAdapter adapter = new EventoBaseAdapter(this,eventos);
		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				  
				String itemValue = eventosFilteredByCategory.get(position).getNombre();
				  
			    // Show Alert 
			    Toast.makeText(getApplicationContext(),
			      "Position :" + position + "  ListItem : " +itemValue , Toast.LENGTH_LONG)
			      .show();
			    
				Bundle bundle = new Bundle();
				bundle.putSerializable(EVENTOS_LISTA_DATA, (Serializable) eventoListData);
				Intent intent = new Intent(getApplicationContext(), DetalleEventoActivity.class);
//				intent.putExtra("evento", "" + position);
			    intent.putExtra(EVENTOS_LISTA_DATA_BUNDLE, bundle);
			    intent.putExtra(EVENTOS_NOMBRE, itemValue);
				startActivityForResult(intent, BASE_REQUEST_CODE);
			  }
			
		});
	}
	
	/**
	 * Carga de Lugares filtrados por Category
	 */
	public void cargarLugares(String category){
		
		eventos =  new ArrayList<EventoBean>();
		lugaresFiltradosPorCategoria = QueryData.getLugaresByCategory(lugarListData, category);
		for (Lugar lugar : lugaresFiltradosPorCategoria) {
		    eventos.add(new EventoBean(
		    		R.drawable.logo, 
		    		lugar.getNombre(),
		    		lugar.getHorarios()));
		}
		
		listView = (ListView) findViewById(R.id.lts_eventos);
		EventoBaseAdapter adapter = new EventoBaseAdapter(this,eventos);
		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				  
				String itemValue = lugaresFiltradosPorCategoria.get(position).getNombre();
				  
			    // Show Alert 
			    Toast.makeText(getApplicationContext(),
			      "Position :" + position + "  ListItem : " +itemValue , Toast.LENGTH_LONG)
			      .show();
			    
				Bundle bundle = new Bundle();
				bundle.putSerializable(LUGAR_LISTA_DATA, (Serializable) lugarListData);
				Intent intent = new Intent(getApplicationContext(), DetalleLugarActivity.class);
//				intent.putExtra("evento", "" + position);
			    intent.putExtra(LUGAR_LISTA_DATA_BUNDLE, bundle);
			    intent.putExtra(LUGAR_NOMBRE, itemValue);
				startActivityForResult(intent, BASE_REQUEST_CODE);
			  }
			
		});
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
//		// ListView Clicked item value
//		String  itemValue    = eventosProximo.get(position).getNombre();
//		Log.v("Dashboard", itemValue);
//		//intent.putExtra("mylist", museumListData);
//		Bundle bundle = new Bundle();
//		bundle.putSerializable(EVENTOS_LISTA_DATA, (Serializable) eventoListData);
//		Intent intent = new Intent(getApplicationContext(), DetalleEventoActivity.class);
//		intent.putExtra("evento", "" + position);
//	    intent.putExtra(EVENTOS_LISTA_DATA_BUNDLE, bundle);
//	    intent.putExtra(EVENTOS_NOMBRE, itemValue);
//		startActivityForResult(intent, BASE_REQUEST_CODE);
	}
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.dashboard, menu);
//		return true;
//	}
	
	/**
	 * Load Data from a CSV filename to a list<Museum>.
	 * 
	 * @param String filename
	 *   The file name to read from.
	 */
	public void loadData(String fileName) {
		List<String[]> list = new ArrayList<String[]>();
		lugarListData = new ArrayList<Lugar>();
		String next[] = {};
		try {
			InputStreamReader csvStreamReader = new InputStreamReader(getAssets().open(fileName));

			CSVReader reader = new CSVReader(csvStreamReader);
			for (;;) {
				next = reader.readNext();
				if (next != null) {
					list.add(next);
				} else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			Lugar lugar = new Lugar();
			lugar.setId(Integer.parseInt(list.get(i)[0]));
			lugar.setCategoria(list.get(i)[1]);
			lugar.setSubcategoria(list.get(i)[2]);
			lugar.setNombre(list.get(i)[3]);
			lugar.setUbicacion(list.get(i)[4]);
			lugar.setDireccion(list.get(i)[5]);
			lugar.setLatitud(Double.parseDouble(list.get(i)[6]));
			lugar.setLongitud(Double.parseDouble(list.get(i)[7]));
			lugar.setHorarios(list.get(i)[8]);
			lugar.setTarifa(list.get(i)[9]);
			lugar.setTelefonoContacto(list.get(i)[10]);
			lugar.setEmail(list.get(i)[11]);
			lugar.setPaginaWeb(list.get(i)[12]);
			lugar.setImagenA(R.drawable.ic_launcher);
			lugar.setImagenB(R.drawable.ic_launcher);
			lugar.setImagenC(R.drawable.ic_launcher);
			//lugar.setImagenA(list.get(i)[13]);
			//lugar.setImagenB(list.get(i)[14]);
			//lugar.setImagenC(list.get(i)[15]);
			
			for (int j = 0; j < eventoListData.size(); j++) {
			    if (lugar.getId() == eventoListData.get(j).getId()) {
			        lugar.addEvento(eventoListData.get(j));
			    }
			}
			
			lugarListData.add(lugar);
			
		}
	}
	
	
	/**
	 * Load Data from a CSV filename to a list<Museum>.
	 * 
	 * @param String filename
	 *   The file name to read from.
	 */
	public void loadDataEventos(String fileName) {
		List<String[]> list = new ArrayList<String[]>();
		eventoListData = new ArrayList<Evento>();
		String next[] = {};
		try {
			InputStreamReader csvStreamReader = new InputStreamReader(getAssets().open(fileName));

			CSVReader reader = new CSVReader(csvStreamReader);
			for (;;) {
				next = reader.readNext();
				if (next != null) {
					list.add(next);
				} else {
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < list.size(); i++) {
			Evento evento = new Evento();
			evento.setId(Integer.parseInt(list.get(i)[0]));
			evento.setCategoria(list.get(i)[1]);
			evento.setNombre(list.get(i)[2]);
			evento.setFechaInicio(Long.parseLong(list.get(i)[3]));
			evento.setFechaFin(Long.parseLong(list.get(i)[4]));
			evento.setHoraInicio(list.get(i)[5]);
			evento.setHoraFin(list.get(i)[6]);
			evento.setHorario(list.get(i)[7]);
			evento.setArtistaExpositor(list.get(i)[8]);
			evento.setLinkEvento(list.get(i)[9]);
			evento.setLugarVenta(list.get(i)[10]);
			evento.setObservaciones(list.get(i)[11]);
			evento.setImagenA(R.drawable.ic_launcher);
			evento.setImagenB(R.drawable.ic_launcher);
			evento.setImagenC(R.drawable.ic_launcher);
			//evento.setImagenA(list.get(i)[12]);
			//evento.setImagenB(list.get(i)[13]);
			//evento.setImagenC(list.get(i)[14]);

			eventoListData.add(evento);
		}
	}

}
