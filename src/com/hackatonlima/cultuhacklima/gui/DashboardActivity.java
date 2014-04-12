package com.hackatonlima.cultuhacklima.gui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.baseadapter.EventoBaseAdapter;
import com.hackatonlima.cultuhacklima.bean.EventoBean;

public class DashboardActivity extends Activity implements OnItemClickListener {

	ListView listView;
	List<EventoBean> eventos;
	public static final int BASE_REQUEST_CODE = 1000;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		cargarEventos();
		
	}
	
	public void cargarEventos(){
		
		eventos =  new ArrayList<EventoBean>();
		
		for(int i=0; i<7;i++){
			eventos.add(new EventoBean(R.drawable.logo,"Evento "+i,"Fecha "+i));
		}
		
		listView = (ListView) findViewById(R.id.lts_eventos);
		EventoBaseAdapter adapter = new EventoBaseAdapter(this,eventos);
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
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dashboard, menu);
		return true;
	}

}
