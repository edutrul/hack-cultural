package com.hackatonlima.cultuhacklima.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.bean.Lugar;

public class BusquedaActivity extends FragmentActivity{


    public GoogleMap mMap;
	public List<Lugar> lugarListData;
	public Map<String, LatLng> markers;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_busqueda);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapAll)).getMap();
        Bundle bundle = getIntent().getBundleExtra(DashboardActivity.LUGAR_LISTA_DATA_BUNDLE);
		lugarListData = (List<Lugar>) bundle.getSerializable(DashboardActivity.LUGAR_LISTA_DATA);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.busqueda, menu);
		return true;
	}
	
	
	@Override
	protected void onResume() {
    	
		Iterator<Lugar> it = lugarListData.iterator();
		markers = new HashMap<String, LatLng>();
		while(it.hasNext())
		{
		    Lugar lugar = it.next();
		    markers.put(lugar.getNombre(), new LatLng(lugar.getLatitud(), lugar.getLongitud()));
		}
    	
//    	
//    	markers = new HashMap<String, LatLng>();
//    	
//    	markers.put("DOTA 2", new LatLng(-31.80, 115.76));
//    	markers.put("DOTA 3", new LatLng(-31.70, 115.66));
//    	markers.put("Yo", new LatLng(Double.parseDouble(DashboardActivity.lat), 
//    		Double.parseDouble(DashboardActivity.lon)));
    	
    	Iterator iter = markers.entrySet().iterator();
    	while (iter.hasNext()) {
    		Map.Entry mEntry = (Map.Entry) iter.next();
			int resultCode = GooglePlayServicesUtil
					.isGooglePlayServicesAvailable(getApplicationContext());
			if (resultCode == ConnectionResult.SUCCESS) {
				// Construct a CameraPosition focusing on Mountain View and animate
				// the camera to that position.
				CameraPosition cameraPosition = new CameraPosition.Builder()
						.target((LatLng)mEntry.getValue()) // Sets the center of the map to
												// Mountain View
						.zoom(15) // Sets the zoom
						.bearing(60) // Sets the orientation of the camera to east
						.tilt(30) // Sets the tilt of the camera to 30 degrees
						.build(); // Creates a CameraPosition from the builder
				mMap.animateCamera(CameraUpdateFactory
						.newCameraPosition(cameraPosition));
	
				mMap.addMarker(new MarkerOptions().position((LatLng)mEntry.getValue()).title(
						(String)mEntry.getKey()));
	
			} else if (resultCode == ConnectionResult.SERVICE_MISSING
					|| resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED
					|| resultCode == ConnectionResult.SERVICE_DISABLED) {
				Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode,
						this, 1);
				dialog.show();
			}
			super.onResume();
			
    	}
	}

}
