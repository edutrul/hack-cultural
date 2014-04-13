package com.hackatonlima.cultuhacklima.gui;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

import com.hackatonlima.cultuhacklima.R;
import com.hackatonlima.cultuhacklima.location.MyLocation;
import com.hackatonlima.cultuhacklima.location.MyLocation.LocationResult;

public class SplashActivity extends Activity {

	private final int delayMillis = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				
				Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
				intent.putExtra(EventosLocalesActivity.FILTRO_POR, "splash");
				startActivity(intent);
				finish();
			}
		}, delayMillis);
        
    }
    
    

    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
    }
    
}
