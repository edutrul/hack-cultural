package com.hackatonlima.cultuhacklima.gui;

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
import com.hackatonlima.cultuhacklima.bean.EventoLocalBean;



public class EventosLocalesActivity extends Activity implements OnItemClickListener{

	public static final int BASE_REQUEST_CODE = 1000;
	ListView listView;
	List<EventoLocalBean> locales;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos_locales);
		
		Intent intent = getIntent();
		String result = intent.getStringExtra("go");
		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		
		if(result.equals("Local")){
			cargarLocales();
		}else{
			cargarEventos();
		}
		
	}
	
	public void cargarLocales(){
		locales = new ArrayList<EventoLocalBean>();
		
		for(int i=0; i<5;i++){
			locales.add(new EventoLocalBean(R.drawable.icoe,"Local "+i));
		}
		listView = (ListView) findViewById(R.id.lts_eventos_locales);
		EventosLocalesBaseAdapter adapter = new EventosLocalesBaseAdapter(this,locales);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
		
	}
	
	public void cargarEventos(){
		List<EventoLocalBean> eventos = new ArrayList<EventoLocalBean>();
		
		for(int i=0; i<5;i++){
			eventos.add(new EventoLocalBean(R.drawable.icoe,"Evento "+i));
		}
		
		listView = (ListView) findViewById(R.id.lts_eventos_locales);
		EventosLocalesBaseAdapter adapter = new EventosLocalesBaseAdapter(this,
				eventos);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent(getApplicationContext(), DetalleEventoActivity.class);
		intent.putExtra("evento", ""+position);
		startActivityForResult(intent, BASE_REQUEST_CODE);
	}
	


}
