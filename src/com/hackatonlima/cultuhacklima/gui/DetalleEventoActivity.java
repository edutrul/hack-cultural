package com.hackatonlima.cultuhacklima.gui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.bean.Evento;
import com.hackatonlima.cultuhacklima.bean.QueryData;



public class DetalleEventoActivity extends FragmentActivity{
	
//	private GoogleMap mMap;
	private LatLng eventoLatLng;
	
	//GoogleMap mMap;
    //GMapV2Direction md;
    
	//LatLng fromPosition = new LatLng(13.687140112679154, 100.53525868803263);
	//LatLng toPosition = new LatLng(13.683660045847258, 100.53900808095932);


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_evento);
		
		Bundle bundle = getIntent().getBundleExtra(DashboardActivity.EVENTOS_LISTA_DATA_BUNDLE);
		List<Evento> eventListData = (List<Evento>) bundle.getSerializable(DashboardActivity.EVENTOS_LISTA_DATA);
		String eventoNombre = getIntent().getStringExtra(DashboardActivity.EVENTOS_NOMBRE);
		
		Evento evento = QueryData.getEventoFromEventoName(eventListData, eventoNombre);
		String[] arrStringEvento = {
				"Id: " + evento.getId(),
				"Categoria: " + evento.getCategoria(),
				"Nombre: " + evento.getNombre(),
				"Fecha Inicio: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date(evento.getFechaInicio() * 1000)),
				"Fecha Fin: " + new SimpleDateFormat("yyyy-MM-dd").format(new Date(evento.getFechaFin() * 1000)),
				"Hora Inicio: " + evento.getHoraInicio(),
				"Horario: " + evento.getHorario(),
				"Artista / Expositor: " + evento.getArtistaExpositor(),
				"Link Evento: " + evento.getLinkEvento(),
				"Lugar Venta: " + evento.getLugarVenta(),
				"Observaciones: " + evento.getObservaciones(),
				"Imagen A: " + evento.getImagenA(),
				"Imagen B: " + evento.getImagenB(),
				"imagen C: " + evento.getImagenC(),
				"Mapa longitud" + (evento.getLugar() != null ? evento.getLugar().getLongitud() : "fd"),
		};
		
		((TextView) findViewById(R.id.txt_categoria_detalle)).setText(evento.getCategoria());
		((TextView) findViewById(R.id.txt_nombre_detalle)).setText(evento.getNombre());
		((TextView) findViewById(R.id.txt_fecha_inicio_detalle)).setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(evento.getFechaInicio() * 1000)));
		((TextView) findViewById(R.id.txt_fecha_fin_detalle)).setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(evento.getFechaFin() * 1000)));
		((TextView) findViewById(R.id.txt_hora_inicio_detalle)).setText(evento.getHoraInicio());
		((TextView) findViewById(R.id.txt_horario_detalle)).setText(evento.getHorario());
		((TextView) findViewById(R.id.txt_artista_detalle)).setText(evento.getArtistaExpositor());
		((TextView) findViewById(R.id.txt_link_evento_detalle)).setText(evento.getLinkEvento());
		((TextView) findViewById(R.id.txt_lugar_venta_detalle)).setText(evento.getLugarVenta());
		((TextView) findViewById(R.id.txt_observaciones_detalle)).setText(evento.getObservaciones());
		
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
