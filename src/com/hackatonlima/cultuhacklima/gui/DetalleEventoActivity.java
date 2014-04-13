package com.hackatonlima.cultuhacklima.gui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hackatonlima.cultuhacklima.R;

public class DetalleEventoActivity extends FragmentActivity{
	
	private GoogleMap mMap;
	private LatLng eventoLatLng;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_evento);
		
		Intent intent = getIntent();
		String result = intent.getStringExtra("evento");
		Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
		
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.mapCultuHackLima)).getMap();
		eventoLatLng = new LatLng(-12.10363400,-76.97180720);
		
	}
	
	@Override
	protected void onResume() {
		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());
		if (resultCode == ConnectionResult.SUCCESS) {
			// Construct a CameraPosition focusing on Mountain View and animate
			// the camera to that position.
			CameraPosition cameraPosition = new CameraPosition.Builder()
					.target(eventoLatLng) // Sets the center of the map to
											// Mountain View
					.zoom(17) // Sets the zoom
					.bearing(90) // Sets the orientation of the camera to east
					.tilt(30) // Sets the tilt of the camera to 30 degrees
					.build(); // Creates a CameraPosition from the builder
			mMap.animateCamera(CameraUpdateFactory
					.newCameraPosition(cameraPosition));

			mMap.addMarker(new MarkerOptions().position(eventoLatLng).title(
					"Panicoop"));

		} else if (resultCode == ConnectionResult.SERVICE_MISSING
				|| resultCode == ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED
				|| resultCode == ConnectionResult.SERVICE_DISABLED) {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(resultCode,
					this, 1);
			dialog.show();
		}
		super.onResume();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalle_evento, menu);
		return true;
	}

}
