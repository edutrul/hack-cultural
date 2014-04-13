package com.hackatonlima.cultuhacklima.gui;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.bean.Lugar;
import com.hackatonlima.cultuhacklima.bean.QueryData;
import com.hackatonlima.cultuhacklima.location.GMapV2Direction;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;





public class DetalleLugarActivity extends SlidingFragmentActivity{
	
//	private GoogleMap mMap;
	GoogleMap mMap;
    GMapV2Direction md;
    
    LatLng fromPosition = new LatLng(Double.parseDouble(DashboardActivity.lat), 
    		Double.parseDouble(DashboardActivity.lon));
    LatLng toPosition;// = new LatLng(13.683660045847258, 100.53900808095932);
	
	//GoogleMap mMap;
    //GMapV2Direction md;
    
	//LatLng fromPosition = new LatLng(13.687140112679154, 100.53525868803263);
	//LatLng toPosition = new LatLng(13.683660045847258, 100.53900808095932);


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		Button button = (Button) findViewById(R.id.btn_notification);
//		  button.setOnClickListener(new View.OnClickListener() {
//			    @Override
//			    public void onClick(View v) {
//			        Log.v("Dota", "Dota2x");
//			    }
//		  });
		
		setContentView(R.layout.activity_detalle_lugar);
		crearMenuIzquierdo();
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        md = new GMapV2Direction();
        mMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.mapCultuHackLima)).getMap();
		
		String lugarNombre = getIntent().getStringExtra(DashboardActivity.LUGAR_NOMBRE);
		Log.v("LUGAR", "I AM FINE");
		Bundle bundle = getIntent().getBundleExtra(DashboardActivity.LUGAR_LISTA_DATA_BUNDLE);
		List<Lugar> lugarListData = (List<Lugar>) bundle.getSerializable(DashboardActivity.LUGAR_LISTA_DATA);

		Lugar lugar = QueryData.getLugarFromLugarName(lugarListData, lugarNombre);
		String[] arrStringLugar = {
				"Id: " + lugar.getId(),
				"Categoria: " + lugar.getCategoria(),
				"Subcategoria: " + lugar.getSubcategoria(),
				"Nombre: " +  lugar.getNombre(),
				"Ubicación: " + lugar.getUbicacion(),
				"Dirección: " + lugar.getDireccion(),
				"Latitud: " + lugar.getLatitud(),
				"Longitud: " + lugar.getLongitud(),
				"Horarios: " + lugar.getHorarios(),
				"Tarifa: " + lugar.getTarifa(),
				"Telefono de Contacto: " + lugar.getTelefonoContacto(),
				"Email: " + lugar.getEmail(),
				"Página Web: " + lugar.getPaginaWeb(),
		};
		
		
		
		toPosition = new LatLng(lugar.getLatitud(), lugar.getLongitud());

		
		((TextView) findViewById(R.id.txt_categoria_detalle)).setText(lugar.getCategoria());
		((TextView) findViewById(R.id.txt_subcategoria_detalle)).setText(lugar.getSubcategoria());
		((TextView) findViewById(R.id.txt_nombre_detalle)).setText(lugar.getNombre());
		((TextView) findViewById(R.id.txt_ubicacion_detalle)).setText(lugar.getUbicacion());
		((TextView) findViewById(R.id.txt_direccion_detalle)).setText(lugar.getDireccion());
		((TextView) findViewById(R.id.txt_latitud_detalle)).setText(String.valueOf(lugar.getLatitud()));
		((TextView) findViewById(R.id.txt_longitud_detalle)).setText(String.valueOf(lugar.getLongitud()));
		((TextView) findViewById(R.id.txt_tarifa_detalle)).setText(lugar.getTarifa());
		((TextView) findViewById(R.id.txt_telefono_contacto_detalle)).setText(lugar.getTelefonoContacto());
		((TextView) findViewById(R.id.txt_email_detalle)).setText(lugar.getEmail());
		((TextView) findViewById(R.id.txt_pagina_web_detalle)).setText(lugar.getPaginaWeb());


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
		

		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		
		 menu.add(0, 1, 20, "Buscar")
	     .setIcon(R.drawable.icoe)
	     .setShowAsAction(
	       MenuItem.SHOW_AS_ACTION_IF_ROOM
	         | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		
		
		//getSupportMenuInflater().inflate(R.menu.contacts, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 1:
			
				Toast.makeText(getApplicationContext(),
						"Puede tener un máximo de 5 contactos",
						Toast.LENGTH_SHORT).show();
			

			break;
		// case R.id.github:
		// Util.goToGitHub(this);
		// return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
    protected void onResume() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(getApplicationContext());
        if (resultCode == ConnectionResult.SUCCESS) {
            // Construct a CameraPosition focusing on Mountain View and animate
            // the camera to that position.
            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(fromPosition) // Sets the center of the map to
                                            // Mountain View
                    .zoom(15) // Sets the zoom
                    .bearing(90) // Sets the orientation of the camera to east
                    .tilt(30) // Sets the tilt of the camera to 30 degrees
                    .build(); // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

            mMap.addMarker(new MarkerOptions().position(fromPosition).title(
                    "Cultura"));
            
            mMap.addMarker(new MarkerOptions().position(toPosition).title(
                    "Aqui"));
            
            Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
            int duration = md.getDurationValue(doc);
            String distance = md.getDistanceText(doc);
            String start_address = md.getStartAddress(doc);
            String copy_right = md.getCopyRights(doc);
            
            ArrayList<LatLng> directionPoint = md.getDirection(doc);
            PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
            
            for(int i = 0 ; i < directionPoint.size() ; i++) {          
                rectLine.add(directionPoint.get(i));
            }
            
            mMap.addPolyline(rectLine);

        } else if (resultCode == ConnectionResult.SERVICE_MISSING
                || resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED
                || resultCode == ConnectionResult.SERVICE_DISABLED) {
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode,
                    this, 1);
            dialog.show();
        }
        

        super.onResume();
	}

	
	
	
  public void createNotification(View view) {
  // Prepare intent which is triggered if the
  // notification is selected
  Intent intent = new Intent(this, DetalleLugarActivity.class);
  PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);

  // Build notification
  // Actions are just fake 
  Notification noti = new Notification.Builder(this)
      .setContentTitle("Hey!" + "Nombre del Evento")
      .setContentText("Lugar del evento").setSmallIcon(R.drawable.icoe)
      .setContentIntent(pIntent)
      .addAction(R.drawable.icoe, "Call", pIntent)
      .addAction(R.drawable.icoe, "More", pIntent)
      .addAction(R.drawable.icoe, "And more", pIntent).build();
  NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
  // hide the notification after its selected
  noti.flags |= Notification.FLAG_AUTO_CANCEL;

  notificationManager.notify(0, noti);
  
  
}
	
public void dota(View view){
	Log.v("GOT","Got it");
}
	

}
