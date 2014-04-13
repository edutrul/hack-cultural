package com.hackatonlima.cultuhacklima.gui;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.bean.Lugar;
import com.hackatonlima.cultuhacklima.bean.QueryData;



public class DetalleLugarActivity extends FragmentActivity{
	
//	private GoogleMap mMap;
	private LatLng eventoLatLng;
	
	//GoogleMap mMap;
    //GMapV2Direction md;
    
	//LatLng fromPosition = new LatLng(13.687140112679154, 100.53525868803263);
	//LatLng toPosition = new LatLng(13.683660045847258, 100.53900808095932);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_lugar);
		
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

//		if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//        
//        md = new GMapV2Direction();
//        try {
//        	mMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapCultuHackLima)).getMap();
//
//        	
//		} catch (Exception e) {
//			Log.v("JODER","-"+e.getMessage());
//		}
//		
//		LatLng coordinates = new LatLng(13.685400079263206, 100.537133384495975);		
//		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));
//		
//		mMap.addMarker(new MarkerOptions().position(fromPosition).title("Start"));
//		mMap.addMarker(new MarkerOptions().position(toPosition).title("End"));
//		
//		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
//		int duration = md.getDurationValue(doc);
//		String distance = md.getDistanceText(doc);
//		String start_address = md.getStartAddress(doc);
//		String copy_right = md.getCopyRights(doc);
//
//		ArrayList<LatLng> directionPoint = md.getDirection(doc);
//		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
//		
//		for(int i = 0 ; i < directionPoint.size() ; i++) {			
//			rectLine.add(directionPoint.get(i));
//		}
//		
//		mMap.addPolyline(rectLine);
		
		
//		md = new GMapV2Direction();
//		mMap = ((SupportMapFragment)getSupportFragmentManager()
//						.findFragmentById(R.id.mapCultuHackLima)).getMap();
//
//		LatLng coordinates = new LatLng(13.685400079263206, 100.537133384495975);		
//		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));
//		
//		mMap.addMarker(new MarkerOptions().position(fromPosition).title("Start"));
//		mMap.addMarker(new MarkerOptions().position(toPosition).title("End"));
//		
//		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
//		int duration = md.getDurationValue(doc);
//		String distance = md.getDistanceText(doc);
//		String start_address = md.getStartAddress(doc);
//		String copy_right = md.getCopyRights(doc);
//
//		ArrayList<LatLng> directionPoint = md.getDirection(doc);
//		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
//		
//		for(int i = 0 ; i < directionPoint.size() ; i++) {			
//			rectLine.add(directionPoint.get(i));
//		}
//		
//		mMap.addPolyline(rectLine);
		
		
//		Intent intent = getIntent();
//		String result = intent.getStringExtra("evento");
//		String lat = intent.getStringExtra("lat");
//		String lon= intent.getStringExtra("lon");
////		
////		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
////		
//		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapCultuHackLima)).getMap();
//		//eventoLatLng =  new LatLng(40.70686417491799, -74.01572942733765);
//		eventoLatLng = new LatLng(Double.parseDouble(lat),Double.parseDouble(lon));
//		//eventoLatLng = new LatLng(-12.10363400,-76.97180720); -12.0444098	-77.031377
//		
//		GMapV2Direction objMapa = new GMapV2Direction();
//		
//		Document doc= objMapa.getDocument(new LatLng(-12.10363400,-76.97180720),
//				new LatLng(-12.0444098,-77.031377),GMapV2Direction.MODE_DRIVING);
//		
//		
//		
//		
//		
//		
//
//		ArrayList<LatLng> directionPoint = objMapa.getDirection(doc);
//		            PolylineOptions rectLine = new PolylineOptions().width(3).color(
//		                    Color.RED);
//
//		            for (int i = 0; i < directionPoint.size(); i++) {
//		                rectLine.add(directionPoint.get(i));
//		            }
//		            Polyline polylin = mMap.addPolyline(rectLine);
//		
		
		
//		GoogleMap mMap = ((SupportMapFragment)getSupportFragmentManager()
//		        .findFragmentById(R.id.mapCultuHackLima)).getMap();
		
	
		
		
		
		
		
		
		
	}
	
//	@Override
//	protected void onResume() {
////		int resultCode = GooglePlayServicesUtil
////				.isGooglePlayServicesAvailable(getApplicationContext());
////		if (resultCode == ConnectionResult.SUCCESS) {
////			// Construct a CameraPosition focusing on Mountain View and animate
////			// the camera to that position.
////			CameraPosition cameraPosition = new CameraPosition.Builder()
////					.target(eventoLatLng) // Sets the center of the map to
////											// Mountain View
////					.zoom(17) // Sets the zoom
////					.bearing(90) // Sets the orientation of the camera to east
////					.tilt(30) // Sets the tilt of the camera to 30 degrees
////					.build(); // Creates a CameraPosition from the builder
////			mMap.animateCamera(CameraUpdateFactory
////					.newCameraPosition(cameraPosition));
////
////			mMap.addMarker(new MarkerOptions().position(eventoLatLng).title(
////					"Cultura"));
////
////		} else if (resultCode == ConnectionResult.SERVICE_MISSING
////				|| resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED
////				|| resultCode == ConnectionResult.SERVICE_DISABLED) {
////			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode,
////					this, 1);
////			dialog.show();
////		}
//		
//		
//		
//		
////		LatLng fromPosition = new LatLng(13.687140112679154, 100.53525868803263);
////		LatLng toPosition = new LatLng(13.683660045847258, 100.53900808095932);
////
////		
////		GMapV2Direction md = new GMapV2Direction();
////
////		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
////		ArrayList<LatLng> directionPoint = md.getDirection(doc);
////		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
////
////		for(int i = 0 ; i < directionPoint.size() ; i++) {          
////		rectLine.add(directionPoint.get(i));
////		}
////
////		mMap.addPolyline(rectLine);
////		
////		
////		
////		super.onResume();
//		
//		
//		
//		
//		
//		
//	}
//	
	
	
	
	

	

}
