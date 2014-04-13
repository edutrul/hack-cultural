package com.hackatonlima.cultuhacklima.gui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.baseadapter.EventoBaseAdapter;
import com.hackatonlima.cultuhacklima.bean.EventoBean;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;



public class DashboardActivity extends SlidingFragmentActivity   implements OnItemClickListener {

	ListView listView;
	List<EventoBean> eventos;
	public static final int BASE_REQUEST_CODE = 1000;
	private boolean ocultarMenuIzquierdo = false;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		
		cargarEventos();
		crearMenuIzquierdo();
		
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
		startActivity(intent);
	}
	
	public void goLugares(View view){
		Intent intent = new Intent(getApplicationContext(),EventosLocalesActivity.class);
		intent.putExtra("go", "Local");
		startActivity(intent);
	}
	
	public void goEvento(View view){
		Intent intent = new Intent(getApplicationContext(),EventosLocalesActivity.class);
		intent.putExtra("go", "Evento");
		startActivity(intent);
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
	

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.dashboard, menu);
//		return true;
//	}

}
