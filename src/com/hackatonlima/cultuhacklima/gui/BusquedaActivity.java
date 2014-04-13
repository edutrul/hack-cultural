package com.hackatonlima.cultuhacklima.gui;

import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.R.layout;
import com.hackatonlima.cultuhacklima.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BusquedaActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_busqueda);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.busqueda, menu);
		return true;
	}

}
