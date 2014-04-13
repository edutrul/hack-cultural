package com.hackatonlima.cultuhacklima.gui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
import com.hackatonlima.cultuhacklima.bean.Evento;
import com.hackatonlima.cultuhacklima.bean.QueryData;
import com.hackatonlima.cultuhacklima.location.GMapV2Direction;
import com.hackatonlima.cultuhacklima.location.Social;



public class DetalleEventoActivity extends FragmentActivity{
	

	GoogleMap mMap;
    GMapV2Direction md;
    
    LatLng fromPosition = new LatLng(Double.parseDouble(DashboardActivity.lat),
    		Double.parseDouble(DashboardActivity.lon) );
    LatLng toPosition; 
	
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_evento);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        
        md = new GMapV2Direction();
        mMap = ((MapFragment) getFragmentManager().findFragmentById(
                R.id.mapCultuHackLima)).getMap();
		
		
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
		
		if (evento.getLugar() != null) {
		    toPosition = new LatLng(evento.getLugar().getLatitud(), evento.getLugar().getLongitud());
		}
		else {
			fromPosition = new LatLng(13.687140112679154, 100.53525868803263);
			toPosition = new LatLng(13.683660045847258, 100.53900808095932);
		}
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
	

	
	public void dota(View view){
		Log.v("GOT","Got it");
	}
	 public void createNotification(View view) {
		    // Prepare intent which is triggered if the
		    // notification is selected
		    Intent intent = new Intent(this, NotificationReceiverActivity.class);
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
	 public void compartir(View view){
		 Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
		    sharingIntent.setType("text/plain");
		    String shareBody = "InkaApp en la #HackatonLima";
		    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
		    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
		startActivity(Intent.createChooser(sharingIntent, "Share via"));
		}

	

}
