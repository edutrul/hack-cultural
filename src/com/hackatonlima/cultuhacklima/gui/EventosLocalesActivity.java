package com.hackatonlima.cultuhacklima.gui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.baseadapter.EventosLocalesBaseAdapter;
import com.hackatonlima.cultuhacklima.bean.Evento;
import com.hackatonlima.cultuhacklima.bean.EventoLocalBean;
import com.hackatonlima.cultuhacklima.bean.Lugar;
import com.hackatonlima.cultuhacklima.bean.QueryData;



public class EventosLocalesActivity extends Activity implements OnItemClickListener{
	
	public final static String FILTRO_POR = "com.hackatonlima.culturahacklima.FILTRO_POR";
	public final static String VALUE_PASSED = "com.hackatonlima.culturahacklima.VALUE_PASSED";
	
	public static final int BASE_REQUEST_CODE = 1000;
	ListView listView;
	List<EventoLocalBean> locales;
	List<Lugar> lugarListData;
	List<Evento> eventoListData;
	String[] arrElems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos_locales);
		
		Intent intent = getIntent();
		String result = intent.getStringExtra("go");
		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		
		if(result.equals("Local")) {
			// GET DATA FROM DASHBOARD.
			Bundle bundle = getIntent().getBundleExtra(DashboardActivity.LUGAR_LISTA_DATA_BUNDLE);
			lugarListData = (List<Lugar>) bundle.getSerializable(DashboardActivity.LUGAR_LISTA_DATA);
		    
			cargarLocales();
		}else{
			Bundle bundle = getIntent().getBundleExtra(DashboardActivity.EVENTOS_LISTA_DATA_BUNDLE);
			eventoListData = (List<Evento>) bundle.getSerializable(DashboardActivity.EVENTOS_LISTA_DATA);
			cargarEventos();
		}
		
	}
	
	public void cargarLocales(){
		locales = new ArrayList<EventoLocalBean>();
		arrElems = QueryData.getLugarCategories(lugarListData);
		
		for (int i = 0; i < arrElems.length; i++){
			locales.add(new EventoLocalBean(R.drawable.icoe, arrElems[i]));
		}
		listView = (ListView) findViewById(R.id.lts_eventos_locales);
		EventosLocalesBaseAdapter adapter = new EventosLocalesBaseAdapter(this,locales);
		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
		       // ListView Clicked item index
			   int itemPosition     = position;
			   
			   // ListView Clicked item value
			   String  itemValue    = arrElems[position];
				  
			    // Show Alert 
			    Toast.makeText(getApplicationContext(),
			      "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
			      .show();
			    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
//			    Bundle bundle = new Bundle();
//			    bundle.putSerializable(DashboardActivity.LUGAR_LISTA_DATA, (Serializable) lugarListData);
//			    intent.putExtra(DashboardActivity.LUGAR_LISTA_DATA_BUNDLE, bundle);
			    //intent.putExtra(MUSEUM_CATEGORY, itemValue);
			    intent.putExtra(FILTRO_POR, "lugares");
			    intent.putExtra(VALUE_PASSED, itemValue);
			    startActivity(intent);
			  }
			
		});
		
	}
	
	public void cargarEventos(){
		//locales = new ArrayList<EventoLocalBean>();
		List<EventoLocalBean> eventos = new ArrayList<EventoLocalBean>();
		arrElems = QueryData.getEventoCategories(eventoListData);
		
		for(int i=0; i< arrElems.length;i++) {
			eventos.add(new EventoLocalBean(R.drawable.icoe, arrElems[i]));
		}
		
		listView = (ListView) findViewById(R.id.lts_eventos_locales);
		EventosLocalesBaseAdapter adapter = new EventosLocalesBaseAdapter(this,
				eventos);
		listView.setAdapter(adapter);
//		listView.setOnItemClickListener(this);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			  @Override
			  public void onItemClick(AdapterView<?> parent, View view,
			    int position, long id) {
				
		       // ListView Clicked item index
			   int itemPosition     = position;
			   
			   // ListView Clicked item value
			   String  itemValue    = (String) arrElems[position];
				  
			    // Show Alert 
			    Toast.makeText(getApplicationContext(),
			      "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
			      .show();
			    Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
//			    Bundle bundle = new Bundle();
//			    bundle.putSerializable(DashboardActivity.EVENTOS_LISTA_DATA, (Serializable) eventoListData);
//			    intent.putExtra(DashboardActivity.EVENTOS_LISTA_DATA_BUNDLE, bundle);
			    //intent.putExtra(MUSEUM_CATEGORY, itemValue);
			    intent.putExtra(FILTRO_POR, "eventos");
			    intent.putExtra(VALUE_PASSED, itemValue);
			    startActivity(intent);
			  }
			
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
